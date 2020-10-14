package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;


public class SumExp implements Node {
    private static SumExp theInstance = new SumExp();

    public static SumExp instance() {
		return theInstance;
	}

    private SumExp() {
    }
}