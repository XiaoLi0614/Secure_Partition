package graph.spec.ast;

import graph.spec.type.PathType;
import graph.spec.type.Type;
import graph.spec.type.VertexType;
import graph.spec.visitor.Visitor;

public class PenultimateOfPath extends UOp {
   private static PenultimateOfPath ourInstance = new PenultimateOfPath();

   public static PenultimateOfPath getInstance() {
      return ourInstance;
   }

   private PenultimateOfPath() {
   }

   public <R> R accept(Visitor.ExpVisitor.OpVisitor.UOpVisitor<R> v) {
      return v.visit(this);
   }

   @Override
   public Sig getSig() {
      return new Sig(
            new Type[]{PathType.getInstance()},
            VertexType.getInstance());
   }

}
