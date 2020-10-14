package jsrc.x10.ast.tree;

import jsrc.x10.ast.tree.type.NonVoidType;
/*
 User: lesani, Date: Nov 2, 2009, Time: 10:53:55 AM
*/

public class LateInitConstFieldDecl implements Node {

    NonVoidType type;
    String name;

    public LateInitConstFieldDecl(NonVoidType type, String name) {
        this.type = type;
        this.name = name;
    }

}
