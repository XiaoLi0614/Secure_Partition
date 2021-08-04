package lambda_calculus.partition_package.tree.command;

import lambda_calculus.partition_package.tree.Node;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

public abstract class Command implements Node {

    public <R> R accept(PartitionVisitor<R> v){
        return v.visit(this);
    }
    public abstract <R> R accept(PartitionVisitor.CommandVisitor<R> v);

    public abstract String toString();

    public abstract String toString(String entranceMethod);

    public abstract boolean equals(Object o);

    public abstract int hashCode();
}

