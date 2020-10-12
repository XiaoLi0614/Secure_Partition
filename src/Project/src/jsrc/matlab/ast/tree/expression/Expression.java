package jsrc.matlab.ast.tree.expression;

import jsrc.matlab.typeinference.type.DataType;
import lesani.compiler.ast.LocInfo;
import lesani.compiler.ast.Node;
import jsrc.matlab.ast.visitor.SVisitor;

public abstract class Expression extends LocInfo implements Node {
    public DataType type; //= UnknownType.instance();

//    public <R> R accept(SVisitor<R> v) {
//        return v.visit(this);
//    }

    abstract public <R> R accept(SVisitor.ExpressionVisitor<R> v);

}
