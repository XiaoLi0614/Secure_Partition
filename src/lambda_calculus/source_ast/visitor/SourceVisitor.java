package lambda_calculus.source_ast.visitor;

import lambda_calculus.source_ast.tree.expression.Conditional;
import lambda_calculus.source_ast.tree.expression.ObjectMethod;
import lambda_calculus.source_ast.tree.expression.Var;
import lambda_calculus.source_ast.tree.expression.id.GId;
import lambda_calculus.source_ast.tree.expression.id.Id;
import lambda_calculus.source_ast.tree.expression.literal.IntLiteral;
import lambda_calculus.source_ast.tree.expression.literal.Literal;
import lambda_calculus.source_ast.tree.expression.op.BinaryOp;
import lambda_calculus.source_ast.tree.expression.op.Plus;
import lambda_calculus.source_ast.tree.expression.op.Sequence;

public interface SourceVisitor<R> {
    interface ExpressionVisitor<R>{
        interface GIdVisitor<R>{
            //R visit(GId gId);
            R visit(Id id);
        }

        interface LiteralVisitor<R>{
            //R visit(Literal literal);
            R visit(IntLiteral intLiteral);
        }

        interface BinaryOpVisitor<R>{
            //R visit(BinaryOp binaryOp);
            R visit(Plus plus);
            R visit(Sequence sequence);
        }

        R visit(Conditional conditional);
        R visit(ObjectMethod objectmethod);
        R visit(Var var);
    }
}