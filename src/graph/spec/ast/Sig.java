package graph.spec.ast;


import graph.spec.type.Type;

public class Sig {
   public Type[] par;
   public Type rType;

   public Sig(Type[] par, Type rType) {
      this.par = par;
      this.rType = rType;
   }
}
