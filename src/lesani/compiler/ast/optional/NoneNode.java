package lesani.compiler.ast.optional;

/*
 User: lesani, Date: Nov 2, 2009, Time: 9:14:06 AM
*/

import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.compiler.ast.Node;

public class NoneNode extends OptionalNode {
    private static NoneNode theInstance = new NoneNode();

    public static NoneNode instance() {
        return theInstance;
    }

    private NoneNode() {
    }

    public boolean isPresent() {
        return false;
    }

   @Override
   public <T2> T2 apply(Fun0<T2> fNone, Fun<Node, T2> fSome) {
      return fNone.apply();
   }

}

