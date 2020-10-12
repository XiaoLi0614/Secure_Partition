package graph.spec.ast;


import graph.spec.type.IntType;
import graph.spec.type.Type;
import graph.spec.visitor.Visitor;

public class Min extends BOp {
   private static Min ourInstance = new Min();

   public static Min getInstance() {
      return ourInstance;
   }

   private Min() {
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
