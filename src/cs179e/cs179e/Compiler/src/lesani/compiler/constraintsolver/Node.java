package lesani.compiler.constraintsolver;


import java.util.HashSet;
import java.util.Set;

public class Node<Var, T> {
    Var var;
    Set<T> values;

    public Node() {
    }

    public Node(Var var, Set<T> values) {
        this.var = var;
        this.values = values;
    }

    public Node(Var var) {
        this.var = var;
        this.values = new HashSet<T>();
    }

    HashSet<Node<Var, T>> neighbors = new HashSet<Node<Var, T>>();
    public void addNeighbor(Node<Var, T> node) {
        neighbors.add(node);
    }

    public boolean addValues(Set<T> values) {
        return this.values.addAll(values);
    }
}
