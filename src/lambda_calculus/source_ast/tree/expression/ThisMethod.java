package lambda_calculus.source_ast.tree.expression;

import lambda_calculus.source_ast.tree.expression.id.GId;
import lambda_calculus.source_ast.tree.expression.id.Id;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;

public class ThisMethod implements Expression{
    //public Option<Expression> receiver = None.instance();
    public GId methodName; ////name of the user declared method
    public Expression[] args; //input arguments of method
    public GId adminNames; // the administrative names for the results of the object call

    public ThisMethod() {
    }

    public ThisMethod(String methodName, String objectName, Expression[] args) {
        this.methodName = new Id(methodName);
        this.args = args;
    }

    public ThisMethod(GId methodName, GId objectName, Expression[] args) {
        this.methodName = methodName;
        this.args = args;
    }

    public <R> R accept(SourceVisitor.ExpressionVisitor<R> thisMethodVisitor) {
        return thisMethodVisitor.visit(this);
    }
}
