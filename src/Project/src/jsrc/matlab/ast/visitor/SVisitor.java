package jsrc.matlab.ast.visitor;

import jsrc.matlab.ast.tree.declaration.Function;
import jsrc.matlab.ast.tree.declaration.Script;
import jsrc.matlab.ast.tree.declaration.type.*;
import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.ast.tree.expression.literal.*;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.tree.expression.op.application.*;
import jsrc.matlab.ast.tree.expression.op.constructors.RangeVectorConstructor;
import jsrc.matlab.ast.tree.expression.op.constructors.MatrixConstructor;
import jsrc.matlab.ast.tree.expression.op.logical.*;
import jsrc.matlab.ast.tree.expression.op.math.*;
import jsrc.matlab.ast.tree.expression.op.relational.*;
import jsrc.matlab.ast.tree.statement.*;
import jsrc.matlab.typeinference.type.TupleType;
import jsrc.matlab.typeinference.type.TypeVar;

public interface SVisitor<R> {

    R visit(Function function);
    R visit(Script script);

    //R visit(Statement statement);
    public interface StatementVisitor<S> {
        S visit(Assignment assignment);
        S visit(ArrayAssignment arrayAssignment);
        S visit(CallSt callSt);
        S visit(CallAndMultiAssignment callAndMultiAssignment);

        S visit(If anIf);
        S visit(For aFor);
        S visit(While aWhile);
        S visit(Switch aSwitch);

        S visit(Break aBreak);
        S visit(Return aReturn);

        S visit(Block block);

        S visit(Print print);
        S visit(Println println);
    }

    //R visit(Expression Expression);
    public interface ExpressionVisitor<U> {

        U visit(Literal literal);
        public interface LiteralVisitor<S> {
            S visit(IntLiteral intLiteral);
            S visit(DoubleLiteral doubleLiteral);
            S visit(StringLiteral stringLiteral);

            S visit(Colon colon);
            S visit(End end);
        }

        U visit(Op op);
        public interface OpVisitor<S> {

            S visit(LogicalOp logicalOp);
            public interface LogicalOpVisitor<T> {
                T visit(And and);
                T visit(Or or);
                T visit(Not not);
                T visit(ElementWiseAnd elementWiseAnd);
                T visit(ElementWiseOr elementWiseOr);
            }

            S visit(RelationalOp relationalOp);
            public interface RelationalVisitor<T> {
                T visit(Equality equality);
                T visit(NotEquality notEquality);
                T visit(LessThan lessThan);
                T visit(GreaterThan greaterThan);
                T visit(LessThanEqual lessThanEqual);
                T visit(GreaterThanEqual greaterThanEqual);
            }

            S visit(MathOp mathOp);
            public interface MathOpVisitor<T> {
                T visit(Divide divide);
                T visit(Times times);
                T visit(DotTimes dotTimes);
                T visit(DotDivide dotDivide);
                T visit(Power power);
                T visit(DotPower dotPower);
//                T visit(Modulus modulus);
                T visit(Minus minus);
                T visit(Plus plus);

                T visit(UnaryMinus unaryMinus);
                T visit(UnaryPlus unaryPlus);

                T visit(Transpose transpose);
            }

            S visit(Call call);
            S visit(ArrayAccess arrayAccess);
            S visit(CallOrArrayAccess callOrArrayAccess);

            S visit(RangeVectorConstructor rangeVectorConstructor);
            S visit(MatrixConstructor matrixConstructor);
        }

        U visit(Id id);
    }

    public interface TypeVisitor<S> {
        S visit(UnitType unitType);
        S visit(IntType intType);
        S visit(DoubleType doubleType);
        S visit(BoolType boolType);
        S visit(StringType stringType);
        S visit(MatrixType matrixType);
        S visit(TupleType tupleType);
        S visit(TypeVar typeVar);
    }

}

