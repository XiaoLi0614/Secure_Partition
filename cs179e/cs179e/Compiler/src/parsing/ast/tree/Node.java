package parsing.ast.tree;

import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.Visitor;
import parsing.ast.visitor.GISVisitor;
import parsing.ast.visitor.GSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public interface Node {
	public void accept(Visitor visitor);

	public <R, A> R accept(GISVisitor<R, A> visitor, A inheritedVar);

	public <A> void accept(GIVisitor<A> visitor, A inheritedVar);

	public <R> R accept(GSVisitor<R> visitor);

}
