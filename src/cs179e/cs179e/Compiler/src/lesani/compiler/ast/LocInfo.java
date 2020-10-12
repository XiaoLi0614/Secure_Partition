package lesani.compiler.ast;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Dec 9, 2010
 * Time: 12:14:07 PM
  */


public class LocInfo {
    public int lineNo;
    public int columnNo;

    @Override
    public String toString() {
        return getLoc();
    }

    public String getLoc() {
       return lineNo + ":" + columnNo;
    }

    public void setLoc(LocInfo locInfo) {
        this.lineNo = locInfo.lineNo;
        this.columnNo = locInfo.columnNo;
    }
}
