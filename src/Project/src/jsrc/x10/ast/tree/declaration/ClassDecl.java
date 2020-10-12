package jsrc.x10.ast.tree.declaration;

import jsrc.x10.ast.tree.Node;
import jsrc.x10.ast.tree.expression.id.Id;
/*
 User: lesani, Date: Nov 2, 2009, Time: 10:02:58 AM
*/

public class ClassDecl implements Node {
    public Visibility visibility = Visibility.PACKAGE; 
    public boolean isValue = false;
    public Id name;
    public Field[] fields;
    public Constructor[] constructors;
    public Method[] methods;

    public ClassDecl(Id name, Field[] fields, Constructor[] constructors) {
        this.name = name;
        this.fields = fields;
        this.constructors = constructors;
        this.methods = new Method[0];
    }


    public ClassDecl(boolean value, Id name, Field[] fields, Constructor[] constructors) {
        isValue = value;
        this.name = name;
        this.fields = fields;
        this.constructors = constructors;
        this.methods = new Method[0];
    }

    public ClassDecl(Id name, Field[] fields, Method[] methods) {
        this.name = name;
        this.fields = fields;
        this.constructors = new Constructor[0];
        this.methods = methods;
    }

    public ClassDecl(Visibility visibility, Id name, Field[] fields, Constructor[] constructors, Method[] methods) {
        this.visibility = visibility;
        this.name = name;
        this.fields = fields;
        this.constructors = constructors;
        this.methods = methods;
    }

    public ClassDecl(Id name, Method[] methods) {
        this.name = name;
        this.fields = new Field[0];
        this.constructors = new Constructor[0];
        this.methods = methods;
    }

    public ClassDecl(Visibility visibility, Id name, Method[] methods) {
        this.visibility = visibility;
        this.name = name;
        this.fields = new Field[0];
        this.constructors = new Constructor[0];
        this.methods = methods;
    }

    public ClassDecl(Id name, Field[] fields, Constructor[] constructors, Method[] methods) {
        this.name = name;
        this.fields = fields;
        this.constructors = constructors;
        this.methods = methods;
    }

    public ClassDecl(boolean value, Id name, Field[] fields, Constructor[] constructors, Method[] methods) {
        this.isValue = value;
        this.name = name;
        this.fields = fields;
        this.constructors = constructors;
        this.methods = methods;
    }

}

