package jsrc.matlab.ast.tree.declaration;

import jsrc.matlab.ast.tree.statement.Statement;
import jsrc.matlab.ast.visitor.SVisitor;

public class Script implements CompilationUnit {

    public Statement[] statements;

    public Script(Statement[] statements) {
        this.statements = statements;
    }

    public <R> R accept(SVisitor<R> v) {
        return v.visit(this);
    }
}
