package parsing.ast.tree;

import parsing.ast.visitor.Visitor;
import parsing.ast.visitor.GISVisitor;
import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.GSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Print implements Statement {
	public Expression argument;

	public Print(Expression argument) {
		this.argument = argument;
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

