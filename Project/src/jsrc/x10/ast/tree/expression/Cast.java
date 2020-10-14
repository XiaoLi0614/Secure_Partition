package jsrc.x10.ast.tree.expression;

import jsrc.x10.ast.tree.type.NonVoidType;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 4:15:11 PM
 */
public class Cast implements Expression {

    public NonVoidType type;
    public Expression operand;

    // In splasma the type can only be:
    // int, double, long, short, byte, value array, dist, region, point 

    public Cast(NonVoidType type, Expression operand) {
        this.type = type;
        this.operand = operand;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }
}
