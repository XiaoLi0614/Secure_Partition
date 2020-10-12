package graph.spec.ast;


import graph.spec.type.IntType;
import graph.spec.type.Type;
import graph.spec.visitor.Visitor;

public abstract class BOp implements Op {

   public <R> R accept(Visitor.ExpVisitor.OpVisitor<R> v) {
      return v.visit(this);
   }

   public abstract <R> R accept(Visitor.ExpVisitor.OpVisitor.BOpVisitor<R> v);

}
