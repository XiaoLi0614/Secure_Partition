package jsrc.x10.ast.tree.expression.x10.derived;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.MethodCall;

public class ArrayUpdate extends MethodCall {
    public Expression array;
    public Expression[] indices;
    public Expression right;


    public ArrayUpdate(Expression array, Expression[] indices, Expression right) {
        Expression[] args = new Expression[indices.length];
        System.arraycopy(indices, 0, args, 0, indices.length);
        args[args.length - 1] = right;
        init(array, "update", args);

        this.array = array;
        this.indices = indices;
        this.right = right;
    }

}
