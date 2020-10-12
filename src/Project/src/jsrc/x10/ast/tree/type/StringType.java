package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class StringType extends ScalarType {
	private static StringType theInstance = new StringType();

    public static StringType instance() {
		return theInstance;
	}

	private StringType() {
	}

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "String";
    }

}