package lambda_calculus.partition_package.tree.expression.literal;

import lambda_calculus.partition_package.visitor.PartitionVisitor;

public class IntLiteral extends Literal {

    public IntLiteral(String lexeme) {
        this.lexeme = lexeme;
    }

    public IntLiteral(int i) {
        this.lexeme = Integer.toString(i);
    }

    public <R> R accept(PartitionVisitor.ExpressionVisitor.LiteralVisitor<R> intLiteralVisitor){
        return intLiteralVisitor.visit(this);
    }

    @Override
    public String toString(){
        return lexeme;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return (lexeme == ((IntLiteral) o).toString());
    }
}
