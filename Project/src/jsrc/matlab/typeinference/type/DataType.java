package jsrc.matlab.typeinference.type;

import jsrc.matlab.ast.visitor.SVisitor;

public interface DataType extends Type {
    public abstract <T> T accept(SVisitor.TypeVisitor<T> visitor);
}
