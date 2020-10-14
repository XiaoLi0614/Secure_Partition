package jsrc.matlab.ast.tree.statement.xtras;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.op.application.Call;
import jsrc.matlab.ast.tree.statement.CallSt;
import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.ast.tree.statement.Statement;
import jsrc.matlab.ast.tree.expression.Id;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Tic extends Statement {

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        Id ticId = new Id("tic");
        Call ticCall = new Call(ticId, new Expression[]{});
        CallSt ticCallSt = new CallSt(ticCall);

        ticId.lineNo = ticCall.lineNo = ticCallSt.lineNo = this.lineNo;
        ticId.columnNo = ticCall.columnNo = ticCallSt.columnNo = this.columnNo;

        return v.visit(ticCallSt);
    }
}

