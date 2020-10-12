package jsrc.matlab.ast.tree.statement;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.ast.visitor.SVisitor;

public class For extends Statement {
    public Id id;
    public Expression range;
    public Block block;

    public For(Id id, Expression range, Block block) {
        this.id = id;
        this.range = range;
        this.block = block;
    }

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }
}
