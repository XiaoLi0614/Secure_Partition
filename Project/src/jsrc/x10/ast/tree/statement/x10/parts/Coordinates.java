package jsrc.x10.ast.tree.statement.x10.parts;

import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 10:08:53 AM
 */
public class Coordinates implements PointFormalVar {
    public jsrc.x10.ast.tree.expression.id.Id[] ids;
    //    String i2;
    //    String i3;

    public Coordinates(Id i1) {
        ids = new Id[] {i1};
    }

    public Coordinates(Id[] ids) {
        this.ids = ids;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.StatementVisitor.X10StatementVisitor.PointFormalVarVisitor v) {
        return v.visit(this);
    }
}
