package jsrc.matlab.astbuild.intastnodes.expressions;

import jsrc.matlab.ast.tree.expression.op.logical.ElementWiseAnd;
import jsrc.matlab.ast.tree.expression.op.relational.RelationalOp;
import lesani.compiler.ast.LocInfo;
import lesani.compiler.ast.Node;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Dec 9, 2010
 * Time: 3:40:24 PM
  */
public class InElementWiseAnd extends LocInfo implements Node {
    public RelationalOp relationalOp;

    public InElementWiseAnd(RelationalOp relationalOp) {
        this.relationalOp = relationalOp;
    }
}
