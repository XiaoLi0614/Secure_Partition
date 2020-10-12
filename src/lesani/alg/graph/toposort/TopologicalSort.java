package lesani.alg.graph.toposort;

import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;

import java.util.*;

class Cycle extends RuntimeException {
   public Queue cycleQueue;

   Cycle() {
   }

   Cycle(Queue cycleQueue) {
      this.cycleQueue = cycleQueue;
   }
}

public class TopologicalSort<Node> {

   private GraphInterface<Node> graph;
   private HashSet<Node> sorted = new HashSet<Node>();
   private HashSet<Node> visited = new HashSet<Node>();
   private List<Node> sortedList = new LinkedList<Node>();

   public Queue<Node> cycle = new LinkedList<Node>();

   public TopologicalSort(GraphInterface<Node> graph) {
      this.graph = graph;
   }

   public Option<List<Node>> sort() {
      try {
         Set<Node> nodes = graph.nodes();
         for (Node node : nodes) {
            if (! sorted.contains(node)) {
//               cycle = new LinkedList<Node>();
//               visited = new HashSet<Node>();
               dfs(node);

            }
         }
         return new Some<List<Node>>(sortedList);
      } catch(RuntimeException e) {
         e.printStackTrace(System.out);
         return None.instance();
      }
   }

   public void dfs(Node node) {
      cycle.add(node);
      // If temporarily marked, there is a cycle.
      if (visited.contains(node))
         throw new Cycle(cycle);

      // Mark temporarily
      visited.add(node);
      Set<Node> adjacents = graph.adjacentsOf(node);
      for (Node adjacent : adjacents)
         if (! sorted.contains(adjacent))
            dfs(adjacent);
//      visited.remove(node);
      sorted.add(node);
      // A nodes is added to the beginning of the list when
      // the traversal comes back.
      sortedList.add(0, node);
   }

   public static <Node> Option<List<Node>> sort(GraphInterface<Node> graph) {
      TopologicalSort sorter = new TopologicalSort(graph);
      return sorter.sort();
   }

   public static void main(String[] args) {
      Graph<Integer> graph = new Graph<Integer>();
      graph.add(1, 2);
      graph.add(2, 3);
      graph.add(2, 4);
      graph.add(3, 5);
      graph.add(4, 5);
      graph.add(5, 6);

      graph.add(7, 8);
      graph.add(8, 9);
      graph.add(8, 10);

      // Introduce cycle
//      graph.add(6, 1);

      Option<List<Integer>> sort = TopologicalSort.sort(graph);

      System.out.println(graph);
      System.out.println(sort);
   }

}



