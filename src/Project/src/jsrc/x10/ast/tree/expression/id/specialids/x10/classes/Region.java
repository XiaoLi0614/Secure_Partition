package jsrc.x10.ast.tree.expression.id.specialids.x10.classes;

import jsrc.x10.ast.visitor.DFSVisitor;


public class Region extends Class {
    private static Region theInstance = new Region();

    public static Region instance() {
		return theInstance;
	}

    private Region() {
        super("region");
    }
    // V2.0: Region
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}