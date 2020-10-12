package graph.lang.meta;

import graph.lang.ast.Exp;
import graph.lang.ast.Gt;
import graph.lang.ast.Lt;
import graph.lang.type.BoolType;
import graph.lang.type.FloatType;
import graph.lang.type.IntType;
import graph.lang.type.Type;
import lesani.collection.Pair;

import java.util.LinkedList;
import java.util.List;

public class LtMeta extends BMathOpMeta {

   private static LtMeta ourInstance = new LtMeta();

   public static LtMeta getInstance() {
      return ourInstance;
   }

   public Exp cons(Exp[] operands) {
      return new Lt(operands[0], operands[1]);
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
      return "LtMeta";
   }

}
