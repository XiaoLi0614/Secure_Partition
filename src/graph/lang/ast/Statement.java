package graph.lang.ast;

import graph.lang.visitor.Visitor;

public abstract class Statement implements java.io.Serializable {
//   Option<Type> type;
   public abstract <R> R accept(Visitor.StVisitor<R> v);

   public abstract String toString();

}
