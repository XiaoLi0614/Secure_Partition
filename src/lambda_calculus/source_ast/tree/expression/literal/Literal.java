package lambda_calculus.source_ast.tree.expression.literal;

import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.visitor.CPSPrinter;

public abstract class Literal implements Expression {
    public String lexeme;

//    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor expressionVisitor) {
//        return expressionVisitor.visit(this);
//    }
//
//    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.LiteralVisitor literalVisitor);

    public Object accept(CPSPrinter.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }

    public abstract Object accept(CPSPrinter.ExpressionVisitor.LiteralVisitor literalVisitor);
}
