package jsrc.x10.ast.tree.expression.literal;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.CPSPrinter;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 12:40:33 PM
 */
public abstract class Literal implements Expression {
    public String lexeme;

//    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor expressionVisitor) {
//        return expressionVisitor.visit(this);
//    }
//
//    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.LiteralVisitor literalVisitor);

    public Object accept(CPSPrinter.FileVisitor.ClassVisitor.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }

    public abstract Object accept(CPSPrinter.FileVisitor.ClassVisitor.ExpressionVisitor.LiteralVisitor literalVisitor);
}
