package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.tree.type.constraint.Constraint;
import jsrc.x10.ast.visitor.DFSVisitor;
import lesani.collection.option.*;
/*
 User: lesani, Date: Nov 2, 2009, Time: 1:16:03 PM
*/

public class PointType extends ScalarType {
    public Option<Constraint> constraint = None.instance();

    public PointType(Constraint constraint) {
        this.constraint = new Some<Constraint>(constraint);
    }

    //int rank;
    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }

}