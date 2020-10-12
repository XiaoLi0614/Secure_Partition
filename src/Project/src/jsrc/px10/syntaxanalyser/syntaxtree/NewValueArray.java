//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.syntaxtree;

/**
 * Grammar production:
 * f0 -> "new"
 * f1 -> NonArrayType()
 * f2 -> "value"
 * f3 -> "["
 * f4 -> Identifier()
 * f5 -> "]"
 * f6 -> ArrayInitializer()
 */
public class NewValueArray implements Node {
   public NodeToken f0;
   public NonArrayType f1;
   public NodeToken f2;
   public NodeToken f3;
   public Identifier f4;
   public NodeToken f5;
   public ArrayInitializer f6;

   public NewValueArray(NodeToken n0, NonArrayType n1, NodeToken n2, NodeToken n3, Identifier n4, NodeToken n5, ArrayInitializer n6) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
      f6 = n6;
   }

   public NewValueArray(NonArrayType n0, Identifier n1, ArrayInitializer n2) {
      f0 = new NodeToken("new");
      f1 = n0;
      f2 = new NodeToken("value");
      f3 = new NodeToken("[");
      f4 = n1;
      f5 = new NodeToken("]");
      f6 = n2;
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

