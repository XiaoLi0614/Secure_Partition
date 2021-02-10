package lambda_calculus.cps_ast.tree.expression.id;

import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.cps_ast.tree.command.ExpSt;
import lambda_calculus.cps_ast.tree.command.ThisMethod;
import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

public class Id extends GId {

    public Id(String name) {
        super(name);
    }

    public <R> R accept(CPSVisitor.ExpressionVisitor.GIdVisitor<R> id){
        return id.visit(this);
    }

    @Override
    public String toString(){
        return lexeme;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return (lexeme == ((Id) o).toString());
    }

    @Override
    public Expression substitute(Var originalVar, Expression replacer){
        return this;
    }

    @Override
    public Command substitute(Var originalVar, ThisMethod replacer){
        return new ExpSt(this);
    }
}
