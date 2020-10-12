package graph.spec.ast;

import graph.spec.visitor.Visitor;

public class PVar extends Var {
   private static PVar ourInstance = new PVar();

   public static PVar getInstance() {
      return ourInstance;
   }

   private PVar() {
   }

   public <R> R accept(Visitor.ExpVisitor.VarVisitor<R> v) {
      return v.visit(this);
   }

}
