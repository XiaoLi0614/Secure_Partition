package graph.lang.meta;

import graph.lang.ast.Exp;
import graph.lang.ast.Minus;

public class MinusMeta extends BMathOpMeta {

   private static MinusMeta ourInstance = new MinusMeta();

   public static MinusMeta getInstance() {
      return ourInstance;
   }

   public Exp cons(Exp[] operands) {
      return new Minus(operands[0], operands[1]);
   }

   @Override
   public String toString() {
      return "MinusMeta";
   }
}
