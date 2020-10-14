package jsrc.x10.ast.tree.expression.id.specialids.x10.classes;

import jsrc.x10.ast.visitor.DFSVisitor;


public class Place extends Class {
    private static Place theInstance = new Place();

    public static Place instance() {
		return theInstance;
	}

    private Place() {
        super("place");
    }
    // V2.0: Place
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}
