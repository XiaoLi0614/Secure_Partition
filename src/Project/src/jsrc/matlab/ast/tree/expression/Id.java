package jsrc.matlab.ast.tree.expression;

import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.type.DataType;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 10:08:25 AM
 */

public class Id extends Expression {

    public String name;

    public Id(String name) {
        this.name = name;
    }

    public <R> R accept(SVisitor.ExpressionVisitor<R> v) {
        return v.visit(this);
    }
}

