package graph.spec.ast;


import graph.spec.visitor.Visitor;

public class True implements Cond {
   private static True ourInstance = new True();

   public static True getInstance() {
      return ourInstance;
   }

   private True() {
   }

   public <R> R accept(Visitor<R> v) {
      return v.visit(this);
   }

   @Override
   public String toString() {
      return "true";
   }

}
