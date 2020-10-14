package lambda_calculus.source_ast.tree.expression.id;

import lambda_calculus.source_ast.visitor.CPSPrinter;

public class Id extends GId {

    public Id(String name) {
        super(name);
    }

//    @Override
//    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor gIdVisitor) {
//        return gIdVisitor.visit(this);
//    }

    @Override
    public Object accept(CPSPrinter.ExpressionVisitor.GIdVisitor gIdVisitor) {
        return gIdVisitor.visit(this);
    }
}
