package lambda_calculus.partition_package.tree.expression.literal;

import lambda_calculus.partition_package.tree.expression.Expression;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

public abstract class Literal implements Expression {
    public String lexeme;

    public <R> R accept(PartitionVisitor.ExpressionVisitor<R> v){
        return v.visit(this);
    }

    public abstract <R> R accept(PartitionVisitor.ExpressionVisitor.LiteralVisitor<R> v);

    public abstract String toString();

    public abstract boolean equals(Object o);
}