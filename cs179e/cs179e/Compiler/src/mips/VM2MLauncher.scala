package mips

import java.io.{OutputStreamWriter, InputStream}
import java.io.InputStreamReader
import java.util.LinkedList

import lesani.compiler.texttree.Printable
import lesani.file.{ConsoleLogger, Logger}

import cs132.util.ProblemException
import cs132.vapor.parser.VaporParser
import cs132.vapor.ast.VaporProgram
import cs132.vapor.ast.VBuiltIn.Op

object VM2MLauncher {

  def parseVaporM(in: InputStream): VaporProgram = {
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
    val allowLocals = false
    val registers: Array[String] =  Array(
      "v0", "v1",
      "a0", "a1", "a2", "a3",
      "t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7",
      "s0", "s1", "s2", "s3", "s4", "s5", "s6", "s7",
      "t8"
    )
    val allowStack = true;

    VaporParser.run(
      new InputStreamReader(in),
      1, 1,
      ops,
      allowLocals, registers, allowStack);
  }

  def processStream(stream: InputStream, logger: Logger): Option[Printable] = {
    try {
      val ast = parseVaporM(stream)
      val printer = VM2M.translate(ast);
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
    // java VM2M < P.vaporm > P.s
    // creates a MIPS program P.s with the same behavior as P.vaporm.
  }
}

