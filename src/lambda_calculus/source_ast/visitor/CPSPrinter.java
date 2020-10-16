package lambda_calculus.source_ast.visitor;

import lambda_calculus.cps_ast.tree.Context;
import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.source_ast.tree.expression.*;
import lambda_calculus.source_ast.tree.expression.id.GId;
import lambda_calculus.source_ast.tree.expression.id.Id;
import lambda_calculus.source_ast.tree.expression.literal.IntLiteral;
import lambda_calculus.source_ast.tree.expression.literal.Literal;
import lambda_calculus.source_ast.tree.expression.op.BinaryOp;
import lambda_calculus.source_ast.tree.expression.op.Plus;
import lambda_calculus.source_ast.tree.expression.op.Sequence;
import lesani.collection.Pair;
import lesani.compiler.texttree.seq.TextSeq;

import java.util.HashMap;


public class CPSPrinter implements SourceVisitor{
    TextSeq seq;
    int administrativeX;
    Command resultAST;
    Context resultContext;

    public CPSPrinter() {
        seq = new TextSeq();
        administrativeX = 0;
        resultContext = new Context();
    }

    //print method for all th expressions
//    public static String print(Expression expression){
//        CPSPrinter p = new CPSPrinter();
//        p.visitDispatch(expression);
//        return p.getText();
//    }

    //public static Pair<Command, Context> CPS_Translation(Expression e){e}

    /*public ExpressionVisitor expressionVisitor =  new ExpressionVisitor();
    public class ExpressionVisitor {
        public Object visitDispatch(Expression expression) {
            return expression.accept(this);
        }

        public Object visit(GId id) {
            return gIdVisitor.visitDispatch(id);
        }

        public Object visit(Literal literal) {
            return literalVisitor.visitDispatch(literal);
        }

        public Object visit(BinaryOp binaryOp) {
            return binaryOpVisitor.visitDispatch(binaryOp);
        }

        public Object visit(ObjectMethod objectMethod) {
            GId objectName = objectMethod.objectName;
            GId methodName = objectMethod.methodName;
            Expression[] args = objectMethod.args;

            //evaluate the argument first
            if(args.length == 0 || args == null){
                seq.put("(call x" + administrativeX + ":= ");
                expressionVisitor.visitDispatch(objectName);
                seq.put(".");
                expressionVisitor.visitDispatch(methodName);
                seq.put("() in ");
                //seq.put(k);
                seq.put(" x" + administrativeX + ")");
            }
            else {
                for (Expression arg : args) {
                    seq.put("lambda ");
                    expressionVisitor.visitDispatch(arg);
                }
            }
            seq.put("(lambda x" + administrativeX + "call x");
            expressionVisitor.visitDispatch(objectName);
            expressionVisitor.visitDispatch(methodName);
            return null;
        }

        public Object visit(Conditional conditional) {
            Expression condition = conditional.condition;
            Expression ifExp = conditional.ifExp;
            Expression elseExp = conditional.elseExp;
            expressionVisitor.visitDispatch(condition);
            expressionVisitor.visitDispatch(ifExp);
            expressionVisitor.visitDispatch(elseExp);
            return null;
        }

        public Object visit(Var var) {
            return null;
        }

        public GIdVisitor gIdVisitor = new GIdVisitor();
        public class GIdVisitor {
            public Object visitDispatch(GId gId) {
                return gId.accept(this);
            }

            public Object visit(Id id) { return null; }
        }

        public LiteralVisitor literalVisitor = new LiteralVisitor();
        public class LiteralVisitor {
            public Object visitDispatch(Literal literal) {
                return literal.accept(this);
            }

            public Object visit(IntLiteral intLiteral) { return null; }
        }

        public BinaryOpVisitor binaryOpVisitor =  new BinaryOpVisitor();
        public class BinaryOpVisitor {
            public Object visitDispatch(BinaryOp binaryOp) {
                expressionVisitor.visitDispatch(binaryOp.operand1);
                expressionVisitor.visitDispatch(binaryOp.operand2);
                return binaryOp.accept(this);
            }

            public Object visit(lambda_calculus.source_ast.tree.expression.op.Plus plus) { return null; }

            public Object visit(lambda_calculus.source_ast.tree.expression.op.Sequence sequence) {
                return null;
            }
        }
    }*/

    public Object visitDispatch(Expression expression) {
        return expression.accept(expressionT);
    }
    public ExpressionT expressionT =  new ExpressionT();
    public class ExpressionT implements ExpressionVisitor<Object>{

        public class GIdT implements ExpressionVisitor.GIdVisitor<Object>{
            @Override
            public Object visit(Id id){return null;}
        }

        public class LiteralT implements ExpressionVisitor.LiteralVisitor<Object>{
            @Override
            public Object visit(IntLiteral intLiteral){return null;}
        }

        public class BinaryOpT implements ExpressionVisitor.BinaryOpVisitor<Object>{
            @Override
            public Object visit(Plus plus){return null;}

            @Override
            public Object visit(Sequence sequence){return null;}
        }

        @Override
        public Object visit(ObjectMethod objectMethod) {
            GId objectName = objectMethod.objectName;
            GId methodName = objectMethod.methodName;
            Expression[] args = objectMethod.args;

            //evaluate the argument first
            if(args.length == 0 || args == null){
                seq.put("(call x" + administrativeX + ":= ");
                visitDispatch(objectName);
                seq.put(".");
                visitDispatch(methodName);
                seq.put("() in ");
                //seq.put(k);
                seq.put(" x" + administrativeX + ")");
            }
            else {
                for (Expression arg : args) {
                    seq.put("lambda ");
                    visitDispatch(arg);
                }
            }
            seq.put("(lambda x" + administrativeX + "call x");
            visitDispatch(objectName);
            visitDispatch(methodName);
            return null;
        }

        @Override
        public Object visit(Conditional conditional) {
            Expression condition = conditional.condition;
            Expression ifExp = conditional.ifExp;
            Expression elseExp = conditional.elseExp;
            visitDispatch(condition);
            visitDispatch(ifExp);
            visitDispatch(elseExp);
            return null;
        }

        @Override
        public Object visit(Var var) {
            return null;
        }
    }
}


