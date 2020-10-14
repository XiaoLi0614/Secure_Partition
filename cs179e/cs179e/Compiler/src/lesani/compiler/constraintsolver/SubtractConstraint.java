package lesani.compiler.constraintsolver;


public class SubtractConstraint<Var> implements Constraint {
    public Var var;
    public Var rightVar;

    public SubtractConstraint(Var var, Var rightVar) {
        this.var = var;
        this.rightVar = rightVar;
    }

    public SubtractConstraint() {
    }

    @Override
    public String toString() {
        return var + "/" + rightVar;
    }
}
