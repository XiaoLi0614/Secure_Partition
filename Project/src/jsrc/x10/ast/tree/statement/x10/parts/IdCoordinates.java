package jsrc.x10.ast.tree.statement.x10.parts;

import jsrc.x10.ast.visitor.DFSVisitor;


public class IdCoordinates implements PointFormalVar {
    public Id id;
    public Coordinates coordinates;

    public IdCoordinates(Id id, Coordinates coordinates) {
        this.id = id;
        this.coordinates = coordinates;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.StatementVisitor.X10StatementVisitor.PointFormalVarVisitor v) {
        return v.visit(this);
    }

}

