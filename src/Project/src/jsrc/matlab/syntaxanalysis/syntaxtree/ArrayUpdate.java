//
// Generated by JTB 1.3.2
//

package jsrc.matlab.syntaxanalysis.syntaxtree;

/**
 * Grammar production:
 * f0 -> IndexedArray()
 * f1 -> "="
 * f2 -> Expression()
 * f3 -> [ ";" ]
 */
public class ArrayUpdate implements Node {
   public IndexedArray f0;
   public NodeToken f1;
   public Expression f2;
   public NodeOptional f3;

   public ArrayUpdate(IndexedArray n0, NodeToken n1, Expression n2, NodeOptional n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
   }

   public ArrayUpdate(IndexedArray n0, Expression n1, NodeOptional n2) {
      f0 = n0;
      f1 = new NodeToken("=");
      f2 = n1;
      f3 = n2;
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

