package lambda_calculus.partition_package.visitor;

import java.util.ArrayList;
import java.util.HashMap;

public class MethodInfo {
    String mname; //the name for the method
    String host; //host principals
    String qc; //communication quorum

    String mcontextC; //the context confidentiality for method
    String mcontextI; //the context integrity for method
    String mcontextA; //the context availability for method

    //ArrayList<Boolean> mresultC; //the result confidentiality for method
    //ArrayList<ArrayList<Integer>> mresultI; //the result integrity for method
    //ArrayList<ArrayList<Integer>> mresultA; //the cresult availability for method

    ArrayList<String> arguC; //the confidentiality for method arguments
    ArrayList<String> arguI; //the integrity for method arguments
    ArrayList<String> arguA; //the availability for method arguments

    int rangeC; //the count for the range constraints

    public MethodInfo(String mName, int argNum, ArrayList<String> methodArgNames){
        mname = mName;
        host = mname + "H";
        qc = mname + "Q";
        mcontextC = mname + "conxtC";
        mcontextI = mname + "conxtI";
        mcontextA = mname + "conxtA";
        arguC = new ArrayList<>();
        arguI = new ArrayList<>();
        arguA = new ArrayList<>();
        //automatically generate bot argument for method
        if (argNum == 0){
            arguC.add(mname + "botC");
            arguI.add(mname + "botI");
            arguA.add(mname + "botA");
        }
        else {
            for(int i = 0; i < argNum; i++){
                arguC.add(mname + methodArgNames.get(i) + "C");
                arguI.add(mname + methodArgNames.get(i) + "I");
                arguA.add(mname + methodArgNames.get(i) + "A");
            }
        }
        rangeC = 0;
        return;
    }

    public String initMethod(){
        StringBuilder result = new StringBuilder();

        //declare variables
        result.append(host + " = [ Int(\'" + host + "_%s\' % i) for i in range(n) ] \n");
        result.append(qc + " = [ [ Int(\'" + qc + "_%s_%s\' % (i, j)) for j in range(n) ] for i in range(n) ]\n");
        result.append(mcontextC + " = [ Bool(\'" + mcontextC + "_%s\' % i) for i in range(n) ]\n");
        result.append(mcontextI + " = [ [ Int(\'" + mcontextI + "_%s_%s\' % (i, j)) for j in range(n) ] for i in range(n) ]\n");
        result.append(mcontextA + " = [ [ Int(\'" + mcontextA + "_%s_%s\' % (i, j)) for j in range(n) ] for i in range(n) ]\n");
        for(int i = 0; i < arguC.size(); i++){
            result.append(arguC.get(i) + " = [ Bool(\'" + arguC.get(i) + "_%s\' % i) for i in range(n) ]\n");
            result.append(arguI.get(i) + " = [ [ Int(\'" + arguI.get(i) + "_%s_%s\' % (i, j)) for j in range(n) ] for i in range(n) ]\n");
            result.append(arguA.get(i) + " = [ [ Int(\'" + arguA.get(i) + "_%s_%s\' % (i, j)) for j in range(n) ] for i in range(n) ]\n");
        }
        return result.toString();
    }

    //range constraints for method context, arguments and communication quorums
    public String mRangeCons(){
        StringBuilder result = new StringBuilder();

        //for the >= 0 constraints
        result.append(mname + "range" + rangeC + " = [ And(0 <= " + mcontextI +
                "[i][j], 0 <= " + mcontextA + "[i][j], 0 <= " + qc + "[i][j], 0 <= ");
        for(int i = 0; i < arguI.size(); i++){
            if(i == arguI.size() - 1){
                result.append(arguI.get(i) + "[i][j], 0 <= " + arguA.get(i) + "[i][j]) for i in range(n) for j in range(n) ]\n");
            }
            else{
                result.append(arguI.get(i) + "[i][j], 0 <= " + arguA.get(i) + "[i][j], 0 <= ");
            }
        }
        result.append("s.add(" + mname + "range" + rangeC + ")\n");


        //for the <= principals constraints
        rangeC ++;
        result.append(mname + "range" + rangeC + " = [And(sLe(" + mcontextI +
                "[i], principals), sLe(" + mcontextA + "[i], principals), sLe(" + qc
                + "[i], principals), sLe(");
        for(int j = 0; j < arguI.size(); j++){
            if(j == arguI.size()-1){
                result.append(arguI.get(j) + "[i], principals), sLe(" + arguA.get(j)+
                        "[i], principals)) for i in range(n)]\n");
            }
            else {
                result.append(arguI.get(j) + "[i], principals), sLe(" +
                        arguA.get(j) + "[i], principals), sLe(");
            }
        }

        result.append("s.add(" + mname + "range" + rangeC + ")\n");
        rangeC++;

        return result.toString();
    }
}
