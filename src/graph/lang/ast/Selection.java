package graph.lang.ast;

import graph.lang.visitor.CVCIPrinter;
import graph.lang.visitor.Visitor;

import java.util.Arrays;
import java.util.HashMap;

public class Selection extends Exp {

//   public Fun predicate;
   public Exp predicate;
   public Exp relation;

   public Selection(Exp pred, Exp rel)
   {
      predicate = pred;
      relation = rel;
   }

   public <R> R accept(Visitor.ExpVisitor<R> v) {
          return v.visit(this);
      }

   @Override
   public String toString() {
      return "SELECT * FROM " + relation + "WHERE " + predicate; }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Selection call = (Selection) o;

      if (!predicate.equals(call.predicate)) return false;

      return relation.equals(call.relation);
   }

   @Override
   public int hashCode() {
      int result = predicate.hashCode();
      result = 31 * result + relation.hashCode();
      return result;
   }
}
