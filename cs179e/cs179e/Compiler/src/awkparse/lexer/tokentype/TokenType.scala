package awkparse.lexer.tokentype

import awkparse.parser.grammar.core.Symbol

case class TokenType(name: String) extends Symbol {
  override def toString = name
}

