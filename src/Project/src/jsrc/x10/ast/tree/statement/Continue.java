package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.visitor.StatementVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Continue extends Statement {
//	private static Continue theInstance = new Continue();

    public static Continue instance() {
//		return theInstance;
        return new Continue();
	}

	public Continue() {
	}
//	private Continue() {
//	}

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }
}