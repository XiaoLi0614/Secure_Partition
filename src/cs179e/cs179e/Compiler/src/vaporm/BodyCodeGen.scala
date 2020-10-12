package vaporm

import lesani.compiler.texttree.Text
import cs132.vapor.ast._
import collection.mutable.Set
import lesani.compiler.texttree.seq.TextSeq
import scala.math._
import collection.mutable.Map

class BodyCodeGen(instrs: Array[VInstr],
                  paramCount: Int,
                  labels: Array[VCodeLabel],
                  allocation: Map[Int, Map[Int, Location]],
                  outRegs: Int=>Set[Int],
                  num2Reg: Map[Int, String],
                  num2ArgReg: Map[Int, String],
                  regList: List[Int],
                  varStackUse: Int,
                  indentCount: Int) {

  var outStackUse = 0
  var stackUseToSaveRegs = 0
  var stackUseToSaveSRegs = 0

  var reg2Num = num2Reg.map(_.swap)
  var argRegCount = num2ArgReg.size
  var usedSRegs: Set[Int] = _

  val text = new TextSeq(indentCount)

  def genBodyCode: (Text, Int, Int) = {

    // An $s is needed to be saved only if it is in the allocation.
    var usedRegs = Set[Int]()
    for (i <- 0 until instrs.length)
      allocation(i).values.map(_ match {
        case RegLoc(r) => usedRegs += r
        case _ =>
      })

    var sRegs = Set[Int]()
    for (i <- 0 until 8)
      sRegs += reg2Num("$s" + 0)
      // Todo: Is this a bug? 0 -> i

    usedSRegs = usedRegs & sRegs

    text.incIndent()
    // Load args from the the arg regs and the in stack to body regs.
    // Loading from the in stack is needed because some operations accept only regs as operands.
    // Todo: Instead of loading to registers now, we need to load them to v0 and v1 when needed.

    // Loading from the arg regs is needed because if a call in this function needs the two arguments
    // in the opposite order, then $a0 = $a1 and $a1 = $a0 does not work. This happens in the accept
    // method of the visitor pattern.
    // Todo:
    // We need to save argument registers to the stack before a call.
    // Then load the argument registers only from the stack.

    for (i <- 0 until argRegCount) {
      text.println(num2Reg(regList(i)) + " = " + num2ArgReg(i))
    }
    for (i <- 0 until paramCount-argRegCount) {
      text.println(num2Reg(regList(i + argRegCount)) + " = in[" + i + "]")
    }

    // Push used save regs $s0..$s7
    for (sReg <- usedSRegs) {
      val offset: Int = varStackUse + stackUseToSaveRegs
      stackUseToSaveSRegs += 1
      text.println("local[" + offset + "] = " + num2Reg(sReg))
    }
    var labelIndex = 0
    for (i <- 0 until instrs.length) {
//      println("LineNo = " + i)
      text.decIndent()
      while (labelIndex < labels.length && labels(labelIndex).instrIndex == i) {
        text.println(labels(labelIndex).ident + ":")
        labelIndex += 1
      }
      text.incIndent()
      val instr = instrs(i)
      val activeRegs = outRegs(i)

      InstVisitor.visitDispatch(instr, allocation(i), activeRegs)
    }
    // Pop save regs $s0..$s7 just before the ret
    text.decIndent()
    text.println()

    var stackUse = varStackUse

    // This is the size of the section used to store variables before a function call
    stackUse += stackUseToSaveRegs
    // This is the size of the section used to store save variables at the beginning of the function.
    stackUse += usedSRegs.size // To save $s registers!


    (text.get, stackUse, outStackUse)
  }

  object InstVisitor extends VInstr.VisitorP[Pair[Map[Int, Location], Set[Int]], Exception] {
    def visitDispatch(instr: VInstr, map: Map[Int, Location], activeRegs: Set[Int]) { instr.accept((map, activeRegs), this) }

    def visit(pair: Pair[Map[Int, Location], Set[Int]], assign: VAssign) {
      val (alloc: Map[Int, Location], activeRegs :Set[Int]) = pair
      val dest = assign.dest
      val source = assign.source
      text.print(gen(dest, alloc))
      text.print(" = ")
      text.println(gen(source, alloc))
    }

    def visit(pair: Pair[Map[Int, Location], Set[Int]], call: VCall) {
      val (alloc: Map[Int, Location],  activeRegs: Set[Int]) = pair

      // Do not need to save s regs
      val toBeSavedRegs = activeRegs &~ usedSRegs

      // Store used regs
      var startOffset = varStackUse + stackUseToSaveSRegs
      var i = -1
      for (activeReg <- toBeSavedRegs) {
        i += 1
        text.print("local[" + (startOffset+i) + "]")
        text.print(" = ")
        text.println(num2Reg(activeReg))
      }
      stackUseToSaveRegs = max(stackUseToSaveRegs, i+1)

      val args = call.args
      val regArgCount = min(args.length, num2ArgReg.size)
      for (i <- 0 until regArgCount) {
        text.print(num2ArgReg(i))
        text.print(" = ")
        text.println(gen(args(i), alloc))
      }

      for (i <- regArgCount until args.length) {
        text.print("out[" + (i-regArgCount) + "]")
        text.print(" = ")
        text.println(gen(args(i), alloc))
      }
      val thisOutStackUse = args.length - regArgCount
      outStackUse = max(thisOutStackUse, outStackUse)

      val addr = call.addr
      text.println("call " + gen(addr, alloc))

      // Restore used regs
      i = -1
      for (activeReg <- toBeSavedRegs) {
        i += 1
        text.print(num2Reg(activeReg))
        text.print(" = ")
        text.println("local[" + (startOffset+i) + "]")
      }

      val dest = call.dest
      if (dest != null) {
        text.print(gen(dest, alloc))
        text.print(" = ")
        text.println("$v0")
      }
    }

    def visit(pair: Pair[Map[Int, Location], Set[Int]], builtIn: VBuiltIn) {
      val (alloc: Map[Int, Location], activeRegs :Set[Int]) = pair
      val dest = builtIn.dest
      val op = builtIn.op
      val args = builtIn.args
      if (dest != null) {
        text.print(gen(dest, alloc))
        text.print(" = ")
      }
      text.print(op.name)
      text.print("(")
      for (i <- 0 until args.length) {
        text.print(gen(args(i), alloc))
        if (i != args.length - 1)
          text.print(" ")
      }
      text.println(")")

    }

    def visit(pair: Pair[Map[Int, Location], Set[Int]], memWrite: VMemWrite) {
      val (alloc: Map[Int, Location], activeRegs :Set[Int]) = pair
      val dest = memWrite.dest
      val source = memWrite.source
      text.print(gen(dest, alloc))
      text.print(" = ")
      text.println(gen(source, alloc))
    }

    def visit(pair: Pair[Map[Int, Location], Set[Int]], memRead: VMemRead) {
      val (alloc: Map[Int, Location], activeRegs :Set[Int]) = pair
      val dest = memRead.dest
      val source = memRead.source
      text.print(gen(dest, alloc))
      text.print(" = ")
      text.println(gen(source, alloc))
    }

    def visit(pair: Pair[Map[Int, Location], Set[Int]], branch: VBranch) {
      val (alloc: Map[Int, Location], activeRegs :Set[Int]) = pair
      val positive = branch.positive
      if (positive)
        text.print("if ")
      else
        text.print("if0 ")
      val value = branch.value
      text.print(gen(value, alloc))
      text.print(" goto ")
      val label = branch.target.ident
      text.println(":" + label)
    }

    def visit(pair: Pair[Map[Int, Location], Set[Int]], goto: VGoto) {
      val (alloc: Map[Int, Location], activeRegs :Set[Int]) = pair
      text.print("goto ")
      val addr = goto.target
      text.println(gen(addr, alloc))
    }

    def visit(pair: Pair[Map[Int, Location], Set[Int]], theReturn: VReturn) {
      val (alloc: Map[Int, Location], activeRegs :Set[Int]) = pair
      val value = theReturn.value
      if (value != null) {
        text.print("$v0 = ")
        text.println(gen(value, alloc))
      }
      // Pop save regs $s0..$s7
      var  i = 0
      for (sReg <- usedSRegs) {
        val offset: Int = varStackUse + i
        i += 1
        text.println("local[" + offset + "] = " + num2Reg(sReg))
      }
      text.println("ret")
    }


    def gen[T <: VTarget](addr: VAddr[T], alloc: Map[Int, Location]): String = {
      if (addr.isInstanceOf[VAddr.Label[T]]) {
        val addrLocal = addr.asInstanceOf[VAddr.Label[T]]
        ":" + addrLocal.label.ident
      } else {//if (addr.isInstanceOf[VAddr.Var]) {
        val addrVar = addr.asInstanceOf[VAddr.Var[T]]
        gen(addrVar.`var`, alloc)
      }
    }

    def gen(varRef: VVarRef, alloc: Map[Int, Location]): String = {
      if (varRef.isInstanceOf[VVarRef.Local]) {
        val varRefLocal = varRef.asInstanceOf[VVarRef.Local]
        var index = varRefLocal.index
//        println(varRefLocal.ident)
//        println(alloc)
        var location = alloc(index)
        gen(location)
      }
      // if (varRef.isInstanceOf[VVarRef.Register]
      // We do not have this in vapor code.
      else {
//        println(varRef)
        null
      }
    }

    def gen(operand: VOperand, alloc: Map[Int, Location]): String = {
      //VLitStr, VOperand.Static, VVarRef
      if (operand.isInstanceOf[VVarRef]) {
        val varRef = operand.asInstanceOf[VVarRef]
        gen(varRef, alloc)
      } else if (operand.isInstanceOf[VLitStr]) {
        val litStr = operand.asInstanceOf[VLitStr]
        "\"" + litStr.value + "\""
      } else if (operand.isInstanceOf[VLitInt]) {
        val litInt = operand.asInstanceOf[VLitInt]
        litInt.value.toString
      } else if (operand.isInstanceOf[VLabelRef[VCodeLabel]]) {
        val labelRef = operand.asInstanceOf[VLabelRef[VCodeLabel]]
        ":" + labelRef.ident
      } else if (operand.isInstanceOf[VLabelRef[VDataSegment]]) {
        val labelRef = operand.asInstanceOf[VLabelRef[VDataSegment]]
        ":" + labelRef.ident
      } else {
        // if (varRef.isInstanceOf[VVarRef.Register]
        // We do not have this in vapor code.
        null
      }
    }

    def gen(memRef: VMemRef, alloc: Map[Int, Location]): String = {
      if (memRef.isInstanceOf[VMemRef.Global]) {
        val memRefGlobal = memRef.asInstanceOf[VMemRef.Global]
        val baseAddr = memRefGlobal.base
        val offset: Int = memRefGlobal.byteOffset
        if (offset != 0)
          "[" + gen(baseAddr, alloc) + "+" + offset + "]"
        else
          "[" + gen(baseAddr, alloc) + "]"
      }
      else
      // if (memRef.isInstanceOf[VMemRef.Stack])
      // We do not have this in vapor code.
        null
    }

    def gen(location: Location): String = {
      location match {
        case RegLoc(i) =>
          num2Reg(i)
        case StackLoc(i) =>
          "local[" + i + "]"
        case InStackLoc(i) =>
          "in[" + i + "]"
      }
    }

  }
}

object BodyCodeGen {
  def genBodyCode(instrs: Array[VInstr],
                  paramCount: Int,
                  labels: Array[VCodeLabel],
                  allocation: Map[Int, Map[Int, Location]],
                  outRegs: Int=>Set[Int],
                  num2Reg: Map[Int, String],
                  num2ArgReg: Map[Int, String],
                  regList: List[Int],
                  stackOffset: Int,
                  indentCount: Int): (Text, Int, Int) = {
    new BodyCodeGen(instrs, paramCount, labels, allocation, outRegs, num2Reg, num2ArgReg, regList, stackOffset, indentCount).genBodyCode
  }
}

