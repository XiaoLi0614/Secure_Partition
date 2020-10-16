package lambda_calculus.source_ast.tree.expression;

import lambda_calculus.source_ast.tree.expression.id.GId;
import lambda_calculus.source_ast.tree.expression.id.Id;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lambda_calculus.source_ast.visitor.SourceVisitor;

public class ObjectMethod implements Expression{
    //public Option<Expression> receiver = None.instance();
    public GId methodName; ////name of the user declared method
    public Expression[] args; //input arguments of method
    public GId objectName; // the object which this method belongs to

    public ObjectMethod() {
    }

    public void init(String methodName, String objectName, Expression[] args) {
        this.methodName = new Id(methodName);
        this.objectName = new Id(objectName);
        this.args = args;
    }

    public ObjectMethod(String methodName, String objectName, Expression[] args) {
        this.methodName = new Id(methodName);
        this.objectName = new Id(objectName);
        this.args = args;
    }

    public ObjectMethod(GId methodName, GId objectName, Expression[] args) {
        this.methodName = methodName;
        this.objectName = objectName;
        this.args = args;
    }

    public <R> R accept(SourceVisitor.ExpressionVisitor<R> objectMethodVisitor) {
        return objectMethodVisitor.visit(this);
    }
}

//    public <R> R accept(Visitor.ExpVisitor.ZOpVisitor<R> v) {
//        return v.visit(this);
//    }
