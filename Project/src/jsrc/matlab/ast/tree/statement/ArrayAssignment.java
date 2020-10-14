package jsrc.matlab.ast.tree.statement;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.ast.visitor.SVisitor;

public class ArrayAssignment extends Statement {
    public Id array;
    public Expression[] indices;
    public Expression right;

    public ArrayAssignment(Id array, Expression[] indices, Expression right) {
        this.array = array;
        this.indices = indices;
        this.right = right;
    }

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }
}
