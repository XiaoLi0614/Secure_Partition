package graph.spec.type;


import graph.spec.visitor.Visitor;

public class BoolType implements Type {
   private static BoolType ourInstance = new BoolType();

   public static BoolType getInstance() {
      return ourInstance;
   }

   private BoolType() {
   }

   public <R> R accept(Visitor.TypeVisitor<R> v) {
         return v.visit(this);
      }

}

