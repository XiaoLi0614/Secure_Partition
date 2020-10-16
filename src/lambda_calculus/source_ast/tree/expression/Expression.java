package lambda_calculus.source_ast.tree.expression;

import lambda_calculus.source_ast.tree.Node;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;
/*
 User: lesani, Date: Nov 2, 2009, Time: 1:50:19 PM
*/

public interface Expression extends Node {
    //Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor expressionVisitor);
    <R> R accept(SourceVisitor.ExpressionVisitor<R> expressionVisitor);
}
