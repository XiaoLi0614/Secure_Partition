package jsrc.matlab.typeinference.type;

import jsrc.matlab.ast.visitor.SVisitor;
import lesani.compiler.typing.substitution.VarSubst;
import lesani.collection.xtolook.Either;
import lesani.collection.func.Fun;

public class TupleType implements DataType {
    public DataType[] types;

    public TupleType(DataType[] types) {
        this.types = types;
    }

    public boolean contains(TypeVar typeVar) {
        for (DataType type : types)
            if (type.contains(typeVar))
                return true;
        return false;
    }


    public Either<lesani.compiler.typing.Type, Integer> apply(VarSubst varSubst) {
        DataType[] xTypes = new DataType[types.length];
        for (int i = 0; i < xTypes.length; i++)
            xTypes[i] = types[i].apply(varSubst).apply(
                new Fun<lesani.compiler.typing.Type, DataType>() {
                    public DataType apply(lesani.compiler.typing.Type input) {
                        return (DataType) input;
                    }
                },
                new Fun<Integer, DataType>() {
                    public DataType apply(Integer input) {
                        throw new RuntimeException();
                    }
                }
            );

        return new Either.Left<lesani.compiler.typing.Type, Integer>(new TupleType(xTypes));
    }



    @Override
    public String toString() {
        String s = "";
        for (int i = 0, typesLength = types.length; i < typesLength; i++) {
            DataType type = types[i];
            s += type;
            if (i != typesLength - 1)
                s += ", ";
        }
        return "TupleType[" + s + "]";
    }

    public <T> T accept(SVisitor.TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
