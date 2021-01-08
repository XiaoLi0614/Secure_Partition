package lambda_calculus.partition_package.tree.command;

import lambda_calculus.partition_package.tree.expression.Expression;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

public class ExpSt extends Command {
    public Expression expression;

    public ExpSt(Expression expression) {
        this.expression = expression;
    }

    public <R> R accept(PartitionVisitor.CommandVisitor<R> expSt){
        return expSt.visit(this);
    }

    @Override
    public String toString(){
        return expression.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpSt that = (ExpSt) o;
        return expression.equals(that.expression);
    }

    @Override
    public int hashCode(){
        return expression.hashCode();
    }
}