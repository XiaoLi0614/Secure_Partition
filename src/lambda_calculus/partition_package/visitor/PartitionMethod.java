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
import lambda_calculus.partition_package.tree.expression.op.Compare;
import lambda_calculus.partition_package.tree.expression.op.Plus;
import lesani.collection.Pair;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PartitionMethod implements PartitionVisitor{
    //Command resultAST;
    HashMap<Node, PartitionProcess> partitionIntermediate; // each node has a three element intermediate structure
    int administrativeM;
    //the arraylist is a list of method definition names that call recursive method and need to be replaced.
    //eg: m2(): let x = a.read() in this.self(x)
    //ArrayList<String> recursionReplacement;

    public String newMName(){
        return "m" + String.valueOf(administrativeM++);
    }

    public PartitionMethod(){
        partitionIntermediate = new HashMap<>();
        administrativeM = 0;
        //recursionReplacement = new ArrayList<>();
    }

    public Object visitDispatch(Expression expression) {
        return expression.accept(expressionP);
    }
    public ExpressionP expressionP =  new ExpressionP();
    public class ExpressionP implements ExpressionVisitor<Object> {
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
                PartitionProcess resultP = new PartitionProcess(new ArrayList<>(), new HashSet<>(), new ExpSt(intLiteral));
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
                PartitionProcess resultP = new PartitionProcess(new ArrayList<>(), resultFreeVariables, new ExpSt(plus));
                partitionIntermediate.put(plus, resultP);
                return resultExpression;
            }

            @Override
            public Object visit(Compare compare){
                Expression resultExpression = new Compare(compare.operatorText, (Expression) visitDispatch(compare.operand1), (Expression) visitDispatch(compare.operand2));
                //add the free variables from two different operators
                HashSet<Var> resultFreeVariables = new HashSet<>(partitionIntermediate.get(compare.operand1).getFreeVariables());
                resultFreeVariables.addAll(partitionIntermediate.get(compare.operand2).getFreeVariables());
                PartitionProcess resultP = new PartitionProcess(new ArrayList<>(), resultFreeVariables, new ExpSt(compare));
                partitionIntermediate.put(compare, resultP);
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

            PartitionProcess resultP = new PartitionProcess(new ArrayList<MethodDefinition>(), resultFreeVariables, new ExpSt(var));
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
            Command resultCommand = new If((Expression)visitDispatch(iF.condition),
                    (Command)visitDispatch(iF.command1),
                    (Command)visitDispatch(iF.command2));
            ArrayList<MethodDefinition> resultDefs = new ArrayList<>(partitionIntermediate.get(iF.command1).getMethodDefinitions());
            resultDefs.addAll(partitionIntermediate.get(iF.command2).getMethodDefinitions());
            HashSet<Var> resultFreeVars = new HashSet<>(partitionIntermediate.get(iF.command1).getFreeVariables());
            resultFreeVars.addAll(partitionIntermediate.get(iF.command2).getFreeVariables());
            resultFreeVars.addAll(partitionIntermediate.get(iF.condition).getFreeVariables());
            If callBackCommand = new If(iF.condition,
                    partitionIntermediate.get(iF.command1).getCallBackName(),
                    partitionIntermediate.get(iF.command2).getCallBackName());

            PartitionProcess resultProcess = new PartitionProcess(resultDefs, resultFreeVars, callBackCommand);
            partitionIntermediate.put(iF, resultProcess);

            return resultCommand;
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
        //Change the sequence to the form more aligned with call instead of if
        //The only difference between call and sequence is that we do not pass more free variables to the continuation(in this situation the second statement in the sequence)
/*        @Override
        public Object visit(Sequence sequence){
            Command resultCommand = new Sequence((Command)visitDispatch(sequence.command1),
                    (Command)visitDispatch(sequence.command2));

            ArrayList<MethodDefinition> resultDefs = new ArrayList<>(partitionIntermediate.get(sequence.command1).getMethodDefinitions());
            resultDefs.addAll(partitionIntermediate.get(sequence.command2).getMethodDefinitions());

            HashSet<Var> resultFreeVars = new HashSet<>(partitionIntermediate.get(sequence.command1).getFreeVariables());
            resultFreeVars.addAll(partitionIntermediate.get(sequence.command2).getFreeVariables());

            //call back is the continuation
            Sequence callBackCommand = new Sequence(partitionIntermediate.get(sequence.command1).getCallBackName(),
                    partitionIntermediate.get(sequence.command2).getCallBackName());

            //we declare a new method
            MethodDefinition newDefinition = new MethodDefinition(newMName(),
                    resultFreeVars,
                    singleCall,
                    callBackName);
            newDefinition.addBody(partitionIntermediate.get(singleCall.nestedCommand).getCallBackName());
            resultDefinitions.add(newDefinition);

            PartitionProcess resultProcess = new PartitionProcess(resultDefs, resultFreeVars, callBackCommand);
            partitionIntermediate.put(sequence, resultProcess);

            return resultCommand;
        }*/

        @Override
        public Object visit(SingleCall singleCall){
            //if this is a method call, we do not need to create a new method call for it. Simply return
            if(singleCall.objectName.toString() == "this"){
                PartitionProcess resultP = new PartitionProcess(new ArrayList<>(), new HashSet<>(), singleCall);
                partitionIntermediate.put(singleCall, resultP);
                if(singleCall.methodName.toString() == "ret"){
                    MethodDefinition retDef = new MethodDefinition("ret", singleCall);
                    ArrayList<MethodDefinition> resultDefs = new ArrayList<>();
                    resultDefs.add(retDef);
                    HashSet<Var> freeVarForRet = new HashSet<>();
                    //freeVarForRet.addAll(retDef.freeVars);
                    //this is the ret method , we have one argument for ret method.
                    visitDispatch(singleCall.args[0]);
                    freeVarForRet.addAll(partitionIntermediate.get(singleCall.args[0]).getFreeVariables());
                    partitionIntermediate.put(singleCall, new PartitionProcess(resultDefs, freeVarForRet, singleCall));
                }
                //if(singleCall.methodName.toString() == "self"){
                    //recursionReplacement.add("m" + String.valueOf(administrativeM));
                //}
                return singleCall;
            }
            else {
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

                //we need to determine whether the method for the same object call already exist?
                //No. Because the object all is not functional.
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
    }

    @Override
    public Object visit(Command command){ return command.accept(commandP); }

    public ArrayList<MethodDefinition> methodSeparation(Command c){
        PartitionMethod b = new PartitionMethod();
        //b.recursionReplacement.put(wMethodName, new Pair<>("", new ArrayList<>()));
        b.visitDispatch(c);
        ArrayList<MethodDefinition> currentDefinitions = b.partitionIntermediate.get(c).getMethodDefinitions();
        // remove redundant ret
        for(int i = 0; i < currentDefinitions.size(); i++){
            if(currentDefinitions.get(i).thisMethodName.toString() == "ret" && i != 0){
                currentDefinitions.remove(i);
            }
        }

        //place the entrance method name for recursion
        for(int i = 0; i < currentDefinitions.size(); i++){
            currentDefinitions.get(i).recursionReplace = currentDefinitions.size();
        }

        //add one free variable for ret
        HashSet<Var> retV = new HashSet<>();
        retV.add(new Var("return"));
        currentDefinitions.get(0).freeVars = retV;
        currentDefinitions.get(0).addBody(new ExpSt(new Var("return")));

        //b.recursionReplacement.get(wMethodName).element1 = currentDefinitions.get(currentDefinitions.size()-1).thisMethodName.toString();
        //for()
        return currentDefinitions;
    }
}
