package lesani.alg.graph.toposort;

import java.util.Set;

public abstract class GraphInterface<T> {
   abstract Set<T> add(T node);

   abstract void add(T source, T target);

   abstract Set<T> nodes();

   abstract Set<T> adjacentsOf(T source);

   @Override
   public String toString() {
      String s = "\n";
      for (T node : nodes()) {
         s += node + ":\n";
         for (T nodep : adjacentsOf(node)) {
            s += "\t" + nodep + "\n";
         }
         s += "\n";
         s += "-----------------------------\n";
      }
      return s;
   }
}
