package jsrc.matlab.ast.tree.expression.op.math;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.visitor.SVisitor;

public abstract class MathOp extends Op {

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor<R> v) {
        return v.visit(this);
    }

    public abstract <R> R accept(SVisitor.ExpressionVisitor.OpVisitor.MathOpVisitor<R> v);
}

