package vaporm

import java.io.{OutputStreamWriter, InputStream}
import java.io.InputStreamReader
import java.util.LinkedList

import lesani.compiler.texttree.Printable
import lesani.file.{ConsoleLogger, Logger}

import cs132.util.ProblemException
import cs132.vapor.parser.VaporParser
import cs132.vapor.ast.VaporProgram
import cs132.vapor.ast.VBuiltIn.Op

object V2VMLauncher {

  def parseVapor(in: InputStream): VaporProgram = {
    val ops = new LinkedList[Op]()
    ops.add(Op.Add)
    ops.add(Op.Sub)
    ops.add(Op.MulS)
    ops.add(Op.Eq)
    ops.add(Op.Lt)
    ops.add(Op.LtS)
    ops.add(Op.PrintIntS)
    ops.add(Op.HeapAllocZ)
    ops.add(Op.Error)
    val allowLocals = true
    val registers: Array[String] = null
    val allowStack = false;

    VaporParser.run(
      new InputStreamReader(in),
      1, 1,
      ops,
      allowLocals, registers, allowStack);
  }

  def processStream(stream: InputStream, logger: Logger): Option[Printable] = {
    try {
      val ast = parseVapor(stream)
      val printer = V2VM.translate(ast);
      Some(printer);
    }
    catch {
      case ex: ProblemException =>
        logger.test.println("Parse Error.")
        logger.test.println(ex.getMessage)
        None
    }
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
    // java V2VM < P.vapor > P.vaporm
    // creates a Vapor-M program P.vaporm with the same behavior as P.vapor.
  }

}

