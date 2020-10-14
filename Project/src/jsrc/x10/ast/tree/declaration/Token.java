package jsrc.x10.ast.tree.declaration;

import jsrc.x10.ast.tree.Node;
import jsrc.px10.syntaxanalyser.syntaxtree.NodeToken;

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
}

