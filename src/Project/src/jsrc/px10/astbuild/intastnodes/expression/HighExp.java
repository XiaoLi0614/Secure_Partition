package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;


public class HighExp implements Node {
    private static HighExp theInstance = new HighExp();

    public static HighExp instance() {
		return theInstance;
	}

    private HighExp() {
    }
}

