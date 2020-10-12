package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.StatementVisitor;
/*
 User: lesani, Date: Nov 2, 2009, Time: 1:50:45 PM
*/

public class Assignment extends Statement {

    public Expression left;
    public Expression right;

    public Assignment(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }
}
