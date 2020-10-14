package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.tree.type.constraint.Constraint;
import jsrc.x10.ast.visitor.DFSVisitor;
import lesani.collection.option.*;


/**
 * User: Mohsen
 * Date: Aug 26, 2009
 */

public class ArrayType extends NonVoidType {
    public boolean isConstant = false;
    public Type elementType;
    public Option<Constraint[]> constraints = None.instance();

    public ArrayType(Type elementType) {
        this.isConstant = false;
        this.elementType = elementType;
    }

    public ArrayType(boolean isConstant, Type elementType) {
        this.isConstant = isConstant;
        this.elementType = elementType;
    }

    public ArrayType(Type elementType, Constraint[] constraints) {
        this.elementType = elementType;
        this.constraints = new Some<Constraint[]>(constraints);
    }

    public ArrayType(boolean isConstant, Type elementType, Constraint[] constraints) {
        this.isConstant = isConstant;
        this.elementType = elementType;
        this.constraints = new Some<Constraint[]>(constraints);
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }
}
