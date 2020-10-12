package graph.spec;

import graph.spec.ast.BOp;
import graph.spec.ast.Cond;
import graph.spec.ast.Op;
import graph.spec.ast.UOp;
import graph.spec.type.Type;
import lesani.collection.option.None;
import lesani.collection.option.Option;

public abstract class Spec implements java.io.Serializable {

   public abstract BOp aggrOp();

   public abstract Dir getDir();

   public abstract Cond pathCond();

   public abstract UOp pathFun();
   public Option<UOp> pathFun2() {
      return None.instance();
   }

   public Type getRType() {
      Type rType = null;
      if (pathFun2().isPresent()) {
         rType = pathFun2().value().getSig().rType;
      } else {
         rType = pathFun().getSig().rType;
      }
      return rType;
   }
}
