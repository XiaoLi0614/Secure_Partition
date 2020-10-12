package parsing.ast.tree;

import parsing.ast.visitor.GISVisitor;
import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.GSVisitor;
import parsing.ast.visitor.Visitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class ArrayType implements Type {
	private static ArrayType ourInstance = new ArrayType();

	public static ArrayType instance() {
		return ourInstance;
	}

	private ArrayType() {
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