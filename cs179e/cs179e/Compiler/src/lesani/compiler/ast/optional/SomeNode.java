package lesani.compiler.ast.optional;

import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.compiler.ast.Node;

/*
 User: lesani, Date: Nov 2, 2009, Time: 9:10:12 AM
*/

public class SomeNode extends OptionalNode {
   Node node;

   public SomeNode(Node node) {
      this.node = node;
   }

   public boolean isPresent() {
      return (node != null);
   }

   @Override
   public <T2> T2 apply(Fun0<T2> fNone, Fun<Node, T2> fSome) {
      return fSome.apply(node);
   }

   public Node get() {
      return node;
   }

   public void set(Node node) {
      this.node = node;
   }

}
