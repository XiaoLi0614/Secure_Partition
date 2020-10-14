package jsrc.x10.ast.tree.statement.x10;

import jsrc.x10.ast.tree.statement.Statement;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 9:34:21 AM
 */
public class Finish extends X10Statement {

    public Statement statement;

    public Finish(Statement statement) {
        this.statement = statement;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.StatementVisitor.X10StatementVisitor x10StatementVisitor) {
        return x10StatementVisitor.visit(this);
    }
}