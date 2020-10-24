package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.tree.expression.Var;
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

    @Override
    public String toString(){
        return expression.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpSt that = (ExpSt) o;
        return expression.equals(that.expression);
    }

    @Override
    public Command substitute(Var originalVar, Expression replacer) {
        return new ExpSt(expression.substitute(originalVar, replacer));
    }
}
