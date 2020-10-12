package awkparse.parser

import ast.{PrePlusPlus, PostPlusPlus, PreMinusMinus, PostMinusMinus, Field, Concat, Exp}
import grammar.awk._
import grammar.core._
import awkparse.exception.{EndOfFileExpected, TokenTypesMismatch, TokenTypeMismatch}
import awkparse.lexer.tokentype._
import awkparse.lexer.{Token, Scanner}
import scala.collection.mutable._

class Parser(scanner: Scanner) {
  // Parse Stack
  val stack = new Stack[Symbol]()
  // Semantic Stack
  class SStack extends Stack[Either[Exp, TokenType]]()
  val sStack = new SStack() {
    def push(exp: Exp) {
      super.push(Left(exp))
    }
    def push(t: TokenType) {
      super.push(Right(t))
    }
  }
  // Branch stack
  val bStack = new Stack[ListBuffer[Symbol]]()
  bStack.push(new ListBuffer[Symbol]())


  val numAct = new SemanticAction(_ => {
    sStack.push(new awkparse.parser.ast.Num(bStack.top.apply(0).asInstanceOf[Token]))
  })
  val pushPlusPlusAct = new SemanticAction(_ => {
    sStack.push(PlusPlus)
  })
  val pushMinusMinusAct = new SemanticAction(_ => {
    sStack.push(MinusMinus)
  })
  val plusAct = new SemanticAction(_ => {
    sStack.push(Plus)
  })
  val minusAct = new SemanticAction(_ => {
    sStack.push(Minus)
  })

  val fieldAct = new SemanticAction(_ => {
    val operand = sStack.pop().left.get
    val field = new Field(operand)
    sStack.push(field)
  })
  val preIEAct = new SemanticAction(_ => {
    var finished = false
    var current = sStack.pop().left.get
    if (!sStack.isEmpty) {
      var top = sStack.top
      while (!finished) {
        top match {
          case Left(e) =>
            finished = true
          case Right(tokenType@TokenType(n)) =>
            tokenType match {
              case PlusPlus => {
                current = new PrePlusPlus(current)
                sStack.pop()
                if (sStack.isEmpty)
                  finished = true
                else
                  top = sStack.top
              }
              case MinusMinus => {
                current = new PreMinusMinus(current)
                sStack.pop()
                if (sStack.isEmpty)
                  finished = true
                else
                  top = sStack.top
              }
              case _ =>
                finished = true
            }
        }
      }
    }
    sStack.push(current)
  })

  val postIEAct = new SemanticAction(_ => {
    val aStack = new Stack[TokenType]()
    var finished = false
    while (!finished) {
      if (sStack.isEmpty) //It cannot be empty
        finished = true
      else {
        sStack.top match {
          case Left(e) =>
            finished = true
          case Right(t@TokenType(n)) =>
            t match {
              case PlusPlus =>
                aStack.push(t)
                sStack.pop()
              case MinusMinus =>
                aStack.push(t)
                sStack.pop()
              case _ =>
                finished = true
            }
        }
      }
    }
    var current = sStack.pop().left.get
    while (!aStack.isEmpty) {
      val tokenType = aStack.pop()
      tokenType match {
        case PlusPlus =>
          current = new PostPlusPlus(current)
        case MinusMinus =>
          current = new PostMinusMinus(current)
        case _ =>

      }
    }
    sStack.push(current)
  })

  val mathOpAct = new SemanticAction(_ => {
    var operand2 = sStack.pop().left.get
    var tokenType = sStack.pop().right.get
    var operand1 = sStack.pop().left.get
    tokenType match {
      case Plus => {
        sStack.push(new awkparse.parser.ast.Plus(operand1, operand2))
      }
      case Minus => {
        sStack.push(new awkparse.parser.ast.Minus(operand1, operand2))
      }
    }
  })
  val epsilonAct = new SemanticAction(_ => {
    sStack.push(Epsilon)
  })
  val concatAct = new SemanticAction(_ => {
    var elem = sStack.pop()
    elem.fold(
      (e: Exp) => {
        var operand2 = e
        var operand1 = sStack.pop().left.get
        sStack.push(new Concat(operand1, operand2))
      },
      (tokenType: TokenType) => {
        // Igonore it if it is an epsilon
      }
    )
  })


  val grammar = new HashMap[NonTerminal, Array[Rule]]()
  grammar += S -> Array(
             Rule(S, Array[Symbol](E, EOF))
  )
  grammar += E -> Array(
             Rule(E, Array[Symbol](ME, Ep, concatAct)),
             Rule(E, Array[Symbol](Epsilon, epsilonAct))
  )
  grammar += Ep -> Array(
             Rule(Ep, Array[Symbol](MEpp, Ep, concatAct)),
             Rule(Ep, Array[Symbol](Epsilon, epsilonAct))
  )
  grammar += MEpp -> Array(
             Rule(MEpp, Array[Symbol](PostIE, MEp))
  )
  grammar += ME -> Array(
             Rule(ME, Array[Symbol](PreIE, MEp))
  )
  //here
  grammar += MEp -> Array(
             Rule(MEp, Array[Symbol](MO, PreIE, mathOpAct, MEp)),
             Rule(MEp, Array[Symbol](Epsilon))
  )
  grammar += PreIE -> Array(
             Rule(PreIE, Array[Symbol](IOS, PostIE, preIEAct))
  )
  grammar += PostIE -> Array(
             Rule(PostIE, Array[Symbol](FE, IOS, postIEAct))
  )
  grammar += IOS -> Array(
             Rule(IOS, Array[Symbol](IO, IOS)),
             Rule(IOS, Array[Symbol](Epsilon))
  )

  grammar += FE -> Array(
             Rule(FE, Array[Symbol](Dollar, IOS, FE, preIEAct, fieldAct)),
             Rule(FE, Array[Symbol](AE))
  )
  grammar += AE -> Array(
             Rule(AE, Array[Symbol](LPar, E, RPar)),
             Rule(AE, Array[Symbol](N))
  )
  grammar += MO -> Array(
             Rule(MO, Array[Symbol](Plus, plusAct)),
             Rule(MO, Array[Symbol](Minus, minusAct))
  )
  grammar += IO -> Array(
             Rule(IO, Array[Symbol](PlusPlus, pushPlusPlusAct)),
             Rule(IO, Array[Symbol](MinusMinus, pushMinusMinusAct))
  )
  grammar += N -> Array(
             Rule(N, Array[Symbol](Num, numAct))
  )

  private def rules(n: NonTerminal) = grammar.get(n).get

  private var parseTable = new HashMap[NonTerminal, Map[TokenType, Rule]]()
  parseTable += S -> (Map(
//  {++,--,F,\(,Nums}
    Num -> rules(S)(0),
    PlusPlus -> rules(S)(0),
    MinusMinus -> rules(S)(0),
//    Plus -> ,
//    Minus -> ,
    LPar -> rules(S)(0),
//    RPar -> ,
    Dollar -> rules(S)(0)
//    EOF -> ,
  ))
  parseTable += E -> (Map(
    Num -> rules(E)(0),
    PlusPlus -> rules(E)(0),
    MinusMinus -> rules(E)(0),
//    Plus -> ,
//    Minus -> ,
    LPar -> rules(E)(0),
//    RPar -> ,
    Dollar -> rules(E)(0)
//    EOF -> ,
  ))
  parseTable += Ep -> (Map(
    Num -> rules(Ep)(0),
    PlusPlus -> rules(Ep)(0),
    MinusMinus -> rules(Ep)(0),
//    Plus -> ,
//    Minus -> ,
    LPar -> rules(Ep)(0),
    RPar -> rules(Ep)(1),
    Dollar -> rules(Ep)(0),
    EOF -> rules(Ep)(1)
  ))
  parseTable += MEpp -> (Map(
    Num -> rules(MEpp)(0),
    PlusPlus -> rules(MEpp)(0),
    MinusMinus -> rules(MEpp)(0),
//    Plus -> ,
//    Minus -> ,
    LPar -> rules(MEpp)(0),
//    RPar ->
    Dollar -> rules(MEpp)(0)
//    EOF ->
  ))
  parseTable += ME -> (Map(
    Num -> rules(ME)(0),
    PlusPlus -> rules(ME)(0),
    MinusMinus -> rules(ME)(0),
//    Plus -> ,
//    Minus -> ,
    LPar -> rules(ME)(0),
//    RPar ->
    Dollar -> rules(ME)(0)
//    EOF ->
  ))
  parseTable += MEp -> (Map(
    Num -> rules(MEp)(1),
//    PlusPlus ->
//    MinusMinus ->
    Plus -> rules(MEp)(0),
    Minus -> rules(MEp)(0),
    LPar -> rules(MEp)(1),
    RPar -> rules(MEp)(1),
    Dollar -> rules(MEp)(1),
    EOF -> rules(MEp)(1)
  ))
  parseTable += PreIE -> (Map(
    Num -> rules(PreIE)(0),
    PlusPlus -> rules(PreIE)(0),
    MinusMinus -> rules(PreIE)(0),
//    Plus -> ,
//    Minus -> ,
    LPar -> rules(PreIE)(0),
//    RPar -> ,
    Dollar -> rules(PreIE)(0)
//    EOF ->
  ))
  parseTable += PostIE -> (Map(
    Num -> rules(PostIE)(0),
//    PlusPlus -> ,
//    MinusMinus -> ,
//    Plus -> ,
//    Minus -> ,
    LPar -> rules(PostIE)(0),
//    RPar ->
    Dollar -> rules(PostIE)(0)
//    EOF ->
  ))
  parseTable += IOS -> (Map(
    Num -> rules(IOS)(1),
    PlusPlus -> rules(IOS)(0),
    MinusMinus -> rules(IOS)(0),
    Plus -> rules(IOS)(1),
    Minus -> rules(IOS)(1),
    LPar -> rules(IOS)(1),
    RPar -> rules(IOS)(1),
    Dollar -> rules(IOS)(1),
    EOF -> rules(IOS)(1)
  ))
  parseTable += FE -> (Map(
    Num -> rules(FE)(1),
//    PlusPlus ->
//    MinusMinus ->
//    Plus ->
//    Minus ->
    LPar -> rules(FE)(1),
//    RPar ->
    Dollar -> rules(FE)(0)
//    EOF ->
  ))
  parseTable += AE -> (Map(
    Num -> rules(AE)(1),
//    PlusPlus ->
//    MinusMinus ->
//    Plus ->
//    Minus ->
    LPar -> rules(AE)(0)
//    RPar ->
//    Dollar -> ,
//    EOF ->
  ))
  parseTable += MO -> (Map(
//    Num -> ,
//    PlusPlus ->
//    MinusMinus ->
    Plus -> rules(MO)(0),
    Minus -> rules(MO)(1)
//    LPar -> ,
//    RPar ->
//    Dollar -> ,
//    EOF ->
  ))
  parseTable += IO -> (Map(
//    Num -> ,
    PlusPlus -> rules(IO)(0),
    MinusMinus -> rules(IO)(1)
//    Plus -> ,
//    Minus -> ,
//    LPar -> ,
//    RPar ->
//    Dollar -> ,
//    EOF ->
  ))
  parseTable += N -> (Map(
    Num -> rules(N)(0)
//    PlusPlus -> ,
//    MinusMinus -> ,
//    Plus -> ,
//    Minus -> ,
//    LPar -> ,
//    RPar -> ,
//    Dollar -> ,
//    EOF ->
  ))

  def parse(): Exp = {
    stack.push(S)
/*
    stack.push(EOF)
    stack.push(E)
*/

    while (!stack.isEmpty) {
//      println("ParseStack: " + stack)
//      println("SemanticStack: " + sStack)
//      println()

      val top = stack.top
      top match {
        case tokenType@TokenType(n) => {
          if (tokenType == Epsilon) {
            stack.pop()
            bStack.top += tokenType
          } else {
            val iToken = scanner.goAhead()
            if (tokenType == iToken.`type`) {
              stack.pop()
              bStack.top += iToken
            } else
              throw new TokenTypeMismatch(tokenType, iToken.`type`, iToken.lineNo, iToken.columnNo)
          }
        }
        case nonTerminal@NonTerminal(n) => {
          val iToken = scanner.lookAhead()
          stack.pop()
          val map = parseTable.get(nonTerminal).get
          val ruleOption = map.get(iToken.`type`);
          ruleOption match {
            case Some(rule) =>
              bStack.top += nonTerminal
              bStack.push(new ListBuffer[Symbol]())
              // To collect the branch stack at the end of this production
              stack.push(new SemanticAction(_ => {
                bStack.pop()
              }))
              val rhs = rule.rhs
              // make a tree
              for (i <-(rhs.length - 1) to 0 by -1)
                stack.push(rhs(i))
            case None =>
              throw new TokenTypesMismatch(map.keySet, iToken.`type`, iToken.lineNo, iToken.columnNo)
          }
        }
        case SemanticAction(action) => {
          action()
          stack.pop()
        }
      }
    }

    if (! scanner.hasNext)
      sStack.pop().left.get
    else {
      val iToken = scanner.lookAhead()
      println(iToken)
      println(stack)
      throw new EndOfFileExpected(iToken.`type`, iToken.lineNo, iToken.columnNo)
    }
  }
}




