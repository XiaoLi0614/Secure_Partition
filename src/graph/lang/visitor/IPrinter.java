package graph.lang.visitor;

import graph.lang.ast.*;
import graph.lang.type.*;
import graph.spec.Spec;
import lesani.compiler.texttree.seq.TextSeq;


public class IPrinter implements Visitor {

   @Override
   public Object visit(SortType sortType) {
      return null;
   }

   public TextSeq seq;
   public Spec spec = null; // Usually not used.

    @Override
    public Object visit(VarDecl varDecl) {
        return null;
    }

    @Override
    public Object visit(TypeDecl typeDecl) {
        return null;
    }

    @Override
   public Object visit(TDecl tDecl) {
      return null;
   }

   @Override
   public Object visit(Assertion assertion) {
      return null;
   }

   public IPrinter() {
      seq = new TextSeq();
   }

   public IPrinter(Spec spec) {
      this.spec = spec;
      seq = new TextSeq();
   }

   public static String print(Fun fun) {
      IPrinter p = new IPrinter();
      p.visit(fun);
      return p.getText();
   }

   public static String print(Exp exp) {
      IPrinter p = new IPrinter();
      p.visitDisptch(exp);
      return p.getText();
   }

   public static String print(Spec spec, Fun fun) {
      IPrinter p = new IPrinter(spec);
      p.visit(fun);
      return p.getText();
   }
   public static String print(Sig sig) {
      IPrinter p = new IPrinter();
      p.visit(sig);
      return p.getText();
   }

   public static String print(Spec spec, Exp exp) {
      IPrinter p = new IPrinter(spec);
      p.visitDisptch(exp);
      return p.getText();
   }

   public Object visit(Sig sig) {
      seq.put("(");
      for (int i = 0; i < sig.pars.length; i++) {
         TDecl tDecl = sig.pars[i];
         seq.put(tDecl.name);
         seq.put(": ");
         visitDisptch(tDecl.type);
         if (i < sig.pars.length - 1)
            seq.put(", ");
      }
      seq.put("): ");
      visitDisptch(sig.rType);
      return null;
   }

   @Override
   public Object visit(Fun fun) {
      seq.put("fun ");
      seq.put(fun.name);
      visit(fun.sig);
      seq.put(" {");
      seq.endLine();
      seq.incIndent();
      visitDisptch(fun.body);
//      seq.endLine();
      seq.decIndent();
      seq.put("}");
      seq.endLine();
      seq.endLine();
      return null;
   }

   public Object visitDisptch(Type type) {
      return type.accept(typePrinter);
   }

   TypePrinter typePrinter = new TypePrinter();
   public class TypePrinter implements Visitor.TypeVisitor<Object> {

      @Override
      public Object visit(SortType sortType) {
         return null;
      }

      @Override
       public Object visit(TupleType tupleType) {
           return null;
       }

       @Override
      public Object visit(BoolType boolType) {
         seq.put("Bool");
         return null;
      }

      @Override
      public Object visit(IntType intType) {
//         seq.put("Int");
         seq.put("Int");
         return null;
      }

      @Override
      public Object visit(FloatType floatType) {
         seq.put("Float");
         return null;
      }

      @Override
      public Object visit(OptionType optionType) {
         seq.put("Option[");
         visitDisptch(optionType.tpar);
         seq.put("]");
         return null;
      }

      @Override
      public Object visit(VertexType vertexType) {
         seq.put("V");
         return null;
      }

      @Override
      public Object visit(EdgeType edgeType) {
         seq.put("E");
         return null;
      }

      @Override
      public Object visit(VoidType voidType) {
         seq.put("Void");
         return null;
      }

      @Override
      public Object visit(TypeVar typeVar) {
         return null;
      }

//      @Override
//      public Object visit(SetType type) {
//         seq.put("Set[");
//         visitDisptch(type.tpar);
//         seq.put("]");
//         return null;
//      }

      @Override
      public Object visit(DirType dirType) {
         seq.put("Dir");
         return null;
      }

      @Override
      public Object visit(VIdType idType) {
         seq.put("Id");
         return null;
      }

      @Override
      public Object visit(PairType pairType) {
         seq.put("Pair[");
         visitDisptch(pairType.tpar1);
         seq.put(", ");
         visitDisptch(pairType.tpar2);
         seq.put("]");
         return null;
      }

      @Override
      public Object visit(SetType setType) {
         return null;
      }

      @Override
      public Object visit(RecordType argebraicDataType) {
         return null;
      }

//      @Override
//	public Object visit(EmptyType emptyType) {
//		// TODO Auto-generated method stub
//		seq.put("KEVAL_ERROR");
//		return null;
//	}

      @Override
      public Object visit(ArrayType arrayType) {
         visitDisptch(arrayType.tpar1);
         seq.put("[");
         visitDisptch(arrayType.tpar2);
         seq.put("]");
         return null;
      }
   }

   public Object visitDisptch(Exp exp) {
      return exp.accept(expPrinter);
   }

   ExpPrinter expPrinter = new ExpPrinter();
   public class ExpPrinter implements Visitor.ExpVisitor<Object> {

      @Override
      public Object visit(Selection selection) {
         return null;
      }

      @Override
      public Object visit(Tuple tuple) {
         return null;
      }

/*      //@Override
      public Object visit(Record record) {
         return null;
      }*/

      @Override
       public Object visit(Quantifier quantifier) {
           return null;
       }

       @Override
      public Object visit(NOp nOp) {
         return null;
      }

      class ZOpPrinter implements Visitor.ExpVisitor.ZOpVisitor<Object> {
         @Override
         public Object visit(SingeltonLiteral singeltonLiteral) {
            return null;
         }

         public Object visit(Var var) {
            seq.put(var.name);
            return null;
         }
         public Object visit(IntLiteral intLiteral) {
            seq.put(intLiteral.i + "");
            return null;
         }

         @Override
         public Object visit(Inf inf) {
            seq.put("∞");
            return null;
         }

         public Object visit(None none) {
            seq.put("none");
            return null;
         }

         @Override
         public Object visit(True aTrue) {
            seq.put("true");
            return null;
         }

         @Override
         public Object visit(False aFalse) {
            seq.put("false");
            return null;
         }

         @Override
         public Object visit(Epsilon epsilon) {
            seq.put("ε");
            return null;
         }

      }

      ZOpPrinter zOpPrinter = new ZOpPrinter();

      class UOpPrinter implements Visitor.ExpVisitor.UOpVisitor<Object> {

         @Override
         public Object visit(SingeltonTuple singeltonTuple) {
            return null;
         }

         @Override
          public Object visit(SetComplement setComplement) {
              return null;
          }

          @Override
          public Object visit(SetCardinality setCardinality) {
              return null;
          }

          public Object visit(Some some) {
            seq.put("some(");
            visitDisptch(some.arg);
            seq.put(")");
            return null;
         }

         @Override
         public Object visit(Fst fst) {
            seq.put("fst(");
            visitDisptch(fst.arg);
            seq.put(")");
            return null;
         }

         @Override
         public Object visit(Snd snd) {
            seq.put("snd(");
            visitDisptch(snd.arg);
            seq.put(")");
            return null;
         }

         @Override
         public Object visit(Not not) {
            seq.put("!(");
            visitDisptch(not.arg);
            seq.put(")");
            return null;
         }

         @Override
         public Object visit(Abs abs) {
            seq.put("|");
            visitDisptch(abs.arg);
            seq.put("|");
            return null;
         }

         @Override
         public Object visit(Projection projection) {
            return null;
         }
         @Override
         public Object visit(ArraySelect arraySelect) {
            return null;
         }
         @Override
         public Object visit(ArrayTypeConstructor arrayTypeConstructor){return null;}
      }

      UOpPrinter uOpPrinter = new UOpPrinter();

      class BOpPrinter implements Visitor.ExpVisitor.BOpVisitor<Object> {

         @Override
         public Object visit(Multiply multiply) {
            return null;
         }

         @Override
         public Object visit(Gte gte) {
            return null;
         }

         @Override
         public Object visit(SetProduct setProduct) {
            return null;
         }

         @Override
          public Object visit(SetMinus setMinus) {
              return null;
          }

          @Override
          public Object visit(SetIntersection setIntersection) {
              return null;
          }

          @Override
          public Object visit(SetMembership setMembership) {
              return null;
          }

          @Override
          public Object visit(SetSubset setSubset) {
              return null;
          }

          @Override
         public Object visit(SetUnion setUnion) {
            return null;
         }

         @Override
          public Object visit(ArrayMax max) {
              seq.put("max-arr(");
              visitDisptch(max.arg1);
              seq.put(", ");
              visitDisptch(max.arg2);
              seq.put(")");
              return null;
          }

          @Override
          public Object visit(ArrayMin min) {
              seq.put("min-arr(");
              visitDisptch(min.arg1);
              seq.put(", ");
              visitDisptch(min.arg2);
              seq.put(")");
              return null;
          }


          public Object visit(Plus plus) {
            visitDisptch(plus.arg1);
            seq.put(" + ");
            visitDisptch(plus.arg2);
            return null;
         }

         public Object visit(Minus minus) {
            visitDisptch(minus.arg1);
            seq.put(" - ");
            visitDisptch(minus.arg2);
            return null;
         }

         public Object visit(Eq eq) {
//         seq.put("(");
            visitDisptch(eq.arg1);
            seq.put(" = ");
            visitDisptch(eq.arg2);
//         seq.put(")");
            return null;
         }

         @Override
         public Object visit(NEq nEq) {
            visitDisptch(nEq.arg1);
            seq.put(" != ");
            visitDisptch(nEq.arg2);
            return null;
         }

         @Override
         public Object visit(Lt lt) {
            visitDisptch(lt.arg1);
            seq.put(" < ");
            visitDisptch(lt.arg2);
            return null;
         }

         @Override
         public Object visit(Gt gt) {
            visitDisptch(gt.arg1);
            seq.put(" > ");
            visitDisptch(gt.arg2);
            return null;
         }

         @Override
         public Object visit(Min min) {
            seq.put("min(");
            visitDisptch(min.arg1);
            seq.put(", ");
            visitDisptch(min.arg2);
            seq.put(")");
            return null;
         }

         @Override
         public Object visit(Max max) {
            seq.put("max(");
            visitDisptch(max.arg1);
            seq.put(", ");
            visitDisptch(max.arg2);
            seq.put(")");
            return null;
         }

         @Override
         public Object visit(Pair pair) {
            seq.put("pair(");
            visitDisptch(pair.arg1);
            seq.put(", ");
            visitDisptch(pair.arg2);
            seq.put(")");
            return null;
         }

         @Override
         public Object visit(And and) {
            seq.put("(");
            visitDisptch(and.arg1);
            seq.put(" && ");
            visitDisptch(and.arg2);
            seq.put(")");
            return null;
         }

         @Override
         public Object visit(Or or) {
            seq.put("(");
            visitDisptch(or.arg1);
            seq.put(" || ");
            visitDisptch(or.arg2);
            seq.put(")");
            return null;
         }

//         @Override
//         public Object visit(Select select) {
//            visitDisptch(select.arg1);
//            seq.put("[");
//            visitDisptch(select.arg2);
//            seq.put("]");
//            return null;
//         }

         @Override
         public Object visit(Implication implication) {
            return null;
         }
      }

      BOpPrinter bOpPrinter = new BOpPrinter();

      class TOpPrinter implements Visitor.ExpVisitor.TOpVisitor<Object> {

          @Override
          public Object visit(Store store) {
             visitDisptch(store.arg1);
             seq.put("[");
             visitDisptch(store.arg2);
             seq.put("->");
             visitDisptch(store.arg3);
             seq.put("]");
             return null;
          }
      }

      TOpPrinter tOpPrinter = new TOpPrinter();


      public Object visit(ZOp zOp) {
         return zOp.accept(zOpPrinter);
      }

      public Object visit(UOp uOp) {
         return uOp.accept(uOpPrinter);
      }

      public Object visit(BOp bOp) {
         return bOp.accept(bOpPrinter);
      }

      public Object visit(TOp tOp) {
         return tOp.accept(tOpPrinter);
      }

      public Object visit(ITE ite) {
         boolean middle = false;
         if (!seq.atBeg()) {
            seq.endLine();
            seq.incIndent();
            middle = true;
         }
         seq.put("if (");
         visitDisptch(ite.condExp);
         seq.put(")");
         seq.endLine();
         seq.incIndent();
         visitDisptch(ite.thenExp);
         seq.endLine();
         seq.decIndent();
         seq.put("else");
         seq.endLine();
         seq.incIndent();
         visitDisptch(ite.elseExp);
         seq.decIndent();
         if (middle) {
            seq.decIndent();
//            seq.endLine();
         }

         return null;
      }

      @Override
      public Object visit(MatchOExp match) {
         boolean middle = false;
         if (!seq.atBeg()) {
            seq.endLine();
            seq.incIndent();
            middle = true;
         }
         seq.put("match ");
         visitDisptch(match.matchExp);
         seq.put(" with");
         seq.endLine();
         seq.put("| some(");
         visitDisptch(match.someVar);
         seq.put(") =>");
         seq.endLine();
         seq.incIndent();
         visitDisptch(match.someExp);
         seq.decIndent();
         seq.endLine();
         seq.put("| none =>");
         seq.endLine();
         seq.incIndent();
         visitDisptch(match.noneExp);
         seq.endLine();
         seq.decIndent();
         seq.put("end");
         if (middle)
            seq.decIndent();
         return null;
      }

      @Override
      public Object visit(Call call) {
         seq.put(call.funName);
         seq.put("(");
         for (int i = 0; i < call.args.length; i++) {
            Exp arg = call.args[i];
            visitDisptch(arg);
            if (i != call.args.length - 1)
               seq.put(", ");
         }
         seq.put(")");
         return null;
      }

      //(state.employees WITH [e.0] := (TRUE, e.1, e.2))
      @Override
      public Object visit(ArrayAssign arrayAssign) {
         seq.put("(");
         visitDisptch(arrayAssign.originalArray);
         seq.put(" WITH [");
         visitDisptch(arrayAssign.index);
         seq.put("] := ");
         visitDisptch(arrayAssign.assignedValue);
         seq.put(")");
         return null;
      }

      public Object visitDisptch(Exp exp) {
         return exp.accept(this);
      }
   }

   public Object visitDisptch(Statement st) {
      return st.accept(stPrinter);
   }

   StVisitor stPrinter = new StVisitor();
   public class StVisitor implements Visitor.StVisitor {

      @Override
      public Object visit(Assignment assignment) {
         visitDisptch(assignment.var);
         seq.put(" := ");
         visitDisptch(assignment.exp);
         seq.put(";");
         seq.endLine();
         return null;
      }

      @Override
      public Object visit(IfThen ifThen) {
         seq.put("if (");
         visitDisptch(ifThen.cond);
         seq.put(")");
         seq.endLine();
         seq.incIndent();
         visitDisptch(ifThen.ifSt);
         seq.decIndent();
         return null;
      }

      @Override
      public Object visit(IfThenElse ifThenElse) {
         seq.put("if (");
         visitDisptch(ifThenElse.cond);
         seq.put(")");
         seq.endLine();
         seq.incIndent();
         visitDisptch(ifThenElse.ifSt);
         seq.decIndent();
         seq.put("else");
         seq.endLine();
         seq.incIndent();
         visitDisptch(ifThenElse.elseSt);
         seq.decIndent();
         return null;
      }

      @Override
      public Object visit(For aFor) {
         seq.put("for (");
         visitDisptch(aFor.var);
         seq.put(" ∈ ");
         visitDisptch(aFor.set);
         seq.put(")");
         seq.endLine();
         seq.incIndent();
         visitDisptch(aFor.st);
         seq.decIndent();
         return null;
      }

      @Override
      public Object visit(StSeq stSeq) {
         for (Statement st : stSeq.sts)
            visitDisptch(st);
         return null;
      }

      @Override
      public Object visit(MatchOSt matchOSt) {
         seq.put("match ");
         visitDisptch(matchOSt.matchExp);
         seq.put(" with");
         seq.endLine();
         seq.put("| some(");
         visitDisptch(matchOSt.someVar);
         seq.put(") =>");
         seq.endLine();
         seq.incIndent();
         visitDisptch(matchOSt.someSt);
         seq.decIndent();
//         seq.endLine();
         seq.put("| none =>");
         seq.endLine();
         seq.incIndent();
         visitDisptch(matchOSt.noneSt);
         seq.decIndent();
         seq.put("end");
         seq.endLine();
         return null;
      }

      @Override
      public Object visit(Signal signal) {
         seq.put("signal(");
         visitDisptch(signal.arg1);
         seq.put(", ");
         visitDisptch(signal.arg2);
         seq.put(");");
         seq.endLine();
         return null;
      }

      @Override
      public Object visit(Return aReturn) {
         seq.put("return ");
//         seq.incIndent();
         visitDisptch(aReturn.arg);
         seq.put(";");
         seq.endLine();
//         seq.decIndent();
         return null;
      }

      @Override
      public Object visit(Skip skip) {
         seq.put(";");
         seq.endLine();
         return null;
      }

      @Override
      public Object visit(Decl decl) {
         visitDisptch(decl.var);
         seq.put(": ");
         visitDisptch(decl.type);
         seq.put(";");
         seq.endLine();
         return null;
      }

      @Override
      public Object visit(SetValue setValue) {
         seq.put("value(");
         visitDisptch(setValue.arg1);
         seq.put(") := ");
         visitDisptch(setValue.arg2);
         seq.put(";");
         seq.endLine();
         return null;
      }
   }
   public String getText() {
      return seq.get().print();
   }

}

