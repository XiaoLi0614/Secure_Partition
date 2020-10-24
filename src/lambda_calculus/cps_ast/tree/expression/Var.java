package lambda_calculus.cps_ast.tree.expression;

import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.cps_ast.tree.command.ExpSt;
import lambda_calculus.cps_ast.tree.expression.id.Id;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;

public class Var implements Expression{
    public Id name;

    public Var(String name) {
        this.name = new Id(name);
    }

    public Var(Id n) {
        this.name = n;
    }

    public <R> R accept(CPSVisitor.ExpressionVisitor<R> varVisitor) {
        return varVisitor.visit(this);
    }

    @Override
    public String toString(){
        return name.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return name.equals(((Var) o).name);
    }

    @Override
    public Expression substitute(Var originalVar, Expression replacer){
        if(this.equals(originalVar)){
            return replacer;
        }
        else return this;
    }
}

