package jsrc.x10.ast.tree.expression.xtras;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 3:52:13 PM
 */
public class DistributionUnion /*implements Expression*/ {

    public String operand1Name;
    public String operand2rangeStartName;
    public String operand2rangeEndName;
    public String operand2placeName;

    public DistributionUnion(String operand1Name, String operand2rangeStartName, String operand2rangeEndName, String operand2placeName) {
        this.operand1Name = operand1Name;
        this.operand2rangeStartName = operand2rangeStartName;
        this.operand2rangeEndName = operand2rangeEndName;
        this.operand2placeName = operand2placeName;
    }

//    public <R> R accept(SVisitor<R> v) {
//        return v.visit(this);
//    }
}
