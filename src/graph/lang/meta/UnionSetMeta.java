package graph.lang.meta;

import graph.lang.ast.Exp;
import graph.lang.ast.SetUnion;
import graph.lang.type.*;

import java.util.LinkedList;
import java.util.List;

public class UnionSetMeta extends BOpMeta {

   private static UnionSetMeta ourInstance = new UnionSetMeta();

   public static UnionSetMeta getInstance() {
      return ourInstance;
   }

   @Override
   public List<lesani.collection.Pair<Type[], Type>> getType() {
      LinkedList<lesani.collection.Pair<Type[], Type>> l =
            new LinkedList<lesani.collection.Pair<Type[], Type>>();
//      l.add(new collection.Pair<Type[], Type>(
//               new Type[]{IntType.getInstance(), IntType.getInstance()},
//               new PairType(IntType.getInstance(), IntType.getInstance())));



//      l.add(new collection.Pair<Type[], Type>(
//              new Type[]{new SetType(IntType.getInstance(), IntType.getInstance()), IntType.getInstance()},
//              new SetType(IntType.getInstance(), IntType.getInstance())));

//      l.add(new collection.Pair<Type[], Type>(
//              new Type[] {new ArrayType(IntType.getInstance(),IntType.getInstance()), IntType.getInstance() },
//              new PairType(new ArrayType(IntType.getInstance(),IntType.getInstance()), IntType.getInstance())));
//
//
//      l.add(new collection.Pair<Type[], Type>(
//              new Type[]{new ArrayType(IntType.getInstance(), IntType.getInstance()), new ArrayType(IntType.getInstance(), IntType.getInstance())},
//              new PairType(new ArrayType(IntType.getInstance(), IntType.getInstance()), new ArrayType(IntType.getInstance(), IntType.getInstance()))));
//
//
//      l.add(new collection.Pair<Type[], Type>(
//               new Type[]{new OptionType(IntType.getInstance()), new OptionType(IntType.getInstance())},
//               new PairType(new OptionType(IntType.getInstance()), new OptionType(IntType.getInstance()))));
//
//      l.add(new collection.Pair<Type[], Type>(
//               new Type[]{new OptionType(IntType.getInstance()), new OptionType(VIdType.getInstance())},
//               new PairType(new OptionType(IntType.getInstance()), new OptionType(VIdType.getInstance()))));
//      l.add(new collection.Pair<Type[], Type>(
//               new Type[]{new OptionType(VIdType.getInstance()), new OptionType(IntType.getInstance())},
//               new PairType(new OptionType(VIdType.getInstance()), new OptionType(IntType.getInstance()))));
//
//      l.add(new collection.Pair<Type[], Type>(
//               new Type[]{new OptionType(IntType.getInstance()), new OptionType(BoolType.getInstance())},
//            new PairType(new OptionType(IntType.getInstance()), new OptionType(BoolType.getInstance()))));
//      l.add(new collection.Pair<Type[], Type>(
//               new Type[]{new OptionType(BoolType.getInstance()), new OptionType(IntType.getInstance())},
//               new PairType(new OptionType(BoolType.getInstance()), new OptionType(IntType.getInstance()))));



      return l;

   }

   public Exp cons(Exp[] operands) {
      return new SetUnion(operands[0], operands[1]);
   }

   @Override
   public String toString() {
      return "PairMeta";
   }
}
