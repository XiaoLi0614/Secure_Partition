package lambda_calculus.source_ast.tree.expression.literal;

import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;

public class IntLiteral extends Literal {

    public IntLiteral(String lexeme) {
        this.lexeme = lexeme;
    }

    public IntLiteral(int i) {
        this.lexeme = Integer.toString(i);
    }

    public <R> R accept(SourceVisitor.ExpressionVisitor.LiteralVisitor<R> intLiteralVisitor){
        return intLiteralVisitor.visit(this);
    }
}
