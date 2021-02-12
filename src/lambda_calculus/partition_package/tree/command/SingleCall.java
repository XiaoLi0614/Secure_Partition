package lambda_calculus.partition_package.tree.command;

import lambda_calculus.partition_package.tree.expression.Expression;
import lambda_calculus.partition_package.tree.expression.Var;
import lambda_calculus.partition_package.tree.expression.id.GId;
import lambda_calculus.partition_package.tree.expression.id.Id;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

import java.util.Optional;

public class SingleCall extends Command {
    public Var administrativeX; // this is the administrative x for the object method
    public GId methodName; ////name of the user declared method
    public Expression[] args; //input arguments of method
    public GId objectName; // the object which this method belongs to
    public Command nestedCommand; // call x = o.m(e) in c
    //todo: we need to add binding here

    public SingleCall(String oName, String mName, Expression[] args, Var aX, Command nCommand) {
        this.methodName = new Id(mName);
        this.objectName = new Id(oName);
        this.args = args;
        this.nestedCommand = nCommand;
        this.administrativeX = aX;
    }

    public SingleCall(Id oName, Id mName, Expression[] args, Var aX, Command nCommand) {
        this.methodName = mName;
        this.objectName = oName;
        this.args = args;
        this.nestedCommand = nCommand;
        this.administrativeX = aX;
    }

    //for callback functions
    public SingleCall(Id mName, Expression[] args) {
        this.methodName = mName;
        this.objectName = new Id("this");
        this.args = args;
    }

    public SingleCall(String mName, Expression[] args) {
        this.methodName = new Id(mName);
        this.objectName = new Id("this");
        this.args = args;
    }

    public <R> R accept(PartitionVisitor.CommandVisitor<R> singleCall){
        return singleCall.visit(this);
    }

    @Override
    public String toString(){
        StringBuilder resultString = new StringBuilder();
        if(this.objectName.toString() == "this"){
            resultString.append( objectName +"." + methodName + "(");
            if(args == null || args.length == 0){}
            else{
                for(Expression e: args){
                    resultString.append(e + ", ");
                }
                resultString.delete(resultString.lastIndexOf(", "), resultString.lastIndexOf(", ") + 2);
            }
            resultString.append(")");
        }
        else {
            resultString.append("let " + administrativeX + " = " + objectName +"." + methodName + "(");
            if(args == null || args.length == 0){}
            else{
                for(Expression e: args){
                    resultString.append(e + ", ");
                }
                resultString.delete(resultString.lastIndexOf(", "), resultString.lastIndexOf(", ") + 2);
            }
            resultString.append(") in " + nestedCommand);
        }
        return  resultString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleCall that = (SingleCall) o;
        if(that.objectName.toString() == "this"){
            if(methodName.toString() == "ret" && that.methodName.toString() == "ret"){return true;}
            else if (!objectName.equals(that.objectName)) return false;
            else if (!methodName.equals(that.methodName)) return false;
            else if ((args == null || args.length == 0)) {
                if (!(that.args == null || that.args.length == 0)) return false;
                else return true;
            }
            else return args.equals(that.args);
        }
        else {
            if (!administrativeX.equals(that.administrativeX)) return false;
            else if (!objectName.equals(that.objectName)) return false;
            else if (!methodName.equals(that.methodName)) return false;
            else if (!nestedCommand.equals(that.nestedCommand)) return false;
            else if ((args == null || args.length == 0)) {
                if (!(that.args == null || that.args.length == 0)) return false;
                else return true;
            }
            else return args.equals(that.args);
        }
    }

    @Override
    public int hashCode(){
        return methodName.hashCode() + objectName.hashCode();
    }
}