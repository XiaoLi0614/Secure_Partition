package lambda_calculus.source_ast.tree.expression.id;

import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;

public abstract class GId implements Expression {
    public String lexeme;

    protected GId(String lexeme) {
        this.lexeme = lexeme;
    }

    public abstract <R> R accept(SourceVisitor.ExpressionVisitor.GIdVisitor<R> v);
}
