package jsrc.x10.ast.tree.xtras;

import jsrc.x10.ast.tree.Node;
import jsrc.x10.ast.tree.declaration.Field;
import jsrc.x10.ast.tree.declaration.Method;

/**
 * Mohsen, Date: Nov 1, 2009, Time: 3:57:38 PM
 */

public class ProgramClass implements Node {

    String name;
    Field[] aConsts;
    Method[] methods;

    public ProgramClass(String name, Field[] aConsts, Method[] methods) {
        this.name = name;
        this.aConsts = aConsts;
        this.methods = methods;
    }

}
