package jsrc.px10.astbuild.intastnodes.statement.caseparts;

import jsrc.x10.ast.tree.statement.Statement;
import lesani.compiler.ast.Node;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 11:48:14 AM
 */
public class SwitchEntry implements Node {

    public SwitchGuard switchGuard;
    public Statement[] statements;

    public SwitchEntry(SwitchGuard switchGuard, Statement[] statements) {
        this.switchGuard = switchGuard;
        this.statements = statements;
    }
}
