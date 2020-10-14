package lambda_calculus.source_ast.tree.expression.id;

import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.visitor.CPSPrinter;


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
    public abstract Object accept(CPSPrinter.ExpressionVisitor.GIdVisitor gIdVisitor);

    public Object accept(CPSPrinter.ExpressionVisitor v) {
        return v.visit(this);
    }

}
