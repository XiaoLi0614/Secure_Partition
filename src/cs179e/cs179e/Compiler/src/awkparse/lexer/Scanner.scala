package awkparse.lexer

import awkparse.exception.{UnknownToken, UnexpectedEOF}
import java.io._
import awkparse.lexer.tokentype.{Plus, PlusPlus, Minus, MinusMinus, Dollar, Num, LPar, RPar, EOF}
/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 */

class Scanner(inputStream: InputStream) {

  private var reader = new BufferedReader(new InputStreamReader(inputStream))
  private var thisChar: Char = 0
  private var charCached: Boolean = false
  private var thisLine: Int = 1
  private var thisColumn: Int = 1
  private var startLineNo: Int = 1
  private var startColumnNo: Int = 1

  private var thisToken: Token = null
  private var tokenCached: Boolean = false

  private var hasMore = true

  lookAhead()


  private[lexer] class EndOfStream() extends Exception

  private def readRawChar(): Char = {
    var ich: Int = 0
    try {
      ich = reader.read
    }
    catch {
      case e: IOException => {
        e.printStackTrace()
      }
    }
    if (ich == -1) throw new EndOfStream()
    var ch: Char = ich.asInstanceOf[Char]
    if (ch == '\n') {
      thisLine += 1
      thisColumn = 1
    }
    else {
      thisColumn += 1; thisColumn
    }
    ch
  }

  private def charLookAhead(): Char = {
    if (charCached)
      return thisChar
    var ch: Char = readRawChar()
    while (ch == '#' || ch == '\t' || ch == ' ' || ch == '\n') {
      if (ch == '#') while (ch != '\n') ch = readRawChar()
      else ch = readRawChar()
    }
    startLineNo = thisLine
    startColumnNo = thisColumn - 1
    thisChar = ch
    charCached = true
    thisChar
  }

  private def charGoAhead(): Char = {
    if (!charCached)
      charLookAhead()
    charCached = false
    thisChar
  }

  private def lookAheadExactNext: Char = {
    try {
      reader.mark(10)
      val i: Int = reader.read
      if (i == -1) throw new EndOfStream()
      val ch: Char = i.asInstanceOf[Char]
      reader.reset()
      ch
    }
    catch {
      case e: IOException => {
        e.printStackTrace()
        0
      }
    }
  }

  private def goAheadExactNext() {
    try {
      val ch = reader.read
      if (ch == '\n') {
        thisLine += 1
        thisColumn = 1
      }
      else {
        thisColumn += 1; thisColumn
      }
    }
    catch {
      case e: IOException => {
        e.printStackTrace()
      }
    }
  }

  def lookAhead(): Token = {
    if (tokenCached)
      return thisToken

    val returnToken: Token = new Token()
    var tokenText: String = ""
    var ch0: Char = 0
    try {
      ch0 = charLookAhead()
      val fun = (ch: Char) => {
        charGoAhead()
        tokenText += new String(Array[Char](ch0))
        returnToken.`type` = Num
      }
      ch0 match {
        case '0' => fun(ch0)
        case '1' => fun(ch0)
        case '2' => fun(ch0)
        case '3' => fun(ch0)
        case '4' => fun(ch0)
        case '5' => fun(ch0)
        case '6' => fun(ch0)
        case '7' => fun(ch0)
        case '8' => fun(ch0)
        case '9' => fun(ch0)
        case '(' => {
          charGoAhead()
          tokenText += new String(Array[Char](ch0))
          returnToken.`type` = LPar
        }
        case ')' => {
          charGoAhead()
          tokenText += new String(Array[Char](ch0))
          returnToken.`type` = RPar
        }
        case '+' => {
          charGoAhead()
          var ch1: Char = 0
          try {
            ch1 = lookAheadExactNext
          }
          catch {
            case endOfStream: Scanner#EndOfStream => {
              throw new UnexpectedEOF(startLineNo, startColumnNo)
            }
          }
          if (ch1 == '+') {
            goAheadExactNext()
            tokenText += new String(Array[Char](ch0, ch1))
            returnToken.`type` = PlusPlus
          }
          else {
            tokenText += new String(Array[Char](ch0))
            returnToken.`type` = Plus
          }
        }
        case '-' => {
          var ch1: Char = 0
          charGoAhead()
          try {
            ch1 = lookAheadExactNext
          }
          catch {
            case endOfStream: EndOfStream => {
              throw new UnexpectedEOF(startLineNo, startColumnNo)
            }
          }
          if (ch1 == '-') {
            goAheadExactNext()
            tokenText += new String(Array[Char](ch0, ch1))
            returnToken.`type` = MinusMinus
          }
          else {
            tokenText += new String(Array[Char](ch0))
            returnToken.`type` = Minus
          }
        }
        case '$' => {
          charGoAhead()
          tokenText += new String(Array[Char](ch0))
          returnToken.`type` = Dollar
        }
        case _ =>
          throw new UnknownToken(startLineNo, startColumnNo)
      }
    }
    catch {
      case e: EndOfStream => {
        returnToken.`type` = EOF
      }
    }
    returnToken.text = tokenText
    returnToken.setLocation(startLineNo, startColumnNo)
    thisToken = returnToken
    tokenCached = true
    returnToken
  }

  def goAhead(): Token = {
    if (!tokenCached)
      lookAhead()
    tokenCached = false
    if (thisToken.`type` == EOF)
      hasMore = false
    thisToken
  }

  def hasNext: Boolean = {
//    if (!tokenCached)
//      lookAhead()
    hasMore
  }
}


