package jsrc.matlab.ast.tree.expression.op.constructors;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.visitor.SVisitor;


public class MatrixConstructor extends Op {
    public Expression[][] elements;
    public static String name = "$VectorConstructor";
    public static String lexeme = "[...]";

    public MatrixConstructor(Expression[][] elements) {
        this.elements = elements;
    }

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor<R> v) {
        return v.visit(this);
    }

    Expression[] operands;
    @Override
    public Expression[] getOperands() {
        if (operands != null)
            return operands;
        int length = (elements.length==0) ? 0 : (elements.length * elements[0].length);
        operands = new Expression[length];
        if (length != 0)
            for (int i = 0; i < elements.length; i++)
                for (int j = 0; j < elements[0].length; j++)
                    operands[i*elements[0].length + j] = elements[i][j];
        return operands;
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
