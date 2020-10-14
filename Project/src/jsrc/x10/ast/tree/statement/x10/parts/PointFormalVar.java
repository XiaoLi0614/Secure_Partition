package jsrc.x10.ast.tree.statement.x10.parts;

import jsrc.x10.ast.visitor.DFSVisitor;


/**
 * User: lesani, Date: Nov 3, 2009, Time: 10:10:13 AM
 */

public interface PointFormalVar {

    Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.StatementVisitor.X10StatementVisitor.PointFormalVarVisitor pointFormalVarVisitor);
}

