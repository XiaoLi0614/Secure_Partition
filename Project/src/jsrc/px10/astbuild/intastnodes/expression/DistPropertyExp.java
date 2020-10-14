package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;


public class DistPropertyExp implements Node {
    private static DistPropertyExp theInstance = new DistPropertyExp();

    public static DistPropertyExp instance() {
		return theInstance;
	}

    private DistPropertyExp() {
    }
}
