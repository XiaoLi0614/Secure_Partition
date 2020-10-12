package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;
import jsrc.x10.ast.tree.expression.Expression;


public class ContainsExp implements Node {
    public Expression expression;

    public ContainsExp(Expression expression) {
        this.expression = expression;
    }
}