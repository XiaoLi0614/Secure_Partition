package awkparse.parser.ast

case class UOp(operand: Exp, name: String) extends Exp {}