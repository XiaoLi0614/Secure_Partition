package graph.lang.ast;

import graph.lang.visitor.Visitor;

public class Epsilon extends ZOp {
   private static Epsilon ourInstance = new Epsilon();

   public static Epsilon getInstance() {
      return ourInstance;
   }

   private Epsilon() {
   }

   public <R> R accept(Visitor.ExpVisitor.ZOpVisitor<R> v) {
      return v.visit(this);
   }

   @Override
   public String toString() {
      return "true";
   }

}
