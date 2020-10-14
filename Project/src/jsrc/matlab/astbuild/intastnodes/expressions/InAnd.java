package jsrc.matlab.astbuild.intastnodes.expressions;

import jsrc.matlab.ast.tree.expression.Expression;
import lesani.compiler.ast.LocInfo;
import lesani.compiler.ast.Node;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Dec 9, 2010
 * Time: 3:40:24 PM
  */
public class InAnd extends LocInfo implements Node {
    public Expression expression;

    public InAnd(Expression expression) {
        this.expression = expression;
    }
}