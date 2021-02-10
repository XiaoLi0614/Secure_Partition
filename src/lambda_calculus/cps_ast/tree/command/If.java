package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;
import lesani.collection.option.*;


public class If extends Command {
    public Expression condition;
    public Command command1;
    public Command command2;

    public If(Expression e, Command c1, Command c2) {
        this.condition = e;
        this.command1 = c1;
        this.command2 = c2;
    }

    public <R> R accept(CPSVisitor.CommandVisitor<R> iF){
        return iF.visit(this);
    }

    @Override
    public String toString(){
        return "If (" + condition + ") then (" + command1 +") else (" + command2 + ")";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        If that = (If) o;

        if(!condition.equals(that.condition)) return false;
        else if (! command1.equals(that.command1)) return false;
        else return command2.equals(that.command2);
    }

    @Override
    public Command substitute(Var originalVar, Expression replacer){
        return new If(condition.substitute(originalVar, replacer),
                command1.substitute(originalVar, replacer),
                command2.substitute(originalVar, replacer));
    }

    @Override
    public Command substitute(Var originalVar, ThisMethod replacer){
        return new If(condition,
                command1.substitute(originalVar, replacer),
                command2.substitute(originalVar, replacer));
    }
}
