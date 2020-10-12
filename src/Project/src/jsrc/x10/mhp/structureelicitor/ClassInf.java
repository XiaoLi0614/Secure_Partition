package jsrc.x10.mhp.structureelicitor;

import jsrc.x10.ast.tree.declaration.Field;
import jsrc.x10.ast.tree.declaration.Method;
import jsrc.x10.ast.tree.type.Type;

import java.util.Map;
import java.util.Set;

public class ClassInf {
    public Map<String, Field> fields;
    public Map<String, Method> methods;

    public ClassInf(Map<String, Field> fields, Map<String, Method> methods) {
        this.fields = fields;
        this.methods = methods;
    }
}
