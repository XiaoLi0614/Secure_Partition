package jsrc.x10.ast.tree;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Dec 9, 2010
 * Time: 12:14:07 PM
  */


public class SourceLoc {
    public int lineNo;
    public int columnNo;

    @Override
    public String toString() {
        return lineNo + ":" + columnNo;
    }
}
