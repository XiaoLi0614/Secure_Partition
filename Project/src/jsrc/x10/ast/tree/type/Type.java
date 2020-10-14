package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.tree.Node;
import jsrc.x10.ast.visitor.DFSVisitor;
/*
 User: lesani, Date: Nov 2, 2009, Time: 12:10:53 PM
*/

public abstract class Type implements Node {

    public abstract Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor);
}


