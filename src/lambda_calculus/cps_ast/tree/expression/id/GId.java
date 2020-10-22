package lambda_calculus.cps_ast.tree.expression.id;

import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

public abstract class GId implements Expression {
    public String lexeme;

    protected GId(String lexeme) {
        this.lexeme = lexeme;
    }

    public <R> R accept(CPSVisitor.ExpressionVisitor<R> v){
        return v.visit(this);
    }

    public abstract <R> R accept(CPSVisitor.ExpressionVisitor.GIdVisitor<R> v);

    public abstract String toString();

    public abstract boolean equals(Object o);
}
