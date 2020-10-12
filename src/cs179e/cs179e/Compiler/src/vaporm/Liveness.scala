package vaporm

import cs132.vapor.ast._
import collection.mutable.Set


object Liveness {

  def livenessAnalyze(instrs: Array[VInstr]): Pair[Array[Set[Int]], Array[Set[Int]]] = {
    // Returns an array that maps each line number to to the set of active variable numbers in the line
    // This is a union of kill (where it is defined) and in (where it flows)

    val ins = new Array[Set[Int]](instrs.length)
    val outs = new Array[Set[Int]](instrs.length)
    val gens = new Array[Set[Int]](instrs.length)
    val kills = new Array[Set[Int]](instrs.length)

    val genKillVisitor = new GenKillVisitor
    for (i <- 0 until instrs.length) {
      val instr = instrs(i)
      val (gen, kill) = genKillVisitor.visitDispatch(instr)
      gens(i) = gen
      kills(i) = kill
      ins(i) = gen &~ kill
      outs(i) = Set[Int]()
    }

    val succ = new Array[Set[Int]](instrs.length)
    for (i <- 0 until instrs.length) {
      val instr = instrs(i)
      succ(i) = Set[Int]()
      if (instr.isInstanceOf[VGoto]) {
        val goto = instr.asInstanceOf[VGoto]
        val target = goto.target
        if (target.isInstanceOf[VAddr.Label[VCodeLabel]]) {
          val labelRef = target.asInstanceOf[VAddr.Label[VCodeLabel]]
          succ(i) += labelRef.label.getTarget.instrIndex
        }
      } else {
        if (i != instrs.length - 1)
          succ(i) += (i+1)
        if (instr.isInstanceOf[VBranch]) {
          val branch = instr.asInstanceOf[VBranch]
          succ(i) += branch.target.getTarget.instrIndex
        }
      }
    }
    var changed = true
    while (changed) {
      changed = false
      for (i <- instrs.length-1 until -1 by -1) {
        val prevIns = ins(i).clone()
        for (s <- succ(i))
          outs(i) ++= ins(s)
        ins(i) ++= outs(i) // These methods do not return whether the set is changed (?)
        ins(i) --= kills(i)
        changed = changed || (!prevIns.equals(ins(i)))
      }
    }
    val actives = new Array[Set[Int]](instrs.length)
    for (i <- 0 until instrs.length)
      actives(i) = ins(i) ++ kills(i)

    (actives, outs)
  }


  class GenKillVisitor extends VInstr.VisitorR[Pair[Set[Int], Set[Int]], Exception] {

    def visitDispatch(instr: VInstr) = instr.accept(this)

    override def visit(assign: VAssign) = {
      val source = assign.source
      val gen = Set[Int]()
      if (source.isInstanceOf[VVarRef.Local]) {
        val varSource = source.asInstanceOf[VVarRef.Local]
        gen += varSource.index
      }

      val dest = assign.dest.asInstanceOf[VVarRef.Local]
      val kill = Set(dest.index)

      (gen, kill)
    }

    override def visit(call: VCall) = {
      val args = call.args
      val gen = Set[Int]()
      for (arg <- args) {
        if (arg.isInstanceOf[VVarRef.Local]) {
          val varSource = arg.asInstanceOf[VVarRef.Local]
          gen += varSource.index
        }
      }

      val addr = call.addr
      if (addr.isInstanceOf[VAddr.Var[VFunction]]) {
        val varAddr = addr.asInstanceOf[VAddr.Var[VFunction]]
        gen += varAddr.`var`.asInstanceOf[VVarRef.Local].index
      }

      val dest = call.dest
      val kill =
        if (dest != null)
          Set(dest.index)
        else
          Set[Int]()

      (gen, kill)
    }

    override def visit(builtIn: VBuiltIn) = {
      val args = builtIn.args
      val gen = Set[Int]()
      for (arg <- args) {
        if (arg.isInstanceOf[VVarRef.Local]) {
          val varSource = arg.asInstanceOf[VVarRef.Local]
          gen += varSource.index
        }
      }

      var kill = Set[Int]()
      val dest = builtIn.dest.asInstanceOf[VVarRef.Local]
      if (dest != null)
        kill = Set(dest.index)

      (gen, kill)
    }

    override def visit(memWrite: VMemWrite) = {
      val source = memWrite.source
      val gen = Set[Int]()
      if (source.isInstanceOf[VVarRef.Local]) {
        val varSource = source.asInstanceOf[VVarRef.Local]
        gen += varSource.index
      }
      val dest = memWrite.dest
      if (dest.isInstanceOf[VMemRef.Global]) {
        val base  = dest.asInstanceOf[VMemRef.Global].base
        if (base.isInstanceOf[VAddr.Var[VDataSegment]]) {
          val varAddr = base.asInstanceOf[VAddr.Var[VDataSegment]]
          gen += varAddr.`var`.asInstanceOf[VVarRef.Local].index
        }
      }

      val kill = Set[Int]()

      (gen, kill)
    }

    override def visit(memRead: VMemRead) = {
      val gen = Set[Int]()
      if (memRead.source.isInstanceOf[VMemRef.Global]) {
        val base  = memRead.source.asInstanceOf[VMemRef.Global].base
        if (base.isInstanceOf[VAddr.Var[VDataSegment]]) {
          val varAddr = base.asInstanceOf[VAddr.Var[VDataSegment]]
          gen += varAddr.`var`.asInstanceOf[VVarRef.Local].index
        }
      }

      val dest = memRead.dest.asInstanceOf[VVarRef.Local]
      val kill = Set(dest.index)
      (gen, kill)
    }

    override def visit(branch: VBranch) = {
      val source = branch.value
      val gen = Set[Int]()
      if (source.isInstanceOf[VVarRef.Local]) {
        val varSource = source.asInstanceOf[VVarRef.Local]
        gen += varSource.index
      }
      val kill = Set[Int]()

      (gen, kill)
    }

    override def visit(goto: VGoto) = {
      val gen = Set[Int]()
      val target = goto.target
      if (target.isInstanceOf[VAddr.Var[VCodeLabel]]) {
        // Although we may not have jump by variables in the code
        val theVar = target.asInstanceOf[VAddr.Var[VCodeLabel]]
        val thisVar: VVarRef = theVar.`var`
        if (thisVar.isInstanceOf[VVarRef.Local]) {
          gen += thisVar.asInstanceOf[VVarRef.Local].index
        }
      }

      (gen, Set[Int]())
    }

    override def visit(thReturn: VReturn) = {
      val source = thReturn.value
      val gen = Set[Int]()
      if (source.isInstanceOf[VVarRef.Local]) {
        val varSource = source.asInstanceOf[VVarRef.Local]
        gen += varSource.index
      }
      val kill = Set[Int]()

      (gen, kill)
    }
  }

}

