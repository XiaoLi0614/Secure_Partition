package lambda_calculus.source_ast.tree.expression;

import lambda_calculus.source_ast.tree.expression.id.Id;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;

public class Var implements Expression{
    //public NonVoidType type;
    public Id name;

    public Var(String name) {
        //this.type = type;
        this.name = new Id(name);
    }
    public <R> R accept(SourceVisitor.ExpressionVisitor<R> varVisitor) {
        return varVisitor.visit(this);
    }
}

