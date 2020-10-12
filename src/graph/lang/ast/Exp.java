package graph.lang.ast;

import graph.lang.visitor.Visitor;

public abstract class Exp implements java.io.Serializable {

   public Exp bound;
   public Exp boundConstraints;

   public abstract <R> R accept(Visitor.ExpVisitor<R> v);

   public abstract String toString();

}
