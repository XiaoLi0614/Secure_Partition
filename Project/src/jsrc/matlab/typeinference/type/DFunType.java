package jsrc.matlab.typeinference.type;

import lesani.collection.xtolook.Either;
import lesani.compiler.typing.substitution.VarSubst;
import lesani.collection.func.Fun;

public class DFunType implements SingleFunType {

    public DataType[] inputs;
    public DataType output;

    public DFunType(DataType[] inputs, DataType output) {
        this.inputs = inputs;
        this.output = output;
    }

    public boolean contains(final TypeVar typeVar) {
        for (DataType input : inputs)
            if (input.contains(typeVar))
                return true;
        return output.contains(typeVar);
    }


    public Either<lesani.compiler.typing.Type, Integer> apply(VarSubst varSubst) {
        DataType[] inputTypes = inputs;
        DataType[] substInputTypes = new DataType[inputTypes.length];
        for (int i = 0; i < substInputTypes.length; i++)
            substInputTypes[i] = inputTypes[i].apply(varSubst).apply(
                new Fun<lesani.compiler.typing.Type, DataType>() {
                    public DataType apply(lesani.compiler.typing.Type type) {
                        return (DataType) type;
                    }
                },
                new Fun<Integer, DataType>() {
                    public DataType apply(Integer input) {
                        // this can not happen.
                        throw new RuntimeException();
                    }
                }
            );

        DataType outputType = output;

        DataType substOutputType = outputType.apply(varSubst).apply(
            new Fun<lesani.compiler.typing.Type, DataType>() {
                public DataType apply(lesani.compiler.typing.Type type) {
                    return (DataType) type;
                }
            },
            new Fun<Integer, DataType>() {
                public DataType apply(Integer integer) {
                    // this can not happen.
                    throw new RuntimeException();
                }
            }
        );
        return new Either.Left<lesani.compiler.typing.Type, Integer>(
                new DFunType(substInputTypes, substOutputType));
    }


    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < inputs.length; i++) {
            DataType input = inputs[i];
            s = s + input;
            if (i != inputs.length - 1)
                s += ", ";
        }
        s += " -> ";
        s += output;
        return s;
    }
}
