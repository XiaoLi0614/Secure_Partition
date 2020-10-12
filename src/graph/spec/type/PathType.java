package graph.spec.type;


import graph.spec.visitor.Visitor;

public class PathType implements Type {
   private static PathType ourInstance = new PathType();

   public static PathType getInstance() {
      return ourInstance;
   }

   private PathType() {
   }

   public <R> R accept(Visitor.TypeVisitor<R> v) {
         return v.visit(this);
      }

}

