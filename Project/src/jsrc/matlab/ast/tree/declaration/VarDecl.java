package jsrc.matlab.ast.tree.declaration;

import lesani.compiler.ast.Node;
import jsrc.matlab.ast.tree.expression.Id;

public abstract class VarDecl implements Node {
//    public Type type;
    public Id id;

//    public VarDecl(Type type, Id id) {
//        this.type = type;
//        this.id = id;
//    }

    public VarDecl(Id id) {
        this.id = id;
//        this.type = UnknownType.instance();
    }
}
