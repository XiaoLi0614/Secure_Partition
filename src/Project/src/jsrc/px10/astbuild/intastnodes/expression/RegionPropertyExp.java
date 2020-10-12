package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;


public class RegionPropertyExp implements Node {
    private static RegionPropertyExp theInstance = new RegionPropertyExp();

    public static RegionPropertyExp instance() {
		return theInstance;
	}

    private RegionPropertyExp() {
    }
}