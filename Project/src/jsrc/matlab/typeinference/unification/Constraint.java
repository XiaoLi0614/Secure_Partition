package jsrc.matlab.typeinference.unification;

import jsrc.matlab.typeinference.type.Type;
import jsrc.matlab.typeinference.type.TypeVar;
import lesani.collection.xtolook.Either;
import lesani.collection.func.Fun;
import lesani.compiler.ast.LocInfo;
import lesani.compiler.typing.substitution.Substitution;

import java.util.HashSet;
import java.util.Set;

public class Constraint {

    public Either<Type, Integer> left;
    public Either<Type, Integer> right;

    public int compilationUnitNo;
    public LocInfo locInfo;
    public String text;

    public Constraint(Either<Type, Integer> left, Either<Type, Integer> right, int compilationUnitNo, LocInfo locInfo, String text) {
        if ((left == null) || (right == null))
            throw new RuntimeException();
        final Fun<Type, Object> f1 = new Fun<Type, Object>() {
            public Object apply(Type input) {
                if (input == null)
                    throw new RuntimeException();
                return null;
            }
        };
        final Fun<Integer, Object> f2 = new Fun<Integer, Object>() {
            public Object apply(Integer input) {
                if (input == null)
                    throw new RuntimeException();
                return null;
            }
        };

        left.apply(f1, f2);
        right.apply(f1, f2);

        this.left = left;
        this.right = right;
        this.compilationUnitNo = compilationUnitNo;
        this.locInfo = locInfo;
        this.text = text;
    }

    public Constraint(Type left, Type right, int compilationUnitNo, LocInfo locInfo, String text) {
        if ((left == null) || (right == null))
            throw new RuntimeException();
        this.left = new Either.Left<Type, Integer>(left);
        this.right = new Either.Left<Type, Integer>(right);
        this.compilationUnitNo = compilationUnitNo;
        this.locInfo = locInfo;
        this.text = text;
    }

    public Constraint(TypeVar left, Integer right, int compilationUnitNo, LocInfo locInfo, String text) {
        if ((left == null) || (right == null))
            throw new RuntimeException();
        this.left = new Either.Left<Type, Integer>(left);
        this.right = new Either.Right<Type, Integer>(right);
        this.compilationUnitNo = compilationUnitNo;
        this.locInfo = locInfo;
        this.text = text;
    }


    public Constraint(Integer i1, Integer i2, int compilationUnitNo, LocInfo locInfo, String text) {
        if ((i1 == null) || (i2 == null))
            throw new RuntimeException();
        this.left = new Either.Right<Type, Integer>(i1);
        this.right = new Either.Right<Type, Integer>(i2);
        this.compilationUnitNo = compilationUnitNo;
        this.locInfo = locInfo;
        this.text = text;
    }

    public Constraint apply(final Substitution substitution) {
        Fun<Type, Either<Type, Integer>> f1 = new Fun<Type, Either<Type, Integer>>() {
            public Either<Type, Integer> apply(Type input) {
                return substitution.apply(input).apply(
                    new Fun<lesani.compiler.typing.Type, Either<Type, Integer>>() {
                        public Either<Type, Integer> apply(lesani.compiler.typing.Type input) {
                            return new Either.Left<Type, Integer>((Type)input);
                        }
                    },
                    new Fun<Integer, Either<Type, Integer>>() {
                        public Either<Type, Integer> apply(Integer input) {
                            return new Either.Right<Type, Integer>(input);
                        }
                    }
                );
            }
        };
        Fun<Integer, Either<Type, Integer>> f2 = new Fun<Integer, Either<Type, Integer>>() {
            public Either<Type, Integer> apply(Integer input) {
                return new Either.Right<Type, Integer>(input);
            }
        };

        Either<Type, Integer> xLeft = left.apply(f1, f2);
        Either<Type, Integer> xRight = right.apply(f1, f2);

        return new Constraint(xLeft, xRight, compilationUnitNo, locInfo, text);
    }

    public Set<Constraint> apply(Set<Constraint> constraints, Substitution substitution) {
        Set<Constraint> substConstraints = new HashSet<Constraint>();
        for (Constraint constraint : constraints)
            substConstraints.add(constraint.apply(substitution));
        return substConstraints;
    }


    @Override
    public String toString() {
        return left + " == " + right + ". At " + compilationUnitNo + "-" + locInfo + ". " + text;// + " " + super.toString();
    }
}

