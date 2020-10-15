package lambda_calculus.cps_ast.tree.expression.id;

import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.visitor.BetaReduction;

public abstract class GId implements Expression {
    public String lexeme;

    protected GId(String lexeme) {
        this.lexeme = lexeme;
    }

    public abstract Object accept(BetaReduction.ExpressionVisitor.GIdVisitor gIdVisitor);

    public Object accept(BetaReduction.ExpressionVisitor v) {
        return v.visit(this);
    }

}
