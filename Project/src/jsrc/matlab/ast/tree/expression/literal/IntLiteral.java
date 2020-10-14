package jsrc.matlab.ast.tree.expression.literal;

import jsrc.matlab.ast.visitor.SVisitor;


/**
 * User: lesani, Date: 5-Nov-2009, Time: 12:41:55 PM
 */
public class IntLiteral extends Literal {

    public IntLiteral(String lexeme) {
        super(lexeme);
    }
    public IntLiteral(int i) {
        super(Integer.toString(i));
    }

    @Override
    public <R> R accept(SVisitor.ExpressionVisitor.LiteralVisitor<R> v) {
        return v.visit(this);
    }

}