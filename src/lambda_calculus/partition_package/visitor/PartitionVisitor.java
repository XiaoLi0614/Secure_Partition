package lambda_calculus.partition_package.visitor;

import lambda_calculus.partition_package.tree.command.*;
import lambda_calculus.partition_package.tree.expression.Conditional;
import lambda_calculus.partition_package.tree.expression.Var;
import lambda_calculus.partition_package.tree.expression.id.GId;
import lambda_calculus.partition_package.tree.expression.id.Id;
import lambda_calculus.partition_package.tree.expression.literal.IntLiteral;
import lambda_calculus.partition_package.tree.expression.literal.Literal;
import lambda_calculus.partition_package.tree.expression.op.BinaryOp;
import lambda_calculus.partition_package.tree.expression.op.Plus;
import lambda_calculus.partition_package.tree.expression.op.Compare;

public interface PartitionVisitor <R> {
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
        R visit(ExpSt expSt);
        R visit(If iF);
        R visit(Sequence sequence);
        R visit(SingleCall singleCall);
        //R visit(MethodDefinition thisMethod);
    }
}
