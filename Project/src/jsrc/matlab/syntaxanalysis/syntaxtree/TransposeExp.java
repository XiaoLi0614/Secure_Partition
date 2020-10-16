//
// Generated by JTB 1.3.2
//

package jsrc.matlab.syntaxanalysis.syntaxtree;

/**
 * Grammar production:
 * f0 -> AtomExp()
 * f1 -> "'"
 */
public class TransposeExp implements Node {
   public AtomExp f0;
   public NodeToken f1;

   public TransposeExp(AtomExp n0, NodeToken n1) {
      f0 = n0;
      f1 = n1;
   }

   public TransposeExp(AtomExp n0) {
      f0 = n0;
      f1 = new NodeToken("'");
   }

   public void accept(jsrc.matlab.syntaxanalysis.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(jsrc.matlab.syntaxanalysis.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(jsrc.matlab.syntaxanalysis.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(jsrc.matlab.syntaxanalysis.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}
