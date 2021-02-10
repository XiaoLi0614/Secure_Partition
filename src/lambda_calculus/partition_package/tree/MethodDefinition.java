package lambda_calculus.partition_package.tree;

import lambda_calculus.partition_package.tree.command.Command;
import lambda_calculus.partition_package.tree.command.SingleCall;
import lambda_calculus.partition_package.tree.expression.Expression;
import lambda_calculus.partition_package.tree.expression.Var;
import lambda_calculus.partition_package.tree.expression.id.GId;
import lambda_calculus.partition_package.tree.expression.id.Id;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

import java.util.HashSet;

public class MethodDefinition extends Object{
    //m'(x1, x2) := let x = o.m(e) in e'
    //public Var administrativeX; //x: this is the administrative x for the object method
    //public GId methodName; ////m: name of the user declared method
    public GId thisMethodName; //m': name of this method
    //public Expression[] args; //e: input arguments of method
    public HashSet<Var> freeVars; //x1, x2: free variable inside this method
    //public GId objectName; //o: the object which this method belongs to
    public Command callBackCommand; // e' : the call back command
    public SingleCall objectCall; // the only object call inside this method
    public Command body; //the body expression of this method

    public MethodDefinition(String thisM, HashSet<Var> fV, SingleCall objectCall, Command nCommand) {
        this.thisMethodName = new Id(thisM);
        this.freeVars = fV;
        //this.methodName = new Id(mName);
        //this.objectName = new Id(oName);
        //this.args = args;
        this.callBackCommand = nCommand;
        this.objectCall = objectCall;
        //this.administrativeX = aX;
    }

    public MethodDefinition(Id thisM, HashSet<Var> fV, SingleCall objectCall, Command nCommand) {
        this.thisMethodName = thisM;
        this.freeVars = fV;
        //this.methodName = mName;
        //this.objectName = oName;
        //this.args = args;
        this.callBackCommand = nCommand;
        this.objectCall = objectCall;
        //this.administrativeX = aX;
    }

    public void addBody(Command b){
        this.body = b;
    }

    @Override
    public String toString(){
        StringBuilder resultString = new StringBuilder();
        resultString.append(thisMethodName.lexeme + "(" );
        if(freeVars == null || freeVars.size() == 0){}
        else{
            for(Var v: freeVars){
                resultString.append(v + ", ");
            }
            //resultString.deleteCharAt(resultString.lastIndexOf(", "));
            resultString.delete(resultString.lastIndexOf(", "), resultString.lastIndexOf(", ") + 2);
        }
        resultString.append(") := ");

        resultString.append("let " + objectCall.administrativeX + " = " + objectCall.objectName + "." + objectCall.methodName + "(");
        if(objectCall.args == null || objectCall.args.length == 0){}
        else{
            for(Expression e: objectCall.args){
                resultString.append(e + ", ");
            }
            //resultString.deleteCharAt(resultString.lastIndexOf(", "));
            resultString.delete(resultString.lastIndexOf(", "), resultString.lastIndexOf(", ") + 2);
        }
        resultString.append(") in " + body);
        return  resultString.toString();
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodDefinition that = (MethodDefinition) o;

        if (!objectCall.objectName.equals(that.objectCall.objectName)) return false;
        else if (!objectCall.methodName.equals(that.objectCall.methodName)) return false;
        else if (!nestedCommand.equals(that.nestedCommand)) return false;
        else if ((args == null || args.length == 0)) {
            if (!(that.args == null || that.args.length == 0)) return false;
            else return true;
        }
        else return args.equals(that.args);
    }

    @Override
    public int hashCode(){}*/
}