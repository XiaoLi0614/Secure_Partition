package graph.lang.type;

import graph.lang.visitor.Visitor;

public class TypeVar implements Type {

   public TypeVar() {
   }

   public <R> R accept(Visitor.TypeVisitor<R> v) {
       return v.visit(this);
   }

}

