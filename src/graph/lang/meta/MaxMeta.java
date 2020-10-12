package graph.lang.meta;

import graph.lang.ast.Exp;
import graph.lang.ast.Max;

public class MaxMeta extends BMathOpMeta {

   private static MaxMeta ourInstance = new MaxMeta();

   public static MaxMeta getInstance() {
      return ourInstance;
   }

   public Exp cons(Exp[] operands) {
      return new Max(operands[0], operands[1]);
   }

   @Override
   public String toString() {
      return "MaxMeta";
   }
}
