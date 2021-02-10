package lambda_calculus.cps_ast.tree.command;


import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.tree.expression.id.GId;
import lambda_calculus.cps_ast.tree.expression.id.Id;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

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

    public <R> R accept(CPSVisitor.CommandVisitor<R> singleCall){
        return singleCall.visit(this);
    }

    @Override
    public String toString(){
        StringBuilder resultString = new StringBuilder();
        resultString.append("call " + administrativeX + " = " + objectName +"." + methodName + "(");
        if(args == null || args.length == 0){}
        else{
            for(Expression e: args){
                resultString.append(e + ", ");
            }
            resultString.delete(resultString.lastIndexOf(", "), resultString.lastIndexOf(", ") + 2);
        }
        resultString.append(") in " + nestedCommand);
        return  resultString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleCall that = (SingleCall) o;

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

    @Override
    public Command substitute(Var originalVar, Expression replacer){
        Expression[] resultVar = args;
        if(args == null || args.length == 0){}
        else {
            resultVar = new Expression[args.length];
            for(int i = 0; i < args.length; i++){
                resultVar[i] = (Expression) args[i].substitute(originalVar, replacer);
            }
        }
        return new SingleCall((Id) objectName,
                (Id) methodName,
                resultVar,
                administrativeX,
                nestedCommand.substitute(originalVar, replacer));
    }

    @Override
    public Command substitute(Var originalVar, ThisMethod replacer){
        Expression[] resultVar = args;
        if(args == null || args.length == 0){}
        else {
            resultVar = new Expression[args.length];
            for(int i = 0; i < args.length; i++){
                resultVar[i] = (Expression) args[i].substitute(originalVar, replacer);
            }
        }
        return new SingleCall((Id) objectName,
                (Id) methodName,
                resultVar,
                administrativeX,
                nestedCommand.substitute(originalVar, replacer));
    }
}


