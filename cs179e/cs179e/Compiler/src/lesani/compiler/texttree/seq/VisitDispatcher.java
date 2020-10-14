package lesani.compiler.texttree.seq;

import lesani.compiler.texttree.Text;
import lesani.compiler.ast.Node;

public interface VisitDispatcher {
    Text visitDispatch(Node node);
}
