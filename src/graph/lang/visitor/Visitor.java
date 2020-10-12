package graph.lang.visitor;

import graph.lang.ast.*;
import graph.lang.type.*;


public interface Visitor<R> {

   R visit(Fun fun);
   R visit(TDecl tDecl);
   R visit(VarDecl varDecl);
   R visit(TypeDecl typeDecl);
   R visit(SortType sortType);
   R visit(Assertion assertion);


   interface TypeVisitor<R> {
      R visit(BoolType boolType);
      R visit(IntType intType);
      R visit(FloatType floatType);
//      R visit(EmptyType emptyType);
      R visit(OptionType optionType);
      R visit(VertexType vertexType);
      R visit(EdgeType edgeType);
      R visit(VoidType voidType);
      R visit(TypeVar typeVar);
      R visit(DirType dirType);
      R visit(VIdType idType);


      R visit(PairType pairType);
      R visit(ArrayType arrayType);
      R visit(SetType setType);

      R visit(RecordType recordType);
      R visit(TupleType tupleType);

      R visit(SortType sortType);


      // R visit(SetType type);
   }

   interface ExpVisitor<R> {

      R visit(ZOp zOp);
      R visit(UOp uOp);
      R visit(BOp bOp);
      R visit(TOp tOp);
      R visit(NOp nOp);
      R visit(ITE ite);
      R visit(MatchOExp match);
      R visit(Call call);
      R visit(ArrayAssign arrayAssign);
      R visit(Quantifier quantifier);
      R visit(Tuple tuple);
      R visit(Selection selection);


      interface ZOpVisitor<S> {
         S visit(Var var);
         S visit(IntLiteral intLiteral);
         S visit(Inf inf);
         S visit(None none);

         S visit(True aTrue);
         S visit(False aFalse);
         S visit(Epsilon epsilon);

         S visit(SingeltonLiteral singeltonLiteral);
      }

      interface UOpVisitor<S> {
         S visit(Some some);
         S visit(Not not);

         S visit(Abs abs);

         S visit(Fst fst);
         S visit(Snd snd);

         S visit(Projection projection);
         S visit(ArraySelect arraySelect);

         S visit(SetComplement setComplement);
         S visit(SetCardinality setCardinality);
         S visit(SingeltonTuple singeltonTuple);
         S visit(ArrayTypeConstructor arrayTypeConstructor);

      }

      interface BOpVisitor<S> {
         S visit(Plus plus);
         S visit(Minus minus);
         S visit(Multiply multiply);
         S visit(Eq eq);
         S visit(NEq nEq);
         S visit(Lt lt);
         S visit(Gt gt);
         S visit(Gte gte);
         S visit(Min min);
         S visit(Max max);
         S visit(Pair pair);
         S visit(And and);
         S visit(Or or);

         S visit(ArrayMax arrayMax);
         S visit(ArrayMin arrayMin);

         S visit(SetUnion setUnion);
         S visit(SetMinus setMinus);
         S visit(SetIntersection setIntersection);
         S visit(SetMembership setMembership);
         S visit(SetSubset setSubset);
         S visit(SetProduct setProduct);

         S visit(Implication implication);

      }

      interface TOpVisitor<S> {
         S visit(Store store);

      }

      interface NOpVisitor<S> {
         S visit(RecordTypeConstructor recordTypeConstructor);

      }




   }

   interface StVisitor<R> {
      R visit(Assignment assignment);
      R visit(IfThen ifThen);
      R visit(IfThenElse ifThenElse);
      R visit(For aFor);
      R visit(StSeq stSeq);
      R visit(MatchOSt matchOSt);
      R visit(Signal signal);
      R visit(Return aReturn);
      R visit(Skip skip);

      R visit(Decl decl);
      R visit(SetValue setValue);


   }
}

