package jsrc.x10.ast.tree.statement.x10.parts;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 10:06:23 AM
 */
public class Id implements PointFormalVar {
    public jsrc.x10.ast.tree.expression.id.Id name;

    public Id(jsrc.x10.ast.tree.expression.id.Id name) {
        this.name = name;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.StatementVisitor.X10StatementVisitor.PointFormalVarVisitor v) {
        return v.visit(this);
    }
}

