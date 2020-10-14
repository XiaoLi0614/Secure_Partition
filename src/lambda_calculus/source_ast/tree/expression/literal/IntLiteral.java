package lambda_calculus.source_ast.tree.expression.literal;

import lambda_calculus.source_ast.visitor.CPSPrinter;

public class IntLiteral extends Literal {

    public IntLiteral(String lexeme) {
        this.lexeme = lexeme;
    }

    public IntLiteral(int i) {
        this.lexeme = Integer.toString(i);
    }

    public Object accept(CPSPrinter.ExpressionVisitor.LiteralVisitor v) {
        return v.visit(this);
    }
}
