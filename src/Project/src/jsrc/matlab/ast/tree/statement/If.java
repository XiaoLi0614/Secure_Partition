package jsrc.matlab.ast.tree.statement;

import lesani.compiler.ast.Node;
import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.visitor.SVisitor;
import lesani.collection.option.*;

public class If extends Statement {

    public Expression condition;
    public Statement ifStatement;
    public Option<ElseIf[]> elseifs = None.instance();
    public Option<Statement> elseStatementOption = None.instance();
    static public class ElseIf implements Node {
        public Expression condition;
        public Statement statement;

        public ElseIf(Expression condition, Block statement) {
            this.condition = condition;
            this.statement = statement;
        }
    }

    public If(Expression condition, Statement ifStatement, ElseIf[] elseifs, Statement elseStatement) {
        this.condition = condition;
        this.ifStatement = ifStatement;
        if (elseifs.length != 0)
            this.elseifs = new Some<ElseIf[]>(elseifs);
        this.elseStatementOption = new Some<Statement>(elseStatement);
    }
    public If(Expression condition, Statement ifStatement, ElseIf[] elseifs) {
        this.condition = condition;
        this.ifStatement = ifStatement;
        if (elseifs.length != 0)
            this.elseifs = new Some<ElseIf[]>(elseifs);
    }

    public <R> R accept(SVisitor.StatementVisitor<R> v) {
        return v.visit(this);
    }
}
