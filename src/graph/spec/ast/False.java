package graph.spec.ast;


import graph.spec.visitor.Visitor;

public class False implements Cond {
   private static False ourInstance = new False();

   public static False getInstance() {
      return ourInstance;
   }

   private False() {
   }

   public <R> R accept(Visitor<R> v) {
      return v.visit(this);
   }

   @Override
   public String toString() {
      return "true";
   }

}
