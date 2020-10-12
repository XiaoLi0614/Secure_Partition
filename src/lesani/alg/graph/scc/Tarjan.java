package lesani.alg.graph.scc;

import lesani.collection.Pair;

import java.util.*;

public class Tarjan {

    public static List<Set<Node>> allSCCs(final Graph graph) {
        final Node initNode = new Node() {};
        Graph graphPrime = new Graph() {
            public Node[] adjacents(Node node) {
                if (node == initNode)
                    return graph.nodes();
                else
                    return graph.adjacents(node);
            }

            public Node[] nodes() {
                return graph.nodes();
                // Should add the initNode but
                // This is not needed for Tarjan.
            }
        };
        LinkedList<Set<Node>> sccs = tarjan(graphPrime, initNode);
        sccs.removeLast();
        return sccs;
    }

    public static LinkedList<Set<Node>> tarjan(Graph graph, Node v) {
        return new Tarjan().tarjanHorse(graph, v);
    }

    private int index = 0;
    private Stack<Node> stack = new Stack<Node>();
    private LinkedList<Set<Node>> SCC = new LinkedList<Set<Node>>();
    private HashMap<Node, Pair<Integer, Integer>> nodeInfo = new HashMap<Node, Pair<Integer, Integer>>();

    private LinkedList<Set<Node>> tarjanHorse(Graph graph, Node node) {
        index++;
        int nodeIndex = index;
        int nodeLowLink = index;
        Pair<Integer, Integer> nodePair = new Pair<Integer, Integer>(nodeIndex, nodeLowLink);
        nodeInfo.put(node, nodePair);

        stack.push(node);

        Node[] nodes = graph.adjacents(node);
        for (Node n : nodes) {
            Pair<Integer, Integer> nPair = nodeInfo.get(n);
            int nIndex = (nPair != null) ? nPair._1() : -1;
            int nLowLink = (nPair != null) ? nPair._2() : Integer.MAX_VALUE;
            if (nIndex == -1) {
                tarjanHorse(graph, n);
                nodeLowLink = Math.min(nodeLowLink, nLowLink);
            } else if (stack.contains(n))
                nodeLowLink = Math.min(nodeLowLink, nIndex);
            nodePair.set_2(nodeLowLink);
        }

        if (nodeLowLink == nodeIndex) {
            Node n;
            Set<Node> component = new HashSet<Node>();
            do {
                n = stack.remove(0);
                component.add(n);
            } while(n != node);
            SCC.add(component);
        }
        return SCC;
    }
}


