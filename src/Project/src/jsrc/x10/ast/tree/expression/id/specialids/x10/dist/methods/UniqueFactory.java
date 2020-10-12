package jsrc.x10.ast.tree.expression.id.specialids.x10.dist.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class UniqueFactory extends Method {

    private static UniqueFactory theInstance = new UniqueFactory();

    public static UniqueFactory instance() {
		return theInstance;
	}

    private UniqueFactory() {
        super("UNIQUE");
    }
    // V2.0: makeUnique
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}