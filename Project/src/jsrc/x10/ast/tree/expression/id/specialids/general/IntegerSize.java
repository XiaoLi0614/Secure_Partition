package jsrc.x10.ast.tree.expression.id.specialids.general;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class IntegerSize extends GSpecialId {
	private static IntegerSize theInstance = new IntegerSize();

    public static IntegerSize instance() {
		return theInstance;
	}

	private IntegerSize() {
        super("$IntegerSize");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.GSpecialIdVisitor v) {
        return v.visit(this);
    }

}