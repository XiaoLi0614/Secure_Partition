package jsrc.x10.ast.tree.expression.x10;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;


public class PointConstructor extends X10Expression {
    public Expression[] expressions;

    public PointConstructor(Expression[] expressions) {
        this.expressions = expressions;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.X10ExpressionVisitor v) {
        return v.visit(this);
    }

}
