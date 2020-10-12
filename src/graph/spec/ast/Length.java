package graph.spec.ast;

import graph.spec.type.IntType;
import graph.spec.type.PathType;
import graph.spec.type.Type;
import graph.spec.visitor.Visitor;

public class Length extends UOp {
   private static Length ourInstance = new Length();

   public static Length getInstance() {
      return ourInstance;
   }

   private Length() {

   }

   public <R> R accept(Visitor.ExpVisitor.OpVisitor.UOpVisitor<R> v) {
      return v.visit(this);
   }

   @Override
   public Sig getSig() {
      return new Sig(
            new Type[]{PathType.getInstance()},
            IntType.getInstance());
   }

}
