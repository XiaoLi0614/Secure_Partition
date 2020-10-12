package graph.process;


import graph.lang.ast.*;
import graph.lang.type.*;
import graph.lang.visitor.CVCIPrinter;
import lesani.alg.perm.NZeroPartition;
import graph.lang.meta.*;
import lesani.collection.Pair;
import lesani.collection.iterator.CartProdIterator;
import lesani.collection.iterator.PIterator;

import java.util.*;




public class Enumerator implements Iterator<Exp> {

   private Map<Type, Map<Integer, Set<Exp>>> exps;

   private Map<Integer, ArrayList<Exp>> seenExps;

   private int size; // The sum of argument sizes
   private int maxSize;

   private long count = 0;

   private Queue<ExpMeta> ops;
   private PIterator<ExpMeta> opsIter;

   private PIterator<int[]> partIter;

   private Queue<Exp> nexts;

   private Type targetType;


   public Enumerator(Sig sig, int maxSize) {
      exps = new HashMap<Type, Map<Integer, Set<Exp>>>();

      size = 0;
      this.maxSize = maxSize - 1;

      seenExps = new HashMap<>();

      ops = new LinkedList<ExpMeta>();
//      ops.add(IfThenElseMeta.getInstance());
      ops.add(PairMeta.getInstance());
      ops.add(PlusMeta.getInstance());
      ops.add(MinusMeta.getInstance());
//      ops.add(MinMeta.getInstance());
//      ops.add(MaxMeta.getInstance());
//      ops.add(MinMeta.getInstance());
//       ops.add(ArrayMaxMeta.getInstance());
//       ops.add(ArrayMinMeta.getInstance());
//      ops.add(EqMeta.getInstance());

      ops.add(StoreMeta.getInstance());
//      ops.add(SelectMeta.getInstance());
//      ops.add(SomeMeta.getInstance());
//      ops.add(WeightOfEdgeMeta.getInstance());
//      ops.add(CapOfEdgeMeta.getInstance());
//      ops.add(SrcOfEdgeMeta.getInstance());
//      ops.add(DestOfEdgeMeta.getInstance());
//      ops.add(IdMeta.getInstance());
      ops.add(FstMeta.getInstance());
      ops.add(SndMeta.getInstance());
      opsIter = new PIterator<ExpMeta>(ops.iterator());

      ExpMeta expMeta = opsIter.peek();
      int arity = expMeta.arity();
      partIter = new PIterator<int[]>(new NZeroPartition(size, arity));

      nexts = new LinkedList<Exp>();

      targetType = sig.rType;

      initPrepareNexts(sig.pars);
   }

   private void initPrepareNexts(TDecl[] pars) {
      for (TDecl par : pars) {
         Var var = new Var(par.name);
         addExp(var, par.type);
         if (targetType.equals(par.type))
            nexts.add(var);
      }

//      addExp(Src.getInstance(), VIdType.getInstance());
//      if (targetType.equals(VertexType.getInstance()))
//         nexts.add(Src.getInstance());

      None n1 = new None(IntType.getInstance());
      addExp(n1, new OptionType(IntType.getInstance()));
      if (targetType.equals(new OptionType(IntType.getInstance())))
         nexts.add(n1);

      None n2 = new None(VertexType.getInstance());
      addExp(n2, new OptionType(VertexType.getInstance()));
      if (targetType.equals(new OptionType(VertexType.getInstance())))
         nexts.add(n2);

      None n3 = new None(VertexType.getInstance());
      addExp(n3, new OptionType(EdgeType.getInstance()));
      if (targetType.equals(new OptionType(EdgeType.getInstance())))
         nexts.add(n3);

//      addExp(Inf.getInstance(), IntType.getInstance());
//      if (targetType.equals(IntType.getInstance()))
//         nexts.add(Inf.getInstance());


      IntLiteral l0 = new IntLiteral(0);
      IntLiteral l1 = new IntLiteral(1);
//      IntLiteral l2 = new IntLiteral(2);
      addExp(l0, IntType.getInstance());
      addExp(l1, IntType.getInstance());
//      addExp(l2, IntType.getInstance());
      if (targetType.equals(IntType.getInstance())) {
         nexts.add(l0);
         nexts.add(l1);
//         nexts.add(l2);
      }

      if (partIter.hasNext()) {
         ExpMeta expMeta = opsIter.peek();
         int[] parts = partIter.peek();

         process(expMeta, parts);
      }

      size = 1;
   }


   private void addExp(Exp exp, Type type) {
//      if (Settings.log)
//         if (type instanceof VIdType) {
//            System.out.println(exp);
//         }

      Map<Integer, Set<Exp>> map = exps.get(type);
      if (map == null) {
         map = new HashMap<Integer, Set<Exp>>();
         exps.put(type, map);
      }

      Set<Exp> set = map.get(size + 1);
      if (set == null) {
         set = new HashSet<Exp>();
         map.put(size + 1, set);
      }

      set.add(exp);
   }

   private Set<Exp> emptyExps = new HashSet<Exp>();
   private Set<Exp> getExps(Type type, int size) {
      Map<Integer, Set<Exp>> tMap = exps.get(type);
      if (tMap == null) {
         return emptyExps;
      } else {
         Set<Exp> exps = tMap.get(size);
         if (exps == null)
            return emptyExps;
         else
            return exps;
      }
   }



   private boolean moveNext() {

      // Iteration over size, operation, partition

      if (partIter.hasNext())
         partIter.next(); // Go past previous

      if (!partIter.hasNext()) {

         if (!opsIter.hasNext()) {
            throw new RuntimeException();
            //return false; // This never happens.
         } else {
//            System.out.println("opsIter hasNet");
            opsIter.next(); // Go past previous
         }

         if (!opsIter.hasNext()) {

            if (size == maxSize)
               return false; // Checked all the programs of less than the given max size.
            else {
               size += 1;
            }
//            if (Settings.log) {
//               System.out.println("\n----------------------------");
//               System.out.println("Size: " + (size + 1));
//               System.out.println("Total number of expressions: "+count);
//               count = 0;
//            }

            opsIter = new PIterator<ExpMeta>(ops.iterator());
         }
//         System.out.println("Next op: " + opsIter.peek());

         ExpMeta expMeta = opsIter.peek();

         int arity = expMeta.arity();
         partIter = new PIterator<int[]>(new NZeroPartition(size, arity));
      }
//      if (partIter.hasNext())
//         System.out.println("Next part: " + Arrays.toString(partIter.peek()));

      return true;
   }


   private boolean process(ExpMeta expMeta, int[] parts) {
      boolean found = false;
      List<Pair<Type[], Type>> opTypes = expMeta.getType();

      Outer:
      for (Pair<Type[], Type> opType : opTypes) {

         Type[] argTypes = opType._1();

//          if(argTypes instanceof ArrayType)
//          {
//
//          }
         Type opRetType = opType._2();

         ArrayList<Set<Exp>> argExps = new ArrayList<Set<Exp>>(argTypes.length);
         for (int i = 0; i < argTypes.length; i++) {
            Set<Exp> exps = getExps(argTypes[i], parts[i]);
            if (exps.isEmpty())
               continue Outer;
            argExps.add(exps);
         }
//         System.out.println(argExps);

         CartProdIterator<Exp> it = new CartProdIterator<Exp>(argExps);
         while (it.hasNext()) {
            List<Exp> args = it.next();
            Exp exp = expMeta.cons(args.toArray(new Exp[args.size()]));
            addExp(exp, opRetType);

            if (targetType.equals(opRetType)) {
               nexts.add(exp);
               found = true;
            }
         }
      }
      return found;
   }


   private void prepareNexts() {
//      if (!nexts.isEmpty())
//         return;

      boolean found = false;
      while (!found) {
         if (!moveNext())
            return; // The field nexts remains empty.

         if (partIter.hasNext()) {
            ExpMeta expMeta = opsIter.peek();
            int[] parts = partIter.peek();

            found = process(expMeta, parts);
         }
      }
   }


   public boolean hasNext() {
      if (!nexts.isEmpty())
         return true;
      prepareNexts();
      return (!nexts.isEmpty());
   }

   public Exp next() {
      if (!hasNext())
         throw new RuntimeException("There is no next.");
//      if (nexts.isEmpty())
//         prepareNexts();

//       if(seenExps.get(size) == null)
//           seenExps.put(size, new ArrayList<>());
//        Exp next = nexts.element();
//        if(seenExps.get(size).contains(next)) {
//            nexts.remove();
//            return new IntLiteral(10000);
//        }
//        else
//            seenExps.get(size).add(next);
      count++;
      return nexts.remove();
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }


   public static void main(String[] args) {
      Sig s = new Sig(new TDecl[]{
                new TDecl("(Pre-State t)",new ArrayType(IntType.getInstance(),IntType.getInstance()))
//              , new TDecl("post-state",new PairType(new ArrayType(IntType.getInstance(),IntType.getInstance()), IntType.getInstance()))
              , new TDecl("(Role t)", IntType.getInstance())
      }, new ArrayType(IntType.getInstance(), IntType.getInstance()));
//       Sig s = new Sig(new TDecl[]{new TDecl("x", IntType.getInstance())},IntType.getInstance());
//            new OptionType(IntType.getInstance()));
//       Sig s = new Sig(new TDecl[]{new TDecl("p", new PairType(new ArrayType(IntType.getInstance(),IntType.getInstance()),IntType.getInstance()))}, IntType.getInstance());
      Iterator<Exp> e = new Enumerator(s, 8);


      int a = 0;
      while (e.hasNext()) {

         Exp exp = e.next();
         String string = CVCIPrinter.print(exp);
         if(!string.equals("10000"))
             a++;
//         if(string.equals("(store (Pre-State t) (Role t) (+ (select (Pre-State t) (Role t)) 1))"))
            System.out.println(string);
      }
       System.out.println(a);

//       Exp store = new Store(new ArrayType(IntType.getInstance(), IntType.getInstance()),  new IntLiteral(0), new IntLiteral(0));
//        Fun f = new Fun("test", s, new Return(new IntLiteral(1)));
//       System.out.println(SMTIPrinter.print(f));
//       Exp p = new Plus(new IntLiteral(1), new IntLiteral(2));
//       Exp pw = new Plus(new IntLiteral(2), new IntLiteral(1));
//       System.out.println(p.equals(pw));
   }
}






