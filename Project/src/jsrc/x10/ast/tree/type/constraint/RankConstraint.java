package jsrc.x10.ast.tree.type.constraint;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;

public class RankConstraint extends Constraint {

    public RankConstraint(Expression value) {
        super(value);
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor.ConstraintVisitor v) {
        return v.visit(this);
    }

}
