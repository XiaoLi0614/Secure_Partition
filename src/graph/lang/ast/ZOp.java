package graph.lang.ast;


import graph.lang.visitor.Visitor;

public abstract class ZOp extends Exp {
   public <R> R accept(Visitor.ExpVisitor<R> v) {
       return v.visit(this);
   }

   public abstract <R> R accept(Visitor.ExpVisitor.ZOpVisitor<R> v);


}
