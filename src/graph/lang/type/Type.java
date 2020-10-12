package graph.lang.type;

import graph.lang.visitor.Visitor;

public interface Type extends java.io.Serializable {

   <R> R accept(Visitor.TypeVisitor<R> v);

}
