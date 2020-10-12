package graph.spec.ast;

import graph.spec.visitor.Visitor;

public class VVar extends Var {
   private static VVar ourInstance = new VVar();

   public static VVar getInstance() {
      return ourInstance;
   }

   private VVar() {
   }

   public <R> R accept(Visitor.ExpVisitor.VarVisitor<R> v) {
      return v.visit(this);
   }
}
