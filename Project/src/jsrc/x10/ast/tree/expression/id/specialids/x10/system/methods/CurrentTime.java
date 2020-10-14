package jsrc.x10.ast.tree.expression.id.specialids.x10.system.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class CurrentTime extends Method {
    private static CurrentTime theInstance = new CurrentTime();

    public static CurrentTime instance() {
		return theInstance;
	}

    private CurrentTime() {
        super("currentTimeMillis");
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }
}