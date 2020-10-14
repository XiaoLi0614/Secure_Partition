package jsrc.matlab.ast.tree.declaration.type;

import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.type.TypeVar;

public class BoolType extends ScalarType {
    private static BoolType theInstance = new BoolType();

    public static BoolType instance() {
        return theInstance;
    }

    private BoolType() {
    }

    public boolean contains(TypeVar typeVar) {
        return false;
    }

    @Override
    public String toString() {
        return "Bool";
    }

    @Override
    public <T> T accept(SVisitor.TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}