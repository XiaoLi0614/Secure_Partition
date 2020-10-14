package awkparse.parser.grammar.core


case class SemanticAction(var action: Unit=>Unit) extends Symbol {
  override def toString = "@"
}