package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;
import jsrc.x10.ast.tree.expression.Expression;


public class MultiplicativeExp implements Node {
    public Expression e;
    public MultiplicativeExp(Expression e) {
        this.e = e;
    }
}