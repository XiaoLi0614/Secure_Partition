package jsrc.x10.ast.tree.expression.op;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 4:02:01 PM
 */
public abstract class UnaryOp implements Expression {

    public Expression operand;

    protected UnaryOp(Expression operand) {
        this.operand = operand;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }

    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.UnaryOpVisitor unaryOpVisitor);
}
