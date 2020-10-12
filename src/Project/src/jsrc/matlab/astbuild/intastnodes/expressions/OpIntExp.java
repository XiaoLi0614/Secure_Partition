package jsrc.matlab.astbuild.intastnodes.expressions;

import jsrc.matlab.ast.tree.expression.Expression;
import lesani.compiler.ast.LocInfo;
import lesani.compiler.ast.Node;

public class OpIntExp extends LocInfo implements Node {

    public String lexeme;
    public Expression operand;

    public OpIntExp(String lexeme, Expression operand) {
        this.lexeme = lexeme;
        this.operand = operand;
    }
}


