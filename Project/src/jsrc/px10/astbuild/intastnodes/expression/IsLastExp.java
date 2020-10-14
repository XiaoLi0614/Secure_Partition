package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;


public class IsLastExp implements Node {
    private static IsLastExp theInstance = new IsLastExp();

    public static IsLastExp instance() {
		return theInstance;
	}

    private IsLastExp() {
    }
}