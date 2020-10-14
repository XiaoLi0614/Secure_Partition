package jsrc.x10.ast.tree.expression.id.specialids.x10.region.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class High extends Method {

    private static High theInstance = new High();

    public static High instance() {
		return theInstance;
	}

    private High() {
        super("high");
    }
    // It's max(0) in v2.0.

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}