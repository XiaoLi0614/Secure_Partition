package lesani.compiler.ast.optional;

import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.compiler.ast.Node;
//import jsrc.x10.ast.visitor.SVisitor;

/*
 User: lesani, Date: Nov 2, 2009, Time: 9:00:08 AM
*/


public abstract class OptionalNode implements Node {
   public abstract boolean isPresent();
//    public <R> R accept(SVisitor<R> v) {
//        return null;
//    }
   public abstract <T2> T2 apply(Fun0<T2> fNone, Fun<Node, T2> fSome);
}

