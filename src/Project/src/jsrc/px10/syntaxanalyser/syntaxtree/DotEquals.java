//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.syntaxtree;

/**
 * Grammar production:
 * f0 -> "."
 * f1 -> "equals"
 * f2 -> "("
 * f3 -> Expression()
 * f4 -> ")"
 */
public class DotEquals implements Node {
   public NodeToken f0;
   public NodeToken f1;
   public NodeToken f2;
   public Expression f3;
   public NodeToken f4;

   public DotEquals(NodeToken n0, NodeToken n1, NodeToken n2, Expression n3, NodeToken n4) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
   }

   public DotEquals(Expression n0) {
      f0 = new NodeToken(".");
      f1 = new NodeToken("equals");
      f2 = new NodeToken("(");
      f3 = n0;
      f4 = new NodeToken(")");
   }

   public void accept(jsrc.px10.syntaxanalyser.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(jsrc.px10.syntaxanalyser.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(jsrc.px10.syntaxanalyser.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(jsrc.px10.syntaxanalyser.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}

