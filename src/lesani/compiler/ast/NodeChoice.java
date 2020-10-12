package lesani.compiler.ast;


public class NodeChoice implements Node {

    public NodeChoice(Node node, int whichChoice) {
       choice = node;
       which = whichChoice;
    }

    public Node choice;
    public int which;

}
