//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.syntaxtree;

/**
 * Grammar production:
 * f0 -> IntegerLiteral()
 *       | LongLiteral()
 *       | HexLiteral()
 *       | FloatingPointLiteral()
 *       | StringLiteral()
 *       | True()
 *       | False()
 *       | HereLiteral()
 *       | PlaceFirstPlace()
 *       | PlaceMaxPlaces()
 *       | DistUnique()
 *       | JavaIntegerSize()
 */
public class Literal implements Node {
   public NodeChoice f0;

   public Literal(NodeChoice n0) {
      f0 = n0;
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

