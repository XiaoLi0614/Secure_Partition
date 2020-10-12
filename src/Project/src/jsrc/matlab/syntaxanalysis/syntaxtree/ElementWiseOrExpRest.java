//
// Generated by JTB 1.3.2
//

package jsrc.matlab.syntaxanalysis.syntaxtree;

/**
 * Grammar production:
 * f0 -> "|"
 * f1 -> ElementWiseAndExp()
 */
public class ElementWiseOrExpRest implements Node {
   public NodeToken f0;
   public ElementWiseAndExp f1;

   public ElementWiseOrExpRest(NodeToken n0, ElementWiseAndExp n1) {
      f0 = n0;
      f1 = n1;
   }

   public ElementWiseOrExpRest(ElementWiseAndExp n0) {
      f0 = new NodeToken("|");
      f1 = n0;
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

