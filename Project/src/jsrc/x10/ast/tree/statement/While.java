package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.StatementVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 12:42:01 PM
 */
public class While extends Statement {

    public Expression condition;
    public Statement statement;

    public While(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }
}
