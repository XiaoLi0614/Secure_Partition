package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;


public class SizeExp implements Node {
    private static SizeExp theInstance = new SizeExp();

    public static SizeExp instance() {
		return theInstance;
	}

    private SizeExp() {
    }
}