package jsrc.px10.astbuild.intastnodes.expression;

import lesani.compiler.ast.Node;
import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.literal.IntLiteral;


public class CoordExp implements Node {
    public Expression expression;
    public IntLiteral intLiteral;

    public CoordExp(Expression expression, IntLiteral intLiteral) {
        this.expression = expression;
        this.intLiteral = intLiteral;
    }
}
