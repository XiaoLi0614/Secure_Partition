package jsrc.matlab.ast.tree.statement;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.visitor.SVisitor;

public class Print extends Statement {

    public Expression arg;

    public Print(Expression arg) {
        this.arg = arg;
    }

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }

}
