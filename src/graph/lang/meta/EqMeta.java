package graph.lang.meta;

import graph.lang.ast.Eq;
import graph.lang.ast.Exp;
import graph.lang.type.*;
import lesani.collection.Pair;

import java.util.LinkedList;
import java.util.List;

public class EqMeta extends BMathOpMeta {

   private static EqMeta ourInstance = new EqMeta();

   public static EqMeta getInstance() {
      return ourInstance;
   }

   public Exp cons(Exp[] operands) {
      return new Eq(operands[0], operands[1]);
   }

   public List<Pair<Type[], Type>> getType() {
      LinkedList<Pair<Type[], Type>> l = new LinkedList<Pair<Type[], Type>>();
      l.add(new Pair<Type[], Type>(
               new Type[]{IntType.getInstance(), IntType.getInstance()},
               BoolType.getInstance()));

      l.add(new Pair<Type[], Type>(
               new Type[]{VIdType.getInstance(), VIdType.getInstance()},
               BoolType.getInstance()));

//      l.add(new Pair<Type[], Type>(
//               new Type[]{VertexType.getInstance(), VertexType.getInstance()},
//               BoolType.getInstance()));

      return l;
   }

   @Override
   public String toString() {
      return "EqMeta";
   }

}
