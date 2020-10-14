package jsrc.x10.ast.tree.declaration;

import jsrc.x10.ast.tree.statement.Block;
/*
 User: lesani, Date: Nov 2, 2009, Time: 11:00:43 AM
*/

public class Constructor implements ClassMember {

    public Visibility visibility = Visibility.PUBLIC;
    public FormalParam[] formalParams;
    public Block block;

    public Constructor(FormalParam[] formalParams, Block block) {
        this.formalParams = formalParams;
        this.block = block;
    }
}
