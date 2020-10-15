package lambda_calculus.cps_ast.tree.expression;

import lambda_calculus.cps_ast.tree.expression.id.Id;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;

public class Var implements Expression{
    public Id name;

    public Var(String name) {
        this.name = new Id(name);
    }
    public Object accept(BetaReduction.ExpressionVisitor varVisitor) {
        return varVisitor.visit(this);
    }
}

