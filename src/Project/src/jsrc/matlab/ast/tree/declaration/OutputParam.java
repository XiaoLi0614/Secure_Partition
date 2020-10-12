package jsrc.matlab.ast.tree.declaration;

import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.typeinference.type.Type;

public class OutputParam extends VarDecl {

    public OutputParam(/*Type type, */Id name) {
        super(/*type, */name);
    }

//    public OutputParam(Id id) {
//        super(id);
//    }
}
