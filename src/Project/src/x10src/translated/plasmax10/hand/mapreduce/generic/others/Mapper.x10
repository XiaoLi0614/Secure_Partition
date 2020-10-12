import x10.util.Set;
import x10.util.Pair;

public interface Mapper[T1, KI, TI] {
    public def map(T1): Set[Pair[KI, TI]];
}

