package jsrc.matlab.ast.tree.declaration.type;

import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.type.TypeVar;

public class IntType extends ScalarType {
    private static IntType theInstance = new IntType();

    public static IntType instance() {
        return theInstance;
    }

    private IntType() {
    }

    public boolean contains(TypeVar typeVar) {
        return false;
    }

    @Override
    public String toString() {
        return "Int";
//        return "Int" + super.toString();
    }

    public <T> T accept(SVisitor.TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
