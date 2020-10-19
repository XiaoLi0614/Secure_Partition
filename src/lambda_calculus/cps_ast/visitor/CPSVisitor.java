package lambda_calculus.cps_ast.visitor;

import lambda_calculus.cps_ast.tree.command.*;
import lambda_calculus.cps_ast.tree.expression.Conditional;
import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.tree.expression.id.Id;
import lambda_calculus.cps_ast.tree.expression.literal.IntLiteral;
import lambda_calculus.cps_ast.tree.expression.op.Plus;

public interface CPSVisitor {
    interface ExpressionVisitor<R>{
        interface GIdVisitor<R>{
            R visit(Id id);
        }

        interface LiteralVisitor<R>{
            R visit(IntLiteral intLiteral);
        }

        interface BinaryOpVisitor<R>{
            R visit(Plus plus);
        }

        R visit(Conditional conditional);
        R visit(Var var);
    }
    interface CommandVisitor<R>{
        R visit(Abstraction abstraction);
        R visit(Application application);
        R visit(ExpSt expSt);
        R visit(If iF);
        R visit(Sequence sequence);
        R visit(SingleCall singleCall);
    }
}
