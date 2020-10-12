package jsrc.x10.ast.tree.xtras.methodcall;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.op.BinaryOp;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 2:19:53 PM
 */
public class Pow extends BinaryOp {

    public Pow(Expression arg1, Expression arg2) {
        super(arg1, arg2);
    }

    @Override
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.BinaryOpVisitor v) {
        return null;
    }
}