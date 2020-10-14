package jsrc.matlab.ast.tree.expression.op.relational;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.visitor.SVisitor;


public abstract class RelationalOp extends Op {
    public Expression operand1;
    public Expression operand2;

    protected RelationalOp(Expression operand1, Expression operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor<R> v) {
        return v.visit(this);
    }

    public abstract <R> R accept(SVisitor.ExpressionVisitor.OpVisitor.RelationalVisitor<R> v);

    @Override
    public Expression[] getOperands() {
        return new Expression[]{operand1, operand2};
    }

}
