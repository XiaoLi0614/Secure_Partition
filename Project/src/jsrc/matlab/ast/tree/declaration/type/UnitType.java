package jsrc.matlab.ast.tree.declaration.type;

import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.type.TypeVar;

public class UnitType extends ScalarType {
    private static UnitType theInstance = new UnitType();

    public static UnitType instance() {
        return theInstance;
    }

    private UnitType() {
    }

    public boolean contains(TypeVar typeVar) {
        return false;
    }

    public <T> T accept(SVisitor.TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Unit";
    }
}
