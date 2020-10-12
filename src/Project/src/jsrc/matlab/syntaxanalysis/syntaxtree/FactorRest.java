//
// Generated by JTB 1.3.2
//

package jsrc.matlab.syntaxanalysis.syntaxtree;

/**
 * Grammar production:
 * f0 -> ( "*" | ".*" | "/" | "./" )
 * f1 -> UnaryExp()
 */
public class FactorRest implements Node {
   public NodeChoice f0;
   public UnaryExp f1;

   public FactorRest(NodeChoice n0, UnaryExp n1) {
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

