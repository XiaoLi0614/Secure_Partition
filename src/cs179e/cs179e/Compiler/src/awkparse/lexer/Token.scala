package awkparse.lexer

import awkparse.parser.grammar.core.Symbol
import tokentype.{EOF, TokenType}

class Token extends Symbol {

  var `type` : TokenType = _
  var text: String = null
  var lineNo: Int = 0
  var columnNo: Int = 0

  def this(t: TokenType) {
    this ()
    `type` = t
  }

  def this(t: TokenType, txt: String) {
    this ()
    `type` = t
    text = txt
  }

  def setLocation(lineNo: Int, columnNo: Int) {
    this.lineNo = lineNo
    this.columnNo = columnNo
  }

  override def toString: String = {
    if (`type` == EOF) return `type`.name
    text + " : " + `type`.name
  }
}
