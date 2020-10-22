package lambda_calculus.cps_ast.visitor;

import lambda_calculus.cps_ast.tree.Context;
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
import lesani.compiler.texttree.seq.TextSeq;

public class BetaReduction implements CPSVisitor{

    Command resultAST;
    Context resultContext;

    public BetaReduction(Context c) {
        resultContext = c;
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
            public Object visit(IntLiteral intLiteral){ return intLiteral;}
        }
        LiteralB literalB = new LiteralB();
        @Override
        public Object visit(Literal literal){ return literal.accept(literalB); }

        public class BinaryOpB implements BinaryOpVisitor<Object>{
            @Override
            public Object visit(Plus plus){ return plus;}
        }
        BinaryOpB binaryOpB = new BinaryOpB();
        @Override
        public Object visit(BinaryOp binaryOp){ return binaryOp.accept(binaryOpB); }

        @Override
        public Object visit(Var var){ return  var; }

        @Override
        public Object visit(Conditional conditional){ return conditional; }
    }

    public Object visitDispatch(Command command) {
        return command.accept(commandB);
    }
    public CommandB commandB =  new CommandB();
    public class CommandB implements CommandVisitor<Object> {
        //the only place we need to reduce is in the application of an abstraction
        @Override
        public Object visit(Application application){
            if(application.function instanceof Abstraction){

            }
            else return application;}

        @Override
        public Object visit(Abstraction abstraction){ return null; }

        @Override
        public Object visit(ExpSt expSt){ return expSt; }

        @Override
        public Object visit(If iF){ return iF; }

        @Override
        public Object visit(Sequence sequence){ return sequence; }

        @Override
        public Object visit(SingleCall singleCall){ return singleCall; }
    }

    //CommandB commandB = new CommandB();
    @Override
    public Object visit(Command command){ return command.accept(commandB); }

    public Command wholeRedecution(Command c){
        Command resultTree = c;
        while(!resultTree.equals((Command)visitDispatch(resultTree))){resultTree = (Command)visitDispatch(resultTree);}
        return resultTree;
    }
}




