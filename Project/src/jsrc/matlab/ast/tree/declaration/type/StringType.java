package jsrc.matlab.ast.tree.declaration.type;

import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.type.TypeVar;

public class StringType extends ScalarType {
    private static StringType theInstance = new StringType();

    public static StringType instance() {
        return theInstance;
    }

    private StringType() {
    }

    public boolean contains(TypeVar typeVar) {
        return false;
    }

    @Override
    public String toString() {
        return "String";
    }

    @Override
    public <T> T accept(SVisitor.TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}