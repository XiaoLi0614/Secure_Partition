//
// Generated by JTB 1.3.2
//

package jsrc.matlab.syntaxanalysis.syntaxtree;

/**
 * Grammar production:
 * f0 -> RelationalExp()
 * f1 -> ( ElementWiseAndExpRest() )*
 */
public class ElementWiseAndExp implements Node {
   public RelationalExp f0;
   public NodeListOptional f1;

   public ElementWiseAndExp(RelationalExp n0, NodeListOptional n1) {
      f0 = n0;
      f1 = n1;
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
