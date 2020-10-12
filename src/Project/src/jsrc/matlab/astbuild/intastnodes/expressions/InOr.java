package jsrc.matlab.astbuild.intastnodes.expressions;

import jsrc.matlab.ast.tree.expression.op.logical.And;
import lesani.compiler.ast.LocInfo;
import lesani.compiler.ast.Node;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Dec 9, 2010
 * Time: 3:40:24 PM
  */
public class InOr extends LocInfo implements Node {
    public And and;

    public InOr(And and) {
        this.and = and;
    }
}
