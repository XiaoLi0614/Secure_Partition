package graph.spec.ast;


import graph.spec.visitor.Visitor;

public class And implements Cond {
   public Cond cond1;
   public Cond cond2;

   public And(Cond cond1, Cond cond2) {
      this.cond1 = cond1;
      this.cond2 = cond2;
   }

   public <R> R accept(Visitor<R> v) {
          return v.visit(this);
      }

}

