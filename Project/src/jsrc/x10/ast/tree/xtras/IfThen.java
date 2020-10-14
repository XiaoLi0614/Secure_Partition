package jsrc.x10.ast.tree.xtras;

import jsrc.x10.ast.tree.statement.Block;
import jsrc.x10.ast.visitor.StatementVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 9:47:55 AM
 */
public class IfThen extends If {

    public String conditionName;
    public Block block;

    public IfThen(String conditionName, Block block) {
        this.conditionName = conditionName;
        this.block = block;
    }

    public Object accept(StatementVisitor statementVisitor) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}