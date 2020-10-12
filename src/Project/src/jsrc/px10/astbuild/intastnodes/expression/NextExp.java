package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;


public class NextExp implements Node {
    private static NextExp theInstance = new NextExp();

    public static NextExp instance() {
		return theInstance;
	}

    private NextExp() {
    }
}