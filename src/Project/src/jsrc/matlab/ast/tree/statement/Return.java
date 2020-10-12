package jsrc.matlab.ast.tree.statement;

import jsrc.matlab.ast.visitor.SVisitor;


/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Return extends Statement {
	private static Return theInstance = new Return();

    public static Return instance() {
		return theInstance;
	}

	private Return() {
	}

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }
}