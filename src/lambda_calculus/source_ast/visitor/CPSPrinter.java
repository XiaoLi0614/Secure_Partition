package lambda_calculus.source_ast.visitor;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lambda_calculus.cps_ast.tree.Context;
import lambda_calculus.cps_ast.tree.command.*;
import lambda_calculus.source_ast.tree.expression.*;
import lambda_calculus.source_ast.tree.expression.ThisMethod;
import lambda_calculus.source_ast.tree.expression.id.GId;
import lambda_calculus.source_ast.tree.expression.id.Id;
import lambda_calculus.source_ast.tree.expression.literal.IntLiteral;
import lambda_calculus.source_ast.tree.expression.literal.Literal;
import lambda_calculus.source_ast.tree.expression.op.BinaryOp;
import lambda_calculus.source_ast.tree.expression.op.Compare;
import lambda_calculus.source_ast.tree.expression.op.Plus;
import lambda_calculus.source_ast.tree.expression.op.Sequence;
import lesani.collection.Pair;
import lesani.compiler.texttree.seq.TextSeq;


import java.util.HashMap;


public class CPSPrinter implements SourceVisitor{
    //TextSeq seq;
    int administrativeX;
    Command resultAST;
    Context resultContext;
    HashMap<Expression, Command>  continuationMap;

    public CPSPrinter() {
        //seq = new TextSeq();
        administrativeX = 0;
        resultContext = new Context();
        continuationMap = new HashMap<>();
    }

    //print method for all the expressions
    public Pair<Command, Context> print(Expression expression){
        CPSPrinter p = new CPSPrinter();
        p.continuationMap.put(expression, new ExpSt(new lambda_calculus.cps_ast.tree.expression.Var("k")));
        resultAST = (Command) p.visitDispatch(expression);
        Pair<Command, Context> returnPair = new Pair<>(resultAST, resultContext);
        return returnPair;
        //return p.getText();
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
                //System.out.println("CPS ID");
                lambda_calculus.cps_ast.tree.expression.id.Id resultId = new lambda_calculus.cps_ast.tree.expression.id.Id(id.lexeme);
                //Command[] resultVar = new Command[1];
                //resultVar[0] = new ExpSt(resultId);
                //Application resultA = new Application(continuationMap.get(id), resultVar);

                return resultId;
            }
        }

        GIdT gIdT = new GIdT();
        @Override
        public Object visit(GId gId){ return gId.accept(gIdT); }

        public class LiteralT implements LiteralVisitor<Object>{
            //[n]k = k n
            @Override
            public Object visit(IntLiteral intLiteral){
                //System.out.println("CPS int");
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
                //System.out.println("CPS plus");
                lambda_calculus.cps_ast.tree.expression.Var[] lambda1Var = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambda1Var[0] = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
                lambda_calculus.cps_ast.tree.expression.Var[] lambda2Var = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambda2Var[0] = new lambda_calculus.cps_ast.tree.expression.Var(newXName());

                //x1 + x2
                lambda_calculus.cps_ast.tree.expression.op.Plus varPlus = new lambda_calculus.cps_ast.tree.expression.op.Plus(lambda1Var[0], lambda2Var[0]);
                Command[] plusBody = new Command[1];
                plusBody[0] = new ExpSt(varPlus);

                //lambda x2 . k(x1 + x2)
                Command body2 = new Abstraction(lambda2Var, new Application(continuationMap.get(plus), plusBody));
                //lambda x2
                resultContext.addVariableToContext(lambda2Var[0], body2);
                continuationMap.put(plus.operand2, body2);
                Command e2Evaluation = (Command) visitDispatch(plus.operand2);
                //resultContext.bindValueToContext(lambda2Var[0], e2Evaluation);

                //-----------------------------------------------------------------
                //lambda x1. [e2] (lambda x2.k( x1 + x2))
                Command body1 = new Abstraction(lambda1Var, e2Evaluation);
                // lambda x1
                resultContext.addVariableToContext(lambda1Var[0], body1);
                continuationMap.put(plus.operand1, body1);
                Command e1Evaluation = (Command) visitDispatch(plus.operand1);
                //resultContext.bindValueToContext(lambda1Var[0], e1Evaluation);
                //todo: I think the bindings are not correct now. I confuse the evaluation and the translation part

                //Command[] body2AsVar = new Command[1];
                //body2AsVar[0] = body2;
                //Command[] body1AsVar = new Command[1];
                //body1AsVar[0] = body1;
                //Command resultCommand = new Application((Command) visitDispatch(plus.operand1), body1AsVar);

                return e1Evaluation;
                //return null;
            }

            @Override
            public Object visit(Compare compare){
                //System.out.println("CPS plus");
                lambda_calculus.cps_ast.tree.expression.Var[] lambda1Var = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambda1Var[0] = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
                lambda_calculus.cps_ast.tree.expression.Var[] lambda2Var = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambda2Var[0] = new lambda_calculus.cps_ast.tree.expression.Var(newXName());

                //x1 + x2
                lambda_calculus.cps_ast.tree.expression.op.Compare varCompare = new lambda_calculus.cps_ast.tree.expression.op.Compare(compare.operatorText, lambda1Var[0], lambda2Var[0]);
                Command[] compareBody = new Command[1];
                compareBody[0] = new ExpSt(varCompare);

                //lambda x2 . k(x1 + x2)
                Command body2 = new Abstraction(lambda2Var, new Application(continuationMap.get(compare), compareBody));
                //lambda x2
                resultContext.addVariableToContext(lambda2Var[0], body2);
                continuationMap.put(compare.operand2, body2);
                Command e2Evaluation = (Command) visitDispatch(compare.operand2);
                //resultContext.bindValueToContext(lambda2Var[0], e2Evaluation);

                //-----------------------------------------------------------------
                //lambda x1. [e2] (lambda x2.k( x1 + x2))
                Command body1 = new Abstraction(lambda1Var, e2Evaluation);
                // lambda x1
                resultContext.addVariableToContext(lambda1Var[0], body1);
                continuationMap.put(compare.operand1, body1);
                Command e1Evaluation = (Command) visitDispatch(compare.operand1);
                //resultContext.bindValueToContext(lambda1Var[0], e1Evaluation);
                //todo: I think the bindings are not correct now. I confuse the evaluation and the translation part

                //Command[] body2AsVar = new Command[1];
                //body2AsVar[0] = body2;
                //Command[] body1AsVar = new Command[1];
                //body1AsVar[0] = body1;
                //Command resultCommand = new Application((Command) visitDispatch(plus.operand1), body1AsVar);

                return e1Evaluation;
                //return null;
            }

/*            //[e1; e2]k = [e1]([e2] (lambda x. k x))
            @Override
            public Object visit(Sequence sequence){
                //lambda x
                lambda_calculus.cps_ast.tree.expression.Var lambdaVar = new lambda_calculus.cps_ast.tree.expression.Var(newXName());

                lambda_calculus.cps_ast.tree.expression.Var[] lambdaAsVar1 = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambdaAsVar1[0] = lambdaVar;

                Command[] lambdaAsVar2 = new Command[1];
                lambdaAsVar2[0] = new ExpSt(lambdaVar);

                Command bodyForE2 = new Abstraction(lambdaAsVar1, new Application(continuationMap.get(sequence), lambdaAsVar2));
                resultContext.addVariableToContext(lambdaVar, bodyForE2);
                //Command[] bodyAsVar = new Command[1];
                //bodyAsVar[0] = bodyForE2;
                //Command[] bodyAsVar2 = new Command[1];
                //bodyAsVar2[0] = new Application(
                        //(Command) visitDispatch(sequence.operand2),
                        //bodyAsVar);
                continuationMap.put(sequence.operand1, new ExpSt(new lambda_calculus.cps_ast.tree.expression.Var("null")));
                Command e1Evaluation = (Command) visitDispatch(sequence.operand1);
                continuationMap.put(sequence.operand2, bodyForE2);
                Command e2Evaluation = (Command) visitDispatch(sequence.operand2);

                lambda_calculus.cps_ast.tree.command.Sequence resultSequence = new lambda_calculus.cps_ast.tree.command.Sequence(e1Evaluation, e2Evaluation);

                //add continuation and context
                //continuationMap.put(sequence.operand1, bodyAsVar2[0]);
                //resultContext.bindValueToContext(lambdaVar, e2Evaluation);

                //Command resultCommand = new Application(
                        //(Command) visitDispatch(sequence.operand1),
                        //bodyAsVar2);
                return resultSequence;
                //return null;
                //return resultCommand;}
            }
        }*/

            //[e1; e2]k = [e1]([e2] (lambda x. k x))
            //[e1; e2]k = [e1] \lambda x1 ([e2](lambda x2. k x2))
            @Override
            public Object visit(Sequence sequence){
                //System.out.println("CPS sequence");
                //lambda x
                lambda_calculus.cps_ast.tree.expression.Var lambdaVar = new lambda_calculus.cps_ast.tree.expression.Var(newXName());

                lambda_calculus.cps_ast.tree.expression.Var[] lambdaAsVar1 = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambdaAsVar1[0] = lambdaVar;

                Command[] lambdaAsVar2 = new Command[1];
                lambdaAsVar2[0] = new ExpSt(lambdaVar);

                Command bodyForE2 = new Abstraction(lambdaAsVar1, new Application(continuationMap.get(sequence), lambdaAsVar2));
                resultContext.addVariableToContext(lambdaVar, bodyForE2);
                //Command[] bodyAsVar = new Command[1];
                //bodyAsVar[0] = bodyForE2;
                //Command[] bodyAsVar2 = new Command[1];
                //bodyAsVar2[0] = new Application(
                        //(Command) visitDispatch(sequence.operand2),
                        //bodyAsVar);

                //produce a lambda no matter this is a sequence or not (\lambda x1)
                lambda_calculus.cps_ast.tree.expression.Var lambdaX1Var = new lambda_calculus.cps_ast.tree.expression.Var(newXName());

                lambda_calculus.cps_ast.tree.expression.Var[] lambdaX1AsVar1 = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambdaX1AsVar1[0] = lambdaX1Var;

                continuationMap.put(sequence.operand2, bodyForE2);
                Command e2Evaluation = (Command) visitDispatch(sequence.operand2);

                Command[] lambdaAsVarX1 = new Command[1];
                lambdaAsVarX1[0] = new ExpSt(lambdaX1Var);

                Command bodyForE1 = new Abstraction(lambdaX1AsVar1, e2Evaluation);
                resultContext.addVariableToContext(lambdaX1Var, bodyForE1);

                continuationMap.put(sequence.operand1, bodyForE1);
                Command e1Evaluation = (Command) visitDispatch(sequence.operand1);

                //todo: this is the old implementation, we may need to change back to this
                //lambda_calculus.cps_ast.tree.command.Sequence resultSequence = new lambda_calculus.cps_ast.tree.command.Sequence(e1Evaluation, e2Evaluation);
                //return resultSequence;

                //add continuation and context
                //continuationMap.put(sequence.operand1, bodyAsVar2[0]);
                //resultContext.bindValueToContext(lambdaVar, e2Evaluation);

                //Command resultCommand = new Application(
                        //(Command) visitDispatch(sequence.operand1),
                        //bodyAsVar2);
                //return null;
                //return resultCommand;}

                return e1Evaluation;
            }
        }

        BinaryOpT binaryT = new BinaryOpT();
        @Override
        public Object visit(BinaryOp binaryOp){ return binaryOp.accept(binaryT); }

        //[o.m(e)] k = [e] (lambda x1. call x2 = o.m(x1) in k x2)
        @Override
        public Object visit(ObjectMethod objectMethod) {
            //System.out.println("CPS object method " + objectMethod.objectName.lexeme + "." + objectMethod.methodName.lexeme);
            //TODO: we need to change to multiple output
            //add bind according to the names
            lambda_calculus.cps_ast.tree.expression.Var lambda2;
            if(objectMethod.adminNames == null){
                lambda2 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
            }
            else {
                lambda2 = new lambda_calculus.cps_ast.tree.expression.Var(objectMethod.adminNames.lexeme);
            }
            //lambda_calculus.cps_ast.tree.expression.Var lambda2 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
            //k x2
            Command[] x2AsVar = new Command[1];
            x2AsVar[0] = new ExpSt(lambda2);
            Application kX2 = new Application(continuationMap.get(objectMethod), x2AsVar);

            //[o.m()] k = call x = o.m() in (k x)
            //this is a termination
            if(objectMethod.args.length == 0 || objectMethod.args == null){
                SingleCall call = new SingleCall(
                        objectMethod.objectName.lexeme, objectMethod.methodName.lexeme, null, lambda2, kX2);
                resultContext.addVariableToContext(lambda2, call);
                return call;
            }
            // if there are arguments, this is not a termination
            else {
                //change this to multiple inputs
                lambda_calculus.cps_ast.tree.expression.Expression[] lambda1AsVar = new lambda_calculus.cps_ast.tree.expression.Expression[objectMethod.args.length];
                lambda_calculus.cps_ast.tree.expression.Var[] lambda1AsVar2 = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambda_calculus.cps_ast.tree.expression.Var[] lambda1AsVar3 = new lambda_calculus.cps_ast.tree.expression.Var[objectMethod.args.length];
                for(int i = 0; i < objectMethod.args.length; i++){
                    lambda_calculus.cps_ast.tree.expression.Var lambda1 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
                    lambda1AsVar[i] = lambda1;
                    lambda1AsVar3[i] = lambda1;
                    if(i == objectMethod.args.length - 1){
                        lambda1AsVar2[0] = lambda1;
                    }
                }
                //x1
                //lambda_calculus.cps_ast.tree.expression.Var lambda1 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
                //lambda_calculus.cps_ast.tree.expression.Expression[] lambda1AsVar = new lambda_calculus.cps_ast.tree.expression.Expression[1];
                //lambda1AsVar[0] = lambda1;
                //lambda_calculus.cps_ast.tree.expression.Var[] lambda1AsVar2 = new lambda_calculus.cps_ast.tree.expression.Var[1];
                //lambda1AsVar2[0] = lambda1;
                SingleCall call2 = new SingleCall(
                        objectMethod.objectName.lexeme,
                        objectMethod.methodName.lexeme,
                        lambda1AsVar,
                        lambda2,
                        kX2);

                resultContext.addVariableToContext(lambda2, call2);


                Abstraction bodyForE = new Abstraction(lambda1AsVar2, call2);
                //[o.m(e1, e2)]k = [e1](lambda x1. ([e2] lambda x2. call x = o.m(x1, x2) in k x))
                //[o.m(e1, e2)]k = [e2][e1](lambda x1, x2. call x = o.m(x1, x2) in k x)

                //resultContext.addVariableToContext(lambda1, bodyForE);
                //continuationMap.put(objectMethod.args[0], bodyForE);

                //change the implementation for multiple inputs to have the first format
                Command interEvaluation = bodyForE;
                for(int i = objectMethod.args.length - 1; i >= 0; i--){
                    //[e2] lambda x2. call x = o.m(x1, x2) in k x)
                    if(i == objectMethod.args.length - 1){
                        resultContext.addVariableToContext(lambda1AsVar3[i], bodyForE);
                        continuationMap.put(objectMethod.args[i], bodyForE);
                        interEvaluation = (Command) visitDispatch(objectMethod.args[i]);
                    }
                    //[e1](lambda x1. ([e2] lambda x2. call x = o.m(x1, x2) in k x))
                    else {
                        lambda_calculus.cps_ast.tree.expression.Var[] lambdaVar = new lambda_calculus.cps_ast.tree.expression.Var[1];
                        lambdaVar[0] = lambda1AsVar3[i];
                        Abstraction bodyForE1 = new Abstraction(lambdaVar, interEvaluation);
                        //todo: double check whether this is lambda1AsVar2 or lambda1AsVar3
                        resultContext.addVariableToContext(lambda1AsVar3[i], bodyForE1);
                        continuationMap.put(objectMethod.args[i], bodyForE1);
                        interEvaluation = (Command) visitDispatch(objectMethod.args[i]);
                    }
                }
                return interEvaluation;
            }
        }

        //[m(e)] k = [e] (\lambda x. k m(x))
        ////[m(e1, e2)]k = [e1](lambda x1. ([e2] lambda x2. k m(x1, x2)))
        @Override
        public Object visit(ThisMethod thisMethod) {
            //System.out.println("CPS this method");
            //[m()] k = k m()
            //this is a termination
            if(thisMethod.args.length == 0 || thisMethod.args == null){
                lambda_calculus.cps_ast.tree.command.ThisMethod method = new lambda_calculus.cps_ast.tree.command.ThisMethod(thisMethod.methodName.lexeme, null);
                Command[] methodAsVar = new Command[1];
                methodAsVar[0] = method;
                Application km = new Application(continuationMap.get(thisMethod), methodAsVar);
                return km;
            }
            // if there are arguments, this is not a termination
            else {
                //change this to multiple inputs
                lambda_calculus.cps_ast.tree.expression.Expression[] lambda1AsVar = new lambda_calculus.cps_ast.tree.expression.Expression[thisMethod.args.length];
                lambda_calculus.cps_ast.tree.expression.Var[] lambda1AsVar2 = new lambda_calculus.cps_ast.tree.expression.Var[1];
                lambda_calculus.cps_ast.tree.expression.Var[] lambda1AsVar3 = new lambda_calculus.cps_ast.tree.expression.Var[thisMethod.args.length];
                for(int i = 0; i < thisMethod.args.length; i++){
                    lambda_calculus.cps_ast.tree.expression.Var lambda1 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
                    lambda1AsVar[i] = lambda1;
                    lambda1AsVar3[i] = lambda1;
                    if(i == thisMethod.args.length - 1){
                        lambda1AsVar2[0] = lambda1;
                    }
                }
                lambda_calculus.cps_ast.tree.command.ThisMethod method2 = new lambda_calculus.cps_ast.tree.command.ThisMethod(
                        thisMethod.methodName.lexeme,
                        lambda1AsVar);
                Command[] methodAsVar2 = new Command[1];
                methodAsVar2[0] = method2;
                Application km2 = new Application(continuationMap.get(thisMethod), methodAsVar2);
                //lambda x2. k m(x1, x2)
                Abstraction bodyForE = new Abstraction(lambda1AsVar2, km2);
                //[m(e1, e2)]k = [e1](lambda x1. ([e2] lambda x2. k m(x1, x2)))
                Command interEvaluation = bodyForE;
                for(int i = thisMethod.args.length - 1; i >= 0; i--){
                    //[e2] lambda x2. k m(x1, x2))
                    if(i == thisMethod.args.length - 1){
                        resultContext.addVariableToContext(lambda1AsVar3[i], bodyForE);
                        continuationMap.put(thisMethod.args[i], bodyForE);
                        interEvaluation = (Command) visitDispatch(thisMethod.args[i]);
                    }
                    //[e1](lambda x1. ([e2] lambda x2. k m(x1, x2)))
                    else {
                        lambda_calculus.cps_ast.tree.expression.Var[] lambdaVar = new lambda_calculus.cps_ast.tree.expression.Var[1];
                        lambdaVar[0] = lambda1AsVar3[i];
                        Abstraction bodyForE1 = new Abstraction(lambdaVar, interEvaluation);
                        resultContext.addVariableToContext(lambda1AsVar2[i], bodyForE1);
                        continuationMap.put(thisMethod.args[i], bodyForE1);
                        interEvaluation = (Command) visitDispatch(thisMethod.args[i]);
                    }
                }
                return interEvaluation;
            }
        }

        //[if e0 then e1 else e2] k = [e0] (lambda x. if x neq 0 then [e1]( lambda x1. k x1) else [e2] (lambda x2. k x2))
        @Override
        public Object visit(Conditional conditional) {
            //System.out.println("CPS condition");
            //produce lambda
            lambda_calculus.cps_ast.tree.expression.Var lambda0 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
            lambda_calculus.cps_ast.tree.expression.Var lambda1 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());
            lambda_calculus.cps_ast.tree.expression.Var lambda2 = new lambda_calculus.cps_ast.tree.expression.Var(newXName());

            //x neq 0


            //k x1
            Command[] x1AsVar = new Command[1];
            x1AsVar[0] = new ExpSt(lambda1);
            lambda_calculus.cps_ast.tree.expression.Var[] x1AsVar2 = new lambda_calculus.cps_ast.tree.expression.Var[1];
            x1AsVar2[0] = lambda1;
            Application kX1 = new Application(continuationMap.get(conditional), x1AsVar);
            Abstraction thenBody = new Abstraction(x1AsVar2, kX1);

            resultContext.addVariableToContext(lambda1, thenBody);
            continuationMap.put(conditional.ifExp, thenBody);
            Command e1Value = (Command) visitDispatch(conditional.ifExp);
            //resultContext.bindValueToContext(lambda1, e1Value);

            //k x2
            Command[] x2AsVar = new Command[1];
            x2AsVar[0] = new ExpSt(lambda2);
            lambda_calculus.cps_ast.tree.expression.Var[] x2AsVar2 = new lambda_calculus.cps_ast.tree.expression.Var[1];
            x2AsVar2[0] = lambda2;
            Application kX2 = new Application(continuationMap.get(conditional), x2AsVar);
            Abstraction elseBody = new Abstraction(x2AsVar2, kX2);

            resultContext.addVariableToContext(lambda2, elseBody);
            continuationMap.put(conditional.elseExp, elseBody);
            Command e2Value = (Command) visitDispatch(conditional.elseExp);
            //resultContext.bindValueToContext(lambda2, e2Value);

            //when we have more to evaluate[], we do not return anything , we simply dispatch.
            lambda_calculus.cps_ast.tree.expression.Var[] x0AsVar = new lambda_calculus.cps_ast.tree.expression.Var[1];
            x0AsVar[0] = lambda0;
            Abstraction continuationForE0 = new Abstraction(x0AsVar, new If(lambda0, e1Value, e2Value));
            resultContext.addVariableToContext(lambda0, continuationForE0);
            continuationMap.put(conditional.condition, continuationForE0);
            Command e0Value = (Command) visitDispatch(conditional.condition);
            //resultContext.bindValueToContext(lambda0, e0Value);

            return e0Value;
            //return null;
        }

        //[x] k = k x
        @Override
        public Object visit(Var var) {
            //System.out.println("CPS var");
            Command[] varAsValue = new Command[1];
            visitDispatch(var.name);
            //varAsValue[0] = new ExpSt(new lambda_calculus.cps_ast.tree.expression.Var(var.name.lexeme));
            varAsValue[0] = new ExpSt(new lambda_calculus.cps_ast.tree.expression.Var((lambda_calculus.cps_ast.tree.expression.id.Id)visitDispatch(var.name)));
            Application resultCommand = new Application(continuationMap.get(var), varAsValue);
            //resultContext.bindValueToContext(var, );
            return resultCommand;
        }
    }
}


