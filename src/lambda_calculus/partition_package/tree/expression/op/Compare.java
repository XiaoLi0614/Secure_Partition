package lambda_calculus.partition_package.tree.expression.op;

import lambda_calculus.partition_package.tree.expression.Expression;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

public class Compare extends BinaryOp{
    public Compare(String operator, Expression operand1, Expression operand2) {
        super(operator, operand1, operand2);
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

        Compare that = (Compare) o;
        if(!operand1.equals(that.operand1)) return false;
        else if(!operand2.equals(that.operand2)) return false;
        else return (operatorText == that.operatorText);
    }

    @Override
    public int hashCode(){
        return operand1.hashCode() + operand2.hashCode();
    }
}
