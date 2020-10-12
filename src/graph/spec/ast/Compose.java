package graph.spec.ast;

import graph.spec.type.IntType;
import graph.spec.type.PathType;
import graph.spec.type.Type;
import graph.spec.visitor.Visitor;

public class Compose extends UOp {
   // op2 (op1 ())
   public UOp op1;
   public UOp op2;

   public Compose(UOp op1, UOp op2) {
      this.op1 = op1;
      this.op2 = op2;
      if (!op1.getSig().rType.equals(op2.getSig().par[0]))
         throw new RuntimeException("Incompatible ops");
   }

   @Override
   public <R> R accept(Visitor.ExpVisitor.OpVisitor.UOpVisitor<R> v) {
      return v.visit(this);
   }

   @Override
   public Sig getSig() {
      return new Sig(
            op1.getSig().par,
            op2.getSig().rType);
   }

}
