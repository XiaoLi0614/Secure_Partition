package jsrc.matlab.astbuild.intastnodes.expressions;


import jsrc.matlab.ast.tree.expression.Expression;
import lesani.compiler.ast.LocInfo;
import lesani.compiler.ast.Node;

public class IntExp extends LocInfo implements Node {
    public Expression expression;

    public IntExp(Expression expression) {
        this.expression = expression;
    }
}
