package parsing.ast.tree;

import parsing.ast.visitor.GISVisitor;
import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.GSVisitor;
import parsing.ast.visitor.Visitor;
import parsing.parser.syntaxtree.NodeToken;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Token implements Node {
	private NodeToken nodeToken;
	public Token(NodeToken nodeToken) {
		this.nodeToken = nodeToken;
	}

	public NodeToken getSpecialAt(int i) {
		return nodeToken.getSpecialAt(i);
	}

	public int numSpecials() {
		return nodeToken.numSpecials();
	}

	public void addSpecial(NodeToken s) {
		nodeToken.addSpecial(s);
	}

	public void trimSpecials() {
		nodeToken.trimSpecials();
	}

	public String toString() {
		return nodeToken.toString();
	}

	public String withSpecials() {
		return nodeToken.withSpecials();
	}

	public int getBeginLine() {
		return nodeToken.beginLine;
	}

	public int getBeginColumn() {
		return nodeToken.beginColumn;
	}

	public int getEndLine() {
		return nodeToken.endLine;
	}

	public int getEndColumn() {
		return nodeToken.endColumn;
	}

	public int getKind() {
		return nodeToken.kind;
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

