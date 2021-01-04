package lambda_calculus.partition_package.visitor;

import lambda_calculus.partition_package.tree.MethodDefinition;
import lambda_calculus.partition_package.tree.Node;
import lambda_calculus.partition_package.tree.PartitionProcess;
import lambda_calculus.partition_package.tree.command.*;
import lambda_calculus.partition_package.tree.expression.Conditional;
import lambda_calculus.partition_package.tree.expression.Expression;
import lambda_calculus.partition_package.tree.expression.Var;
import lambda_calculus.partition_package.tree.expression.id.GId;
import lambda_calculus.partition_package.tree.expression.id.Id;
import lambda_calculus.partition_package.tree.expression.literal.IntLiteral;
import lambda_calculus.partition_package.tree.expression.literal.Literal;
import lambda_calculus.partition_package.tree.expression.op.BinaryOp;
import lambda_calculus.partition_package.tree.expression.op.Plus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SecureTypeChecking implements PartitionVisitor{
    HashMap<Node, envForTypeCheck> environment; // each node has an environment for type checking

    public SecureTypeChecking(){
        environment = new HashMap<>();
    }

    public Object visitDispatch(Expression expression) {
        return expression.accept(expressionT);
    }
    public ExpressionT expressionT =  new ExpressionT();
    public class ExpressionT implements ExpressionVisitor<Object> {
        public class GIdB implements GIdVisitor<Object>{
            @Override
            public Object visit(Id id){
                return true;}
        }
        GIdB gIdB = new GIdB();
        @Override
        public Object visit(GId gId){ return gId.accept(gIdB); }

        public class LiteralB implements LiteralVisitor<Object>{
            @Override
            public Object visit(IntLiteral intLiteral){
                //TODO: how to show the lowest and highest?
                return true;
            }
        }
        LiteralB literalB = new LiteralB();
        @Override
        public Object visit(Literal literal){ return literal.accept(literalB); }

        public class BinaryOpB implements BinaryOpVisitor<Object>{
            @Override
            public Object visit(Plus plus){
                environment.put(plus.operand1, environment.get(plus).clone());
                environment.put(plus.operand2, environment.get(plus).clone());
                Boolean resultB = (Boolean) visitDispatch(plus.operand1) & (Boolean) visitDispatch(plus.operand2);

                if(environment.get(plus).getGamma().get(plus.toString()) != null){
                    Boolean inter = environment.get(plus.operand1).getGamma().get(plus.operand1.toString()).
                            ciaJoin(environment.get(plus.operand2).getGamma().get(plus.operand2.toString())).
                            ciaLeq(environment.get(plus).getGamma().get(plus.toString()));
                    return resultB & inter;
                }
                //currently we do not let user specify the type for intermediate result.
                //Instead we infer them
                else {
                    environment.get(plus).getGamma().put(plus.toString(),
                            environment.get(plus.operand1).getGamma().get(plus.operand1.toString()).
                            ciaJoin(environment.get(plus.operand2).getGamma().get(plus.operand2.toString())));
                    return resultB;
                }
            }
        }
        BinaryOpB binaryOpB = new BinaryOpB();
        @Override
        public Object visit(BinaryOp binaryOp){ return binaryOp.accept(binaryOpB); }

        @Override
        public Object visit(Var var){
            if(environment.get(var).getGamma().get(var.toString()) != null){

            }
            else {
                //todo: the same as int, have the bottom type
            }
            return true;
        }

        @Override
        public Object visit(Conditional conditional){
            Expression resultExpression = new Conditional((Expression)visitDispatch(conditional.condition),
                    (Expression)visitDispatch(conditional.ifExp),
                    (Expression)visitDispatch(conditional.elseExp));
            //get all the free variables from this if expression
            HashSet<Var> resultFreeVariables = new HashSet<>(partitionIntermediate.get(conditional.condition).getFreeVariables());
            resultFreeVariables.addAll(partitionIntermediate.get(conditional.ifExp).getFreeVariables());
            resultFreeVariables.addAll(partitionIntermediate.get(conditional.elseExp).getFreeVariables());

            PartitionProcess resultP = new PartitionProcess(new ArrayList<>(), resultFreeVariables, new ExpSt(conditional));
            partitionIntermediate.put(conditional, resultP);
            return resultExpression;
        }
    }

    public Object visitDispatch(Command command) {
        return command.accept(commandP);
    }
    public CommandP commandP =  new CommandP();
    public class CommandP implements CommandVisitor<Object> {
        @Override
        public Object visit(ExpSt expSt){
            Command resultCommand = new ExpSt((Expression)visitDispatch(expSt.expression));
            partitionIntermediate.put(expSt, partitionIntermediate.get(expSt.expression));
            return resultCommand;
        }

        @Override
        public Object visit(If iF){
            //the first step is to set the environment for the dispatched commands
            environment.put(iF.condition, environment.get(iF).clone());
            Boolean resultB = (Boolean) visitDispatch(iF.condition);

            environment.put(iF.command1, environment.get(iF).clone());
            CIAType temp1 = environment.get(iF.command1).getCurrentContext().ciaJoin(environment.get(iF.condition).getGamma().get(iF.condition));
            
            environment.put(iF.command2, environment.get(iF).clone());

            resultB &= (Boolean)visitDispatch(iF.command1) & (Boolean)visitDispatch(iF.command2);


            return resultB;
        }

        @Override
        public Object visit(Sequence sequence){
            Command resultCommand = new Sequence((Command)visitDispatch(sequence.command1),
                    (Command)visitDispatch(sequence.command2));
            ArrayList<MethodDefinition> resultDefs = new ArrayList<>(partitionIntermediate.get(sequence.command1).getMethodDefinitions());
            resultDefs.addAll(partitionIntermediate.get(sequence.command2).getMethodDefinitions());
            HashSet<Var> resultFreeVars = new HashSet<>(partitionIntermediate.get(sequence.command1).getFreeVariables());
            resultFreeVars.addAll(partitionIntermediate.get(sequence.command2).getFreeVariables());
            Sequence callBackCommand = new Sequence(partitionIntermediate.get(sequence.command1).getCallBackName(),
                    partitionIntermediate.get(sequence.command2).getCallBackName());

            PartitionProcess resultProcess = new PartitionProcess(resultDefs, resultFreeVars, callBackCommand);
            partitionIntermediate.put(sequence, resultProcess);

            return resultCommand;
        }

        @Override
        public Object visit(SingleCall singleCall){
            Command resultCommand = new SingleCall((Id)singleCall.objectName,
                    (Id)singleCall.methodName,
                    singleCall.args,
                    singleCall.administrativeX,
                    (Command)visitDispatch(singleCall.nestedCommand));
            //get the method definitions from nested command
            ArrayList<MethodDefinition> resultDefinitions = new ArrayList<>(partitionIntermediate.get(singleCall.nestedCommand).getMethodDefinitions());
            HashSet<Var> freeVarSet = new HashSet<>(partitionIntermediate.get(singleCall.nestedCommand).getFreeVariables());
            if(singleCall.args == null || singleCall.args.length == 0){
            }
            else {
                for(Expression argE: singleCall.args){
                    visitDispatch(argE);
                    freeVarSet.addAll(partitionIntermediate.get(argE).getFreeVariables());
                }
            }
            Var toreRemoved = singleCall.administrativeX;
            for(Var v : freeVarSet){
                if(v.equals(toreRemoved)){
                    toreRemoved = v;
                }
            }
            freeVarSet.remove(toreRemoved);
            Var[] callBackFreeArgs = new Var[freeVarSet.size()];

            SingleCall callBackName = new SingleCall(newMName(), freeVarSet.toArray(callBackFreeArgs));
            MethodDefinition newDefinition = new MethodDefinition((Id) callBackName.methodName,
                    freeVarSet,
                    singleCall,
                    callBackName);
            newDefinition.addBody(partitionIntermediate.get(singleCall.nestedCommand).getCallBackName());
            resultDefinitions.add(newDefinition);
            partitionIntermediate.put(singleCall, new PartitionProcess(resultDefinitions, freeVarSet, callBackName));

            return resultCommand;
        }
    }

    @Override
    public Object visit(Command command){ return command.accept(commandP); }

    public Boolean classTypeChck(ArrayList<MethodDefinition> methods){
        SecureTypeChecking b = new SecureTypeChecking();
        b.visitDispatch(c);
        ArrayList<MethodDefinition> currentDefinitions = b.partitionIntermediate.get(c).getMethodDefinitions();

        return ;
    }
}
