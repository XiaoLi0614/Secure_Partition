package jsrc.x10.ast.tree.xtras;

import jsrc.x10.ast.tree.declaration.Constructor;
import jsrc.x10.ast.tree.declaration.Field;
/*
 User: lesani, Date: Nov 2, 2009, Time: 10:02:58 AM
*/

public class ValueClassDecl implements TopLevelDecl {

    String name;
    Field[] lateInitConstFields;
    Constructor[] constructors;

    public ValueClassDecl(String name, Field[] lateInitConstFields, Constructor[] constructors) {
        this.name = name;
        this.lateInitConstFields = lateInitConstFields;
        this.constructors = constructors;
    }

}