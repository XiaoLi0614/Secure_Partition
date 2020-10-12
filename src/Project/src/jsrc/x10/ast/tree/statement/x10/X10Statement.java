package jsrc.x10.ast.tree.statement.x10;

import jsrc.x10.ast.tree.statement.Statement;
import jsrc.x10.ast.visitor.DFSVisitor;
import jsrc.x10.ast.visitor.StatementVisitor;

public abstract class X10Statement extends Statement {

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }

    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.StatementVisitor.X10StatementVisitor x10StatementVisitor);
}
