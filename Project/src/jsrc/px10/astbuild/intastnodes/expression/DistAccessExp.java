package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;
import jsrc.x10.ast.tree.expression.Expression;


public class DistAccessExp implements Node {
    public Expression[] expressions;

    public DistAccessExp(Expression[] expressions) {
        this.expressions = expressions;
    }
}