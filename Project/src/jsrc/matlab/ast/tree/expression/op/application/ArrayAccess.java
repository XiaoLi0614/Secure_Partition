package jsrc.matlab.ast.tree.expression.op.application;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.visitor.SVisitor;

public class ArrayAccess extends Op {
    public Id array;
    public Expression[] args;

    public ArrayAccess(Id array, Expression[] args) {
        this.array = array;
        this.args = args;
    }

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor<R> v) {
        return v.visit(this);
    }

    @Override
    public String getName() {
        return array.name;
    }

    @Override
    public String getLexeme() {
        return array.name;
    }

    @Override
    public Expression[] getOperands() {
        return args;
    }

}
