package jsrc.x10.ast.tree.statement.derived;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.MethodCall;
import jsrc.x10.ast.tree.expression.id.specialids.general.PrintId;
import jsrc.x10.ast.tree.expression.id.specialids.general.SErr;
import jsrc.x10.ast.tree.statement.ExpSt;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 11:21:08 AM
 */
public class PrintError extends ExpSt {

    public PrintError(Expression arg) {
        super(new MethodCall(SErr.instance(), PrintId.instance(), new Expression[] {arg}));
    }

}
