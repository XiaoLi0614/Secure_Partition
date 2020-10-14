package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class IntType extends ScalarType {
	private static IntType theInstance = new IntType();

    public static IntType instance() {
		return theInstance;
	}

	private IntType() {
	}

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Int";
    }

}