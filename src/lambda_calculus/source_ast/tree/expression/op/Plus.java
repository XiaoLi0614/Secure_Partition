package lambda_calculus.source_ast.tree.expression.op;

import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.tree.expression.op.BinaryOp;
import lambda_calculus.source_ast.visitor.CPSPrinter;


public class Plus extends BinaryOp {

    public Plus(Expression operand1, Expression operand2) {
        super("+", operand1, operand2);
    }
    public Object accept(CPSPrinter.ExpressionVisitor.BinaryOpVisitor v) {
        return v.visit(this);
    }
}