package lambda_calculus.cps_ast.tree.command;


import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.tree.expression.id.GId;
import lambda_calculus.cps_ast.tree.expression.id.Id;
import lambda_calculus.cps_ast.visitor.BetaReduction;

public class SingleCall extends Command {
    public Var administrativeX; // this is the administrative x for the object method
    public GId methodName; ////name of the user declared method
    public Expression[] args; //input arguments of method
    public GId objectName; // the object which this method belongs to
    public Command nestedCommand; // call x = o.m(e) in c
    //todo: we need to add binding here

    public SingleCall(String oName, String mName, Expression[] args, String aX, Command nCommand) {
        this.methodName = new Id(mName);
        this.objectName = new Id(oName);
        this.args = args;
        this.nestedCommand = nCommand;
        this.administrativeX = new Var(aX);
    }

    public Object accept(BetaReduction statementVisitor) {
        return statementVisitor.visit(this);
    }
}


