package parsing.ast.tree;

import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.GSVisitor;
import parsing.ast.visitor.Visitor;
import parsing.ast.visitor.GISVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class MethodCall implements Expression {

	public Expression receiver;
	public Id methodName;
	public List<Expression> arguments;


	public MethodCall(Expression receiver, Id methodName, List<Expression> arguments) {
		this.receiver = receiver;
		this.methodName = methodName;
		this.arguments = arguments;
	}

	public MethodCall(Expression receiver, Id methodName) {
		this.receiver = receiver;
		this.methodName = methodName;
		this.arguments = new List<Expression>();
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

