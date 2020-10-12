package jsrc.matlab.ast.tree.statement;

import lesani.compiler.ast.LocInfo;
import lesani.compiler.ast.Node;
import jsrc.matlab.ast.visitor.SVisitor;


/**
 * User: lesani, Date: Nov 3, 2009, Time: 9:11:16 AM
 */
public abstract class Statement extends LocInfo implements Node {
    public abstract <S> S accept(SVisitor.StatementVisitor<S> v);
//    public <R> R accept(SVisitor<R> v) {
//        return v.visit(this);
//    }
}