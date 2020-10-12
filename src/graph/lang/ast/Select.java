//package graph.lang.ast;
//
//import graph.lang.visitor.Visitor;
//
//public class Select extends BOp {
//   final String s = "select";
//   public Select(Exp arg1, Exp arg2) {
//      super(arg1, arg2);
//   }
//
//   public <R> R accept(Visitor.ExpVisitor.BOpVisitor<R> v) {
//          return v.visit(this);
//      }
//
//   @Override
//   protected String opName() {
//      return "select";
//   }
//
//   @Override
//   public boolean equals(Object o) {
//      if (this == o) return true;
//      if (o == null || getClass() != o.getClass()) return false;
//      if (!super.equals(o)) return false;
//
//      Select plus = (Select) o;
//
//      return s.equals(plus.s);
//   }
//
//   @Override
//   public int hashCode() {
//      int result = super.hashCode();
//      result = 31 * result + s.hashCode();
//      return result;
//   }
//}
