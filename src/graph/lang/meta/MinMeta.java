package graph.lang.meta;

import graph.lang.ast.Exp;
import graph.lang.ast.Min;

public class MinMeta extends BMathOpMeta {

   private static MinMeta ourInstance = new MinMeta();

   public static MinMeta getInstance() {
      return ourInstance;
   }

   public Exp cons(Exp[] operands) {
      return new Min(operands[0], operands[1]);
   }

   @Override
   public String toString() {
      return "MinMeta";
   }
}
