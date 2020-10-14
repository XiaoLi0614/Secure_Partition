package jsrc.x10.ast.tree.expression.x10.sx10lib;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;

public class Sum extends SX10Expression {

    public Expression array;

    public Sum(Expression array) {
        this.array = array;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.X10ExpressionVisitor.SX10ExpressionVisitor v) {
        return v.visit(this);
    }
}
