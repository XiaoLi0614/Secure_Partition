package jsrc.x10.ast.tree.expression.x10.sx10lib;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;


public class Ordinal extends SX10Expression {
    public Expression region;
    public Expression point;

    public Ordinal(Expression region, Expression point) {
        this.region = region;
        this.point = point;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.X10ExpressionVisitor.SX10ExpressionVisitor v) {
        return v.visit(this);
    }
}