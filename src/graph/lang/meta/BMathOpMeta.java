package graph.lang.meta;

import graph.lang.type.ArrayType;
import graph.lang.type.IntType;
import graph.lang.type.Type;
import lesani.collection.Pair;

import java.util.LinkedList;
import java.util.List;


public abstract class BMathOpMeta extends BOpMeta {

   public List<Pair<Type[], Type>> getType() {
      LinkedList<Pair<Type[], Type>> l = new LinkedList<Pair<Type[], Type>>();
      l.add(new Pair<Type[], Type>(
               new Type[]{IntType.getInstance(), IntType.getInstance()},
               IntType.getInstance()));

      return l;
   }

}
