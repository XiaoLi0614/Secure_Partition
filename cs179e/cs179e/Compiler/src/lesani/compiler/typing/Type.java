package lesani.compiler.typing;

import lesani.collection.xtolook.Either;
import lesani.compiler.typing.substitution.VarSubst;

public interface Type {

    Either<Type, Integer> apply(VarSubst varSubst);

//    Either<Type,Integer> apply(TypeVarSubst<Type> varSubst);
//    Either<Type,Integer> apply(IndexVarSubst<Type> varSubst);
}
