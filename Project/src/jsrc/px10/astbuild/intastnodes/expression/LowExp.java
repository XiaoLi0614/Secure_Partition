package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;


public class LowExp implements Node {
    private static LowExp theInstance = new LowExp();

    public static LowExp instance() {
		return theInstance;
	}

    private LowExp() {
    }
}