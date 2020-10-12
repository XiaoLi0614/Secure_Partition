package jsrc.x10.ast.tree.expression.literal;

import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 12:41:55 PM
 */
public class DoubleLiteral extends Literal {

    public DoubleLiteral(String lexeme) {
        this.lexeme = lexeme;
    }
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.LiteralVisitor v) {
        return v.visit(this);
    }
}