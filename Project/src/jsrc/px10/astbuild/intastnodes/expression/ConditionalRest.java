package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;
import jsrc.x10.ast.tree.expression.Expression;


public class ConditionalRest implements Node {
    public Expression ifExp;
    public Expression elseExp;

    public ConditionalRest(Expression ifExp, Expression elseExp) {
        this.ifExp = ifExp;
        this.elseExp = elseExp;
    }
}
