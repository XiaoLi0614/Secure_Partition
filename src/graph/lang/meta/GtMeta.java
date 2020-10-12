package graph.lang.meta;

import graph.lang.ast.Eq;
import graph.lang.ast.Exp;
import graph.lang.ast.Gt;
import graph.lang.type.*;
import lesani.collection.Pair;

import java.util.LinkedList;
import java.util.List;

public class GtMeta extends BMathOpMeta {

   private static GtMeta ourInstance = new GtMeta();

   public static GtMeta getInstance() {
      return ourInstance;
   }

   public Exp cons(Exp[] operands) {
      return new Gt(operands[0], operands[1]);
   }

   public List<Pair<Type[], Type>> getType() {
      LinkedList<Pair<Type[], Type>> l = new LinkedList<Pair<Type[], Type>>();
      l.add(new Pair<Type[], Type>(
               new Type[]{IntType.getInstance(), IntType.getInstance()},
               BoolType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[]{FloatType.getInstance(), FloatType.getInstance()},
               BoolType.getInstance()));

      return l;
   }

   @Override
   public String toString() {
      return "GtMeta";
   }

}
