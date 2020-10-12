package lesani.compiler.ast;

public class NodeSequence implements Node {
    public Node[] nodes;

    public NodeSequence(Node[] nodes) {
        this.nodes = nodes;
    }
}
