package lesani.compiler.typing.substitution;

import lesani.collection.xtolook.Either;
import lesani.compiler.typing.Type;
import lesani.compiler.typing.TypeVar;


public class IndexVarSubst/*<T extends lesani.compiler.typing.Type>*/ extends VarSubst/*<Type>*/ {
    public TypeVar typeVar;
    public Integer integer;

    public IndexVarSubst(TypeVar typeVar, Integer integer) {
        this.typeVar = typeVar;
        this.integer = integer;
    }

    @Override
    public Either<Type, Integer> apply(Type type) {
//        VarSubst<lesani.compiler.typing.Type> varSubst = (VarSubst<lesani.compiler.typing.Type>) this;
//        return (Either<Type, Integer>)type.apply(varSubst);
        return type.apply(this);
    }

    @Override
    public String toString() {
        return (this.typeVar + "->" + integer);
    }

}

