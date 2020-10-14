package jsrc.x10.ast.tree.xtras.methodcall;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.op.UnaryOp;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 4:00:44 PM
 */
public class Exp extends UnaryOp {

    public Exp(Expression arg) {
        super(arg);
    }

    @Override
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.UnaryOpVisitor v) {
        return null;
    }

}
