package graph.lang.type;

import graph.lang.visitor.Visitor;

public class FloatType implements Type {
   private static FloatType ourInstance = new FloatType();

   public static FloatType getInstance() {
      return ourInstance;
   }

   private FloatType() {
   }

   public <R> R accept(Visitor.TypeVisitor<R> v) {
       return v.visit(this);
   }
}

