package jsrc.matlab.converttox10;

import jsrc.matlab.ast.tree.declaration.CompilationUnit;
import jsrc.matlab.ast.tree.declaration.type.UnitType;
import jsrc.matlab.ast.tree.expression.literal.Literal;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.tree.expression.literal.Colon;
import jsrc.matlab.ast.tree.expression.literal.End;
import jsrc.matlab.ast.tree.expression.op.application.Call;
import jsrc.matlab.ast.tree.expression.op.constructors.MatrixConstructor;
import jsrc.matlab.ast.tree.expression.op.constructors.RangeVectorConstructor;
import jsrc.matlab.ast.visitor.SVisitor;

import jsrc.matlab.typeinference.type.*;
import jsrc.x10.ast.tree.expression.*;
import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.expression.literal.*;
import jsrc.x10.ast.tree.expression.op.logical.*;
import jsrc.x10.ast.tree.expression.op.relational.*;
import jsrc.x10.ast.tree.expression.x10.derived.ArrayAccess;
import jsrc.x10.ast.tree.expression.x10.derived.ArrayUpdate;
import jsrc.x10.ast.tree.statement.Break;
import jsrc.x10.ast.tree.statement.derived.Print;
import jsrc.x10.ast.tree.statement.derived.Println;
import jsrc.x10.ast.tree.statement.x10.PointFor;
import jsrc.x10.ast.tree.type.DoubleType;
import jsrc.x10.ast.tree.type.IntType;
import jsrc.x10.ast.tree.type.MatrixType;
import jsrc.x10.ast.tree.type.ScalarType;
import jsrc.x10.ast.tree.type.StringType;
import jsrc.x10.ast.tree.type.Type;
import lesani.collection.Triple;
import lesani.collection.func.Fun0;
import lesani.compiler.typing.SymbolTable;
import jsrc.x10.ast.tree.Node;
import jsrc.x10.ast.tree.declaration.*;
import jsrc.x10.ast.tree.expression.op.math.*;
import jsrc.x10.ast.tree.statement.*;
import jsrc.x10.ast.tree.statement.Assignment;
import jsrc.x10.ast.tree.statement.Block;
import jsrc.x10.ast.tree.statement.If;
import jsrc.x10.ast.tree.statement.Statement;
import jsrc.x10.ast.tree.type.*;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.typing.exception.DuplicateDefExc;
import lesani.file.Address;
import lesani.collection.func.Fun;

import java.util.*;

public class ASTConverter implements SVisitor<Node> {

    public static Node[] convert(CompilationUnit[] compilationUnits) {
        return new ASTConverter(compilationUnits).convert();
    }

    private jsrc.matlab.ast.tree.declaration.CompilationUnit[] compilationUnits;
    private SymbolTable<Type> symbolTable = new SymbolTable<Type>();


    public ASTConverter(CompilationUnit[] compilationUnits) {
        this.compilationUnits = compilationUnits;
    }

    public Node[] convert() {
        Node[] x10ASTs = new Node[compilationUnits.length];
        for (int i = 0; i < compilationUnits.length; i++) {
             CompilationUnit compilationUnit = compilationUnits[i];
             x10ASTs[i] = visitDispatch(compilationUnit);
        }
        return x10ASTs;
    }
/*
    public static Node[] convert(TypedCompilationUnits typedCompilationUnits) {
        return new ASTConverter(typedCompilationUnits).convert();
    }

    private jsrc.matlab.ast.tree.declaration.CompilationUnit[] compilationUnits;
    private SymbolTable<jsrc.matlab.typeinference.type.Type> symbolTable = new SymbolTable<jsrc.matlab.typeinference.type.Type>();


    public ASTConverter(TypedCompilationUnits typedCompilationUnits) {
        jsrc.matlab.ast.tree.declaration.CompilationUnit[] compilationUnits = typedCompilationUnits.compilationUnits;
        SymbolTable<jsrc.matlab.typeinference.type.Type> symbolTable = typedCompilationUnits.symbolTable;
        this.symbolTable = symbolTable;
        this.compilationUnits = compilationUnits;
    }

    public Node[] convert() {
        Node[] x10ASTs = new Node[compilationUnits.length];
        for (int i = 0; i < compilationUnits.length; i++) {
             CompilationUnit compilationUnit = compilationUnits[i];
             x10ASTs[i] = visitDispatch(compilationUnit);
        }
        return x10ASTs;
    }
*/

    private Node visitDispatch(jsrc.matlab.ast.tree.Node node) {
        return node.accept(this);
    }

    private VarDeclOrSt returnSt; // As we do not have nested function, this does not need to be a stack;
    private Stack<String> arrayName = new Stack<String>();
    private Stack<jsrc.matlab.ast.tree.declaration.type.MatrixType> arrayType = new Stack<jsrc.matlab.ast.tree.declaration.type.MatrixType>();
    private Stack<Integer> indexNum = new Stack<Integer>();
    private Stack<Boolean> linearIndexing = new Stack<Boolean>();


    public Node visit(jsrc.matlab.ast.tree.declaration.Function function) {
        symbolTable.startScope();

        Triple<
            Type,
            VarDeclOrSt[],
                VarDeclOrSt
        > outputPair = function.outputParams.apply(
            new Fun0<Triple<Type, VarDeclOrSt[], VarDeclOrSt>>() {
                public Triple<Type, VarDeclOrSt[], VarDeclOrSt> apply() {
                    return new Triple<
                                Type,
                                VarDeclOrSt[],
                            VarDeclOrSt
                            >(
                                VoidType.instance(),
                                new VarDeclOrSt[0],
                                VoidReturn.instance()
                            );
                }
            },
            new Fun<jsrc.matlab.ast.tree.expression.Id[], Triple<Type, VarDeclOrSt[], VarDeclOrSt>>() {
                public Triple<Type, VarDeclOrSt[], VarDeclOrSt> apply(jsrc.matlab.ast.tree.expression.Id[] outputParams) {
                    if (outputParams.length == 1) {

                        jsrc.matlab.ast.tree.expression.Id outId = outputParams[0];
                        String outputName = outId.name;
                        DataType outputType = outId.type;
                        Type xOutputType = typeVisitor.visitDispatch(outputType);
                        NonVoidType xnvOutputType = (NonVoidType) xOutputType;

                        VarDeclOrSt[] statements = new VarDeclOrSt[] {
                            new VarDecl(xnvOutputType, outputName)
                        };
                        try {
                            symbolTable.put(outputName, xnvOutputType);
                        } catch (DuplicateDefExc e) {e.printStackTrace(System.out);}

                        return new Triple<
                                    Type,
                                    VarDeclOrSt[],
                                VarDeclOrSt
                                >(
                                    xnvOutputType,
                                    statements,
                                    new ValueReturn(outputName)
                                );
                    } else {
                        Type[] xOutputTypes = new Type[outputParams.length];
                        VarDeclOrSt[] varDecls = new VarDeclOrSt[outputParams.length];
                        String[] outputNames = new String[outputParams.length];
                        for (int i = 0; i < xOutputTypes.length; i++) {
                            String outputName = outputParams[i].name;
                            DataType outputType = outputParams[i].type;
                            outputNames[i] = outputName;
                            Type xOutputType = typeVisitor.visitDispatch(outputType);
                            xOutputTypes[i] = xOutputType;
                            NonVoidType xnvOutputType = (NonVoidType) xOutputType;
                            varDecls[i] = new VarDecl(xnvOutputType, outputName);
                            try {
                                symbolTable.put(outputName, xnvOutputType);
                            } catch (DuplicateDefExc e) {e.printStackTrace(System.out);}

                        }
                        Type outputType = new DefinedType("Tuple" + xOutputTypes.length, xOutputTypes);
                        VarDeclOrSt returnSt = new ValueReturn(new New(outputType, outputNames));

                        return new Triple<
                                    Type,
                                    VarDeclOrSt[],
                                VarDeclOrSt
                                >(
                                    outputType,
                                    varDecls,
                                    returnSt
                                );
                    }
                }
            }
        );

        Type xOutputType = outputPair._1();
        VarDeclOrSt[] varDecls = outputPair._2();
        returnSt = outputPair._3();

        String xName = function.name.name;
        
        Option<FormalParam[]> xInputParams = function.inputParams.apply(
            new Fun0<Option<FormalParam[]>>() {
                public Option<FormalParam[]> apply() {
                    return None.instance();
                }
            },
            new Fun<jsrc.matlab.ast.tree.expression.Id[], Option<FormalParam[]>>() {
                public Option<FormalParam[]> apply(jsrc.matlab.ast.tree.expression.Id[] inputParams) {
                    FormalParam[] xInputParams = new FormalParam[inputParams.length];

                    for (int i = 0; i < inputParams.length; i++) {
                        jsrc.matlab.ast.tree.expression.Id inputParam = inputParams[i];
                        String xInputParamName = inputParam.name;

                        final DataType inputParamType = inputParam.type;

                        Type xInputParamType = typeVisitor.visitDispatch(inputParamType);
                        NonVoidType xnvInputParamType = (NonVoidType) xInputParamType;

                        xInputParams[i] = new FormalParam(xnvInputParamType, xInputParamName);

                        try {
                            symbolTable.put(xInputParamName, xnvInputParamType);
                        } catch (DuplicateDefExc e) {e.printStackTrace(System.out);}
                    }
                    return new Some<FormalParam[]>(xInputParams);
                }
            }
        );

        jsrc.matlab.ast.tree.statement.Statement[] statements = function.statements;
        List<VarDeclOrSt> xVarDeclOrStsList = new LinkedList<VarDeclOrSt>();

        for (jsrc.matlab.ast.tree.statement.Statement statement : statements) {
            VarDeclOrSt[] xIterVarDeclOrSts = statementVisitor.visitDispatch(statement);
            xVarDeclOrStsList.addAll(Arrays.asList(xIterVarDeclOrSts));
        }

        int xBlockStsCount =
                (statements[statements.length - 1] instanceof jsrc.matlab.ast.tree.statement.Return) ?
                varDecls.length + xVarDeclOrStsList.size() :
                varDecls.length + xVarDeclOrStsList.size() + 1;

        //System.out.println((statements[statements.length - 1] instanceof jsrc.matlab.ast.tree.statement.Return));
        //System.out.println(xBlockStsCount);
        //System.out.println(varDecls.length + xBlockStsList.size() + 1);

        VarDeclOrSt[] xVarDeclOrSts = new VarDeclOrSt[xBlockStsCount];

        System.arraycopy(varDecls, 0, xVarDeclOrSts, 0, varDecls.length);

        int i = 0;
        for (VarDeclOrSt statement : xVarDeclOrStsList) {
            xVarDeclOrSts[varDecls.length + i] = statement;
            i++;
        }

        //System.out.println((statements[statements.length - 1] instanceof jsrc.matlab.ast.tree.statement.Return));
        if (!(statements[statements.length - 1] instanceof jsrc.matlab.ast.tree.statement.Return)) {
            xVarDeclOrSts[xBlockStsCount - 1] = returnSt;
            //System.out.println(returnSt);
        }
        //for (int j = 0; j < xBlockSts.length; j++) {
        //    BlockSt xBlockSt = xBlockSts[j];
        //    System.out.println(xBlockSt);
        //}

        Block xBlock = new Block(xVarDeclOrSts);

        Method method = new Method(
            Visibility.PUBLIC, true, false, xOutputType, xName, xInputParams, xBlock);

        String capName = Address.capName(xName);

        Id className = new Id(capName);

        ClassDecl classDecl = new ClassDecl(Visibility.PUBLIC, className, new Method[]{method});

        symbolTable.endScope();
        return new File (new ClassDecl[] {classDecl});
    }

    public Node visit(jsrc.matlab.ast.tree.declaration.Script script) {
        symbolTable.startScope();

        returnSt = VoidReturn.instance();

        VoidType xOutputType = VoidType.instance();

        FormalParam[] xInputParams = new FormalParam[] {
            new FormalParam(new LocalArrayType(StringType.instance()), "args")
//            new FormalParam(new ArrayType(StringType.instance()), "args")
        };

        String xName = "main";

        jsrc.matlab.ast.tree.statement.Statement[] statements = script.statements;
        List<VarDeclOrSt> statementList = new LinkedList<VarDeclOrSt>();
        for (jsrc.matlab.ast.tree.statement.Statement statement : statements) {
            VarDeclOrSt[] xIterVarDeclOrSts = statementVisitor.visitDispatch(statement);
            statementList.addAll(Arrays.asList(xIterVarDeclOrSts));
        }

        VarDeclOrSt[] xVarDeclOrSts = new VarDeclOrSt[statementList.size()];

        int i = 0;
        for (VarDeclOrSt statement : statementList) {
            xVarDeclOrSts[i] = statement;
            i++;
        }

        Block xBlock = new Block(xVarDeclOrSts);

        Method method = new Method(
            Visibility.PUBLIC, true, false, xOutputType, xName, xInputParams, xBlock);

        String capName = Address.capName(xName);

        Id className = new Id(capName);

        ClassDecl classDecl = new ClassDecl(Visibility.PUBLIC, className, new Method[]{method});

        symbolTable.endScope();

        return new File (new ClassDecl[] {classDecl});
    }

    private TheBlockStVisitor statementVisitor = new TheBlockStVisitor();
    private class TheBlockStVisitor implements StatementVisitor<VarDeclOrSt[]> {
        public VarDeclOrSt[] visitDispatch(jsrc.matlab.ast.tree.statement.Statement statement) {
            return statement.accept(this);
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.Assignment assignment) {
            String varName = assignment.id.name;
            DataType idType = assignment.id.type;

//            jsrc.matlab.ast.tree.expression.Expression right = assignment.right;
//            DataType rightType = assignment.right.type;
//            Class rightClass = assignment.right.getClass();

//            System.out.println("rightClass = " + rightClass);
//            System.out.println("idType = " + idType);
//            System.out.println("idTypeClass = " + idType.getClass());
//            System.out.println("rightType = " + rightType);
//            System.out.println("rightTypeClass = " + rightType.getClass());

//            (rightType instanceof ConstraintMaker.ConvertedHVectorType)

            Expression rightExp = expressionVisitor.visitDispatch(assignment.right);

            if ((assignment.right instanceof jsrc.matlab.ast.tree.expression.Id) &&
                (idType instanceof jsrc.matlab.ast.tree.declaration.type.MatrixType))
                rightExp = new MethodCall(rightExp, "clone", new Expression[]{});

            VarDeclOrSt statement = makeVarDeclOrAssignment(varName, idType, rightExp);
            return singleToArraySt(statement);
        }


        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.CallAndMultiAssignment callAndMultiAssignment) {
            jsrc.matlab.ast.tree.expression.Id[] ids = callAndMultiAssignment.ids;
            jsrc.matlab.ast.tree.expression.op.application.Call call = callAndMultiAssignment.call;
            Expression methodCall = expressionVisitor.visitDispatch(call);

            if (ids.length == 1) {
                jsrc.matlab.ast.tree.expression.Id id = ids[0];
                return singleToArraySt(makeVarDeclOrAssignment(id.name, call.type, methodCall));
            }


            jsrc.matlab.typeinference.type.DataType type = call.type;

            // Can be a TypeTuple or HVectorType
            TupleType tupleType;
            DataType[] elementTypes;
            if (type instanceof jsrc.matlab.typeinference.type.TupleType) {
                jsrc.matlab.typeinference.type.TupleType theTupleType =
                        (jsrc.matlab.typeinference.type.TupleType) type;
                elementTypes = theTupleType.types;
                tupleType = theTupleType;
            } else { // type instanceof jsrc.matlab.typeinference.type.HVectorType
                jsrc.matlab.ast.tree.declaration.type.MatrixType vectorType =
                        (jsrc.matlab.ast.tree.declaration.type.MatrixType) type;
                elementTypes = new DataType[vectorType.m.right()];
                for (int i = 0; i < elementTypes.length; i++)
                    elementTypes[i] = vectorType.elemType.left();
                tupleType = new TupleType(elementTypes);
            }
            Type xType = typeVisitor.visitDispatch(tupleType);
            NonVoidType xnvType = (NonVoidType) xType;

            String idName = freshIdName();

            VarDecl varDeclAndCall = new VarDecl(xnvType, idName, methodCall);

            VarDeclOrSt[] statements = new VarDeclOrSt[ids.length + 1];
            statements[0] = varDeclAndCall;

            for (int i = 0; i < ids.length; i++) {
                jsrc.matlab.ast.tree.expression.Id id = ids[i];
                Expression xRight = new FieldSelection(new Id(idName), new Id("_" + (i+1)));
                statements[i + 1] = makeVarDeclOrAssignment(id.name, elementTypes[i], xRight);
            }

            return statements;
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.ArrayAssignment arrayAssignment) {
            String name = arrayAssignment.array.name;

            if (arrayAssignment.indices.length == 1 && arrayAssignment.indices[0] instanceof Colon) {
                Expression xRight = expressionVisitor.visitDispatch(arrayAssignment.right);
                return singleToArraySt(new ExpSt(
                    new MethodCall("Lib", "colonAssign", new Expression[]{new Id(name), xRight})
                ));
            }

            Expression[] xIndices = new Expression[arrayAssignment.indices.length];

            arrayType.push((jsrc.matlab.ast.tree.declaration.type.MatrixType)arrayAssignment.array.type);
            if (arrayAssignment.indices.length == 1)
                linearIndexing.push(true);
            else
                linearIndexing.push(false);
            arrayName.push(name);
            for (int i = 0; i < xIndices.length; i++) {
                indexNum.push(i);
                xIndices[i] = expressionVisitor.visitDispatch(arrayAssignment.indices[i]);
                indexNum.pop();
            }
            arrayName.pop();
            Expression xRight = expressionVisitor.visitDispatch(arrayAssignment.right);
            linearIndexing.pop();
            arrayType.pop();

            return singleToArraySt(new ExpSt(new ArrayUpdate(new Id(name), xIndices, xRight)));
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.If theIf) {

            Expression xOutCondition = xBoolExpression(theIf.condition);
            VarDeclOrSt[] xOutIfVarDeclOrSts = statementVisitor.visitDispatch(theIf.ifStatement);

            Option<VarDeclOrSt[]> xElseBlockStsOption;
            VarDeclOrSt[] xElseVarDeclOrSts;
            Option<jsrc.matlab.ast.tree.statement.If.ElseIf[]> elseIfsOption = theIf.elseifs;
            if (elseIfsOption.isPresent()) {
                jsrc.matlab.ast.tree.statement.If.ElseIf[] elseIfs = ((Some<jsrc.matlab.ast.tree.statement.If.ElseIf[]>)theIf.elseifs).get();

                Option<jsrc.matlab.ast.tree.statement.Statement> elseBlockStOption = theIf.elseStatementOption;
                if (elseBlockStOption.isPresent()) {
                    jsrc.matlab.ast.tree.statement.Statement elseBlockSt = ((Some<jsrc.matlab.ast.tree.statement.Statement>) elseBlockStOption).get();

                    xElseVarDeclOrSts = statementVisitor.visitDispatch(elseBlockSt);
                    xElseBlockStsOption = new Some<VarDeclOrSt[]>(xElseVarDeclOrSts);
                } else
                    xElseBlockStsOption = None.instance();

                for (int i = elseIfs.length - 1; i >= 0 ; i--) {
                    jsrc.matlab.ast.tree.statement.If.ElseIf elseIf = elseIfs[i];
                    Expression xCondition = xBoolExpression(elseIf.condition);
                    VarDeclOrSt[] xIfVarDeclOrSts = statementVisitor.visitDispatch(elseIf.statement);
                    if (xElseBlockStsOption.isPresent()) {
                        xElseVarDeclOrSts = ((Some<VarDeclOrSt[]>)xElseBlockStsOption).get();
                        xElseVarDeclOrSts = singleToArraySt(new If(xCondition, arrayToSingleSt(xIfVarDeclOrSts), arrayToSingleSt(xElseVarDeclOrSts)));
                    } else
                        xElseVarDeclOrSts = singleToArraySt(new If(xCondition, arrayToSingleSt(xIfVarDeclOrSts)));
                    xElseBlockStsOption = new Some<VarDeclOrSt[]>(xElseVarDeclOrSts);
                }
            } else {
                Option<jsrc.matlab.ast.tree.statement.Statement> elseBlockStOption = theIf.elseStatementOption;
                if (elseBlockStOption.isPresent()) {
                    jsrc.matlab.ast.tree.statement.Statement elseBlockSt = ((Some<jsrc.matlab.ast.tree.statement.Statement>) elseBlockStOption).get();

                    xElseVarDeclOrSts = statementVisitor.visitDispatch(elseBlockSt);
                    xElseBlockStsOption = new Some<VarDeclOrSt[]>(xElseVarDeclOrSts);
                } else
                    xElseBlockStsOption = None.instance();
            }

            if (xElseBlockStsOption.isPresent()) {
                xElseVarDeclOrSts = ((Some<VarDeclOrSt[]>)xElseBlockStsOption).get();
                return singleToArraySt(new If(xOutCondition, arrayToSingleSt(xOutIfVarDeclOrSts), arrayToSingleSt(xElseVarDeclOrSts)));
            } else
                return singleToArraySt(new If(xOutCondition, arrayToSingleSt(xOutIfVarDeclOrSts)));
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.For theFor) {

            String idName = theFor.id.name;
            Expression xRange = expressionVisitor.visitDispatch(theFor.range);
            xRange = new MethodCall(xRange, "values", new Expression[0]);

            VarDeclOrSt[] xVarDeclOrSts = statementVisitor.visitDispatch(theFor.block);
            Statement xStatement = arrayToSingleSt(xVarDeclOrSts);

            PointFor xFor = new PointFor(PointFor.Type.FOR, idName, xRange, xStatement);

            return singleToArraySt(xFor);
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.While theWhile) {
            Expression xBoolCondition = xBoolExpression(theWhile.condition);
            VarDeclOrSt[] xVarDeclOrSts = statementVisitor.visitDispatch(theWhile.statement);
            Statement xStatement = arrayToSingleSt(xVarDeclOrSts);
            While xWhile = new While(xBoolCondition, xStatement);
            return singleToArraySt(xWhile);
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.Switch theSwitch) {
            Expression xSelector = expressionVisitor.visitDispatch(theSwitch.selector);
            Switch.Case[] xCases = new Switch.Case[theSwitch.cases.length];
            for (int i = 0; i < xCases.length; i++)
                xCases[i] = processCase(theSwitch.cases[i]);
            Option<VarDeclOrSt[]> defaultBlockSts = theSwitch.defaultStatements.apply(
                new Fun0<Option<VarDeclOrSt[]>>() {
                    public Option<VarDeclOrSt[]> apply() {
                        return None.instance();
                    }
                },
                new Fun<jsrc.matlab.ast.tree.statement.Statement[], Option<VarDeclOrSt[]>>() {
                    public Option<VarDeclOrSt[]> apply(jsrc.matlab.ast.tree.statement.Statement[] defaultBlockSts) {
                        VarDeclOrSt[] allxVarDeclOrSts = computeXBlockSts(defaultBlockSts);
                        return new Some<VarDeclOrSt[]>(allxVarDeclOrSts);
                    }
                }
            );

            Switch xSwitch = new Switch(xSelector, xCases, defaultBlockSts);

            return singleToArraySt(xSwitch);
        }

        public Switch.Case processCase(jsrc.matlab.ast.tree.statement.Switch.Case theCase) {
            Expression xGuard = expressionVisitor.visitDispatch(theCase.guard);
            VarDeclOrSt[] xVarDeclOrSts = computeXBlockSts(theCase.statements);
            return new Switch.Case(xGuard, xVarDeclOrSts);
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.CallSt callSt) {
            Expression expression = expressionVisitor.visitDispatch(callSt.call);
            ExpSt expSt = new ExpSt(expression);
            return singleToArraySt(expSt);
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.Return aReturn) {
            return singleToArraySt(returnSt);
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.Break aBreak) {
            return singleToArraySt(Break.instance()); // To do late: break means return out of loops
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.Print print) {
            return singleToArraySt(new Print(expressionVisitor.visitDispatch(print.arg)));
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.Println println) {
            return singleToArraySt(new Println(expressionVisitor.visitDispatch(println.arg)));
        }

        public VarDeclOrSt[] visit(jsrc.matlab.ast.tree.statement.Block block) {
            jsrc.matlab.ast.tree.statement.Statement[] statements = block.statements;
            Block xBlock = new Block(computeXBlockSts(statements));
            return singleToArraySt(xBlock);
        }

        private Statement arrayToSingleSt(VarDeclOrSt[] statements) {
            return
                (statements.length == 1) ?
                    (Statement)(statements[0]) :
                    new Block(statements);
        }

        private VarDeclOrSt[] singleToArraySt(VarDeclOrSt statement) {
            return new VarDeclOrSt[]{statement};
        }

        private VarDeclOrSt[] computeXBlockSts(jsrc.matlab.ast.tree.statement.Statement[] statements) {
            List<VarDeclOrSt> allxVarDeclOrStsList = new ArrayList<VarDeclOrSt>();
            for (int i = 0; i < statements.length; i++) {
                VarDeclOrSt[] xVarDeclOrSts = statementVisitor.visitDispatch(statements[i]);
//                for (int j = 0; j < xBlockSts.length; j++) {
//                    BlockSt xBlockSt = xBlockSts[j];
//                    if (xBlockSt == null)
//                        throw new RuntimeException();
//                }
                allxVarDeclOrStsList.addAll(Arrays.asList(xVarDeclOrSts));
            }
            VarDeclOrSt[] resultArray = new VarDeclOrSt[allxVarDeclOrStsList.size()];
            int i = 0;
            for (VarDeclOrSt statement : allxVarDeclOrStsList) {
                resultArray[i] = statement;
                i++;
            }
            return resultArray;
        }

    }
    private VarDeclOrSt makeVarDeclOrAssignment(String varName, DataType type, Expression rightExp) {
        VarDeclOrSt statement;
        if (!symbolTable.contains(varName)) {
            // It should not be unit

//                if (!(type instanceof ScalarType))
//                    throw new RuntimeException();

            Type xType = typeVisitor.visitDispatch(type);
            NonVoidType xnvType = (NonVoidType) xType;

            try {
                symbolTable.put(varName, xnvType);
            } catch (DuplicateDefExc e) { e.printStackTrace(System.out); }

            statement = new VarDecl(xnvType, new Id(varName), rightExp);
        } else
            statement = new Assignment(new Id(varName), rightExp);
        return statement;
    }

    private Expression xBoolExpression(jsrc.matlab.ast.tree.expression.Expression expression) {
        Expression xExpression = expressionVisitor.visitDispatch(expression);
        jsrc.matlab.typeinference.type.DataType type = expression.type;
        if (type == jsrc.matlab.ast.tree.declaration.type.BoolType.instance())
            return xExpression;
/*
        if (type instanceof jsrc.matlab.ast.tree.declaration.type.MatrixType) {
            jsrc.matlab.ast.tree.declaration.type.MatrixType matrixType =
                    (jsrc.matlab.ast.tree.declaration.type.MatrixType) type;
            jsrc.matlab.ast.tree.declaration.type.ScalarType elemType = matrixType.elemType.apply(
                new Fun<jsrc.matlab.ast.tree.declaration.type.ScalarType, jsrc.matlab.ast.tree.declaration.type.ScalarType>() {
                    public jsrc.matlab.ast.tree.declaration.type.ScalarType apply(jsrc.matlab.ast.tree.declaration.type.ScalarType input) {
                        return input;
                    }
                },
                new Fun<TypeVar, jsrc.matlab.ast.tree.declaration.type.ScalarType>() {
                    public jsrc.matlab.ast.tree.declaration.type.ScalarType apply(TypeVar input) {
                        throw new RuntimeException();
                    }
                }
            );
            if (elemType == jsrc.matlab.ast.tree.declaration.type.BoolType.instance())
                return xExpression;
        }
*/
//        System.out.println();
//        System.out.println("Type at xBoolExpression: " + type);
        //return new NotEquality(xCondition, new IntLiteral(0));
        return new MethodCall(new Id("Lib"), new Id("logical"), new Expression[]{xExpression});
    }

    private int freshIdCount = 0;
    private String freshIdName() {
        freshIdCount++;
        return "xId" + freshIdCount;
    }
    
    private TheExpressionVisitor expressionVisitor = new TheExpressionVisitor();
    private class TheExpressionVisitor implements ExpressionVisitor<Expression> {
        public Expression visitDispatch(jsrc.matlab.ast.tree.expression.Expression expression) {
            return expression.accept(this);
        }

        public Expression visit(Literal literal) {
            return literalVisitor.visitDispatch(literal);
        }

        private TheLiteralVisitor literalVisitor = new TheLiteralVisitor();
        private class TheLiteralVisitor implements LiteralVisitor<Expression> {
            public Expression visitDispatch(jsrc.matlab.ast.tree.expression.literal.Literal literal) {
                return literal.accept(this);
            }
            public Expression visit(jsrc.matlab.ast.tree.expression.literal.StringLiteral stringLiteral) {
                return new StringLiteral("\"" + stringLiteral.lexeme + "\"");
            }

            public Expression visit(jsrc.matlab.ast.tree.expression.literal.DoubleLiteral doubleLiteral) {
                return new DoubleLiteral(doubleLiteral.lexeme);
            }

            public Expression visit(jsrc.matlab.ast.tree.expression.literal.IntLiteral intLiteral) {
                return new IntLiteral(intLiteral.lexeme);
            }

            public Expression visit(Colon colon) {
                RangeVectorConstructor rangeVectorConstructor = new RangeVectorConstructor(
                        new jsrc.matlab.ast.tree.expression.literal.IntLiteral(1),
                        End.instance()
                );
                rangeVectorConstructor.type = colon.type;
                return expressionVisitor.visitDispatch(
                        rangeVectorConstructor);

//                if (linearIndexing.peek()) {
                    //mymark
//                    jsrc.matlab.ast.tree.declaration.type.MatrixType colonType = (jsrc.matlab.ast.tree.declaration.type.MatrixType)colon.type;
//                    rangeVectorConstructor.type = new jsrc.matlab.ast.tree.declaration.type.MatrixType(colonType.elemType, colonType.m, colonType.n);
//                    Transpose transpose = new Transpose(rangeVectorConstructor);
//                    transpose.type = colon.type;
//                    return expressionVisitor.visitDispatch(
//                            transpose);
//                } else {
//                }
            }

            public Expression visit(End end) {
                String name = arrayName.peek();
                int index = indexNum.peek();
                if (linearIndexing.peek()) {
                // This is for matrix(:) that is translated to matrix(1:end).
                    jsrc.matlab.ast.tree.declaration.type.MatrixType matrixType = arrayType.peek();
                    if (matrixType.horizontal())
                        return new FieldSelection(new Id(name), new Id("m"));
                    else if (matrixType.vertical())
                        return new FieldSelection(new Id(name), new Id("n"));
                    else
                        return new Times(
                            new FieldSelection(new Id(name), new Id("n")),
                            new FieldSelection(new Id(name), new Id("m"))
                        );
                } else {
                    if (index == 0)
                        return new FieldSelection(new Id(name), new Id("n"));
                    else /*if (index == 1)*/
                        return new FieldSelection(new Id(name), new Id("m"));
                    //else
                    //    return new MethodCall(new Id(name), new Id("dim"), new Expression[]{new IntLiteral(index + 1)});
                }
            }
        }

        public Expression visit(Op op) {
            return opVisitor.visitDispatch(op);
        }
        private TheOpVisitor opVisitor = new TheOpVisitor();
        private class TheOpVisitor implements OpVisitor<Expression> {
            public Expression visitDispatch(jsrc.matlab.ast.tree.expression.op.Op op) {
                return op.accept(this);
            }

            public Expression visit(jsrc.matlab.ast.tree.expression.op.relational.RelationalOp expression) {
                return relationalOpVisitor.visitDispatch(expression);
            }

            private TheRelationalVisitor relationalOpVisitor = new TheRelationalVisitor();
            private class TheRelationalVisitor implements OpVisitor.RelationalVisitor<Expression> {
                public Expression visitDispatch(jsrc.matlab.ast.tree.expression.op.relational.RelationalOp relationalOp) {
                    return relationalOp.accept(this);
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.relational.Equality equality) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(equality.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(equality.operand2);
                    if (((equality.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (equality.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((equality.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (equality.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new Equality(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "eq", new Expression[]{xOperand1, xOperand2});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.relational.NotEquality notEquality) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(notEquality.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(notEquality.operand2);
                    if (((notEquality.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (notEquality.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((notEquality.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (notEquality.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new NotEquality(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "neq", new Expression[]{xOperand1, xOperand2});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.relational.LessThan lessThan) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(lessThan.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(lessThan.operand2);
                    if (((lessThan.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (lessThan.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((lessThan.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (lessThan.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new LessThan(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "lt", new Expression[]{xOperand1, xOperand2});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.relational.GreaterThan greaterThan) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(greaterThan.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(greaterThan.operand2);
                    if (((greaterThan.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (greaterThan.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((greaterThan.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (greaterThan.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new GreaterThan(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "gt", new Expression[]{xOperand1, xOperand2});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.relational.LessThanEqual lessThanEqual) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(lessThanEqual.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(lessThanEqual.operand2);
                    if (((lessThanEqual.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (lessThanEqual.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((lessThanEqual.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (lessThanEqual.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new LessThanEqual(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "lteq", new Expression[]{xOperand1, xOperand2});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.relational.GreaterThanEqual greaterThanEqual) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(greaterThanEqual.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(greaterThanEqual.operand2);
                    if (((greaterThanEqual.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (greaterThanEqual.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((greaterThanEqual.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (greaterThanEqual.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new GreaterThanEqual(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "gteq", new Expression[]{xOperand1, xOperand2});
                }
            }

            public Expression visit(jsrc.matlab.ast.tree.expression.op.logical.LogicalOp logicalOp) {
                return logicalOpVisitor.visitDispatch(logicalOp);
            }
            private TheLogicalOpVisitor logicalOpVisitor = new TheLogicalOpVisitor();
            private class TheLogicalOpVisitor implements LogicalOpVisitor<Expression> {
                public Expression visitDispatch(jsrc.matlab.ast.tree.expression.op.logical.LogicalOp logicalOp) {
                    return logicalOp.accept(this);
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.logical.And and) {
                    Expression xOperand1 = xBoolExpression(and.operand1);
                    Expression xOperand2 = xBoolExpression(and.operand2);
                    return new And(xOperand1, xOperand2);
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.logical.Or or) {
                    Expression xOperand1 = xBoolExpression(or.operand1);
                    Expression xOperand2 = xBoolExpression(or.operand2);
                    return new Or(xOperand1, xOperand2);
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.logical.Not not) {
                    Expression xOperand = expressionVisitor.visitDispatch(not.operand);
                    if (not.operand.type instanceof jsrc.matlab.ast.tree.declaration.type.BoolType)
                        return new Not(xOperand);
//                    if (not.operand.type instanceof jsrc.matlab.ast.tree.declaration.type.ScalarType)
//                        return new Not(new NotEquality(xOperand, new IntLiteral(0)));
                    return new MethodCall("Lib", "not", new Expression[]{xOperand});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.logical.ElementWiseAnd elementWiseAnd) {
                    // This is not needed to be converted to bool.
                    Expression xOperand1 = expressionVisitor.visitDispatch(elementWiseAnd.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(elementWiseAnd.operand2);
//                    if ((elementWiseAnd.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.ScalarType) &&
//                        (elementWiseAnd.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.ScalarType)) {
//                        if (!(elementWiseAnd.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.BoolType))
//                            xOperand1 = new NotEquality(xOperand1, new IntLiteral(0));
//                        if (!(elementWiseAnd.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.BoolType))
//                            xOperand2 = new NotEquality(xOperand2, new IntLiteral(0));
//                        return new And(xOperand1, xOperand2);
//                    }
//                    return new ElementWiseAnd(xOperand1, xOperand2);
                    return new MethodCall("Lib", "and", new Expression[]{xOperand1, xOperand2});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.logical.ElementWiseOr elementWiseOr) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(elementWiseOr.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(elementWiseOr.operand2);

//                    if ((elementWiseOr.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.ScalarType) &&
//                        (elementWiseOr.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.ScalarType)) {
//                        if (!(elementWiseOr.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.BoolType))
//                            xOperand1 = new NotEquality(xOperand1, new IntLiteral(0));
//                        if (!(elementWiseOr.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.BoolType))
//                            xOperand2 = new NotEquality(xOperand2, new IntLiteral(0));
//                        return new Or(xOperand1, xOperand2);
//                    }
//                    return new ElementWiseOr(xOperand1, xOperand2);
                    return new MethodCall("Lib", "or", new Expression[]{xOperand1, xOperand2});
                }
            }


            public Expression visit(jsrc.matlab.ast.tree.expression.op.math.MathOp mathOp) {
                return mathOpVisitor.visitDispatch(mathOp);
            }
            private TheMathOpVisitor mathOpVisitor = new TheMathOpVisitor();
            private class TheMathOpVisitor implements MathOpVisitor<Expression> {
                public Expression visitDispatch(jsrc.matlab.ast.tree.expression.op.math.MathOp mathOp) {
                    return mathOp.accept(this);
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.Plus plus) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(plus.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(plus.operand2);
                    if (((plus.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (plus.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((plus.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (plus.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new Plus(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "plus", new Expression[]{xOperand1, xOperand2});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.Minus minus) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(minus.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(minus.operand2);
                    if (((minus.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (minus.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((minus.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (minus.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new Minus(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "minus", new Expression[]{xOperand1, xOperand2});
                }



                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.DotTimes dotTimes) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(dotTimes.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(dotTimes.operand2);
                    if (((dotTimes.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (dotTimes.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((dotTimes.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (dotTimes.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new Times(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "dotTimes", new Expression[]{xOperand1, xOperand2});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.DotDivide dotDivide) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(dotDivide.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(dotDivide.operand2);
                    if (((dotDivide.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (dotDivide.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((dotDivide.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (dotDivide.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new Divide(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "dotDivide", new Expression[]{xOperand1, xOperand2});
                }

//                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.Modulus modulus) {
//                    Expression xoperand1 = expressionVisitor.visitDispatch(modulus.operand1);
//                    Expression xoperand2 = expressionVisitor.visitDispatch(modulus.operand2);
//                    return new Modulus(xoperand1, xoperand2);
//                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.DotPower dotPower) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(dotPower.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(dotPower.operand2);
                    if (((dotPower.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (dotPower.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((dotPower.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (dotPower.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new Power(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "dotPower", new Expression[]{xOperand1, xOperand2});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.UnaryMinus unaryMinus) {
                    Expression xOperand = expressionVisitor.visitDispatch(unaryMinus.operand);
                    if ((unaryMinus.operand.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                        (unaryMinus.operand.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType))
                        return new UnaryMinus(xOperand);
                    else
                        return new MethodCall("Lib", "minus", new Expression[]{xOperand});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.UnaryPlus unaryPlus) {
                    Expression xOperand = expressionVisitor.visitDispatch(unaryPlus.operand);
                    if ((unaryPlus.operand.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                        (unaryPlus.operand.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType))
                        return new UnaryPlus(xOperand);
                    else
                        return new MethodCall("Lib", "plus", new Expression[]{xOperand});

                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.Times times) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(times.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(times.operand2);
                    if (((times.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (times.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((times.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (times.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new Times(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "times", new Expression[]{xOperand1, xOperand2});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.Divide divide) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(divide.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(divide.operand2);
                    if (((divide.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (divide.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
                        ((divide.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                         (divide.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                        return new Divide(xOperand1, xOperand2);
                    else
                        return new MethodCall("Lib", "divide", new Expression[]{xOperand1, xOperand2});
                }


                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.Power power) {
                    Expression xOperand1 = expressionVisitor.visitDispatch(power.operand1);
                    Expression xOperand2 = expressionVisitor.visitDispatch(power.operand2);
//                    if (((power.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
//                         (power.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)) &&
//                        ((power.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
//                         (power.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)))
                    if  ((power.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) &&
                         (power.operand2.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType))
                        return new Power(xOperand1, xOperand2);
                    if ((power.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.IntType) ||
                        (power.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType) ||
                        (power.operand1.type instanceof jsrc.matlab.ast.tree.declaration.type.BoolType))
                        return new MethodCall("Lib", "dotPower", new Expression[]{xOperand1, xOperand2});
                    return new MethodCall("Lib", "power", new Expression[]{xOperand1, xOperand2});
                }

                public Expression visit(jsrc.matlab.ast.tree.expression.op.math.Transpose transpose) {
                    Expression xoperand = expressionVisitor.visitDispatch(transpose.operand);
                    return new MethodCall(xoperand, "transpose", new Expression[0]);
                }
            }

            public Expression visit(MatrixConstructor matrixConstructor) {

                jsrc.matlab.ast.tree.expression.Expression[][] elements = matrixConstructor.elements;
                Expression[][] xElements;
                if (elements.length != 0)
                    xElements = new Expression[elements.length][elements[0].length];
                else
                    xElements = new Expression[0][0];

                for (int i = 0; i < elements.length; i++) {
                    for (int j = 0; j < xElements[0].length; j++) {
                        jsrc.matlab.ast.tree.expression.Expression element = elements[i][j];
                        xElements[i][j] = expressionVisitor.visitDispatch(element);
                    }
                }

                // new IntMatrix([1, 2, 3]);
                // and
                // new IntMatrix(
                //            [[1, 2, 3],
                //             [4, 5, 6],
                //             [7, 8, 9]]);
                Expression[] args;
                if (xElements.length == 0) {
                    args = new Expression[0];
                } else if (xElements.length == 1) {
                    // To deal with the concatenation case

                    if (
                        (xElements[0].length > 0 && elements[0][0].type instanceof jsrc.matlab.ast.tree.declaration.type.MatrixType) ||
                        (xElements[0].length > 1 && elements[0][1].type instanceof jsrc.matlab.ast.tree.declaration.type.MatrixType)
                    )
                        return new MethodCall("Lib", "concat", new Expression[]{xElements[0][0], xElements[0][1]});

                    args = new Expression[]{new ArrayLiteral(xElements[0])};
                }
                else {
                    ArrayLiteral[] arrays = new ArrayLiteral[elements.length];
                    for (int i = 0; i < xElements.length; i++) {
                        arrays[i] = new ArrayLiteral(xElements[i]);
                    }
                    args = new Expression[]{new ArrayLiteral(arrays)};
                }

                Type xType = typeVisitor.visitDispatch(matrixConstructor.type);

                return new New(xType, args);
            }

            public Expression visit(final jsrc.matlab.ast.tree.expression.op.constructors.RangeVectorConstructor rangeVectorConstructor) {
                jsrc.matlab.ast.tree.declaration.type.MatrixType type =
                        new jsrc.matlab.ast.tree.declaration.type.MatrixType(
                                jsrc.matlab.ast.tree.declaration.type.IntType.instance(),
                                1, TypeVar.fresh()
                        );
                final Type xType = typeVisitor.visitDispatch(type);
                final Expression xLeftEndPoint = expressionVisitor.visitDispatch(rangeVectorConstructor.leftEndPoint);
                final Expression xRightEndPoint = expressionVisitor.visitDispatch(rangeVectorConstructor.rightEndPoint);
                return rangeVectorConstructor.step.apply(
                    new Fun0<Expression>() {
                        public Expression apply() {
                            return new New(xType, new Expression[] {xLeftEndPoint, xRightEndPoint});
                        }
                    },
                    new Fun<jsrc.matlab.ast.tree.expression.Expression, Expression>() {
                        public Expression apply(jsrc.matlab.ast.tree.expression.Expression step) {
                            Expression xStep = expressionVisitor.visitDispatch(step);
                            return new New(xType, new Expression[] {xLeftEndPoint, xStep, xRightEndPoint});
                        }
                    }
                );
            }


            public Expression visit(jsrc.matlab.ast.tree.expression.op.application.CallOrArrayAccess callOrArrayAccess) {
                //Check if this is a call or array indexing.
                //System.out.println(callOrArrayAccess.id.type);
                if (callOrArrayAccess.id.type instanceof jsrc.matlab.ast.tree.declaration.type.MatrixType)
                    return visit(new jsrc.matlab.ast.tree.expression.op.application.ArrayAccess(
                        callOrArrayAccess.id,
                        callOrArrayAccess.args
                    ));
                else {
                    Call call = new Call(
                            callOrArrayAccess.id,
                            callOrArrayAccess.args,
                            callOrArrayAccess.lib,
                            callOrArrayAccess.type,
                            callOrArrayAccess.origType
                    );
                    return visit(call);
                }
            }


            public Expression visit(jsrc.matlab.ast.tree.expression.op.application.Call call) {
                String xName = call.name.name;

                jsrc.matlab.ast.tree.expression.Expression[] args = call.args;
                Expression[] xArgs = new Expression[args.length];
                for (int i = 0; i < xArgs.length; i++) {
                    xArgs[i] = expressionVisitor.visitDispatch(args[i]);
                    if ((args[i] instanceof jsrc.matlab.ast.tree.expression.Id) &&
                        (args[i].type instanceof jsrc.matlab.ast.tree.declaration.type.MatrixType))
                        xArgs[i] = new MethodCall(xArgs[i], "clone", new Expression[]{});
                }

                if (xName.equals("readMatrix")) {
                    jsrc.matlab.ast.tree.declaration.type.MatrixType matrixType =
                            (jsrc.matlab.ast.tree.declaration.type.MatrixType)call.type;
                    xName = matrixType.elemType.apply(
                        new Fun<jsrc.matlab.ast.tree.declaration.type.ScalarType, String>() {
                            public String apply(jsrc.matlab.ast.tree.declaration.type.ScalarType input) {
                                if (input == jsrc.matlab.ast.tree.declaration.type.DoubleType.instance())
                                    return "readDoubleMatrix";
                                else
                                    return "readIntMatrix";
                            }
                        }, new Fun<TypeVar, String>() {
                            public String apply(TypeVar input) {
                                throw new RuntimeException();
                            }
                        }
                    );
                }

                if (xName.equals("mod"))
                    return new Modulus(xArgs[0], xArgs[1]);

                if (xName.equals("sort")) {
                    DataType callType = call.type;
                    if ((args.length == 2) && (args[1] instanceof jsrc.matlab.ast.tree.expression.literal.StringLiteral)) {
                        jsrc.matlab.ast.tree.expression.literal.StringLiteral st =
                                (jsrc.matlab.ast.tree.expression.literal.StringLiteral) args[1];
                        if (st.lexeme.equals("descend"))
                            xName += "D";
                        Expression[] newXArgs = new Expression[xArgs.length - 1];
                        for (int i = 0; i < newXArgs.length; i++)
                            newXArgs[i] = xArgs[i];
                        xArgs = newXArgs;
                    }
                    if (callType instanceof TupleType) {
                        xName += "I";
                    }
                }

                if (xName.equals("ones") || xName.equals("zeros")) {
                    jsrc.matlab.ast.tree.declaration.type.MatrixType matrixType =
                            (jsrc.matlab.ast.tree.declaration.type.MatrixType) call.type;
                    xName = matrixType.elemType.apply(
                        new Fun<jsrc.matlab.ast.tree.declaration.type.ScalarType, String>() {
                            public String apply(jsrc.matlab.ast.tree.declaration.type.ScalarType scalarType) {
                                if (scalarType instanceof jsrc.matlab.ast.tree.declaration.type.IntType)
                                    return "";
                                else //if (scalarType instanceof jsrc.matlab.ast.tree.declaration.type.DoubleType)
                                    return "d";
                            }
                        },
                        new Fun<TypeVar, String>() {
                            public String apply(TypeVar input) {
                                throw new RuntimeException();
                            }
                        }
                    ) + xName;
                }

                String className; //Static call
                if (call.isLib())
                    className = "Lib";
                else
                    className = Address.capName(xName);
                Expression receiver = new Id(className);


                MethodCall methodCall = new MethodCall(receiver, xName, xArgs);

                DataType origType = call.origType;
                if (origType instanceof TupleType) {
//                    TupleType tupleType = (TupleType) origType;
//                    DataType outType = tupleType.types[0];
//                    int m = tupleType.types.length;
//                    String tupleOut = freshIdName();
//                    BlockSt statement1 = makeVarDeclOrAssignment(tupleOut, tupleType, methodCall);
//                    ScalarType xType = (ScalarType)typeVisitor.visitDispatch(outType);
//                    MatrixType matrixType = new MatrixType(xType, 1, m);
//                    Expression[] args = new Expression[m];
//                    for (int i = 0; i < args.length; i++)
//                        args[i] = new FieldSelection(new Id(tupleOut), new Id("_" + (i+1)));
//                    Expression exp = new New(matrixType, args);
//                    BlockSt statement2 = makeVarDeclOrAssignment(varName, idType, exp);
//                    return new BlockSt[]{statement1, statement2};
                    return new MethodCall("Lib", "tupleToVector", new Expression[]{methodCall});
                }

                return methodCall;
            }


            public Expression visit(jsrc.matlab.ast.tree.expression.op.application.ArrayAccess arrayAccess) {
                String name = arrayAccess.getName();
                jsrc.matlab.ast.tree.expression.Expression[] args = arrayAccess.args;
                Expression[] xArgs = new Expression[args.length];

                arrayType.push((jsrc.matlab.ast.tree.declaration.type.MatrixType) arrayAccess.array.type);

                if (args.length == 1)
                    linearIndexing.push(true);
                else
                    linearIndexing.push(false);
                arrayName.push(name);
                for (int i = 0; i < args.length; i++) {
                    jsrc.matlab.ast.tree.expression.Expression arg = args[i];
                    indexNum.push(i);
                    xArgs[i] = expressionVisitor.visitDispatch(arg);
                    indexNum.pop();
                }
                arrayName.pop();
                linearIndexing.pop();
                arrayType.pop();

                if (args.length == 1) { // Linear Indexing
                    jsrc.matlab.ast.tree.declaration.type.MatrixType arrayType =
                            (jsrc.matlab.ast.tree.declaration.type.MatrixType) arrayAccess.array.type;

                    jsrc.matlab.typeinference.type.DataType indexType = arrayAccess.args[0].type;

                    if (indexType instanceof jsrc.matlab.ast.tree.declaration.type.IntType) {
                        return new ArrayAccess(new Id(name), xArgs);
                    } else if (args[0] instanceof Colon) {
                        return new MethodCall(name, "singleColumn", new Expression[0]);
                    } else {
                        jsrc.matlab.ast.tree.declaration.type.MatrixType matrixIndexType =
                                (jsrc.matlab.ast.tree.declaration.type.MatrixType)indexType;


                        String arraySym =
                            (arrayType.horizontal()) ?
                                "H" :
                                (
                                    (arrayType.vertical()) ?
                                        "V" :
                                        (
                                            (arrayType.hasKnownRowCount() && arrayType.hasKnownColumnCount()) ?
                                            "M" :
                                            "N"
                                        )
                                );

                        String indexSym =
                            (matrixIndexType.horizontal()) ?
                                "H" :
                                (
                                    (matrixIndexType.vertical()) ?
                                        "V" :
                                        (
                                            (arrayType.hasKnownRowCount() && arrayType.hasKnownColumnCount()) ?
                                            "M" :
                                            "N"
                                        )
                                );

                        if (arraySym.equals("N") || indexSym.equals("N"))
                            return new ArrayAccess(new Id(name), xArgs);
                        else {
                            String methodName = "apply" + arraySym + indexSym;
                            return new MethodCall(new Id(name), methodName, xArgs);
                        }
                    }
                }

                return new ArrayAccess(new Id(name), xArgs);
            }

        }

        public Expression visit(jsrc.matlab.ast.tree.expression.Id id) {
            return new Id(id.name);
        }

    }

    private TheTypeVisitor typeVisitor = new TheTypeVisitor();
    private class TheTypeVisitor implements TypeVisitor<Type> {
        public Type visitDispatch(jsrc.matlab.typeinference.type.DataType type) {
            return type.accept(this);
        }

        public Type visit(jsrc.matlab.ast.tree.declaration.type.IntType intType) {
            return IntType.instance();
        }

        public Type visit(jsrc.matlab.ast.tree.declaration.type.DoubleType doubleType) {
            return DoubleType.instance();
        }

        public Type visit(jsrc.matlab.ast.tree.declaration.type.BoolType boolType) {
            return BooleanType.instance();
        }

        public Type visit(jsrc.matlab.ast.tree.declaration.type.StringType stringType) {
            return StringType.instance();
        }

        public Type visit(jsrc.matlab.ast.tree.declaration.type.MatrixType matrixType) {
            ScalarType elemType = matrixType.elemType.apply(
                new Fun<jsrc.matlab.ast.tree.declaration.type.ScalarType, ScalarType>() {
                    public ScalarType apply(jsrc.matlab.ast.tree.declaration.type.ScalarType scalarType) {
                        return (ScalarType)typeVisitor.visitDispatch(scalarType);
                    }
                },
                new Fun<TypeVar, ScalarType>() {
                    public ScalarType apply(TypeVar typeVar) {
                        throw new RuntimeException(); // We do not expect TypeVars here.
                    }
                }
            );
            Option<Integer> n = matrixType.n.apply(
                new Fun<TypeVar, Option<Integer>>() {
                    public Option<Integer> apply(TypeVar input) {
                        return None.instance();
                    }
                },
                new Fun<Integer, Option<Integer>>() {
                    public Option<Integer> apply(Integer i) {
                        return new Some<Integer>(i);
                    }
                }
            );
            Option<Integer> m = matrixType.m.apply(
                new Fun<TypeVar, Option<Integer>>() {
                    public Option<Integer> apply(TypeVar input) {
                        return None.instance();
                    }
                },
                new Fun<Integer, Option<Integer>>() {
                    public Option<Integer> apply(Integer i) {
                        return new Some<Integer>(i);
                    }
                }
            );

            MatrixType xMatrixType = new MatrixType(elemType, n, m);
//            System.out.println("ASTConverter$TheTypeVisitor.visit");
//            System.out.println(xMatrixType);
            return xMatrixType;
        }

        public Type visit(TupleType tupleType) {
            jsrc.matlab.typeinference.type.DataType[] types = tupleType.types;
            int num = types.length;
            Type[] xTypes = new Type[num];
            for (int i = 0; i < xTypes.length; i++)
                xTypes[i] = typeVisitor.visitDispatch(types[i]);

            return new DefinedType("Tuple" + num, xTypes);
        }

        public Type visit(TypeVar typeVar) {
            throw new RuntimeException("Inside type var: " + typeVar);
        }

        public Type visit(UnitType unitType) {
            return VoidType.instance();
        }

//        private Type makeMatrix(Integer n, Integer m) {
//            return new DefinedType("Matrix", new String[] {n.toString(), m.toString()});
//        }
//        private Type makeHVector(Integer n) {
//            return new DefinedType("HVector", new String[] {n.toString()});
//        }
//        private Type makeVVector(Integer n) {
//            return new DefinedType("VVector", new String[] {n.toString()});
//        }

    }
}

