package graph.lang.meta;

import graph.lang.ast.Exp;
import graph.lang.ast.Some;
import graph.lang.type.*;
import lesani.collection.Pair;

import java.util.LinkedList;
import java.util.List;

public class SomeMeta extends UOpMeta {

   private static SomeMeta ourInstance = new SomeMeta();

   public static SomeMeta getInstance() {
      return ourInstance;
   }

   public List<Pair<Type[], Type>> getType() {
      LinkedList<Pair<Type[], Type>> l = new LinkedList<Pair<Type[], Type>>();

      l.add(new Pair<Type[], Type>(
               new Type[] { IntType.getInstance() },
               new OptionType(IntType.getInstance())));
      l.add(new Pair<Type[], Type>(
               new Type[] { VertexType.getInstance() },
               new OptionType(VertexType.getInstance())));
      l.add(new Pair<Type[], Type>(
               new Type[] { EdgeType.getInstance() },
               new OptionType(EdgeType.getInstance())));
      l.add(new Pair<Type[], Type>(
               new Type[] { VIdType.getInstance() },
               new OptionType(VIdType.getInstance())));

      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(IntType.getInstance(), IntType.getInstance()) },
               new OptionType(new PairType(IntType.getInstance(), IntType.getInstance()))));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(IntType.getInstance(), BoolType.getInstance()) },
               new OptionType(new PairType(IntType.getInstance(), BoolType.getInstance()))));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(BoolType.getInstance(), IntType.getInstance()) },
               new OptionType(new PairType(BoolType.getInstance(), IntType.getInstance()))));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(BoolType.getInstance(), BoolType.getInstance()) },
               new OptionType(new PairType(BoolType.getInstance(), BoolType.getInstance()))));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(IntType.getInstance(), VIdType.getInstance()) },
               new OptionType(new PairType(IntType.getInstance(), VIdType.getInstance()))));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(VIdType.getInstance(), IntType.getInstance()) },
               new OptionType(new PairType(VIdType.getInstance(), IntType.getInstance()))));

      return l;
   }

   public Exp cons(Exp[] operands) {
      return new Some(operands[0]);
   }

   @Override
   public String toString() {
      return "SomeMeta";
   }
}
