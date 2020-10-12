//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.syntaxtree;

/**
 * Grammar production:
 * f0 -> "Math"
 * f1 -> "."
 * f2 -> "sin"
 * f3 -> "("
 * f4 -> Expression()
 * f5 -> ")"
 */
public class SinExpression implements Node {
   public NodeToken f0;
   public NodeToken f1;
   public NodeToken f2;
   public NodeToken f3;
   public Expression f4;
   public NodeToken f5;

   public SinExpression(NodeToken n0, NodeToken n1, NodeToken n2, NodeToken n3, Expression n4, NodeToken n5) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
   }

   public SinExpression(Expression n0) {
      f0 = new NodeToken("Math");
      f1 = new NodeToken(".");
      f2 = new NodeToken("sin");
      f3 = new NodeToken("(");
      f4 = n0;
      f5 = new NodeToken(")");
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

