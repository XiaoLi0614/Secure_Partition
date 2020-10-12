//
// Generated by JTB 1.3.2
//

package parsing.parser.syntaxtree;

import parsing.parser.visitor.GJNoArguVisitor;
import parsing.parser.visitor.GJVisitor;
import parsing.parser.visitor.GJVoidVisitor;
import parsing.parser.visitor.Visitor;

/**
 * Grammar production:
 * f0 -> PrimaryExpression()
 * f1 -> "."
 * f2 -> "length"
 */
public class ArrayLength implements Node {
	public parsing.ast.tree.ArrayLength outArrayLength;

   public PrimaryExpression f0;
   public NodeToken f1;
   public NodeToken f2;

   public ArrayLength(PrimaryExpression n0, NodeToken n1, NodeToken n2) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
   }

   public ArrayLength(PrimaryExpression n0) {
      f0 = n0;
      f1 = new NodeToken(".");
      f2 = new NodeToken("length");
   }

   public void accept(Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}

