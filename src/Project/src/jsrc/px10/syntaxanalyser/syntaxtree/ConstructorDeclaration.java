//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.syntaxtree;

/**
 * Grammar production:
 * f0 -> "public"
 * f1 -> Identifier()
 * f2 -> "("
 * f3 -> ( FormalParameterList() )?
 * f4 -> ")"
 * f5 -> Block()
 */
public class ConstructorDeclaration implements Node {
   public NodeToken f0;
   public Identifier f1;
   public NodeToken f2;
   public NodeOptional f3;
   public NodeToken f4;
   public Block f5;

   public ConstructorDeclaration(NodeToken n0, Identifier n1, NodeToken n2, NodeOptional n3, NodeToken n4, Block n5) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
   }

   public ConstructorDeclaration(Identifier n0, NodeOptional n1, Block n2) {
      f0 = new NodeToken("public");
      f1 = n0;
      f2 = new NodeToken("(");
      f3 = n1;
      f4 = new NodeToken(")");
      f5 = n2;
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

