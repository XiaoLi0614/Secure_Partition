package graph.lang.type;

import graph.lang.visitor.Visitor;

public class VIdType implements Type {
   private static VIdType ourInstance = new VIdType();

   public static VIdType getInstance() {
      return ourInstance;
   }

   private VIdType() {
   }

   public <R> R accept(Visitor.TypeVisitor<R> v) {
       return v.visit(this);
   }
}

