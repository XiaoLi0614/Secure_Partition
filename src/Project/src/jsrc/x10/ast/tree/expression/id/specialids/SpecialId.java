package jsrc.x10.ast.tree.expression.id.specialids;

import jsrc.x10.ast.tree.expression.id.GId;
import jsrc.x10.ast.visitor.DFSVisitor;



// A special id has a String name like an Id.
// Special Ids are interesting ids in the source code. For
// example the name of the distribution class and ... .
// If one is interested in these ids for example to
// translate them to new ids say in a new version, he can
// write the visitor for these special ids. Otherwise he
// can just write the visit method for GId.
// The string name of special ids should be initialized to
// the lexeme in the source code in the constructor of these
// classes. This is because one may match the name in these
// classes to the names in the source file.

public abstract class SpecialId extends GId {

    protected SpecialId(String name) {
        super(name);
    }

    @Override
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor gIdVisitor) {
        return gIdVisitor.visit(this);
    }

    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor specialIdVisitor);

}
