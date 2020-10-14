package jsrc.x10.ast.tree.expression.literal;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;

public class ArrayLiteral extends Literal {

    public Expression[] elements;

    public ArrayLiteral(Expression[] elements) {
        this.elements = elements;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.LiteralVisitor v) {
        return v.visit(this);
    }
}
