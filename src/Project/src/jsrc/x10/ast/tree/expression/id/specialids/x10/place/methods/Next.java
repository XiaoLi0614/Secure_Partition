package jsrc.x10.ast.tree.expression.id.specialids.x10.place.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Next extends Method {

    private static Next theInstance = new Next();

    public static Next instance() {
		return theInstance;
	}

    private Next() {
        super("next");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}