package jsrc.matlab.typeinference.type;

import lesani.collection.xtolook.Either;
import lesani.collection.func.Fun;
import lesani.compiler.typing.Type;
import lesani.compiler.typing.substitution.VarSubst;

import java.util.LinkedList;
import java.util.List;

public class IntersectionFunType implements FunType {

    public List<SingleFunType> types;

    public IntersectionFunType() {
        types = new LinkedList<SingleFunType>();
    }

    public IntersectionFunType(List<SingleFunType> types) {
        this.types = types;
    }

    public IntersectionFunType(SingleFunType type1, SingleFunType type2) {
        types = new LinkedList<SingleFunType>();
        types.add(type1);
        types.add(type2);
    }

    public void add(SingleFunType singleFunType) {
        types.add(singleFunType);
    }

    public boolean contains(TypeVar typeVar) {
        for (SingleFunType type : types) {
            if (type.contains(typeVar))
                return true;
        }
        return false;
    }

    public Either<Type, Integer> apply(VarSubst varSubst) {
        List<SingleFunType> xTypes = new LinkedList<SingleFunType>();
        for (SingleFunType type : types) {
            xTypes.add(varSubst.apply(type).apply(
                new Fun<Type, SingleFunType>() {
                    public SingleFunType apply(Type input) {
                        return (SingleFunType)input;
                    }
                },
                new Fun<Integer, SingleFunType>() {
                    public SingleFunType apply(Integer input) {
                        throw new RuntimeException();
                    }
                }
            ));
        }
        return new Either.Left<Type, Integer>(new IntersectionFunType(xTypes));
    }
}
