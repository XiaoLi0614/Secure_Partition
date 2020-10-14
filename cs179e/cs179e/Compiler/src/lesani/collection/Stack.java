package lesani.collection;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * User: Mohsen's Desktop
 * Date: Aug 29, 2009
 */

public class Stack<T> {
	private LinkedList<T> linkedList = new LinkedList<T>();

	public T peek() {
		return linkedList.peek();
	}

	public T pop() {
		return linkedList.pop();
	}

	public void push(T t) {
		linkedList.push(t);
	}

	public Iterator<T> topDownIterator() {
		return linkedList.iterator();
	}
}
