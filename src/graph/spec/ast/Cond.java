package graph.spec.ast;


import graph.spec.visitor.Visitor;

public interface Cond {

   <R> R accept(Visitor<R> v);

}
