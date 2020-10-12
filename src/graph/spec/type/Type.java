package graph.spec.type;


import graph.spec.visitor.Visitor;

public interface Type {

   <R> R accept(Visitor.TypeVisitor<R> v);

}
