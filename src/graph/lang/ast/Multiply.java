package graph.lang.ast;

import graph.lang.visitor.Visitor;

public class Multiply extends BOp {
   final String s = "*";
   public Multiply(Exp arg1, Exp arg2) {
      super(arg1, arg2);
   }

   public <R> R accept(Visitor.ExpVisitor.BOpVisitor<R> v) {
          return v.visit(this);
      }

   @Override
   protected String opName() {
      return "*";
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Multiply plus = (Multiply) o;
      if (!super.equals(o))
      {
         if (arg1.equals(plus.arg2) && arg2.equals(plus.arg1)) return true;
         else return false;
      }

      return s.equals(plus.s);
   }

   @Override
   public int hashCode() {
      int result = super.hashCode();
      result = 31 * result + s.hashCode();
      return result;
   }
}
