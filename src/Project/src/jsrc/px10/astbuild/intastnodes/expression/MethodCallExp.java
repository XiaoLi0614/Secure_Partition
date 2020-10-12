package jsrc.px10.astbuild.intastnodes.expression;

import jsrc.px10.syntaxanalyser.syntaxtree.NodeToken;
import jsrc.x10.ast.tree.SourceLoc;
import jsrc.x10.ast.tree.expression.id.Id;
import lesani.compiler.ast.Node;
import jsrc.x10.ast.tree.expression.Expression;


public class MethodCallExp implements Node {
    public Id methodName;
    public Expression[] args;

    public MethodCallExp(Id methodName, Expression[] args) {
        this.methodName = methodName;
        this.args = args;
    }

    public SourceLoc sourceLoc = new SourceLoc();
    public void setLoc(NodeToken nodeToken) {
        sourceLoc.lineNo = nodeToken.beginLine;
        sourceLoc.columnNo = nodeToken.beginColumn;
    }

}
