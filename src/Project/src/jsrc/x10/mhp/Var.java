package jsrc.x10.mhp;

import jsrc.x10.ast.tree.statement.Statement;

public class Var {
    Statement statement;
    boolean aux = false;


    private static int noCounter = 0;
    int no;

    public Var(Statement statement) {
        no = noCounter++;
        this.statement = statement;
    }

    public Var(Statement statement, boolean b) {
        no = noCounter++;
        this.statement = statement;
        aux = b;
    }

    @Override
    public String toString() {
        return (aux?"A":"") + "Var" + (statement==null?"skip":statement.sourceLoc);
    }
}
