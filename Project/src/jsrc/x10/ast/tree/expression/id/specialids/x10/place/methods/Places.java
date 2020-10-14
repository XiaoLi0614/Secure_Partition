package jsrc.x10.ast.tree.expression.id.specialids.x10.place.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Places extends Method {

    private static Places theInstance = new Places();

    public static Places instance() {
		return theInstance;
	}

    private Places() {
        super("places");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}