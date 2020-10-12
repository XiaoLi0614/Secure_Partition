package jsrc.x10.ast.tree.expression.op.math;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.op.UnaryOp;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 3:57:38 PM
 */
public class Complement extends UnaryOp {

    public Complement(Expression arg) {
        super(arg);
    }
        public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.UnaryOpVisitor v) {
        return v.visit(this);
    }
}