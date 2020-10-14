package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.visitor.StatementVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Break extends Statement {
//	private static Break theInstance = new Break();

    public static Break instance() {
//		return theInstance;
        return new Break();
	}

	public Break() {
    }

//	private Break() {
//	}

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }
}