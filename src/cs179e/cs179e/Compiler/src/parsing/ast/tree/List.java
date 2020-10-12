package parsing.ast.tree;


import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.Visitor;
import parsing.ast.visitor.GISVisitor;
import parsing.ast.visitor.GSVisitor;


public class List<T extends Node> extends java.util.LinkedList<T> implements Node {

	public T elementAt(int i)  { return get(i); }

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
