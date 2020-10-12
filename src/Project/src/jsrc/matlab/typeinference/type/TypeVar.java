package jsrc.matlab.typeinference.type;

import jsrc.matlab.ast.visitor.SVisitor;
import lesani.compiler.typing.substitution.IndexVarSubst;
import lesani.compiler.typing.substitution.TypeVarSubst;
import lesani.compiler.typing.substitution.VarSubst;
import lesani.collection.xtolook.Either;

public class TypeVar extends lesani.compiler.typing.TypeVar implements DataType {


    protected TypeVar(int i) {
        super(i);
    }

    public static TypeVar fresh() {
        lesani.compiler.typing.TypeVar typeVar =
            lesani.compiler.typing.TypeVar.fresh();
        return new TypeVar(typeVar.getI());
    }

    public TypeVar iFresh() {
        return fresh();
    }


    public boolean contains(TypeVar typeVar) {
        return typeVar == this;
    }

    public <T> T accept(SVisitor.TypeVisitor<T> visitor) {
        return visitor.visit(this);
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
