package jsrc.x10.ast.tree.expression.id.specialids.x10.place.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class IsFirst extends Method {

    private static IsFirst theInstance = new IsFirst();

    public static IsFirst instance() {
		return theInstance;
	}

    private IsFirst() {
        super("isFirst");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}