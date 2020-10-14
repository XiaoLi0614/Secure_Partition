package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.StatementVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 9:29:01 AM
 */
public class DoWhile extends Statement {

    public Statement statement;
    public Expression condition;

    public DoWhile(Statement statement, Expression condition) {
        this.statement = statement;
        this.condition = condition;
    }

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }
}
