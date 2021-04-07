package lambda_calculus.partition_package.visitor;

import lesani.collection.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectInfo {
    String oname; //name for the object
    String Qs; //storage quorum name
    String Qc; //communication quorum name
    //ArrayList<String> methodName; // object method names
    HashMap<String, Pair<ArrayList<String>, String>> omArgusC; //object method argument and return confidentiality
    HashMap<String, Pair<ArrayList<String>, String>> omArgusI; //object method argument and return integrity
    HashMap<String, Pair<ArrayList<String>, String>> omArgusA; //object method argument and return availability
    int rangeO;

    //the hashmap is the map from object method name to the number of argument
    public ObjectInfo(String on, HashMap<String, Integer> arguNum){
        oname = on;
        Qs = on + "qs";
        Qc = on + "qc";
        //methodName = new ArrayList<>();
        omArgusC = new HashMap<>();
        omArgusI = new HashMap<>();
        omArgusA = new HashMap<>();

        rangeO = 0;

        //loop through all the object method in  this object
        for(String m: arguNum.keySet()){
            omArgusC.put(m, new Pair<>(new ArrayList<>(), oname + m + "outputC" ));
            omArgusI.put(m, new Pair<>(new ArrayList<>(), oname + m + "outputI" ));
            omArgusA.put(m, new Pair<>(new ArrayList<>(), oname + m + "outputA" ));
            //automatically generate bot for null input
            if(arguNum.get(m).intValue() == 0){
                omArgusC.get(m).element1.add(oname + m + "botC");
                omArgusI.get(m).element1.add(oname + m + "botI");
                omArgusA.get(m).element1.add(oname + m + "botA");
            }
            else {
                for(int c = 0; c < arguNum.get(m).intValue(); c++){
                    omArgusC.get(m).element1.add(oname + m + "input" + c + "C");
                    omArgusI.get(m).element1.add(oname + m + "input" + c + "I");
                    omArgusA.get(m).element1.add(oname + m + "input" + c + "A");
                }
            }
        }
        return;
    }

    //the input is the predefined confidentiality requirements: object method name maps to requirement
    public String initObject(HashMap<String, ArrayList<Boolean>> predefinedOM){
        StringBuilder result = new StringBuilder();

        //placement information for objects
        result.append(Qs + " = [ [ Int(\"" + Qs + "_%s_%s\" % (i, j)) for j in range(n) ] for i in range(n) ]\n");
        result.append(Qc + " = [ [ Int(\"" + Qc + "_%s_%s\" % (i, j)) for j in range(n) ] for i in range(n) ]\n");

        //initialize the input arguments and output for each object method
        for(String s: omArgusC.keySet()){
            //input arguments initialization
            for(int c = 0; c < omArgusC.get(s).element1.size(); c++){
                if(predefinedOM.keySet().contains(omArgusC.get(s).element1.get(c))){
                    result.append(omArgusC.get(s).element1.get(c) + cTrans(predefinedOM.get(omArgusC.get(s).element1.get(c))));
                }
                else{
                    result.append(omArgusC.get(s).element1.get(c) +
                            " = [ Bool(\'" + omArgusC.get(s).element1.get(c) + "_%s\' % i) for i in range(n) ]\n");
                }
                //result.append(omArgusC.get(s).element1.get(c) +
                        //" = [ Bool(\'" + omArgusC.get(s).element1.get(c) + "_%s\' % i) for i in range(n) ]\n");
                result.append(omArgusI.get(s).element1.get(c) + " = [ [ Int(\"" + omArgusI.get(s).element1.get(c) +
                        "_%s_%s\" % (i, j)) for j in range(n) ] for i in range(n) ]\n");
                result.append(omArgusA.get(s).element1.get(c) + " = [ [ Int(\"" + omArgusA.get(s).element1.get(c) +
                        "_%s_%s\" % (i, j)) for j in range(n) ] for i in range(n) ]\n");
            }
            //output variable initialization
            //if there is a predefined confidentiality for output, use it instead of declare new variables
            if(predefinedOM.keySet().contains(omArgusC.get(s).element2)){
                result.append(omArgusC.get(s).element2 + cTrans(predefinedOM.get(omArgusC.get(s).element2)));
            }
            else{
                result.append(omArgusC.get(s).element2 +
                        " = [ Bool(\'" + omArgusC.get(s).element2 + "_%s\' % i) for i in range(n) ]\n");
            }
            result.append(omArgusI.get(s).element2 + " = [ [ Int(\"" + omArgusI.get(s).element2 +
                    "_%s_%s\" % (i, j)) for j in range(n) ] for i in range(n) ]\n");
            result.append(omArgusA.get(s).element2 + " = [ [ Int(\"" + omArgusA.get(s).element2 +
                    "_%s_%s\" % (i, j)) for j in range(n) ] for i in range(n) ]\n");
        }
        return result.toString();
    }

    public String oRangeCons(){
        StringBuilder result = new StringBuilder();

        //for the >= 0 constraints
        result.append(oname + "range" + rangeO + " = [ And(0 <= " + Qs + "[i][j], 0 <= " + Qc +
                "[i][j], 0 <= ");
        int count = 1;
        for(String s: omArgusI.keySet()){
            result.append(omArgusI.get(s).element2 + "[i][j], 0 <= " +
                    omArgusA.get(s).element2 + "[i][j], 0 <= ");
            for(int i = 0; i < omArgusI.get(s).element1.size(); i++){
                //the ending one
                if((count == omArgusI.keySet().size()) && (i == omArgusI.get(s).element1.size() - 1)){
                    result.append(omArgusI.get(s).element1.get(i) + "[i][j], 0 <= " +
                            omArgusA.get(s).element1.get(i) + "[i][j]) for i in range(n) for j in range(n) ]\n");
                }
                else {
                    result.append(omArgusI.get(s).element1.get(i) + "[i][j], 0 <= " +
                            omArgusA.get(s).element1.get(i) + "[i][j], 0 <= ");
                }
            }
            count++;
        }

        result.append("s.add(" + oname + "range" + rangeO + ")\n");

        //for the <= principals constrains
        rangeO++;
        int count1 = 1;
        result.append(oname + "range" + rangeO + " = [And(sLe(" + Qs + "[i], principals), sLe(" + Qc + "[i], principals), sLe(");
        //for every object method
        for(String s: omArgusI.keySet()){
            result.append(omArgusI.get(s).element2 + "[i], principals), sLe(" + omArgusA.get(s).element2 + "[i], principals), sLe(");
            //for every argument of the method
            for(int j = 0; j < omArgusI.get(s).element1.size(); j++){
                //the ending one
                if((count1 == omArgusI.keySet().size()) && (j == omArgusI.get(s).element1.size() - 1)){
                    result.append(omArgusI.get(s).element1.get(j) + "[i], principals), sLe(" +
                            omArgusA.get(s).element1.get(j) + "[i], principals)) for i in range(n)]\n");
                }
                else {
                    result.append(omArgusI.get(s).element1.get(j) + "[i], principals), sLe(" +
                            omArgusA.get(s).element1.get(j) + "[i], principals), sLe(");
                }
            }
            count1++;
        }

        result.append("s.add(" + oname + "range" + rangeO + ")\n");
        rangeO++;

        //for object can not be hosted on client constraints
        //if(!oname.equals("user")){
            //result.append(oname + "range" + rangeO + " = [And(0 == " + Qs + "[i][n-1]) for i in range(n)]\n");
            //result.append("s.add(" + oname + "range" + rangeO + ")\n");
        //}
        //rangeO++;

        return result.toString();
    }

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
}
