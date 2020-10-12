package vapor

import core.J2V
import parsing.parser.syntaxtree.Goal
import parsing.ast.tree.CompilationUnit
import parsing.astbuilder.ASTBuilder
import typechecking.TypeChecker
import lesani.file.{Logger, ConsoleLogger}
import parsing.parser.parser.{ParseException, MiniJavaParser}
import lesani.compiler.typing.exception.TypeErrorException
import lesani.compiler.texttree.Printable
import java.io.{OutputStreamWriter, InputStream}

object J2VLauncher {

  def processStream(stream: InputStream, logger: Logger): Option[Printable] = {
    try {
      val parser: MiniJavaParser = new MiniJavaParser(stream)
      val root: Goal = parser.Goal
      val compilationUnit: CompilationUnit = new ASTBuilder(root).build
      val typeChecker: TypeChecker = new TypeChecker(compilationUnit)
      val classDir = typeChecker.check
      val printer = J2V.translate(classDir);
      return Some(printer);
    } catch {
      case e: ParseException => {
        System.err.println("Parse Error.")
        e.printStackTrace(System.out);
      }
      case e: TypeErrorException => {
        logger.println("Type error")
        logger.test.println("Type Error: " + e.getMessage)
      }
    }
    logger.test.println("")
    None
  }

  def main(args: Array[String]) {
    val printerOption = processStream(System.in, ConsoleLogger.instance)
    printerOption match {
      case Some(printer) =>
        val writer: OutputStreamWriter = new OutputStreamWriter(System.out)
        printer.print(writer)
        writer.close()
      case None =>
    }
    // java J2V < P.java > P.vapor
    // creates a Vapor program P.vapor with the same behavior as P.java.
  }
}

