package jsrc.x10.ast.tree.expression.id;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.CPSPrinter;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 10:08:25 AM
 */
public abstract class GId implements Expression {
    public String lexeme;

    protected GId(String lexeme) {
        this.lexeme = lexeme;
    }

//    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor gIdVisitor);
//
//    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor v) {
//        return v.visit(this);
//    }
    public abstract Object accept(CPSPrinter.FileVisitor.ClassVisitor.ExpressionVisitor.GIdVisitor gIdVisitor);

    public Object accept(CPSPrinter.FileVisitor.ClassVisitor.ExpressionVisitor v) {
        return v.visit(this);
    }

}
