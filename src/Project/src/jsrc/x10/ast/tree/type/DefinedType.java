package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.visitor.DFSVisitor;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
/*
 User: lesani, Date: Nov 2, 2009, Time: 1:20:03 PM
*/

public class DefinedType extends ScalarType {
    public String name;
    public Option<Type[]> typeParams = None.instance();
    public Option<String[]> properties = None.instance();


    public DefinedType(String name) {
        this.name = name;
    }

    public DefinedType(String name, String[] properties) {
        this.name = name;
        this.properties = new Some<String[]>(properties);
    }

    public DefinedType(String name, Type[] typeParams) {
        this.name = name;
        this.typeParams = new Some<Type[]>(typeParams);
    }

    public DefinedType(String name, Type[] typeParams, String[] properties) {
        this.name = name;
        this.typeParams = new Some<Type[]>(typeParams);
        this.properties = new Some<String[]>(properties);
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }

}
