//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.syntaxtree;

/**
 * Grammar production:
 * f0 -> NonArrayType()
 * f1 -> "["
 * f2 -> ":"
 * f3 -> RankEquation()
 * f4 -> ( DistributionEquation() )?
 * f5 -> "]"
 */
public class UpdatableArrayType implements Node {
   public NonArrayType f0;
   public NodeToken f1;
   public NodeToken f2;
   public RankEquation f3;
   public NodeOptional f4;
   public NodeToken f5;

   public UpdatableArrayType(NonArrayType n0, NodeToken n1, NodeToken n2, RankEquation n3, NodeOptional n4, NodeToken n5) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
   }

   public UpdatableArrayType(NonArrayType n0, RankEquation n1, NodeOptional n2) {
      f0 = n0;
      f1 = new NodeToken("[");
      f2 = new NodeToken(":");
      f3 = n1;
      f4 = n2;
      f5 = new NodeToken("]");
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

