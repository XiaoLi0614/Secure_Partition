package graph.spec.ast;

import graph.spec.visitor.Visitor;

public interface Exp {

   <R> R accept(Visitor.ExpVisitor<R> v);

}
