package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;
import jsrc.x10.ast.tree.expression.Expression;


public class ArrayAccessExp implements Node {
    public Expression[] expressions;

    public ArrayAccessExp(Expression[] expressions) {
        this.expressions = expressions;
    }
}
