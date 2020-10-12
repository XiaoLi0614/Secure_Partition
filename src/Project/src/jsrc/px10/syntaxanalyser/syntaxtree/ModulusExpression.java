//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.syntaxtree;

/**
 * Grammar production:
 * f0 -> "%"
 * f1 -> MapExpression()
 */
public class ModulusExpression implements Node {
   public NodeToken f0;
   public MapExpression f1;

   public ModulusExpression(NodeToken n0, MapExpression n1) {
      f0 = n0;
      f1 = n1;
   }

   public ModulusExpression(MapExpression n0) {
      f0 = new NodeToken("%");
      f1 = n0;
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

