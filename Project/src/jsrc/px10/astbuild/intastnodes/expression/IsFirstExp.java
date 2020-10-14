package jsrc.px10.astbuild.intastnodes.expression;

import jsrc.px10.syntaxanalyser.syntaxtree.NodeToken;
import jsrc.x10.ast.tree.SourceLoc;
import lesani.compiler.ast.Node;


public class IsFirstExp implements Node {
//    private static IsFirstExp theInstance = new IsFirstExp();

//    public static IsFirstExp instance() {
//		return theInstance;
//	}

    public IsFirstExp() {
    }

    public SourceLoc sourceLoc = new SourceLoc();

    public void setLoc(NodeToken nodeToken) {
        sourceLoc.lineNo = nodeToken.beginLine;
        sourceLoc.columnNo = nodeToken.beginColumn;
    }
}