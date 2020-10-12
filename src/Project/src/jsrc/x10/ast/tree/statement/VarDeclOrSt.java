package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.tree.Node;
import jsrc.x10.ast.tree.SourceLoc;


// A BlockSt is either a Statement or a VarDecl.

public class VarDeclOrSt implements Node {


    // SourceLoc should be moved to Node.
    public SourceLoc sourceLoc = new SourceLoc();

    public void setLoc(int lineNo, int columnNo) {
        this.sourceLoc.lineNo = lineNo;
        this.sourceLoc.columnNo = columnNo;
    }

    public void setLoc(SourceLoc sourceLoc) {
        this.sourceLoc.lineNo = sourceLoc.lineNo;
        this.sourceLoc.columnNo = sourceLoc.columnNo;
    }

}
