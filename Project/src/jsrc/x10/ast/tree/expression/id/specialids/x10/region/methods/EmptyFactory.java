package jsrc.x10.ast.tree.expression.id.specialids.x10.region.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class EmptyFactory extends Method {

    private static EmptyFactory theInstance = new EmptyFactory();

    public static EmptyFactory instance() {
		return theInstance;
	}

    private EmptyFactory() {
        super("factory.emptyRegion");
    }
    // V2.0: makeEmpty
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}