package lesani.alg.graph.scc;

public interface Graph {
    Node[] adjacents(Node node);
    Node[] nodes();
/*
    Node getNode(int i) {
        return nodes()(i);
    }
*/
//    boolean edge(Node n1, Node n2);
}
