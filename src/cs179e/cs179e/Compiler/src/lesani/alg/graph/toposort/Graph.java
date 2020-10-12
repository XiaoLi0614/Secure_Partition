package lesani.alg.graph.toposort;

//import speclang.spec2smt2.ElseNode;

import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.collection.option.Option;
import java.util.*;




public class Graph<T> extends GraphInterface<T> {

   private Map<T, Set<T>> adjacents = new HashMap<T, Set<T>>();

   public Graph() {  }

   public Graph(Map<T, Set<T>> adjacents) {
      this.adjacents = adjacents;
   }

   public Set<T> add(T node) {
      if (node == null)
         throw new RuntimeException();
      Set<T> set = adjacents.get(node);
      if (set == null) {
         set = new HashSet<T>();
         adjacents.put(node, set);
      }
      return set;
   }

   public void add(final T source, final T target) {
      if (source == null || target == null) {
         System.out.println();
         System.out.println("source = " + source);
         System.out.println("target = " + target);
         throw new RuntimeException();
      }
//      if (source instanceof ElseNode)
//      if (source instanceof XNode) {
//         if (((XNode) source).getLabel().equals("W14")) {
//         System.out.println();
//         System.out.println("source = " + source);
//         System.out.println("target = " + target);
//         for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
//            System.out.println(stackTraceElement);
//         }
//         System.out.println();
//      }}


      Set<T> set = add(source);
      add(target);
      set.add(target);


      final TopologicalSort ts = new TopologicalSort(this);
      final Option<List<T>> nodes = ts.sort();
      nodes.apply(
         new Fun0<Object>() {
            public Object apply() {
               System.out.println("Graph");
               System.out.println(Graph.this);
               System.out.println("Adding edge:");
               System.out.println("source = " + source);
               System.out.println("target = " + target);
               System.out.println("The cycle:");
               for (Object node : ts.cycle) {
                  System.out.println(node);
               }
               System.exit(1);
               return null;
            }
         },
         new Fun<List<T>, Object>() {
            public Object apply(List<T> input) {
               return null;
            }
         }
      );

   }

   public Set<T> nodes() {
      return adjacents.keySet();
   }

   public Set<T> adjacentsOf(T source) {
      return adjacents.get(source);
   }

   public void transClosure() {
      for (T node : adjacents.keySet()) {
         Set<T> reachables = traverse(node);
         for (T reachable : reachables) {
            add(node, reachable);
         }
      }
   }
   public void selfExTransClosure() {
      for (T node : adjacents.keySet()) {
         Set<T> reachables = traverse(node);
         for (T reachable : reachables) {
            if (node != reachable)
               add(node, reachable);
         }
      }
   }

   public Set<T> traverse(T node) {
      Set<T> closed = new HashSet<T>();
      Queue<T> fringe = new LinkedList<T>();
      fringe.add(node);
      while (!fringe.isEmpty()) {
         T cNode = fringe.poll();
         closed.add(cNode);
         Set<T> adjacents = adjacentsOf(cNode);
         for (T adjacent : adjacents)
            if (!closed.contains(adjacent))
               fringe.add(adjacent);
      }
      return closed;
   }

   public static <T> Map<T, Set<T>> selfExTransClosure(Map<T, Set<T>> map) {
      Graph<T> graph = new Graph<T>(map);
      graph.selfExTransClosure();
      return graph.adjacents;
   }

}
