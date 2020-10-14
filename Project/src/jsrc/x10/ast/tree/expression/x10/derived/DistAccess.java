package jsrc.x10.ast.tree.expression.x10.derived;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.MethodCall;
import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 9:37:57 AM
 */
public class DistAccess extends MethodCall {

    public DistAccess(Expression dist, Expression[] indices) {
        super(dist, "apply", indices);
    }

    public Expression dist() {
        return receiver.apply(
            new Fun0<Expression>() {
                public Expression apply() {
                    return null;
                }
            },
            new Fun<Expression, Expression>() {
                public Expression apply(Expression receiver) {
                    return receiver;
                }
            }
        );
    }


    public Expression[] indices() {
        return args;
    }

}