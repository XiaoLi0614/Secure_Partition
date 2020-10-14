package awkparse.parser.grammar.core


case class NonTerminal(name: String) extends Symbol {
  override def toString = name
}