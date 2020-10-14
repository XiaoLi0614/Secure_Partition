package jsrc.x10.ast.tree.expression.id.specialids.x10.region.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Low extends Method {

    private static Low theInstance = new Low();

    public static Low instance() {
		return theInstance;
	}

    private Low() {
        super("low");
    }
    // It's min(0) in v2.0.

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}