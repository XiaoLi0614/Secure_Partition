package graph.lang.type;

import graph.lang.ast.Sig;
import graph.lang.ast.TDecl;
import graph.lang.visitor.Visitor;

public class RecordType implements Type {

   public int arity;
   public TDecl[] tDeclPars;
   public String sortName;

   public RecordType(String name, Sig sig) {
      arity = sig.pars.length;
      tDeclPars = sig.pars;
      this.sortName = name;
   }

   public String[] allFieldNames(){
      String[] fieldNames = new String[this.tDeclPars.length];
      for(int i = 0; i < this.tDeclPars.length; i++){
         fieldNames[i] = tDeclPars[i].name;
      }
      return fieldNames;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      RecordType recordType = (RecordType) o;

      if (!tDeclPars.equals(recordType.tDeclPars)) return false;
      return true;
   }

   @Override
   public int hashCode() {
      int result = tDeclPars.hashCode();
      return result;
   }

   public <R> R accept(Visitor.TypeVisitor<R> v) {
       return v.visit(this);
   }

   @Override
   public String toString() {
      return "[#" + " record " + "#]";
   }
}
