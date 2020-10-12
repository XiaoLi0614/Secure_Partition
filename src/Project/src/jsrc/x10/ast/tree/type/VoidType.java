package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class VoidType extends Type {
	private static VoidType theInstance = new VoidType();

    public static VoidType instance() {
		return theInstance;
	}

	private VoidType() {
	}

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }

}