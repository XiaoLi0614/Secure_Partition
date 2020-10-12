package jsrc.x10.ast.tree.expression.id.specialids.general;

import jsrc.x10.ast.visitor.DFSVisitor;

public class PrintId extends GSpecialId {

    private static PrintId theInstance = new PrintId();

    public static PrintId instance() {
        return theInstance;
    }

    public PrintId() {
        super("print");
    }

    @Override
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.GSpecialIdVisitor v) {
        return v.visit(this);
    }
}
