package graph.spec.type;


import graph.spec.visitor.Visitor;

public class IntType implements Type {
   private static IntType ourInstance = new IntType();

   public static IntType getInstance() {
      return ourInstance;
   }

   private IntType() {
   }

   public <R> R accept(Visitor.TypeVisitor<R> v) {
       return v.visit(this);
   }
}

