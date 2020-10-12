package parsing.ast.tree;

import parsing.ast.visitor.GISVisitor;
import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.GSVisitor;
import parsing.ast.visitor.Visitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class FalseLiteral implements Expression {
	private static FalseLiteral ourInstance = new FalseLiteral();

	public static FalseLiteral getInstance() {
		return ourInstance;
	}

	private FalseLiteral() {
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

