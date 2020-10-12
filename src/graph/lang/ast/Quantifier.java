package graph.lang.ast;


import graph.lang.visitor.IPrinter;
import graph.lang.visitor.Visitor;

import java.util.HashMap;

public class Quantifier extends Exp{

   public enum QUANTIFIER_TYPE
   {
      FORALL,
      EXISTS
   }

   public QUANTIFIER_TYPE type;
   public Sig sig;
   public Exp assertion;


   public Quantifier(QUANTIFIER_TYPE type, Sig sig, Exp assertion) {
      this.type = type;
      this.sig = sig;
      this.assertion = assertion;
   }

   @Override
   public String toString() {
      return IPrinter.print(this);
   }

   @Override
   public <R> R accept(Visitor.ExpVisitor<R> v) {
      return v.visit(this);
   }
}


