package awkparse.exception

import awkparse.lexer.tokentype.TokenType


case class TokenTypeMismatch(expected: TokenType, input: TokenType, lineNo: Int, columnNo: Int) extends Exception

