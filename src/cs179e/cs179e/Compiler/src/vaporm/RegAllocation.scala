package vaporm

import Liveness._
import LinearScan._
import cs132.vapor.ast.{VVarRef, VInstr}
import scala.math._
import collection.mutable.Map
import collection.mutable.Set


object RegAllocation {
  def allocate(
    insts: Array[VInstr],
    params: Array[VVarRef.Local],
    regs: List[Int],
    argRegCount: Int):
    (Map[Int, Map[Int, Location]], Int=>Set[Int], Int) = {
    // allocation, outRegs, stackUse


    val (activeSets, outSets) = livenessAnalyze(insts)

//    println("ActiveSets")
//    for (activeSet <- activeSets) {
//      for (a <- activeSet) {
//        print(a + " ")
//      }
//      println()
//    }
    val (regParams, stackParams) = splitParam(params, argRegCount)

    val (line2Var2Interval: Map[Int, Map[Int, Interval]], stackUse: Int) =
          linearScan(activeSets, regParams, stackParams, regs)

    val allocation =
      line2Var2Interval.map(
        (t: (Int, Map[Int, Interval])) =>
          new Tuple2(t._1, t._2.map(
            (tp: (Int, Interval)) =>
              new Tuple2(tp._1, tp._2.location)
          )
        )
      )

    // The map outRegs maps a line number to the registers that are going to be used after it.
    // This is used to determine the registers that should be saved before a function call.
    val outRegs = (lineNo: Int) => {
      val registers = Set[Int]()
      for (varNo <- outSets(lineNo)) {
        val alloc = allocation(lineNo)
        if (alloc.contains(varNo))
          alloc(varNo) match {
            case RegLoc(i) =>
              registers += i
            case _ =>
          }
        else
          throw new RuntimeException
      }
      registers
    }

    //val allocation = (lineNo: Int) => (varNo: Int) => line2Var2Interval(lineNo)(varNo).location
    (allocation, outRegs, stackUse)

  }

  def splitParam(params: Array[VVarRef.Local], argRegCount: Int): Pair[Array[Int], Array[Int]] = {
    val regParamCount = min(argRegCount, params.length)
    val regPrams = new Array[Int](regParamCount)
    for (i <- 0 until regParamCount)
      regPrams(i) = /*(*/params(i).index /*, argRegs(i))*/
      //regPrams(i) = reg2Num("$a" + i)
    val stackParamCount = params.length - regParamCount
    val stackParams = new Array[Int](stackParamCount)
    for (i <- 0 until stackParamCount)
      stackParams(i) = params(regParamCount + i).index
      //stackParams(i) = i
    (regPrams, stackParams)
  }

}




