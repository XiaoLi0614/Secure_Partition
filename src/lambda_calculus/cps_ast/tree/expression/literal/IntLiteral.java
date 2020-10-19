package lambda_calculus.cps_ast.tree.expression.literal;

import lambda_calculus.cps_ast.tree.expression.literal.Literal;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

public class IntLiteral extends Literal {

    public IntLiteral(String lexeme) {
        this.lexeme = lexeme;
    }

    public IntLiteral(int i) {
        this.lexeme = Integer.toString(i);
    }

    public <R> R accept(CPSVisitor.ExpressionVisitor.LiteralVisitor<R> intLiteralVisitor){
        return intLiteralVisitor.visit(this);
    }
}
