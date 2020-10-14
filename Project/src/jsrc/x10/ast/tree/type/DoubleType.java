package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class DoubleType extends ScalarType {
	private static DoubleType theInstance = new DoubleType();

    public static DoubleType instance() {
		return theInstance;
	}

	private DoubleType() {
	}

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Double";
    }

}