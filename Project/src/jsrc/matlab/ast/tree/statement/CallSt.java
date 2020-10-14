package jsrc.matlab.ast.tree.statement;

import jsrc.matlab.ast.tree.expression.op.application.Call;
import jsrc.matlab.ast.visitor.SVisitor;

public class CallSt extends Statement {

    public Call call;

    public CallSt(Call call) {
        this.call = call;
    }

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }
}
