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
 * f0 -> IntegerLiteral()
 *       | TrueLiteral()
 *       | FalseLiteral()
 *       | Identifier()
 *       | ThisExpression()
 *       | ArrayAllocationExpression()
 *       | AllocationExpression()
 *       | NotExpression()
 *       | BracketExpression()
 */
public class PrimaryExpression implements Node {
	public parsing.ast.tree.Expression outExpression;


   public NodeChoice f0;

   public PrimaryExpression(NodeChoice n0) {
      f0 = n0;
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
