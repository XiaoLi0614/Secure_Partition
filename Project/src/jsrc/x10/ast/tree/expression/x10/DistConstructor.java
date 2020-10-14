package jsrc.x10.ast.tree.expression.x10;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;


public class DistConstructor extends X10Expression {
    public Expression regionExpression;
    public Expression placeExpression;

    public DistConstructor(Expression regionExpression, Expression placeExpression) {
        this.regionExpression = regionExpression;
        this.placeExpression = placeExpression;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.X10ExpressionVisitor v) {
        return v.visit(this);
    }

}