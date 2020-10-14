package jsrc.matlab.ast.tree.expression.literal;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.visitor.SVisitor;


/**
 * User: lesani, Date: 5-Nov-2009, Time: 12:40:33 PM
 */
public abstract class Literal extends Expression {
    public String lexeme;

    protected Literal(String lexeme) {
        this.lexeme = lexeme;
    }

    public <R> R accept(SVisitor.ExpressionVisitor<R> v) {
        return v.visit(this);
    }

    abstract public <R> R accept(SVisitor.ExpressionVisitor.LiteralVisitor<R> v);
}