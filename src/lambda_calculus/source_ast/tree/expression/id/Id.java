package lambda_calculus.source_ast.tree.expression.id;

import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;

public class Id extends GId {

    public Id(String name) {
        super(name);
    }

    public <R> R accept(SourceVisitor.ExpressionVisitor.GIdVisitor<R> id){
        return id.visit(this);
    }
}
