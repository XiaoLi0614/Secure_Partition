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

public class PartitionMethod implements PartitionVisitor{
    Command resultAST;
    HashMap<Node, PartitionProcess> partitionIntermediate; // each node has a three element intermediate structure
    int administrativeM;

    public String newMName(){
        return "m" + String.valueOf(administrativeM++);
    }

    public PartitionMethod(){
        partitionIntermediate = new HashMap<>();
        administrativeM = 0;
    }

    public Object visitDispatch(Expression expression) {
        return expression.accept(expressionB);
    }
    public ExpressionB expressionB =  new ExpressionB();
    public class ExpressionB implements ExpressionVisitor<Object> {
        public class GIdB implements GIdVisitor<Object>{
            @Override
            public Object visit(Id id){
                return null;}
        }
        GIdB gIdB = new GIdB();
        @Override
        public Object visit(GId gId){ return gId.accept(gIdB); }

        public class LiteralB implements LiteralVisitor<Object>{
            @Override
            public Object visit(IntLiteral intLiteral){
                PartitionProcess resultP = new PartitionProcess(null, new HashSet<>(), new ExpSt(intLiteral));
                partitionIntermediate.put(intLiteral, resultP);
                return intLiteral;
            }
        }
        LiteralB literalB = new LiteralB();
        @Override
        public Object visit(Literal literal){ return literal.accept(literalB); }

        public class BinaryOpB implements BinaryOpVisitor<Object>{
            @Override
            public Object visit(Plus plus){
                Expression resultExpression = new Plus((Expression) visitDispatch(plus.operand1), (Expression) visitDispatch(plus.operand2));
                //add the free variables from two different operators
                HashSet<Var> resultFreeVariables = new HashSet<>(partitionIntermediate.get(plus.operand1).getFreeVariables());
                resultFreeVariables.addAll(partitionIntermediate.get(plus.operand2).getFreeVariables());
                PartitionProcess resultP = new PartitionProcess(null, resultFreeVariables, new ExpSt(plus));
                partitionIntermediate.put(plus, resultP);
                return resultExpression;
            }
        }
        BinaryOpB binaryOpB = new BinaryOpB();
        @Override
        public Object visit(BinaryOp binaryOp){ return binaryOp.accept(binaryOpB); }

        @Override
        public Object visit(Var var){
            HashSet<Var> resultFreeVariables = new HashSet<>();
            resultFreeVariables.add(var);

            PartitionProcess resultP = new PartitionProcess(null, resultFreeVariables, new ExpSt(var));
            partitionIntermediate.put(var, resultP);
            return var;
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

            PartitionProcess resultP = new PartitionProcess(null, resultFreeVariables, new ExpSt(conditional));
            partitionIntermediate.put(conditional, resultP);
            return resultExpression;
        }
    }

    public Object visitDispatch(Command command) {
        return command.accept(commandB);
    }
    public CommandB commandB =  new CommandB();
    public class CommandB implements CommandVisitor<Object> {
        @Override
        public Object visit(ExpSt expSt){
            Command resultCommand = new ExpSt((Expression)visitDispatch(expSt.expression));
            partitionIntermediate.put(expSt, partitionIntermediate.get(expSt.expression));
            return resultCommand;
        }

        @Override
        public Object visit(If iF){
            return new If((Expression)visitDispatch(iF.condition),
                    (Command)visitDispatch(iF.command1),
                    (Command)visitDispatch(iF.command2));
        }

        @Override
        public Object visit(Sequence sequence){
            return new Sequence((Command)visitDispatch(sequence.command1),
                    (Command)visitDispatch(sequence.command2));
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
            
            MethodDefinition newDefinition = new MethodDefinition(newMName(),
                    partitionIntermediate.get(singleCall.nestedCommand).getFreeVariables().toArray(),
                    );
            return resultCommand;
        }
    }

    @Override
    public Object visit(Command command){ return command.accept(commandB); }
}
