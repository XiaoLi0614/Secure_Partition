package lambda_calculus.partition_package.visitor;

import com.sun.org.apache.xpath.internal.operations.Bool;
import fj.Hash;
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
    //the arraylist has \tau_x and \tau_2. The hashmap has \tau_1
    HashMap<String, Pair<ArrayList<CIAType>, ArrayList<CIAType>>> methodType;
    // the first string is for object name, the second string is for method name, the last one is for the arguments
    //for return value the string is ret
    HashMap<String, HashMap<String, ArrayList<CIAType>>> objectMethodType;
    HashMap<String, CIAType> objUmb;
    //The object and method map(about host and arguments) does not change during the type inference / type checking
    HashMap<String, Pair<quorumDef, quorumDef>> OMap;
    HashMap<String, Pair<nodeSet, quorumDef>> MMap;
    Statistics statistics;
    CIAType botType;

    public SecureTypeChecking(){
        environment = new HashMap<>();
        methodType = new HashMap<>();
        objectMethodType = new HashMap<>();
        objUmb = new HashMap<>();
        OMap = new HashMap<>();
        MMap = new HashMap<>();
        statistics = new Statistics();
    }

    public Object visitDispatch(Expression expression) {
        return expression.accept(expressionT);
    }
    public ExpressionT expressionT =  new ExpressionT();
    public class ExpressionT implements ExpressionVisitor<Object> {
        public class GIdB implements GIdVisitor<Object>{
            @Override
            public Object visit(Id id){
                //CIAType dummy = new CIAType();
                //environment.get(id).getGamma().put(id.toString(), dummy.CIABot());
                environment.get(id).getGamma().put(id.toString(), botType);
                return true;}
        }
        GIdB gIdB = new GIdB();
        @Override
        public Object visit(GId gId){ return gId.accept(gIdB); }

        public class LiteralB implements LiteralVisitor<Object>{
            @Override
            public Object visit(IntLiteral intLiteral){
                //CIAType dummy = new CIAType();
                //environment.get(intLiteral).getGamma().put(intLiteral.toString(), dummy.CIABot());
                environment.get(intLiteral).getGamma().put(intLiteral.toString(), botType);
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
                    statistics.addCons();
                    return resultB & interB;
                }
                //currently we do not let user specify the type for intermediate result.
                //Instead we infer them
                else {
                    environment.get(plus).getGamma().put(plus.toString(),
                            environment.get(plus.operand1).getGamma().get(plus.operand1.toString()).
                            ciaJoin(environment.get(plus.operand2).getGamma().get(plus.operand2.toString())));
                    statistics.addCons();
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
                //CIAType dummy = new CIAType();
                //environment.get(var).getGamma().put(var.toString(), dummy.CIABot());
                environment.get(var).getGamma().put(var.toString(), botType);
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
                statistics.addCons();
                return resultB & interB;
            }
            //currently we do not let user specify the type for intermediate result.
            //Instead we infer them
            else {
                environment.get(conditional).getGamma().put(conditional.toString(),
                        environment.get(conditional.ifExp).getGamma().get(conditional.ifExp.toString()).
                                ciaJoin(environment.get(conditional.elseExp).getGamma().get(conditional.elseExp.toString())).
                                ciaJoin(environment.get(conditional.condition).getGamma().get(conditional.condition.toString())));
                statistics.addCons();
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
            environment.get(expSt).getGamma().put(expSt.toString(), environment.get(expSt.expression).getGamma().get(expSt.expression.toString()));
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
                statistics.addCons();
                return resultB & interB;
            }
            //currently we do not let user specify the type for intermediate result.
            //Instead we infer them
            else {
                environment.get(iF).getGamma().put(iF.toString(),
                        environment.get(iF.command1).getGamma().get(iF.command1.toString()).
                                ciaJoin(environment.get(iF.command2).getGamma().get(iF.command2.toString())).
                                ciaJoin(environment.get(iF.condition).getGamma().get(iF.condition.toString())));
                statistics.addCons();
                return resultB;
            }
        }

        @Override
        public Object visit(Sequence sequence){
            //we first type the expression e1, then bind the type \tau_1 with the administrative x
            environment.put(sequence.command1, environment.get(sequence).clone());
            Boolean resultB = (Boolean) visitDispatch(sequence.command1);
            environment.put(sequence.command2, environment.get(sequence).clone());
            //todo: may need to generalize the to the command instead of only the singlecall
            if(sequence.command1 instanceof SingleCall){
                environment.get(sequence.command2).getGamma().put(((SingleCall) sequence.command1).administrativeX.toString(),
                        environment.get(sequence.command1).getGamma().get(sequence.command1.toString()));
            }

            quorumDef tempA = environment.get(sequence.command2).getCurrentContext().
                    aMeet(environment.get(sequence.command1).getGamma().get(sequence.command1.toString()).getAvailability());
            environment.get(sequence.command2).getCurrentContext().setAvailability(tempA);
            resultB &= (Boolean) visitDispatch(sequence.command2);

            if(environment.get(sequence).getGamma().get(sequence.toString()) != null){
                CIAType inter = environment.get(sequence.command1).getGamma().get(sequence.command1.toString()).
                        ciaJoin(environment.get(sequence.command2).getGamma().get(sequence.command2.toString()));
                Boolean interB = inter.ciaLeq(environment.get(sequence).getGamma().get(sequence.toString()));
                statistics.addCons();
                return resultB & interB;
            }
            //currently we do not let user specify the type for intermediate result.
            //Instead we infer them
            else {
                environment.get(sequence).getGamma().put(sequence.toString(),
                        environment.get(sequence.command1).getGamma().get(sequence.command1.toString()).
                                ciaJoin(environment.get(sequence.command2).getGamma().get(sequence.command2.toString())));
                statistics.addCons();
                return resultB;
            }
        }

        @Override
        //method and object calls are all in the single call node
        public Object visit(SingleCall singleCall){
            if(environment.get(singleCall) == null){
                environment.put(singleCall, new envForTypeCheck());
            }

            //when this is a method call
            Boolean resultB = true;
            if(singleCall.objectName.toString() =="this"){
                //we need to infer the method type for this method
                if(methodType.get(singleCall.methodName.toString()) == null ||
                        methodType.get(singleCall.methodName.toString()).element1.size() == 0){
                    methodType.put(singleCall.methodName.toString(), new Pair<>(new ArrayList<>(), new ArrayList<>()));
                    methodType.get(singleCall.methodName.toString()).element1.add(environment.get(singleCall).getCurrentContext().clone());
                    for(int a = 0; a < singleCall.args.length; a++){
                        Expression args = singleCall.args[a];
                        environment.put(args, environment.get(singleCall).clone());
                        resultB &= (Boolean) visitDispatch(args);
                        //todo: how to infer the a1 out of Availability function
                        //we infer the \tau_1 as the join of \tau and \tau_x
                        CIAType temp = environment.get(singleCall).getCurrentContext().
                                ciaJoin(environment.get(args).getGamma().get(args.toString()));
                        methodType.get(singleCall.methodName.toString()).element2.add(a, temp);
                        statistics.startACheck();
                        if(!MMap.get(singleCall.methodName.toString()).element2.
                                availabilityProj(temp.getAvailability().getQuorum(), environment.get(singleCall).getCurrentHost())){
                            System.out.println("Inferred availability does not mee the requirement.");
                        }
                        statistics.endACheck();
                        statistics.addCons();
                    }
                    //set the return value for the method
                    //todo: we have not set the return type for the method at this point
                    //methodType.get(singleCall.methodName.toString()).element1.get(1) = null now/
                    environment.get(singleCall).getGamma().put(singleCall.toString(), methodType.get(singleCall.methodName.toString()).element1.get(1));
                    return resultB;
                }
                //we have the typed context for the method
                else {
                    CIAType temp0 = methodType.get(singleCall.methodName.toString()).element1.get(0);
                    resultB &= environment.get(singleCall).getCurrentContext().ciaLeq(temp0);
                    statistics.addCons();

                    //when there is no argument for the method
                    //we need to add dummy argument for the implicit constraints
                    //for example, \tau_1 can have the same type as \tau_x' and the availability constraint is on the A(\tau_x')
                    if(singleCall.args == null || singleCall.args.length == 0){
                        //resultB &= MMap.get(singleCall.methodName.toString()).element2.
                                //availabilityProj(methodType.get(singleCall.methodName.toString()).element1.get(0).getAvailability().getQuorum(),
                                        //environment.get(singleCall).getCurrentHost());
                        //now we have changed the dummy variable to the argument named "bot" in the mAName
                        //now all the implicit constraints are forced by the bot argument
                        statistics.startACheck();
                        resultB &= MMap.get(singleCall.methodName.toString()).element2.
                                availabilityProj(methodType.get(singleCall.methodName.toString()).element2.get(0).getAvailability().getQuorum(),
                                        environment.get(singleCall).getCurrentHost());
                        statistics.endACheck();
                        statistics.addCons();
                        resultB &= environment.get(singleCall).getCurrentContext().ciaLeq(methodType.get(singleCall.methodName.toString()).element2.get(0));
                        statistics.addCons();

                        //set the return value for the method
                        environment.get(singleCall).getGamma().put(singleCall.toString(), methodType.get(singleCall.methodName.toString()).element1.get(1));
                        return resultB;
                    }
                    else {
                        for(int a = 0; a < singleCall.args.length; a++){
                            Expression argE = singleCall.args[a];
                            environment.put(argE, environment.get(singleCall).clone());
                            resultB &= (Boolean) visitDispatch(argE);
                            resultB &= environment.get(argE).getGamma().get(argE.toString()).
                                    ciaJoin(environment.get(singleCall).getCurrentContext()).
                                    ciaLeq(methodType.get(singleCall.methodName.toString()).element2.get(a));
                            statistics.addCons();
                            statistics.startACheck();
                            resultB &= MMap.get(singleCall.methodName.toString()).element2.
                                    availabilityProj(methodType.get(singleCall.methodName.toString()).element2.get(a).getAvailability().getQuorum(),
                                            environment.get(singleCall).getCurrentHost());
                            statistics.endACheck();
                            statistics.addCons();
                        }
                        //set the return value for the method
                        environment.get(singleCall).getGamma().put(singleCall.toString(), methodType.get(singleCall.methodName.toString()).element1.get(1));
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
                    for(int e = 0; e < singleCall.args.length; e++){
                        Expression args = singleCall.args[e];
                        environment.put(args, environment.get(singleCall).clone());
                        resultB &= (Boolean) visitDispatch(args);
                        //todo: how to infer the a1 out of Availability function
                        //we infer the \tau_1 as the join of \tau and \tau_x
                        CIAType temp = environment.get(singleCall).getCurrentContext().
                                ciaJoin(environment.get(args).getGamma().get(args.toString()));
                        objectMethodType.get(Oname).get(OMName).add(e, temp);
                        //todo: don't forget to initialization
                        /*if(!OMap.get(Oname).element2.
                                availabilityProj(temp.getAvailability().getQuorum(), environment.get(singleCall).getCurrentHost())){
                            System.out.println("Inferred availability does not mee the requirement.");
                        }*/
                    }
                    int retIndex = objectMethodType.get(singleCall.objectName.toString()).get(singleCall.methodName.toString()).size()-1;
                    //set the object method return value
                    environment.get(singleCall).getGamma().
                            put(singleCall.toString(),
                                    objectMethodType.get(singleCall.objectName.toString()).get(singleCall.methodName.toString()).get(retIndex));
                    //set the administrative  x's type in the environment
                    environment.get(singleCall).getGamma().
                            put(singleCall.administrativeX.toString(),
                                    objectMethodType.get(singleCall.objectName.toString()).get(singleCall.methodName.toString()).get(retIndex));
                    return resultB;
                }
                //we have the typed context for the method
                else {
                    //when there is no argument for the method
                    if(singleCall.args == null || singleCall.args.length == 0){
                        //when there is no argument, the type of the dummy argument is the same as current context
                        //\tau_1 = \tau_x for the availability constraints
                        //resultB &= OMap.get(singleCall.objectName.toString()).element2.
                                //availabilityProj(environment.get(singleCall).getCurrentContext().getAvailability().getQuorum(),
                                        //environment.get(singleCall).getCurrentHost());

                        //now we have changed the dummy variable to the argument named "bot" in the mAName
                        //now all the implicit constraints are forced by the bot argument
                        statistics.startACheck();
                        resultB &= OMap.get(Oname).element2.
                                availabilityProj(objectMethodType.get(Oname).get(OMName).get(0).getAvailability().getQuorum(),
                                        environment.get(singleCall).getCurrentHost());
                        statistics.endACheck();
                        statistics.addCons();
                        resultB &= environment.get(singleCall).getCurrentContext().ciaLeq(objectMethodType.get(Oname).get(OMName).get(0));
                        statistics.addCons();

                        int retIndex = objectMethodType.get(singleCall.objectName.toString()).get(singleCall.methodName.toString()).size()-1;
                        //set the object method return value
                        environment.get(singleCall).getGamma().
                                put(singleCall.toString(),
                                        objectMethodType.get(singleCall.objectName.toString()).get(singleCall.methodName.toString()).get(retIndex));
                        //set the administrative  x's type in the environment
                        environment.get(singleCall).getGamma().
                                put(singleCall.administrativeX.toString(),
                                        objectMethodType.get(singleCall.objectName.toString()).get(singleCall.methodName.toString()).get(retIndex));
                        return resultB;
                    }
                    else {
                        for(int a = 0; a < singleCall.args.length; a++){
                            Expression argE = singleCall.args[a];
                            environment.put(argE, environment.get(singleCall).clone());
                            resultB &= (Boolean) visitDispatch(argE);
                            resultB &= environment.get(argE).getGamma().get(argE.toString()).
                                    ciaJoin(environment.get(singleCall).getCurrentContext()).
                                    ciaLeq(objectMethodType.get(Oname).get(OMName).get(a));
                            statistics.addCons();
                            //need to check this later
                            statistics.startACheck();
                            resultB &= OMap.get(singleCall.objectName.toString()).element2.
                                    availabilityProj(objectMethodType.get(singleCall.objectName.toString()).get(singleCall.methodName.toString()).get(a).getAvailability().getQuorum(),
                                            environment.get(singleCall).getCurrentHost());
                            statistics.endACheck();
                            statistics.addCons();
                        }
                        //set the object method return value
                        int retIndex = objectMethodType.get(singleCall.objectName.toString()).get(singleCall.methodName.toString()).size()-1;
                        environment.get(singleCall).getGamma().
                                put(singleCall.toString(),
                                        objectMethodType.get(singleCall.objectName.toString()).get(singleCall.methodName.toString()).get(retIndex));
                        //set the administrative  x's type in the environment
                        environment.get(singleCall).getGamma().
                                put(singleCall.administrativeX.toString(),
                                        objectMethodType.get(singleCall.objectName.toString()).get(singleCall.methodName.toString()).get(retIndex));
                        return resultB;
                    }
                }
            }
        }
    }

    @Override
    public Object visit(Command command){ return command.accept(commandT); }

    //the last element in the array is the return type.
    public Boolean fieldCheck(String objName, SecureTypeChecking s){
        Boolean resultB = true;
        HashMap<String, ArrayList<CIAType>> objSig = s.objectMethodType.get(objName);
        nodeSet objHosts = s.OMap.get(objName).element1.unionQuorum();
        for(String mName: objSig.keySet()){
            ArrayList<CIAType> argTypes = objSig.get(mName);
            CIAType retVType = argTypes.get(argTypes.size()-1);
            resultB &= retVType.cLeq(objHosts);
            statistics.addCons();
            statistics.startSIntegrityCheck();
            resultB &= s.OMap.get(objName).element1.fieldIntegrity(retVType.getIntegrity().getQuorum());
            statistics.endSIntegrityCheck();
            statistics.addCons();
            statistics.startACheck();
            resultB &= s.OMap.get(objName).element1.availabilityCons(retVType.getAvailability().getQuorum());
            statistics.endACheck();
            statistics.addCons();
            for(int i = 0; i < argTypes.size() - 1; i++){
                //resultB &= s.objUmb.get(objName).ciaLeq(argTypes.get(i)) & argTypes.get(i).ciaLeq(retVType);
                resultB &= argTypes.get(i).ciaLeq(retVType);
                statistics.addCons();
                //todo: this is not needed
                //resultB &= argTypes.get(i).cLeq(objHosts);
                statistics.startCIntegrityCheck();
                resultB &= s.OMap.get(objName).element2.methodIntegrity(argTypes.get(i).getIntegrity().getQuorum());
                statistics.endCIntegrityCheck();
                statistics.addCons();
            }
        }
        //check the integrity constraints for Cintegrity(Q2)
        //todo: the umbrella is to general
        //resultB &= s.OMap.get(objName).element2.methodIntegrity(s.objUmb.get(objName).getIntegrity().getQuorum());
        //resultB &= s.OMap.get(objName).element1.fieldIntegrity(s.objUmb.get(objName).getIntegrity().getQuorum());
        //resultB &= s.OMap.get(objName).element1.availabilityCons(s.objUmb.get(objName).getAvailability().getQuorum());
        System.out.println("the field check for " + objName + " is " + resultB.toString());
        return resultB;
    }

    //mArgNames are the corresponding names for arguments in the methods
    public Boolean methodCheck(MethodDefinition m, int n, SecureTypeChecking s, ArrayList<String> mArgNames){
        Boolean resultB = true;
        //set up the input arguments to type the body of method and the object call
        for(Expression arg : m.freeVars){
            int indexFroArg = mArgNames.indexOf(arg.toString());
            s.environment.get(m.body).getGamma().put(arg.toString(),
                    s.methodType.get(m.thisMethodName.toString()).element2.get(indexFroArg));
            s.environment.get(m.objectCall).getGamma().put(arg.toString(),
                    s.methodType.get(m.thisMethodName.toString()).element2.get(indexFroArg));
        }
        //set the current hosts
        s.environment.get(m.body).setCurrentHost(s.MMap.get(m.thisMethodName.toString()).element1);
        s.environment.get(m.body).setCurrentContext(s.methodType.get(m.thisMethodName.toString()).element1.get(0));
        s.environment.get(m.objectCall).setCurrentHost(s.MMap.get(m.thisMethodName.toString()).element1);
        s.environment.get(m.objectCall).setCurrentContext(s.methodType.get(m.thisMethodName.toString()).element1.get(0));

        //set the administrative x type in the method body
        resultB &= (Boolean) s.visitDispatch(m.objectCall);
        s.environment.get(m.body).getGamma().put(m.objectCall.administrativeX.toString(),
                s.environment.get(m.objectCall).getGamma().get(m.objectCall.toString()));

        resultB &= (Boolean) s.visitDispatch(m.body);
        resultB &= s.environment.get(m.body).getGamma().get(m.body.toString()).
                ciaLeq(s.methodType.get(m.thisMethodName.toString()).element1.get(1));
        statistics.addCons();
        for(CIAType argT : s.methodType.get(m.thisMethodName.toString()).element2){
            //\tau_1 <= \tau_x
            //resultB &= argT.ciaLeq(s.methodType.get(m.thisMethodName.toString()).element1.get(0));
            //\tau_1 <= \tau_2
            resultB &= argT.ciaLeq(s.methodType.get(m.thisMethodName.toString()).element1.get(1));
            statistics.addCons();
            statistics.startCIntegrityCheck();
            resultB &= s.MMap.get(m.thisMethodName.toString()).element2.
                    methodIntegrity(argT.getIntegrity().getQuorum());
            statistics.endCIntegrityCheck();
            statistics.addCons();
        }
        //double check whether the return value \tau_2 has to be able to hosted on the method hosts
//        resultB &= s.methodType.get(m.thisMethodName.toString()).element1.get(0).
//                ciaJoin(s.methodType.get(m.thisMethodName.toString()).element1.get(1)).
//                cLeq(s.environment.get(m.body).getCurrentHost());
        resultB &= s.methodType.get(m.thisMethodName.toString()).element1.get(0).
                cLeq(s.environment.get(m.body).getCurrentHost());
        statistics.addCons();
        System.out.println("The method " + m.thisMethodName.toString() + " has been checked " + resultB);
        return resultB;
    }

/*    //We does not supply all the intermediate method signature, we need to infer some of the signature
    public Boolean classTypeCheck(ArrayList<MethodDefinition> methods,
                                  ArrayList<Pair<nodeSet, quorumDef>> methodsSig,
                                  HashMap<String, HashMap<String, ArrayList<CIAType>>> objSigs,
                                  HashMap<String, Pair<quorumDef, quorumDef>> objHosts, HashMap<String, CIAType> predefinedVar,
                                  HashMap<String, CIAType> predefinedUmb){
        Boolean r = true;
        SecureTypeChecking b = new SecureTypeChecking();
        //set the M(H, Q) and O(Q1, Q2) and object signature
        for(int i = 0; i < methods.size(); i++){
            b.MMap.put(methods.get(i).thisMethodName.toString(), methodsSig.get(i));
        }
        b.OMap = objHosts;
        b.objectMethodType = objSigs;
        b.objUmb = predefinedUmb;

        //first do field check then we do method check
        for(String oname : objSigs.keySet()){
            r &= fieldCheck(oname, b);
        }
        for(int i = methods.size() - 1; i >= 0; i--){
            b.environment.get(methods.get(i).body).setGamma(predefinedVar);
            r &= methodCheck(methods.get(i), i, b);
        }
        return r;
    }*/

    //we only need to do type checking, all the methods signatures are supplied.
    public Boolean classTypeCheck(ArrayList<MethodDefinition> methods,
                                  ArrayList<Pair<nodeSet, quorumDef>> methodsSig,
                                  ArrayList<Pair<ArrayList<CIAType>, ArrayList<CIAType>>> methodTypes,
                                  ArrayList<ArrayList<String>> methodArgNames,
                                  HashMap<String, HashMap<String, ArrayList<CIAType>>> objSigs,
                                  HashMap<String, Pair<quorumDef, quorumDef>> objHosts,
                                  HashMap<String, CIAType> predefinedVar,
                                  HashMap<String, CIAType> predefinedUmb,
                                  CIAType botT){
        Boolean r = true;
        SecureTypeChecking b = new SecureTypeChecking();
        b.statistics.startCheck();
        //set the M(H, Q) and O(Q1, Q2) and object signature
        for(int i = 0; i < methods.size(); i++){
            b.MMap.put(methods.get(i).thisMethodName.toString(), methodsSig.get(i));
            b.methodType.put(methods.get(i).thisMethodName.toString(), methodTypes.get(i));
        }
        b.OMap = objHosts;
        b.objectMethodType = objSigs;
        b.objUmb = predefinedUmb;
        b.botType = botT;

        //first do field check then we do method check
        for(String oname : objSigs.keySet()){
            r &= fieldCheck(oname, b);
        }
        HashMap<String, CIAType> preGamma = new HashMap<>();
        for(int i = methods.size() - 1; i >= 0; i--){
            if(i == methods.size() - 1){
                b.environment.put(methods.get(i).body, new envForTypeCheck());
                b.environment.get(methods.get(i).body).setGamma(predefinedVar);
                b.environment.put(methods.get(i).objectCall, new envForTypeCheck());
                b.environment.get(methods.get(i).objectCall).setGamma(predefinedVar);
            }
            else {
                b.environment.put(methods.get(i).body, new envForTypeCheck());
                b.environment.get(methods.get(i).body).setGamma(preGamma);
                b.environment.put(methods.get(i).objectCall, new envForTypeCheck());
                b.environment.get(methods.get(i).objectCall).setGamma(preGamma);
            }
            r &= methodCheck(methods.get(i), i, b, methodArgNames.get(i));
            preGamma = b.environment.get(methods.get(i).body).getGamma();
        }
        b.statistics.endCheck();
        b.statistics.printStatistics();
        return r;
    }
}

