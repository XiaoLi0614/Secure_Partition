package jsrc.x10.ast.tree.xtras;

import jsrc.x10.ast.tree.Node;
import jsrc.x10.ast.tree.type.NonVoidType;
import jsrc.x10.ast.tree.expression.MethodCall;
/*
 User: lesani, Date: Nov 2, 2009, Time: 10:43:29 AM
*/

public class ProgramClassFieldDecl implements Node {
    NonVoidType type;
    String name;
    MethodCall methodCall;

    public ProgramClassFieldDecl(NonVoidType type, String name, MethodCall methodCall) {
        this.type = type;
        this.name = name;
        this.methodCall = methodCall;
    }

}
