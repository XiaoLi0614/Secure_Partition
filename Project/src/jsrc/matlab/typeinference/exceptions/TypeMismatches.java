package jsrc.matlab.typeinference.exceptions;

import jsrc.matlab.typeinference.unification.Constraint;

import java.util.LinkedList;
import java.util.List;


public class TypeMismatches extends Exception {
    public List<TypeMismatch> typeMismatches;
    public List<Constraint> constraints;
    public fj.data.List<List<List<Constraint>>> altConsts;

    public TypeMismatches(List<TypeMismatch> typeMismatches, List<Constraint> constraints, fj.data.List<List<List<Constraint>>> altConsts) {
        this.typeMismatches = typeMismatches;
        this.constraints = constraints;
        this.altConsts = altConsts;
    }

    public TypeMismatches(TypeMismatch typeMismatch, List<Constraint> constraints, fj.data.List<List<List<Constraint>>> altConsts) {
        typeMismatches = new LinkedList<TypeMismatch>();
        typeMismatches.add(typeMismatch);
        this.constraints = constraints;
        this.altConsts = altConsts;

    }
}

