package jsrc.x10.ast.tree.expression.literal;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class False extends Literal {

    private static False theInstance = new False();

    public static False instance() {
		return theInstance;
	}

	private False() {
        lexeme = "false";
	}

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.LiteralVisitor v) {
        return v.visit(this);
    }
}