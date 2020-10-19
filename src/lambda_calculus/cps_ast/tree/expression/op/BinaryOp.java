package lambda_calculus.cps_ast.tree.expression.op;

import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

public abstract class BinaryOp implements Expression {

    public Expression operand1;
    public Expression operand2;
    public String operatorText;

    protected BinaryOp(String operatorText, Expression operand1, Expression operand2) {
        this.operatorText = operatorText;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public <R> R accept(CPSVisitor.ExpressionVisitor<R> v){
        return v.visit(this);
    }

    public abstract <R> R accept(CPSVisitor.ExpressionVisitor.BinaryOpVisitor<R> v);
}
