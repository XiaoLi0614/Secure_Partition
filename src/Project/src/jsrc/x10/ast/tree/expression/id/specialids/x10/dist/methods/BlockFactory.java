package jsrc.x10.ast.tree.expression.id.specialids.x10.dist.methods;

import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Method;
import jsrc.x10.ast.visitor.DFSVisitor;

public class BlockFactory extends Method {

    private static BlockFactory theInstance = new BlockFactory();

    public static BlockFactory instance() {
		return theInstance;
	}

    private BlockFactory() {
        super("factory.block");
    }
    // V2.0: makeBlock
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.GIdVisitor.SpecialIdVisitor.SX10IdVisitor v) {
        return v.visit(this);
    }

}