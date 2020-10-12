package jsrc.x10.ast.tree.expression.id.specialids.x10.object;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Equals extends Method {

    private static Equals theInstance = new Equals();

    public static Equals instance() {
		return theInstance;
	}

    private Equals() {
        super("equals");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}