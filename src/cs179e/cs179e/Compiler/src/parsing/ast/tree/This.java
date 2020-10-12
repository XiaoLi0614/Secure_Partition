package parsing.ast.tree;

import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.Visitor;
import parsing.ast.visitor.GISVisitor;
import parsing.ast.visitor.GSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class This implements Expression {
	private static This ourInstance = new This();

	public static This getInstance() {
		return ourInstance;
	}

	private This() {
	}


	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public <R, A> R accept(GISVisitor<R, A> visitor, A inheritedVar) {
		return visitor.visit(this, inheritedVar);
	}

	public <A> void accept(GIVisitor<A> visitor, A inheritedVar) {
		visitor.visit(this, inheritedVar);
	}

	public <R> R accept(GSVisitor<R> visitor) {
		return visitor.visit(this);
	}
}

