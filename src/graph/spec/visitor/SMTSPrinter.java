package graph.spec.visitor;

import graph.spec.Spec;
import graph.spec.ast.*;
import graph.spec.type.*;
import lesani.compiler.texttree.seq.TextSeq;

public class SMTSPrinter {

   Spec spec;
   TextSeq seq;

   public SMTSPrinter() {
      seq = new TextSeq();
   }

   public SMTSPrinter(TextSeq seq) {
      this.seq = seq;
   }

   public SMTSPrinter(Spec spec) {
      seq = new TextSeq();
      this.spec = spec;
   }

   public static String print(Spec spec) {
      SMTSPrinter p = new SMTSPrinter(spec);

      p.printAggrOp();
      p.printPathFun();
      p.printPathCond();

      return p.getText();
   }


   private void printAggrOp() {
      // (define-fun aggr ((a Int) (b Int)) Int
      //   (min a b)
      //)
//      throw new RuntimeException(
//                  "Aggregation function type incorrect.");

      Type rType = spec.pathFun().getSig().rType;

//      if (!spec.pathFun2().isPresent()) {

         seq.put("(define-fun aggr ((a ");
         visitDispatch(rType);
         seq.put(") (b ");
         visitDispatch(rType);
         seq.put(")) ");
         visitDispatch(rType);
         seq.endLine();

         seq.incIndent();
         seq.put("(");
         visitDispatch(spec.aggrOp());
         if (rType != VIdType.getInstance())
            seq.put("-b");
         seq.put(" a b)");
         seq.endLine();
         seq.decIndent();

         seq.put(")");
         seq.endLine();
         seq.endLine();

//      } else {
//         Type rType2 = spec.pathFun().getSig().rType;
//
//         seq.put("(define-fun aggr ((a ");
//         visitDispatch(rType);
//         seq.put(") (av ");
//         visitDispatch(rType2);
//         seq.put(") (b ");
//         visitDispatch(rType);
//         seq.put(") (bv ");
//         visitDispatch(rType2);
//         seq.put(")) ");
//         visitDispatch(rType2);
//         seq.endLine();
//
//         seq.incIndent();
//         seq.put("(");
//         visitDispatch(spec.aggrOp());
//         if (rType != VIdType.getInstance())
//            seq.put("-b");
//         seq.put("-2");
//         if (rType2 != VIdType.getInstance())
//            seq.put("-b");
//         seq.put(" a av b bv)");
//         seq.endLine();
//         seq.decIndent();
//
//         seq.put(")");
//         seq.endLine();
//         seq.endLine();
//      }
   }


   private void printPathFun() {
      //(define-fun path-prop ((p Path)) Int
      //   (weight-of-p p)
      //)

      UOp op = spec.pathFun();
      Sig sig = op.getSig();
      if (sig.par.length != 1
            || sig.par[0] != PathType.getInstance())
         throw new RuntimeException(
               "Path function type incorrect.");

      seq.put("(define-fun path-fun ((p Path)) ");
      visitDispatch(sig.rType);
      seq.endLine();

      seq.incIndent();
      visitDispatch(new OpApp(op, PVar.getInstance()));
      seq.endLine();
      seq.decIndent();

      seq.put(")");
      seq.endLine();
      seq.endLine();

      if (spec.pathFun2().isPresent()) {
         UOp op2 = spec.pathFun2().value();
         Sig sig2 = op2.getSig();
         if (sig2.par.length != 1
               || sig2.par[0] != PathType.getInstance())
            throw new RuntimeException(
                  "Path function 2 type incorrect.");

         seq.put("(define-fun path-fun-2 ((p Path)) ");
         visitDispatch(sig2.rType);
         seq.endLine();

         seq.incIndent();
         visitDispatch(new OpApp(op2, PVar.getInstance()));
         seq.endLine();
         seq.decIndent();

         seq.put(")");
         seq.endLine();
         seq.endLine();

      }
   }


   private void printPathCond() {
      seq.put("(define-fun path-cond ((p Path)) Bool");
      seq.endLine();
      seq.incIndent();
      visitDispatch(spec.pathCond());
      seq.endLine();
      seq.decIndent();
      seq.put(")");
      seq.endLine();
      seq.endLine();
   }

   public static String printCond(Cond cond) {
      SMTSPrinter printer = new SMTSPrinter();
      printer.visitDispatch(cond);
      return printer.getText();
   }


   public Object visitDispatch(Cond cond) {
      return cond.accept(visitor);
   }

   public Object visitDispatch(Type type) {
      return type.accept(visitor.typeVisitor);
   }

   public Object visitDispatch(Exp exp) {
      return exp.accept(visitor.expVisitor);
   }

   public Object visitDispatch(Op op) {
      return op.accept(visitor.expVisitor.opVisitor);
   }

   SMTVisitor visitor = new SMTVisitor();
   public class SMTVisitor implements Visitor {

      public Object visit(Eq eq) {
         //      ObjectSeq seq = new ObjectSeq();
         seq.put("(= ");
         seq.endLine();
         seq.incIndent();
         visitDispatch(eq.arg1);
         seq.endLine();
         visitDispatch(eq.arg2);
         seq.endLine();
         seq.decIndent();
         seq.put(")");
         //      return seq.get();
         return null;
      }

      public Object visit(And and) {
         seq.put("(and ");
         seq.endLine();
         seq.incIndent();
         visitDispatch(and.cond1);
         seq.endLine();
         visitDispatch(and.cond2);
         seq.endLine();
         seq.decIndent();
         seq.put(")");
         //      return seq.get();
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


      SMTTypeVisitor typeVisitor = new SMTTypeVisitor();
      public class SMTTypeVisitor implements TypeVisitor<Object> {

         @Override
         public Object visit(BoolType boolType) {
            seq.put("Bool");
            return null;
         }

         @Override
         public Object visit(IntType intType) {
//            seq.put("Int");
            seq.put("BInt");
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
         public Object visit(PathType pathType) {
            seq.put("Path");
            return null;
         }

         @Override
         public Object visit(VIdType idType) {
            seq.put("Int");
            return null;
         }
      }

      SMTExpVisitor expVisitor = new SMTExpVisitor();
      public class SMTExpVisitor implements ExpVisitor<Object> {

         public Object visit(OpApp opApp) {
            seq.put("(");
            visitDispatch(opApp.op);
            seq.put(" ");
            visitDispatch(opApp.arg);
            seq.put(")");
            if (opApp.op instanceof Compose)
               seq.put(")");
            return null;
         }

         public Object visit(Num num) {
            seq.put("" + num.n);
            return null;
         }

         public Object visit(Var var) {
            visitDispatch(var);
            return null;
         }

         public Object visitDispatch(Var var) {
            return var.accept(varVisitor);
         }

         SMTVarVisitor varVisitor = new SMTVarVisitor();

         class SMTVarVisitor implements VarVisitor {
            public Object visit(Src src) {
               seq.put("src");
               return null;
            }

            public Object visit(VVar vVar) {
               seq.put("v");
               return null;
            }

            public Object visit(PVar pVar) {
               seq.put("p");
               return null;
            }
         }

         public Object visitDispatch(Op op) {
            return op.accept(opVisitor);
         }

         SMTOpVisitor opVisitor = new SMTOpVisitor();

         class SMTOpVisitor implements OpVisitor {

            class UOpV implements UOpVisitor<String> {
               public String arg;

               public String visit(Length length) {
//                  seq.put("(");
//                  seq.put("length ");
//                  seq.put(arg);
//                  return "(length " + arg + ")";
                  seq.put("length");
                  return null;
               }

               public String visit(WeightOfPath weightOfPath) {
//                  seq.put("(");
//                  seq.put("weight-of-p ");
//                  seq.put(arg);
//                  return "(weight-of-p " + arg + ")";
                  seq.put("weight-of-p");
                  return null;
               }

               @Override
               public String visit(CapOfPath capOfPath) {
//                  seq.put("(");
//                  seq.put("cap-of-p ");
//                  seq.put(arg);
//                  return "(cap-of-p " + arg + ")";
                  seq.put("cap-of-p");
                  return null;
               }

               public String visit(SrcOfPath srcOfPath) {
//                  seq.put("(");
//                  seq.put("src-of-p ");
//                  seq.put(arg);
//                  return "(src-of-p " + arg + ")";
                  seq.put("src-of-p");
                  return null;
               }

               @Override
               public String visit(PenultimateOfPath penultimateOfPath) {
                  seq.put("pen-of-p");
                  return null;
               }

               @Override
               public String visit(VId id) {
//                  seq.put("(");
//                  seq.put("id ");
//                  return "(id " + arg + ")";
                  seq.put("id");
                  return null;
               }

               @Override
               public String visit(Compose compose) {
//                  arg = visitDispatch(compose.op1);
//                  return visitDispatch(compose.op2);
                  visitDispatch(compose.op2);
                  seq.put(" (");
                  visitDispatch(compose.op1);
                  return null;
               }

               public String visitDispatch(UOp uop) {
                  return uop.accept(this);
               }

//               public Object handle(UOp uop) {
//                  String s = visitDispatch(uop);
//                  seq.put(s);
//                  return null;
//               }
            }
            UOpV uOpV = new UOpV();

            class BOpV implements BOpVisitor {

               public Object visit(Min min) {
                  seq.put("min");
//                  if (spec.getRType() == VIdType.getInstance())
//                     seq.put("min");
//                  else
//                     seq.put("min-b");
                  return null;
               }

               public Object visit(Max max) {
                  seq.put("max");
//                  if (spec.getRType() == VIdType.getInstance())
//                     seq.put("max");
//                  else
//                     seq.put("max-b");
                  return null;
               }
            }
            BOpV bOpV = new BOpV();

            @Override
            public Object visit(UOp uop) {
//               uop.accept(uOpV);
               uOpV.visitDispatch(uop);
//               uOpV.handle(uop);
               return null;
            }

            @Override
            public Object visit(BOp bop) {
               return bop.accept(bOpV);
            }
         }

         public Object visitDispatch(Exp exp) {
            return exp.accept(expVisitor);
         }

      }

   }

   public String getText() {
      return seq.get().print();
   }

}
