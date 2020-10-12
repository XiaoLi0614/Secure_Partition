package jsrc.matlab.typeinference.constraintelicitation;


import fj.F2;
import jsrc.matlab.ast.tree.Node;
import jsrc.matlab.ast.tree.declaration.CompilationUnit;
import jsrc.matlab.ast.tree.declaration.Function;
import jsrc.matlab.ast.tree.declaration.Script;
import jsrc.matlab.ast.tree.declaration.type.*;
import jsrc.matlab.ast.tree.expression.*;
import jsrc.matlab.ast.tree.expression.literal.*;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.tree.expression.op.application.*;
import jsrc.matlab.ast.tree.expression.op.constructors.MatrixConstructor;
import jsrc.matlab.ast.tree.expression.op.constructors.RangeVectorConstructor;
import jsrc.matlab.ast.tree.expression.op.logical.*;
import jsrc.matlab.ast.tree.expression.op.math.*;
import jsrc.matlab.ast.tree.expression.op.relational.*;
import jsrc.matlab.ast.tree.statement.*;
import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.exceptions.SemanticErrorException;
import lesani.collection.option.None;
import lesani.compiler.typing.SymbolTable;
import jsrc.matlab.typeinference.type.*;
import jsrc.matlab.typeinference.unification.Constraint;
import lesani.collection.Pair;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.typing.exception.DuplicateDefExc;
import lesani.compiler.typing.exception.SymbolNotFoundException;
import lesani.collection.func.*;
import static lesani.math.LMath.*;
import static jsrc.matlab.typeinference.constraintelicitation.TypeDef.*;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import static lesani.gui.console.Logger.*;


public class ConstraintElicitor implements SVisitor {

    public static Pair<List<Constraint>, fj.data.List<List<List<Constraint>>>> elicit(CompilationUnit[] compilationUnits) {
        final Pair<List<Constraint>, fj.data.List<List<List<Constraint>>>> pair = new ConstraintElicitor(compilationUnits).elicit();
        println();
        println("Constraints:");
        println(printConsts(pair._1()));
        println("AltConstraints:");
        println(printAltConsts(pair._2()));

//        System.out.println();
//        System.out.println("Constraints:");
//        System.out.println(printConsts(pair._1()));
//        System.out.println("AltConstraints:");
//        System.out.println(printAltConsts(pair._2()));
        return pair;
    }

    public static String printConsts(List<Constraint> constraints) {
        String s = "";
        for (Constraint constraint : constraints) {
            s += constraint + "\n";
        }
        return s;
    }

    public static String printAltConsts(fj.data.List<List<List<Constraint>>> altConstraints) {
//        String s = "";
//        int altsNo = 0;
//        for (List<List<Constraint>> altConstraint : altConstraints) {
//            altsNo++;
//            s += "Alts no. " + altsNo + "\n";
//            int altNo = 0;
//            for (List<Constraint> constraints : altConstraint) {
//                altNo++;
//                s += "\tAlt no. " + altsNo + "-" + altNo + "\n";
//                for (Constraint constraint : constraints) {
//                    s += "\t\t" + constraint;
//                    s += "\n";
//                }
//            }
//        }
//        return s;

        class IntHolder {public int i; IntHolder(int i) { this.i = i; } }
        final IntHolder altsNo = new IntHolder(0);
        F2<String, List<List<Constraint>>, String> f = new F2<String, List<List<Constraint>>, String>() {
            @Override
            public String f(String s, List<List<Constraint>> altConstraint) {
                altsNo.i++;
                s += "Alts no. " + altsNo.i + "\n";
                int altNo = 0;
                for (List<Constraint> constraints : altConstraint) {
                    altNo++;
                    s += "\tAlt no. " + altsNo.i + "->" + altNo + "\n";
                    for (Constraint constraint : constraints) {
                        s += "\t\t" + constraint;
                        s += "\n";
                    }
                }
                return s;
            }

        };
        return altConstraints.foldLeft(f, "");
    }

    private CompilationUnit[] compilationUnits;
    private List<CompilationUnit> processedUnits = new LinkedList<CompilationUnit>();
    private Boolean[] isProcessedUnits;
    private int compilationUnitNo = 0;
    public SymbolTable<Type> symbolTable = new SymbolTable<Type>();
    private HashSet<String> libFunction = TypeDef.libFunctions;
    private ConstraintMaker constraintMaker = new ConstraintMaker();


    public ConstraintElicitor(CompilationUnit[] compilationUnits) {
        this.compilationUnits = compilationUnits;
        isProcessedUnits = new Boolean[compilationUnits.length];
    }


    private void initializeSymbolTable() {
        try {
            // -------------------------------------------------------------
            // Operators:
            // Relational ops
            symbolTable.put(Equality.name, normalOpToBoolTypes);
            symbolTable.put(NotEquality.name, normalOpToBoolTypes);
            symbolTable.put(GreaterThan.name, normalOpToBoolTypes);
            symbolTable.put(GreaterThanEqual.name, normalOpToBoolTypes);

            symbolTable.put(LessThan.name, normalOpToBoolTypes);
            symbolTable.put(LessThanEqual.name, normalOpToBoolTypes);

            // Logical ops
                // Short circuit And and Or
            symbolTable.put(And.name, shortCircuitAndAndOrType);
            symbolTable.put(Or.name, shortCircuitAndAndOrType);
                // Elementwise And and Or
            symbolTable.put(ElementWiseAnd.name, normalOpToBoolTypes);
            symbolTable.put(ElementWiseOr.name, normalOpToBoolTypes);
                // Not
            symbolTable.put(Not.name, notTypes);

            // Math ops
            symbolTable.put(Times.name, timesTypes);
            symbolTable.put(Divide.name, dividesTypes);

            symbolTable.put(DotTimes.name, binaryNormalOpTypes);
            symbolTable.put(DotDivide.name, binaryNormalOpTypes);
            symbolTable.put(DotPower.name, binaryNormalOpTypes);

            symbolTable.put(Plus.name, unaryAndNormalOpTypes);
            symbolTable.put(Minus.name, unaryAndNormalOpTypes);

            symbolTable.put(MatrixConstructor.name, vectorConstructorTypes);
            symbolTable.put(RangeVectorConstructor.name, rangeVectorConstructorTypes);

            symbolTable.put(Transpose.name, transposeTypes);
            symbolTable.put(Power.name, powerTypes);

            // -------------------------------------------------------------
            // Lib functions:

            symbolTable.put("readFormatImage", readFormatImageTypes);
            symbolTable.put("writeFormatImage", writeFormatImageTypes);
            symbolTable.put("readMatrix", readMatrixTypes);
            symbolTable.put("writeMatrix", writeMatrixTypes);
            symbolTable.put("readIntMatrix", readIntMatrixTypes);
            symbolTable.put("writeIntMatrix", writeIntMatrixTypes);
            symbolTable.put("readDoubleMatrix", readDoubleMatrixTypes);
            symbolTable.put("writeDoubleMatrix", writeDoubleMatrixTypes);
            symbolTable.put("dispImage", (Type)null);

            symbolTable.put("logical", logicalTypes);
            symbolTable.put("size", sizeTypes);
            symbolTable.put("disp", dispTypes);
            symbolTable.put("mod", modTypes);
            symbolTable.put("sqrt", sqrtTypes);
            symbolTable.put("randn", randnTypes);
            symbolTable.put("zeros", zerosTypes);
            symbolTable.put("ones", onesTypes);
            symbolTable.put("randperm", randpermTypes);
            symbolTable.put("norm", normTypes);
            symbolTable.put("sort", sortTypes);
            symbolTable.put("abs", absTypes);
            symbolTable.put("union", unionTypes);
            symbolTable.put("pinv", pinvTypes);
            symbolTable.put("length", lengthTypes);
            symbolTable.put("sum", sumTypes);
            symbolTable.put("double", doubleTypes);
            symbolTable.put("tic", ticTypes);
            symbolTable.put("toc", tocTypes);
            symbolTable.put("find", findTypes);
            symbolTable.put("reshape", reshapeTypes);
            symbolTable.put("min", minAndMaxTypes);
            symbolTable.put("max", minAndMaxTypes);

            // -------------------------------------------------------------

        } catch (DuplicateDefExc e) {
            e.printStackTrace(System.out);
        }
    }
    public CompilationUnit[] getProcessedCompilationUnits() {
        CompilationUnit[] compilationUnits = new CompilationUnit[processedUnits.size()];
        return (CompilationUnit[])processedUnits.toArray(compilationUnits);
    }
    public Pair<List<Constraint>, fj.data.List<List<List<Constraint>>>> elicit() {
        // Gather constraints.
        // At first, get and put the name of all functions with a type var
        // in the context.
        // Process the trees in any order.

        symbolTable.startScope();
        initializeSymbolTable();
        for (int i1 = 0, compilationUnitsLength = compilationUnits.length; i1 < compilationUnitsLength; i1++) {
            CompilationUnit compilationUnit = compilationUnits[i1];
            compilationUnitNo = i1;
            if (compilationUnit instanceof Function) {
                final Function fun = (Function) compilationUnit;
                String funName = fun.name.name;
                if (symbolTable.contains(funName)) {
                    isProcessedUnits[i1] = false;
                    continue;
                }
                else {
                    isProcessedUnits[i1] = true;
                    processedUnits.add(compilationUnit);
                }

                DataType[] inputTypes = fun.inputParams.apply(
                    new Fun0<DataType[]>() {
                        public DataType[] apply() {
//                            return new DataType[]{UnitType.instance()};
                            return new DataType[]{};
                        }
                    },
                    new Fun<Id[], DataType[]>() {
                        public DataType[] apply(Id[] inputParams) {
                            DataType[] inputTypes = new DataType[inputParams.length];
                            for (int i = 0; i < inputTypes.length; i++)
                                inputParams[i].type = inputTypes[i] = TypeVar.fresh();
//                                System.out.println(fun.inputParams[i].name + ":" + fun.inputParams[i].type);
                            return inputTypes;
                        }
                    }
                );

                DataType outputType = fun.outputParams.apply(
                    new Fun0<DataType>() {
                        public DataType apply() {
                            return UnitType.instance();
                        }
                    },
                    new Fun<Id[], DataType>() {
                        public DataType apply(Id[] outputParams) {
                            int outputCount = outputParams.length;
                            if (outputCount == 1) {
                                final TypeVar typeVar = TypeVar.fresh();
                                outputParams[0].type = typeVar;
                                return typeVar;
                            } else {
                                DataType[] outputTypes;
                                outputTypes = new DataType[outputCount];
                                for (int i = 0; i < outputTypes.length; i++)
                                    outputParams[i].type = outputTypes[i] = TypeVar.fresh();
                                return new TupleType(outputTypes);
                            }
                        }
                    }
                );

                Type type = new DFunType(inputTypes, outputType);
                try {
                    symbolTable.put(funName, type);
                } catch (DuplicateDefExc e) {
                }
            } else {
                isProcessedUnits[i1] = true;
                processedUnits.add(compilationUnit);
            }
        }
        // Process the script firs to make unification faster.
        for (int i = 0, compilationUnitsLength = compilationUnits.length; i < compilationUnitsLength; i++) {
            if (isProcessedUnits[i]) {
                CompilationUnit compilationUnit = compilationUnits[i];
                if (compilationUnit instanceof Script) {
                    symbolTable.startScope();
                    compilationUnitNo = i;
                    visitDispatch(compilationUnit);
                    symbolTable.endScope();
                }
            }
        }
        for (int i = 0, compilationUnitsLength = compilationUnits.length; i < compilationUnitsLength; i++) {
            if (isProcessedUnits[i]) {
                CompilationUnit compilationUnit = compilationUnits[i];
                if (!(compilationUnit instanceof Script)) {
                    symbolTable.startScope();
                    compilationUnitNo = i;
                    visitDispatch(compilationUnit);
                    symbolTable.endScope();
                }
            }
        }

        Pair<List<Constraint>, fj.data.List<List<List<Constraint>>>> result = constraintMaker.getResult();
//        System.out.println();
//        System.out.println("Constraints:");
//        System.out.println(printConsts(result._1()));
//        System.out.println("AltConstraints:");
//        System.out.println(printAltConsts(result._2()));

        return result;


        // To do later: To generate different versions of a function for different
        // call sites, compute the call graph, perform scc search and topological
        // sorting.
        // compute a type scheme for each function.
    }

    private int atIndexPosition = 0;
    private void enterIndexPosition() {
        atIndexPosition++;
    }
    private void leaveIndexPosition() {
        atIndexPosition--;
    }
    private boolean atIndexPosition() {
        return atIndexPosition != 0;
    }


    private void visitDispatch(Node node) {
        node.accept(this);
    }

    public Object visit(Function function) {
        Option<Id[]> inputs = function.inputParams;
        Option<Id[]> outputs = function.outputParams;

        inputs.apply(
            new Fun0<Object>() {
                public Object apply() {
                    return null;
                }
            },
            new Fun<Id[], Object>() {
                public Object apply(Id[] inputs) {
                    try {
                    for (Id id : inputs)
                        symbolTable.put(id.name, id.type);
                    } catch (DuplicateDefExc e) {}
                    return null;
                }
            }
        );
        outputs.apply(
            new Fun0<Object>() {
                public Object apply() {
                    return null;
                }
            },
            new Fun<Id[], Object>() {
                public Object apply(Id[] outputs) {
                    try {
                        for (Id id : outputs)
                            symbolTable.put(id.name, id.type);
                    } catch (DuplicateDefExc e) {}
                    return null;
                }
            }
        );

        for (int i = 0; i < function.statements.length; i++) {
            Statement statement = function.statements[i];
            statementVisitor.visitDispatch(statement);
        }
        return null;
    }

    public Object visit(Script script) {
        for (int i = 0; i < script.statements.length; i++) {
            Statement statement = script.statements[i];
            statementVisitor.visitDispatch(statement);
        }
        return null;
    }

    private TheStatementVisitor statementVisitor = new TheStatementVisitor();
    private class TheStatementVisitor implements StatementVisitor {
        public Object visitDispatch(Statement statement) {
            return statement.accept(this);
        }

        public Object visit(Assignment assignment) {
            DataType idType = expressionVisitor.visitDispatch(assignment.id);
            DataType rightType = expressionVisitor.visitDispatch(assignment.right);
            constraintMaker.addConstraint(idType, rightType,
                    compilationUnitNo, assignment,
                    "The two sides of the assignment");

            // if the left hand side is an id
            //    if the result of the call is a TupleType
            //       if the types are the same
            //          A horizontal vector of the type is assigned to the id
            //          This part, we did in the constraintMaker for the result type of calls.
            //       else
            //          Only the first element of the result of the call is assigned to the id
            //          This is ugly and we do not support.
            //    else
            //       Just a single value assignment

            return null;
        }

        public Object visit(ArrayAssignment arrayAssignment) {
            String id = arrayAssignment.array.name;
            Expression[] indices = arrayAssignment.indices;
            Expression rightExp = arrayAssignment.right;

            DataType rightType = expressionVisitor.visitDispatch(rightExp);

            Type idType = null;
            try {
                idType = symbolTable.get(id);
            } catch (SymbolNotFoundException e) {
                throw new SemanticErrorException(compilationUnitNo, arrayAssignment,
                        "Assignment to unknown array " + id);
            }

            DataType idDataType = (DataType) idType;
            arrayAssignment.array.type = idDataType;

            if ((indices.length == 1) && (indices[0] instanceof Colon)) {
                // Handle the case matrix(:) separately.
                TypeVar elemType = TypeVar.fresh();
                TypeVar n = TypeVar.fresh();
                TypeVar m = TypeVar.fresh();
                MatrixType matrixType = new MatrixType(elemType, n, m);
                constraintMaker.addConstraint(
                        idDataType, matrixType,
                        compilationUnitNo, arrayAssignment,
                        "Indexed array");

                constraintMaker.newAlts();
                constraintMaker.newAlt();

                constraintMaker.addConstraint(
                        rightType, elemType,
                        compilationUnitNo, arrayAssignment,
                        "Array assignment right hand side");
                constraintMaker.finishAlt();
                constraintMaker.newAlt();

                constraintMaker.addConstraint(
                        rightType, matrixType,
                        compilationUnitNo, arrayAssignment,
                        "Array assignment right hand side");

                constraintMaker.finishAlt();
                constraintMaker.addAlts();
                return null;

            } else {

                DataType[] argTypes = new DataType[indices.length + 2];
                argTypes[0] = idDataType;
                argTypes[argTypes.length - 1] = rightType;
                Option<Value>[] argValues = (Option<Value>[]) Array.newInstance(Option.class, indices.length + 2);
                argValues[0] = None.instance();
                argValues[argTypes.length - 1] = None.instance();

                enterIndexPosition();
                for (int i = 1; i < argTypes.length - 1; i++) {
                    Expression operand = indices[i - 1];
                    argTypes[i] = expressionVisitor.visitDispatch(operand);
                    argValues[i] = expressionValueVisitor.visitDispatch(operand);
                }
                leaveIndexPosition();

                IntersectionFunType theFunTypes = (IntersectionFunType)arrayAssignmentTypes.instantiate();
                List<SingleFunType> funTypes = theFunTypes.types;

                return constraintMaker.addConstrainsFor(
                        funTypes, argTypes, argValues,
                        new String[] {"Indexed array"},
                        new String[] {"Assignment right hand side"},
                        compilationUnitNo, arrayAssignment, id);
            }
        }

        public Object visit(CallSt callSt) {
            expressionVisitor.visitDispatch(callSt.call);
            return null;
        }

        public Object visit(CallAndMultiAssignment callAndMultiAssignment) {
            Id[] ids = callAndMultiAssignment.ids;
            DataType[] idTypes = new DataType[ids.length];
            for (int i = 0; i < ids.length; i++)
                idTypes[i] = expressionVisitor.visitDispatch(ids[i]);

            Call call = callAndMultiAssignment.call;
            DataType callType = expressionVisitor.visitDispatch(call);

            if (ids.length == 1) {
                constraintMaker.addConstraint(idTypes[0], callType,
                        compilationUnitNo, call,
                        "The id on the left hand side and the return value of the call on the right hand side");
            } else {
                constraintMaker.newAlts();
                constraintMaker.newAlt();
                DataType[] elemTypes = new DataType[ids.length];
                for (int i = 0; i < elemTypes.length; i++)
                    elemTypes[i] = TypeVar.fresh();
                TupleType tupleType = new TupleType(elemTypes);
                constraintMaker.addConstraint(callType, tupleType,
                        compilationUnitNo, call,
                        "The return value of the call");
                for (int i = 0; i < elemTypes.length; i++) {
                    constraintMaker.addConstraint(idTypes[i], elemTypes[i],
                            compilationUnitNo, callAndMultiAssignment,
                            "The " + plusOneOrdinal(i) + " entry of the two sides of the assignment");
                }
                constraintMaker.finishAlt();
                constraintMaker.newAlt();
                TypeVar elemType = TypeVar.fresh();
                HVectorType hVectorType = new HVectorType(elemType, ids.length);
                constraintMaker.addConstraint(callType, hVectorType,
                        compilationUnitNo, call,
                        "The return value of the call");
                for (int i = 0; i < elemTypes.length; i++) {
                    constraintMaker.addConstraint(idTypes[i], elemType,
                            compilationUnitNo, callAndMultiAssignment,
                            "The " + plusOneOrdinal(i) + " entry of the two sides of the assignment");
                }
                constraintMaker.finishAlt();

                constraintMaker.addAlts();
            }
            return null;
        }

        public Object visit(If theIf) {
            DataType conditionType = expressionVisitor.visitDispatch(theIf.condition);
            constraintMaker.addAltConstrainsFor(conditionType, TypeDef.boolAndNumericTypes,
                    compilationUnitNo, theIf.condition,
                    "The condition expression");

            statementVisitor.visitDispatch(theIf.ifStatement);

            Option<If.ElseIf[]> elseifsOption = theIf.elseifs;
            elseifsOption.apply(
                new Fun0<Object>() {
                    public Object apply() {
                        return null;
                    }
                },
                new Fun<If.ElseIf[], Object>() {
                    public Object apply(If.ElseIf[] input) {
                        for (If.ElseIf elseIf : input) {
                            Expression condition = elseIf.condition;
                            DataType conditionType = expressionVisitor.visitDispatch(condition);
                            constraintMaker.addAltConstrainsFor(conditionType, TypeDef.boolAndNumericTypes,
                                    compilationUnitNo, elseIf.condition,
                                    "The condition expression");
                            Statement statement = elseIf.statement;
                            statementVisitor.visitDispatch(statement);
                        }
                        return null;
                    }
                }
            );

            Option<Statement> elseBlockOption = theIf.elseStatementOption;
            if (elseBlockOption.isPresent()) {
                Statement elseBlock = ((Some<Statement>)elseBlockOption).get();
                statementVisitor.visitDispatch(elseBlock);
            }
            return null;
        }


        public Object visit(For theFor) {
            DataType idType = expressionVisitor.visitDispatch(theFor.id);
            DataType rangeType = expressionVisitor.visitDispatch(theFor.range);
            constraintMaker.addConstraint(idType, IntType.instance(),
                    compilationUnitNo, theFor.id,
                    "Index variable");
            TypeVar var = TypeVar.fresh();
            constraintMaker.addConstraint(rangeType, new HVectorType(IntType.instance(), var),
                    compilationUnitNo, theFor.range,
                    "Range expression of the for statement");
            return statementVisitor.visitDispatch(theFor.block);
        }

        public Object visit(While theWhile) {
            DataType conditonType = expressionVisitor.visitDispatch(theWhile.condition);
            constraintMaker.addAltConstrainsFor(conditonType, TypeDef.boolAndNumericTypes,
                    compilationUnitNo, theWhile.condition,
                    "The condition expression");
            statementVisitor.visitDispatch(theWhile.statement);
            return null;
        }

        public Object visit(Switch theSwitch) {
            DataType switchExpType = expressionVisitor.visitDispatch(theSwitch.selector);
            Switch.Case[] cases = theSwitch.cases;
            for (Switch.Case theCase : cases) {
                DataType caseExpType = expressionVisitor.visitDispatch(theCase.guard);
                constraintMaker.addConstraint(switchExpType, caseExpType,
                        compilationUnitNo, theCase.guard,
                        "The switch expression and the case expression");

                for (int i = 0; i < theCase.statements.length; i++) {
                    Statement statement = theCase.statements[i];
                    statementVisitor.visitDispatch(statement);
                }
            }

            theSwitch.defaultStatements.apply(
                new Fun0<Object>() {
                    public Object apply() {
                        return null;
                    }
                },
                new Fun<Statement[], Object>() {
                    public Object apply(Statement[] defaultStatements) {
                        for (int i = 0; i < defaultStatements.length; i++) {
                            Statement defaultStatement = defaultStatements[i];
                            statementVisitor.visitDispatch(defaultStatement);
                        }
                        return null;
                    }
                }
            );

            return null;
        }

        public Object visit(Break theBreak) {
            return null;
        }

        public Object visit(Return theReturn) {
            return null;
        }

        public Object visit(Block block) {
            Statement[] statements = block.statements;
            for (Statement statement : statements)
                statementVisitor.visitDispatch(statement);
            return null;
        }

        public Object visit(Print print) {
            return expressionVisitor.visitDispatch(print.arg);
        }

        public Object visit(Println println) {
            return expressionVisitor.visitDispatch(println.arg);
        }
    }


    // ----------------------------------------------------------------------------------------
    // Expression

    private TheExpressionVisitor expressionVisitor = new TheExpressionVisitor();
    private class TheExpressionVisitor implements ExpressionVisitor<DataType> {
        public DataType visitDispatch(Expression expression) {
            final DataType type = expression.accept(this);
            expression.type = type;
            return type;
        }

        public DataType visit(Literal literal) {
            return literalVisitor.visitDispatch(literal);
        }

        TheLiteralVisitor literalVisitor = new TheLiteralVisitor();
        private class TheLiteralVisitor implements LiteralVisitor<DataType> {
            public DataType visitDispatch(Literal literal) {
                return literal.accept(this);
            }
            public DataType visit(IntLiteral intLiteral) {
                TypeVar var = TypeVar.fresh();

                constraintMaker.newAlts();
                constraintMaker.newAlt();
                constraintMaker.addConstraint(var, IntType.instance(),
                                                compilationUnitNo, intLiteral, "Integer literal typed as Int");
                constraintMaker.finishAlt();
//                constraintMaker.newAlt();
//                constraintMaker.addConstraint(var, DoubleType.instance(),
//                                                compilationUnitNo, intLiteral, "Integer literal typed as Double");
//                constraintMaker.finishAlt();
                constraintMaker.addAlts();

                return var;
//                return IntType.instance();
            }

            public DataType visit(DoubleLiteral doubleLiteral) {
                return DoubleType.instance();
            }

            public DataType visit(StringLiteral stringLiteral) {
                return StringType.instance();
            }

            public DataType visit(Colon colon) {
                if (!atIndexPosition())
                    throw new SemanticErrorException(compilationUnitNo, colon,
                            "\":\" should be used only at array index position");

                RangeVectorConstructor c = new RangeVectorConstructor(
                    new IntLiteral("1"),
                    End.instance()
                );
                c.setLoc(colon);
                return expressionVisitor.visitDispatch(c);
            }

            public DataType visit(End end) {
                // Should check that end is at an array index position.
                if (!atIndexPosition()) {
//                    throw new RuntimeException();
                    throw new SemanticErrorException(compilationUnitNo, end,
                            "\"end\" should be used only at array index position");
                }
                return IntType.instance();
            }
        }

        public DataType visit(Op op) {

//            if (op instanceof And) {
//                And and = (And) op;
//                System.out.println("And");
//                System.out.println(and.operand1);
//                System.out.println(and.operand2);
//                try {
//                    System.out.println(symbolTable.get(op.getName()));
//                } catch (SymbolNotFoundException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
//            }

            if (op instanceof MatrixConstructor) {
                MatrixConstructor matrixConstructor = (MatrixConstructor) op;
                return processMatrixConstructor(matrixConstructor);
            }

            Type opType;
            try {
                opType = symbolTable.get(op.getName());
            } catch (SymbolNotFoundException e) {
                throw new SemanticErrorException(compilationUnitNo, op,
                        "Unknown operation/function/matrix " + op.getName());
            }
//            System.out.println(op.getName());
//            System.out.println(opType);
            if (!(opType instanceof FunType))
                return processArrayAccess(op);


            Expression[] operands = op.getOperands();
            DataType[] argTypes = new DataType[operands.length];
            Option<Value>[] argValues = (Option<Value>[]) Array.newInstance(Option.class, operands.length);

            for (int i = 0; i < operands.length; i++) {
                Expression operand = operands[i];
                argTypes[i] = expressionVisitor.visitDispatch(operand);
                argValues[i] = expressionValueVisitor.visitDispatch(operand);
            }


            List<SingleFunType> funTypes;
            FunType funType = (FunType) opType;
            if (funType instanceof SingleFunType) {
                SingleFunType sFunType = (SingleFunType) funType;

                funTypes = new LinkedList<SingleFunType>();
                funTypes.add(sFunType);
            } else {
                IntersectionFunType iFunType = (IntersectionFunType) funType;
                funTypes = iFunType.types;
            }

            TypeVar typeVar = constraintMaker.addConstrainsFor(
                    funTypes, argTypes, argValues,
                    compilationUnitNo, op, op.getLexeme());

            if ((op instanceof Call) || (op instanceof CallOrArrayAccess)) {
                if (op instanceof Call) {
                    Call call = (Call) op;
                    if (libFunction.contains(op.getName()))
                        call.setLib();
                    call.origType = constraintMaker.origType;
//                    System.out.println("origType = " + call.origType);
                } else {
                    CallOrArrayAccess call = (CallOrArrayAccess) op;
                    if (libFunction.contains(op.getName()))
                        call.setLib();
                    call.origType = constraintMaker.origType;
//                    System.out.println("origType = " + call.origType);
                }
            }

            return typeVar;
        }

        private DataType processMatrixConstructor(MatrixConstructor matrixConstructor) {
            Expression[][] elements = matrixConstructor.elements;
            int rowCount = elements.length;
            int columnCount;
            if (rowCount == 0)
                columnCount = 0;
            else
                columnCount = elements[0].length;

            for (int i = 0; i < elements.length; i++) {
                if (columnCount != elements[i].length)
                    throw new SemanticErrorException(compilationUnitNo, matrixConstructor,
                    "Number of elements in the rows should be the same.");
            }


            Type opType;
            try {
                opType = symbolTable.get(matrixConstructor.getName());
            } catch (SymbolNotFoundException e) { throw new RuntimeException(); }

            IntersectionFunType intersectionFunType = (IntersectionFunType) opType;
            List<SingleFunType> singleFunTypes = intersectionFunType.types;


            DataType[] elementTypes = new DataType[rowCount * columnCount];
            for (int i = 0; i < rowCount; i++)
                for (int j = 0; j < columnCount; j++)
                    elementTypes[i*columnCount + j] = expressionVisitor.visitDispatch(elements[i][j]);

            List<SingleFunType> funTypes = new LinkedList<SingleFunType>();
            for (SingleFunType singleFunType : singleFunTypes) {
                DFunType funType;
                if (singleFunType instanceof VarArgFunType) {
                    VarArgFunType varArgFunType = (VarArgFunType) singleFunType;
                    DataType elemType = varArgFunType.elemType;
                    DataType resultType = varArgFunType.computer.computeOutputType(rowCount, columnCount);
                    DataType[] inputs = new DataType[rowCount * columnCount];
                    for (int i = 0; i < rowCount; i++)
                        for (int j = 0; j < columnCount; j++)
                            inputs[i*columnCount + j] = elemType;
                    funType = new DFunType(inputs, resultType);
                } else if (singleFunType instanceof DFunType) {
                    funType = (DFunType) singleFunType;
                } else {
                    throw new RuntimeException();
                }
                funTypes.add(funType);
            }

            Option<Value>[] argValues = (Option<Value>[]) Array.newInstance(Option.class, elementTypes.length);
            for (int i = 0; i < argValues.length; i++)
                argValues[i] = None.instance();


            TypeVar resultVar = constraintMaker.addConstrainsFor(
                funTypes, elementTypes, argValues,
                compilationUnitNo, matrixConstructor, matrixConstructor.getLexeme()
            );
            return resultVar;

/*
            DataType[][] elementTypes = new DataType[rowCount][columnCount];
            for (int i = 0; i < elementTypes.length; i++)
                for (int j = 0; j < elementTypes[i].length; j++)
                    elementTypes[i][j] = expressionVisitor.visitDispatch(elements[i][j]);

            final TypeVar resultVar = TypeVar.fresh();
            constraintMaker.newAlts();
            for (SingleFunType singleFunType : singleFunTypes) {
                constraintMaker.newAlt();
                VarArgFunType varArgFunType = (VarArgFunType) singleFunType;
                DataType elemType = varArgFunType.elemType;

                DataType resultType = varArgFunType.computer.computeOutputType(rowCount, columnCount);

                for (int i = 0; i < elementTypes.length; i++) {
                    for (int j = 0; j < elementTypes[i].length; j++) {
                        constraintMaker.addConstraint(
                            elementTypes[i][j], elemType,
                            compilationUnitNo, matrixConstructor,
                            "All elements should be of type " + elemType + ". Element (" + (i+1) + ", " + (j+1) + ")");
                    }
                }
                constraintMaker.addConstraint(
                        resultVar, resultType,
                        compilationUnitNo, matrixConstructor,
                        "Result of matrix constructor");

                constraintMaker.finishAlt();
            }
            constraintMaker.addAlts();

            return resultVar;
*/
        }

        private DataType processArrayAccess(Op op) {
            // Could have a pass to remove CallOrArrayAccess here.
            if (!((op instanceof ArrayAccess) || (op instanceof CallOrArrayAccess)))
                throw new RuntimeException();

            String id = op.getName();
            Expression[] arrayArgs = op.getOperands();

            Type idType;
            try {
                idType = symbolTable.get(id);
            } catch (SymbolNotFoundException e) {
                throw new SemanticErrorException(compilationUnitNo, op,
                        "Indexing an unknown array " + id);
            }

            DataType idDataType = (DataType) idType;

            if (op instanceof ArrayAccess) {
                ArrayAccess arrayAccess = (ArrayAccess) op;
                arrayAccess.array.type = idDataType;
            } else {
                CallOrArrayAccess callOrArrayAccess = (CallOrArrayAccess) op;
                callOrArrayAccess.id.type = idDataType;
            }

            if ((arrayArgs.length == 1) && (arrayArgs[0] instanceof Colon)) {
                // Handle the case matrix(:) separately.
                TypeVar elemType = TypeVar.fresh();
                TypeVar n = TypeVar.fresh();
                TypeVar m = TypeVar.fresh();
                MatrixType matrixType = new MatrixType(elemType, n, m);

                constraintMaker.addConstraint(
                        idDataType, matrixType,
                        compilationUnitNo, op,
                        "Indexed array");

                TypeVar rn = TypeVar.fresh(); // Can do more to find the length of the result statically (?)
                arrayArgs[0].type = new VVectorType(IntType.instance(), rn);
                return new VVectorType(elemType, rn);
            } else if ((arrayArgs.length == 1) && (arrayArgs[0] instanceof End)) {
                // Handle the case matrix(end) separately.
                arrayArgs[0].type = IntType.instance();
                TypeVar elemType = TypeVar.fresh();
                TypeVar n = TypeVar.fresh();
                TypeVar m = TypeVar.fresh();
                MatrixType matrixType = new MatrixType(elemType, n, m);

                constraintMaker.addConstraint(
                        idDataType, matrixType,
                        compilationUnitNo, op,
                        "Indexed array");
                return elemType;
            } else {
                DataType[] argTypes = new DataType[arrayArgs.length + 1];
                argTypes[0] = idDataType;
                Option<Value>[] argValues = (Option<Value>[]) Array.newInstance(Option.class, arrayArgs.length + 1);
                argValues[0] = None.instance();

//                System.out.println("In processArrayAccess");
//                System.out.println(op.getName());
//                System.out.println(op.getClass());
//                System.out.println("op = " + op);


                enterIndexPosition();
                for (int i = 1; i < argTypes.length; i++) {
                    Expression operand = arrayArgs[i - 1];
//                    System.out.println("operand = " + operand);
                    argTypes[i] = expressionVisitor.visitDispatch(operand);
                    argValues[i] = expressionValueVisitor.visitDispatch(operand);
                }
                leaveIndexPosition();

                IntersectionFunType theFunTypes = (IntersectionFunType)arrayAccessTypes.instantiate();
                List<SingleFunType> funTypes = theFunTypes.types;

                return constraintMaker.addConstrainsFor(
                        funTypes, argTypes, argValues,
                        new String[] {"Indexed array"},
                        compilationUnitNo, op, op.getName());
            }
        }


        public DataType visit(Id id) {
            DataType type;
            try {
                type = (DataType)symbolTable.get(id.name);
            } catch (SymbolNotFoundException e) {
                try {
                    type = TypeVar.fresh();
                    symbolTable.put(id.name, type);
//                    System.out.println(id.name + ":" + id.type);
                } catch (DuplicateDefExc e1) {
                    throw new RuntimeException("");
                }
            }
            id.type = type;
            return type;
        }
    }

    TheExpressionValueVisitor expressionValueVisitor = new TheExpressionValueVisitor();
    private class TheExpressionValueVisitor implements ExpressionVisitor<Option<Value>> {
        public Option<Value> visitDispatch(Expression expression) {
            return expression.accept(this);
        }

        public Option<Value> visit(Literal literal) {
            return literalValueVisitor.visitDispatch(literal);
        }

        TheLiteralValueVisitor literalValueVisitor = new TheLiteralValueVisitor();
        private class TheLiteralValueVisitor implements LiteralVisitor<Option<Value>> {
            public Option<Value> visitDispatch(Literal literal) {
                return literal.accept(this);
            }

            public Option<Value> visit(IntLiteral intLiteral) {
                int i = Integer.parseInt(intLiteral.lexeme);
                return new Some<Value>(new Value(i));
            }

            public Option<Value> visit(DoubleLiteral doubleLiteral) {
                double d = Double.parseDouble(doubleLiteral.lexeme);
                return new Some<Value>(new Value(d));
            }

            public Option<Value> visit(StringLiteral stringLiteral) {
                return new Some<Value>(new Value(stringLiteral.lexeme));
            }

            public Option<Value> visit(Colon colon) {
                // Maybe we can improve this later.
                return None.instance();
            }

            public Option<Value> visit(End end) {
                // Maybe we can improve this later.
                return None.instance();
            }
        }

        public Option<Value> visit(Op op) {
            return None.instance();
        }

        public Option<Value> visit(Id id) {
            return None.instance();
        }
    }

}
