package jsrc.x10.ast.tree.expression.id.specialids.x10.classes;

import jsrc.x10.ast.visitor.DFSVisitor;


public class Dist extends Class {
    private static Dist theInstance = new Dist();

    public static Dist instance() {
		return theInstance;
	}

    private Dist() {
        super("dist");
    }
    //V2.0: Dist
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}