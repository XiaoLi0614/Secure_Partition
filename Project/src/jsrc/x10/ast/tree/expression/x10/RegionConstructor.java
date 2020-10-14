package jsrc.x10.ast.tree.expression.x10;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;
import lesani.collection.option.*;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 3:54:59 PM
 */

public class RegionConstructor extends X10Expression {

    public static class Dimension {
        public Option<Expression> min;
        public Expression max;

        public Dimension(Expression min, Expression max) {
            this.min = new Some<Expression>(min);
            this.max = max;
        }

        public Dimension(Expression e) {
            this.min = None.instance();
            this.max = e;            
        }
    }
    public Dimension[] dims;

    public RegionConstructor(Dimension[] dims) {
        this.dims = dims;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.X10ExpressionVisitor v) {
        return v.visit(this);
    }
}
