package jsrc.x10.mhp;

import jsrc.x10.ast.tree.statement.Statement;

public class VarTriple {
    public Var m;
    public Var o;
    public Var l;

    //public Statement statement;

    public VarTriple(Var m, Var o, Var l/*, Statement statement*/) {
        this.m = m;
        this.o = o;
        this.l = l;
//        this.statement = statement;
    }

    public VarTriple(Statement statement) {
        m = new Var(statement);
        o = new Var(statement);
        l = new Var(statement);
    }
}
