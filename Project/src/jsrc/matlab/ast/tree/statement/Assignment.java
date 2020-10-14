package jsrc.matlab.ast.tree.statement;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.ast.visitor.SVisitor;

/*
 User: lesani, Date: Nov 2, 2009, Time: 1:50:45 PM
*/

public class Assignment extends Statement {

    public Id id;
    public Expression right;

    public Assignment(Id id, Expression right) {
        this.id = id;
        this.right = right;
    }

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }
}