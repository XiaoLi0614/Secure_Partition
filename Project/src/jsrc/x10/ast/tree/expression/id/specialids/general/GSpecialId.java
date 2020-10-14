package jsrc.x10.ast.tree.expression.id.specialids.general;

import jsrc.x10.ast.tree.expression.id.specialids.SpecialId;
import jsrc.x10.ast.visitor.DFSVisitor;

public abstract class GSpecialId extends SpecialId {

    protected GSpecialId(String name) {
        super(name);
    }

    @Override
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor specialIdVisitor) {
        return specialIdVisitor.visit(this);
    }

    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.GSpecialIdVisitor gSpecialIdVisitor);
}
