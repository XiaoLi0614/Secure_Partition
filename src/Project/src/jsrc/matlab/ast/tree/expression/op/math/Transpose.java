package jsrc.matlab.ast.tree.expression.op.math;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.visitor.SVisitor;


public class Transpose extends MathOp {

    public Expression operand;
    public static String name = "$Transpose";
    public static String lexeme = "'";

    public Transpose(Expression operand) {
        this.operand = operand;
    }

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor.MathOpVisitor<R> v) {
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
