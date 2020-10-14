package jsrc.x10.ast.tree.statement.x10;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.statement.Statement;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 9:22:29 AM
 */

public class Async extends X10Statement {

    public Expression place;
    public Statement statement;

    public Async(Expression place, Statement statement) {
        this.place = place;
        this.statement = statement;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.StatementVisitor.X10StatementVisitor x10StatementVisitor) {
        return x10StatementVisitor.visit(this);
    }
}
