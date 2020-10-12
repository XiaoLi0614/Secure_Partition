package graph.spec.ast;

import graph.spec.visitor.Visitor;

public abstract class Var implements Exp {
   public <R> R accept(Visitor.ExpVisitor<R> v) {
          return v.visit(this);
      }

   public abstract <R> R accept(Visitor.ExpVisitor.VarVisitor<R> v);

}
