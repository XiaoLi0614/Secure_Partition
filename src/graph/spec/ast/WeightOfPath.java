package graph.spec.ast;

import graph.spec.type.IntType;
import graph.spec.type.PathType;
import graph.spec.type.Type;
import graph.spec.visitor.Visitor;

public class WeightOfPath extends UOp {
   private static WeightOfPath ourInstance = new WeightOfPath(IntType.getInstance());

   public static WeightOfPath getInstance() {
      return ourInstance;
   }

   public Type type; // type of the cap, either int or float.
   public WeightOfPath(Type type) {
      this.type = type;
   }

   public <R> R accept(Visitor.ExpVisitor.OpVisitor.UOpVisitor<R> v) {
      return v.visit(this);
   }

   @Override
   public Sig getSig() {
      return new Sig(
            new Type[]{PathType.getInstance()},
            type);
   }

}
