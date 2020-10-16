package lambda_calculus.source_ast.tree.expression.op;
import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;

public class Sequence extends BinaryOp {
    public Sequence(Expression operand1, Expression operand2) {
        super(";", operand1, operand2);
    }

    public <R> R accept(SourceVisitor.ExpressionVisitor.BinaryOpVisitor<R> binaryOpVisitor){
        return binaryOpVisitor.visit(this);
    }

    public <R> R accept(SourceVisitor.ExpressionVisitor<R> v){
        return v.visit(this);
    }
}
