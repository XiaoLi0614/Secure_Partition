package graph.lang.type;

import graph.lang.visitor.Visitor;

public class VoidType implements Type {
   private static VoidType ourInstance = new VoidType();

   public static VoidType getInstance() {
      return ourInstance;
   }

   private VoidType() {
   }

   public <R> R accept(Visitor.TypeVisitor<R> v) {
       return v.visit(this);
   }
}

