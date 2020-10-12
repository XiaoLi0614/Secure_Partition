package jsrc.matlab.ast.tree.expression.op.logical;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.converttox10.ASTConverter;

public abstract class LogicalOp extends Op {

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor<R> v) {
        return v.visit(this);
    }

    public abstract <R> R accept(SVisitor.ExpressionVisitor.OpVisitor.LogicalOpVisitor<R> v);

}
