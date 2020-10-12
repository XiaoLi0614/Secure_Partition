package jsrc.x10.ast.tree.expression;

import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.type.Type;
import jsrc.x10.ast.visitor.DFSVisitor;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 10:37:02 AM
 */
public class New implements Expression {

    public Type type;
    public Expression[] args;


    public New(Type type, Expression[] args) {
        this.type = type;
        this.args = args;
    }

    public New(Type type, String[] outputNames) {
        this.type = type;
        Expression[] args = new Id[outputNames.length];
        for (int i = 0; i < outputNames.length; i++)
            args[i] = new Id(outputNames[i]);
        this.args = args;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visit(this);
    }
}