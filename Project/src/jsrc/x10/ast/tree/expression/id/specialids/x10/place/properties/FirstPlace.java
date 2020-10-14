package jsrc.x10.ast.tree.expression.id.specialids.x10.place.properties;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Property;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class FirstPlace extends Property {

    private static FirstPlace theInstance = new FirstPlace();

    public static FirstPlace instance() {
		return theInstance;
	}

    private FirstPlace() {
        super("FIRST_PLACE");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}