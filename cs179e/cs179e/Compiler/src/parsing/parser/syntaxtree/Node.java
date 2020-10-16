//
// Generated by JTB 1.3.2
//

package parsing.parser.syntaxtree;

import parsing.parser.visitor.GJNoArguVisitor;
import parsing.parser.visitor.GJVisitor;
import parsing.parser.visitor.GJVoidVisitor;
import parsing.parser.visitor.Visitor;

/**
 * The interface which all syntax tree classes must implement.
 */
public interface Node extends java.io.Serializable {
   public void accept(Visitor v);
   public <R,A> R accept(GJVisitor<R,A> v, A argu);
   public <R> R accept(GJNoArguVisitor<R> v);
   public <A> void accept(GJVoidVisitor<A> v, A argu);
}
