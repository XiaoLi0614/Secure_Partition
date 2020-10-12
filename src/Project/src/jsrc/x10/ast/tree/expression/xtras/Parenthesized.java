package jsrc.x10.ast.tree.expression.xtras;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 10:02:40 AM
 */
public class Parenthesized implements Expression {

    // This is needed to preserve operation priorities in source to source translation.

    public Expression expression;

    public Parenthesized(Expression expression) {
        this.expression = expression;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor v) {
        return v.visit(this);
    }
}