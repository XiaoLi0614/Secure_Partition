package jsrc.matlab.typeinference.type;

import jsrc.matlab.typeinference.constraintelicitation.Value;
import lesani.collection.xtolook.Either;
import lesani.collection.func.Fun;
import lesani.collection.option.Option;
import lesani.compiler.typing.Type;
import lesani.compiler.typing.substitution.VarSubst;

public class ValueDepFunType implements SingleFunType {

    public static interface OutputTypeComputer {
        public DataType computeOutputType(Option<Value>[] optionValues);
    }

    public DataType[] inputs;
    public OutputTypeComputer computer;

    public ValueDepFunType(DataType[] inputs, OutputTypeComputer computer) {
        this.inputs = inputs;
        this.computer = computer;
    }

    public boolean contains(TypeVar typeVar) {
        return false;
    }

    public Either<Type, Integer> apply(VarSubst varSubst) {
        DataType[] xInputs = new DataType[inputs.length];
        for (int i = 0; i < xInputs.length; i++) {
            xInputs[i] = varSubst.apply(inputs[i]).apply(
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
        }
        return new Either.Left<Type, Integer>(new ValueDepFunType(xInputs, computer));
    }

}
