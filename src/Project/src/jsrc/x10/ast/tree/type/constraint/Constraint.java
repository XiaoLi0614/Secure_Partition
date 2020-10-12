package jsrc.x10.ast.tree.type.constraint;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.DFSVisitor;


public abstract class Constraint {
    public Expression value;

    public Constraint(Expression value) {
        this.value = value;
    }

    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor.ConstraintVisitor constraintVisitor);
}
