package jsrc.x10.ast.tree.expression.id.specialids.general;

import jsrc.x10.ast.visitor.DFSVisitor;

public class SOut extends GSpecialId {

    private static SOut theInstance = new SOut();

    public static SOut instance() {
        return theInstance;
    }

    public SOut() {
        super("System.out");
    }

    @Override
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.GSpecialIdVisitor v) {
        return v.visit(this);
    }
}
