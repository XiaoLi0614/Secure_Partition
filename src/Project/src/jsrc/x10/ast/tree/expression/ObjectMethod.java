package jsrc.x10.ast.tree.expression;

import jsrc.x10.ast.tree.expression.id.GId;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.statement.Block;
import jsrc.x10.ast.tree.type.Type;
import jsrc.x10.ast.visitor.CPSPrinter;

public class ObjectMethod implements Expression{
    //public Option<Expression> receiver = None.instance();
    public GId methodName; ////name of the user declared method
    public Expression[] args; //input arguments of method
    public Type returnType; // the return value of the method
    protected Block methodBody; //body of the method
    //public ArrayList<Type> arguments = new ArrayList<>(); //input arguments of method
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

    public Object accept(CPSPrinter.FileVisitor.ClassVisitor.ExpressionVisitor objectMethodVisitor) {
        return objectMethodVisitor.visit(this);
    }
}
