package jsrc.x10.ast.tree.expression.id.specialids.x10.array.properties;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Property;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Distribution extends Property {

    private static Distribution theInstance = new Distribution();

    public static Distribution instance() {
		return theInstance;
	}

    private Distribution() {
        super("distribution");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }
}