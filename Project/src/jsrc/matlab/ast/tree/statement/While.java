package jsrc.matlab.ast.tree.statement;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.visitor.SVisitor;

public class While extends Statement {
    public Expression condition;
    public Statement statement;

    public While(Expression condition, Block statement) {
        this.condition = condition;
        this.statement = statement;
    }

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }
}