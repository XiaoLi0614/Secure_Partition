package jsrc.matlab.ast.tree.expression.op.xtras;

import jsrc.matlab.ast.tree.expression.Expression;


/**
 * User: lesani, Date: Nov 3, 2009, Time: 2:20:20 PM
 */
public abstract class BinaryOp {

    public Expression operand1;
    public Expression operand2;

//    public final String operatorText;

    protected BinaryOp(/*String operatorText,*/ Expression operand1, Expression operand2) {
//        this.operatorText = operatorText;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }
}