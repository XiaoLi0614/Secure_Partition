package lesani.alg.graph.dijkstra;


import java.util.*;

class Vertex implements Comparable<Vertex> {
   public final String name;
   public Edge[] adjacencies;
   public double minDistance = Double.POSITIVE_INFINITY;
   public Vertex parent;
   public Vertex(String argName) {
      name = argName;
   }
   public String toString() { return name; }

   public int compareTo(Vertex other) {
      return Double.compare(minDistance, other.minDistance);
   }

   @Override
   public int hashCode() {
      return name.hashCode();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Vertex vertex = (Vertex) o;

      return name.equals(vertex.name);

   }
}


class Edge {
   public final Vertex target;
   public final double weight;
   public Edge(Vertex argTarget, double argWeight) {
      target = argTarget; weight = argWeight;
   }
}

class Fringe {

   PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();

   public void add(Vertex pVertex, Edge edge, Vertex vertex) {

      if (pVertex == null) {
         queue.add(vertex);
         return;
      }

      double weight = edge.weight;
      double currentDistance = vertex.minDistance;
      double newDistance = pVertex.minDistance + weight;
      if (newDistance < currentDistance) {
         vertex.minDistance = newDistance ;
         vertex.parent = pVertex;
         // This could be optimized if the priority queue had an upheap method.
         queue.remove(vertex);
         queue.add(vertex);
      }
   }

   public Vertex removeNext() {
      return queue.poll();
   }

   public boolean isEmpty() {
      return queue.isEmpty();
   }
}

public class Dijkstra {

   public static void computePaths(Vertex source) {
      source.minDistance = 0.;
      Fringe fringe = new Fringe();
      Set<Vertex> closed = new HashSet<Vertex>();

      fringe.add(null, null, source);

      while (!fringe.isEmpty()) {
         Vertex u = fringe.removeNext();
         closed.add(u);
         for (Edge e : u.adjacencies) {
            Vertex v = e.target;
            if (!closed.contains(v))
               fringe.add(u, e, v);
         }
      }
   }

   public static List<Vertex> getShortestPathTo(Vertex target) {
      List<Vertex> path = new ArrayList<Vertex>();
      for (Vertex vertex = target; vertex != null; vertex = vertex.parent)
         path.add(vertex);
      Collections.reverse(path);
      return path;
   }

   public static void main(String[] args) {
      Vertex a = new Vertex("A");
      Vertex b = new Vertex("B");
      Vertex c = new Vertex("C");
      Vertex d = new Vertex("D");
      Vertex e = new Vertex("E");

      a.adjacencies = new Edge[]{
            new Edge(b, 5),
            new Edge(c, 10),
            new Edge(d, 8)
      };
      b.adjacencies = new Edge[]{
            new Edge(a, 5),
            new Edge(c, 3),
            new Edge(e, 7)
      };
      c.adjacencies = new Edge[]{
            new Edge(a, 10),
            new Edge(b, 3)
      };
      d.adjacencies = new Edge[]{
            new Edge(a, 8),
            new Edge(e, 2)
      };
      e.adjacencies = new Edge[]{
            new Edge(b, 7),
            new Edge(d, 2)
      };
      Vertex[] vertices = { a, b, c, d, e };
      computePaths(a);
      for (Vertex v : vertices) {
         System.out.println("Distance to " + v + ": " + v.minDistance);
         List<Vertex> path = getShortestPathTo(v);
         System.out.println("Path: " + path);
      }
   }
   /*
   Should print:
   Distance to A: 0.0
   Path: [A]
   Distance to B: 5.0
   Path: [A, B]
   Distance to C: 8.0
   Path: [A, B, C]
   Distance to D: 8.0
   Path: [A, D]
   Distance to E: 10.0
   Path: [A, D, E]
   */
}

