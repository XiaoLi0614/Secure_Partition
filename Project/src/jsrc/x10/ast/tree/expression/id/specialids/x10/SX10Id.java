package jsrc.x10.ast.tree.expression.id.specialids.x10;

import jsrc.x10.ast.tree.expression.id.specialids.SpecialId;
import jsrc.x10.ast.visitor.DFSVisitor;

public abstract class SX10Id extends SpecialId {

    public SX10Id(String name) {
        super(name);
    }

    @Override
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor specialIdVisitor) {
        return specialIdVisitor.visit(this);
    }

    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor sx10IdVisitor);
}
