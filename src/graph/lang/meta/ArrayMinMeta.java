package graph.lang.meta;

import graph.lang.ast.ArrayMin;
import graph.lang.ast.Exp;
import graph.lang.ast.Min;

public class ArrayMinMeta extends BArrayOpMeta {

   private static ArrayMinMeta ourInstance = new ArrayMinMeta();

   public static ArrayMinMeta getInstance() {
      return ourInstance;
   }

   public Exp cons(Exp[] operands) {
      return new ArrayMin(operands[0], operands[1]);
   }

   @Override
   public String toString() {
      return "ArrayMinMeta";
   }
}
