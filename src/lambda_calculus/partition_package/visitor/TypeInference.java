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

public class TypeInference implements PartitionVisitor{
    //the number of different trust domains
    int n;
    //the principal list
    ArrayList<Integer> principals;
    //final result type
    ArrayList<Boolean> resultC;
    ArrayList<ArrayList<Integer>> resultI;
    ArrayList<ArrayList<Integer>> resultA;
    //the initial context \tau_0
    ArrayList<Boolean> startC;
    ArrayList<ArrayList<Integer>> startI;
    ArrayList<ArrayList<Integer>> startA;

    //user defined variables, currently the user's requirements are on the confidentiality
    HashMap<String, ArrayList<Boolean>> predefinedV; //pre defined variables's confidentiality
    HashMap<String, HashMap<String, ArrayList<Boolean>>> predefinedOM; //pre defined object method variables' confidentiality

    //the host information for res
    ArrayList<Integer> resH;

    //variables in the current context needed to be refreshed at every node.
    HashMap<Node, envForTypeInfer> environment;

    //The constraint string
    //StringBuilder s;

    //The hashmap from the node to the basic variable names
    HashMap<Node, ArrayList<String>> statementC;
    HashMap<Node, ArrayList<String>> statementI;
    HashMap<Node, ArrayList<String>> statementA;

    ArrayList<Boolean> botC;
    ArrayList<ArrayList<Integer>> botI;
    ArrayList<ArrayList<Integer>> botA;

    //hashmap for methods and objects information
    HashMap<String, MethodInfo> mInfo;
    HashMap<String, ObjectInfo> oInfo;

    public TypeInference(){
        n = 0;
        principals = new ArrayList<>();
        resultC = new ArrayList<>();
        resultI = new ArrayList<>();
        resultA = new ArrayList<>();
        startC = new ArrayList<>();
        startI = new ArrayList<>();
        startA = new ArrayList<>();
        predefinedV = new HashMap<>();
        predefinedOM = new HashMap<>();
        resH = new ArrayList<>();
        environment = new HashMap<>();
        //s = new StringBuilder();
        statementC = new HashMap<>();
        statementI = new HashMap<>();
        statementA = new HashMap<>();
        botC = new ArrayList<>();
        botI = new ArrayList<>();
        botA = new ArrayList<>();
        mInfo = new HashMap<>();
        oInfo = new HashMap<>();
    }

    public TypeInference(int num, ArrayList<Integer> p){}

    public void updateStatementAll(Node n, ArrayList<String> c, ArrayList<String> i, ArrayList<String> a){
        if(statementC.get(n) == null || statementC.get(n).isEmpty()){
            statementC.put(n, new ArrayList<>());
            statementC.get(n).addAll(c);
        }
        else {
            statementC.get(n).addAll(c);
        }
        if(statementI.get(n) == null || statementI.get(n).isEmpty()){
            statementI.put(n, new ArrayList<>());
            statementI.get(n).addAll(i);
        }
        else {
            statementI.get(n).addAll(i);
        }
        if(statementA.get(n) == null || statementA.get(n).isEmpty()){
            statementA.put(n, new ArrayList<>());
            statementA.get(n).addAll(a);
        }
        else {
            statementA.get(n).addAll(a);
        }
        return;
    }

    public void updateStatementAvai(Node n, ArrayList<String> a){
        if(statementA.get(n) == null || statementA.get(n).isEmpty()){
            statementA.put(n, new ArrayList<>());
            statementA.get(n).addAll(a);
        }
        else {
            statementA.get(n).addAll(a);
        }
        return;
    }

    public Object visitDispatch(Expression expression) {
        return expression.accept(expressionT);
    }
    public ExpressionT expressionT =  new ExpressionT();
    public class ExpressionT implements ExpressionVisitor<Object> {
        public class GIdB implements GIdVisitor<Object>{
            @Override
            public Object visit(Id id){
                ArrayList<String> bc = new ArrayList<String>();
                bc.add("botC");
                ArrayList<String> bi = new ArrayList<String>();
                bi.add("botI");
                ArrayList<String> ba = new ArrayList<String>();
                ba.add("botA");
                updateStatementAll(id, bc, bi, ba);
                StringBuilder result = new StringBuilder();
                return result;}
        }
        GIdB gIdB = new GIdB();
        @Override
        public Object visit(GId gId){ return gId.accept(gIdB); }

        public class LiteralB implements LiteralVisitor<Object>{
            @Override
            public Object visit(IntLiteral intLiteral){
                ArrayList<String> bc = new ArrayList<String>();
                bc.add("botC");
                ArrayList<String> bi = new ArrayList<String>();
                bi.add("botI");
                ArrayList<String> ba = new ArrayList<String>();
                ba.add("botA");
                updateStatementAll(intLiteral, bc, bi, ba);
                StringBuilder result = new StringBuilder();
                return result;
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

                StringBuilder result = ((StringBuilder) visitDispatch(plus.operand1)).
                        append(((StringBuilder) visitDispatch(plus.operand2)).toString());
                updateStatementAll(plus, statementC.get(plus.operand1), statementI.get(plus.operand1), statementA.get(plus.operand1));
                updateStatementAll(plus, statementC.get(plus.operand2), statementI.get(plus.operand2), statementA.get(plus.operand2));
                return result;
            }
        }
        BinaryOpB binaryOpB = new BinaryOpB();
        @Override
        public Object visit(BinaryOp binaryOp){ return binaryOp.accept(binaryOpB); }

        @Override
        public Object visit(Var var){
            //if the variable is predefined by user, use the type of the pre defined variable
            if(predefinedV.containsKey(var.toString() + "C")){
                ArrayList<String> bc = new ArrayList<String>();
                bc.add(var.toString() + "C");
                ArrayList<String> bi = new ArrayList<String>();
                bi.add("botI");
                ArrayList<String> ba = new ArrayList<String>();
                ba.add("botA");
                updateStatementAll(var, bc, bi, ba);
            }
            //if the variable is not predefined, then the default type is bottom type
            else {
                ArrayList<String> bc = new ArrayList<String>();
                bc.add("botC");
                ArrayList<String> bi = new ArrayList<String>();
                bi.add("botI");
                ArrayList<String> ba = new ArrayList<String>();
                ba.add("botA");
                updateStatementAll(var, bc, bi, ba);
            }
            StringBuilder result = new StringBuilder();
            return result;
        }

        @Override
        public Object visit(Conditional conditional){
            //the first step is to set the environment for the dispatched commands
            environment.put(conditional.condition, environment.get(conditional).clone());
            StringBuilder result = (StringBuilder) visitDispatch(conditional.condition);

            //the context needs to include condition's type
            environment.put(conditional.ifExp, environment.get(conditional).clone());
            environment.get(conditional.ifExp).currentContextC.addAll(statementC.get(conditional.condition));
            environment.get(conditional.ifExp).currentContextI.addAll(statementI.get(conditional.condition));
            environment.get(conditional.ifExp).currentContextA.addAll(statementA.get(conditional.condition));

            environment.put(conditional.elseExp, environment.get(conditional).clone());
            environment.get(conditional.elseExp).currentContextC.addAll(statementC.get(conditional.condition));
            environment.get(conditional.elseExp).currentContextI.addAll(statementI.get(conditional.condition));
            environment.get(conditional.elseExp).currentContextA.addAll(statementA.get(conditional.condition));

            //add the constraints of two branches
            result.append((StringBuilder)visitDispatch(conditional.ifExp)).toString();
            result.append((StringBuilder)visitDispatch(conditional.elseExp)).toString();

            //set if statement's type
            updateStatementAll(conditional, statementC.get(conditional.condition), statementI.get(conditional.condition), statementA.get(conditional.condition));
            updateStatementAll(conditional, statementC.get(conditional.ifExp), statementI.get(conditional.ifExp), statementA.get(conditional.ifExp));
            updateStatementAll(conditional, statementC.get(conditional.elseExp), statementI.get(conditional.elseExp), statementA.get(conditional.elseExp));

            return result;
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
            StringBuilder result = (StringBuilder) visitDispatch(expSt.expression);

            //set expression statement type
            updateStatementAll(expSt, statementC.get(expSt.expression), statementI.get(expSt.expression), statementA.get(expSt.expression));

            return result;
        }

        @Override
        public Object visit(If iF){
            //the first step is to set the environment for the dispatched commands
            environment.put(iF.condition, environment.get(iF).clone());
            StringBuilder result = (StringBuilder) visitDispatch(iF.condition);

            //the context needs to include condition's type
            environment.put(iF.command1, environment.get(iF).clone());
            environment.get(iF.command1).currentContextC.addAll(statementC.get(iF.condition));
            environment.get(iF.command1).currentContextI.addAll(statementI.get(iF.condition));
            environment.get(iF.command1).currentContextA.addAll(statementA.get(iF.condition));

            environment.put(iF.command2, environment.get(iF).clone());
            environment.get(iF.command2).currentContextC.addAll(statementC.get(iF.condition));
            environment.get(iF.command2).currentContextI.addAll(statementI.get(iF.condition));
            environment.get(iF.command2).currentContextA.addAll(statementA.get(iF.condition));

            //add the constraints of two branches
            result.append((StringBuilder)visitDispatch(iF.command1)).toString();
            result.append((StringBuilder)visitDispatch(iF.command2)).toString();

            //set if statement's type
            updateStatementAll(iF, statementC.get(iF.condition), statementI.get(iF.condition), statementA.get(iF.condition));
            updateStatementAll(iF, statementC.get(iF.command1), statementI.get(iF.command1), statementA.get(iF.command1));
            updateStatementAll(iF, statementC.get(iF.command2), statementI.get(iF.command2), statementA.get(iF.command2));

            return result;
        }

        @Override
        public Object visit(Sequence sequence){
            //we first type the expression e1, then bind the type \tau_1 with the administrative x
            environment.put(sequence.command1, environment.get(sequence).clone());
            StringBuilder result = (StringBuilder) visitDispatch(sequence.command1);
            environment.put(sequence.command2, environment.get(sequence).clone());

            //todo: may need to generalize the to the command instead of only the singlecall
            if(sequence.command1 instanceof SingleCall){
                statementC.put(((SingleCall) sequence.command1).administrativeX, statementC.get(sequence.command1));
                statementI.put(((SingleCall) sequence.command1).administrativeX, statementI.get(sequence.command1));
                statementA.put(((SingleCall) sequence.command1).administrativeX, statementA.get(sequence.command1));
            }

            environment.get(sequence.command2).currentContextA.addAll(statementA.get(sequence.command1));
            result.append(((StringBuilder) visitDispatch(sequence.command2)).toString());

            //set the sequence statement type
            updateStatementAll(sequence, statementC.get(sequence.command1), statementI.get(sequence.command1), statementA.get(sequence.command1));
            updateStatementAll(sequence, statementC.get(sequence.command2), statementI.get(sequence.command2), statementA.get(sequence.command2));
            return result;
        }

        @Override
        //method and object calls are all in the single call node
        public Object visit(SingleCall singleCall){
            if(environment.get(singleCall) == null){
                environment.put(singleCall, new envForTypeInfer());
            }

            //when this is a method call (ThisCallT)
            StringBuilder result = new StringBuilder();
            if(singleCall.objectName.toString() =="this"){
                //\tau_x <= \tau_x'
                for(String conxtxC: environment.get(singleCall).currentContextC){
                    result.append("s.add(cLe(" + conxtxC + ", " + mInfo.get(singleCall.methodName.toString()).mcontextC + "))\n");
                }
                for(String conxtI: environment.get(singleCall).currentContextI){
                    result.append("s.add(bLe(" + mInfo.get(singleCall.methodName.toString()).mcontextI + ", " + conxtI + "))\n");
                }
                for(String conxtA: environment.get(singleCall).currentContextA){
                    result.append("s.add(bLe(" + mInfo.get(singleCall.methodName.toString()).mcontextA + ", " + conxtA + "))\n");
                }

                //when there is no argument for the method
                //we need to add dummy argument for the implicit constraints
                //now we have changed the dummy variable to the argument named "bot" in the mAName
                //now all the implicit constraints are forced by the bot argument
                //for example, \tau_1 can have the same type as \tau_x' and the availability constraint is on the A(\tau_x')
                if(singleCall.args == null || singleCall.args.length == 0){
                    //a1 <= AvailabilityP(a1, Q|H)
                    result.append("s.add(availabilityP(" + mInfo.get(singleCall.methodName.toString()).arguA.get(0) +
                            ", " + mInfo.get(singleCall.methodName.toString()).qc + ", " + environment.get(singleCall).currentHosts + "))\n");

                    ////\tau_x <= \tau_1
                    for(String conxtxC: environment.get(singleCall).currentContextC){
                        result.append("s.add(cLe(" + conxtxC + ", " + mInfo.get(singleCall.methodName.toString()).arguC.get(0) + "))\n");
                    }
                    for(String conxtI: environment.get(singleCall).currentContextI){
                        result.append("s.add(bLe(" + mInfo.get(singleCall.methodName.toString()).arguI.get(0) + ", " + conxtI + "))\n");
                    }
                    for(String conxtA: environment.get(singleCall).currentContextA){
                        result.append("s.add(bLe(" + mInfo.get(singleCall.methodName.toString()).arguA.get(0) + ", " + conxtA + "))\n");
                    }

                    //set the return value for the method
                    ArrayList<String> retC = new ArrayList<>();
                    retC.add("resultC");
                    ArrayList<String> retI = new ArrayList<>();
                    retI.add("resultI");
                    ArrayList<String> retA = new ArrayList<>();
                    retA.add("resultA");
                    statementC.put(singleCall, retC);
                    statementI.put(singleCall, retI);
                    statementA.put(singleCall, retA);

                    return result;
                }
                else {
                    for(int a = 0; a < singleCall.args.length; a++){
                        Expression argE = singleCall.args[a];
                        environment.put(argE, environment.get(singleCall).clone());
                        result.append(((StringBuilder) visitDispatch(argE)).toString());

                        //\tau <= \tau_1
                        for(String cVariables: statementC.get(argE)){
                            result.append("s.add(cLe(" + cVariables + ", " + mInfo.get(singleCall.methodName.toString()).arguC.get(a) + "))\n ");
                        }
                        for(String iVariables: statementI.get(argE)){
                            result.append("s.add(bLe(" + mInfo.get(singleCall.methodName.toString()).arguI.get(a)  + ", " + iVariables + "))\n ");
                        }
                        for(String aVariables: statementA.get(argE)){
                            result.append("s.add(bLe(" + mInfo.get(singleCall.methodName.toString()).arguA.get(a)  + ", " + aVariables + "))\n ");
                        }

                        //\tau_x <= \tau_1
                        for(String conxtxC: environment.get(singleCall).currentContextC){
                            result.append("s.add(cLe(" + conxtxC + ", " + mInfo.get(singleCall.methodName.toString()).arguC.get(a) + "))\n");
                        }
                        for(String conxtI: environment.get(singleCall).currentContextI){
                            result.append("s.add(bLe(" + mInfo.get(singleCall.methodName.toString()).arguI.get(a) + ", " + conxtI + "))\n");
                        }
                        for(String conxtA: environment.get(singleCall).currentContextA){
                            result.append("s.add(bLe(" + mInfo.get(singleCall.methodName.toString()).arguA.get(a) + ", " + conxtA + "))\n");
                        }

                        //a1 <= Availability(Q|H)
                        result.append("s.add(availabilityP(" + mInfo.get(singleCall.methodName.toString()).arguA.get(a) +
                                ", " + mInfo.get(singleCall.methodName.toString()).qc + ", " + environment.get(singleCall).currentHosts + "))\n");

                    }

                    //set the return value for the method
                    ArrayList<String> retC = new ArrayList<>();
                    retC.add("resultC");
                    ArrayList<String> retI = new ArrayList<>();
                    retI.add("resultI");
                    ArrayList<String> retA = new ArrayList<>();
                    retA.add("resultA");
                    statementC.put(singleCall, retC);
                    statementI.put(singleCall, retI);
                    statementA.put(singleCall, retA);

                    return result;
                }
            }
            //this is an object call
            else {
                String Oname = singleCall.objectName.toString();
                String OMName = singleCall.methodName.toString();

                //c2 <= H
                result.append("s.add(cLeH(" + oInfo.get(Oname).omArgusC.get(OMName).element2 + ", " + environment.get(singleCall).currentHosts + "))\n");

                //we have the typed context for the method
                //when there is no argument for the method
                if(singleCall.args == null || singleCall.args.length == 0){
                    //when there is no argument, the type of the dummy argument is the same as current context
                    //\tau_1 = \tau_x for the availability constraints
                    //now we have changed the dummy variable to the argument named "bot" in the mAName
                    //now all the implicit constraints are forced by the bot argument

                    //a1 <= AvailabilityP(a1, Q|H)
                    result.append("s.add(availabilityP(" + oInfo.get(Oname).omArgusA.get(OMName).element1.get(0) +
                            ", " + oInfo.get(Oname).Qc + ", " + environment.get(singleCall).currentHosts + "))\n");

                    ////\tau_x <= \tau_1
                    for(String conxtxC: environment.get(singleCall).currentContextC){
                        result.append("s.add(cLe(" + conxtxC + ", " + oInfo.get(Oname).omArgusC.get(OMName).element1.get(0) + "))\n");
                    }
                    for(String conxtI: environment.get(singleCall).currentContextI){
                        result.append("s.add(bLe(" + oInfo.get(Oname).omArgusI.get(OMName).element1.get(0) + ", " + conxtI + "))\n");
                    }
                    for(String conxtA: environment.get(singleCall).currentContextA){
                        result.append("s.add(bLe(" + oInfo.get(Oname).omArgusA.get(OMName).element1.get(0) + ", " + conxtA + "))\n");
                    }

                    //set the return value for the method
                    ArrayList<String> oretC = new ArrayList<>();
                    oretC.add(oInfo.get(Oname).omArgusC.get(OMName).element2);
                    ArrayList<String> oretI = new ArrayList<>();
                    oretI.add(oInfo.get(Oname).omArgusI.get(OMName).element2);
                    ArrayList<String> oretA = new ArrayList<>();
                    oretA.add(oInfo.get(Oname).omArgusA.get(OMName).element2);
                    statementC.put(singleCall, oretC);
                    statementI.put(singleCall, oretI);
                    statementA.put(singleCall, oretA);

                    return result;
                }
                else {
                    for(int a = 0; a < singleCall.args.length; a++){
                        Expression argE = singleCall.args[a];
                        environment.put(argE, environment.get(singleCall).clone());
                        result.append(((StringBuilder) visitDispatch(argE)).toString());

                        //\tau <= \tau_1
                        for(String cVariables: statementC.get(argE)){
                            result.append("s.add(cLe(" + cVariables + ", " + oInfo.get(Oname).omArgusC.get(OMName).element1.get(a) + "))\n ");
                        }
                        for(String iVariables: statementI.get(argE)){
                            result.append("s.add(bLe(" + oInfo.get(Oname).omArgusI.get(OMName).element1.get(a) + ", " + iVariables + "))\n ");
                        }
                        for(String aVariables: statementA.get(argE)){
                            result.append("s.add(bLe(" + oInfo.get(Oname).omArgusA.get(OMName).element1.get(a) + ", " + aVariables + "))\n ");
                        }

                        //\tau_x <= \tau_1
                        for(String conxtxC: environment.get(singleCall).currentContextC){
                            result.append("s.add(cLe(" + conxtxC + ", " + oInfo.get(Oname).omArgusC.get(OMName).element1.get(a) + "))\n");
                        }
                        for(String conxtI: environment.get(singleCall).currentContextI){
                            result.append("s.add(bLe(" + oInfo.get(Oname).omArgusI.get(OMName).element1.get(a) + ", " + conxtI + "))\n");
                        }
                        for(String conxtA: environment.get(singleCall).currentContextA){
                            result.append("s.add(bLe(" + oInfo.get(Oname).omArgusA.get(OMName).element1.get(a) + ", " + conxtA + "))\n");
                        }

                        //a1 <= Availability(Q|H)
                        result.append("s.add(availabilityP(" + oInfo.get(Oname).omArgusA.get(OMName).element1.get(a) +
                                ", " + oInfo.get(Oname).Qc + ", " + environment.get(singleCall).currentHosts + "))\n");

                    }

                    //set the return value for the method
                    ArrayList<String> oretC = new ArrayList<>();
                    oretC.add(oInfo.get(Oname).omArgusC.get(OMName).element2);
                    ArrayList<String> oretI = new ArrayList<>();
                    oretI.add(oInfo.get(Oname).omArgusI.get(OMName).element2);
                    ArrayList<String> oretA = new ArrayList<>();
                    oretA.add(oInfo.get(Oname).omArgusA.get(OMName).element2);
                    statementC.put(singleCall, oretC);
                    statementI.put(singleCall, oretI);
                    statementA.put(singleCall, oretA);

                    return result;
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
            resultB &= s.OMap.get(objName).element1.fieldIntegrity(retVType.getIntegrity().getQuorum());
            resultB &= s.OMap.get(objName).element1.availabilityCons(retVType.getAvailability().getQuorum());
            for(int i = 0; i < argTypes.size() - 1; i++){
                //resultB &= s.objUmb.get(objName).ciaLeq(argTypes.get(i)) & argTypes.get(i).ciaLeq(retVType);
                resultB &= argTypes.get(i).ciaLeq(retVType);
                //todo: this is not needed
                //resultB &= argTypes.get(i).cLeq(objHosts);
                resultB &= s.OMap.get(objName).element2.methodIntegrity(argTypes.get(i).getIntegrity().getQuorum());
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
        if(n != 0){
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
        }

        for(CIAType argT : s.methodType.get(m.thisMethodName.toString()).element2){
            //\tau_1 <= \tau_x
            //resultB &= argT.ciaLeq(s.methodType.get(m.thisMethodName.toString()).element1.get(0));
            //\tau_1 <= \tau_2
            resultB &= argT.ciaLeq(s.methodType.get(m.thisMethodName.toString()).element1.get(1));
            resultB &= s.MMap.get(m.thisMethodName.toString()).element2.
                    methodIntegrity(argT.getIntegrity().getQuorum());
        }
        //double check whether the return value \tau_2 has to be able to hosted on the method hosts
//        resultB &= s.methodType.get(m.thisMethodName.toString()).element1.get(0).
//                ciaJoin(s.methodType.get(m.thisMethodName.toString()).element1.get(1)).
//                cLeq(s.environment.get(m.body).getCurrentHost());
        resultB &= s.methodType.get(m.thisMethodName.toString()).element1.get(0).
                cLeq(s.environment.get(m.body).getCurrentHost());
        System.out.println("The method " + m.thisMethodName.toString() + " has been checked " + resultB);
        return resultB;
    }

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
