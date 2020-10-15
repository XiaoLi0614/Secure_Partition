package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.tree.Node;
import lambda_calculus.cps_ast.visitor.BetaReduction;

public abstract class Command implements Node {
    public abstract Object accept(BetaReduction BetaVisitor);
}
