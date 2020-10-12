package jsrc.matlab.ast.tree.expression.op.logical;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.visitor.SVisitor;


/**
 * User: lesani, Date: Nov 3, 2009, Time: 3:57:38 PM
 */
public class Not extends LogicalOp {
    public Expression operand;
    public static String name = "$Not";
    public static String lexeme = "~";

    public Not(Expression operand) {
        this.operand = operand;
    }

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor.LogicalOpVisitor<R> v) {
        return v.visit(this);
    }

    @Override
    public Expression[] getOperands() {
        return new Expression[]{operand};
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLexeme() {
        return lexeme;
    }
}