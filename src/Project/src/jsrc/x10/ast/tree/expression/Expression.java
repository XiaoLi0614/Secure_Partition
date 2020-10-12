package jsrc.x10.ast.tree.expression;

import jsrc.x10.ast.tree.Node;
import jsrc.x10.ast.visitor.CPSPrinter;
import jsrc.x10.ast.visitor.DFSVisitor;
/*
 User: lesani, Date: Nov 2, 2009, Time: 1:50:19 PM
*/

public interface Expression extends Node {
    //Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor expressionVisitor);
    Object accept(CPSPrinter.FileVisitor.ClassVisitor.ExpressionVisitor expressionVisitor);
}
