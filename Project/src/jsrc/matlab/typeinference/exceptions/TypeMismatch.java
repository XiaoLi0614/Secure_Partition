package jsrc.matlab.typeinference.exceptions;

import jsrc.matlab.typeinference.unification.Constraint;
import lesani.compiler.typing.substitution.Substitution;

import java.util.LinkedList;
import java.util.List;


public class TypeMismatch extends Exception {
    public Constraint constraint;
    public Constraint origConstr;
    public LinkedList<Integer> altNos;

    public LinkedList<Constraint> visitedConstrains;
    public Substitution substitution;

    public TypeMismatch(Constraint constraint, Constraint origConstr, LinkedList<Integer> altNos, LinkedList<Constraint> visitedConstrains, Substitution substitution) {
        this.constraint = constraint;
        this.origConstr = origConstr;
        this.altNos = altNos;
        this.visitedConstrains = visitedConstrains;
        this.substitution = substitution;
    }

    @Override
    public String toString() {
        return "Type mismatch between " + constraint.left + " and " + constraint.right +
                " at " + constraint.locInfo;
    }
}

