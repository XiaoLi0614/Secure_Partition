import x10.util.Set;

public interface Reducer[TI, T2] {
    public def reduce(Set[TI]): T2;
}
