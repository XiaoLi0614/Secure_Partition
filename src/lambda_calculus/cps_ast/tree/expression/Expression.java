package lambda_calculus.cps_ast.tree.expression;

import lambda_calculus.cps_ast.tree.Node;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

import java.io.Serializable;

public interface Expression extends Node{
    //Node continuation = null;
    <R> R accept(CPSVisitor.ExpressionVisitor<R> expressionVisitor);
}
