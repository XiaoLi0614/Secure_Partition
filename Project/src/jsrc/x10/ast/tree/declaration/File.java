package jsrc.x10.ast.tree.declaration;

import jsrc.x10.ast.tree.Node;
import jsrc.x10.ast.visitor.DFSVisitor;


public class File implements Node {

    //public MainClass mainClass;
    //ProgramClass programClass;
    public ClassDecl[] classDecls;
    //ValueClassDecl[] valueClassDeclArray;
    //public Set protoMethods;

    //public File(MainClass mainClass, /*ProgramClass programClass, */ClassDecl[] classDecls, /*, ValueClassDecl[] valueClassDeclArray*/Set protoMethods) {
    public File(ClassDecl[] classDecls) {
        this.classDecls = classDecls;
    }

    public Object accept(DFSVisitor v) {
        return v.visit(this);
    }
}

