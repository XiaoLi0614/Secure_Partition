package lambda_calculus.partition_package.tree.expression;

import lambda_calculus.partition_package.tree.expression.id.Id;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

public class Var implements Expression{
    public Id name;

    public Var(String name) {
        this.name = new Id(name);
    }

    public Var(Id n) {
        this.name = n;
    }

    public <R> R accept(PartitionVisitor.ExpressionVisitor<R> varVisitor) {
        return varVisitor.visit(this);
    }

    @Override
    public String toString(){
        return name.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return name.equals(((Var) o).name);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }
}
