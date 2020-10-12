package vapor

import java.io.{FileNotFoundException}
import lesani.collection.option.None
import lesani.collection.option.Option
import lesani.collection.option.Some
import lesani.compiler.texttree.Printable
import lesani.file._
import java.io.File
import java.io.FileInputStream
import lesani.file.Launcher.processFiles

object J2VTester {

  private class J2VProcess extends SingleFileProcess {
    def process(file: File, logger: Logger): Option[Printable] = {
      try {
        val stream: FileInputStream = new FileInputStream(file)
        val printerOption = J2VLauncher.processStream(stream, logger)
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
    val inputPath = baseDir + "/" + "tests/vapor/src"
    val inputExt = "java"
    val outputPath = baseDir + "/" + "tests/vapor/out"
    val outputExt = "vapor"
    val process = new J2VTester.J2VProcess
    processFiles(process, inputPath, outputPath, inputExt, outputExt)
  }

}
