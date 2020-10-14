package jsrc.x10.mhp;

import jsrc.x10.ast.tree.declaration.*;
import jsrc.x10.ast.tree.expression.*;
import jsrc.x10.ast.tree.expression.id.GId;
import jsrc.x10.ast.tree.expression.id.specialids.general.SErr;
import jsrc.x10.ast.tree.expression.id.specialids.general.SOut;
import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Dist;
import jsrc.x10.ast.tree.expression.literal.This;
import jsrc.x10.ast.tree.statement.*;
import jsrc.x10.ast.tree.statement.x10.Async;
import jsrc.x10.ast.tree.statement.x10.Finish;
import jsrc.x10.ast.tree.statement.x10.PointFor;
import jsrc.x10.ast.tree.statement.x10.X10Statement;
import jsrc.x10.ast.tree.type.DefinedType;
import jsrc.x10.ast.tree.type.Type;
import jsrc.x10.ast.visitor.DFSVisitor;
import lesani.compiler.constraintsolver.ConstConstraint;
import lesani.compiler.constraintsolver.SubsetConstraint;
import jsrc.x10.mhp.structureelicitor.ClassInf;
import jsrc.x10.mhp.structureelicitor.StructureElicitor;
import lesani.collection.Pair;
import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.compiler.typing.SymbolTable;
import lesani.compiler.typing.exception.DuplicateDefExc;
import lesani.compiler.typing.exception.SymbolNotFoundException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class ConstraintElicitor  extends DFSVisitor {
    private File file;
    private Map<String, ClassInf> classInfs;
    private Map<Pair<String, String>, VarTriple> functionVars = new HashMap<Pair<String, String>, VarTriple>();
    private SymbolTable<Type> symbolTable = new SymbolTable<Type>();

    public HashSet<ConstConstraint<Var, Statement>> level1Consts = new HashSet<ConstConstraint<Var, Statement>>();
    public HashSet<SubsetConstraint<Var>> level1subsets = new HashSet<SubsetConstraint<Var>>();
    //public HashSet<ConstConstraint<Pair<Statement, Statement>>> level2Consts = new HashSet<ConstConstraint<Pair<Statement, Statement>>>();
    public HashSet<SubsetConstraint<Var>> level2subsets = new HashSet<SubsetConstraint<Var>>();
    public HashSet<CrossConstraint> level2Crosses = new HashSet<CrossConstraint>();
    public Var mainVar;
    // We assume there is no method overloading.

    public ConstraintElicitor(File file) {
        this.file = file;
        StructureElicitor elicitor = new StructureElicitor(file);

        classInfs = elicitor.getClassInfs();

        for (String className : classInfs.keySet()) {
            ClassInf classInf = classInfs.get(className);
            for (String methodName : classInf.methods.keySet()) {
                VarTriple varTriple = new VarTriple(classInf.methods.get(methodName).block);
                functionVars.put(new Pair<String, String>(className, methodName), varTriple);
                // Should really check the type of the class to be a MainClass.
                if (methodName.equals("main"))
                    mainVar = varTriple.m;
            }
        }

        symbolTable.startScope();
        addGlobalsToSymbolTable();
    }

    private void addGlobalsToSymbolTable() {
        try {
            symbolTable.put(SOut.instance().lexeme, new DefinedType("SOut"));
            symbolTable.put(SErr.instance().lexeme, new DefinedType("SErr"));
            // Need to add support for static methods to imporove this.
            // Now, this says Math is an object of type "Math".
            symbolTable.put("Math", new DefinedType("Math"));
            symbolTable.put(Dist.instance().lexeme, new DefinedType("Dist"));
        } catch (DuplicateDefExc duplicateDefExc) { duplicateDefExc.printStackTrace(); }
    }

    public void elicit() {
        fileVisitor.visitDispatch(file);
    }

    {
        fileVisitor = new FileVisitor() {{
            classVisitor = new ClassVisitor() {
                public Object visitDispatch(ClassDecl classDecl) {
                    symbolTable.startScope();
                    try {
                        symbolTable.put("this", new DefinedType(classDecl.name.lexeme));
                    } catch (DuplicateDefExc e) { e.printStackTrace(); }
                    super.visitDispatch(classDecl);
                    symbolTable.endScope();
                    return null;
                }

                @Override
                public Object visit(Field field) {
                    try {
                        symbolTable.put(field.name.lexeme, field.type);
                    } catch (DuplicateDefExc duplicateDefExc) { duplicateDefExc.printStackTrace(); }
                    return null;
                }

                {

                methodVisitor = new MethodVisitor() {
                    public Object visitDispatch(Method method) {
                        symbolTable.startScope();
                        visit(method.formalParams);
                        String className = null;
                        try {
                            className = ((DefinedType)symbolTable.get("this")).name;
                        } catch (SymbolNotFoundException e) { e.printStackTrace(); }

                        VarTriple varTripleI = functionVars.get(new Pair<String, String>(className, method.name.lexeme));
                        Var mi = varTripleI.m; Var oi = varTripleI.o; Var li = varTripleI.l;
                        VarTriple varTripleS = (VarTriple)visit(method.block);
                        Var ms = varTripleS.m; Var os = varTripleS.o; Var ls = varTripleS.l;

                        SubsetConstraint c1 = new SubsetConstraint(mi, ms);
                        SubsetConstraint c2 = new SubsetConstraint(oi, os);
                        SubsetConstraint c3 = new SubsetConstraint(li, ls);

                        level2subsets.add(c1);

                        level1subsets.add(c2);
                        level1subsets.add(c3);

                        symbolTable.endScope();
                        return null;
                    }
                };

                shared = new Shared() {
                    
                    public Object visit(FormalParam formalParam) {
                        try {
                            symbolTable.put(formalParam.name.lexeme, formalParam.type);
                        } catch (DuplicateDefExc duplicateDefExc) { duplicateDefExc.printStackTrace(); }
                        return null;
                    }

                    public Object visit(VarDecl varDecl) {
                        try {
                            symbolTable.put(varDecl.name.lexeme, varDecl.type);
                        } catch (DuplicateDefExc duplicateDefExc) { duplicateDefExc.printStackTrace(); }
                        return null;
                    }

                    @Override
                    public Object visit(GId id) {
                        try {
                            return symbolTable.get(id.lexeme);
                        } catch (SymbolNotFoundException e) {
//                            System.out.println("Id not found in the symbol table: " + id.lexeme);
                            return null;
                        }
                    }

                    private MethodCall inMethodCall;
                    private VarTriple inMethodCallVarTriple;


                    {
                        statementVisitor = new StatementVisitor() {
                            
                            public Object visitDispatch(Statement statement) {
                                Var ms; Var os; Var ls;
                                if (
                                    (!(statement instanceof Block)) &&
                                    (!(statement instanceof If)) &&
                                    (!(statement instanceof While)) &&
                                    (!(statement instanceof ExpSt)) &&
                                    (!(statement instanceof X10Statement))
                                ) {
                                    ms = new Var(statement); os = new Var(statement); ls = new Var(statement);
                                    inMethodCall = null;
                                    super.visitDispatch(statement);
                                    if (inMethodCall != null) {
                                        // In the benchmarks, there is at most one method call in each statement.
                                        // We have the method call and then this statement: St = f(); StRest
                                        Var mi = inMethodCallVarTriple.m;
                                        Var oi = inMethodCallVarTriple.o;
                                        Var li = inMethodCallVarTriple.l;

                                        Var m = new Var(statement); Var o = new Var(statement); Var l = new Var(statement);

                                        SubsetConstraint c1 = new SubsetConstraint(m, mi);
                                        SubsetConstraint c2 = new SubsetConstraint(m, ms);
                                        CrossConstraint c3 = new CrossConstraint(m, oi, ls);
                                        CrossConstraint c4 = new CrossConstraint(m, ls, oi);

                                        SubsetConstraint c5 = new SubsetConstraint(o, oi);
                                        SubsetConstraint c6 = new SubsetConstraint(o, os);

                                        SubsetConstraint c7 = new SubsetConstraint(l, li);
                                        SubsetConstraint c8 = new SubsetConstraint(l, ls);

                                        level2subsets.add(c1);
                                        level2subsets.add(c2);
                                        level2Crosses.add(c3);
                                        level2Crosses.add(c4);

                                        level1subsets.add(c5);
                                        level1subsets.add(c6);
                                        level1subsets.add(c7);
                                        level1subsets.add(c8);

                                        ms = m; os = o; ls = l;
                                    }
                                } else {
                                    VarTriple sVarTriple = (VarTriple)super.visitDispatch(statement);
                                    ms = sVarTriple.m; os = sVarTriple.o; ls = sVarTriple.l;
                                }
                                if (!(statement instanceof Block)) {
                                    HashSet<Statement> set = new HashSet<Statement>();
                                    set.add(statement);
                                    ConstConstraint<Var, Statement> c = new ConstConstraint<Var, Statement>(ls, set);
                                    level1Consts.add(c);
                                }
                                return new VarTriple(ms, os, ls);
                            }

                            @Override
                            public Object visit(Block block) {
                                symbolTable.startScope();
                                VarDeclOrSt[] varDeclOrSts = block.statements;
                                Var ms2 = new Var(null); Var os2 = new Var(null); Var ls2 = new Var(null);
                                for (int i = varDeclOrSts.length - 1; i >= 0 ; i--) {
                                    VarDeclOrSt varDeclOrSt = varDeclOrSts[i];
                                    Statement statement;

                                    if (varDeclOrSt instanceof VarDecl) {
                                        VarDecl varDecl = (VarDecl)varDeclOrSt;
                                        shared.visit(varDecl);
                                        if (varDecl.expression.isPresent()) {
                                            statement = new Assignment(varDecl.name, varDecl.expression.value());
                                            statement.setLoc(varDecl.sourceLoc);
                                        } else
                                            continue;
                                    } else {
                                        statement = (Statement) varDeclOrSt;
                                    }

                                    VarTriple varTriple = (VarTriple)visitDispatch(statement);
                                    Var ms1 = varTriple.m; Var os1 = varTriple.o; Var ls1 = varTriple.l;
                                    Var m = new Var(statement, true); Var o = new Var(statement, true); Var l = new Var(statement, true);
                                    SubsetConstraint c1 = new SubsetConstraint(m, ms1);
                                    SubsetConstraint c2 = new SubsetConstraint(m, ms2);
                                    CrossConstraint c3 = new CrossConstraint(m, os1, ls2);
                                    CrossConstraint c4 = new CrossConstraint(m, ls2, os1);

                                    SubsetConstraint c5 = new SubsetConstraint(o, os1);
                                    SubsetConstraint c6 = new SubsetConstraint(o, os2);

                                    SubsetConstraint c7 = new SubsetConstraint(l, ls1);
                                    SubsetConstraint c8 = new SubsetConstraint(l, ls2);

                                    level2subsets.add(c1);
                                    level2subsets.add(c2);
                                    level2Crosses.add(c3);
                                    level2Crosses.add(c4);

                                    level1subsets.add(c5);
                                    level1subsets.add(c6);
                                    level1subsets.add(c7);
                                    level1subsets.add(c8);

                                    ms2 = m; os2 = o; ls2 = l;

                                }
                                symbolTable.endScope();
                                return new VarTriple(ms2, os2, ls2);
                            }

                            @Override
                            public Object visit(final If theIf) {
                                final VarTriple ifVarTriple = (VarTriple)visitDispatch(theIf.ifStatement);
                                return theIf.elseStatement.apply(
                                    new Fun0<VarTriple>() {
                                        public VarTriple apply() {
                                            return ifVarTriple;
                                        }
                                    },
                                    new Fun<Statement, VarTriple>() {
                                        public VarTriple apply(Statement statement) {
                                            Var m1 = ifVarTriple.m;
                                            Var o1 = ifVarTriple.o;
                                            Var l1 = ifVarTriple.l;
                                            Var m = new Var(theIf, true); Var o = new Var(theIf, true); Var l = new Var(theIf, true);
                                            VarTriple elseVarTriple = (VarTriple)visitDispatch(statement);
                                            Var m2 = elseVarTriple.m;
                                            Var o2 = elseVarTriple.o;
                                            Var l2 = elseVarTriple.l;

                                            SubsetConstraint c1 = new SubsetConstraint(m, m1);
                                            SubsetConstraint c2 = new SubsetConstraint(m, m2);

                                            SubsetConstraint c3 = new SubsetConstraint(o, o1);
                                            SubsetConstraint c4 = new SubsetConstraint(o, o2);

                                            SubsetConstraint c5 = new SubsetConstraint(l, l1);
                                            SubsetConstraint c6 = new SubsetConstraint(l, l2);

                                            level2subsets.add(c1);
                                            level2subsets.add(c2);

                                            level1subsets.add(c3);
                                            level1subsets.add(c4);
                                            level1subsets.add(c5);
                                            level1subsets.add(c6);

                                            return new VarTriple(m, o, l);
                                        }
                                    }
                                );
                            }

                            @Override
                            public Object visit(While theWhile) {
                                return visitALoop(theWhile.statement);
                            }

                            public Object visitALoop(Statement statement) {
                                VarTriple sVarTriple = (VarTriple) visitDispatch(statement);
                                Var ms = sVarTriple.m; Var os = sVarTriple.o; Var ls = sVarTriple.l;
                                Var m = new Var(statement, true); Var o = new Var(statement, true); Var l = new Var(statement, true);
                                SubsetConstraint c1 = new SubsetConstraint(m, ms);
                                CrossConstraint c2 = new CrossConstraint(m, os, ls);
                                CrossConstraint c3 = new CrossConstraint(m, ls, os);

                                SubsetConstraint c4 = new SubsetConstraint(o, os);
                                SubsetConstraint c5 = new SubsetConstraint(l, ls);

                                level2subsets.add(c1);
                                level2Crosses.add(c2);
                                level2Crosses.add(c3);

                                level1subsets.add(c4);
                                level1subsets.add(c5);

                                return new VarTriple(m, o, l);
                            }

                            @Override
                            public Object visit(ExpSt expSt) {
                                // In the benchmarks, we do not have ExpSts other than method calls.
                                // In the benchmarks, there is at most one method call in each statement.
                                inMethodCall = null;
                                expressionVisitor.visitDispatch(expSt.expression);
                                if (inMethodCall != null) {
                                    Var mi = inMethodCallVarTriple.m;
                                    Var oi = inMethodCallVarTriple.o;
                                    Var li = inMethodCallVarTriple.l;
                                    Var m = new Var(expSt); Var o = new Var(expSt); Var l = new Var(expSt);
                                    SubsetConstraint c1 = new SubsetConstraint(m, mi);
                                    SubsetConstraint c2 = new SubsetConstraint(o, oi);
                                    SubsetConstraint c3 = new SubsetConstraint(l, li);
                                    level2subsets.add(c1);
                                    level1subsets.add(c2);
                                    level1subsets.add(c3);
                                    return new VarTriple(m, o, l);
                                } else {
//                                    System.out.println("Non-interesting ExpSt: " + expSt.expression);
//                                    throw new RuntimeException();
                                    return new VarTriple(expSt);
                                }
                            }

                            {
                                x10StatementVisitor = new X10StatementVisitor() {
                                    
                                    public Object visit(Async async) {
                                        Var m = new Var(async, true); Var o = new Var(async, true); Var l = new Var(async, true);
                                        VarTriple sVarTriple = (VarTriple)statementVisitor.visitDispatch(async.statement);
                                        Var ms = sVarTriple.m;
                                        Var os = sVarTriple.o;
                                        Var ls = sVarTriple.l;

                                        SubsetConstraint c1 = new SubsetConstraint(m, ms);
                                        SubsetConstraint c2 = new SubsetConstraint(o, ls);
                                        SubsetConstraint c3 = new SubsetConstraint(l, ls);

                                        level2subsets.add(c1);

                                        level1subsets.add(c2);
                                        level1subsets.add(c3);

                                        return new VarTriple(m, o, l);
                                    }

                                    
                                    public Object visit(Finish finish) {
                                        Var m = new Var(finish, true); Var o = new Var(finish, true); Var l = new Var(finish, true);
                                        VarTriple sVarTriple = (VarTriple)statementVisitor.visitDispatch(finish.statement);
                                        Var ms = sVarTriple.m;
                                        Var os = sVarTriple.o;
                                        Var ls = sVarTriple.l;
                                        HashSet<Statement> phi = new HashSet<Statement>();
                                        SubsetConstraint c1 = new SubsetConstraint(m, ms);
                                        ConstConstraint<Var, Statement> c2 = new ConstConstraint<Var, Statement>(o, phi);
                                        SubsetConstraint c3 = new SubsetConstraint(l, ls);

                                        level2subsets.add(c1);

                                        level1Consts.add(c2);
                                        level1subsets.add(c3);

                                        return new VarTriple(m, o, l);
                                    }

                                    @Override
                                    public Object visit(PointFor theFor) {
                                        return visitALoop(theFor.statement);
                                    }
                                };
                            }
                        };

                        expressionVisitor = new ExpressionVisitor() {
                            
                            public Object visit(New theNew) {
                                // In the benchmarks, there is no code for constructors.
                                // Otherwise, we had to consider New expressions as method calls
                                // to constructors.
                                return theNew.type;
                            }

                            public Object visit(MethodCall methodCall) {
                                Expression receiver = methodCall.receiver.apply(
                                    new Fun0<Expression>() {
                                        public Expression apply() {
                                            return This.instance();
                                        }
                                    },
                                    new Fun<Expression, Expression>() {
                                        public Expression apply(Expression input) {
                                            return input;
                                        }
                                    }
                                );
                                Type receiverType = (Type)visitDispatch(receiver);
                                if (receiverType instanceof DefinedType) {
                                    DefinedType definedType = (DefinedType) receiverType;
                                    String className = definedType.name;
                                    String methodName = methodCall.methodName.lexeme;
                                    if (classInfs.containsKey(className)) {
                                        VarTriple varTriple = functionVars.get(new Pair<String, String>(className, methodName));
                                        inMethodCall = methodCall;
                                        inMethodCallVarTriple = varTriple;
                                    }
                                }
                                // As in the benchmarks, a method call does not happen in the receiver
                                // expression of another method call, we do not need to return a type here.
                                return null;
                            }

                            {
                                literalVisitor = new LiteralVisitor() {
                                    
                                    public Object visit(This theThis) {
                                        try {
                                            return symbolTable.get("this");
                                        } catch (SymbolNotFoundException e) { e.printStackTrace(); return null; }
                                    }
                                };
                            }
                        };
                    }
                };
            }};
        }};
    }
}



// Field selection and method call in statements
// MapReduce:
// on this, new and println
// MapReduce:
// on this, new, Math, println
// testArray.distribution; testArray is a field array
// dist.factory.block(R);
// dist.UNIQUE;
// pl.isFirst() // pl is a local var of type place


/*
// Extra Code
                            public Object visit(Cast cast) {
                                return cast.type;
                            }


                            public Object visit(Conditional conditional) {
                                return visitDispatch(conditional.ifExp);
                            }


                            public Object visit(Parenthesized parenthesized) {
                                return visitDispatch(parenthesized.expression);
                            }


*/