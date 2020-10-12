package vapor.core

import typechecking.direlicitor.ClassDir
import lesani.compiler.texttree.Printable


object J2V {
  def translate(classDir: ClassDir): Printable = {
    // Classes are already ordered as superclass, subclass in the type checking phase.

    MakeClassTables(classDir.classes)

    GenerateVapor(classDir)

  }
}

