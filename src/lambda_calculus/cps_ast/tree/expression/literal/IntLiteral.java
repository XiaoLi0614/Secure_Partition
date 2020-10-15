package lambda_calculus.cps_ast.tree.expression.literal;

import lambda_calculus.cps_ast.tree.expression.literal.Literal;
import lambda_calculus.cps_ast.visitor.BetaReduction;

public class IntLiteral extends Literal {

    public IntLiteral(String lexeme) {
        this.lexeme = lexeme;
    }

    public IntLiteral(int i) {
        this.lexeme = Integer.toString(i);
    }

    public Object accept(BetaReduction.ExpressionVisitor.LiteralVisitor v) {
        return v.visit(this);
    }
}
