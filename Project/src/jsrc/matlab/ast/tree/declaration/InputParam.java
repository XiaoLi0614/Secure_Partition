package jsrc.matlab.ast.tree.declaration;

import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.typeinference.type.Type;

/*
 User: lesani, Date: Nov 2, 2009, Time: 11:16:38 AM
*/

public class InputParam extends VarDecl {
    public InputParam(/*Type type,*/ Id name) {
        super(/*type,*/ name);
    }

//    public InputParam(Id id) {
//        super(id);
//    }
}
