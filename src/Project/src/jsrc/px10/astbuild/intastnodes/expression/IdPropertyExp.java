package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;


public class IdPropertyExp implements Node {
    private static IdPropertyExp theInstance = new IdPropertyExp();

    public static IdPropertyExp instance() {
		return theInstance;
	}

    private IdPropertyExp() {
    }
}