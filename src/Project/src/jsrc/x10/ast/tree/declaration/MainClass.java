package jsrc.x10.ast.tree.declaration;

import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.type.LocalArrayType;
import jsrc.x10.ast.tree.type.StringType;
import jsrc.x10.ast.tree.type.VoidType;
import jsrc.x10.ast.tree.statement.Block;

/**
 * Mohsen, Date: Nov 1, 2009, Time: 3:58:38 PM
 */

public class MainClass extends ClassDecl {
//    public Id name;
//    public Id args;
//    public Block block;

    public MainClass(Id name, Id args, Block block) {
        super(name, new Field[0], new Constructor[0], new Method[] {
                new Method(
                        true,
                        VoidType.instance(),
                        new Id("main"),
                        new FormalParam[]{(new FormalParam(new LocalArrayType(StringType.instance()), args))},
                        block)
        });
//        this.name = name;
//        this.args = args;
//        this.block = block;
    }

}
