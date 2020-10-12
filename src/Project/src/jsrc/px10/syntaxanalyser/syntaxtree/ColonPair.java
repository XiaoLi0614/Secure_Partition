//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.syntaxtree;

/**
 * Grammar production:
 * f0 -> Expression()
 * f1 -> ":"
 * f2 -> Expression()
 */
public class ColonPair implements Node {
   public Expression f0;
   public NodeToken f1;
   public Expression f2;

   public ColonPair(Expression n0, NodeToken n1, Expression n2) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
   }

   public ColonPair(Expression n0, Expression n1) {
      f0 = n0;
      f1 = new NodeToken(":");
      f2 = n1;
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

