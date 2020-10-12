package graph.spec.ast;


import graph.spec.visitor.Visitor;

public class OpApp implements Exp {
   public Op op;
   public Exp arg;

   public OpApp(Op op, Exp arg) {
      this.op = op;
      this.arg = arg;
   }

   public <R> R accept(Visitor.ExpVisitor<R> v) {
          return v.visit(this);
      }
}


