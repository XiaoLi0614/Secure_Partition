package lambda_calculus.cps_ast.tree.expression.id;

import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

public class Id extends GId {

    public Id(String name) {
        super(name);
    }

    public <R> R accept(CPSVisitor.ExpressionVisitor.GIdVisitor<R> id){
        return id.visit(this);
    }
}
