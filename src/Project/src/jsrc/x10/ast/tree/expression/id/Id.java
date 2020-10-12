package jsrc.x10.ast.tree.expression.id;

import jsrc.x10.ast.visitor.CPSPrinter;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 10:08:25 AM
 */
public class Id extends GId {

    public Id(String name) {
        super(name);
    }

//    @Override
//    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor gIdVisitor) {
//        return gIdVisitor.visit(this);
//    }

    @Override
    public Object accept(CPSPrinter.FileVisitor.ClassVisitor.ExpressionVisitor.GIdVisitor gIdVisitor) {
        return gIdVisitor.visit(this);
    }
}
