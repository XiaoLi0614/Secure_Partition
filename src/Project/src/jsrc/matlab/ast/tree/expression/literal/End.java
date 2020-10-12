package jsrc.matlab.ast.tree.expression.literal;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.literal.Literal;
import jsrc.matlab.ast.visitor.SVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class End extends Literal {
	private static End theInstance = new End();

    public static End instance() {
		return theInstance;
	}

	public End() {
        super("end");
    }

    public <R> R accept(SVisitor.ExpressionVisitor.LiteralVisitor<R> v) {
        return v.visit(this);
    }
}