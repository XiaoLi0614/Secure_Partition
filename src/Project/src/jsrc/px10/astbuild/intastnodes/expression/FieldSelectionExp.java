package jsrc.px10.astbuild.intastnodes.expression;

import jsrc.x10.ast.tree.expression.id.Id;
import lesani.compiler.ast.Node;


public class FieldSelectionExp implements Node {
    public Id fieldName;

    public FieldSelectionExp(Id fieldName) {
        this.fieldName = fieldName;
    }
}