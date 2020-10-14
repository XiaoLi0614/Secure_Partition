package jsrc.matlab.typeinference.type;

import lesani.collection.xtolook.Either;
import lesani.collection.func.Fun;
import lesani.compiler.typing.Type;
import lesani.compiler.typing.substitution.VarSubst;

public class VarArgFunType implements SingleFunType {


    public static interface OutputTypeComputer {
        DataType computeOutputType(int rows, int columns);
    }

    public DataType elemType;
    public OutputTypeComputer computer;


    public VarArgFunType(DataType elemType, OutputTypeComputer computer) {
        this.elemType = elemType;
        this.computer = computer;
    }

    public boolean contains(TypeVar typeVar) {
        return /*(output.contains(typeVar) || */elemType.contains(typeVar);
    }

    public Either<Type, Integer> apply(VarSubst varSubst) {
        DataType xVarType;
        xVarType = varSubst.apply(elemType).apply(
            new Fun<Type, DataType>() {
                public DataType apply(Type input) {
                    return (DataType)input;
                }
            },
            new Fun<Integer, DataType>() {
                public DataType apply(Integer input) {
                    throw new RuntimeException();
                }
            }
        );
        return new Either.Left<Type, Integer>(new VarArgFunType(xVarType, computer));
    }

}
