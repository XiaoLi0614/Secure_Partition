package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class LongType extends ScalarType {
	private static LongType theInstance = new LongType();

    public static LongType instance() {
		return theInstance;
	}

	private LongType() {
	}

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Long";
    }
}