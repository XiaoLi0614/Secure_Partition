package jsrc.matlab.ast.tree.declaration.type;

import jsrc.matlab.typeinference.type.TypeVar;
import lesani.collection.xtolook.Either;

public class VVectorType extends MatrixType {

    public VVectorType(Either<ScalarType, TypeVar> elemType, Either<TypeVar, Integer> n) {
        super(elemType, n, new Either.Right<TypeVar, Integer>(1));
    }

    public VVectorType(ScalarType elemType, Integer n) {
        super(elemType, n, 1);
    }

    public VVectorType(TypeVar typeVar, Integer n) {
        super(typeVar, n, 1);
    }

    public VVectorType(TypeVar typeVar, TypeVar n) {
        super(typeVar, n, 1);
    }

    public VVectorType(ScalarType scalarType, TypeVar var) {
        super(scalarType, var, 1);
    }
}
