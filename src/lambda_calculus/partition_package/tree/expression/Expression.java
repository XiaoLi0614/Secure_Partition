package lambda_calculus.partition_package.tree.expression;

import lambda_calculus.partition_package.tree.Node;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

public interface Expression extends Node {
    <R> R accept(PartitionVisitor.ExpressionVisitor<R> expressionVisitor);
}
