package jsrc.matlab.ast.tree.statement;

import lesani.collection.option.Option;
import lesani.compiler.ast.Node;
import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.visitor.SVisitor;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 12:19:29 PM
 */
public class Switch extends Statement {
    public Expression selector;
    public Case[] cases;
    public Option<Statement[]> defaultStatements;

    public static class Case implements Node {
        public Expression guard;
        public Statement[] statements;

        public Case(Expression guard, Statement[] statements) {
            this.guard = guard;
            this.statements = statements;
        }
    }

    public Switch(Expression selector, Case[] cases, Option<Statement[]> defaultStatements) {
        this.selector = selector;
        this.cases = cases;
        this.defaultStatements = defaultStatements;
    }

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }
}