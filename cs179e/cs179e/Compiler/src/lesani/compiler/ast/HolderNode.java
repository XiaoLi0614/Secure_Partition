package lesani.compiler.ast;


// This class just wraps classes that are not Nodes but should be returned as the
// return in visit methods in ASTBuilder.
public class HolderNode<T> implements Node {
    public T o;

    public HolderNode(T o) {
        this.o = o;
    }

    public T get() {
        return o;
    }
}
