package lesani.compiler.typing.substitution;

import lesani.collection.xtolook.Either;
import lesani.compiler.typing.Type;


public abstract class VarSubst/*<Type extends lesani.compiler.typing.Type>*/ {

    public abstract Either<Type, Integer> apply(Type type);

}
