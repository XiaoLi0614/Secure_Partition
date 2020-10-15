package lambda_calculus.cps_ast.tree.expression;

import lambda_calculus.cps_ast.tree.Node;
import lambda_calculus.cps_ast.visitor.BetaReduction;

public interface Expression extends Node {
    Node continuation = null;
    Object accept(BetaReduction.ExpressionVisitor expressionVisitor);
}
