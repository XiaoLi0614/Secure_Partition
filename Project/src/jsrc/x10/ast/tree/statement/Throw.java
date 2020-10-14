package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.StatementVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 12:38:39 PM
 */
public class Throw extends Statement {
    public Expression arg;

    public Throw(Expression arg) {
        this.arg = arg;
    }

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }
}
