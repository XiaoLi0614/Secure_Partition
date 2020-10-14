package awkparse.exception

import awkparse.lexer.tokentype.TokenType
import scala.collection.Set

case class TokenTypesMismatch(set: Set[TokenType], input: TokenType, lineNo: Int, columnNo: Int) extends Exception
