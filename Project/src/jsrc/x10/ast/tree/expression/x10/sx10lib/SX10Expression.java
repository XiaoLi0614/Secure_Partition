package jsrc.x10.ast.tree.expression.x10.sx10lib;


import jsrc.x10.ast.tree.expression.x10.X10Expression;
import jsrc.x10.ast.visitor.DFSVisitor;

public abstract class SX10Expression extends X10Expression {

    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.X10ExpressionVisitor.SX10ExpressionVisitor sx10ExpressionVisitor);

    @Override
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.X10ExpressionVisitor x10ExpressionVisitor) {
        return x10ExpressionVisitor.visit(this);
    }
}
