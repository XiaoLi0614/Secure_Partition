package jsrc.x10.ast.tree.expression.id.specialids.x10.place.properties;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Property;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class MaxPlaces extends Property {

    private static MaxPlaces theInstance = new MaxPlaces();

    public static MaxPlaces instance() {
		return theInstance;
	}

    private MaxPlaces() {
        super("MAX_PLACES");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}