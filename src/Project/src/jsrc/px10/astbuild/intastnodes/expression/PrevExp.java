package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;


public class PrevExp implements Node {
    private static PrevExp theInstance = new PrevExp();

    public static PrevExp instance() {
		return theInstance;
	}

    private PrevExp() {
    }
}