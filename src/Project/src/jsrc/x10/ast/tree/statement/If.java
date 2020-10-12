package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.StatementVisitor;
import lesani.collection.option.*;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 9:46:07 AM
 */
public class If extends Statement {
    public Expression condition;
    public Statement ifStatement;
    public Option<Statement> elseStatement = None.instance();

    public If(Expression expression, Statement ifStatement, Statement elseStatement) {
        this.condition = expression;
        this.ifStatement = ifStatement;
        this.elseStatement = new Some<Statement>(elseStatement);
    }

    public If(Expression condition, Statement ifStatement) {
        this.condition = condition;
        this.ifStatement = ifStatement;
    }

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }
}
