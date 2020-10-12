package graph.spec.ast;

import graph.spec.visitor.Visitor;

public class Num implements Exp {
   public int n;

   public Num(int n) {
      this.n = n;
   }

   public <R> R accept(Visitor.ExpVisitor<R> v) {
      return v.visit(this);
   }
}
