package jsrc.matlab.ast.tree.statement;

import jsrc.matlab.ast.tree.expression.op.application.Call;
import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.ast.visitor.SVisitor;

public class CallAndMultiAssignment extends Statement {

    public Id[] ids;
    public Call call;

    public CallAndMultiAssignment(Id[] ids, Call call) {
        this.ids = ids;
        this.call = call;
    }

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }
}
