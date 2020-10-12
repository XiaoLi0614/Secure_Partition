package graph.spec.ast;

import graph.spec.type.PathType;
import graph.spec.type.Type;
import graph.spec.type.VertexType;
import graph.spec.visitor.Visitor;

public class SrcOfPath extends UOp {
   private static SrcOfPath ourInstance = new SrcOfPath();

   public static SrcOfPath getInstance() {
      return ourInstance;
   }

   private SrcOfPath() {
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
