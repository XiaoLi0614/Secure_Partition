package graph.spec.ast;


import graph.spec.type.IntType;
import graph.spec.type.Type;
import graph.spec.visitor.Visitor;

public class Max extends BOp {
   private static Max ourInstance = new Max();

   public static Max getInstance() {
      return ourInstance;
   }

   private Max() {
   }

   public <R> R accept(Visitor.ExpVisitor.OpVisitor.BOpVisitor<R> v) {
      return v.visit(this);
   }

//   @Override
//   public Sig getSig() {
//      return new Sig(
//            new Type[]{IntType.getInstance(), IntType.getInstance()},
//            IntType.getInstance());
//   }

}
