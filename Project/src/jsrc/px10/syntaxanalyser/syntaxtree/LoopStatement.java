//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.syntaxtree;

/**
 * Grammar production:
 * f0 -> LoopQualifier()
 * f1 -> "("
 * f2 -> PointType()
 * f3 -> ExplodedSpecification()
 * f4 -> ":"
 * f5 -> Expression()
 * f6 -> ")"
 * f7 -> Statement()
 */
public class LoopStatement implements Node {
   public LoopQualifier f0;
   public NodeToken f1;
   public PointType f2;
   public ExplodedSpecification f3;
   public NodeToken f4;
   public Expression f5;
   public NodeToken f6;
   public Statement f7;

   public LoopStatement(LoopQualifier n0, NodeToken n1, PointType n2, ExplodedSpecification n3, NodeToken n4, Expression n5, NodeToken n6, Statement n7) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
      f6 = n6;
      f7 = n7;
   }

   public LoopStatement(LoopQualifier n0, PointType n1, ExplodedSpecification n2, Expression n3, Statement n4) {
      f0 = n0;
      f1 = new NodeToken("(");
      f2 = n1;
      f3 = n2;
      f4 = new NodeToken(":");
      f5 = n3;
      f6 = new NodeToken(")");
      f7 = n4;
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
