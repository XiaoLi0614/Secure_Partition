package graph.lang.ast;

import graph.lang.visitor.Visitor;

public class Inf extends ZOp {
   private static Inf ourInstance = new Inf();

   public static Inf getInstance() {
      return ourInstance;
   }

   private Inf() {
   }

   public <R> R accept(Visitor.ExpVisitor.ZOpVisitor<R> v) {
      return v.visit(this);
   }

   @Override
   public String toString() {
      return "Inf";
   }

}
