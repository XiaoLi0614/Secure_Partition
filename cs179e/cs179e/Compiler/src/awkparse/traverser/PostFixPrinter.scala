package awkparse.traverser

import awkparse.parser.ast._

class PostFixPrinter(exp: Exp) {
  def print: String = print(exp)

  def print(exp: Exp): String = {
    exp match {
      case Num(token) => token.text
      case BinOp(operand1, operand2, name) => print(operand1) + " " + print(operand2) + " " + name
      case UOp(operand, name) => print(operand) + " " + name
    }
  }

}