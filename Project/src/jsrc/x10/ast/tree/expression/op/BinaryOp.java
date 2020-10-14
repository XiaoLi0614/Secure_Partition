package jsrc.x10.ast.tree.expression.op;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 2:20:20 PM
 */
public abstract class BinaryOp implements Expression {

    public Expression operand1;
    public Expression operand2;

//    public final String operatorText;

    protected BinaryOp(/*String operatorText,*/ Expression operand1, Expression operand2) {
//        this.operatorText = operatorText;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }

    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.BinaryOpVisitor binaryOpVisitor);
}
