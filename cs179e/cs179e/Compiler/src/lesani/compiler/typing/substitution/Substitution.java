package lesani.compiler.typing.substitution;

import fj.F2;
import fj.data.List;
import lesani.collection.xtolook.Either;
import lesani.collection.func.Fun;
import lesani.compiler.typing.Type;
import lesani.compiler.typing.TypeVar;


public class Substitution/*<Type extends lesani.compiler.typing.Type>*/ {

    List<VarSubst> varSubsts = List.list();

    public Substitution() {
    }

    private Substitution(List<VarSubst> varSubsts) {
        this.varSubsts = varSubsts;
    }

    public Substitution add(TypeVar typeVar, Type type) {
        VarSubst varSubst = new TypeVarSubst(typeVar, type);
        return new Substitution(varSubsts.cons(varSubst));
//        System.out.println("New VarSubst = " + varSubst);
    }
    public Substitution add(VarSubst varSubst) {
        return new Substitution(varSubsts.cons(varSubst));
        // This is added to the beginning of the list not the end of the list.
//        System.out.println("New VarSubst = " + varSubst);
    }


    public Either<Type, Integer> apply(Type type) {
        final F2<VarSubst, Either<Type, Integer>, Either<Type, Integer>> f = new F2<VarSubst, Either<Type, Integer>, Either<Type, Integer>>() {
            @Override
            public Either<Type, Integer> f(final VarSubst varSubst, final Either<Type, Integer> either) {
                return either.apply(
                    new Fun<Type, Either<Type, Integer>>() {
                        public Either<Type, Integer> apply(Type type) {
//                            System.out.println("varSubst = " + varSubst);
//                            System.out.println("type = " + type);
                            final Either<Type, Integer> applied = varSubst.apply(type);
//                            System.out.println("applied = " + applied);
                            return applied;
                        }
                    },
                    new Fun<Integer, Either<Type, Integer>>() {
                        public Either<Type, Integer> apply(Integer integer) {
                            return either;
                        }
                    }
                );
            }
        };
        Either<Type, Integer> init = new Either.Left<Type, Integer>(type);
        return varSubsts.foldRight(f, init);

//        for (VarSubst varSubst : varSubsts)
//            currentType = varSubst.apply(currentType);
//        return currentType;
    }

    private Either<Type, Integer> apply(final Either<Type, Integer> either) {
        return either.apply(
            new Fun<Type, Either<Type, Integer>>() {
                public Either<Type, Integer> apply(Type type1) {

                    final Either<Type, Integer> xType = Substitution.this.apply(type1);
                    if (xType == null)
                        throw new RuntimeException();
                    return xType;
                }
            },
            new Fun<Integer, Either<Type, Integer>>() {
                public Either<Type, Integer> apply(Integer input) {
                    return either;
                }
            }
        );
    }

    @Override
    public String toString() {
//        String s = "";
//        for (VarSubst varSubst : varSubsts)
//            s += varSubst + "\n";
//        return s;
        F2<VarSubst, String, String> f = new F2<VarSubst, String, String>() {
            @Override
            public String f(VarSubst varSubst, String s) {
                return s + varSubst + "\n";
            }
        };
        return varSubsts.foldRight(f, "");
        // We do fold left to get the elements in the reverse order.
    }
}







