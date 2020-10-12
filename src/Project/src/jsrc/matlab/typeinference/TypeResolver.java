package jsrc.matlab.typeinference;

import jsrc.matlab.ast.tree.expression.literal.Colon;
import jsrc.matlab.ast.tree.Node;
import jsrc.matlab.ast.tree.declaration.*;
import jsrc.matlab.ast.tree.declaration.type.MatrixType;
import jsrc.matlab.ast.tree.declaration.type.ScalarType;
import jsrc.matlab.ast.tree.expression.*;
import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.literal.Literal;
import jsrc.matlab.ast.tree.expression.op.Op;
import jsrc.matlab.ast.tree.expression.op.application.ArrayAccess;
import jsrc.matlab.ast.tree.expression.op.application.Call;
import jsrc.matlab.ast.tree.expression.op.application.CallOrArrayAccess;
import jsrc.matlab.ast.tree.statement.*;
import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.type.DataType;
import jsrc.matlab.typeinference.type.Type;
import jsrc.matlab.typeinference.type.TypeVar;
import lesani.compiler.typing.Symbol;
import lesani.compiler.typing.SymbolTable;
import lesani.compiler.typing.exception.DuplicateDefExc;
import lesani.compiler.typing.exception.SymbolNotFoundException;
import lesani.compiler.typing.substitution.Substitution;
import lesani.collection.func.Fun0;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.collection.func.Fun;

import java.util.Iterator;

import static lesani.gui.console.Logger.*;

public class TypeResolver implements SVisitor {

    public static void resolve(CompilationUnit[] compilationUnits, Substitution substitution) {
//        System.out.println("In resolver");
        new TypeResolver(compilationUnits, substitution).resolve();
    }

    public static void resolve(SymbolTable<Type> symbolTable, Substitution substitution) {
        Iterator<Symbol> iterator = symbolTable.iterator();
        while (iterator.hasNext()) {
            Symbol symbol = iterator.next();
            Type type = null;
            try {
                type = symbolTable.get(symbol);
            } catch (SymbolNotFoundException e) { e.printStackTrace(); }
            Type xType = substitution.apply(type).apply(
                new Fun<lesani.compiler.typing.Type, Type>() {
                    public Type apply(lesani.compiler.typing.Type type) {
                        return (Type)type;
                    }
                },
                new Fun<Integer, Type>() {
                    public Type apply(Integer input) {
                        throw new RuntimeException();
                    }
                }
            );
            try {
                symbolTable.put(symbol, xType);
            } catch (DuplicateDefExc e) { e.printStackTrace(); }
        }
    }

    private CompilationUnit[] compilationUnits;
    private Substitution substitution;
//    private HashSet<String> typedSet = new HashSet<String>();

    public TypeResolver(CompilationUnit[] compilationUnits, Substitution substitution) {
        this.compilationUnits = compilationUnits;
        this.substitution = substitution;
    }

    public void resolve() {
        for (CompilationUnit compilationUnit : compilationUnits)
            visitDispatch(compilationUnit);
    }

    private void visitDispatch(Node node) {
        node.accept(this);
    }

    public Object visit(Function function) {
        Option<Id[]> inputParams = function.inputParams;
        Option<Id[]> outputParams = function.outputParams;
        inputParams.apply(
            new Fun0<Object>() {
                public Object apply() {
                    return null;
                }
            },
            new Fun<Id[], Object>() {
                public Object apply(Id[] inputParams) {
                    for (Id inputParam : inputParams) {
                        inputParam.type = substitution.apply(inputParam.type).apply(
                            new Fun<lesani.compiler.typing.Type, DataType>() {
                                public DataType apply(lesani.compiler.typing.Type xType) {
                                    if (xType instanceof DataType)
                                        return (DataType) xType;
                                    throw new RuntimeException(); //We do not expect function type here.
                                }
                            },
                            new Fun<Integer, DataType>() {
                                public DataType apply(Integer input) {
                                    throw new RuntimeException(); //We do not expect integers type here.
                                }
                            }
                        );
//            System.out.println(inputParam.name + ":" + inputParam.type);
//            typedSet.add(inputParam.name);

                   }
                    return null;
                }
            }
        );
        outputParams.apply(
            new Fun0<Object>() {
                public Object apply() {
                    return null;
                }
            },
            new Fun<Id[], Object>() {
                public Object apply(Id[] outputParams) {
                    for (Id outputParam : outputParams) {
                        outputParam.type = substitution.apply(outputParam.type).apply(
                            new Fun<lesani.compiler.typing.Type, DataType>() {
                                public DataType apply(lesani.compiler.typing.Type xType) {
                                    if (xType instanceof DataType)
                                        return (DataType) xType;
                                    else
                                        throw new RuntimeException(); //We do not expect function type here.
                                }
                            },
                            new Fun<Integer, DataType>() {
                                public DataType apply(Integer input) {
                                    throw new RuntimeException(); //We do not expect integers type here.
                                }
                            }
                        );
//            System.out.println(outputParam.name + ":" + outputParam.type);
//            typedSet.add(outputParam.name);
                    }
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
            expressionVisitor.visitDispatch(assignment.id);
            expressionVisitor.visitDispatch(assignment.right);
            return null;
        }

        public Object visit(ArrayAssignment arrayAssignment) {
            expressionVisitor.visitDispatch(arrayAssignment.array);
            for (int i = 0; i < arrayAssignment.indices.length; i++) {
                Expression index = arrayAssignment.indices[i];
                if (!(index instanceof Colon))
                    expressionVisitor.visitDispatch(index);
            }
            expressionVisitor.visitDispatch(arrayAssignment.right);
            return null;
        }

        public Object visit(CallSt callSt) {
            expressionVisitor.visitDispatch(callSt.call);
            return null;
        }

        public Object visit(CallAndMultiAssignment callAndMultiAssignment) {
            for (int i = 0; i < callAndMultiAssignment.ids.length; i++) {
                Id id = callAndMultiAssignment.ids[i];
                expressionVisitor.visitDispatch(id);
            }
            expressionVisitor.visitDispatch(callAndMultiAssignment.call);
            return null;
        }

        public Object visit(If theIf) {
            expressionVisitor.visitDispatch(theIf.condition);
            statementVisitor.visitDispatch(theIf.ifStatement);
            Option<If.ElseIf[]> elseIfsOption = theIf.elseifs;
            elseIfsOption.apply(
                new Fun0<Object>() {
                    public Object apply() {
                        return null;
                    }
                },
                new Fun<If.ElseIf[], Object>() {
                    public Object apply(If.ElseIf[] elseIfs) {
                        for (int i = 0; i < elseIfs.length; i++) {
                            If.ElseIf elseIf = elseIfs[i];
                            expressionVisitor.visitDispatch(elseIf.condition);
                            statementVisitor.visitDispatch(elseIf.statement);
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

        public Object visit(For aFor) {
            expressionVisitor.visitDispatch(aFor.id);
            expressionVisitor.visitDispatch(aFor.range);
            statementVisitor.visitDispatch(aFor.block);
            return null;
        }

        public Object visit(While aWhile) {
            expressionVisitor.visitDispatch(aWhile.condition);
            statementVisitor.visitDispatch(aWhile.statement);
            return null;
        }

        public Object visit(Switch aSwitch) {
            expressionVisitor.visitDispatch(aSwitch.selector);
            Switch.Case[] cases = aSwitch.cases;
            for (Switch.Case aCase : cases) {
                expressionVisitor.visitDispatch(aCase.guard);
                for (int i = 0; i < aCase.statements.length; i++) {
                    Statement statement = aCase.statements[i];
                    statementVisitor.visitDispatch(statement);
                }
            }
            aSwitch.defaultStatements.apply(
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

        public Object visit(Break aBreak) {
            return null;
        }

        public Object visit(Return aReturn) {
            return null;
        }

        public Object visit(Block block) {
            Statement[] statements = block.statements;
            for (Statement statement : statements)
                statementVisitor.visitDispatch(statement);
            return null;
        }

        public Object visit(Print print) {
            expressionVisitor.visitDispatch(print.arg);
            return null;
        }

        public Object visit(Println println) {
            expressionVisitor.visitDispatch(println.arg);
            return null;
        }
    }

    private DataType resolveType(Type type) {
        return substitution.apply(type).apply(
                    new Fun<lesani.compiler.typing.Type, DataType>() {
                        public DataType apply(lesani.compiler.typing.Type xType) {
                            if (xType instanceof DataType)
                                return (DataType) xType;
                            else
                                throw new RuntimeException(); //We do not expect function type here.
                        }
                    },
                    new Fun<Integer, DataType>() {
                        public DataType apply(Integer input) {
                            throw new RuntimeException(); //We do not expect integer type here.
                        }
                    }
            );
    }
    private TheExpressionVisitor expressionVisitor = new TheExpressionVisitor();
    private class TheExpressionVisitor implements ExpressionVisitor<Object> {
        public DataType visitDispatch(Expression expression) {
            expression.accept(this);
//            System.out.println(expression.type);
            print(expression.type);

            DataType type = resolveType(expression.type);

            // If a type is resolved to a matrix of 1x1,
            // then it can be resolved to the element type of the matrix type.
            if (type instanceof MatrixType) {

                MatrixType matrixType = (MatrixType) type;
//                System.out.println("TypeResolver$TheExpressionVisitor.visitDispatch");
//                System.out.println(matrixType);

                final Fun<TypeVar, Boolean> f1 = new Fun<TypeVar, Boolean>() {
                    public Boolean apply(TypeVar input) {
                        return false;
                    }
                };
                final Fun<Integer, Boolean> f2 = new Fun<Integer, Boolean>() {
                    public Boolean apply(Integer input) {
                        return (input == 1);
                    }
                };
                boolean bothOne = matrixType.n.apply(f1, f2);
                bothOne = bothOne && matrixType.m.apply(f1, f2);

                if (bothOne) {
                    type = matrixType.elemType.apply(
                        new Fun<ScalarType, DataType>() {
                            public DataType apply(ScalarType scalarType) {
                                return scalarType;
                            }
                        },
                        new Fun<TypeVar, DataType>() {
                            public DataType apply(TypeVar input) {
                                throw new RuntimeException();
                            }
                        }
                    );
                }
            }

            expression.type = type;
            println(" substituted with " + expression.type);
//            System.out.println(" substituted with " + expression.type);
            if (expression.type == null)
                throw new RuntimeException();
            return type;
        }

        public Object visit(Literal literal) {
            return null;
        }

        public Object visit(Op op) {
            Expression[] operands = op.getOperands();
            for (Expression operand : operands)
                expressionVisitor.visitDispatch(operand);

            if (op instanceof ArrayAccess) {
                ArrayAccess arrayAccess = (ArrayAccess) op;
                expressionVisitor.visitDispatch(arrayAccess.array);
            }
            if (op instanceof Call) {
                Call call = (Call) op;
                call.origType = resolveType(call.origType);
                if (call.origType != null) {
//                    System.out.print("converted origType " + call.origType);
                    call.origType = resolveType(call.origType);
//                    System.out.println("to " + call.origType);
                }

            //    expressionVisitor.visitDispatch(call.name);
            }
            if (op instanceof CallOrArrayAccess) {
                CallOrArrayAccess callOrArrayAccess = (CallOrArrayAccess) op;
                if (callOrArrayAccess.origType != null) {
//                    System.out.print("converted origType " + callOrArrayAccess.origType);
                    callOrArrayAccess.origType = resolveType(callOrArrayAccess.origType);
//                    System.out.println("to " + callOrArrayAccess.origType);
                }
                // If it is a call, prevent passing a null type.
                if (callOrArrayAccess.id.type != null)
                    expressionVisitor.visitDispatch(callOrArrayAccess.id);
                //System.out.println("This type is" + callOrArrayAccess.id.type);
            }

            return null;
        }

        public Object visit(Id id) {
            return null;
        }
    }
}

