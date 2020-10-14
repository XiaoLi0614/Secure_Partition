package jsrc.px10.astbuild.intastnodes.statement.caseparts;

import jsrc.x10.ast.tree.expression.Expression;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 11:42:29 AM
 */
public class CaseGuard extends SwitchGuard {
    public Expression guard;

    public CaseGuard(Expression guard) {
        this.guard = guard;
    }

    public CaseGuard() {
    }
}
