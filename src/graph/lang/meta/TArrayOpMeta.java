package graph.lang.meta;

import lesani.collection.Pair;
import graph.lang.type.ArrayType;
import graph.lang.type.IntType;
import graph.lang.type.Type;

import java.util.LinkedList;
import java.util.List;


public abstract class TArrayOpMeta extends TOpMeta {

   public List<Pair<Type[], Type>> getType() {
      LinkedList<Pair<Type[], Type>> l = new LinkedList<Pair<Type[], Type>>();
      l.add(new Pair<Type[], Type>(
               new Type[]{new ArrayType(IntType.getInstance(), IntType.getInstance()), IntType.getInstance(), IntType.getInstance()},
               new ArrayType(IntType.getInstance(), IntType.getInstance())));
      return l;
   }

}
