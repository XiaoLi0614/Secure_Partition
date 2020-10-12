package jsrc.x10.ast.tree.expression.op.math;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.op.UnaryOp;
import jsrc.x10.ast.visitor.DFSVisitor;

public class PreIncrement extends UnaryOp {
    public PreIncrement(Expression arg) {
        super(arg);
    }
        public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.UnaryOpVisitor v) {
        return v.visit(this);
    }

}