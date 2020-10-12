package jsrc.matlab.ast.tree.expression.literal;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.literal.Literal;
import jsrc.matlab.ast.visitor.SVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Colon extends Literal {
//	private static Colon theInstance = new Colon();
//
//    public static Colon instance() {
//		return theInstance;
//	}
//
//	private Colon() {
//	}

	public Colon() {
        super(":");
    }

    public <R> R accept(SVisitor.ExpressionVisitor.LiteralVisitor<R> v) {
        return v.visit(this);
    }
}

