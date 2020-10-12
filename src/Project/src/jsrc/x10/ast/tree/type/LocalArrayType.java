package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.visitor.DFSVisitor;

public class LocalArrayType extends NonVoidType {

    public Type elementType;

    public LocalArrayType(Type elementType) {
        this.elementType = elementType;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }
}
