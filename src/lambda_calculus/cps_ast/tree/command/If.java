package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lesani.collection.option.*;


public class If extends Command {
    public Expression condition;
    public Command command1;
    public Command command2;

    public If(Expression e, Command c1, Command c2) {
        this.condition = e;
        this.command1 = c1;
        this.command2 = c2;
    }

    public Object accept(BetaReduction betaVisitor) {
        return betaVisitor.visit(this);
    }
}
