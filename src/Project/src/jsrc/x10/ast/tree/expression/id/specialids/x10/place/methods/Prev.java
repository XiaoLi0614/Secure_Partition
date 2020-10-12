package jsrc.x10.ast.tree.expression.id.specialids.x10.place.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Prev extends Method {

    private static Prev theInstance = new Prev();

    public static Prev instance() {
		return theInstance;
	}

    private Prev() {
        super("prev");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}