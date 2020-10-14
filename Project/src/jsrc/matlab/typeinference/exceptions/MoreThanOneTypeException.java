package jsrc.matlab.typeinference.exceptions;

import lesani.compiler.typing.substitution.Substitution;

public class MoreThanOneTypeException extends RuntimeException {
    public Substitution substitution1;
    public Substitution substitution2;

    public MoreThanOneTypeException(Substitution substitution1, Substitution substitution2) {
        this.substitution1 = substitution1;
        this.substitution2 = substitution2;
    }
}
