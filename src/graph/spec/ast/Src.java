package graph.spec.ast;

import graph.spec.visitor.Visitor;

public class Src extends Var {
   private static Src ourInstance = new Src();

   public static Src getInstance() {
      return ourInstance;
   }

   private Src() {
   }

   public <R> R accept(Visitor.ExpVisitor.VarVisitor<R> v) {
      return v.visit(this);
   }

}
