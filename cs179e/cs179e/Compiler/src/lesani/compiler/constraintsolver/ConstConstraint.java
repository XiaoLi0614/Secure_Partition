package lesani.compiler.constraintsolver;


import java.util.Set;

public class ConstConstraint<Var, T> implements Constraint {
    public Var var;
    public Set<T> rightValue;

    public ConstConstraint(Var var, Set<T> rightValue) {
        this.var = var;
        this.rightValue = rightValue;
    }

    public ConstConstraint() {
    }
}
