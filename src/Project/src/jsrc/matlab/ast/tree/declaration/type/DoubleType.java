package jsrc.matlab.ast.tree.declaration.type;

import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.type.TypeVar;

public class DoubleType extends ScalarType {
    private static DoubleType theInstance = new DoubleType();

    public static DoubleType instance() {
        return theInstance;
    }

    private DoubleType() {
    }

    @Override
    public boolean contains(TypeVar typeVar) {
        return false;
    }

    @Override
    public String toString() {
        return "Double";
    }

    public <T> T accept(SVisitor.TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
