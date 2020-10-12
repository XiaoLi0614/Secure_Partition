package graph.lang.meta;

import graph.lang.ast.Exp;
import graph.lang.ast.Fst;
import graph.lang.ast.Some;
import graph.lang.type.*;
import lesani.collection.Pair;

import java.util.LinkedList;
import java.util.List;

public class FstMeta extends UOpMeta {

   private static FstMeta ourInstance = new FstMeta();

   public static FstMeta getInstance() {
      return ourInstance;
   }

   public List<Pair<Type[], Type>> getType() {
      LinkedList<Pair<Type[], Type>> l = new LinkedList<Pair<Type[], Type>>();

      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(IntType.getInstance(), IntType.getInstance()) },
               IntType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(IntType.getInstance(), BoolType.getInstance()) },
               IntType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(IntType.getInstance(), VIdType.getInstance()) },
               IntType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(IntType.getInstance(), VertexType.getInstance()) },
               IntType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(IntType.getInstance(), EdgeType.getInstance()) },
               IntType.getInstance()));


      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(BoolType.getInstance(), IntType.getInstance()) },
               BoolType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(BoolType.getInstance(), BoolType.getInstance()) },
               BoolType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(BoolType.getInstance(), VIdType.getInstance()) },
               BoolType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(BoolType.getInstance(), VertexType.getInstance()) },
               BoolType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(BoolType.getInstance(), EdgeType.getInstance()) },
               BoolType.getInstance()));


      l.add(new Pair<Type[], Type>(
              new Type[] {new PairType(new ArrayType(IntType.getInstance(),IntType.getInstance()), IntType.getInstance()) },
              new ArrayType(IntType.getInstance(), IntType.getInstance())));


      l.add(new Pair<Type[], Type>(
              new Type[] {new PairType(new ArrayType(IntType.getInstance(),IntType.getInstance()), new ArrayType(IntType.getInstance(),IntType.getInstance())) },
              new ArrayType(IntType.getInstance(), IntType.getInstance())));

      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(
                     new OptionType(IntType.getInstance()),
                     new OptionType(IntType.getInstance())) },
            new OptionType(IntType.getInstance())));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(
                     new OptionType(IntType.getInstance()),
                     new OptionType(VIdType.getInstance())) },
            new OptionType(IntType.getInstance())));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(
                     new OptionType(VIdType.getInstance()),
                     new OptionType(IntType.getInstance())) },
            new OptionType(VIdType.getInstance())));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(
                     new OptionType(IntType.getInstance()),
                     new OptionType(BoolType.getInstance())) },
            new OptionType(IntType.getInstance())));
      l.add(new Pair<Type[], Type>(
               new Type[] { new PairType(
                     new OptionType(BoolType.getInstance()),
                     new OptionType(IntType.getInstance())) },
            new OptionType(BoolType.getInstance())));



      return l;
   }

   public Exp cons(Exp[] operands) {
      return new Fst(operands[0]);
   }

   @Override
   public String toString() {
      return "FstMeta";
   }
}
