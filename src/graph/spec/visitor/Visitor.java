package graph.spec.visitor;

import graph.spec.ast.*;
import graph.spec.type.*;

public interface Visitor<R> {

   R visit(Eq eq);
   R visit(And and);
   R visit(True aTrue);
   R visit(False aFalse);

   interface ExpVisitor<R> {

      R visit(OpApp opApp);
      R visit(Num num);
      R visit(Var var);

      interface VarVisitor<R> {
         R visit(VVar vVar);
         R visit(Src src);
         R visit(PVar pVar);
      }

      interface OpVisitor<R> {
         R visit(UOp uop);
         R visit(BOp uop);

         interface UOpVisitor<R> {
            R visit(Length length);
            R visit(WeightOfPath weightOfPath);
            R visit(CapOfPath capOfPath);

            R visit(SrcOfPath srcOfPath);
            R visit(PenultimateOfPath penultimateOfPath);

            R visit(Compose compose);
            R visit(VId id);
         }

         interface BOpVisitor<R> {
            R visit(Min min);
            R visit(Max max);
         }
      }
   }

   interface TypeVisitor<R> {
      R visit(BoolType boolType);
      R visit(IntType intType);
      R visit(VertexType vertexType);
      R visit(EdgeType edgeType);
      R visit(PathType pathType);
      R visit(VIdType idType);
   }

}

// --: unimportant
// ??: has a name?
// X: already done
// ! not done yet

//                    Itself,                  Src,  Pen
// Min, L, Src   ->   SS Shortest Length  X,   --,   BFS X
// Min, W, Src   ->   SS Shortest Path  X,   --,   ??
// Min, C, Src   ->   SS Narrowest Path !,   --,   --

// Min, L, True  ->   All Shortest Length  --,   --,   --
// Min, W, True  ->   All Shortest Path  X,   !,   --
// Min, C, True  ->   All Narrowest Path  !,   !,  --


// Max, L, Src   ->   SS Longest Length  X,   --,   --
// Max, W, Src   ->   SS Longest Path  !,   --,   --
// Max, C, Src   ->   SS Widest Path  X,   --,   --

// Max, L, True  ->   Ecc  X,   !,   --
// Max, W, True  ->   W Ecc  X,   !,   --
// Max, C, True  ->   All Widest Path  !,   !,   --


// Radius = Min Ecc
// Diameter = Max Ecc


