package lesani.compiler.constraintsolver;


public class SubsetConstraint<Var> implements Constraint {
    public Var var;
    public Var rightVar;

    public SubsetConstraint(Var var, Var rightVar) {
        this.var = var;
        this.rightVar = rightVar;
    }

    public SubsetConstraint() {
    }

    @Override
    public String toString() {
        return var + "<-" + rightVar;
    }
}
