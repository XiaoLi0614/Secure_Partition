package lambda_calculus.cps_ast.tree.expression.literal;

import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.cps_ast.tree.command.ExpSt;
import lambda_calculus.cps_ast.tree.command.ThisMethod;
import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.tree.expression.literal.Literal;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

public class IntLiteral extends Literal {

    public IntLiteral(String lexeme) {
        this.lexeme = lexeme;
    }

    public IntLiteral(int i) {
        this.lexeme = Integer.toString(i);
    }

    public <R> R accept(CPSVisitor.ExpressionVisitor.LiteralVisitor<R> intLiteralVisitor){
        return intLiteralVisitor.visit(this);
    }

    @Override
    public String toString(){
        return lexeme;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return (lexeme == ((IntLiteral) o).toString());
    }

    @Override
    public Expression substitute(Var originalVar, Expression replacer){
        return this;
    }

    @Override
    public Command substitute(Var originalVar, ThisMethod replacer){
        return new ExpSt(this);
    }
}
