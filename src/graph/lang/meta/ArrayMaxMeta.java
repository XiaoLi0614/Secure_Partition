package graph.lang.meta;

import graph.lang.ast.ArrayMax;
import graph.lang.ast.Exp;
import graph.lang.ast.Max;

public class ArrayMaxMeta extends BArrayOpMeta {

   private static ArrayMaxMeta ourInstance = new ArrayMaxMeta();

   public static ArrayMaxMeta getInstance() {
      return ourInstance;
   }

   public Exp cons(Exp[] operands) {
      return new ArrayMax(operands[0], operands[1]);
   }

   @Override
   public String toString() {
      return "ArrayMaxMeta";
   }
}
