//
// Generated by JTB 1.3.2
//

package jsrc.matlab.syntaxanalysis.syntaxtree;

/**
 * Grammar production:
 * f0 -> "["
 * f1 -> IdentifierList()
 * f2 -> "]"
 * f3 -> "="
 * f4 -> Call()
 * f5 -> [ ";" ]
 */
public class CallAndMultipleAssignmentSt implements Node {
   public NodeToken f0;
   public IdentifierList f1;
   public NodeToken f2;
   public NodeToken f3;
   public Call f4;
   public NodeOptional f5;

   public CallAndMultipleAssignmentSt(NodeToken n0, IdentifierList n1, NodeToken n2, NodeToken n3, Call n4, NodeOptional n5) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
   }

   public CallAndMultipleAssignmentSt(IdentifierList n0, Call n1, NodeOptional n2) {
      f0 = new NodeToken("[");
      f1 = n0;
      f2 = new NodeToken("]");
      f3 = new NodeToken("=");
      f4 = n1;
      f5 = n2;
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
