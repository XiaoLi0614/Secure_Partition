package jsrc.x10.ast.tree.expression;

import jsrc.x10.ast.tree.expression.id.GId;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 10:02:40 AM
 */
public class FieldSelection implements Expression {
    public Expression receiver;
    public GId fieldName;

    public FieldSelection(Expression receiver, GId fieldName) {
        this.receiver = receiver;
        this.fieldName = fieldName;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }
}
