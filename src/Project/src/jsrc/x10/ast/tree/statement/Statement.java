package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.visitor.StatementVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 9:11:16 AM
 */
public abstract class Statement extends VarDeclOrSt {
    public abstract Object accept(StatementVisitor statementVisitor);
}
