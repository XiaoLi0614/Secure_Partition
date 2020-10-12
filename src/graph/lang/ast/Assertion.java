package graph.lang.ast;


import graph.lang.visitor.IPrinter;

import java.util.HashMap;

public class Assertion implements java.io.Serializable {

   public Exp assertion;

   public Assertion(Exp exp) {
      this.assertion = exp;
   }

   @Override
   public String toString() {
      return "assertion";
   }


}


