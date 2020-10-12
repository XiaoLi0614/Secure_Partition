package graph.lang.meta;

import lesani.collection.Pair;
import graph.lang.ast.*;
import graph.lang.type.*;

import java.util.LinkedList;
import java.util.List;

public class SelectorMeta extends UOpMeta {

   private static SelectorMeta ourInstance = new SelectorMeta();

   public static SelectorMeta getInstance() {
      return ourInstance;
   }

   public List<Pair<Type[], Type>> getType() {
      LinkedList<Pair<Type[], Type>> l = new LinkedList<Pair<Type[], Type>>();

//      l.add(new Pair<Type[], Type>(
//               new Type[] { new PairType(IntType.getInstance(), IntType.getInstance()) },
//               IntType.getInstance()));
//
//               BoolType.getInstance()));


//      l.add(new Pair<Type[], Type>(
//              new Type[] {new RecordType(new Sig(new TDecl[]{new TDecl("x",IntType.getInstance())}, IntType.getInstance()))}, new TypeVar()));


//      l.add(new Pair<Type[], Type>(
//              new Type[] {new PairType(new ArrayType(IntType.getInstance(),IntType.getInstance()), new ArrayType(IntType.getInstance(),IntType.getInstance())) },
//              new ArrayType(IntType.getInstance(), IntType.getInstance())));


      return l;
   }

   //TODO REALLLLLLY BAD TOFFF
   public Exp cons(Exp[] operands) {
      return new Projection(operands[0], "test");
   }

   @Override
   public String toString() {
      return "SelectorMeta";
   }
}
