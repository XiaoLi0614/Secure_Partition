package lesani.alg.graph.scala

import java.util

//changed
//all the hash set has an added prefix util before it


class TopologicalSort[Node](graph: Graph[Node]) {

  val sorted = new util.HashSet[Node]()
  var closed = new util.HashSet[Node]()
  var seq: List[Node] = Nil

  def sort: Option[List[Node]] = {
    try {
      val nodes = graph.nodes

      for (node <- nodes) {
        if (! sorted.contains(node)) {
          closed = new util.HashSet[Node]()
          dfs(node)
        }
      }
      Some(seq)

    } catch {
      case Cycle =>
        None
    }
  }

  object Cycle extends Exception

  def dfs(node: Node) {
    if (closed.contains(node))
      throw Cycle
    closed.add(node)
    val adjacents = graph.adjacents(node)
    for (adjacent <- adjacents)
      if (! sorted.contains(adjacent))
        dfs(adjacent)
     closed.remove(node)
     sorted.add(node)
     seq = node :: seq
  }

}


object TopologicalSort {
  def apply[Node](graph: Graph[Node]): Option[List[Node]] = {
    val sorter = new TopologicalSort(graph)
    sorter.sort
  }
}

