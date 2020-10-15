package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.source_ast.tree.expression.Expression;

public class ExpSt extends Command {
    public Expression expression;

    public ExpSt(Expression expression) {
        this.expression = expression;
    }

    public Object accept(BetaReduction betaVisitor) {
        return betaVisitor.visit(this);
    }
}
