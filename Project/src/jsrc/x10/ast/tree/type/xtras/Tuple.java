package jsrc.x10.ast.tree.type.xtras;

import jsrc.x10.ast.tree.type.Type;


public class Tuple {
    Type[] types;

    public Tuple(Type... types) {
        this.types = types;
    }

    public Type get(int i) {
        return types[i];
    }
}
