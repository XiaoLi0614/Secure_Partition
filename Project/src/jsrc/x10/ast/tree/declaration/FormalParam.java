package jsrc.x10.ast.tree.declaration;

import jsrc.x10.ast.tree.Node;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.type.NonVoidType;
/*
 User: lesani, Date: Nov 2, 2009, Time: 11:16:38 AM
*/

public class FormalParam implements Node {
    public boolean isFinal = false;
    public NonVoidType type;
    public Id name;


    public FormalParam(NonVoidType type, String name) {
        this.type = type;
        this.name = new Id(name);
    }

    public FormalParam(NonVoidType type, Id name) {
        this.type = type;
        this.name = name;
    }

    public FormalParam(boolean aFinal, NonVoidType type, Id name) {
        isFinal = aFinal;
        this.type = type;
        this.name = name;
    }

}
