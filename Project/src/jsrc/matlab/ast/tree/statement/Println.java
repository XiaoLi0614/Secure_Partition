package jsrc.matlab.ast.tree.statement;

import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.ast.tree.expression.Expression;

public class Println extends Statement {

    public Expression arg;

    public Println(Expression arg) {
        this.arg = arg;
    }

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }

}
