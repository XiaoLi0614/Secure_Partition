package jsrc.matlab.ast.tree.expression.op.constructors;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.visitor.SVisitor;
import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.collection.option.*;

public class RangeVectorConstructor extends Op {
    public Expression leftEndPoint;
    public Option<Expression> step = None.instance();
    public Expression rightEndPoint;

    public static String name = "$RangeVectorConstructor";
    public static String lexeme = "_:_:_";

    public RangeVectorConstructor(Expression leftEndPoint, Expression rightEndPoint) {
        this.leftEndPoint = leftEndPoint;
        this.rightEndPoint = rightEndPoint;
    }

    public RangeVectorConstructor(Expression leftEndPoint, Expression step, Expression rightEndPoint) {
        this.leftEndPoint = leftEndPoint;
        this.step = new Some<Expression>(step);
        this.rightEndPoint = rightEndPoint;
    }

    public <R> R accept(SVisitor.ExpressionVisitor.OpVisitor<R> v) {
        return v.visit(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLexeme() {
        return lexeme;
    }

    @Override
    public Expression[] getOperands() {
        return step.apply(
            new Fun0<Expression[]>() {
                public Expression[] apply() {
                    return new Expression[] {leftEndPoint, rightEndPoint};
                }
            },
            new Fun<Expression, Expression[]>() {
                public Expression[] apply(Expression stepExp) {
                    return new Expression[] {leftEndPoint, stepExp, rightEndPoint};
                }
            }
        );

    }
}
