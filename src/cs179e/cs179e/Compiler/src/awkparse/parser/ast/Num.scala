package awkparse.parser.ast

import awkparse.lexer.Token

case class Num(token: Token) extends Exp {
  val name = "Num"
}

