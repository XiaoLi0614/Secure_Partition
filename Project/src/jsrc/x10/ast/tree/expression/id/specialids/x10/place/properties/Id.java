package jsrc.x10.ast.tree.expression.id.specialids.x10.place.properties;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Property;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Id extends Property {

    private static Id theInstance = new Id();

    public static Id instance() {
		return theInstance;
	}

    private Id() {
        super("id");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}

