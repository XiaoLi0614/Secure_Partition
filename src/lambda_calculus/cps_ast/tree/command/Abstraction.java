package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.source_ast.tree.expression.Expression;

public class Abstraction extends Command {
    public Var[] lambdas;
    public Command body;
    //todo: we need to add new variable for lambda here

    public Abstraction(Var[] lambdas, Command body) {
        this.lambdas = lambdas;
        this.body = body;
    }

    public Object accept(BetaReduction betaVisitor) {
        return betaVisitor.visit(this);
    }
}
