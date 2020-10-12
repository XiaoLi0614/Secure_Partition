package lesani.xtras.simpprog;

import java.util.HashSet;
import java.util.Set;

public class Test3 {

   public static Set<Set<Integer>> makeSets(Set<Integer> set) {
      if (set.size() == 1) {
         HashSet<Set<Integer>> sets = new HashSet<Set<Integer>>();
         sets.add(new HashSet<Integer>(set));
         sets.add(new HashSet<Integer>());
         return sets;
      }

      Integer elem = set.iterator().next();
      set.remove(elem);
      Set<Set<Integer>> interSets = makeSets(set);
      Set<Set<Integer>> newSets = new HashSet<Set<Integer>>();
      for (Set<Integer> interSet : interSets) {
         Set<Integer> newSet = new HashSet<Integer>(interSet);
         newSet.add(elem);
         newSets.add(newSet);
      }
      newSets.addAll(interSets);
      newSets.add(new HashSet<Integer>());
      return newSets;
   }


   public static void main(String[] args) {
      Set<Integer> set = new HashSet<Integer>();
      set.add(1);
      set.add(2);
      set.add(3);
      Set<Set<Integer>> sets = makeSets(set);
      System.out.println(sets);
   }

}




