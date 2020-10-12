package jsrc.x10.ast.tree.expression;

import jsrc.x10.ast.tree.SourceLoc;
import jsrc.x10.ast.tree.expression.id.GId;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.visitor.DFSVisitor;
import lesani.collection.option.*;
/*
 User: lesani, Date: Nov 2, 2009, Time: 11:33:17 AM
*/

public class MethodCall implements Expression {

    public Option<Expression> receiver = None.instance();
    public GId methodName;
    public Expression[] args;

    public MethodCall() {
    }

    public void init(Expression receiver, String methodName, Expression[] args) {
        this.receiver = new Some<Expression>(receiver);
        this.methodName = new Id(methodName);
        this.args = args;
    }

    public MethodCall(Expression receiver, GId methodName, Expression[] args) {
        this.receiver = new Some<Expression>(receiver);
        this.methodName = methodName;
        this.args = args;
    }

    public MethodCall(Expression receiver, String methodName, Expression[] args) {
        this.receiver = new Some<Expression>(receiver);
        this.methodName = new Id(methodName);
        this.args = args;
    }

    public MethodCall(String receiver, String methodName, Expression[] args) {
        this.receiver = new Some<Expression>(new Id(receiver));
        this.methodName = new Id(methodName);
        this.args = args;
    }

    public MethodCall(GId methodName, Expression[] args) {
        this.methodName = methodName;
        this.args = args;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }


    // SourceLoc should be moved to Node.
    public SourceLoc sourceLoc = new SourceLoc();

    public void setLoc(int lineNo, int columnNo) {
        this.sourceLoc.lineNo = lineNo;
        this.sourceLoc.columnNo = columnNo;
    }

    public void setLoc(SourceLoc sourceLoc) {
        this.sourceLoc.lineNo = sourceLoc.lineNo;
        this.sourceLoc.columnNo = sourceLoc.columnNo;
    }

}
