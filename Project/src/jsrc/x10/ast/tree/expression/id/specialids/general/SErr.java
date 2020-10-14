package jsrc.x10.ast.tree.expression.id.specialids.general;

import jsrc.x10.ast.visitor.DFSVisitor;

public class SErr extends GSpecialId {

    private static SErr theInstance = new SErr();

    public static SErr instance() {
        return theInstance;
    }

    public SErr() {
        super("System.err");
    }

    @Override
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.GSpecialIdVisitor v) {
        return v.visit(this);
    }
}
