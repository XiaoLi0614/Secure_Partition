package jsrc.x10.ast.tree.expression.literal;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class This extends Literal {
    private static This theInstance = new This();

    public static This instance() {
		return theInstance;
	}

	private This() {
        lexeme = "this";
	}
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.LiteralVisitor v) {
        return v.visit(this);
    }
}