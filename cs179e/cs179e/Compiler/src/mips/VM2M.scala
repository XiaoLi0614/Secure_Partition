package mips

import lesani.compiler.texttree.Printable
import lesani.compiler.texttree.seq.TextSeq
import cs132.vapor.ast._
import cs132.vapor.ast.VBuiltIn.Op._
import cs132.vapor.ast.VMemRef.Stack.Region._

class VM2M(vaporMProg: VaporProgram) {
  val text = new TextSeq

  def translate(): Printable = {
    text.println(".data")
    val dataSegments = vaporMProg.dataSegments
    for (dataSegment <- dataSegments) {
      translate(dataSegment)
      text.println()
    }
    text.println(".text")
    text.incIndent()
    text.println("jal Main")
    text.println("li $v0 10")
    text.println("syscall")
    text.decIndent()
    val functions = vaporMProg.functions
    for (function <- functions)
      translate(function)
    genLib()
    text.get
  }
//
  private def translate(dataSegment: VDataSegment) {
    text.println(dataSegment.ident + ":")
    text.incIndent()
    for (value <- dataSegment.values) {
      if (value.isInstanceOf[VLabelRef[VDataSegment]]) {
        val labelRef = value.asInstanceOf[VLabelRef[VDataSegment]]
        text.println(labelRef.ident)
      } else if (value.isInstanceOf[VLabelRef[VCodeLabel]]) {
        val labelRef = value.asInstanceOf[VLabelRef[VCodeLabel]]
        text.println(labelRef.ident)
      } else if (value.isInstanceOf[VLabelRef[VFunction]]) {
        val labelRef = value.asInstanceOf[VLabelRef[VFunction]]
        text.println(labelRef.ident)
      } else {
        val labelRef = value.asInstanceOf[VLitInt]
        text.println("\t.word\t" + labelRef.value)
      }
    }
    text.decIndent()
  }

  private def translate(function: VFunction) {
    val name = function.ident

    val stack = function.stack
    val localCount = stack.local
    val outCount = stack.out
    val frameSize = (localCount + outCount + 2) * 4
    val localAdr = (i: Int) => /*(if (i!=0) "-" else "") +*/ ((outCount+i)*4) + "($sp)"
    val outAdr = (i: Int) => (i*4) + "($sp)"
    val inAdr = (i: Int) => (i*4) + "($fp)"

    val instrs = function.body
    val labels = function.labels

    text.println(name + ":")
    text.incIndent()

    text.println("sw $fp -8($sp)")            // Save $fp
    text.println("move $fp $sp")              // update fp
    text.println("subu $sp $sp " + frameSize) // update sp
    text.println("sw $ra -4($fp)")            // save $ra


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

      InstVisitor.visitDispatch(instr)
    }

    text.decIndent()

    object InstVisitor extends VInstr.Visitor[Exception] {
      def visitDispatch(instr: VInstr) { instr.accept(this) }

      def visit(assign: VAssign) {
        val dest = assign.dest
        val source = assign.source
        if (source.isInstanceOf[VLitInt]) {
          text.print("li ")
          text.print(genVarRef(dest))
          text.print(" ")
          text.println(genOperand(source))
        } else if (source.isInstanceOf[VLabelRef[VTarget]]) {
          text.print("la ")
          text.print(genVarRef(dest))
          text.print(" ")
          text.println(genOperand(source))
        } else {
          text.print("move ")
          text.print(genVarRef(dest))
          text.print(" ")
          text.println(genOperand(source))
        }
      }

      def visit(call: VCall) {
        if (call.addr.isInstanceOf[VAddr.Var[VTarget]])
          text.println("jalr " + genAddr(call.addr))
        else
          text.println("jal " + genAddr(call.addr))
      }

      def visit(builtIn: VBuiltIn) {
        val op = builtIn.op
        val dest = builtIn.dest
        val args = builtIn.args
        /*
          Add, Sub, MulS
          Eq, Lt, LtS
          PrintIntS
          HeapAllocZ
          Error
        */
        if (op == PrintIntS) {
          val arg = args(0)
          if (arg.isInstanceOf[VVarRef]) {
            val varRef = arg.asInstanceOf[VVarRef]
            text.println("move $a0 " + genVarRef(varRef))
          } else if (arg.isInstanceOf[VLitInt]) {
            val litInt = arg.asInstanceOf[VLitInt]
            text.println("li $a0 " + litInt.value.toString)
          }
          text.println("jal _print")
          return
        } else if (op == HeapAllocZ) {
          val arg = args(0)
          if (arg.isInstanceOf[VLitInt]) {
            val litInt = arg.asInstanceOf[VLitInt]
            text.println("li $a0 " + litInt.value.toString)
           } else
            text.println("move $a0 " + genOperand(arg))
          text.println("jal _heapAlloc")
          text.println("move " + genVarRef(dest)  + " $v0")
          return
        } else if (op == Error) {
          text.println("la $a0 _str0")
          text.println("j _error")
          return
        } else if ((op == Sub) || (op == MulS)) {
          val arg0 = args(0)
          val arg1 = args(1)
          if (arg0.isInstanceOf[VLitInt] && arg1.isInstanceOf[VLitInt]) {
            val litInt0 = arg0.asInstanceOf[VLitInt].value
            val litInt1 = arg1.asInstanceOf[VLitInt].value
            val value = if (op == Sub) (litInt0 - litInt1) else (litInt0 * litInt1)
            text.println("li " + genVarRef(dest) + " " + value)
            return
          }
          val arg0Str =
            if (arg0.isInstanceOf[VLitInt]) {
              val litInt = arg0.asInstanceOf[VLitInt]
              text.println("li $t9 " + litInt.value.toString)
              "$t9"
            } else
              genOperand(arg0)
          val arg1Str =
            if (arg1.isInstanceOf[VLitInt]) {
              val litInt = arg1.asInstanceOf[VLitInt]
              text.println("li $t9 " + litInt.value.toString)
              "$t9"
            } else
              genOperand(arg1)
          val mipsOp =
            if (op == Sub)
              "subu" // Todo: Why not sub?
            else if (op == MulS)
              "mul"
            text.println(mipsOp + " " + genVarRef(dest) + " " + arg0Str + " " + arg1Str)
            return
        } else if ((op == Add) || (op == Lt) || (op == LtS)) {
          val arg0 = args(0)
          val arg1 = args(1)
          if (arg0.isInstanceOf[VLitInt] && arg1.isInstanceOf[VLitInt]) {
            val litInt0 = arg0.asInstanceOf[VLitInt].value
            val litInt1 = arg1.asInstanceOf[VLitInt].value
            val value = if (op == Add) (litInt0 + litInt1) else (if (litInt0 < litInt1) 1 else 0)
            text.println("li " + genVarRef(dest) + " " + value)
            return
          }
          var theI = ""
          val arg0Str =
            if (arg0.isInstanceOf[VLitInt]) {
              val litInt = arg0.asInstanceOf[VLitInt]
              theI = ""
              text.println("li $t9 " + litInt.value.toString)
              "$t9"
            } else
              genOperand(arg0)
          val arg1Str =
            if (arg1.isInstanceOf[VLitInt]) {
              val litInt = arg1.asInstanceOf[VLitInt]
              theI = "i"
              litInt.value.toString
            } else
              genOperand(arg1)
          val mipsOp =
            if (op == Add)
              "add" + theI + "u"
            else if ((op == Lt) || (op == LtS))
              "slt" + theI
            text.println(mipsOp + " " + genVarRef(dest) + " " + arg0Str + " " + arg1Str)
            return
        }

        /*
        // This is never used.
        if (op == LtS) {
          text.print("subu ")
          text.print(gen(dest))
          text.print(" ")
          text.print(gen(dest))
          text.print(" ")
          text.print(1 + "")
          text.println()
        }
        */
      }

      def visit(memWrite: VMemWrite) {
        val dest = memWrite.dest
        val source = memWrite.source
        if (source.isInstanceOf[VLabelRef[VTarget]]) {
          text.print("la $t9 ")
          text.println(genOperand(source))
          text.print("sw $t9 ")
          text.println(genMemRef(dest))
        } else if (source.isInstanceOf[VLitInt]) {
          text.print("li $t9 ")
          text.println(genOperand(source))
          text.print("sw $t9 ")
          text.println(genMemRef(dest))
        } else {
          text.print("sw ")
          text.print(genOperand(source))
          text.print(" ")
          text.println(genMemRef(dest))
        }
      }

      def visit(memRead: VMemRead) {
        val dest = memRead.dest
        val source = memRead.source
        text.print("lw ")
        text.print(genVarRef(dest))
        text.print(" ")
        text.println(genMemRef(source))
      }

      def visit(branch: VBranch) {
        val positive = branch.positive
        val value = branch.value
        val label = branch.target.ident
        if (positive)
          text.print("bnez")
        else
          text.print("beqz")
        text.print(" ")
        text.print(genOperand(value))
        text.print(" ")
        text.println(label)
      }

      def visit(goto: VGoto) {
        text.print("j ")
        val addr = goto.target
        text.println(genAddr(addr))
      }

      def visit(theReturn: VReturn) {
        text.println("lw $ra -4($fp)")              // Restore $ra
        text.println("lw $fp -8($fp)")              // Restore $fp
        text.println("addu $sp $sp "  + frameSize)  // Restore $sp
        text.println("jr $ra")                      // Jump back
      }
    }

    // ---------------------------------------------------------------

    def genVarRef(varRef: VVarRef): String = {
      if (varRef.isInstanceOf[VVarRef.Register]) {
        val varRefReg = varRef.asInstanceOf[VVarRef.Register]
        var reg = varRefReg.ident
        "$" + reg
      }
      // if (varRef.isInstanceOf[VVarRef.Local]) {
      // We do not have this in vaporm code.
      else {
//        println(varRef)
        null
      }
    }

    def genOperand(operand: VOperand): String = {
      // VVarRef, VLitStr, VOperand.Static
      if (operand.isInstanceOf[VVarRef]) {
        val varRef = operand.asInstanceOf[VVarRef]
        genVarRef(varRef)
      } else if (operand.isInstanceOf[VLitStr]) {
        val litStr = operand.asInstanceOf[VLitStr]
        "\"" + litStr.value + "\""
      } else if (operand.isInstanceOf[VLitInt]) {
        val litInt = operand.asInstanceOf[VLitInt]
        litInt.value.toString
      } else if (operand.isInstanceOf[VLabelRef[VCodeLabel]]) {
        val labelRef = operand.asInstanceOf[VLabelRef[VCodeLabel]]
        labelRef.ident
      } else if (operand.isInstanceOf[VLabelRef[VDataSegment]]) {
        val labelRef = operand.asInstanceOf[VLabelRef[VDataSegment]]
        labelRef.ident
      } else if (operand.isInstanceOf[VLabelRef[VFunction]]) {
        val labelRef = operand.asInstanceOf[VLabelRef[VFunction]]
        labelRef.ident
      } else
        null
    }

    def genAddr[T <: VTarget](addr: VAddr[T]): String = {
      if (addr.isInstanceOf[VAddr.Label[T]]) {
        val addrLocal = addr.asInstanceOf[VAddr.Label[T]]
        addrLocal.label.ident
      } else {//if (addr.isInstanceOf[VAddr.Var]) {
        val addrVar = addr.asInstanceOf[VAddr.Var[T]]
        genVarRef(addrVar.`var`)
      }
    }

    def genMemRef(memRef: VMemRef): String = {
      if (memRef.isInstanceOf[VMemRef.Global]) {
        // We do not have this in vaporm code.
        val memRefGlobal = memRef.asInstanceOf[VMemRef.Global]
        val baseAddr = memRefGlobal.base
        val offset: Int = memRefGlobal.byteOffset
        offset + "(" + genAddr(baseAddr) + ")"
      } else { //if (memRef.isInstanceOf[VMemRef.Stack]) {
        val memRefStack = memRef.asInstanceOf[VMemRef.Stack]
        val stackType = memRefStack.region
        val index = memRefStack.index
        if (stackType == In)
          inAdr(index)
        else if (stackType == Out)
          outAdr(index)
        else //if (stackType == Local) {
          localAdr(index)
      }
    }

  }

  private def genLib() {
    text.println("_print:")
    text.incIndent()
    text.println("li $v0 1   # syscall: print integer")
    text.println("syscall")
    text.println("la $a0 _newline")
    text.println("li $v0 4   # syscall: print string")
    text.println("syscall")
    text.println("jr $ra")
    text.decIndent()
    text.println("_error:")
    text.incIndent()
    text.println("li $v0 4   # syscall: print string")
    text.println("syscall")
    text.println("li $v0 10  # syscall: exit")
    text.println("syscall")
    text.decIndent()
    text.println("_heapAlloc:")
    text.incIndent()
    text.println("li $v0 9   # syscall: sbrk")
    text.println("syscall")
    text.println("jr $ra")
    text.decIndent()
    text.println(".data")
    text.println(".align 0")
    text.incIndent()
    text.println("_newline: .asciiz \"\\n\"")
    text.println("_str0: .asciiz \"null pointer\\n\"")
    text.decIndent()
    text.println()
  }

}

object VM2M {
  def translate(vaporMProg: VaporProgram): Printable = {
    new VM2M(vaporMProg).translate()
  }
}

