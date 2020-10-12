package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.visitor.CPSPrinter;
import jsrc.x10.ast.visitor.StatementVisitor;
/*
 User: lesani, Date: Nov 2, 2009, Time: 11:20:46 AM
*/

public class Block extends Statement {
//    public BlockSt[] statements;
//
//    public Block(BlockSt[] statements) {
//        this.statements = statements;
//    }


    public VarDeclOrSt[] statements;

    public Block(VarDeclOrSt[] statements) {
        this.statements = statements;
        for (int i = 0; i < statements.length; i++) {
            VarDeclOrSt statement = statements[i];
            if (statement == null) {
//                System.out.println("Exception");
                throw new RuntimeException();
            }
        }
    }

    public Object accept(StatementVisitor statementVisitor) {
        return statementVisitor.visit(this);
    }

}
