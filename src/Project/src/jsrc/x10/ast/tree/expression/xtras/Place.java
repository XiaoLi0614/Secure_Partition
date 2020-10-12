package jsrc.x10.ast.tree.expression.xtras;

/**
 * User: lesani, Date: 4-Nov-2009, Time: 8:05:26 PM
 */
public class Place /*implements Expression */{
    public String placeNumberName;

    public Place(String placeNumberName) {
        this.placeNumberName = placeNumberName;
    }

//    public <R> R accept(SVisitor<R> v) {
//        return v.visit(this);
//    }
}
