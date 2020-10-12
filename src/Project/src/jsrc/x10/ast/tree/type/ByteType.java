package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class ByteType extends ScalarType {
	private static ByteType theInstance = new ByteType();

    public static ByteType instance() {
		return theInstance;
	}

	private ByteType() {
	}

    @Override
    public String toString() {
        return "Byte";
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }

}