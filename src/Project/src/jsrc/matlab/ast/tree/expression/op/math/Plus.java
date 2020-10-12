package jsrc.matlab.ast.tree.expression.op.math;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.visitor.SVisitor;


/**
 * User: lesani, Date: Nov 3, 2009, Time: 2:19:53 PM
 */
public class Plus extends MathOp {
    public Expression operand1;
    public Expression operand2;
    public static String name = "$Plus";
    public static String lexeme = "+";

    public Plus(Expression operand1, Expression operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor.MathOpVisitor<R> v) {
        return v.visit(this);
    }

    @Override
    public Expression[] getOperands() {
        return new Expression[]{operand1, operand2};
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
