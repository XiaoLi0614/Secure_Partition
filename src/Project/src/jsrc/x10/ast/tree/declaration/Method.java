package jsrc.x10.ast.tree.declaration;

import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.type.Type;
import jsrc.x10.ast.tree.statement.Block;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
/*
 User: lesani, Date: Nov 2, 2009, Time: 10:44:50 AM
*/

public class Method implements ClassMember {

    public Visibility visibility = Visibility.PUBLIC;
    public boolean isProto = false;
    public boolean isStatic = false;
    public boolean isFinal = false;
    public Type type;
    public Id name;
    public Option<FormalParam[]> formalParams;
    public Block block;

    public Method(boolean isStatic, Type type, Id name, FormalParam[] formalParams, Block block) {
        this.isStatic = isStatic;
        this.type = type;
        this.name = name;
        this.formalParams = new Some<FormalParam[]>(formalParams);
        this.block = block;
    }

    public Method(Visibility visibility, boolean isStatic, boolean isFinal, Type type, Id name, FormalParam[] formalParams, Block block) {
        this.visibility = visibility;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.type = type;
        this.name = name;
        this.formalParams = new Some<FormalParam[]>(formalParams);
        this.block = block;
    }

    public Method(Visibility visibility, boolean isStatic, boolean isFinal, Type type, String name, Option<FormalParam[]> formalParams, Block block) {
        this.visibility = visibility;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.type = type;
        this.name = new Id(name);
        this.formalParams = formalParams;
        this.block = block;
    }

    public Method(Visibility visibility, boolean isStatic, boolean isFinal, Type type, String name, FormalParam[] formalParams, Block block) {
        this.visibility = visibility;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.type = type;
        this.name = new Id(name);
        this.formalParams = new Some<FormalParam[]>(formalParams);
        this.block = block;
    }

}
