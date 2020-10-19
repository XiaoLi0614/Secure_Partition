package lambda_calculus.source_ast.tree.expression.op;

import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;

public abstract class BinaryOp implements Expression {

    public Expression operand1;
    public Expression operand2;
    public String operatorText;

    protected BinaryOp(String operatorText, Expression operand1, Expression operand2) {
        this.operatorText = operatorText;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public <R> R accept(SourceVisitor.ExpressionVisitor<R> v){
        return v.visit(this);
    }
}
