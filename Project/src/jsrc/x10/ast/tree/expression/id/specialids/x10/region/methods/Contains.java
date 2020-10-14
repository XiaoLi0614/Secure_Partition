package jsrc.x10.ast.tree.expression.id.specialids.x10.region.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Contains extends Method {

    private static Contains theInstance = new Contains();

    public static Contains instance() {
		return theInstance;
	}

    private Contains() {
        super("contains");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}