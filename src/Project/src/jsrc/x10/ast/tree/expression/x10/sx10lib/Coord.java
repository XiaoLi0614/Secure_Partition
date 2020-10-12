package jsrc.x10.ast.tree.expression.x10.sx10lib;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;


public class Coord extends SX10Expression {
    public Expression region;
    public Expression pointIndex;
    public Expression coordIndex;

    public Coord(Expression region, Expression pointIndex, Expression coordIndex) {
        this.region = region;
        this.pointIndex = pointIndex;
        this.coordIndex = coordIndex;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.X10ExpressionVisitor.SX10ExpressionVisitor v) {
        return v.visit(this);
    }
}
