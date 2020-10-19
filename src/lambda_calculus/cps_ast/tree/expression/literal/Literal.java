package lambda_calculus.cps_ast.tree.expression.literal;

import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.tree.expression.Expression;

public abstract class Literal implements Expression {
    public String lexeme;

    public Object accept(BetaReduction.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }

    public abstract Object accept(BetaReduction.ExpressionVisitor.LiteralVisitor literalVisitor);
}
