package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;


public class MaxExp implements Node {
    private static MaxExp theInstance = new MaxExp();

    public static MaxExp instance() {
		return theInstance;
	}

    private MaxExp() {
    }
}