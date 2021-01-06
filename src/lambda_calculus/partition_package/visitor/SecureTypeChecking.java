package lambda_calculus.partition_package.visitor;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
import lesani.collection.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SecureTypeChecking implements PartitionVisitor{
    HashMap<Node, envForTypeCheck> environment; // each node has an environment for type checking
    // \tau_x * \tau_argus -> \tau_2
    //the arraylist has \tau_x and \tau_2. The hashmap has \tau_2
    HashMap<String, Pair<ArrayList<CIAType>, HashMap<String, CIAType>>> methodType;
    // the first string is for object name, the second string is for method name, the last one is for the arguments
    HashMap<String, HashMap<String, HashMap<String, CIAType>>> objectMethodType;

    public SecureTypeChecking(){
        environment = new HashMap<>();
        methodType = new HashMap<>();
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
                    CIAType inter = environment.get(plus.operand1).getGamma().get(plus.operand1.toString()).
                            ciaJoin(environment.get(plus.operand2).getGamma().get(plus.operand2.toString()));
                    Boolean interB = inter.ciaLeq(environment.get(plus).getGamma().get(plus.toString()));
                    return resultB & interB;
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
            //the first step is to set the environment for the dispatched commands
            environment.put(conditional.condition, environment.get(conditional).clone());
            Boolean resultB = (Boolean) visitDispatch(conditional.condition);

            environment.put(conditional.ifExp, environment.get(conditional).clone());
            CIAType temp1 = environment.get(conditional.ifExp).getCurrentContext().ciaJoin(environment.get(conditional.condition).getGamma().get(conditional.condition.toString()));
            environment.get(conditional.ifExp).setCurrentContext(temp1);
            environment.put(conditional.elseExp, environment.get(conditional).clone());
            CIAType temp2 = environment.get(conditional.elseExp).getCurrentContext().ciaJoin(environment.get(conditional.condition).getGamma().get(conditional.condition.toString()));
            environment.get(conditional.elseExp).setCurrentContext(temp2);

            resultB &= (Boolean)visitDispatch(conditional.ifExp) & (Boolean)visitDispatch(conditional.elseExp);

            if(environment.get(conditional).getGamma().get(conditional.toString()) != null){
                CIAType inter = environment.get(conditional.ifExp).getGamma().get(conditional.ifExp.toString()).
                        ciaJoin(environment.get(conditional.elseExp).getGamma().get(conditional.elseExp.toString())).
                        ciaJoin(environment.get(conditional.condition).getGamma().get(conditional.toString()));
                Boolean interB = inter.ciaLeq(environment.get(conditional).getGamma().get(conditional.toString()));
                return resultB & interB;
            }
            //currently we do not let user specify the type for intermediate result.
            //Instead we infer them
            else {
                environment.get(conditional).getGamma().put(conditional.toString(),
                        environment.get(conditional.ifExp).getGamma().get(conditional.ifExp.toString()).
                                ciaJoin(environment.get(conditional.elseExp).getGamma().get(conditional.elseExp.toString())).
                                ciaJoin(environment.get(conditional.condition).getGamma().get(conditional.condition.toString())));
                return resultB;
            }
        }
    }

    public Object visitDispatch(Command command) {
        return command.accept(commandT);
    }
    public CommandT commandT =  new CommandT();
    public class CommandT implements CommandVisitor<Object> {
        @Override
        public Object visit(ExpSt expSt){
            environment.put(expSt.expression, environment.get(expSt).clone());
            Boolean resultB = (Boolean) visitDispatch(expSt.expression);
            return resultB;
        }

        @Override
        public Object visit(If iF){
            //the first step is to set the environment for the dispatched commands
            environment.put(iF.condition, environment.get(iF).clone());
            Boolean resultB = (Boolean) visitDispatch(iF.condition);

            environment.put(iF.command1, environment.get(iF).clone());
            CIAType temp1 = environment.get(iF.command1).getCurrentContext().ciaJoin(environment.get(iF.condition).getGamma().get(iF.condition.toString()));
            environment.get(iF.command1).setCurrentContext(temp1);
            environment.put(iF.command2, environment.get(iF).clone());
            CIAType temp2 = environment.get(iF.command2).getCurrentContext().ciaJoin(environment.get(iF.condition).getGamma().get(iF.condition.toString()));
            environment.get(iF.command2).setCurrentContext(temp2);

            resultB &= (Boolean)visitDispatch(iF.command1) & (Boolean)visitDispatch(iF.command2);

            if(environment.get(iF).getGamma().get(iF.toString()) != null){
                CIAType inter = environment.get(iF.command1).getGamma().get(iF.command1.toString()).
                        ciaJoin(environment.get(iF.command2).getGamma().get(iF.command2.toString())).
                        ciaJoin(environment.get(iF.condition).getGamma().get(iF.toString()));
                Boolean interB = inter.ciaLeq(environment.get(iF).getGamma().get(iF.toString()));
                return resultB & interB;
            }
            //currently we do not let user specify the type for intermediate result.
            //Instead we infer them
            else {
                environment.get(iF).getGamma().put(iF.toString(),
                        environment.get(iF.command1).getGamma().get(iF.command1.toString()).
                                ciaJoin(environment.get(iF.command2).getGamma().get(iF.command2.toString())).
                                ciaJoin(environment.get(iF.condition).getGamma().get(iF.condition.toString())));
                return resultB;
            }
        }

        @Override
        public Object visit(Sequence sequence){
            environment.put(sequence.command1, environment.get(sequence).clone());
            Boolean resultB = (Boolean) visitDispatch(sequence.command1);
            environment.put(sequence.command2, environment.get(sequence).clone());
            quorumDef tempA = environment.get(sequence.command2).getCurrentContext().
                    aMeet(environment.get(sequence.command1).getGamma().get(sequence.command1.toString()).getAvailability());
            environment.get(sequence.command2).getCurrentContext().setAvailability(tempA);
            resultB &= (Boolean) visitDispatch(sequence.command2);

            if(environment.get(sequence).getGamma().get(sequence.toString()) != null){
                CIAType inter = environment.get(sequence.command1).getGamma().get(sequence.command1.toString()).
                        ciaJoin(environment.get(sequence.command2).getGamma().get(sequence.command2.toString()));
                Boolean interB = inter.ciaLeq(environment.get(sequence).getGamma().get(sequence.toString()));
                return resultB & interB;
            }
            //currently we do not let user specify the type for intermediate result.
            //Instead we infer them
            else {
                environment.get(sequence).getGamma().put(sequence.toString(),
                        environment.get(sequence.command1).getGamma().get(sequence.command1.toString()).
                                ciaJoin(environment.get(sequence.command2).getGamma().get(sequence.command2.toString()));
                return resultB;
            }
        }

        @Override
        //method and object calls are all in the single call node
        public Object visit(SingleCall singleCall){
            //when this is a method call
            Boolean resultB = true;
            if(singleCall.objectName.toString() =="this"){
                //we need to infer the method type for this method
                if(methodType.get(singleCall.methodName.toString()) == null ||
                        methodType.get(singleCall.methodName.toString()).element1.size() == 0){
                    methodType.put(singleCall.methodName.toString(), new Pair<>(new ArrayList<>(), new HashMap<>()));
                    methodType.get(singleCall.methodName.toString()).element1.add(environment.get(singleCall).getCurrentContext().clone());
                    for(Expression args: singleCall.args){
                        environment.put(args, environment.get(singleCall).clone());
                        resultB &= (Boolean) visitDispatch(args);
                        //todo: how to infer the a1 out of Availability function
                        //we infer the \tau_1 as the join of \tau and \tau_x
                        CIAType temp = environment.get(singleCall).getCurrentContext().
                                ciaJoin(environment.get(args).getGamma().get(args.toString()));
                        methodType.get(singleCall.methodName.toString()).element2.put(args.toString(), temp);
                        if(!environment.get(singleCall).getMMap().get(singleCall.methodName.toString()).element2.
                                availabilityProj(temp.getAvailability().getQuorum(), environment.get(singleCall).getCurrentHost())){
                            System.out.println("Inferred availability does not mee the requirement.");
                        }
                    }
                    return resultB;
                }
                //we have the typed context for the method
                else {
                    CIAType temp0 = methodType.get(singleCall.methodName.toString()).element1.get(0);
                    resultB &= environment.get(singleCall).getCurrentContext().ciaLeq(temp0);

                    //when there is no argument for the method
                    if(singleCall.args == null || singleCall.args.length == 0){
                        return resultB;
                    }
                    else {
                        for(Expression argE : singleCall.args){
                            environment.put(argE, environment.get(singleCall).clone());
                            resultB &= (Boolean) visitDispatch(argE);
                            resultB &= environment.get(argE).getGamma().get(argE.toString()).
                                    ciaJoin(environment.get(singleCall).getCurrentContext()).
                                    ciaLeq(methodType.get(singleCall.methodName.toString()).element2.get(argE.toString()));
                            resultB &= environment.get(singleCall).getMMap().get(singleCall.methodName.toString()).element2.
                                    availabilityProj(environment.get(argE).getGamma().get(argE.toString()).getAvailability().getQuorum(),
                                            environment.get(singleCall).getCurrentHost());
                        }
                        return resultB;
                    }
                }
            }
            //this is an object call
            else {
                String Oname = singleCall.objectName.toString();
                String OMName = singleCall.methodName.toString();
                //we need to infer the method type for this method
                if(objectMethodType.get(Oname).get(OMName) == null){
                    for(Expression args: singleCall.args){
                        environment.put(args, environment.get(singleCall).clone());
                        resultB &= (Boolean) visitDispatch(args);
                        //todo: how to infer the a1 out of Availability function
                        //we infer the \tau_1 as the join of \tau and \tau_x
                        CIAType temp = environment.get(singleCall).getCurrentContext().
                                ciaJoin(environment.get(args).getGamma().get(args.toString()));
                        objectMethodType.get(Oname).get(OMName).put(args.toString(), temp);
                        //todo: don't forget to initialization
                        if(!environment.get(singleCall).getOMap().get(Oname + OMName).element2.
                                availabilityProj(temp.getAvailability().getQuorum(), environment.get(singleCall).getCurrentHost())){
                            System.out.println("Inferred availability does not mee the requirement.");
                        }
                    }
                    return resultB;
                }
                //we have the typed context for the method
                else {
                    //when there is no argument for the method
                    if(singleCall.args == null || singleCall.args.length == 0){
                        return resultB;
                    }
                    else {
                        for(Expression argE : singleCall.args){
                            environment.put(argE, environment.get(singleCall).clone());
                            resultB &= (Boolean) visitDispatch(argE);
                            resultB &= environment.get(argE).getGamma().get(argE.toString()).
                                    ciaJoin(environment.get(singleCall).getCurrentContext()).
                                    ciaLeq(objectMethodType.get(Oname).get(OMName).get(argE.toString()));
                            resultB &= environment.get(singleCall).getMMap().get(singleCall.methodName.toString()).element2.
                                    availabilityProj(environment.get(argE).getGamma().get(argE.toString()).getAvailability().getQuorum(),
                                            environment.get(singleCall).getCurrentHost());
                        }
                        return resultB;
                    }
                }
            }
        }
    }

    @Override
    public Object visit(Command command){ return command.accept(commandT); }

    //public Boolean fieldCheck(){}

    public Boolean methodCheck(MethodDefinition m, nodeSet h){
        Boolean resultB = true;
        //set up the input arguments to type the body of method
        for(Expression arg : m.freeVars){
            environment.get(m.body).getGamma().put(arg.toString(),
                    methodType.get(m.thisMethodName.toString()).element2.get(arg.toString()));
        }
        //set the current hosts
        environment.get(m.body).setCurrentHost(h);

        resultB &= (Boolean) visitDispatch(m.body);
        resultB &= environment.get(m.body).getGamma().get(m.body.toString()).
                ciaLeq(methodType.get(m.thisMethodName.toString()).element1.get(1));
        for(CIAType argT : methodType.get(m.thisMethodName.toString()).element2.values()){
            resultB &= argT.ciaLeq(methodType.get(m.thisMethodName.toString()).element1.get(0));
            resultB &= environment.get(m.body).getMMap().get(m.thisMethodName.toString()).element2.
                    methodIntegrity(argT.getIntegrity().getQuorum());
        }
        resultB &= methodType.get(m.thisMethodName.toString()).element1.get(0).
                ciaJoin(methodType.get(m.thisMethodName.toString()).element1.get(1)).
                cLeq(environment.get(m).getCurrentHost());
        return resultB;
    }

    public Boolean classTypeCheck(HashMap<MethodDefinition, Pair<nodeSet, quorumDef>> methods, ){
        Boolean r = true;
        SecureTypeChecking b = new SecureTypeChecking();
        //first do field check then we do method check
        //set the M(H, Q) and O(Q1, Q2)
        b.
        return true;
    }
}

