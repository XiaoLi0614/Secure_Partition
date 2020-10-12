package parsing.ast.tree;

import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.GSVisitor;
import parsing.ast.visitor.Visitor;
import parsing.ast.visitor.GISVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class ArrayLookup implements Expression {
	public Expression array;
	public Expression index;

	public ArrayLookup(Expression array, Expression index) {
		this.array = array;
		this.index = index;
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
