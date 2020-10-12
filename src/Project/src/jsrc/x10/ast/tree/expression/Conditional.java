package jsrc.x10.ast.tree.expression;

import jsrc.x10.ast.visitor.CPSPrinter;
import jsrc.x10.ast.visitor.DFSVisitor;


public class Conditional implements Expression {

    public Expression condition;
    public Expression ifExp;
    public Expression elseExp;

    public Conditional(Expression condition, Expression ifExp, Expression elseExp) {
        this.condition = condition;
        this.ifExp = ifExp;
        this.elseExp = elseExp;
    }

    public Object accept(CPSPrinter.FileVisitor.ClassVisitor.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }
}
