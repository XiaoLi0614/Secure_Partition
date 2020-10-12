//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.syntaxtree;

/**
 * Grammar production:
 * f0 -> ( "final" )?
 * f1 -> Type()
 * f2 -> Identifier()
 */
public class FinalFormalParameter implements Node {
   public NodeOptional f0;
   public Type f1;
   public Identifier f2;

   public FinalFormalParameter(NodeOptional n0, Type n1, Identifier n2) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
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

