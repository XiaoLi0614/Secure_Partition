package graph.spec.ast;


import graph.spec.visitor.Visitor;

public class Eq implements Cond {
   public Exp arg1;
   public Exp arg2;

   public Eq(Exp arg1, Exp arg2) {
      this.arg1 = arg1;
      this.arg2 = arg2;
   }

   public <R> R accept(Visitor<R> v) {
          return v.visit(this);
      }

}

