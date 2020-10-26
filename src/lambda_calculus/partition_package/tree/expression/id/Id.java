package lambda_calculus.partition_package.tree.expression.id;

import lambda_calculus.partition_package.visitor.PartitionVisitor;

public class Id extends GId {

    public Id(String name) {
        super(name);
    }

    public <R> R accept(PartitionVisitor.ExpressionVisitor.GIdVisitor<R> id){
        return id.visit(this);
    }

    @Override
    public String toString(){
        return lexeme;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return (lexeme == ((Id) o).toString());
    }
}

