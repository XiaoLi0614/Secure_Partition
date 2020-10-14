package jsrc.matlab.astbuild.intastnodes.declarations;

import jsrc.matlab.ast.tree.expression.Id;
import lesani.compiler.ast.Node;


public class FunOutput implements Node {

    public Id[] outputParams;

    public FunOutput(Id[] outputParams) {
        this.outputParams = outputParams;
    }
}
