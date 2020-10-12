package jsrc.x10.ast.tree.expression.id.specialids.general;

import jsrc.x10.ast.visitor.DFSVisitor;

public class PrintlnId extends GSpecialId {

    private static PrintlnId theInstance = new PrintlnId();

    public static PrintlnId instance() {
        return theInstance;
    }

    public PrintlnId() {
        super("println");
    }

    @Override
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.GSpecialIdVisitor v) {
        return v.visit(this);
    }

}
