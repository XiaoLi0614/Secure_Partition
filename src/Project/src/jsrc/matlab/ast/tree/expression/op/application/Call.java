package jsrc.matlab.ast.tree.expression.op.application;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.type.DataType;
/*
 User: lesani, Date: Nov 2, 2009, Time: 11:33:17 AM
*/

public class Call extends Op {
    public Id name;
    public Expression[] args;
    public boolean lib = false; // Is the call on a lib function
    public DataType origType;

    public Call(Id name, Expression[] args) {
        this.name = name;
        this.args = args;
    }

    public Call(Id name, Expression[] args, boolean lib) {
    }

    public Call(Id id, Expression[] args, boolean lib, DataType origType) {
        this.name = id;
        this.args = args;
        this.lib = lib;
        this.origType = origType;
    }
    public Call(Id id, Expression[] args, boolean lib, DataType type, DataType origType) {
        this.name = id;
        this.args = args;
        this.lib = lib;
        this.type = type;
        this.origType = origType;
    }

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor<R> v) {
        return v.visit(this);
    }


    @Override
    public String getName() {
        return name.name;
    }

    @Override
    public String getLexeme() {
        return name.name;
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