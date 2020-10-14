package jsrc.matlab.astbuild.intastnodes.expressions;

import jsrc.matlab.ast.tree.expression.op.logical.And;
import jsrc.matlab.ast.tree.expression.op.logical.ElementWiseAnd;
import lesani.compiler.ast.LocInfo;
import lesani.compiler.ast.Node;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Dec 9, 2010
 * Time: 3:40:24 PM
  */
public class InElementWiseOr extends LocInfo implements Node {
    public ElementWiseAnd elementWiseAnd;

    public InElementWiseOr(ElementWiseAnd elementWiseAnd) {
        this.elementWiseAnd = elementWiseAnd;
    }
}
