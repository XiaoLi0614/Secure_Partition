package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.tree.Node;
import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

public abstract class Command implements Node {
/*    public <R> R accept(CPSVisitor<R> v){
        return v.visit(this);
    }*/

    public <R> R accept(CPSVisitor<R> v){
        return v.visit(this);
    }
    public abstract <R> R accept(CPSVisitor.CommandVisitor<R> v);

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract Command substitute(Var originalVar, Expression replacer);
}
