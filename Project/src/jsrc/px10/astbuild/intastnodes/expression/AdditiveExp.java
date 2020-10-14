package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;
import jsrc.x10.ast.tree.expression.Expression;


public class AdditiveExp implements Node {
    public Expression e;
    public AdditiveExp(Expression e) {
        this.e = e;
    }
}
