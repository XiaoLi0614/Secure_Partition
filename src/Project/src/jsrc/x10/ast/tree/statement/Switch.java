package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.StatementVisitor;
import lesani.collection.option.*;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 12:19:29 PM
 */
public class Switch extends Statement {
    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }

    public static class Case {
        public Expression guard;
        public VarDeclOrSt[] statements;

        public Case(Expression guard, VarDeclOrSt[] statements) {
            this.guard = guard;
            this.statements = statements;
        }
    }

    public Expression selector;
    public Case[] cases;
    public Option<VarDeclOrSt[]> defaultBlockStatements = None.instance();

    public Switch(Expression selector, Case[] cases, Option<VarDeclOrSt[]> defaultBlockStatements) {
        this.selector = selector;
        this.cases = cases;
        this.defaultBlockStatements = defaultBlockStatements;
    }

}
