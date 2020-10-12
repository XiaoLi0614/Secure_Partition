package jsrc.x10.ast.tree.statement.derived;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.MethodCall;
import jsrc.x10.ast.tree.expression.id.specialids.general.PrintlnId;
import jsrc.x10.ast.tree.expression.id.specialids.general.SOut;
import jsrc.x10.ast.tree.statement.ExpSt;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 11:17:21 AM
 */
public class Println extends ExpSt {

    public Println(Expression arg) {
        super(new MethodCall(SOut.instance(), PrintlnId.instance(), new Expression[] {arg}));
    }

}
