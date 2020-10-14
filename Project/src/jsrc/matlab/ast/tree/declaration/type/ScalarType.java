package jsrc.matlab.ast.tree.declaration.type;

import jsrc.matlab.ast.tree.Node;
import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.type.DataType;
import lesani.collection.xtolook.Either;
import lesani.compiler.typing.Type;
import lesani.compiler.typing.substitution.VarSubst;

public abstract class ScalarType implements DataType, Node {

    public <R> R accept(SVisitor<R> v) {
        return null;
    }

    public abstract <T> T accept(SVisitor.TypeVisitor<T> visitor);

    public Either<Type, Integer> apply(VarSubst varSubst) {
        return new Either.Left<Type, Integer>(this);
    }

}
