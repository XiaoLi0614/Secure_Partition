package jsrc.x10.ast.tree.expression.literal;

import jsrc.x10.ast.visitor.CPSPrinter;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 12:41:55 PM
 */
public class IntLiteral extends Literal {

    public IntLiteral(String lexeme) {
        this.lexeme = lexeme;
    }

    public IntLiteral(int i) {
        this.lexeme = Integer.toString(i);
    }

    public Object accept(CPSPrinter.FileVisitor.ClassVisitor.ExpressionVisitor.LiteralVisitor v) {
        return v.visit(this);
    }
}
