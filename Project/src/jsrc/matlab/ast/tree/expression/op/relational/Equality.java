package jsrc.matlab.ast.tree.expression.op.relational;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.visitor.SVisitor;


/**
 * User: lesani, Date: Nov 3, 2009, Time: 2:19:53 PM
 */
public class Equality extends RelationalOp {
    public static String name = "$Equality";
    public static String lexeme = "==";

    public Equality(Expression operand1, Expression operand2) {
        super(operand1, operand2);
    }

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor.RelationalVisitor<R> v) {
        return v.visit(this);
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
