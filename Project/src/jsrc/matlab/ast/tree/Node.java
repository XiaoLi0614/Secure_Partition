package jsrc.matlab.ast.tree;

import jsrc.matlab.ast.visitor.SVisitor;

/**
 * Mohsen, Date: Nov 1, 2009, Time: 4:08:52 PM
 */
public interface Node extends lesani.compiler.ast.Node {
    public <R> R accept(SVisitor<R> v);

}