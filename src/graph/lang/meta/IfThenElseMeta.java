package graph.lang.meta;

import graph.lang.ast.Exp;
import graph.lang.ast.ITE;
import graph.lang.type.*;
import lesani.collection.Pair;

import java.util.LinkedList;
import java.util.List;


public class IfThenElseMeta implements ExpMeta {
   public int arity() {
      return 3;
   }

   public List<Pair<Type[], Type>> getType() {
      LinkedList<Pair<Type[], Type>> l = new LinkedList<Pair<Type[], Type>>();
      l.add(new Pair<Type[], Type>(
               new Type[] {BoolType.getInstance(), IntType.getInstance(), IntType.getInstance()},
               IntType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[] {BoolType.getInstance(), VertexType.getInstance(), VertexType.getInstance()},
               VertexType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[] {BoolType.getInstance(), EdgeType.getInstance(), EdgeType.getInstance()},
               EdgeType.getInstance()));
      l.add(new Pair<Type[], Type>(
               new Type[] {BoolType.getInstance(), new OptionType(EdgeType.getInstance()), new OptionType(EdgeType.getInstance())},
               new OptionType(EdgeType.getInstance())));
      l.add(new Pair<Type[], Type>(
               new Type[] {BoolType.getInstance(), new OptionType(IntType.getInstance()), new OptionType(IntType.getInstance())},
               new OptionType(IntType.getInstance())));
      l.add(new Pair<Type[], Type>(
               new Type[] {BoolType.getInstance(), new OptionType(VertexType.getInstance()), new OptionType(VertexType.getInstance())},
               new OptionType(VertexType.getInstance())));
      return l;
   }

   public Exp cons(Exp[] operands) {
      return new ITE(operands[0], operands[1], operands[2]);
   }

   private static IfThenElseMeta ourInstance = new IfThenElseMeta();

   public static IfThenElseMeta getInstance() {
      return ourInstance;
   }

   @Override
   public String toString() {
      return "IfThenElseMeta";
   }
}
