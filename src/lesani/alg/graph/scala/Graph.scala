package lesani.alg.graph.scala

trait Graph[Node] {
  def adjacents(node: Node): Array[Node]
  def nodes: Array[Node]
}

