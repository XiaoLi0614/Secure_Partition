package jsrc.matlab.ast.tree.expression.op;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.visitor.SVisitor;
import lesani.compiler.ast.LocInfo;


public abstract class Op extends Expression {

    public abstract String getName();
    public abstract String getLexeme();

    public abstract Expression[] getOperands();



    public <R> R accept(SVisitor.ExpressionVisitor<R> v) {
        return v.visit(this);
    }

    abstract public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor<R> v);

}
