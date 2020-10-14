package awkparse.parser.ast

class Minus(op1: Exp, op2: Exp) extends BinOp(op1, op2, "-")

