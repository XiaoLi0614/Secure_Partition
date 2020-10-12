package jsrc.x10.ast.tree.expression.id.specialids.x10.classes;

import jsrc.x10.ast.visitor.DFSVisitor;


public class System extends Class {
    private static System theInstance = new System();

    public static System instance() {
		return theInstance;
	}

    private System() {
        super("System");
    }
    // V2.0: Place
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}