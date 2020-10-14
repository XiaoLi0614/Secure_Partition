package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.visitor.StatementVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class VoidReturn extends Return {
//	private static VoidReturn theInstance = new VoidReturn();

    public static VoidReturn instance() {
//		return theInstance;
        return new VoidReturn();
	}

	public VoidReturn() {
	}
/*
	private VoidReturn() {
	}
*/

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }
}