package graph.spec.ast;

import graph.spec.type.*;
import graph.spec.visitor.Visitor;

public class VId extends UOp {
   private static VId ourInstance = new VId();

   public static VId getInstance() {
      return ourInstance;
   }

   private VId() {

   }

   public <R> R accept(Visitor.ExpVisitor.OpVisitor.UOpVisitor<R> v) {
      return v.visit(this);
   }

   @Override
   public Sig getSig() {
      return new Sig(
            new Type[]{VertexType.getInstance()},
            VIdType.getInstance());
   }

}
