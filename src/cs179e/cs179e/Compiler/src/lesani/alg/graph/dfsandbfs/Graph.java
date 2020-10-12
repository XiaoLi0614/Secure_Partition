package lesani.alg.graph.dfsandbfs;

import lesani.alg.graph.toposort.GraphInterface;
import lesani.alg.graph.toposort.TopologicalSort;
import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.collection.option.Option;

import java.util.*;

//import speclang.spec2smt2.ElseNode;

import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.collection.option.Option;

import java.util.*;

public class Graph<T> {

   private Map<T, Set<T>> adjacents = new HashMap<T, Set<T>>();

   public Graph() {
   }

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
         throw new RuntimeException();
      }


      Set<T> set = add(source);
      add(target);
      set.add(target);

   }

   public Set<T> nodes() {
      return adjacents.keySet();
   }

   public Set<T> adjacentsOf(T source) {
      return adjacents.get(source);
   }

   public Queue<T> bfs(T node) {
      LinkedList<T> list = new LinkedList<T>();
      HashSet<T> closed = new HashSet<T>();
      Queue<T> queue = new LinkedList<T>();
      HashSet<T> inQueue = new HashSet<T>();

      queue.add(node);
      inQueue.add(node);

      while (!queue.isEmpty()) {
         T cNode = queue.poll();
         list.add(cNode);
         closed.add(cNode);
         Set<T> adjacents = adjacentsOf(cNode);
         for (T adjacent : adjacents)
            if (!closed.contains(adjacent) &&
                !inQueue.contains(adjacent)) {
               queue.add(adjacent);
               inQueue.add(adjacent);
            }

      }
      return list;
   }


   public LinkedList<T> dfs(T node) {
      LinkedList<T> list = new LinkedList<T>();
      HashSet<T> closed = new HashSet<T>();
      Stack<T> stack = new Stack<T>();
      HashSet<T> inStack = new HashSet<T>();

      stack.add(node);
      inStack.add(node);

      while (!stack.isEmpty()) {
         T cNode = stack.pop();
         if (!closed.contains(cNode)) {
            list.add(cNode);
            closed.add(cNode);
            Set<T> adjacents = adjacentsOf(cNode);
            for (T adjacent : adjacents)
               if (!closed.contains(adjacent) &&
                   !inStack.contains(adjacent)) {
                  stack.add(adjacent);
                  inStack.add(adjacent);
               }
         }
      }
      return list;
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

      System.out.println(graph.bfs(1));
      System.out.println(graph.dfs(1));
   }

}



