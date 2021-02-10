package lambda_calculus.cps_ast.tree.expression;


import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.cps_ast.tree.command.If;
import lambda_calculus.cps_ast.tree.command.ThisMethod;
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

    @Override
    public String toString(){
        return "If (" + condition + ") then (" + ifExp +") else (" + elseExp + ")";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conditional that = (Conditional) o;

        if(!condition.equals(that.condition)) return false;
        else if (! ifExp.equals(that.ifExp)) return false;
        else return elseExp.equals(that.elseExp);
    }

    @Override
    public Expression substitute(Var originalVar, Expression replacer){
        return new Conditional(this.condition.substitute(originalVar, replacer),
                this.ifExp.substitute(originalVar, replacer),
                this.elseExp.substitute(originalVar, replacer));
    }

    @Override
    public Command substitute(Var originalVar, ThisMethod replacer){
        //todo: this condition may need to be dispatched
        //currently in the if condition, we can not have this method
        return new If(this.condition,
                this.ifExp.substitute(originalVar, replacer),
                this.elseExp.substitute(originalVar, replacer));
    }
}
