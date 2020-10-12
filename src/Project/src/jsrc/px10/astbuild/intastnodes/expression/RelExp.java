package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;
import jsrc.x10.ast.tree.expression.Expression;


public class RelExp implements Node {
    public Expression e;

    public RelExp(Expression e) {
        this.e = e;
    }
}
