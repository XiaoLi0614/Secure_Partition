package lambda_calculus.partition_package.tree;

import lambda_calculus.partition_package.tree.command.Command;
import lambda_calculus.partition_package.tree.expression.Var;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PartitionProcess {
    ArrayList<MethodDefinition> methodsList;
    HashSet<Var> freeVariables;
    Command callBackName;

    public PartitionProcess(){
        methodsList = new ArrayList<>();
        freeVariables = new HashSet<>();
    }

    //This cN can be simply a method within this class
    public PartitionProcess(ArrayList<MethodDefinition> mL, HashSet<Var> fV, Command cN){
        methodsList = mL;
        freeVariables = fV;
        callBackName = cN;
    }

    public ArrayList<MethodDefinition> getMethodDefinitions(){ return methodsList; }

    public void addMethodDefinition(MethodDefinition mD){ methodsList.add(mD); }

    public HashSet<Var> getFreeVariables(){ return freeVariables; }

    public void writeFreeVars(HashSet<Var> newFreeVars){ freeVariables = newFreeVars; }

    public Command getCallBackName(){ return callBackName; }

    public void writeCallBackName(Command cN){ callBackName = cN; }
}
