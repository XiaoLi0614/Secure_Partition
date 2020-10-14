package lambda_calculus.source_ast.tree.expression.op;

import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.visitor.CPSPrinter;

public abstract class BinaryOp implements Expression {

    public Expression operand1;
    public Expression operand2;
    public String operatorText;

//    public final String operatorText;

    protected BinaryOp(String operatorText, Expression operand1, Expression operand2) {
        this.operatorText = operatorText;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }
//
//    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor expressionVisitor) {
//        return expressionVisitor.visit(this);
//    }
//
//    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.BinaryOpVisitor binaryOpVisitor);

    public Object accept(CPSPrinter.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }

    public abstract Object accept(CPSPrinter.ExpressionVisitor.BinaryOpVisitor binaryOpVisitor);
}
