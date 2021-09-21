package lambda_calculus.source_ast.visitor;

import lambda_calculus.source_ast.tree.expression.Conditional;
import lambda_calculus.source_ast.tree.expression.ObjectMethod;
import lambda_calculus.source_ast.tree.expression.ThisMethod;
import lambda_calculus.source_ast.tree.expression.Var;
import lambda_calculus.source_ast.tree.expression.id.GId;
import lambda_calculus.source_ast.tree.expression.id.Id;
import lambda_calculus.source_ast.tree.expression.literal.IntLiteral;
import lambda_calculus.source_ast.tree.expression.literal.Literal;
import lambda_calculus.source_ast.tree.expression.op.BinaryOp;
import lambda_calculus.source_ast.tree.expression.op.Compare;
import lambda_calculus.source_ast.tree.expression.op.Plus;
import lambda_calculus.source_ast.tree.expression.op.Sequence;

public interface SourceVisitor<R> {
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
            R visit(Sequence sequence);
            R visit(Compare compare);
        }

        R visit(Conditional conditional);
        R visit(ObjectMethod objectmethod);
        R visit(ThisMethod thismethod);
        R visit(Var var);
    }
}
