package jsrc.x10.ast.tree.expression.literal;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class True extends Literal {
    private static True theInstance = new True();

    public static True instance() {
		return theInstance;
	}

	private True() {
        lexeme = "true";
	}

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.LiteralVisitor v) {
        return v.visit(this);
    }
}