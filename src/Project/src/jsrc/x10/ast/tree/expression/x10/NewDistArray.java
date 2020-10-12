package jsrc.x10.ast.tree.expression.x10;

import jsrc.x10.ast.tree.Node;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.statement.Block;
import jsrc.x10.ast.tree.statement.x10.parts.PointFormalVar;
import jsrc.x10.ast.tree.type.PointType;
import jsrc.x10.ast.tree.type.ScalarType;
import jsrc.x10.ast.visitor.DFSVisitor;
import lesani.collection.option.*;

/**
 * User: lesani, Date: 5-Nov-2009, Time: 12:14:23 PM
 */
public class NewDistArray extends X10Expression {
    public boolean isConstant = false;
    public ScalarType elementType;
    public Id regionOrDistName;
    public Option<ArrayInit> arrayInitOption = None.instance();

    public NewDistArray(ScalarType elementType, Id regionOrDistName, ArrayInit arrayInitOption) {
        this.elementType = elementType;
        this.regionOrDistName = regionOrDistName;
        this.arrayInitOption = new Some<ArrayInit>(arrayInitOption);
    }
    public NewDistArray(ScalarType elementType, Id regionName) {
        this.elementType = elementType;
        this.regionOrDistName = regionName;
    }

    public NewDistArray(boolean isConstant, ScalarType elementType, Id regionOrDistName, ArrayInit arrayInitOption) {
        this.isConstant = isConstant;
        this.elementType = elementType;
        this.regionOrDistName = regionOrDistName;
        this.arrayInitOption = new Some<ArrayInit>(arrayInitOption);
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.ExpressionVisitor.X10ExpressionVisitor x10ExpressionVisitor) {
        return x10ExpressionVisitor.visit(this);
    }

    /**
     * User: lesani, Date: 5-Nov-2009, Time: 12:06:38 PM
     */
    public static class ArrayInit implements Node {
        public PointType pointType;
        public PointFormalVar pointFormalVar;
        public Block block;

        public ArrayInit(PointType pointType, PointFormalVar pointFormalVar, Block block) {
            this.pointType = pointType;
            this.pointFormalVar = pointFormalVar;
            this.block = block;
        }
    }
}


