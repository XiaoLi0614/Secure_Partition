package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.visitor.StatementVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 11:30:17 AM
 */
public class ValueReturn extends Return {
    public Expression arg;

    public ValueReturn(Expression arg) {
        this.arg = arg;
    }

    public ValueReturn(String outputName) {
        this.arg = new Id(outputName);
    }

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }
}
