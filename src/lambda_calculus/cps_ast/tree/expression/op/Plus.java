package lambda_calculus.cps_ast.tree.expression.op;

import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.cps_ast.tree.command.ExpSt;
import lambda_calculus.cps_ast.tree.command.ThisMethod;
import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.tree.expression.op.BinaryOp;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;


public class Plus extends BinaryOp {

    public Plus(Expression operand1, Expression operand2) {
        super("+", operand1, operand2);
    }
    public <R> R accept(CPSVisitor.ExpressionVisitor.BinaryOpVisitor<R> binaryOpVisitor){
        return binaryOpVisitor.visit(this);
    }

    @Override
    public String toString(){
        return "(" + operand1 + " " + operatorText + " " + operand2 + ")";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plus that = (Plus) o;
        if(!operand1.equals(that.operand1)) return false;
        else if(!operand2.equals(that.operand2)) return false;
        else return (operatorText == that.operatorText);
    }

    @Override
    public Expression substitute(Var originalVar, Expression replacer) {
        return new Plus(this.operand1.substitute(originalVar, replacer),
                this.operand2.substitute(originalVar, replacer));
    }

    @Override
    public Command substitute(Var originalVar, ThisMethod replacer) {
        return new ExpSt(new Var("notValid"));
    }
}