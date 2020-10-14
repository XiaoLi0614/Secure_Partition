package jsrc.x10.ast.tree.expression.id.specialids.x10.region.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Size extends Method {

    private static Size theInstance = new Size();

    public static Size instance() {
		return theInstance;
	}

    private Size() {
        super("size");
    }
    // It's max() in v2.0.

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}