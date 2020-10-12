package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class PlaceType extends ScalarType {
	private static PlaceType theInstance = new PlaceType();

    public static PlaceType instance() {
		return theInstance;
	}

	private PlaceType() {
	}

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }
}