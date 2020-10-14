package jsrc.x10.ast.tree.statement.xtras;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 9:16:32 AM
 */
public class FieldAssignment /*implements Statement*/ {
    public String receiver;
    public String fieldName;
    public String rightValueName;

    public FieldAssignment(String receiver, String fieldName, String rightValueName) {
        this.receiver = receiver;
        this.fieldName = fieldName;
        this.rightValueName = rightValueName;
    }

//    public <R> R accept(SVisitor<R> v) {
//        return v.visit(this);
//    }
}
