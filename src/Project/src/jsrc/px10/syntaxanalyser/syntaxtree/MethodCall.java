//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.syntaxtree;

/**
 * Grammar production:
 * f0 -> Identifier()
 * f1 -> "("
 * f2 -> ( ExpressionList() )?
 * f3 -> ")"
 */
public class MethodCall implements Node {
   public Identifier f0;
   public NodeToken f1;
   public NodeOptional f2;
   public NodeToken f3;

   public MethodCall(Identifier n0, NodeToken n1, NodeOptional n2, NodeToken n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
   }

   public MethodCall(Identifier n0, NodeOptional n1) {
      f0 = n0;
      f1 = new NodeToken("(");
      f2 = n1;
      f3 = new NodeToken(")");
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

