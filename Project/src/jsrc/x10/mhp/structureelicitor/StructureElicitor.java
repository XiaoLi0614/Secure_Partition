package jsrc.x10.mhp.structureelicitor;

import jsrc.x10.ast.tree.declaration.ClassDecl;
import jsrc.x10.ast.tree.declaration.Field;
import jsrc.x10.ast.tree.declaration.File;
import jsrc.x10.ast.tree.declaration.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

import java.util.HashMap;
import java.util.Map;

public class StructureElicitor extends DFSVisitor {
    public Map<String, ClassInf> classInfs = new HashMap<String, ClassInf>();

//    public Map<String, Set<String>> classes = new HashMap<String, Set<String>>();

    public StructureElicitor(File file) {
        visitDispatch(file);
    }

    {
        fileVisitor = new FileVisitor() { {
            classVisitor = new ClassVisitor() {
                @Override
                public Object visitDispatch(ClassDecl classDecl) {
                    HashMap<String, Field> fieldInfs = new HashMap<String, Field>();
                    for (Field field : classDecl.fields) {
                        String name = field.name.lexeme;
                        fieldInfs.put(name, field);
                    }

                    HashMap<String, Method> methodInfs = new HashMap<String, Method>();
                    // We assume that we do not have method overloading.
                    for (Method method : classDecl.methods) {
                        String name = method.name.lexeme;
                        methodInfs.put(name, method);
                    }

                    classInfs.put(classDecl.name.lexeme, new ClassInf(fieldInfs, methodInfs));
                    return null;
                }
            };
        }};
    }

    public Map<String, ClassInf> getClassInfs() {
        return classInfs;
    }
}



