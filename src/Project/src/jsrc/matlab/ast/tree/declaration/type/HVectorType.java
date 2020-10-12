package jsrc.matlab.ast.tree.declaration.type;

import jsrc.matlab.typeinference.type.TypeVar;
import lesani.collection.xtolook.Either;

public class HVectorType extends MatrixType {


    public HVectorType(Either<ScalarType, TypeVar> elemType, Either<TypeVar, Integer> n) {
        super(elemType, new Either.Right<TypeVar, Integer>(1), n);
    }

    public HVectorType(ScalarType elemType, Integer n) {
        super(elemType, 1, n);
    }

    public HVectorType(TypeVar typeVar, Integer n) {
        super(typeVar, 1, n);
    }

    public HVectorType(TypeVar typeVar, TypeVar n) {
        super(typeVar, 1, n);
    }

    public HVectorType(ScalarType elemType, TypeVar n) {
        super(elemType, 1, n);
    }

}
