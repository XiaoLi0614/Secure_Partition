package graph.spec.type;


import graph.spec.visitor.Visitor;

public class EdgeType implements Type {
   private static EdgeType ourInstance = new EdgeType();

   public static EdgeType getInstance() {
      return ourInstance;
   }

   private EdgeType() {
   }
   public <R> R accept(Visitor.TypeVisitor<R> v) {
       return v.visit(this);
   }
}
