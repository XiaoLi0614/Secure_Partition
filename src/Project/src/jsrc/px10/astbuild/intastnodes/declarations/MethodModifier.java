package jsrc.px10.astbuild.intastnodes.declarations;

import lesani.compiler.ast.Node;
import jsrc.x10.ast.tree.declaration.Visibility;


public class MethodModifier implements Node {
    public Visibility visibility = Visibility.PUBLIC;
    public boolean isStatic = false;
    public boolean isFinal = false;

    public MethodModifier(Visibility visibility, boolean aStatic, boolean aFinal) {
        this.visibility = visibility;
        isStatic = aStatic;
        isFinal = aFinal;
    }

    public MethodModifier(Visibility visibility, boolean aStatic) {
        this.visibility = visibility;
        isStatic = aStatic;
    }

    public MethodModifier(Visibility visibility) {
        this.visibility = visibility;
    }
}
