package mips

import lesani.collection.option.None
import lesani.collection.option.Option
import lesani.collection.option.Some
import lesani.file.Launcher.processFiles
import lesani.file.{Logger, SingleFileProcess}
import lesani.compiler.texttree.Printable
import java.io.{FileNotFoundException, FileInputStream, File}

object VM2MTester {

  private class V2VMProcess extends SingleFileProcess {
    def process(file: File, logger: Logger): Option[Printable] = {
      try {
        val stream: FileInputStream = new FileInputStream(file)
        val printerOption = VM2MLauncher.processStream(stream, logger)
        printerOption match {
          case scala.Some(printer) =>
            new Some(printer)
          case scala.None =>
            // Why cast? See comments in the None class in java.
            None.instance().asInstanceOf[Option[Printable]];
        }
      } catch {
        case e: FileNotFoundException => {
          logger.println("File " + file.getName + " not found.")
          None.instance().asInstanceOf[Option[Printable]];
        }
      }
    }

    def exceptionHandler(e: Exception) {
      e.printStackTrace(System.out)
    }
  }

  def main(args: Array[String]) {
    // Working dir should be
    // /media/MOHSENHD/1.Works/4.Course/1.CC/Project/Compiler
    val baseDir: String = System.getProperty("user.dir")

    val inputPath = baseDir + "/" + "tests/mips/src"
//    val inputPath = "/media/MOHSENHD/1.Works/4.Course/1.CC/Project/Compiler/tests/mips/src/Factorial.vaporm"
    val inputExt = "vaporm"
    val outputPath = baseDir + "/" + "tests/mips/out"
    val outputExt = "s"
    val process = new VM2MTester.V2VMProcess
    processFiles(process, inputPath, outputPath, inputExt, outputExt)
  }
}


