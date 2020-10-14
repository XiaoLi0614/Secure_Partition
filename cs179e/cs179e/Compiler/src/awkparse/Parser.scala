package awkparse

import exception._
import lexer.Scanner
import traverser.PostFixPrinter
import java.io.{InputStream, FileInputStream, IOException, File}
import awkparse.exception.EndOfFileExpected

object Parser {

  def main(args: Array[String]) {
    try {
      val stream: InputStream = interpretArgs(args)
      if (stream == null) return
      var scanner = new Scanner(stream)
      var p = new parser.Parser(scanner)
      val exp = p.parse()
      val s = new PostFixPrinter(exp).print
      println(s)
      println("Expression parsed successfully.")
    } catch {
      case e: EndOfFileExpected =>
        println("Parse error in line " + e.lineNo + ".")
        System.err.println("(End of file expected at " + e.lineNo + ":" + e.columnNo + ".)")
      case e: TokenTypeMismatch =>
        println("Parse error in line " + e.lineNo + ".")
        System.err.println("(Token Type mismatch at " + e.lineNo + ":" + e.columnNo + ". Found " + e.input + ". Expected " + e.expected + ".)")
      case e: TokenTypesMismatch =>
        println("Parse error in line " + e.lineNo + ".")
        System.err.println("(Token Type mismatch at " + e.lineNo + ":" + e.columnNo + ". Found " + e.input + ". Expected one of " + e.set + ".)")
      case e: UnexpectedEOF =>
        println("Parse error in line " + e.lineNo + ".")
        System.err.println("(Unexpected end of file at " + e.lineNo + ":" + e.columnNo + ".)")
      case e: UnknownToken =>
        println("Parse error in line " + e.lineNo + ".")
        System.err.println("(Unknown token at " + e.lineNo + ":" + e.columnNo + ".)")
    }
  }

  private def interpretArgs(args: Array[String]): InputStream = {
    if (args.length > 1) {
      val usage: String = "usage: java Parse < InputFileName\n" +
                          "       or\n" +
                          "       java Parse InputFileName"
      println(usage)
      null
    } else
      if (args.length == 1)
        new FileInputStream(args(0))
      else
        System.in
  }
}
