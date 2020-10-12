package vapor.core

import parsing.ast.visitor.GSDepthFirstSearchVisitor
import typechecking.direlicitor.ClassDir
import lesani.compiler.texttree.seq.TextSeq
import collection.mutable.ListBuffer
import lesani.compiler.texttree.{Snippet, Text}
import lesani.compiler.typing.{SymbolTable, Symbol}
import lesani.collection.func.Fun
import lesani.compiler.typing.exception.{SymbolNotFoundException, DuplicateDefExc}
import parsing.ast.tree._
import typechecking.types.{IntArray, DefinedType, StringArray, Boolean}
import scala.collection.JavaConversions._

class GenerateVapor(classDir: ClassDir) extends GSDepthFirstSearchVisitor[Pair[Text, typechecking.types.Type]] {

  trait Entry {
    def getType: typechecking.types.Type
  }
  case class ClassEntry(clazz: typechecking.types.Class) extends Entry {
    override def getType = clazz
  }
  case class MethodEntry(method: typechecking.types.Method) extends Entry {
    override def getType = method
  }
  case class FieldEntry(theType: typechecking.types.Type) extends Entry {
    override def getType = theType
  }
  case class LocalEntry(theType: typechecking.types.Type) extends Entry {
    override def getType = theType
  }

  private val text = new TextSeq()
  private val symbolTable = new SymbolTable[Entry]


  def gen(): Text = {
    symbolTable.startScope()
    type Type = typechecking.types.Type
    type Class = typechecking.types.Class
    for (entry <- classDir.classMap)
      symbolTable.put(entry._1, ClassEntry(entry._2))

    val classes = classDir.classes
    for (theClass <- classes) {
      if (theClass.nameSymbol() == classDir.mainClassSymbol)
        gen(theClass.asInstanceOf[typechecking.types.MainClass])
      else
        gen(theClass)
      text.newLine()
    }
    text.get
  }

  def genMethodName(method: Symbol, clazz: Symbol) {
    // A method m of a class c is represented as c.m
    text.print(clazz.toString)
    text.print(".")
    text.print(method.toString)
  }

  def genVTableName(className: String) {
    text.print(className + "VTable")
  }

  def genVTable(className: String, vTable: VTable) {
    text.print("const ")
    genVTableName(className)
    text.println()
    text.incIndent()
    for (methodClassPair <- vTable.seq) {
      val methodSymbol = methodClassPair._1
      val clazz = methodClassPair._2
      val classSymbol = clazz.nameSymbol()
      text.print(":")
      genMethodName(methodSymbol, classSymbol)
      text.println()
    }
    text.decIndent()
  }

   def gen(theClass: typechecking.types.Class) {
    symbolTable.startScope()
    addSymbolsOf(theClass)
    symbolTable.put("this", ClassEntry(theClass))
    val vTable = theClass.vTable
    genVTable(theClass.name(), vTable)
    text.println()
    for (method <- theClass.methodTrees) {
      visitDispatch(method)
      text.println()
    }
    symbolTable.endScope()
  }

  def gen(mainClass: typechecking.types.MainClass) {
    symbolTable.startScope()
    addSymbolsOf(mainClass)
    //symbolTable.put("this", mainClass)
    symbolTable.startScope()
    //symbolTable.put(mainClass.mainMethod.name(), StringArray.instance)

    //val main = mainClass.mainMethod;
    text.println("func " + "Main()")
    text.incIndent()
    for (statement <- mainClass.statements)
      visitDispatch(statement)
    text.println("ret")
    text.decIndent()

    symbolTable.endScope()
    symbolTable.endScope()
  }

  def visitDispatch(node: Node) = node.accept(this)

  override def visit(method: Method) = {
    var methodName = method.name.toString
    var methodInfo = symbolTable.get(methodName).asInstanceOf[MethodEntry].method
    symbolTable.startScope()
    addSymbolsOf(methodInfo)

    visitDispatch(method.varDecls)
    val clazz = symbolTable.get("this").getType.asInstanceOf[typechecking.types.Class]
    val className: String = clazz.name()
    val methodSymbol = Symbol.symbol(methodName)
    val classSymbol = Symbol.symbol(className)

    text.print("func ")
    genMethodName(methodSymbol, classSymbol)
    text.print("(")
    text.print("this")
    if (method.parameters.size() > 0)
      text.print(" ")
    var i = 0
    for (param: Parameter <- method.parameters) {
      i += 1
      text.print(param.name.toString)
      if (i != method.parameters.size())
        text.print(" ")
    }
    text.println(")")

    text.incIndent()
    // We do not traverse parameters and var declarations. (?)
    for (statement <- method.statements)
      visitDispatch(statement)
    text.decIndent()
    symbolTable.endScope()
    null
  }

  override def visit(varDecl: VarDecl) = {
    var name = varDecl.id.token.toString
    var `type` = varDecl.`type`.accept(this)._2
    `type` = resolve(`type`)
    try {
      symbolTable.put(name, LocalEntry(`type`))
    }
    catch { case exc: DuplicateDefExc => {} }

    null
  }

  override def visit(block: Block) = {
    for (statement <- block.statements)
      visitDispatch(statement)
    null
  }

  override def visit(assign: Assign) = {
    val right = visitDispatch(assign.right)._1

    val name = assign.id.toString
    val entry = symbolTable.get(name)
    entry match {
      case FieldEntry(fieldType) =>
        val clazz = symbolTable.get("this").asInstanceOf[ClassEntry].clazz
        val index = clazz.classRecord.getIndex(name).get
        val offset = index * 4 + 4

        text.print("[")
        text.print("this")
        text.print("+")
        text.print(offset.toString)
        text.print("]")
        text.print(" = ")
        text.println(right)

        null
      case _ => // A local or parameter variable
        (new Snippet(name), entry.getType)
        text.print(assign.id.toString)
        text.print(" = ")
        text.println(right)
        null
    }
  }

  override def visit(arrayAssign: ArrayAssign) = {
    val base = visitDispatch(arrayAssign.array)._1
    val index = visitDispatch(arrayAssign.index)._1
    val right = visitDispatch(arrayAssign.right)._1

    //todo: Add array index out of bounds like array access below

    val offset = freshTemp()
    text.print(offset)
    text.print(" = ")
    text.print("MulS(")
    text.print(index)
    text.print(" ")
    text.print("4")
    text.println(")")

    val directAddr = freshTemp()
    text.print(directAddr)
    text.print(" = ")
    text.print("Add(")
    text.print(base)
    text.print(" ")
    text.print(offset)
    text.println(")")

    // We store the length of the array at the first location.
    text.print("[")
    text.print(directAddr)
    text.print("+4]")
    text.print(" = ")
    text.println(right)

    null
  }

  override def visit(theIf: If) = {
    val thenLabel = freshLabel()
    val elseLabel = freshLabel()
    val endLabel = freshLabel()
    val condition = visitDispatch(theIf.condition)._1
    text.print("-")
    text.print(" ")
    text.print(condition)
    text.print(" ")
    text.print("goto")
    text.print(" ")
    text.print(":")
    text.println(elseLabel)

    text.print(thenLabel)
    text.println(":")
    text.incIndent()
    visitDispatch(theIf.ifStatement)
    text.decIndent()
    text.print("goto")
    text.print(" ")
    text.print(":")
    text.println(endLabel)

    text.print(elseLabel)
    text.println(":")
    text.incIndent()
    visitDispatch(theIf.elseStatement)
    text.decIndent()

    text.print(endLabel)
    text.println(":")

    null
  }

  override def visit(theWhile: While) = {
    val startLabel = freshLabel()
    val endLabel = freshLabel()

    text.print(startLabel)
    text.println(":")
    val condition = visitDispatch(theWhile.condition)._1
    text.print("if0")
    text.print(" ")
    text.print(condition)
    text.print(" ")
    text.print("goto")
    text.print(" ")
    text.print(":")
    text.println(endLabel)

    text.incIndent()
    visitDispatch(theWhile.body)
    text.decIndent()

    text.print("goto")
    text.print(" ")
    text.print(":")
    text.println(startLabel)

    text.print(endLabel)
    text.println(":")

    null
  }

  override def visit(aReturn: Return) = {
    val exp = visitDispatch(aReturn.expression)._1
    text.print("ret")
    text.print(" ")
    text.println(exp)
    null
  }

  override def visit(print: Print) = {
    val arg = visitDispatch(print.argument)._1
    text.print("PrintIntS(")
    text.print(arg)
    text.println(")")
    null
  }

  override def visit(and: And) = {
    val zeroLabel = freshLabel()
    val endLabel = freshLabel()
    val result = freshTemp()
    val operand1 = visitDispatch(and.operand1)._1
    val operand2 = visitDispatch(and.operand2)._1
    text.print("if0")
    text.print(" ")
    text.print(operand1)
    text.print(" ")
    text.print("goto")
    text.print(" ")
    text.print(":")
    text.println(zeroLabel)
    text.print("if0")
    text.print(" ")
    text.print(operand2)
    text.print(" ")
    text.print("goto")
    text.print(" ")
    text.print(":")
    text.println(zeroLabel)

    text.print(result)
    text.print(" = ")
    text.println("1")

    text.print("goto")
    text.print(" ")
    text.print(":")
    text.println(endLabel)

    text.print(zeroLabel)
    text.println(":")
    text.print(result)
    text.print(" = ")
    text.println("0")

    text.print(endLabel)
    text.println(":")

    (new Snippet(result), Boolean.instance())
  }

  override def visit(lessThan: LessThan) = {
    val operand1 = visitDispatch(lessThan.operand1)._1
    val operand2 = visitDispatch(lessThan.operand2)._1
    val result = freshTemp()
    text.print(result)
    text.print(" = ")
    text.print("LtS(")
    text.print(operand1)
    text.print(" ")
    text.print(operand2)
    text.println(")")

    (new Snippet(result), Boolean.instance())
  }

  override def visit(plus: Plus) = {
    val operand1 = visitDispatch(plus.operand1)._1
    val operand2 = visitDispatch(plus.operand2)._1
    val result = freshTemp()
    text.print(result)
    text.print(" = ")
    text.print("Add(")
    text.print(operand1)
    text.print(" ")
    text.print(operand2)
    text.println(")")

    (new Snippet(result), typechecking.types.Int.instance())
  }

  override def visit(minus: Minus) = {
    val operand1 = visitDispatch(minus.operand1)._1
    val operand2 = visitDispatch(minus.operand2)._1
    val result = freshTemp()
    text.print(result)
    text.print(" = ")
    text.print("Sub(")
    text.print(operand1)
    text.print(" ")
    text.print(operand2)
    text.println(")")

    (new Snippet(result), typechecking.types.Int.instance())
  }

  override def visit(mult: Mult) = {
    val operand1 = visitDispatch(mult.operand1)._1
    val operand2 = visitDispatch(mult.operand2)._1
    val result = freshTemp()
    text.print(result)
    text.print(" = ")
    text.print("MulS(")
    text.print(operand1)
    text.print(" ")
    text.print(operand2)
    text.println(")")

    (new Snippet(result), typechecking.types.Int.instance())
  }

  override def visit(arrayLookup: ArrayLookup) = {
    val base = visitDispatch(arrayLookup.array)._1
    val index = visitDispatch(arrayLookup.index)._1

    val size = freshTemp()
    text.print(size)
    text.print(" = ")
    text.print("[")
    text.print(base)
    text.println("]")

    val check = freshTemp()
    text.print(check)
    text.print(" = ")
    text.print("Lt(")
    text.print(index)
    text.print(" ")
    text.print(size)
    text.println(")")

    val startLabel = freshLabel()
    text.print("if ")
    text.print(check)
    text.print(" goto :")
    text.println(startLabel)
    text.println("Error(\"Array index out of bounds\")")

    text.print(startLabel)
    text.println(":")
    val offset = freshTemp()
    text.print(offset)
    text.print(" = ")
    text.print("MulS(")
    text.print(index)
    text.print(" ")
    text.print("4")
    text.println(")")

    val directAddr = freshTemp()
    text.print(directAddr)
    text.print(" = ")
    text.print("Add(")
    text.print(base)
    text.print(" ")
    text.print(offset)
    text.println(")")

    val result = freshTemp()

    text.print(result)
    text.print(" = ")
    text.print("[")
    text.print(directAddr)
    text.println("+4]")

    (new Snippet(result), typechecking.types.Int.instance())
  }

  override def visit(arrayLength: ArrayLength) = {
    val base = arrayLength.array.toString
    val result = freshTemp()
    text.print(result)
    text.print(" = ")
    text.print("[")
    text.print(base)
    text.println("]")

    (new Snippet(result), typechecking.types.Int.instance())
  }

  override def visit(methodCall: MethodCall) = {
    val pair = visitDispatch(methodCall.receiver)
    val receiver = pair._1
    val startLabel = freshLabel()
    text.print("if ")
    text.print(receiver)
    text.print(" ")
    text.print("goto ")
    text.print(":")
    text.println(startLabel)
    text.incIndent()
    text.println("Error(\"Null pointer\")")
    text.decIndent()
    text.print(startLabel)
    text.println(":")
    val receiverClass = pair._2.asInstanceOf[typechecking.types.Class]
    val vTable = freshTemp()
    text.print(vTable)
    text.print(" = ")
    text.print("[")
    text.print(receiver)
    text.println("]")
    val methodName = methodCall.methodName.toString
    val index = receiverClass.vTable.getIndex(methodName)
    val fun = freshTemp()
    text.print(fun)
    text.print(" = ")
    text.print("[")
    text.print(vTable)
    text.print("+")
    text.print((index*4).toString)
    text.println("]")

    val args = new ListBuffer[Text]()
    for (arg <- methodCall.arguments)
      args += visitDispatch(arg)._1

    val result = freshTemp()
    text.print(result)
    text.print(" = ")
    text.print("call ")
    text.print(fun)
    text.print("(")
    text.print(receiver)
    if (args.length > 0)
      text.print(" ")
    var i = 0
    for (arg <- args) {
      i += 1
      text.print(arg)
      if (i != args.length)
        text.print(" ")
    }
    text.println(")")

    val methodSymbol = Symbol.symbol(methodName)
    val retType = receiverClass.methods.get(methodSymbol).returnType

    (new Snippet(result), retType)
  }

  override def visit(intLiteral: IntLiteral) =
    (new Snippet(intLiteral.token.toString), typechecking.types.Int.instance())

  override def visit(trueLiteral: TrueLiteral) =
    (new Snippet("1"), Boolean.instance())

  override def visit(falseLiteral: FalseLiteral) =
    (new Snippet("0"), Boolean.instance())

  override def visit(id: Id) = {
    val name = id.token.toString

    val entry = symbolTable.get(name)
    entry match {
      case FieldEntry(fieldType) =>
        val name = id.toString
        val clazz = symbolTable.get("this").asInstanceOf[ClassEntry].clazz
        val index = clazz.classRecord.getIndex(name).get
        val offset = index * 4 + 4
        val result = freshTemp()

        text.print(result)
        text.print(" = ")
        text.print("[")
        text.print("this")
        text.print("+")
        text.print(offset.toString)
        text.println("]")
        (new Snippet(result), entry.getType)
      case _ => // A local or parameter variable
        (new Snippet(name), entry.getType)
    }


  }
  override def visit(theThis: This) = {
    val name = "this"
    val theType = symbolTable.get(name).getType
    (new Snippet(name), theType)
  }

  override def visit(allocation: Allocation) = {
    val className = allocation.className.toString
    val clazz  = symbolTable.get(className).asInstanceOf[ClassEntry].clazz
    val recordSize = clazz.classRecord.size*4 + 4
    val result = freshTemp()

    // todo: result added to the symbol table
    text.print(result)
    text.print(" = ")
    text.print("HeapAllocZ(")
    text.print(recordSize.toString)
    text.println(")")

    text.print("[")
    text.print(result)
    text.print("]")
    text.print(" = ")
    text.print(":")
    genVTableName(className)
    text.println()

    (new Snippet(result), clazz)
  }

  override def visit(arrayAllocation: ArrayAllocation) = {
    val arraySize = visitDispatch(arrayAllocation.expression)._1

    val arrayByteSize = freshTemp()
    text.print(arrayByteSize)
    text.print(" = ")
    text.print("MulS(")
    text.print(arraySize)
    text.print(" ")
    text.print("4")
    text.println(")")

    val byteSize = freshTemp()
    text.print(byteSize)
    text.print(" = ")
    text.print("Add(")
    text.print(arrayByteSize)
    text.print(" ")
    text.print("4")
    text.println(")")

    // todo: result added to the symbol table
    val result = freshTemp()
    text.print(result)
    text.print(" = ")
    text.print("HeapAllocZ(")
    text.print(byteSize)
    text.println(")")

    // We store the length of the array at the first location.
    text.print("[")
    text.print(result)
    text.print("]")
    text.print(" = ")
    text.println(arraySize)

    (new Snippet(result), IntArray.instance())
  }

  override def visit(not: Not) = {
    val thenLabel = freshLabel()
    val elseLabel = freshLabel()
    val endLabel = freshLabel()
    val result = freshTemp()
    val operand = visitDispatch(not.operand)._1
    text.print("if0")
    text.print(" ")
    text.print(operand)
    text.print(" ")
    text.print("goto")
    text.print(" ")
    text.print(":")
    text.println(elseLabel)

    text.print(thenLabel)
    text.println(":")
    text.incIndent()
    text.print(result)
    text.print(" = ")
    text.println("0")
    text.decIndent()
    text.print("goto")
    text.print(" ")
    text.print(":")
    text.println(endLabel)

    text.print(elseLabel)
    text.println(":")
    text.incIndent()
    text.print(result)
    text.print(" = ")
    text.println("1")
    text.decIndent()

    text.print(endLabel)
    text.println(":")

    (new Snippet(result), Boolean.instance())
  }

  override def visit(intType: IntType) =
    (null, typechecking.types.Int.instance())

  override def visit(booleanType: BooleanType) =
    (null, Boolean.instance())

  override def visit(arrayType: ArrayType) =
    (null, IntArray.instance())


  // ------------------------------------------------------

  var tempCount = 0
  private def tempText(i: Int): String = "t." + i
  private def freshTemp(): String = {
    tempCount += 1
    tempText(tempCount)
  }
  var labelCount = 0
  private def labelText(i: Int): String = "L." + i
  private def freshLabel(): String = {
    labelCount += 1
    labelText(labelCount)
  }

  // ------------------------------------------------------
  private def addSymbolsOf(clazz: typechecking.types.Class) {
    clazz.superClassOpt.apply(new Fun[typechecking.types.Class, AnyRef] {
      def apply(superClass: typechecking.types.Class): AnyRef = {
        addSymbolsOf(superClass)
        null
      }
    })
    var fields = clazz.fields
    type Type = typechecking.types.Type
    type Method = typechecking.types.Method
    for (symbolType <- fields) {
      try {
        val fieldSymbol = symbolType._1
        //println("Added " + fieldSymbol)
        val `type` = symbolType._2
        symbolTable.put(fieldSymbol, FieldEntry(`type`))
      } catch { case duplicateDefExc: DuplicateDefExc => {} }
    }
    var methods = clazz.methods
    for (symbolMethod <- methods)
      try {
        val methodSymbol = symbolMethod._1
        val method = symbolMethod._2
        symbolTable.put(methodSymbol, MethodEntry(method))
      }
      catch { case duplicateDefExc: DuplicateDefExc => {} }
  }

  private def addSymbolsOf(method: typechecking.types.Method) {
    for (p:typechecking.types.Method.Parameter <- method.parameters)
      try {
        symbolTable.put(p.name, LocalEntry(p.`type`))
      } catch { case exc: DuplicateDefExc => {} }
    try {
      symbolTable.put("$ret", LocalEntry(method.returnType))
    } catch { case exc: DuplicateDefExc => {} }
  }

  private def resolve(`type` : typechecking.types.Type): typechecking.types.Type = {
      if (`type`.isInstanceOf[DefinedType]) {
        var definedType: DefinedType = `type`.asInstanceOf[DefinedType]
        try {
          symbolTable.get(definedType.name).getType
        } catch { case e: SymbolNotFoundException => {null} }
      }
      else
        `type`
    }
  }

object GenerateVapor {

  def apply(classDir: ClassDir): Text = {
    new GenerateVapor(classDir).gen()
  }

}
