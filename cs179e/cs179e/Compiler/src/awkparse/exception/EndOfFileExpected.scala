package awkparse.exception

import awkparse.lexer.tokentype.TokenType


case class EndOfFileExpected(input: TokenType, lineNo: Int, columnNo: Int) extends Exception

