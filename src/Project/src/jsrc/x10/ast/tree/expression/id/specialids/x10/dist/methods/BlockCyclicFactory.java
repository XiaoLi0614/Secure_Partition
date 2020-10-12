package jsrc.x10.ast.tree.expression.id.specialids.x10.dist.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class BlockCyclicFactory extends Method {

    private static BlockCyclicFactory theInstance = new BlockCyclicFactory();

    public static BlockCyclicFactory instance() {
		return theInstance;
	}

    private BlockCyclicFactory() {
        super("factory.blockCyclic");
    }
    // V2.0: makeBlockCyclic
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}