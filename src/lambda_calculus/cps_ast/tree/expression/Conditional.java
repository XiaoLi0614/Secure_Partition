package lambda_calculus.cps_ast.tree.expression;


import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

public class Conditional implements Expression {

    public Expression condition;
    public Expression ifExp;
    public Expression elseExp;

    public Conditional(Expression condition, Expression ifExp, Expression elseExp) {
        this.condition = condition;
        this.ifExp = ifExp;
        this.elseExp = elseExp;
    }

    public <R> R accept(CPSVisitor.ExpressionVisitor<R> conditionalVisitor) {
        return conditionalVisitor.visit(this);
    }
}
