package jsrc.x10.ast.tree.expression.xtras;

import jsrc.x10.ast.tree.expression.Expression;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 10:27:35 AM
 */
public class RegionSelection /*implements Expression*/ {
    public Expression array;

    public RegionSelection(Expression array) {
        this.array = array;
    }
//    public <R> R accept(SVisitor<R> v) {
//        return v.visit(this);
//    }
}
