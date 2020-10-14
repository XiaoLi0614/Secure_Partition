package lambda_calculus.source_ast.tree.expression;

import lambda_calculus.source_ast.visitor.CPSPrinter;


public class Conditional implements Expression {

    public Expression condition;
    public Expression ifExp;
    public Expression elseExp;

    public Conditional(Expression condition, Expression ifExp, Expression elseExp) {
        this.condition = condition;
        this.ifExp = ifExp;
        this.elseExp = elseExp;
    }

    public Object accept(CPSPrinter.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }
}
