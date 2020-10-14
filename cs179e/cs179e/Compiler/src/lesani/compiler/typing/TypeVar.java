package lesani.compiler.typing;


import lesani.collection.xtolook.Either;
import lesani.compiler.typing.substitution.IndexVarSubst;
import lesani.compiler.typing.substitution.TypeVarSubst;
import lesani.compiler.typing.substitution.VarSubst;

public class TypeVar implements Type {

    // todo: Protect counter if concurrency is used.
    private static int counter = 0;
    private int i;

    protected TypeVar(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }


    public static TypeVar fresh() {
        return new TypeVar(counter++);
    }

    public TypeVar iFresh() {
        return fresh();
    }

    // Do not need to override equals.

    public static void restart() {
        counter = 0;
    }

    @Override
    public String toString() {
        return "T" + i;
    }

    public Either<lesani.compiler.typing.Type, Integer> apply(VarSubst varSubst) {
        if (varSubst instanceof TypeVarSubst)
            return new Either.Left<lesani.compiler.typing.Type,Integer>(apply((TypeVarSubst) varSubst));
        else
            return apply((IndexVarSubst) varSubst);
    }

    private Type apply(TypeVarSubst typeVarSubst) {
        if (this == typeVarSubst.typeVar)
            return (Type)typeVarSubst.subtType;
        else
            return this;
    }

    public Either<lesani.compiler.typing.Type, Integer> apply(IndexVarSubst indexVarSubst) {
        if (this == indexVarSubst.typeVar)
            return new Either.Right<lesani.compiler.typing.Type, Integer>(indexVarSubst.integer);
        else
            return new Either.Left<lesani.compiler.typing.Type, Integer>(this);
    }
}
