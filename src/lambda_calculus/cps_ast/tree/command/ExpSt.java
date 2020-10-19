package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

public class ExpSt extends Command {
    public Expression expression;

    public ExpSt(Expression expression) {
        this.expression = expression;
    }

    public <R> R accept(CPSVisitor.CommandVisitor<R> expSt){
        return expSt.visit(this);
    }
}
