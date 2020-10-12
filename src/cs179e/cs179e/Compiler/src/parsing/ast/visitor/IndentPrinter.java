package parsing.ast.visitor;

import parsing.ast.tree.CompilationUnit;
import parsing.ast.tree.List;
import parsing.ast.tree.Node;
import parsing.ast.tree.Token;

import java.util.Iterator;


/**
 * User: Mohsen's Desktop
 * Date: Aug 27, 2009
 */

public class IndentPrinter extends DepthFirstSearchVisitor {

	private CompilationUnit compilationUnit;
	private String out = "";

	static class Counter {
		private int c;
		Counter(int c) { this.c = c; }
		public int inc() { c++; return get(); }
		public int dec() { c--; return get(); }
		private int get() {	return c; }
	}

	final Counter indentCount = new Counter(0);
	public IndentPrinter(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;

		setPre(
			new Callable() {
				public void run(Node node) {
					print(node.getClass().getSimpleName() + "\n");
					indentCount.inc();
				}
			}
		);
		setIn(new InCallable() {
			public void run(Node node, int no) {
				indentPrint(node.getClass().getFields()[no].getName() + ": ");
			}
		});
		setPost(
			new Callable() {
				public void run(Node node) {
					indentCount.dec();
				}
			}
		);
	}

	private void print(String s) {
		out += s;
	}
	private void indentPrint(String s) {
		int c = indentCount.get();
		for (int i = 0; i < c; i++) {
			print("\t");
		}
		print(s);
	}

	public String print() {
		compilationUnit.accept(this);
		return out;
	}

	//------------------------------------------------------------
	// Visits

	public void visit(List list) {
		pre.run(list);
		Iterator iterator = list.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Node node = (Node)iterator.next();
			indentPrint("[" + i + "]: ");
			node.accept(this);
			i++;
		}
		post.run(list);
	}

	public void visit(Token token) {
		print(" \"" + token.toString() + "\"\n");
	}

}
