package lambda_calculus.source_ast.tree.expression.op;

import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.tree.expression.op.BinaryOp;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;

public class Compare extends BinaryOp{

    public Compare(String operator, Expression operand1, Expression operand2) {
        super(operator, operand1, operand2);
    }

    public <R> R accept(SourceVisitor.ExpressionVisitor.BinaryOpVisitor<R> binaryOpVisitor){
        return binaryOpVisitor.visit(this);
    }
}
