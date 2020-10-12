package jsrc.matlab.ast.tree.expression.op.application;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.type.DataType;

public class CallOrArrayAccess extends Op {
    public Id id;
    public Expression[] args;
    public boolean lib = false;
    public DataType origType;

    public CallOrArrayAccess(Id id, Expression[] args) {
        this.id = id;
        this.args = args;
    }

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor<R> v) {
        return v.visit(this);
    }

    @Override
    public String getName() {
        return id.name;
    }

    @Override
    public String getLexeme() {
        return id.name;
    }

    @Override
    public Expression[] getOperands() {
        return args;
    }
    public void setLib() {
        lib = true;
    }

    public boolean isLib() {
        return lib;
    }

}
