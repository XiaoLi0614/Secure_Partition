package lambda_calculus.partition_package.tree.expression.id;

import lambda_calculus.partition_package.tree.expression.Expression;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

public abstract class GId implements Expression {
    public String lexeme;

    protected GId(String lexeme) {
        this.lexeme = lexeme;
    }

    public <R> R accept(PartitionVisitor.ExpressionVisitor<R> v){
        return v.visit(this);
    }

    public abstract <R> R accept(PartitionVisitor.ExpressionVisitor.GIdVisitor<R> v);

    public abstract String toString();

    public abstract boolean equals(Object o);
}