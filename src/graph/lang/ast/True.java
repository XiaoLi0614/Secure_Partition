package graph.lang.ast;

import graph.lang.visitor.Visitor;

public class True extends ZOp {
   private static True ourInstance = new True();

   public static True getInstance() {
      return ourInstance;
   }

   private True() {
   }

   public <R> R accept(Visitor.ExpVisitor.ZOpVisitor<R> v) {
      return v.visit(this);
   }

   @Override
   public String toString() {
      return "true";
   }

}
