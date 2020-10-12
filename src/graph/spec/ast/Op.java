package graph.spec.ast;


import graph.spec.visitor.Visitor;

public interface Op {
   <R> R accept(Visitor.ExpVisitor.OpVisitor<R> v);

}
