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
import lambda_calculus.cps_ast.tree.expression.op.Compare;
import lambda_calculus.cps_ast.tree.expression.op.Plus;

public class translateToOtherAST implements CPSVisitor {
    lambda_calculus.partition_package.tree.command.Command resultAST;
    //Context resultContext;

    public translateToOtherAST() {
        //resultContext = c;
    }

    public Object visitDispatch(Expression expression) {
        return expression.accept(expressionB);
    }
    public translateToOtherAST.ExpressionB expressionB =  new translateToOtherAST.ExpressionB();
    public class ExpressionB implements ExpressionVisitor<Object> {
        public class GIdB implements GIdVisitor<Object>{
            @Override
            public Object visit(Id id){ return new lambda_calculus.partition_package.tree.expression.id.Id(id.lexeme);}
        }
        translateToOtherAST.ExpressionB.GIdB gIdB = new translateToOtherAST.ExpressionB.GIdB();
        @Override
        public Object visit(GId gId){ return gId.accept(gIdB); }

        public class LiteralB implements LiteralVisitor<Object>{
            @Override
            public Object visit(IntLiteral intLiteral){ return new lambda_calculus.partition_package.tree.expression.literal.IntLiteral(Integer.valueOf(intLiteral.lexeme));}
        }
        translateToOtherAST.ExpressionB.LiteralB literalB = new translateToOtherAST.ExpressionB.LiteralB();
        @Override
        public Object visit(Literal literal){ return literal.accept(literalB); }

        public class BinaryOpB implements BinaryOpVisitor<Object>{
            @Override
            public Object visit(Plus plus){
                lambda_calculus.partition_package.tree.expression.Expression op1 = (lambda_calculus.partition_package.tree.expression.Expression)visitDispatch(plus.operand1);
                lambda_calculus.partition_package.tree.expression.Expression op2 = (lambda_calculus.partition_package.tree.expression.Expression)visitDispatch(plus.operand2);
                return new lambda_calculus.partition_package.tree.expression.op.Plus(op1, op2);
            }

            @Override
            public Object visit(Compare compare){
                lambda_calculus.partition_package.tree.expression.Expression op1 = (lambda_calculus.partition_package.tree.expression.Expression)visitDispatch(compare.operand1);
                lambda_calculus.partition_package.tree.expression.Expression op2 = (lambda_calculus.partition_package.tree.expression.Expression)visitDispatch(compare.operand2);
                return new lambda_calculus.partition_package.tree.expression.op.Compare(compare.operatorText, op1, op2);
            }
        }
        translateToOtherAST.ExpressionB.BinaryOpB binaryOpB = new translateToOtherAST.ExpressionB.BinaryOpB();
        @Override
        public Object visit(BinaryOp binaryOp){ return binaryOp.accept(binaryOpB); }

        @Override
        public Object visit(Var var){
            lambda_calculus.partition_package.tree.expression.Var resultVar = new lambda_calculus.partition_package.tree.expression.Var((lambda_calculus.partition_package.tree.expression.id.Id)visitDispatch(var.name));
            return resultVar;
        }

        @Override
        public Object visit(Conditional conditional){
            lambda_calculus.partition_package.tree.expression.Expression resultCondition = (lambda_calculus.partition_package.tree.expression.Expression)visitDispatch(conditional.condition);
            lambda_calculus.partition_package.tree.expression.Expression resultIf = (lambda_calculus.partition_package.tree.expression.Expression)visitDispatch(conditional.ifExp);
            lambda_calculus.partition_package.tree.expression.Expression resultElse = (lambda_calculus.partition_package.tree.expression.Expression)visitDispatch(conditional.elseExp);
            return new lambda_calculus.partition_package.tree.expression.Conditional(resultCondition, resultIf, resultElse);
        }
    }

    public Object visitDispatch(Command command) {
        return command.accept(commandB);
    }
    public translateToOtherAST.CommandB commandB =  new translateToOtherAST.CommandB();
    public class CommandB implements CommandVisitor<Object> {
        //the only place we need to reduce is in the application of an abstraction
        @Override
        public Object visit(Application application){
            if(application.values == null || application.values.length == 0){
                return null;
            }
            else if(application.values.length == 1){
                //there is no application in the target language, we eliminate the k in this step
                //we translate k into ret and eliminate the k with recursion
                if(application.values[0] instanceof ThisMethod){
                    return (lambda_calculus.partition_package.tree.command.Command)visitDispatch(application.values[0]);
                }
                else if(application.values[0] instanceof ExpSt){
                    Expression[] arg = new Expression[1];
                    arg[0] = ((ExpSt)application.values[0]).expression;
                    lambda_calculus.partition_package.tree.command.Command resultCommand = (lambda_calculus.partition_package.tree.command.Command)visitDispatch(new ThisMethod("ret", arg));
                    return resultCommand;
                }
                else return null;
            }
            else return new Error("Application(except administrative continuation) after reduction appears.");
        }

        @Override
        public Object visit(Abstraction abstraction){
            return null; }

        @Override
        public Object visit(ExpSt expSt){
            lambda_calculus.partition_package.tree.expression.Expression resultExpression = (lambda_calculus.partition_package.tree.expression.Expression)visitDispatch(expSt.expression);
            return new lambda_calculus.partition_package.tree.command.ExpSt(resultExpression);}

        @Override
        public Object visit(If iF){
            lambda_calculus.partition_package.tree.expression.Expression resultCondition = (lambda_calculus.partition_package.tree.expression.Expression)visitDispatch(iF.condition);
            lambda_calculus.partition_package.tree.command.Command resultIf = (lambda_calculus.partition_package.tree.command.Command)visitDispatch(iF.command1);
            lambda_calculus.partition_package.tree.command.Command resultElse = (lambda_calculus.partition_package.tree.command.Command)visitDispatch(iF.command2);

            return new lambda_calculus.partition_package.tree.command.If(resultCondition,
                    resultIf,
                    resultElse);
        }

        /*@Override
        public Object visit(Sequence sequence){
            lambda_calculus.partition_package.tree.command.Command result1 = (lambda_calculus.partition_package.tree.command.Command)visitDispatch(sequence.command1);
            lambda_calculus.partition_package.tree.command.Command result2 = (lambda_calculus.partition_package.tree.command.Command)visitDispatch(sequence.command2);
            return new lambda_calculus.partition_package.tree.command.Sequence(result1, result2);
        }*/

        //the sequence should be translated to a single call, because the second statement is contained in the continuation of the first one.
        @Override
        public Object visit(Sequence sequence){
            lambda_calculus.partition_package.tree.command.Command result1 = (lambda_calculus.partition_package.tree.command.Command)visitDispatch(sequence.command1);
            //lambda_calculus.partition_package.tree.command.Command result2 = (lambda_calculus.partition_package.tree.command.Command)visitDispatch(sequence.command2);
            return result1;
        }

        @Override
        public Object visit(SingleCall singleCall){
            lambda_calculus.partition_package.tree.expression.id.Id oN = (lambda_calculus.partition_package.tree.expression.id.Id)visitDispatch(singleCall.objectName);
            lambda_calculus.partition_package.tree.expression.id.Id mN = (lambda_calculus.partition_package.tree.expression.id.Id)visitDispatch(singleCall.methodName);
            lambda_calculus.partition_package.tree.expression.Var aX = (lambda_calculus.partition_package.tree.expression.Var)visitDispatch(singleCall.administrativeX);
            lambda_calculus.partition_package.tree.command.Command resultNestedC = (lambda_calculus.partition_package.tree.command.Command)visitDispatch(singleCall.nestedCommand);
            lambda_calculus.partition_package.tree.expression.Expression[] resultArgs;
            if(singleCall.args == null || singleCall.args.length == 0){
                resultArgs = null;
            }
            else {
                resultArgs = new lambda_calculus.partition_package.tree.expression.Expression[singleCall.args.length];
                for(int i = 0; i < singleCall.args.length; i++){
                    resultArgs[i] = (lambda_calculus.partition_package.tree.expression.Expression)visitDispatch(singleCall.args[i]);
                }
            }

            return new lambda_calculus.partition_package.tree.command.SingleCall(oN,
                    mN,
                    resultArgs,
                    aX,
                    resultNestedC);
        }

        @Override
        public Object visit(ThisMethod thisMethod){
            lambda_calculus.partition_package.tree.expression.id.Id mN = (lambda_calculus.partition_package.tree.expression.id.Id)visitDispatch(thisMethod.methodName);
            lambda_calculus.partition_package.tree.expression.Expression[] resultArgs;
            if(thisMethod.args == null || thisMethod.args.length == 0){
                resultArgs = null;
            }
            else {
                resultArgs = new lambda_calculus.partition_package.tree.expression.Expression[thisMethod.args.length];
                for(int i = 0; i < thisMethod.args.length; i++){
                    resultArgs[i] = (lambda_calculus.partition_package.tree.expression.Expression)visitDispatch(thisMethod.args[i]);
                }
            }

            return new lambda_calculus.partition_package.tree.command.SingleCall(mN, resultArgs);
        }


    }

    //CommandB commandB = new CommandB();
    @Override
    public Object visit(Command command){ return command.accept(commandB); }

    public lambda_calculus.partition_package.tree.command.Command getAST(Command c){
        translateToOtherAST b = new translateToOtherAST();
        resultAST = (lambda_calculus.partition_package.tree.command.Command) b.visitDispatch(c);
        return resultAST;
    }
}
