package awkparse.parser.ast

case class BinOp(operand1: Exp, operand2: Exp, name: String) extends Exp {}