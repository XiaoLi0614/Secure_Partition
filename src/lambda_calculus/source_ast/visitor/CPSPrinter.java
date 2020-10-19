package lambda_calculus.source_ast.visitor;

import lambda_calculus.cps_ast.tree.Context;
import lambda_calculus.cps_ast.tree.command.*;
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
    HashMap<Expression, Command>  continuationMap;

    public CPSPrinter() {
        seq = new TextSeq();
        administrativeX = 0;
        resultContext = new Context();
        continuationMap = new HashMap<>();
    }

    //print method for all the expressions
    public static String print(Expression expression){
        CPSPrinter p = new CPSPrinter();
        p.visitDispatch(expression);
        return p.getText();
    }

    public String newXName(){
        return "x" + String.valueOf(administrativeX++);
    }

/*
    public static Pair<Command, Context> CPS_Translation(Expression e){
        CPSPrinter p = new CPSPrinter();
        p.visitDispatch(e);
    }
*/

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

        //For these two functions, we produce an application ast node in the target language
        public class GIdT implements ExpressionVisitor.GIdVisitor<Object>{
            //[x]k = k x
            @Override
            public Object visit(Id id){
                seq.put(id.lexeme);
                lambda_calculus.cps_ast.tree.expression.id.Id resultId = new lambda_calculus.cps_ast.tree.expression.id.Id(id.lexeme);
                Command[] resultVar = new Command[1];
                resultVar[0] = new ExpSt(resultId);
                Application resultA = new Application(continuationMap.get(id), resultVar);
                return resultA;
            }
        }

        public class LiteralT implements ExpressionVisitor.LiteralVisitor<Object>{
            //[n]k = k n
            @Override
            public Object visit(IntLiteral intLiteral){
                seq.put(intLiteral.lexeme);
                lambda_calculus.cps_ast.tree.expression.literal.IntLiteral resultInt = new lambda_calculus.cps_ast.tree.expression.literal.IntLiteral(intLiteral.lexeme);
                Command[] resultVar = new Command[1];
                resultVar[0] = new ExpSt(resultInt);
                Application resultA = new Application(continuationMap.get(intLiteral), resultVar);
                return resultA;
            }
        }

        public class BinaryOpT implements ExpressionVisitor.BinaryOpVisitor<Object>{
            //[e1 + e2]k = [e1](lambda x1. [e2]( lambda x2. k(x1 + x2)))
            @Override
            public Object visit(Plus plus){
                // lambda x1
                lambda_calculus.cps_ast.tree.expression.Var[] lambda1Var = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambda1Var[0] = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
                resultContext.addVariableToContext(lambda1Var[0]);
                //lambda x2
                lambda_calculus.cps_ast.tree.expression.Var[] lambda2Var = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambda2Var[0] = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
                resultContext.addVariableToContext(lambda2Var[0]);
                //x1 + x2
                lambda_calculus.cps_ast.tree.expression.op.Plus varPlus = new lambda_calculus.cps_ast.tree.expression.op.Plus(lambda1Var[0], lambda2Var[0]);
                Command[] plusBody = new Command[1];
                plusBody[0] = new ExpSt(varPlus);

                Command body2 = new Abstraction(lambda2Var, new Application(continuationMap.get(plus), plusBody));
                Command[] body2AsVar = new Command[1];
                body2AsVar[0] = body2;

                Command body1 = new Abstraction(lambda1Var, new Application((Command) visitDispatch(plus.operand2), body2AsVar));
                Command[] body1AsVar = new Command[1];
                body1AsVar[0] = body1;
                Command resultCommand = new Application((Command) visitDispatch(plus.operand1), body1AsVar);

                //update the continuation map for future use
                continuationMap.put(plus.operand1, body1);
                continuationMap.put(plus.operand2, body2);
                //update context for evaluation
                resultContext.bindValueToContext(lambda1Var[0], (Command) visitDispatch(plus.operand1));
                resultContext.bindValueToContext(lambda2Var[0], (Command) visitDispatch(plus.operand2));

                return resultCommand;
            }

            //[e1; e2]k = [e1]([e2] (lambda x. k x))
            @Override
            public Object visit(Sequence sequence){
                //lambda x
                lambda_calculus.cps_ast.tree.expression.Var lambdaVar = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
                resultContext.addVariableToContext(lambdaVar);

                lambda_calculus.cps_ast.tree.expression.Var[] lambdaAsVar1 = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambdaAsVar1[0] = lambdaVar;

                Command[] lambdaAsVar2 = new Command[1];
                lambdaAsVar2[0] = new ExpSt(lambdaVar);

                Command bodyForE2 = new Abstraction(lambdaAsVar1, new Application(continuationMap.get(sequence), lambdaAsVar2));
                Command[] bodyAsVar = new Command[1];
                bodyAsVar[0] = bodyForE2;
                Command[] bodyAsVar2 = new Command[1];
                bodyAsVar2[0] = new Application(
                        (Command) visitDispatch(sequence.operand2),
                        bodyAsVar);

                //add continuation and context
                continuationMap.put(sequence.operand2, bodyForE2);
                continuationMap.put(sequence.operand1, bodyAsVar2[0]);
                resultContext.bindValueToContext(lambdaVar, (Command) visitDispatch(sequence.operand2));

                Command resultCommand = new Application(
                        (Command) visitDispatch(sequence.operand1),
                        bodyAsVar2);

                return resultCommand;}
        }

        //[o.m(e)] k = [e] (lambda x1. call x2 = o.m(x1) in k x2)
        @Override
        public Object visit(ObjectMethod objectMethod) {
            lambda_calculus.cps_ast.tree.expression.Var lambda2 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
            resultContext.addVariableToContext(lambda2);
            //k x2
            Command[] x2AsVar = new Command[1];
            x2AsVar[0] = new ExpSt(lambda2);
            Application kX2 = new Application(continuationMap.get(objectMethod), x2AsVar);

            //[o.m()] k = call x = o.m() in (k x)
            if(objectMethod.args.length == 0 || objectMethod.args == null){
                SingleCall call = new SingleCall(
                        objectMethod.objectName.lexeme, objectMethod.methodName.lexeme, null, lambda2, kX2);
                resultContext.bindValueToContext(lambda2, call);
                //todo: what is the continuation for the termination?
                return call;
            }
            // if there are arguments
            else {
                //x1
                lambda_calculus.cps_ast.tree.expression.Var lambda1 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
                lambda_calculus.cps_ast.tree.expression.Expression[] lambda1AsVar = new lambda_calculus.cps_ast.tree.expression.Expression[1];
                lambda1AsVar[0] = lambda1;
                lambda_calculus.cps_ast.tree.expression.Var[] lambda1AsVar2 = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambda1AsVar2[0] = lambda1;
                SingleCall call2 = new SingleCall(
                        objectMethod.objectName.lexeme,
                        objectMethod.methodName.lexeme,
                        lambda1AsVar,
                        lambda2,
                        kX2);
                Abstraction bodyForE = new Abstraction(lambda1AsVar2, call2);
                Command[] bodyForEAsValue = new Command[1];
                bodyForEAsValue[0] = bodyForE;
                Application resultCommand = new Application((Command) visitDispatch(objectMethod.args[0]), bodyForEAsValue);

                resultContext.bindValueToContext(lambda2, call2);
                resultContext.bindValueToContext(lambda1, (Command) visitDispatch(objectMethod.args[0]));

                continuationMap.put(objectMethod.args[0], bodyForE);

                return resultCommand;
            }
        }


        //[if e0 then e1 else e2] k = [e0] (lambda x. if x neq 0 then [e1]( lambda x1. k x1) else [e2] (lambda x2. k x2))
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

        //[x] k = k x
        @Override
        public Object visit(Var var) {
            Command[] varAsValue = new Command[1];
            varAsValue[0] = new ExpSt(new lambda_calculus.cps_ast.tree.expression.Var(var.name.lexeme));
            Application resultCommand = new Application(continuationMap.get(var), varAsValue);
            return resultCommand;
        }
    }

    public String getText() {
        return seq.get().print();
    }
}


