package jsrc.matlab.ast.tree.expression.op.xtras;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.op.application.Call;
import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.ast.visitor.SVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Toc extends Expression {

    public <R> R accept(SVisitor.ExpressionVisitor<R> v) {
        Id tocId = new Id("toc");
        Call tocCall = new Call(tocId, new Expression[]{});

        tocId.lineNo = tocCall.lineNo = this.lineNo;
        tocId.columnNo = tocCall.columnNo = this.columnNo;

        return v.visit(tocCall);
    }
}

