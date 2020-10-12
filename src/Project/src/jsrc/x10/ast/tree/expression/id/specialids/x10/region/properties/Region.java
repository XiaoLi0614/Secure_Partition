package jsrc.x10.ast.tree.expression.id.specialids.x10.region.properties;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Property;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Region extends Property {

    private static Region theInstance = new Region();

    public static Region instance() {
		return theInstance;
	}

    private Region() {
        super("region");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}