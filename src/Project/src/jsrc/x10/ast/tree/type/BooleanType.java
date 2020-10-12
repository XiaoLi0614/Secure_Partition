package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.visitor.CPSPrinter;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class BooleanType extends ScalarType {
	private static BooleanType theInstance = new BooleanType();

    public static BooleanType instance() {
		return theInstance;
	}

	private BooleanType() {
	}

    @Override
    public String toString() {
        return "Boolean";
    }

    @Override
    public Object accept(CPSPrinter.FileVisitor.ClassVisitor.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }
}