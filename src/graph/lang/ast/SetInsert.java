//package graph.lang.ast;
//
//
//import graph.lang.visitor.Visitor;
//
//public class SetInsert extends BOp {
//   final String s = "SetMinus";
//   public SetInsert(Exp arg1, Exp arg2) {
//      super(arg1, arg2);
//   }
//   public <R> R accept(Visitor.ExpVisitor.BOpVisitor<R> v) {
//          return v.visit(this);
//      }
//
//   @Override
//   protected String opName() {
//      return "SetMinus";
//   }
//
//   @Override
//   public boolean equals(Object o) {
//      if (this == o) return true;
//      if (o == null || getClass() != o.getClass()) return false;
////      if (!super.equals(o)) return false;
//
//      SetInsert arrayMin = (SetInsert) o;
//
//      if (!super.equals(o))
//      {
//         if (arg1.equals(arrayMin.arg2) && arg2.equals(arrayMin.arg1)) return true;
//         else return false;
//      }
//
//      return s.equals(arrayMin.s);
//   }
//
//   @Override
//   public int hashCode() {
//      int result = super.hashCode();
//      result = 31 * result + s.hashCode();
//      return result;
//   }
//}
//
//
