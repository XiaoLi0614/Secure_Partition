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

    public Object visitDispatch(Expression expression) {
        return expression.accept(expressionT);
    }
    public ExpressionT expressionT =  new ExpressionT();
    public class ExpressionT implements ExpressionVisitor<Object>{

        //For these two functions, we produce an application ast node in the target language
        public class GIdT implements GIdVisitor<Object>{
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

        GIdT gIdT = new GIdT();
        @Override
        public Object visit(GId gId){ return gId.accept(gIdT); }

        public class LiteralT implements LiteralVisitor<Object>{
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

        LiteralT literalT = new LiteralT();
        @Override
        public Object visit(Literal literal){ return literal.accept(literalT); }

        public class BinaryOpT implements BinaryOpVisitor<Object>{
            //[e1 + e2]k = [e1](lambda x1. [e2]( lambda x2. k(x1 + x2)))
            //this is a non-terminal node
            @Override
            public Object visit(Plus plus){
                lambda_calculus.cps_ast.tree.expression.Var[] lambda1Var = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambda1Var[0] = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
                lambda_calculus.cps_ast.tree.expression.Var[] lambda2Var = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambda2Var[0] = new lambda_calculus.cps_ast.tree.expression.Var(newXName());

                //x1 + x2
                lambda_calculus.cps_ast.tree.expression.op.Plus varPlus = new lambda_calculus.cps_ast.tree.expression.op.Plus(lambda1Var[0], lambda2Var[0]);
                Command[] plusBody = new Command[1];
                plusBody[0] = new ExpSt(varPlus);

                //lambda x2
                resultContext.addVariableToContext(lambda2Var[0]);
                //lambda x2 . k(x1 + x2)
                Command body2 = new Abstraction(lambda2Var, new Application(continuationMap.get(plus), plusBody));
                continuationMap.put(plus.operand2, body2);
                Command e2Evaluation = (Command) visitDispatch(plus.operand2);
                resultContext.bindValueToContext(lambda2Var[0], e2Evaluation);

                //-----------------------------------------------------------------
                // lambda x1
                resultContext.addVariableToContext(lambda1Var[0]);
                //lambda x1. [e2] (lambda x2.k( x1 + x2))
                Command body1 = new Abstraction(lambda1Var, e2Evaluation);
                continuationMap.put(plus.operand1, body1);
                Command e1Evaluation = (Command) visitDispatch(plus.operand1);
                resultContext.bindValueToContext(lambda1Var[0], e1Evaluation);
                //todo: I think the bindings are not correct now. I confuse the evaluation and the translation part

                //Command[] body2AsVar = new Command[1];
                //body2AsVar[0] = body2;
                //Command[] body1AsVar = new Command[1];
                //body1AsVar[0] = body1;
                //Command resultCommand = new Application((Command) visitDispatch(plus.operand1), body1AsVar);

                return null;
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
                //Command[] bodyAsVar = new Command[1];
                //bodyAsVar[0] = bodyForE2;
                //Command[] bodyAsVar2 = new Command[1];
                //bodyAsVar2[0] = new Application(
                        //(Command) visitDispatch(sequence.operand2),
                        //bodyAsVar);

                visitDispatch(sequence.operand1);
                continuationMap.put(sequence.operand2, bodyForE2);
                Command e2Evaluation = (Command) visitDispatch(sequence.operand2);

                //add continuation and context
                //continuationMap.put(sequence.operand1, bodyAsVar2[0]);
                resultContext.bindValueToContext(lambdaVar, e2Evaluation);

                //Command resultCommand = new Application(
                        //(Command) visitDispatch(sequence.operand1),
                        //bodyAsVar2);

                return null;
                //return resultCommand;}
                }
        }

        BinaryOpT binaryT = new BinaryOpT();
        @Override
        public Object visit(BinaryOp binaryOp){ return binaryOp.accept(binaryT); }

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
            //this is a termination
            if(objectMethod.args.length == 0 || objectMethod.args == null){
                SingleCall call = new SingleCall(
                        objectMethod.objectName.lexeme, objectMethod.methodName.lexeme, null, lambda2, kX2);
                resultContext.bindValueToContext(lambda2, call);
                return call;
            }
            // if there are arguments, this is not a termination
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
                resultContext.bindValueToContext(lambda2, call2);

                Abstraction bodyForE = new Abstraction(lambda1AsVar2, call2);
                //Command[] bodyForEAsValue = new Command[1];
                //bodyForEAsValue[0] = bodyForE;
                //Application resultCommand = new Application((Command) visitDispatch(objectMethod.args[0]), bodyForEAsValue);
                continuationMap.put(objectMethod.args[0], bodyForE);
                Command eEvaluation = (Command) visitDispatch(objectMethod.args[0]);
                resultContext.bindValueToContext(lambda1, eEvaluation);
                //return resultCommand;
                return null;
            }
        }


        //[if e0 then e1 else e2] k = [e0] (lambda x. if x neq 0 then [e1]( lambda x1. k x1) else [e2] (lambda x2. k x2))
        @Override
        public Object visit(Conditional conditional) {
            //produce lambda
            lambda_calculus.cps_ast.tree.expression.Var lambda0 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
            lambda_calculus.cps_ast.tree.expression.Var lambda1 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
            lambda_calculus.cps_ast.tree.expression.Var lambda2 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
            resultContext.addVariableToContext(lambda0);
            resultContext.addVariableToContext(lambda1);
            resultContext.addVariableToContext(lambda2);

            resultContext.bindValueToContext(lambda0, (Command) visitDispatch(conditional.condition));
            resultContext.bindValueToContext(lambda1, (Command) visitDispatch(conditional.ifExp));
            resultContext.bindValueToContext(lambda2, (Command) visitDispatch(conditional.elseExp));

            //x neq 0


            //k x1
            Command[] x1AsVar = new Command[1];
            x1AsVar[0] = new ExpSt(lambda1);
            lambda_calculus.cps_ast.tree.expression.Var[] x1AsVar2 = new lambda_calculus.cps_ast.tree.expression.Var[1];
            x1AsVar2[0] = lambda1;
            Application kX1 = new Application(continuationMap.get(conditional), x1AsVar);
            Abstraction thenBody = new Abstraction(x1AsVar2, kX1);

            continuationMap.put(conditional.ifExp, thenBody);

            //k x2
            Command[] x2AsVar = new Command[1];
            x2AsVar[0] = new ExpSt(lambda2);
            lambda_calculus.cps_ast.tree.expression.Var[] x2AsVar2 = new lambda_calculus.cps_ast.tree.expression.Var[1];
            x2AsVar2[0] = lambda2;
            Application kX2 = new Application(continuationMap.get(conditional), x2AsVar);
            Abstraction elseBody = new Abstraction(x2AsVar2, kX2);

            continuationMap.put(conditional.elseExp, elseBody);

            //when we have more to evaluate[], we do not return anything , we simply dispatch.
            lambda_calculus.cps_ast.tree.expression.Var[] x0AsVar = new lambda_calculus.cps_ast.tree.expression.Var[1];
            x0AsVar[0] = lambda0;
            Abstraction continuationForE0 = new Abstraction(x0AsVar, new If(lambda0, (Command) visitDispatch(conditional.ifExp), (Command) visitDispatch(conditional.elseExp)));
            continuationMap.put(conditional.condition, continuationForE0);
            visitDispatch(conditional.condition);

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


