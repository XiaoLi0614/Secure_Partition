package jsrc.matlab.ast.tree.statement;

import jsrc.matlab.ast.visitor.SVisitor;


/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Break extends Statement {
	private static Break theInstance = new Break();

    public static Break instance() {
		return theInstance;
	}

	private Break() {
	}

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }
}