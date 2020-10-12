package graph.lang.type;

import graph.lang.visitor.Visitor;

public class DirType implements Type {
   private static DirType ourInstance = new DirType();

   public static DirType getInstance() {
      return ourInstance;
   }

   private DirType() {
   }

   public <R> R accept(Visitor.TypeVisitor<R> v) {
       return v.visit(this);
   }
}

