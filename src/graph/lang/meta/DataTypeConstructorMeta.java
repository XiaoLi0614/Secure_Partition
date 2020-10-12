package graph.lang.meta;

import graph.lang.ast.RecordTypeConstructor;
import graph.lang.ast.Exp;
import graph.lang.type.*;

import java.util.LinkedList;
import java.util.List;

public class DataTypeConstructorMeta extends NOpMeta {

   @Override
   public int arity() {
      return 4;
   }

   private static DataTypeConstructorMeta ourInstance = new DataTypeConstructorMeta();

   public static DataTypeConstructorMeta getInstance() {
      return ourInstance;
   }

   @Override
   public List<lesani.collection.Pair<Type[], Type>> getType() {
      LinkedList<lesani.collection.Pair<Type[], Type>> l =
            new LinkedList<lesani.collection.Pair<Type[], Type>>();
//      l.add(new collection.Pair<Type[], Type>(
//               new Type[]{IntType.getInstance(), IntType.getInstance()},
//               new PairType(IntType.getInstance(), IntType.getInstance())));

      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{IntType.getInstance(), BoolType.getInstance()},
               new PairType(IntType.getInstance(), BoolType.getInstance())));
      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{BoolType.getInstance(), IntType.getInstance()},
               new PairType(BoolType.getInstance(), IntType.getInstance())));

      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{IntType.getInstance(), VIdType.getInstance()},
               new PairType(IntType.getInstance(), VIdType.getInstance())));
      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{VIdType.getInstance(), IntType.getInstance()},
               new PairType(VIdType.getInstance(), IntType.getInstance())));

      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{IntType.getInstance(), BoolType.getInstance()},
               new PairType(IntType.getInstance(), BoolType.getInstance())));
      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{BoolType.getInstance(), IntType.getInstance()},
               new PairType(BoolType.getInstance(), IntType.getInstance())));

      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{IntType.getInstance(), VertexType.getInstance()},
               new PairType(IntType.getInstance(), VertexType.getInstance())));
      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{VertexType.getInstance(), IntType.getInstance()},
               new PairType(VertexType.getInstance(), IntType.getInstance())));

//      l.add(new collection.Pair<Type[], Type>(
//              new Type[]{IntType.getInstance(), new ArrayType(IntType.getInstance(), IntType.getInstance())},
//              new PairType(IntType.getInstance(), new ArrayType(IntType.getInstance(), IntType.getInstance()))));

//      l.add(new collection.Pair<Type[], Type>(
//              new Type[] {new ArrayType(IntType.getInstance(),IntType.getInstance()), IntType.getInstance() },
//              new PairType(new ArrayType(IntType.getInstance(),IntType.getInstance()), IntType.getInstance())));


      l.add(new lesani.collection.Pair<Type[], Type>(
              new Type[]{new ArrayType(IntType.getInstance(), IntType.getInstance()), new ArrayType(IntType.getInstance(), IntType.getInstance())},
              new PairType(new ArrayType(IntType.getInstance(), IntType.getInstance()), new ArrayType(IntType.getInstance(), IntType.getInstance()))));


      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{new OptionType(IntType.getInstance()), new OptionType(IntType.getInstance())},
               new PairType(new OptionType(IntType.getInstance()), new OptionType(IntType.getInstance()))));

      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{new OptionType(IntType.getInstance()), new OptionType(VIdType.getInstance())},
               new PairType(new OptionType(IntType.getInstance()), new OptionType(VIdType.getInstance()))));
      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{new OptionType(VIdType.getInstance()), new OptionType(IntType.getInstance())},
               new PairType(new OptionType(VIdType.getInstance()), new OptionType(IntType.getInstance()))));

      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{new OptionType(IntType.getInstance()), new OptionType(BoolType.getInstance())},
            new PairType(new OptionType(IntType.getInstance()), new OptionType(BoolType.getInstance()))));
      l.add(new lesani.collection.Pair<Type[], Type>(
               new Type[]{new OptionType(BoolType.getInstance()), new OptionType(IntType.getInstance())},
               new PairType(new OptionType(BoolType.getInstance()), new OptionType(IntType.getInstance()))));



      return l;

   }

   public Exp cons(Exp[] operands) {
      return new RecordTypeConstructor(null, null);
   }

   @Override
   public String toString() {
      return "PairMeta";
   }
}
