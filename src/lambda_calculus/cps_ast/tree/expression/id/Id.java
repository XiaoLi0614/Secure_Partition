package lambda_calculus.cps_ast.tree.expression.id;

import lambda_calculus.cps_ast.visitor.BetaReduction;

public class Id extends GId {

    public Id(String name) {
        super(name);
    }

    @Override
    public Object accept(BetaReduction.ExpressionVisitor.GIdVisitor gIdVisitor) {
        return gIdVisitor.visit(this);
    }
}
