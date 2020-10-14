package jsrc.x10.ast.tree.statement.derived;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.MethodCall;
import jsrc.x10.ast.tree.expression.id.specialids.general.PrintId;
import jsrc.x10.ast.tree.expression.id.specialids.general.SOut;
import jsrc.x10.ast.tree.statement.ExpSt;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 11:19:14 AM
 */
public class Print extends ExpSt {

    public Print(Expression arg) {
        super(new MethodCall(SOut.instance(), PrintId.instance(), new Expression[] {arg}));
    }

}
