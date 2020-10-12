package graph.lang.meta;

import graph.lang.ast.Exp;
import graph.lang.ast.Store;

public class StoreMeta extends TArrayOpMeta {

   private static StoreMeta ourInstance = new StoreMeta();

   public static StoreMeta getInstance() {
      return ourInstance;
   }

   public Exp cons(Exp[] operands) {
      return new Store(operands[0], operands[1], operands[2]);
   }

   @Override
   public String toString() {
      return "SelectMeta";
   }

}
