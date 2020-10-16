package lambda_calculus.source_ast.tree.expression;

import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;


public class Conditional implements Expression {

    public Expression condition;
    public Expression ifExp;
    public Expression elseExp;

    public Conditional(Expression condition, Expression ifExp, Expression elseExp) {
        this.condition = condition;
        this.ifExp = ifExp;
        this.elseExp = elseExp;
    }
    public <R> R accept(SourceVisitor.ExpressionVisitor<R> conditionalVisitor) {
        return conditionalVisitor.visit(this);
    }
}
