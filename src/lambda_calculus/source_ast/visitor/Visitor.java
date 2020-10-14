package lambda_calculus.source_ast.visitor;

import lambda_calculus.source_ast.tree.expression.Expression;

public interface Visitor<R> {
    R visit(Expression expression);
}
