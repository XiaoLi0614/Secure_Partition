package lambda_calculus.source_ast.tree.expression.literal;

import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;

public abstract class Literal implements Expression {
    public String lexeme;

    public <R> R accept(SourceVisitor.ExpressionVisitor<R> v){
        return v.visit(this);
    }

    public abstract <R> R accept(SourceVisitor.ExpressionVisitor.LiteralVisitor<R> v);
}
