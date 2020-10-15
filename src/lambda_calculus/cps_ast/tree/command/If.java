package lambda_calculus.cps_ast.tree.command;


import lambda_calculus.source_ast.tree.expression.Expression;
import lesani.collection.option.*;


public class If extends Statement {
    public Expression condition;
    public Statement ifStatement;
    public Statement elseStatement;

    public If(Expression expression, Statement ifStatement, Statement elseStatement) {
        this.condition = expression;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }
}
