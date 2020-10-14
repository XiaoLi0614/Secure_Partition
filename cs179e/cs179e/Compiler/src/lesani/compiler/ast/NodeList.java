package lesani.compiler.ast;

import lesani.compiler.ast.Node;


import java.util.*;

public class NodeList<T extends Node> implements Node {
	private Vector<T> nodes;

	public NodeList() { nodes = new Vector<T>(); }

	public void addToBeg(T n) { nodes.add(0, n); }
	public void add(T n) { nodes.addElement(n); }
	public void add(NodeList<T> nodeList) {
        Iterator<T> iterator = nodeList.iterator();
        while (iterator.hasNext()) {
            T node = iterator.next();
            nodes.addElement(node);
        }
    }

	public Iterator<T> iterator() { return nodes.iterator(); }
	public T elementAt(int i)  { return nodes.elementAt(i); }
	public int size() { return nodes.size(); }

}