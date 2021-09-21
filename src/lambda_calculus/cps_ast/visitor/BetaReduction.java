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
import lambda_calculus.cps_ast.tree.expression.op.Compare;
import lambda_calculus.cps_ast.tree.expression.op.Plus;
import lesani.compiler.texttree.seq.TextSeq;
import sun.nio.ch.AbstractPollArrayWrapper;

public class BetaReduction implements CPSVisitor{

    Command resultAST;
    //Context resultContext;

    public BetaReduction() {
        //resultContext = c;
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
            public Object visit(Plus plus){
                return new Plus((Expression) visitDispatch(plus.operand1), (Expression) visitDispatch(plus.operand2));
            }

            @Override
            public Object visit(Compare compare){
                return new Compare(compare.operatorText, (Expression) visitDispatch(compare.operand1), (Expression) visitDispatch(compare.operand2));
            }
        }
        BinaryOpB binaryOpB = new BinaryOpB();
        @Override
        public Object visit(BinaryOp binaryOp){ return binaryOp.accept(binaryOpB); }

        @Override
        public Object visit(Var var){ return  var; }

        @Override
        public Object visit(Conditional conditional){
            return new Conditional((Expression)visitDispatch(conditional.condition),
                    (Expression)visitDispatch(conditional.ifExp),
                    (Expression)visitDispatch(conditional.elseExp));
        }
    }

    public Object visitDispatch(Command command) {
        return command.accept(commandB);
    }
    public CommandB commandB =  new CommandB();
    public class CommandB implements CommandVisitor<Object> {
        //the only place we need to reduce is in the application of an abstraction
        @Override
        public Object visit(Application application){
            //(lambda x1, x2. (x1 + x2) )v1 v2
            //when the abstraction has more lambdas to fill than the applications, it is correct and we can proceed to substitution
            if(application.function instanceof Abstraction ){
                Command resultCommand = ((Abstraction)application.function).body;
                if(((Abstraction)application.function).lambdas.length == application.values.length){
                    //here we need to substitute the value in the function
                    for(int i = 0; i < application.values.length; i++){
                        if(application.values[i] instanceof ThisMethod){
                            resultCommand = ((Abstraction)application.function).body.substitute(
                                    ((Abstraction)application.function).lambdas[i],
                                    (ThisMethod) application.values[i]);
                        }
                        else {
                            resultCommand = ((Abstraction)application.function).body.substitute(
                                    ((Abstraction)application.function).lambdas[i],
                                    ((ExpSt)application.values[i]).expression);
                        }
                    }
                    return resultCommand;
                }
                ////(lambda x1, x2, x3. (x1 + x2) )v1 v2
                else if (((Abstraction)application.function).lambdas.length < application.values.length){
                    Command resultFunction = ((Abstraction)application.function).body;
                    for(int i = 0; i < application.values.length; i++){
                        resultFunction = ((Abstraction)application.function).body.substitute(
                                ((Abstraction)application.function).lambdas[i],
                                ((ExpSt)application.values[i]).expression);
                    }
                    Var[] resultLambdas = new Var[((Abstraction)application.function).lambdas.length - application.values.length];
                    for(int j = 0; j < resultLambdas.length; j++){
                        resultLambdas[j] = ((Abstraction)application.function).lambdas[j + application.values.length];
                    }
                    resultCommand = new Abstraction(resultLambdas, resultFunction);
                    return resultCommand;
                }
                //(x1 + x2) v1
                //when there are more values than the lambdas, it can be sequence situation
                else {
                    new Error("More values than lambdas in application");
                    return new Application((Command) visitDispatch(application.function), application.values);
                    //todo: this return is only the first function in order for sequence to work. May need to be generalized. For instance, the lambdas and values paired up and the rest of values are ignored
                    //return visitDispatch(application.function);
                }
            }
            //no application needed
            //else return new Application((Command) visitDispatch(application.function), application.values);
            //continuation situation
            else if (application.function.toString() == "k"){return new Application((Command) visitDispatch(application.function), application.values);}
            //we can add ret here
            //else if (application.function.toString() == "k"){
                //return new ThisMethod("ret", application.values);
            //}

            ////It can be the sequence situation here.
            else { return visitDispatch(application.function);}
        }


        @Override
        public Object visit(Abstraction abstraction){
            return new Abstraction(abstraction.lambdas, (Command)visitDispatch(abstraction.body)); }

        @Override
        public Object visit(ExpSt expSt){
            return new ExpSt((Expression)visitDispatch(expSt.expression)); }

        @Override
        public Object visit(If iF){
            return new If((Expression)visitDispatch(iF.condition),
                    (Command)visitDispatch(iF.command1),
                    (Command)visitDispatch(iF.command2)); }

        @Override
        public Object visit(Sequence sequence){
            return new Sequence((Command)visitDispatch(sequence.command1),
                    (Command)visitDispatch(sequence.command2)); }

        @Override
        public Object visit(SingleCall singleCall){
            return new SingleCall((Id)singleCall.objectName,
                    (Id)singleCall.methodName,
                    singleCall.args,
                    singleCall.administrativeX,
                    (Command)visitDispatch(singleCall.nestedCommand)); }

        @Override
        public Object visit(ThisMethod thismethod){
            return new ThisMethod((Id)thismethod.methodName,
                    thismethod.args); }
    }

    //CommandB commandB = new CommandB();
    @Override
    public Object visit(Command command){ return command.accept(commandB); }

    //can be used not only for beta reduction but also alpha convension
    //public Command substitute(Var originalVar, Command replacer, Command originalFunction){

    //}

    public Command wholeReduction(Command c){
        BetaReduction b = new BetaReduction();
        resultAST = (Command) b.visitDispatch(c);
        while(!resultAST.equals((Command)b.visitDispatch(resultAST))){
            resultAST = (Command)b.visitDispatch(resultAST);
        }
        return resultAST;
    }
}




