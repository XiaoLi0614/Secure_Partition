package jsrc.matlab.ast.tree.statement;

import jsrc.matlab.ast.visitor.SVisitor;

/*
 User: lesani, Date: Nov 2, 2009, Time: 1:50:45 PM
*/

public class Block extends Statement {

    public Statement[] statements;

    public Block(Statement[] statements) {
        this.statements = statements;
    }

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }
}