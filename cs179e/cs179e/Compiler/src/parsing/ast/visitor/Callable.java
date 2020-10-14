package parsing.ast.visitor;

import parsing.ast.tree.Node;

/**
 * User: Mohsen's Desktop
 * Date: Aug 27, 2009
 */

public interface Callable {
	public void run(Node node);
}
