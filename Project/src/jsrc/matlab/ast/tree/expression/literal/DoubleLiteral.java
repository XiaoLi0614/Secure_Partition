package jsrc.matlab.ast.tree.expression.literal;

import jsrc.matlab.ast.visitor.SVisitor;


/**
 * User: lesani, Date: 5-Nov-2009, Time: 12:41:55 PM
 */
public class DoubleLiteral extends Literal {

    public DoubleLiteral(String lexeme) {
        super(lexeme);
    }

    @Override
    public <R> R accept(SVisitor.ExpressionVisitor.LiteralVisitor<R> v) {
        return v.visit(this);
    }

}