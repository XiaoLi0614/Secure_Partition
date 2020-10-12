package vaporm

import collection.mutable.{ListBuffer, Set, Map, Stack}

class LinearScan() {
}

object LinearScan {

  def linearScan(activeSets: Array[Set[Int]], regParams: Array[Int], stackParams: Array[Int], regs: List[Int]):
                (Map[Int, Map[Int, Interval]], Int) = {
    // Returns line2Var2Interval map and stackUse
    // Todo:
    // Although other algorithms may assigns different locations to the same variable in two different lines, this algorithm never does.
    // So the interface of this function can be changed to a map from variables.
    // And then a wrapper can adapt it to a more general interface for register allocators.
    // Todo:
    // The range of the map should also locations. Interval is a concept of this algorithm. Currently it is interval and it is reduced to location later.

    val (intervals, fixedIntervals, remainedRegs, line2Var2Interval) =
      computeIntervals(activeSets, regParams, stackParams, regs)

    val stackUse =
      scan(intervals, fixedIntervals, remainedRegs, regs.size)

    (line2Var2Interval, stackUse)
  }

  private def computeIntervals(
                activeSets: Array[Set[Int]],
                regParams: Array[Int],
                stackParams: Array[Int],
                regs: List[Int]):
                (ListBuffer[Interval], Set[Interval], List[Int], Map[Int, Map[Int, Interval]]) = {
    // Init
    val lineNo2Var2Interval = Map[Int, Map[Int, Interval]]()
    for (i <- -1 until activeSets.length)
      lineNo2Var2Interval += (i -> Map[Int, Interval]())
    val intervals =  new ListBuffer[Interval]()
    var fixedIntervals = Set[Interval]()

    var remainedRegs = regs


    // The params
    for (i <- 0 until regParams.length) {
      val varNo = regParams(i)
      val reg = remainedRegs.head
      remainedRegs = remainedRegs.tail
      // Todo: Here are we are assuming that the argument registers are first in the list of registers.
      // We can make it clear by passing body and argument registers separately to the linearScan
      // We can also take splitting of the arguments to register and stack arguments from outside into the algorithm.
      // The argument registers that are not used for arguments can be added to the pool of registers to be used in the body.

      val interval = new Interval(-1, 0, new RegLoc(reg))
      intervals += interval
      fixedIntervals += interval
      // fixed intervals are note spilled later.
      lineNo2Var2Interval(-1) += (varNo -> interval)
    }

    for (i <- 0 until stackParams.length) {
      // Todo: The arguments that do not fit in the argument registers should be spilled to stack.
      //val interval = new Interval(-1, 0, new InStackLoc(i))
      val reg = remainedRegs.head
      remainedRegs = remainedRegs.tail
      val interval = new Interval(-1, 0, new RegLoc(reg))
      // Use the first body regs to store stack args
      lineNo2Var2Interval(-1) += (stackParams(i) -> interval)
    }

    /*
    // This finds multiple intervals for a variable.
    var prevActiveSet = Set[Int]() ++ (regParams.map(_._1)) ++ stackParams
    for (lineNo <- 0 until activeSets.length + 1) {
      val activeSet =
        if (lineNo != activeSets.length)
          activeSets(lineNo)
        else
          Set[Int]() // A dummy line after the last

      for (varNo <- prevActiveSet if (!activeSet.contains(varNo)))
        lineNo2Var2Interval(lineNo-1)(varNo).finishLine = lineNo-1
      for (varNo <- activeSet) {
        if (!prevActiveSet.contains(varNo)) {
          val interval = new Interval(lineNo, 0, null)
          intervals += interval
          lineNo2Var2Interval(lineNo)(varNo) = interval
        } else
          lineNo2Var2Interval(lineNo)(varNo) = lineNo2Var2Interval(lineNo-1)(varNo)
      }
      prevActiveSet = activeSet
    }
    */

    var var2Interval = Map[Int, Interval]()
    var2Interval ++= lineNo2Var2Interval(-1)
    for (lineNo <- 0 until activeSets.length) {
      val activeSet = activeSets(lineNo)
      for (varNo <- activeSet) {
        if (!var2Interval.contains(varNo)) {
          val interval = new Interval(lineNo, lineNo, null)
          intervals += interval
          var2Interval(varNo) = interval
        } else {
          val interval = var2Interval(varNo)
          interval.finishLine = lineNo
        }
      }
    }
    for (varNo <- var2Interval.keySet) {
      val interval = var2Interval(varNo)
      for (lineNo <- interval.startLine until interval.finishLine + 1)
        lineNo2Var2Interval(lineNo)(varNo) = interval
    }

    println("Intervals:")
    for (interval <- intervals)
      println(interval)
    println("lineNo2Var2Interval:")
    for (lineNo <- 0 until activeSets.length) {
      println("LineNo: " + lineNo + " " + lineNo2Var2Interval(lineNo))
    }
    (intervals, fixedIntervals, remainedRegs, lineNo2Var2Interval)
  }


  def scan(intervals: ListBuffer[Interval], fixedIntervals: Set[Interval], initRegs: List[Int], regCount: Int): Int = {
    var stackOffSet = -1
    val freeRegs = new Stack[Int]()
    for (reg <- initRegs.reverse)
      freeRegs.push(reg)
    val active = new Array[Interval](regCount)
    var activeSize = 0

    def insert(interval: Interval) {
      var i = 0
      var done = (i == activeSize)
      while (!done) {
        val thisInterval = active(i)
        if (interval.finishLine < thisInterval.finishLine) {
          done = true
        } else {
          i += 1
          done = (i == activeSize)
        }
      }
      for (j <- activeSize until i by -1)
        active(j) = active(j-1)
      active(i) = interval
      activeSize += 1
    }

    def remove(i: Int) {
      for (j <- i until activeSize-1)
        active(j) = active(j+1)
      if (i < activeSize)
        activeSize -= 1
    }

    for (interval <- intervals) {
      println(activeSize)
      println(freeRegs)
      // Expire old intervals
      var done = (activeSize == 0)
      while (!done) {
        val thisInterval = active(0)
        if (thisInterval.finishLine >= interval.startLine)
          done = true
        else {
          active(0).location match {
            case RegLoc(i) =>
              //if (!argRegs.contains(i))
                freeRegs.push(i)
            case _ =>
          }
          remove(0)
          done = (activeSize == 0)
        }
      }

      if (fixedIntervals.contains(interval)) {
        // It is assumed that there is space for fixed interval.
        // (They come at the beginning and are fewer than the number of registers).
        insert(interval)
      } else if (activeSize != regCount) {
        val regNo = freeRegs.pop()
        interval.location = new RegLoc(regNo)
        insert(interval)
      } else {
        // Spill an interval (and not fixed interval)
        println(activeSize)
        println(freeRegs)
        var i = activeSize - 1
        var done = false
        while (!done) {
          val last = active(i)
          if (fixedIntervals.contains(last)) {
            i = i - 1
          } else if (last.finishLine < interval.finishLine) {
            stackOffSet += 1
            interval.location = new StackLoc(stackOffSet)
            done = true
          } else {
            remove(i)
            interval.location = last.location
            insert(interval)
            stackOffSet += 1
            last.location = new StackLoc(stackOffSet)
            done = true
          }
        }
      }
    }

    stackOffSet + 1
  }

}



