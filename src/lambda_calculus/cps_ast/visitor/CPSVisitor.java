package lambda_calculus.cps_ast.visitor;

import lambda_calculus.cps_ast.tree.command.*;
import lambda_calculus.cps_ast.tree.expression.Conditional;
import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.tree.expression.id.GId;
import lambda_calculus.cps_ast.tree.expression.id.Id;
import lambda_calculus.cps_ast.tree.expression.literal.IntLiteral;
import lambda_calculus.cps_ast.tree.expression.literal.Literal;
import lambda_calculus.cps_ast.tree.expression.op.BinaryOp;
import lambda_calculus.cps_ast.tree.expression.op.Compare;
import lambda_calculus.cps_ast.tree.expression.op.Plus;

public interface CPSVisitor<R> {
    interface ExpressionVisitor<R>{
        R visit(GId gId);
        R visit(Literal literal);
        R visit(BinaryOp binaryOp);

        interface GIdVisitor<R>{
            R visit(Id id);
        }

        interface LiteralVisitor<R>{
            R visit(IntLiteral intLiteral);
        }

        interface BinaryOpVisitor<R>{
            R visit(Plus plus);
            R visit(Compare compare);
        }

        R visit(Conditional conditional);
        R visit(Var var);
    }

    R visit(Command command);
    interface CommandVisitor<R>{
        R visit(Abstraction abstraction);
        R visit(Application application);
        R visit(ExpSt expSt);
        R visit(If iF);
        R visit(Sequence sequence);
        R visit(SingleCall singleCall);
        R visit(ThisMethod thisMethod);
    }
}
