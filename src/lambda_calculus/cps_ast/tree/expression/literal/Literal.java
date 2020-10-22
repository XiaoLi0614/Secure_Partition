package lambda_calculus.cps_ast.tree.expression.literal;

import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

public abstract class Literal implements Expression {
    public String lexeme;

    public <R> R accept(CPSVisitor.ExpressionVisitor<R> v){
        return v.visit(this);
    }

    public abstract <R> R accept(CPSVisitor.ExpressionVisitor.LiteralVisitor<R> v);

    public abstract String toString();

    public abstract boolean equals(Object o);
}
