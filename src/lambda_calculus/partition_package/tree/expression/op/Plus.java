package lambda_calculus.partition_package.tree.expression.op;

import lambda_calculus.partition_package.tree.expression.Expression;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

public class Plus extends BinaryOp {

    public Plus(Expression operand1, Expression operand2) {
        super("+", operand1, operand2);
    }
    public <R> R accept(PartitionVisitor.ExpressionVisitor.BinaryOpVisitor<R> binaryOpVisitor){
        return binaryOpVisitor.visit(this);
    }

    @Override
    public String toString(){
        return "(" + operand1 + " " + operatorText + " " + operand2 + ")";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plus that = (Plus) o;
        if(!operand1.equals(that.operand1)) return false;
        else if(!operand2.equals(that.operand2)) return false;
        else return (operatorText == that.operatorText);
    }
}
