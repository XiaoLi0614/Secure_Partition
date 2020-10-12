package vaporm

import lesani.compiler.texttree.seq.TextSeq
import RegAllocation._
import BodyCodeGen._
import cs132.vapor.ast._
import lesani.compiler.texttree.{Text, Printable}
import scala.collection.mutable.Set
import collection.mutable.Map
import scala.math._

/*
  There are 23 registers: $s0..$s7, $t0..$t8, $a0..$a3, $v0, $v1.
  Registers are global to all functions (whereas local variables were local to a function activation).
  To follow MIPS calling conventions, use the registers as follows:
    $s0..$s7: general use callee-saved
    $t0..$t8: general use caller-saved
    $a0..$a3: reserved for argument passing
    $v0: returning a result from a call
    $v0, $v1: can also be used as temporary registers for loading values from the stack
    The register $t9 is reserved for the next assignment.

    Each function has three stack arrays called "in", "out", and "locals".
    The "in" and "out" array are for passing arguments between functions.
    The "in" array refers to the "out" array of the caller. The "local"
    array is for function-local storage (for example: spilled registers).
    The sizes of these arrays are declared at the top of every function
    (instead of a parameter list).
    Each element of each array is a 4-byte word. The indexes into the array
    is the word-offset (not the byte offset). Array references can be used
    wherever memory references can be used. Indices start from zero.
    func Run [in 2, out 0, local 4]
      $r1 = in[1]
      local[3] = $r1
      ret
    String[] registers = {
      "v0", "v1",
      "a0", "a1", "a2", "a3",
      "t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7",
      "s0", "s1", "s2", "s3", "s4", "s5", "s6", "s7",
      "t8",
    };
*/

class V2VM(vaporProgram: VaporProgram) {
  val text = new TextSeq

  // Defines the order of argument registers
  
  val argRegList = List(
    (0, "$a0"), (1, "$a1"), (2, "$a2"), (3, "$a3")
  )

  val num2ArgReg = Map[Int, String]()
  for (numReg <- argRegList) {
    num2ArgReg += (numReg._1 -> numReg._2)
  } 

  val bodyRegList = List(
    (4, "$t0"), (5, "$t1"), (6, "$t2"), (7, "$t3"), (8, "$t4"), (9, "$t5"), (10, "$t6"), (11, "$t7"),
    (12, "$s0"), (13, "$s1"), (14, "$s2"), (15, "$s3"), (16, "$s4"), (17, "$s5"), (18, "$s6"), (19, "$s7")
  )
  val num2BodyReg = Map[Int, String]()
  for (numReg <- bodyRegList) {
    num2BodyReg += (numReg._1 -> numReg._2)
  }

  val regList = argRegList ++ bodyRegList;

  val num2Reg = Map[Int, String]() ++ num2ArgReg ++ num2BodyReg

  val argRegCount = num2ArgReg.keys.size

  val bodyReg2Num = num2BodyReg.map(_.swap)
  val bodyRegCount = num2BodyReg.keys.size

  val reg2Num = num2Reg.map(_.swap)
  val regCount = num2Reg.keys.size

  def translate(): Text = {
    val dataSegments = vaporProgram.dataSegments
    for (dataSegment <- dataSegments) {
      translate(dataSegment)
      text.println()
    }
    val functions = vaporProgram.functions
    for (function <- functions)
      translate(function)
    text.get
  }

  private def translate(dataSegment: VDataSegment) {
    text.println("const " + dataSegment.ident)
    text.incIndent()
    for (value <- dataSegment.values) {
      if (value.isInstanceOf[VLabelRef[VDataSegment]]) {
        val labelRef = value.asInstanceOf[VLabelRef[VDataSegment]]
        text.println(":" + labelRef.ident)
      } else if (value.isInstanceOf[VLabelRef[VCodeLabel]]) {
        val labelRef = value.asInstanceOf[VLabelRef[VCodeLabel]]
        text.println(":" + labelRef.ident)
      } else if (value.isInstanceOf[VLabelRef[VFunction]]) {
        val labelRef = value.asInstanceOf[VLabelRef[VFunction]]
        text.println(":" + labelRef.ident)
      } else {
        val labelRef = value.asInstanceOf[VLitInt]
        text.println(":" + labelRef.value)
      }
    }
    text.decIndent()
  }

  private def translate(function: VFunction) {
//    for (v <- function.vars)
//      print(v + " ")
//    println()
    println(function.ident)

    val name = function.ident
    val params = function.params
    val body = function.body
    val labels = function.labels

    // Todo: we need to pass all the registerst to allocate function not just the body registers.
    // We can pass the two sets separately.
    val (allocation, outRegs, varStackUse) =
      allocate(body, params, bodyRegList.map(_._1), argRegList.size)
    // Get the allocation:
    // allocation: (lineNo, varNo) -> location that is either registerNo or stackOffset, inStackOffset
    // stackUse

    val indentCount = text.indentCount()

    println(allocation)

    // Gen code according to the allocation
    // Prepare the code for the body before the code for fun header, to have outStackUse.
    val (bodyCode, stackUse, outStackUse) = genBodyCode(
      body, params.length, labels,
      allocation, outRegs, num2Reg, num2ArgReg, bodyRegList.map(_._1), varStackUse,
      indentCount)

    val inStackUse = max(0, params.length - argRegCount)
    text.println("func " + name + " [in " + inStackUse + ", out " + outStackUse + ", local " + stackUse + "]")
    text.println(bodyCode)
  }

}



object V2VM {
  def translate(vaporProgram: VaporProgram): Printable = {
    new V2VM(vaporProgram).translate()
  }

}



// With a little care to prevent the addr of call in an argument register, maybe argument registers can be reused too.

/*
  val line2Regs = (lineNo: Int) => {
    val registers = Set[Int]()
    for (location <- allocation(lineNo).values)
      location match {
        case RegLoc(i) =>
          registers += i
        case _ =>
      }
    registers
  }
*/

