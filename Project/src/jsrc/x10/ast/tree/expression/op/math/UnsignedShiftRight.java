package jsrc.x10.ast.tree.expression.op.math;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.op.BinaryOp;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 2:19:53 PM
 */
public class UnsignedShiftRight extends BinaryOp {

    public UnsignedShiftRight(Expression operand1, Expression operand2) {
        super(operand1, operand2);
    }
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.BinaryOpVisitor v) {
        return v.visit(this);
    }
}