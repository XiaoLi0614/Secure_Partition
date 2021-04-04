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

    ArrayList<Boolean> botC;
    ArrayList<ArrayList<Integer>> botI;
    ArrayList<ArrayList<Integer>> botA;

    //the information below need to be initialized in classInference method

    //hashmap for methods and objects information
    HashMap<String, MethodInfo> mInfo;
    HashMap<String, ObjectInfo> oInfo;

    //The hashmap from the node to the basic variable names
    HashMap<String, ArrayList<String>> statementC;
    HashMap<String, ArrayList<String>> statementI;
    HashMap<String, ArrayList<String>> statementA;

    //variables in the current context needed to be refreshed at every node.
    HashMap<Node, envForTypeInfer> environment;

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
        statementC = new HashMap<>();
        statementI = new HashMap<>();
        statementA = new HashMap<>();
        botC = new ArrayList<>();
        botI = new ArrayList<>();
        botA = new ArrayList<>();
        mInfo = new HashMap<>();
        oInfo = new HashMap<>();
    }

    public TypeInference(int num, ArrayList<Integer> p,
                         ArrayList<Boolean> rc, ArrayList<ArrayList<Integer>> ri, ArrayList<ArrayList<Integer>> ra,
                         ArrayList<Boolean> sc, ArrayList<ArrayList<Integer>> si, ArrayList<ArrayList<Integer>> sa,
                         ArrayList<Boolean> bc, ArrayList<ArrayList<Integer>> bi, ArrayList<ArrayList<Integer>> ba,
                         ArrayList<Integer> rH, HashMap<String, ArrayList<Boolean>> pV, HashMap<String, HashMap<String, ArrayList<Boolean>>> pOM)
    {
        n = num;
        principals = p;
        resultC = rc;
        resultI = ri;
        resultA = ra;
        startC = sc;
        startI = si;
        startA = sa;
        botC = bc;
        botI = bi;
        botA = ba;
        resH = rH;
        predefinedV = pV;
        predefinedOM = pOM;

        mInfo = new HashMap<>();
        oInfo = new HashMap<>();
        statementC = new HashMap<>();
        statementI = new HashMap<>();
        statementA = new HashMap<>();
        environment = new HashMap<>();
    }

    public void updateStatementAll(Node n, ArrayList<String> c, ArrayList<String> i, ArrayList<String> a){
        if(statementC.get(n.toString()) == null || statementC.get(n.toString()).isEmpty()){
            statementC.put(n.toString(), new ArrayList<>());
            statementC.get(n.toString()).addAll(c);
        }
        else {
            statementC.get(n.toString()).addAll(c);
        }
        if(statementI.get(n.toString()) == null || statementI.get(n.toString()).isEmpty()){
            statementI.put(n.toString(), new ArrayList<>());
            statementI.get(n.toString()).addAll(i);
        }
        else {
            statementI.get(n.toString()).addAll(i);
        }
        if(statementA.get(n.toString()) == null || statementA.get(n.toString()).isEmpty()){
            statementA.put(n.toString(), new ArrayList<>());
            statementA.get(n.toString()).addAll(a);
        }
        else {
            statementA.get(n.toString()).addAll(a);
        }
        return;
    }

    public void updateStatementAvai(Node n, ArrayList<String> a){
        if(statementA.get(n.toString()) == null || statementA.get(n.toString()).isEmpty()){
            statementA.put(n.toString(), new ArrayList<>());
            statementA.get(n.toString()).addAll(a);
        }
        else {
            statementA.get(n.toString()).addAll(a);
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
                updateStatementAll(plus, statementC.get(plus.operand1.toString()), statementI.get(plus.operand1.toString()), statementA.get(plus.operand1.toString()));
                updateStatementAll(plus, statementC.get(plus.operand2.toString()), statementI.get(plus.operand2.toString()), statementA.get(plus.operand2.toString()));
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
            environment.get(conditional.ifExp).currentContextC.addAll(statementC.get(conditional.condition.toString()));
            environment.get(conditional.ifExp).currentContextI.addAll(statementI.get(conditional.condition.toString()));
            environment.get(conditional.ifExp).currentContextA.addAll(statementA.get(conditional.condition.toString()));

            environment.put(conditional.elseExp, environment.get(conditional).clone());
            environment.get(conditional.elseExp).currentContextC.addAll(statementC.get(conditional.condition.toString()));
            environment.get(conditional.elseExp).currentContextI.addAll(statementI.get(conditional.condition.toString()));
            environment.get(conditional.elseExp).currentContextA.addAll(statementA.get(conditional.condition.toString()));

            //add the constraints of two branches
            result.append((StringBuilder)visitDispatch(conditional.ifExp)).toString();
            result.append((StringBuilder)visitDispatch(conditional.elseExp)).toString();

            //set if statement's type
            updateStatementAll(conditional, statementC.get(conditional.condition.toString()), statementI.get(conditional.condition.toString()), statementA.get(conditional.condition.toString()));
            updateStatementAll(conditional, statementC.get(conditional.ifExp.toString()), statementI.get(conditional.ifExp.toString()), statementA.get(conditional.ifExp.toString()));
            updateStatementAll(conditional, statementC.get(conditional.elseExp.toString()), statementI.get(conditional.elseExp.toString()), statementA.get(conditional.elseExp.toString()));

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
            updateStatementAll(expSt, statementC.get(expSt.expression.toString()), statementI.get(expSt.expression.toString()), statementA.get(expSt.expression.toString()));

            return result;
        }

        @Override
        public Object visit(If iF){
            //the first step is to set the environment for the dispatched commands
            environment.put(iF.condition, environment.get(iF).clone());
            StringBuilder result = (StringBuilder) visitDispatch(iF.condition);

            //the context needs to include condition's type
            environment.put(iF.command1, environment.get(iF).clone());
            environment.get(iF.command1).currentContextC.addAll(statementC.get(iF.condition.toString()));
            environment.get(iF.command1).currentContextI.addAll(statementI.get(iF.condition.toString()));
            environment.get(iF.command1).currentContextA.addAll(statementA.get(iF.condition.toString()));

            environment.put(iF.command2, environment.get(iF).clone());
            environment.get(iF.command2).currentContextC.addAll(statementC.get(iF.condition.toString()));
            environment.get(iF.command2).currentContextI.addAll(statementI.get(iF.condition.toString()));
            environment.get(iF.command2).currentContextA.addAll(statementA.get(iF.condition.toString()));

            //add the constraints of two branches
            result.append((StringBuilder)visitDispatch(iF.command1)).toString();
            result.append((StringBuilder)visitDispatch(iF.command2)).toString();

            //set if statement's type
            updateStatementAll(iF, statementC.get(iF.condition.toString()), statementI.get(iF.condition.toString()), statementA.get(iF.condition.toString()));
            updateStatementAll(iF, statementC.get(iF.command1.toString()), statementI.get(iF.command1.toString()), statementA.get(iF.command1.toString()));
            updateStatementAll(iF, statementC.get(iF.command2.toString()), statementI.get(iF.command2.toString()), statementA.get(iF.command2.toString()));

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
                statementC.put(((SingleCall) sequence.command1).administrativeX.toString(), statementC.get(sequence.command1.toString()));
                statementI.put(((SingleCall) sequence.command1).administrativeX.toString(), statementI.get(sequence.command1.toString()));
                statementA.put(((SingleCall) sequence.command1).administrativeX.toString(), statementA.get(sequence.command1.toString()));
            }

            environment.get(sequence.command2).currentContextA.addAll(statementA.get(sequence.command1.toString()));
            result.append(((StringBuilder) visitDispatch(sequence.command2)).toString());

            //set the sequence statement type
            updateStatementAll(sequence, statementC.get(sequence.command1.toString()), statementI.get(sequence.command1.toString()), statementA.get(sequence.command1.toString()));
            updateStatementAll(sequence, statementC.get(sequence.command2.toString()), statementI.get(sequence.command2.toString()), statementA.get(sequence.command2.toString()));
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
                    statementC.put(singleCall.toString(), retC);
                    statementI.put(singleCall.toString(), retI);
                    statementA.put(singleCall.toString(), retA);

                    return result;
                }
                else {
                    for(int a = 0; a < singleCall.args.length; a++){
                        Expression argE = singleCall.args[a];
                        environment.put(argE, environment.get(singleCall).clone());
                        result.append(((StringBuilder) visitDispatch(argE)).toString());

                        //\tau <= \tau_1
                        for(String cVariables: statementC.get(argE.toString())){
                            result.append("s.add(cLe(" + cVariables + ", " + mInfo.get(singleCall.methodName.toString()).arguC.get(a) + "))\n");
                        }
                        for(String iVariables: statementI.get(argE.toString())){
                            result.append("s.add(bLe(" + mInfo.get(singleCall.methodName.toString()).arguI.get(a)  + ", " + iVariables + "))\n");
                        }
                        for(String aVariables: statementA.get(argE.toString())){
                            result.append("s.add(bLe(" + mInfo.get(singleCall.methodName.toString()).arguA.get(a)  + ", " + aVariables + "))\n");
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
                    statementC.put(singleCall.toString(), retC);
                    statementI.put(singleCall.toString(), retI);
                    statementA.put(singleCall.toString(), retA);

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
                    statementC.put(singleCall.toString(), oretC);
                    statementI.put(singleCall.toString(), oretI);
                    statementA.put(singleCall.toString(), oretA);

                    return result;
                }
                else {
                    for(int a = 0; a < singleCall.args.length; a++){
                        Expression argE = singleCall.args[a];
                        environment.put(argE, environment.get(singleCall).clone());
                        result.append(((StringBuilder) visitDispatch(argE)).toString());

                        //\tau <= \tau_1
                        for(String cVariables: statementC.get(argE.toString())){
                            result.append("s.add(cLe(" + cVariables + ", " + oInfo.get(Oname).omArgusC.get(OMName).element1.get(a) + "))\n");
                        }
                        for(String iVariables: statementI.get(argE.toString())){
                            result.append("s.add(bLe(" + oInfo.get(Oname).omArgusI.get(OMName).element1.get(a) + ", " + iVariables + "))\n");
                        }
                        for(String aVariables: statementA.get(argE.toString())){
                            result.append("s.add(bLe(" + oInfo.get(Oname).omArgusA.get(OMName).element1.get(a) + ", " + aVariables + "))\n");
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
                    statementC.put(singleCall.toString(), oretC);
                    statementI.put(singleCall.toString(), oretI);
                    statementA.put(singleCall.toString(), oretA);

                    return result;
                }
            }
        }
    }

    @Override
    public Object visit(Command command){ return command.accept(commandT); }

    public StringBuilder fieldCheck(String objName, TypeInference infer){
        StringBuilder result = new StringBuilder();
        ObjectInfo objSig = infer.oInfo.get(objName);
        String objHosts = infer.oInfo.get(objName).Qs;

        for(String mName: objSig.omArgusC.keySet()){
            //todo: declassification and umbrella check

            //c_m' <= union q
            result.append("s.add(confQ(" + objSig.omArgusC.get(mName).element2 + ", " + objHosts + "))\n");

            //i_m' <= Sintegrity(Qs)
            result.append("s.add(sIntegrity(" + objSig.omArgusI.get(mName).element2 + ", " + objHosts + "))\n");

            //a_m' <= Availability(Qs)
            result.append("s.add(availabilityC(" + objSig.omArgusA.get(mName).element2 + ", " + objHosts + "))\n");

            for(int i = 0; i < objSig.omArgusI.get(mName).element1.size() - 1; i++){
                //i_m <= CIntegrity(Qc)
                result.append("s.add(cIntegrity(" + objSig.omArgusI.get(mName).element1.get(i) + ", " + infer.oInfo.get(objName).Qc);

                //c_m <= c_m', i_m <= i_m', a_m <= a_m'
                result.append("s.add(lableLe(" + objSig.omArgusC.get(mName).element1.get(i) + ", " + objSig.omArgusC.get(mName).element2 +
                        ", " + objSig.omArgusI.get(mName).element1.get(i) + ", " + objSig.omArgusI.get(mName).element2 + ", " +
                        objSig.omArgusA.get(mName).element1.get(i) + ", " + objSig.omArgusA.get(mName).element2 + "))\n");
            }
        }
        return result;
    }

    //n is the index of method definition in the array
    public StringBuilder methodCheck(MethodDefinition m, int n, TypeInference infer, ArrayList<String> mArgNames, MethodInfo mInfo){
        StringBuilder result = new StringBuilder();
        //need to differentiate res and other method call setup
        if(n != 0){
            // if the free variable in m is empty, we set bot = context
            if(m.freeVars.isEmpty()){
                result.append(mInfo.arguC.get(0) + " = " + mInfo.mcontextC + "\n");
                result.append(mInfo.arguI.get(0) + " = " + mInfo.mcontextI + "\n");
                result.append(mInfo.arguA.get(0) + " = " + mInfo.mcontextA + "\n");
            }
            else {
                //set up the input arguments to type the body of method and the object call
                for(Expression arg : m.freeVars){
                    int indexForArg = mArgNames.indexOf(arg.toString());

                    ArrayList<String> argC = new ArrayList<>();
                    argC.add(mInfo.arguC.get(indexForArg));
                    infer.statementC.put(arg.toString(), argC);
                    ArrayList<String> argI = new ArrayList<>();
                    argI.add(mInfo.arguI.get(indexForArg));
                    infer.statementI.put(arg.toString(), argI);
                    ArrayList<String> argA = new ArrayList<>();
                    argA.add(mInfo.arguA.get(indexForArg));
                    infer.statementA.put(arg.toString(), argA);
                }
            }

            //set the current hosts
            infer.environment.put(m.body, new envForTypeInfer());
            infer.environment.put(m.objectCall, new envForTypeInfer());
            infer.environment.get(m.body).currentHosts = mInfo.host;
            infer.environment.get(m.objectCall).currentHosts = mInfo.host;

            //set the initial context
            ArrayList<String> mconxtC = new ArrayList<>();
            mconxtC.add(mInfo.mcontextC);
            infer.environment.get(m.body).currentContextC = mconxtC;
            ArrayList<String> mconxtI = new ArrayList<>();
            mconxtI.add(mInfo.mcontextI);
            infer.environment.get(m.body).currentContextI = mconxtI;
            ArrayList<String> mconxtA = new ArrayList<>();
            mconxtA.add(mInfo.mcontextA);
            infer.environment.get(m.body).currentContextA = mconxtA;

            //set the administrative x type in the method body
            result.append(((StringBuilder) infer.visitDispatch(m.objectCall)).toString());
            infer.statementC.put(m.objectCall.administrativeX.toString(), infer.statementC.get(m.objectCall.toString()));
            infer.statementI.put(m.objectCall.administrativeX.toString(), infer.statementI.get(m.objectCall.toString()));
            infer.statementA.put(m.objectCall.administrativeX.toString(), infer.statementA.get(m.objectCall.toString()));

            result.append(((StringBuilder) infer.visitDispatch(m.body)).toString());
            //todo: the part about return type is not implemented right now

            //i_1 <= cIntegrity(Qc)
            for(String argI : mInfo.arguI){
                //\tau_1 <= \tau_2
                if(n != infer.mInfo.keySet().size() - 1){
                    result.append("s.add(cIntegrity(" + argI + ", " + mInfo.qc + "))\n");
                }
                else {
                    result.append("s.add(cIntegrityE(" + argI + ", " + mInfo.qc + "))\n");
                }
            }

            //c_x <= H
            result.append("s.add(cLeH(" + mInfo.mcontextC + ", " + mInfo.host + "))\n");

            return result;
        }
        //this is the set up for the res method
        else {
            result.append("s.add(cLeH(resultC, resH))\n");
            result.append("s.add(cIntegrity(resultI, resQ))\n");

            return result;
        }
    }

    public StringBuilder classTypeCheck(
            ArrayList<MethodDefinition> methods,
            ArrayList<ArrayList<String>> methodArgNames,
            HashMap<String, HashMap<String, Integer>> objectMethods,
            int num, ArrayList<Integer> p,
            ArrayList<Boolean> rc, ArrayList<ArrayList<Integer>> ri, ArrayList<ArrayList<Integer>> ra,
            ArrayList<Boolean> sc, ArrayList<ArrayList<Integer>> si, ArrayList<ArrayList<Integer>> sa,
            ArrayList<Boolean> bc, ArrayList<ArrayList<Integer>> bi, ArrayList<ArrayList<Integer>> ba,
            ArrayList<Integer> rH, HashMap<String, ArrayList<Boolean>> pV, HashMap<String, HashMap<String, ArrayList<Boolean>>> pOM){

        StringBuilder r = new StringBuilder();
        TypeInference infer = new TypeInference(num, p, rc, ri, ra, sc, si, sa, bc, bi, ba, rH, pV, pOM);

        r.append("n = " + num + "\n");
        r.append("principals = " + infer.hTrans(p));

        //set the predefined variable value in statementC
        for(String vname: pV.keySet()){
            ArrayList<String> vc = new ArrayList<>();
            vc.add(vname + "C");
            infer.statementC.put(vname, vc);
            r.append(vname + "C" + infer.cTrans(pV.get(vname)));

            //set variables for integrity and availability
            ArrayList<String> vi = new ArrayList<>();
            vi.add(vname + "I");
            infer.statementI.put(vname, vi);
            //initialize the variables
            r.append(infer.bVarGen(vname, "I"));
            //add range constraints
            r.append(infer.QSRangeConst(vname + "I"));

            ArrayList<String> va = new ArrayList<>();
            va.add(vname + "A");
            infer.statementA.put(vname, va);
            //initialize the variables
            r.append(infer.bVarGen(vname, "A"));
            //add range constraints
            r.append(infer.QSRangeConst(vname + "A"));
        }

        //print start context
        r.append("startC" + infer.cTrans(sc));
        r.append("startI" + infer.bTrans(si));
        r.append("startA" + infer.bTrans(sa));

        //print bot type
        r.append("botC" + infer.cTrans(bc));
        r.append("botI" + infer.bTrans(bi));
        r.append("botA" + infer.bTrans(ba));

        //print result type
        r.append("resultC" + infer.cTrans(rc));
        r.append("resultI" + infer.bTrans(ri));
        r.append("resultA" + infer.bTrans(ra));

        //print retH
        r.append("resH = " + infer.hTrans(rH));

        //initial resQ
        r.append("resQ = [ [ Int(\"resQ_%s_%s\" % (i, j)) for j in range(n) ] for i in range(n) ]\n");
        r.append("s.add([ And(0 <= resQ[i][j]) for i in range(n) for j in range(n) ])\n");
        r.append("s.add([ And(sLe(resQ[i], principals)) for i in range(n) ])\n");

        //set the mInfo, the index start with 1 to ignore res method
        for(int i = 1; i < methods.size(); i++){
            infer.mInfo.put(methods.get(i).thisMethodName.toString(),
                    new MethodInfo(methods.get(i).thisMethodName.toString(), methods.get(i).freeVars.size(), methodArgNames.get(i)));
            r.append(infer.mInfo.get(methods.get(i).thisMethodName.toString()).initMethod());
            r.append(infer.mInfo.get(methods.get(i).thisMethodName.toString()).mRangeCons());
        }
        //set up mInfo for res method
        infer.mInfo.put(methods.get(0).thisMethodName.toString(), new MethodInfo());

        //set the oInfo
        for(String on: objectMethods.keySet()){
            infer.oInfo.put(on, new ObjectInfo(on, objectMethods.get(on)));
            r.append(infer.oInfo.get(on).initObject(infer.predefinedOM.get(on)));
            r.append(infer.oInfo.get(on).oRangeCons());
        }

        //first do field check then we do method check
        for(String oname : objectMethods.keySet()){
            r.append(fieldCheck(oname, infer).toString());
        }

        //then the method checks
        for(int i = 0; i < methods.size(); i++){
            r.append(methodCheck(methods.get(i), i, infer, methodArgNames.get(i), infer.mInfo.get(methods.get(i).thisMethodName.toString())));
        }

        //we need to have a this call check for the entrance method (the last method in the method definition array)
        r.append(infer.entranceThisCallT(methods.get(methods.size() - 1), methodArgNames.get(methodArgNames.size() - 1)).toString());

        return r;
    }

    //transfer confidentiality information from ArrayList<Boolean> to [False, True, True]
    public String cTrans(ArrayList<Boolean> c){
        StringBuilder result = new StringBuilder();
        result.append(" = [ ");
        for(int i = 0; i < c.size(); i++){
            if(i == c.size()-1){
                if(c.get(i) == true){ result.append("True ]\n"); }
                else {result.append("False ]\n");}
            }
            else {
                if(c.get(i) == true){ result.append("True, "); }
                else {result.append("False, ");}
            }
        }
        return result.toString();
    }

    //transfer integrity and availability information from ArrayList<ArrayList<Integer>> to [[1, 2, 3], [0, 0, 2], [0, 0, 0]]
    public String bTrans(ArrayList<ArrayList<Integer>> b){
        StringBuilder result = new StringBuilder();
        result.append(" = [");
        for(int i = 0; i < b.size(); i++){
            result.append("[ ");
            for(int j = 0; j < b.get(i).size(); j++){
                if(j == b.get(i).size() -1){
                    result.append(b.get(i).get(j).toString());
                }
                else {
                    result.append(b.get(i).get(j).toString() + ", ");
                }
            }
            if(i == b.size() -1){
                result.append("] ");
            }
            else {
                result.append("], ");
            }
        }
        result.append("]\n");

        return result.toString();
    }

    //transfer host information from ArrayList<Integer> to [0, 0, 1]
    public String hTrans(ArrayList<Integer> h){
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        for(int i = 0; i < h.size(); i++){
            if(i == h.size() - 1){
                result.append(h.get(i).toString() + "]\n");
            }
            else {
                result.append(h.get(i).toString() + ", ");
            }
        }
        return result.toString();
    }

    //generate confidentiality variable array according to the given name
    public String cVarGen(String cname){
        StringBuilder result = new StringBuilder();
        result.append(cname + "C" + " = [ Bool(\'" + cname + "C" + "_%s\' % i) for i in range(n) ]\n");
        return result.toString();
    }

    //generate integrity and availability variable arrays according to the given name
    public String bVarGen(String bname, String IorA){
        StringBuilder result = new StringBuilder();
        result.append(bname + IorA + " = [ [ Int(\"" + bname + IorA + "_%s_%s\" % (i, j)) for j in range(n) ] for i in range(n) ]\n");
        return result.toString();
    }

    //the constraints for quorum systems like [[], [], []]
    public String QSRangeConst(String qsName){
        StringBuilder result = new StringBuilder();
        // for the >= 0 constraint
        result.append(qsName + "range0" + " = [ And(0 <= " + qsName + "[i][j]) for i in range(n) for j in range(n) ]\n");
        result.append("s.add(" + qsName + "range0)\n" );

        //for the <= principals constraints
        result.append(qsName + "range1" + " = [And(sLe(" + qsName + "[i], principals)) for i in range(n)]\n");
        result.append("s.add(" + qsName + "range1)\n" );

        return result.toString();
    }

    public StringBuilder entranceThisCallT(MethodDefinition mDef, ArrayList<String> mn){
        StringBuilder result = new StringBuilder();

        //start \tau_x <= \tau_x'
        result.append("s.add(cLe(startC, " + mInfo.get(mDef.thisMethodName.toString()).mcontextC + "))\n");
        result.append("s.add(bLe(" + mInfo.get(mDef.thisMethodName.toString()).mcontextI + ", startI))\n");
        result.append("s.add(bLe(" + mInfo.get(mDef.thisMethodName.toString()).mcontextA + ", startA))\n");

        //when there is no argument for the method
        //we need to add dummy argument for the implicit constraints
        //now we have changed the dummy variable to the argument named "bot" in the mAName
        //now all the implicit constraints are forced by the bot argument
        //for example, \tau_1 can have the same type as \tau_x' and the availability constraint is on the A(\tau_x')
        if(mDef.freeVars == null || mDef.freeVars.size() == 0){
            //a1 <= AvailabilityP(a1, Q|H)
            result.append("s.add(availabilityP(" + mInfo.get(mDef.thisMethodName.toString()).arguA.get(0) +
                    ", " + mInfo.get(mDef.thisMethodName.toString()).qc + ", resH))\n");

            ////\tau_x <= \tau_1
            result.append("s.add(cLe(startC, " + mInfo.get(mDef.thisMethodName.toString()).arguC.get(0) + "))\n");
            result.append("s.add(bLe(" + mInfo.get(mDef.thisMethodName.toString()).arguI.get(0) + ", startI))\n");
            result.append("s.add(bLe(" + mInfo.get(mDef.thisMethodName.toString()).arguA.get(0) + ", startA))\n");

            //set the return value for the method
            //ArrayList<String> retC = new ArrayList<>();
            //retC.add("resultC");
            //ArrayList<String> retI = new ArrayList<>();
            //retI.add("resultI");
            //ArrayList<String> retA = new ArrayList<>();
            //retA.add("resultA");
            //statementC.put(singleCall.toString(), retC);
            //statementI.put(singleCall.toString(), retI);
            //statementA.put(singleCall.toString(), retA);

            return result;
        }
        else {
            for(Var arg: mDef.freeVars){
                //for the entrance method, all the arguments are from user. There is no constraints
                result.append(((StringBuilder) visitDispatch(arg)).toString());
            }
            for (int a = 0; a < mDef.freeVars.size(); a++){
                //\tau_x <= \tau_1
                result.append("s.add(cLe(startC, " + mInfo.get(mDef.thisMethodName.toString()).arguC.get(a) + "))\n");
                result.append("s.add(bLe(" + mInfo.get(mDef.thisMethodName.toString()).arguI.get(a) + ", startI))\n");
                result.append("s.add(bLe(" + mInfo.get(mDef.thisMethodName.toString()).arguA.get(a) + ", startA))\n");

                //\tau <= \tau_1
                String cVariable = statementC.get(mn.get(a)).get(0);
                result.append("s.add(cLe(" + cVariable + ", " + mInfo.get(mDef.thisMethodName.toString()).arguC.get(a) + "))\n");
                String iVariable = statementI.get(mn.get(a)).get(0);
                result.append("s.add(bLe(" + mInfo.get(mDef.thisMethodName.toString()).arguI.get(a)  + ", " + iVariable + "))\n");
                String aVariable = statementA.get(mn.get(a)).get(0);
                result.append("s.add(bLe(" + mInfo.get(mDef.thisMethodName.toString()).arguA.get(a)  + ", " + aVariable + "))\n");

                //a1 <= Availability(Q|H)
                result.append("s.add(availabilityP(" + mInfo.get(mDef.thisMethodName.toString()).arguA.get(a) +
                        ", " + mInfo.get(mDef.thisMethodName.toString()).qc + ", resH))\n");
            }

            //set the return value for the method
            //ArrayList<String> retC = new ArrayList<>();
            //retC.add("resultC");
            //ArrayList<String> retI = new ArrayList<>();
            //retI.add("resultI");
            //ArrayList<String> retA = new ArrayList<>();
            //retA.add("resultA");
            //statementC.put(singleCall.toString(), retC);
            //statementI.put(singleCall.toString(), retI);
            //statementA.put(singleCall.toString(), retA);

        return result; }
    }
}
