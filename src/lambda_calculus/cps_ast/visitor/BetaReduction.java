package lambda_calculus.cps_ast.visitor;

import lambda_calculus.cps_ast.tree.command.*;
import lambda_calculus.cps_ast.tree.expression.Conditional;
import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.tree.expression.id.GId;
import lambda_calculus.cps_ast.tree.expression.id.Id;
import lambda_calculus.cps_ast.tree.expression.literal.IntLiteral;
import lambda_calculus.cps_ast.tree.expression.literal.Literal;
import lambda_calculus.cps_ast.tree.expression.op.BinaryOp;
import lambda_calculus.cps_ast.tree.expression.op.Plus;
import lambda_calculus.source_ast.tree.expression.ObjectMethod;
import lesani.compiler.texttree.seq.TextSeq;

public class BetaReduction implements CPSVisitor{

    TextSeq seq;
    //TextSeq k;
    int administrativeX;

    public BetaReduction() {
        seq = new TextSeq();
        administrativeX = 0;
    }

    public Object visitDispatch(Expression expression) {
        return expression.accept(expressionB);
    }
    public ExpressionB expressionB =  new ExpressionB();
    public class ExpressionB implements ExpressionVisitor<Object> {
        public class GIdB implements GIdVisitor<Object>{
            @Override
            public Object visit(Id id){ return null;}
        }
        GIdB gIdB = new GIdB();
        @Override
        public Object visit(GId gId){ return gId.accept(gIdB); }

        public class LiteralB implements LiteralVisitor<Object>{
            @Override
            public Object visit(IntLiteral intLiteral){ return null;}
        }
        LiteralB literalB = new LiteralB();
        @Override
        public Object visit(Literal literal){ return literal.accept(literalB); }

        public class BinaryOpB implements BinaryOpVisitor<Object>{
            @Override
            public Object visit(Plus plus){ return null;}
        }
        BinaryOpB binaryOpB = new BinaryOpB();
        @Override
        public Object visit(BinaryOp binaryOp){ return binaryOp.accept(binaryOpB); }

        @Override
        public Object visit(Var var){ return  null; }

        @Override
        public Object visit(Conditional conditional){ return null; }
    }

    /*public Object visitDispatch(Command command) {
        return command.accept(commandB);
    }
    public CommandB commandB =  new CommandB();*/
    public class CommandB implements CommandVisitor<Object> {
        @Override
        public Object visit(Application application){ return null; }

        @Override
        public Object visit(Abstraction abstraction){ return null; }

        @Override
        public Object visit(ExpSt expSt){ return null; }

        @Override
        public Object visit(If iF){ return null; }

        @Override
        public Object visit(Sequence sequence){ return null; }

        @Override
        public Object visit(SingleCall singleCall){ return null; }
    }

    CommandB commandB = new CommandB();
    @Override
    public Object visit(Command command){ return command.accept(commandB); }
}




