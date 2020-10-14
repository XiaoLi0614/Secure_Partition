package jsrc.x10.ast.tree.expression.id.specialids.x10.region.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Projection extends Method {

    private static Projection theInstance = new Projection();

    public static Projection instance() {
		return theInstance;
	}

    private Projection() {
        super("rank");
    }
    // It's projection in v2.0.

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}