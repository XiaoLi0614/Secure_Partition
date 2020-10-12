package parsing.ast.tree;

import parsing.ast.visitor.Visitor;
import parsing.ast.visitor.GISVisitor;
import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.GSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Minus implements Expression {
	public Expression operand1;
	public Expression operand2;

	public Minus(Expression operand1, Expression operand2) {
		this.operand1 = operand1;
		this.operand2 = operand2;
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

