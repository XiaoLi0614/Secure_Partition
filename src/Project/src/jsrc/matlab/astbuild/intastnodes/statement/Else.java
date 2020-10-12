package jsrc.matlab.astbuild.intastnodes.statement;

import lesani.compiler.ast.Node;
import jsrc.matlab.ast.tree.statement.Statement;

public class Else implements Node {
    public Statement[] statements;

    public Else(Statement[] statements) {
        this.statements = statements;
    }
}
