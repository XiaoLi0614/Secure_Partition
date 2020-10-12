package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.StatementVisitor;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 5:20:37 PM
 */
public class ExpSt extends Statement {
    public Expression expression;

    public ExpSt(Expression expression) {
        this.expression = expression;
    }

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }
}
