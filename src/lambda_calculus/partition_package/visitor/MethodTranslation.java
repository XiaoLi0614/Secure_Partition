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
import lambda_calculus.partition_package.tree.expression.op.Compare;
import lambda_calculus.partition_package.tree.expression.op.Plus;
import lesani.collection.Pair;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

public class MethodTranslation implements PartitionVisitor{
    //the output path for the usecase, which should be a folder
    String outputPath;
    String usecaseName;

    //map method name to runtime object class name,
    //eg: m0-> bftsmart.usecase.onetimetransfer_optimized.OTTA
    HashMap<String, ArrayList<String>> methodHost;
    //object placement, which is the map from object name to class name
    //eg: i1-> bftsmart.usecase.onetimetransfer_optimized.OTTA
    HashMap<String, ArrayList<String>> objectHost;

    int n;

    //the map from trust domain name to the runtime class name
    //eg: A-> bftsmart.usecase.onetimetransfer_optimized.OTTA
    HashMap<String, String> runtimeClassDomain;

    //the order corresponding to the same order for the domain, eg: A, B, C, client
    ArrayList<String> domainNames;

    //the number of tab need to be append in the current line
    int tabNum;

    //the primary type of object methods should be given by user
    HashMap<String, String> objectMethodReturnType;
    //the object argument type, which should be given by user
    HashMap<String, ArrayList<String>> objectMethodArgType;

    //HashMap<String, String> administartiveType;
    String currentMethodName;

    //the input argument types for the method, which should be updated by the object method call
    HashMap<String, String> argumentType;

    //the object classes implemented by the users
    ArrayList<String> importedObjects;

    //the user defined object primary type
    HashMap<String, String> objPrimaryType;

    //user defined request related types
    String requestName;
    ArrayList<String> requestArgs;
    Pair<String, String> retType;

    public MethodTranslation(String uName, String outFolder){
        outputPath = outFolder;
        usecaseName = uName;
        n = 0;
        methodHost = new HashMap<>();
        objectHost = new HashMap<>();
        domainNames = new ArrayList<>();
        runtimeClassDomain = new HashMap<>();
        tabNum = 1;
        initializeObjectRet(usecaseName);
    }

    public void initializeObjectRet(String usecaseName){
        switch (usecaseName){
            case "ott":
                objectMethodReturnType = new HashMap<>();
                objectMethodReturnType.put("i1.read", "Integer");
                objectMethodReturnType.put("i2.read", "Integer");
                objectMethodReturnType.put("a.write", "Boolean");
                objectMethodReturnType.put("a.read", "Boolean");
                ArrayList<String> aWriteType = new ArrayList<>();
                aWriteType.add("Boolean");
                objectMethodArgType = new HashMap<>();
                objectMethodArgType.put("a.write", aWriteType);
                argumentType = new HashMap<>();
                argumentType.put("x", "Integer");
                argumentType.put("1", "Integer");
                importedObjects = new ArrayList<>();
                importedObjects.add("bftsmart.demo.register.BooleanRegisterClient");
                importedObjects.add("bftsmart.demo.register.IntegerRegisterClient");
                importedObjects.add("bftsmart.usecase.PartitionedObject");
                objPrimaryType = new HashMap<>();
                objPrimaryType.put("i1", "IntegerRegisterClient");
                objPrimaryType.put("i2", "IntegerRegisterClient");
                objPrimaryType.put("a", "BooleanRegisterClient");
                requestName = "transfer";
                requestArgs = new ArrayList<>();
                requestArgs.add("x");
                retType = new Pair<>("x", "Integer");
                break;
            case "ot":
                objectMethodReturnType = new HashMap<>();
                objectMethodReturnType.put("i1.read", "Integer");
                objectMethodReturnType.put("i2.read", "Integer");
                objectMethodReturnType.put("a.write", "Boolean");
                objectMethodReturnType.put("a.read", "Boolean");
                ArrayList<String> aWriteType1 = new ArrayList<>();
                aWriteType1.add("Boolean");
                objectMethodArgType = new HashMap<>();
                objectMethodArgType.put("a.write", aWriteType1);
                argumentType = new HashMap<>();
                argumentType.put("x", "Integer");
                argumentType.put("1", "Integer");
                importedObjects = new ArrayList<>();
                importedObjects.add("bftsmart.demo.register.BooleanRegisterClient");
                importedObjects.add("bftsmart.demo.register.IntegerRegisterClient");
                importedObjects.add("bftsmart.usecase.PartitionedObject");
                objPrimaryType = new HashMap<>();
                objPrimaryType.put("i1", "IntegerRegisterClient");
                objPrimaryType.put("i2", "IntegerRegisterClient");
                objPrimaryType.put("a", "BooleanRegisterClient");
                requestName = "transfer";
                requestArgs = new ArrayList<>();
                requestArgs.add("x");
                retType = new Pair<>("x", "Integer");
                break;
            case "friendmap":
                objectMethodReturnType = new HashMap<>();
                objectMethodReturnType.put("mapServ.getMap", "String");
                objectMethodReturnType.put("Alice.newBox", "String");
                objectMethodReturnType.put("Alice.ID", "Integer");
                objectMethodReturnType.put("Alice.expand", "String");
                objectMethodReturnType.put("Alice.addComment", "String");
                objectMethodReturnType.put("Bob.ID", "Integer");
                objectMethodReturnType.put("Bob.location", "String");
                objectMethodReturnType.put("Bob.comment", "String");
                objectMethodReturnType.put("Snapp.isFriend", "Boolean");
                ArrayList<String> snappFriendType = new ArrayList<>();
                snappFriendType.add("Integer");
                snappFriendType.add("Integer");
                ArrayList<String> aExpandType = new ArrayList<>();
                aExpandType.add("String");
                aExpandType.add("String");
                ArrayList<String> addCommentType = new ArrayList<>();
                addCommentType.add("String");
                addCommentType.add("String");
                ArrayList<String> getMapType = new ArrayList<>();
                getMapType.add("String");
                objectMethodArgType = new HashMap<>();
                objectMethodArgType.put("Snapp.isFriend", snappFriendType);
                objectMethodArgType.put("Alice.expand", aExpandType);
                objectMethodArgType.put("Alice.addComment", addCommentType);
                objectMethodArgType.put("mapServ.getMap", getMapType);
                argumentType = new HashMap<>();
                importedObjects = new ArrayList<>();
                importedObjects.add("bftsmart.demo.friendmap.AliceClient");
                importedObjects.add("bftsmart.demo.friendmap.BobClient");
                importedObjects.add("bftsmart.usecase.Client");
                importedObjects.add("bftsmart.demo.friendmap.SnappClient");
                importedObjects.add("bftsmart.usecase.PartitionedObject");
                importedObjects.add("bftsmart.demo.friendmap.MapServiceClient");
                objPrimaryType = new HashMap<>();
                objPrimaryType.put("mapServ", "MapServiceClient");
                objPrimaryType.put("Snapp", "SnappClient");
                objPrimaryType.put("Alice", "AliceClient");
                objPrimaryType.put("Bob", "BobClient");
                requestName = "friendMap";
                requestArgs = new ArrayList<>();
                retType = new Pair<>("map", "String");
                break;
            case "ticket":
                objectMethodReturnType = new HashMap<>();
                objectMethodReturnType.put("customer.ticketNum", "Integer");
                objectMethodReturnType.put("airline.getPrice1", "String");
                objectMethodReturnType.put("airline.getPrice2", "Integer");
                objectMethodReturnType.put("customer.updateInfo", "void");
                objectMethodReturnType.put("customer.getID", "Integer");
                objectMethodReturnType.put("airline.decSeat", "void");
                objectMethodReturnType.put("bank.getBalance1", "Integer");
                objectMethodReturnType.put("bank.getBalance2", "Integer");
                objectMethodReturnType.put("customer.updatePayment", "void");
                objectMethodReturnType.put("bank.decBalance", "void");
                ArrayList<String> getPrice1Type = new ArrayList<>();
                getPrice1Type.add("Integer");
                ArrayList<String> getPrice2Type = new ArrayList<>();
                getPrice2Type.add("Integer");
                ArrayList<String> updateInfoType = new ArrayList<>();
                updateInfoType.add("String");
                updateInfoType.add("Integer");
                ArrayList<String> getBalance1Type = new ArrayList<>();
                getBalance1Type.add("Integer");
                ArrayList<String> getBalance2Type = new ArrayList<>();
                getBalance2Type.add("Integer");
                ArrayList<String> updatePaymentType = new ArrayList<>();
                updatePaymentType.add("Integer");
                updatePaymentType.add("Integer");
                ArrayList<String> decSeatType = new ArrayList<>();
                decSeatType.add("Integer");
                ArrayList<String> decBalanceType = new ArrayList<>();
                decBalanceType.add("Integer");
                objectMethodArgType = new HashMap<>();
                objectMethodArgType.put("airline.getPrice1", getPrice1Type);
                objectMethodArgType.put("airline.getPrice2", getPrice2Type);
                objectMethodArgType.put("customer.updateInfo", updateInfoType);
                objectMethodArgType.put("bank.getBalance1", getBalance1Type);
                objectMethodArgType.put("bank.getBalance2", getBalance2Type);
                objectMethodArgType.put("customer.updatePayment", updatePaymentType);
                objectMethodArgType.put("airline.decSeat", decSeatType);
                objectMethodArgType.put("bank.decBalance", decBalanceType);
                argumentType = new HashMap<>();
                argumentType.put("1", "Integer");
                argumentType.put("0", "Integer");
                importedObjects = new ArrayList<>();
                importedObjects.add("bftsmart.demo.airlineagent.AirlineAgentClient");
                importedObjects.add("bftsmart.runtime.util.IntIntPair");
                importedObjects.add("bftsmart.usecase.PartitionedObject");
                importedObjects.add("bftsmart.demo.bankagent.BankAgentClient");
                importedObjects.add("bftsmart.demo.useragent.UserAgentClient");
                objPrimaryType = new HashMap<>();
                objPrimaryType.put("airline", "AirlineAgentClient");
                objPrimaryType.put("bank", "BankAgentClient");
                objPrimaryType.put("customer", "UserAgentClient");
                requestName = "buyTicket";
                requestArgs = new ArrayList<>();
                //todo: may need to add ticketNum here
                retType = new Pair<>("bought", "Boolean");
                break;
            case "auction":
                objectMethodReturnType = new HashMap<>();
                objectMethodReturnType.put("user.read", "Integer");
                objectMethodReturnType.put("A.makeOffer1", "OfferInfo");
                objectMethodReturnType.put("A.makeOffer2", "Integer");
                objectMethodReturnType.put("user.update", "void");
                objectMethodReturnType.put("B.makeOffer1", "OfferInfo");
                objectMethodReturnType.put("B.makeOffer2", "Integer");
                objectMethodReturnType.put("user.declareWinner", "OfferInfo");
                ArrayList<String> AmakeOffer1Type = new ArrayList<>();
                AmakeOffer1Type.add("Integer");
                AmakeOffer1Type.add("Integer");
                ArrayList<String> AmakeOffer2Type = new ArrayList<>();
                AmakeOffer2Type.add("Integer");
                AmakeOffer2Type.add("Integer");
                ArrayList<String> BmakeOffer1Type = new ArrayList<>();
                BmakeOffer1Type.add("Integer");
                BmakeOffer1Type.add("Integer");
                ArrayList<String> BmakeOffer2Type = new ArrayList<>();
                BmakeOffer2Type.add("Integer");
                BmakeOffer2Type.add("Integer");
                ArrayList<String> updateType = new ArrayList<>();
                updateType.add("OfferInfo");
                updateType.add("Integer");
                ArrayList<String> declareWinnerType = new ArrayList<>();
                declareWinnerType.add("Integer");
                objectMethodArgType = new HashMap<>();
                objectMethodArgType.put("A.makeOffer1", AmakeOffer1Type);
                objectMethodArgType.put("A.makeOffer2", AmakeOffer2Type);
                objectMethodArgType.put("B.makeOffer1", BmakeOffer1Type);
                objectMethodArgType.put("B.makeOffer2", BmakeOffer2Type);
                objectMethodArgType.put("user.update", updateType);
                objectMethodArgType.put("user.declareWinner", declareWinnerType);
                argumentType = new HashMap<>();
                argumentType.put("o", "Integer");
                //argumentType.put("1", "Integer");
                //argumentType.put("0", "Integer");
                importedObjects = new ArrayList<>();
                importedObjects.add("bftsmart.demo.airlineagent.AirlineAgentClient");
                importedObjects.add("bftsmart.usecase.PartitionedObject");
                importedObjects.add("bftsmart.demo.useragent.UserAgentClient");
                importedObjects.add("bftsmart.usecase.Client");
                objPrimaryType = new HashMap<>();
                objPrimaryType.put("A", "AirlineAgentClient");
                objPrimaryType.put("B", "AirlineAgentClient");
                objPrimaryType.put("user", "UserAgentClient");
                requestName = "auction";
                requestArgs = new ArrayList<>();
                requestArgs.add("o");
                retType = new Pair<>("offer", "OfferInfo");
                break;
             default:
                System.out.println("Please input supported use-cases!");
                break;
        }
    }

    public StringBuilder insertTab(){
        StringBuilder result = new StringBuilder();
        result.append("\n");
        for(int i = 0; i < this.tabNum; i++){
            result.append("\t");
        }
        return result;
    }

    public void readInfoFromFile(String filePath){
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader rd = new BufferedReader(fr);
            String line = null;
            int classNum = 0;
            while ((line = rd.readLine()) != null) {
                //the line contains trust domain information in order
                if(line.startsWith("#")) {
                    classNum++;
                    String hostsSetName = line.split("\\s+")[1];
                    String partitionedClassName = line.split("\\s+")[2];
                    runtimeClassDomain.put(hostsSetName, partitionedClassName);
                    domainNames.add(hostsSetName);
                }
                //define the number of trust domain
                else if(line.startsWith("n =")){
                    String num = line.replaceAll("[^\\d]", "");
                    n = Integer.valueOf(num);
                }
                //read the method hosts.
                else if(Pattern.compile("resH|m\\d+H").matcher(line).find()){
                    String methodName = line.substring(0, line.indexOf('H'));
                    if(methodName.equals("res")){
                        methodName = "ret";
                    }
                    String str = line.substring(line.indexOf('['), line.indexOf(']')+1);
                    //produce the host from string like: [0, 0, 1]
                    str = str.replaceAll("[^\\d]", " ");
                    str =  str.trim();
                    ArrayList<String> methodClass = new ArrayList<>();
                    for(int i = 0; i < n; i++){
                        int hNum = Integer.parseInt(str.split("\\s+")[i]);
                        if(hNum <= 0){ }
                        //we get the object class where the method residents by trust domain name
                        else {
                            methodClass.add(runtimeClassDomain.get(domainNames.get(i)));
                        }
                    }
                    //we need to set ret method on bftsmart.Client object
                    if(methodName.equals("ret")){
                        ArrayList<String> retClass = new ArrayList<>();
                        retClass.add(runtimeClassDomain.get("Client"));
                        methodHost.put(methodName, retClass);
                    }
                    else {
                        methodHost.put(methodName, methodClass);
                    }
                }
                //regular expression for object hosts
                else if(Pattern.compile("\\w+OH").matcher(line).find()){
                    String objectName = line.substring(0, line.indexOf("OH"));
                    String str = line.substring(line.indexOf('['), line.indexOf(']')+1);
                    //produce the host from string like: [0, 0, 1]
                    str = str.replaceAll("[^\\d]", " ");
                    str =  str.trim();
                    ArrayList<String> objectClass = new ArrayList<>();
                    for(int i = 0; i < n; i++){
                        int hNum = Integer.parseInt(str.split("\\s+")[i]);
                        if(hNum <= 0){ }
                        else {
                            objectClass.add(runtimeClassDomain.get(domainNames.get(i)));
                        }
                    }
                    objectHost.put(objectName, objectClass);
                }
            }
            fr.close();
            rd.close();
        }
        catch (IOException e)
        {
            System.out.println("Cannot read use-case config file");
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
                StringBuilder result = new StringBuilder();
                result.append(id.toString());
                return result;}
        }
        GIdB gIdB = new GIdB();
        @Override
        public Object visit(GId gId){ return gId.accept(gIdB); }

        public class LiteralB implements LiteralVisitor<Object>{
            @Override
            public Object visit(IntLiteral intLiteral){
                StringBuilder result = new StringBuilder();
                result.append(intLiteral.toString());
                return result;
            }
        }
        LiteralB literalB = new LiteralB();
        @Override
        public Object visit(Literal literal){ return literal.accept(literalB); }

        public class BinaryOpB implements BinaryOpVisitor<Object>{
            @Override
            public Object visit(Plus plus){
                StringBuilder result = ((StringBuilder) visitDispatch(plus.operand1));
                result.append(" " + plus.operatorText + " ");
                result.append(((StringBuilder) visitDispatch(plus.operand2)).toString());
                return result;
            }

            @Override
            public Object visit(Compare compare){
                StringBuilder result = ((StringBuilder) visitDispatch(compare.operand1));
                result.append(" " + compare.operatorText + " ");
                result.append(((StringBuilder) visitDispatch(compare.operand2)).toString());
                return result;
            }
        }
        BinaryOpB binaryOpB = new BinaryOpB();
        @Override
        public Object visit(BinaryOp binaryOp){ return binaryOp.accept(binaryOpB); }

        @Override
        public Object visit(Var var){
            StringBuilder result = new StringBuilder();
            result.append(var.toString());
            return result;
        }

        @Override
        public Object visit(Conditional conditional){
            StringBuilder result = new StringBuilder();
            result.append(insertTab());
            result.append("if(");

            //if statement
            //if the conditional is not of boolean type, we need to translate it. eg: if(x) -> if(x == 1)
            if(argumentType.get(conditional.condition.toString()) != null && !argumentType.get(conditional.condition.toString()).equals("Boolean")){
                if(argumentType.get(conditional.condition.toString()).equals("Integer")){
                    result.append((StringBuilder) visitDispatch(conditional.condition) + " == 1");
                }
                else if(argumentType.get(conditional.condition.toString()).equals("void")){
                    result.append("true");
                }
            }
            else {
                result.append((StringBuilder) visitDispatch(conditional.condition));
            }
            tabNum++;
            result.append("){");
            //result.append(insertTab());
            result.append((StringBuilder)visitDispatch(conditional.ifExp)).toString();
            tabNum--;
            result.append(insertTab() + "}");

            //else statement
            result.append(insertTab() + "else{");
            tabNum++;
            //result.append(insertTab());
            result.append((StringBuilder)visitDispatch(conditional.elseExp)).toString();
            tabNum--;
            result.append(insertTab() + "}");

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
            StringBuilder result = (StringBuilder) visitDispatch(expSt.expression);
            return result;
        }

        @Override
        public Object visit(If iF){
            StringBuilder result = new StringBuilder();
            result.append(insertTab());
            result.append("if(");

            //if statement
            //if the conditional is not of boolean type, we need to translate it. eg: if(x) -> if(x == 1)
            if(argumentType.get(iF.condition.toString()) != null && !argumentType.get(iF.condition.toString()).equals("Boolean")){
                if(argumentType.get(iF.condition.toString()).equals("Integer")){
                    result.append((StringBuilder) visitDispatch(iF.condition) + " == 1");
                }
                else if(argumentType.get(iF.condition.toString()).equals("void")){
                    result.append("true");
                }
            }
            else {
                result.append((StringBuilder) visitDispatch(iF.condition));
            }
            tabNum++;
            result.append("){");
            //result.append(insertTab());
            result.append((StringBuilder)visitDispatch(iF.command1)).toString();
            tabNum--;
            result.append(insertTab() + "}");

            //else statement
            result.append(insertTab() + "else{");
            tabNum++;
            //result.append(insertTab());
            result.append((StringBuilder)visitDispatch(iF.command2)).toString();
            tabNum--;
            result.append(insertTab() + "}");

            return result;
        }

        @Override
        public Object visit(Sequence sequence){
            StringBuilder result = new StringBuilder();
            result.append((StringBuilder) visitDispatch(sequence.command1));
            result.append((StringBuilder) visitDispatch(sequence.command2));
            return result;
        }

        @Override
        //method and object calls are all in the single call node
        public Object visit(SingleCall singleCall){
            //when this is a method call (ThisCallT)
            StringBuilder result = new StringBuilder();
            if(singleCall.objectName.toString() =="this"){
                //call the next method without any return
                result.append(insertTab() +
                        "runtime.invoke(\"" + singleCall.methodName.lexeme + "\", callerId+\"::" +
                        currentMethodName + "\", ++n");

                if(singleCall.args == null || singleCall.args.length == 0){
                    result.append(");");
                    return result;
                }
                //In ticket, void is passed to ret, we need to translate void -> boolean
                else {
                    for(int a = 0; a < singleCall.args.length; a++){
                        Expression argE = singleCall.args[a];
                        if(a == singleCall.args.length - 1){
                            if(singleCall.methodName.lexeme == "ret" && argumentType.get(argE.toString()) != null && retType.element2.equals("Boolean")){
                            //if(singleCall.methodName.lexeme == "ret"){
                                if(argumentType.get(argE.toString()).equals("void")){
                                    result.append( ", true);");
                                }
                            else if(argumentType.get(argE.toString()).equals("Integer")){
                                    if(argE.toString().equals("1")){
                                        result.append( ", true);");
                                    }
                                    else {
                                        result.append( ", false);");
                                    }
                                }
                            }
                            else {
                                result.append( ", " + ((StringBuilder) visitDispatch(argE)) + ");");
                            }
                            //result.append( ", " + ((StringBuilder) visitDispatch(argE)) + ");");
                        }
                        else {
                            result.append(", " + ((StringBuilder) visitDispatch(argE)));
                        }
                    }
                    return result;
                }
            }
            //this is an object call, we need to insert object method return type because of reflective execution
            else {
                //execute the object call with administrative x then do the nested call
                //update argument type for the callee methods
                argumentType.put(singleCall.administrativeX.toString(), objectMethodReturnType.get(singleCall.objectName + "." + singleCall.methodName));
                String Oname = singleCall.objectName.toString();
                String OMName = singleCall.methodName.toString();
                //when the method name is makeOffer, we need to add A or B as appendix.
                if(singleCall.methodName.lexeme.equals("makeOffer1") || singleCall.methodName.lexeme.equals("makeOffer2")){
                    OMName = "makeOffer" + Oname + singleCall.methodName.lexeme.substring(singleCall.methodName.lexeme.length()-1);
                }

                //when the return type is void, we do not need to do administrative var assignment.
                if(objectMethodReturnType.get(singleCall.objectName.toString() + "." + singleCall.methodName.toString()).equals("void")){
                    result.append(insertTab() + "runtime.invokeObj(\"" + Oname + "\", \"" + OMName +
                            "\", \"" + currentMethodName + "\", callerId+\"::" + currentMethodName + "\", ++n");
                }
                else {
                    result.append(insertTab() +  objectMethodReturnType.get(singleCall.objectName.toString() + "." + singleCall.methodName.toString()) + " " +
                            singleCall.administrativeX.toString() + " = " + "(" + objectMethodReturnType.get(singleCall.objectName.toString() + "." + singleCall.methodName.toString()) + ") " +
                            "runtime.invokeObj(\"" + Oname + "\", \"" + OMName +
                            "\", \"" + currentMethodName + "\", callerId+\"::" + currentMethodName + "\", ++n");
                }

                if(singleCall.args == null || singleCall.args.length == 0){
                    result.append(");");
                    //return result;
                }
                else {
                    for(int a = 0; a < singleCall.args.length; a++){
                        Expression argE = singleCall.args[a];
                        result.append(", ");
                        //check the argument type is the same as required by the object method
                        if(objectMethodArgType.get(singleCall.objectName + "." + singleCall.methodName) != null &&
                                !objectMethodArgType.get(singleCall.objectName + "." + singleCall.methodName).get(a).equals(argumentType.get(argE.toString()))){
                            //if(argumentType.get(argE.toString()).equals("Integer") && objectMethodArgType.get(singleCall.objectName + "." + singleCall.methodName).equals("Boolean")){
                            //translate integer to boolean
                            if(argumentType.get(argE.toString()) == "Integer"){
                                if(argE.toString().equals("1")){
                                    result.append("true");
                                }
                                else {
                                    result.append("false");
                                }
                            }
                        }
                        else {
                            result.append(((StringBuilder) visitDispatch(argE)).toString());
                        }
                        //result.append(((StringBuilder) visitDispatch(argE)).toString());
                        if(a == singleCall.args.length-1){
                            result.append(");");
                        }
                        else {
                            //result.append(", ");
                        }
                    }
                    //return result;
                }
                //go to the nested command
                //result.append((StringBuilder) visitDispatch(singleCall.nestedCommand));
                return result;
            }
        }
    }

    @Override
    public Object visit(Command command){ return command.accept(commandT); }

    //returns an arraylist of methods in java from each runtime objects
    public HashMap<String, StringBuilder> methodsInJava(ArrayList<MethodDefinition> methodDefs, MethodTranslation mt){
    //public StringBuilder methodsInJava(ArrayList<MethodDefinition> methodDefs){
        //MethodTranslation trans = new MethodTranslation(usecaseName, outFolderPath);
        mt.readInfoFromFile(mt.outputPath + "configs");
        HashMap<String, StringBuilder> result = new HashMap<>();
        //StringBuilder result = new StringBuilder();

        //the entrance method need to be translated first.
        //traverse methods from entrance in order to produce the argument type for callee methods
        for(int i = methodDefs.size()-1; i > 0; i--){
            StringBuilder mTrans = new StringBuilder();
            mTrans.append(insertTab() + "public void " + methodDefs.get(i).thisMethodName + "(String callerId, Integer n");
            if(methodDefs.get(i).freeVars.isEmpty() || methodDefs.get(i).freeVars.size() == 0){
                mTrans.append(")");
            }
            else {
                for(Var arg: methodDefs.get(i).freeVars){
                    mTrans.append(", " + argumentType.get(arg.toString()) + " " + arg.toString());
                }
                //mTrans.replace(mTrans.lastIndexOf(", "), mTrans.lastIndexOf(", ") + 2, ")");
                mTrans.append(")");
            }
            mTrans.append("{");
            mt.tabNum ++;
            mTrans.append(mt.insertTab() + "logger.trace(\"execute " + methodDefs.get(i).thisMethodName + "\");");
            mt.currentMethodName = methodDefs.get(i).thisMethodName.toString();
            mTrans.append(mt.visitDispatch(methodDefs.get(i).objectCall));
            mTrans.append(mt.visitDispatch(methodDefs.get(i).body));
            mt.tabNum--;
            mTrans.append(mt.insertTab() + "}");
            result.put(methodDefs.get(i).thisMethodName.toString(), mTrans);
            //result.add(i, mTrans);
            //result.append(mTrans);
        }

        //todo: the ret and entrance does not need traverse AST tree
        return result;
    }

    //client object class only has two methods: the request method and ret method
    //the output is the whole file including the interface, the package name can be found by runtimeClassDomain
    public StringBuilder clientInJava(String entraceMethod, MethodTranslation mt){

        StringBuilder result = new StringBuilder();
        String packageName = mt.runtimeClassDomain.get("Client").substring(0, mt.runtimeClassDomain.get("Client").lastIndexOf("."));
        String className = mt.runtimeClassDomain.get("Client").substring(mt.runtimeClassDomain.get("Client").lastIndexOf(".")+1, mt.runtimeClassDomain.get("Client").length());
        result.append("package " + packageName + ";\n\n");
        result.append("import bftsmart.usecase.Client;\nimport bftsmart.usecase.PartitionedObject;\n\n");
        result.append("public class " + className + " extends PartitionedObject implements Client {\n");
        result.append("\t@Override\n");
        result.append("\tpublic void request(Object... args) { " + mt.requestName + "(");

        //the initial argument is provided by the user
        if (mt.requestArgs == null || mt.requestArgs.isEmpty()){
            result.append("); }\n\n");
        }
        else {
            for(int i = 0; i < mt.requestArgs.size(); i++){
                result.append("("+ mt.argumentType.get(mt.requestArgs.get(i)) + ") arg[" + i + "], ");
            }
            result.replace(result.lastIndexOf(", "), result.length(), "); }\n\n");
        }

        //the request method
        result.append("\tpublic void " + mt.requestName + "(");
        if (mt.requestArgs == null || mt.requestArgs.isEmpty()){
            result.append(")\n\t{\n\t\t");
        }
        else {
            for(int i = 0; i < mt.requestArgs.size(); i++){
                result.append(mt.argumentType.get(mt.requestArgs.get(i)) + " " + mt.requestArgs.get(i) + ", ");
            }
            result.replace(result.lastIndexOf(", "), result.length(), ")\n\t{\n\t\t");
        }
        result.append("runtime.getExecs().put(sequenceNumber, System.currentTimeMillis());\n\t\t");
        result.append("runtime.invoke(\"" + entraceMethod + "\", \"" + mt.requestName + "\", sequenceNumber++");
        if (mt.requestArgs == null || mt.requestArgs.isEmpty()){
            result.append(");\n\t}\n\n\t");
        }
        else {
            for(int i = 0; i < mt.requestArgs.size(); i++){
                result.append(", " + mt.requestArgs.get(i) );
            }
            result.append(");\n\t}\n\n\t");
        }

        //the ret method is the same for all the usecases
        result.append("public void ret(String callerId, Integer n, " + mt.retType.element2 + " " + mt.retType.element1 + "){\n\t\t" );
        result.append("String seqNumber = callerId.split(\"::\")[1];\n\t\t");
        result.append("int id = Integer.valueOf(seqNumber);\n\t\t");
        result.append("runtime.getExecs().put(id, System.currentTimeMillis() - runtime.getExecs().get(id));\n\t\t");
        result.append("System.out.println(String.format(\"response time for call %s: %s\", id, runtime.getExecs().get(id)));\n\t\t");
        result.append("responseReceived++;\n\t\t");
        result.append("objCallLock.lock();\n\t\t");
        result.append("requestBlock.signalAll();\n\t\t");
        result.append("objCallLock.unlock();\n\t}\n}");
        return result;
    }

    //create a folder which contains the separated methods in different trust domain of java classes
    //the output path need to be in the bftsmart directory
    public void createClasses(MethodTranslation mt, ArrayList<MethodDefinition> mDefs) throws IOException
    {
        HashMap<String, StringBuilder> transformation = mt.methodsInJava(mDefs, mt);
        StringBuilder clientClass = mt.clientInJava(mDefs.get(mDefs.size()-1).thisMethodName.lexeme, mt);
        try {
            //create the folder
            //File f = new File(outputPath + fileName + ".java");
            File folder = new File(outputPath);
            if (!folder.exists()) {
                //f.createNewFile();
                folder.mkdirs();
            }

            //put all the methods and objects in the same class together
            HashMap<String, StringBuilder> runtimeClass = new HashMap<>();
            for(int i = 0; i < mt.domainNames.size(); i++){
                StringBuilder s = new StringBuilder();

                //package information
                s.append("package " + mt.runtimeClassDomain.get(mt.domainNames.get(i)).substring(0, mt.runtimeClassDomain.get(mt.domainNames.get(i)).lastIndexOf(".")) + ";\n\n");

                //import user defined objects
                for(int j = 0; j < mt.importedObjects.size(); j++){
                    s.append("import " + mt.importedObjects.get(j) + ";\n");
                }

                //class interface
                String objName = mt.runtimeClassDomain.get(mt.domainNames.get(i)).substring(mt.runtimeClassDomain.get(mt.domainNames.get(i)).lastIndexOf(".")+1, mt.runtimeClassDomain.get(mt.domainNames.get(i)).length());
                s.append("public class " + objName + " extends PartitionedObject {\n\t");

                runtimeClass.put(mt.runtimeClassDomain.get(mt.domainNames.get(i)), s);
            }

            //init fields for all the host(ArrayList)
            for(String oName: mt.objectHost.keySet()){
                for(String h: mt.objectHost.get(oName)){
                    runtimeClass.get(h).append("public " + mt.objPrimaryType.get(oName) + " " + oName + ";\n\t");
                }
            }

            //init methods for all the hosts(Arraylist)
            for(String mName: mt.methodHost.keySet()){
                for(String h: mt.methodHost.get(mName)){
                    runtimeClass.get(h).append(transformation.get(mName));
                }
            }

            //create separated object class files
            for(String className: mt.runtimeClassDomain.keySet()){
                String fileName = mt.runtimeClassDomain.get(className).substring(mt.runtimeClassDomain.get(className).lastIndexOf(".")+1, mt.runtimeClassDomain.get(className).length());
                File f = new File(outputPath + fileName + ".java");
                if(!className.equals("Client")){
                    //write the collected methods for the specific class
                    FileOutputStream consOutputStream = new FileOutputStream(f, true);
                    StringBuilder content = runtimeClass.get(mt.runtimeClassDomain.get(className)).append("\n}");
                    consOutputStream.write(content.toString().getBytes());
                    consOutputStream.flush();
                    consOutputStream.close();
                }
                //we can flush the client file directly
                else {
                    FileOutputStream consOutputStream = new FileOutputStream(f, true);
                    consOutputStream.write(clientClass.toString().getBytes());
                    consOutputStream.flush();
                    consOutputStream.close();
                }
            }
        }
        catch (IOException e) {
            System.out.println("IO exception when print result to file");
            e.printStackTrace();
        }
    }
}
