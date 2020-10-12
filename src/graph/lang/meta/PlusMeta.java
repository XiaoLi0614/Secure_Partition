package graph.lang.meta;

import graph.lang.ast.Exp;
import graph.lang.ast.Plus;

public class PlusMeta extends BMathOpMeta {

   private static PlusMeta ourInstance = new PlusMeta();

   public static PlusMeta getInstance() {
      return ourInstance;
   }

   public Exp cons(Exp[] operands) {
      return new Plus(operands[0], operands[1]);
   }

   @Override
   public String toString() {
      return "PlusMeta";
   }

}
