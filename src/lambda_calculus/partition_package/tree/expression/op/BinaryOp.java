package lambda_calculus.partition_package.tree.expression.op;

import lambda_calculus.partition_package.tree.expression.Expression;
import lambda_calculus.partition_package.tree.expression.Var;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

public abstract class BinaryOp implements Expression {

    public Expression operand1;
    public Expression operand2;
    public String operatorText;

    protected BinaryOp(String operatorText, Expression operand1, Expression operand2) {
        this.operatorText = operatorText;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public <R> R accept(PartitionVisitor.ExpressionVisitor<R> v){
        return v.visit(this);
    }

    public abstract <R> R accept(PartitionVisitor.ExpressionVisitor.BinaryOpVisitor<R> v);

    public abstract String toString();

    public abstract boolean equals(Object o);
}