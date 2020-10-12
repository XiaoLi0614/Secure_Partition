package jsrc.x10.ast.tree.expression.id.specialids.x10.place.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class IsLast extends Method {

    private static IsLast theInstance = new IsLast();

    public static IsLast instance() {
		return theInstance;
	}

    private IsLast() {
        super("isLast");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}