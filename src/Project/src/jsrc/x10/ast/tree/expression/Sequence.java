package jsrc.x10.ast.tree.expression;
import jsrc.x10.ast.visitor.CPSPrinter;

public class Sequence implements Expression{
    public Expression exp1;
    public Expression exp2;

    public Sequence(Expression Exp1, Expression Exp2) {
        this.exp1 = Exp1;
        this.exp2 = Exp2;
    }

    public Object accept(CPSPrinter.FileVisitor.ClassVisitor.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }
}
