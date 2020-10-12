package jsrc.x10.ast.visitor;

import jsrc.x10.ast.tree.declaration.Method;
import jsrc.x10.ast.tree.declaration.others.IsFinal;
import jsrc.x10.ast.tree.declaration.others.IsStatic;
import jsrc.x10.ast.tree.expression.id.GId;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.expression.id.specialids.SpecialId;
import jsrc.x10.ast.tree.expression.id.specialids.general.*;
import jsrc.x10.ast.tree.expression.id.specialids.x10.SX10Id;
import jsrc.x10.ast.tree.expression.op.logical.*;
import jsrc.x10.ast.tree.expression.x10.sx10lib.*;
import jsrc.x10.ast.tree.expression.id.specialids.x10.array.properties.Distribution;
import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.*;
import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.System;
import jsrc.x10.ast.tree.expression.id.specialids.x10.dist.methods.BlockCyclicFactory;
import jsrc.x10.ast.tree.expression.id.specialids.x10.dist.methods.BlockFactory;
import jsrc.x10.ast.tree.expression.id.specialids.x10.dist.methods.UniqueFactory;
import jsrc.x10.ast.tree.expression.id.specialids.x10.object.Equals;
import jsrc.x10.ast.tree.expression.id.specialids.x10.place.methods.*;
import jsrc.x10.ast.tree.expression.id.specialids.x10.place.properties.FirstPlace;
import jsrc.x10.ast.tree.expression.id.specialids.x10.place.properties.MaxPlaces;
import jsrc.x10.ast.tree.expression.id.specialids.x10.region.methods.*;
import jsrc.x10.ast.tree.expression.id.specialids.x10.system.methods.CurrentTime;
import jsrc.x10.ast.tree.expression.x10.*;
import jsrc.x10.ast.tree.expression.xtras.Parenthesized;
import jsrc.x10.ast.tree.statement.x10.Async;
import jsrc.x10.ast.tree.statement.x10.Finish;
import jsrc.x10.ast.tree.statement.x10.PointFor;
import jsrc.x10.ast.tree.statement.x10.X10Statement;
import jsrc.x10.ast.tree.statement.x10.parts.Coordinates;
import jsrc.x10.ast.tree.statement.x10.parts.IdCoordinates;
import jsrc.x10.ast.tree.statement.x10.parts.PointFormalVar;
import jsrc.x10.ast.tree.type.*;
import jsrc.x10.ast.tree.declaration.*;
import jsrc.x10.ast.tree.statement.*;
import jsrc.x10.ast.tree.expression.*;
import jsrc.x10.ast.tree.expression.op.UnaryOp;
import jsrc.x10.ast.tree.expression.op.BinaryOp;
import jsrc.x10.ast.tree.expression.op.math.*;
import jsrc.x10.ast.tree.expression.op.relational.*;
import jsrc.x10.ast.tree.expression.literal.*;
import jsrc.x10.ast.tree.type.constraint.Constraint;
import jsrc.x10.ast.tree.type.constraint.DistConstraint;
import jsrc.x10.ast.tree.type.constraint.RankConstraint;
import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.collection.option.Option;
import lesani.collection.option.Some;

import lesani.compiler.texttree.seq.TextSeq;

public class CPSPrinter {
    TextSeq seq;

    public CPSPrinter() {
        seq = new TextSeq();
    }
    public Object visitDispatch(File file) {
        return visit(file);
    }

    public Object visit(File file) {
        return fileVisitor.visitDispatch(file);
    }

    public FileVisitor fileVisitor = new FileVisitor();
    public class FileVisitor {
        public Object visitDispatch(File file) {
            ClassDecl[] classDecls = file.classDecls;
            for (ClassDecl classDecl : classDecls)
                classVisitor.visitDispatch(classDecl);
            return null;
        }

        //Todo: add whole class definition later
        public ClassVisitor classVisitor = new ClassVisitor();
//        public class ClassVisitor {
//            public Object visitDispatch(ClassDecl classDecl) {
//                visit(classDecl.visibility);
//                visit(classDecl.name);
//                for (Field field : classDecl.fields)
//                    visit(field);
//                for (Constructor constructor : classDecl.constructors)
//                    visit(constructor);
//                for (Method method : classDecl.methods)
//                    visit(method);
//                return null;
//            }
        public class ClassVisitor {
            public Object visitDispatch(ClassDecl classDecl) {
                visit(classDecl.name);
//                for (Field field : classDecl.fields)
//                    visit(field);
//                for (Constructor constructor : classDecl.constructors)
//                    visit(constructor);
//                for (Method method : classDecl.methods)
//                    visit(method);
                for (Method oMethod : classDecl.methods)
                    visit(oMethod);
                return null;
            }

//            public Object visit(Visibility visibility) { return shared.visit(visibility); }
            //public Object visit(IsStatic isStatic) { return enclosing.visit(isStatic); }
            //isValue
            public Object visit(Id name) { return shared.visit(name); }
            public Object visit(Field field) { return fieldVisitor.visitDispatch(field); }
            public Object visit(Constructor constructor) { return constructorVisitor.visitDispatch(constructor); }
            public Object visit(Method method) { return methodVisitor.visitDispatch(method); }

            public FieldVisitor fieldVisitor =  new FieldVisitor();
            public class FieldVisitor {
                public Object visitDispatch(Field field) {
//                    visit(field.visibility);
//                    visit(new IsStatic(field.isStatic));
//                    visit(new IsFinal(field.isFinal));
                    visit(field.type);
                    visit(field.name);
                    field.initExpression.apply(new Fun0<Object>() {
                        public Object apply() {
                            return null;
                        }
                    }, new Fun<Expression, Object>() {
                        public Object apply(Expression expression) {
                            return visit(expression);
                        }
                    });
                    return null;
                }
//                public Object visit(Visibility visibility) { return ClassVisitor.this.shared.visit(visibility); }
//                public Object visit(IsStatic isStatic) { return ClassVisitor.this.shared.visit(isStatic); }
//                public Object visit(IsFinal isFinal) { return ClassVisitor.this.shared.visit(isFinal); }
                public Object visit(NonVoidType type) { return ClassVisitor.this.shared.visit(type); }
                public Object visit(Id name) { return ClassVisitor.this.shared.visit(name); }
                public Object visit(Expression expression) {
                    ExpSt expSt = new ExpSt(expression);
                    //Todo: Set location of expression for ExpSt. (should we?)
                    return ClassVisitor.this.shared.statementVisitor.visitDispatch(expSt);
                }
            }

            public ConstructorVisitor constructorVisitor =  new ConstructorVisitor();
            public class ConstructorVisitor {
                public Object visitDispatch(Constructor constructor) {
//                    visit(constructor.visibility);
                    visit(constructor.formalParams);
                    visit(constructor.block);
                    return null;
                }

//                public Object visit(Visibility visibility) { return ClassVisitor.this.visit(visibility); }
                public Object visit(FormalParam[] formalParams) {
                    for (FormalParam formalParam : formalParams) {
                        ClassVisitor.this.shared.visit(formalParam);
                    }
                    return null;
                }
                public Object visit(Block block) { return ClassVisitor.this.shared.statementVisitor.visitDispatch(block); }
                // If someone wants to treat the bock of constructors different from blocks of methods,
                // he should write a visitor at this point and override visit(Block) to call that.
            }

            public MethodVisitor methodVisitor =  new MethodVisitor();
            public class MethodVisitor {
                public Object visitDispatch(Method method) {
//                    visit(method.visibility);
//                    visit(new IsStatic(method.isStatic));
//                    visit(new IsFinal(method.isFinal));
                    visit(method.type);
                    visit(method.name);
                    visit(method.formalParams);
                    visit(method.block);
                    return null;
                }

//                public Object visit(Visibility visibility) { return ClassVisitor.this.visit(visibility); }
//                //public boolean isProto = false;
//                public Object visit(IsStatic isStatic) { return ClassVisitor.this.shared.visit(isStatic); }
//                public Object visit(IsFinal isFinal) { return ClassVisitor.this.shared.visit(isFinal); }
                public Object visit(Type type) { return ClassVisitor.this.shared.visit(type); }
                public Object visit(jsrc.x10.ast.tree.expression.id.Id name) { return ClassVisitor.this.shared.visit(name); }
                public Object visit(Option<FormalParam[]> formalParams) {
                    formalParams.apply(
                            new Fun0<Object>() {
                                public Object apply() {
                                    return null;
                                }
                            },
                            new Fun<FormalParam[], Object>() {
                                public Object apply(FormalParam[] input) {
                                    for (FormalParam formalParam : input) {
                                        ClassVisitor.this.shared.visit(formalParam);
                                    }
                                    return null;
                                }
                            }
                    );
                    return null;
                }
                public Object visit(Block block) { return ClassVisitor.this.shared.statementVisitor.visitDispatch(block); }
            }

            public Shared shared = new Shared();
            public class Shared {
//                public Object visit(Visibility visibility) { return null; }
//                public Object visit(IsStatic isStatic) { return null; }
//                public Object visit(IsFinal isFinal) { return null; }
                public Object visit(GId name) { return gIdVisitor.visitDispatch(name); }

                public GIdVisitor gIdVisitor = new GIdVisitor();
                public class GIdVisitor {
                    public Object visitDispatch(GId gId) {
                        //return gId.accept(this);
                        //todo: change this to expression Gid visitor
                        return null;
                    }

                    public Object visit(Id id) { return null; }
//                    public Object visit(SpecialId specialId) { return specialIdVisitor.visitDispatch(specialId); }
//
//                    public SpecialIdVisitor specialIdVisitor = new SpecialIdVisitor();
//                    public class SpecialIdVisitor {
//                        public Object visitDispatch(SpecialId specialId) {
//                            return specialId.accept(this);
//                        }
//
//                        public Object visit(GSpecialId gSpecialId) { return gSpecialIdVisitor.visitDispatch(gSpecialId); }
//                        public Object visit(SX10Id sx10Id) { return x10SpecialIdVisitor.visitDispatch(sx10Id); }
//
//                        public GSpecialIdVisitor gSpecialIdVisitor = new GSpecialIdVisitor();
//                        public class GSpecialIdVisitor {
//                            public Object visitDispatch(GSpecialId gSpecialId) {
//                                return gSpecialId.accept(this);
//                            }
//
//                            public Object visit(PrintId printId) { return null; }
//                            public Object visit(PrintlnId printlnId) { return null; }
//                            public Object visit(IntegerSize integerSize) { return null; }
//
//                            public Object visit(SOut sOut) { return null; }
//                            public Object visit(SErr sErr) { return null; }
//                        }
//
//                        public SX10IdVisitor x10SpecialIdVisitor = new SX10IdVisitor();
//                        public class SX10IdVisitor {
//                            public Object visitDispatch(SX10Id sx10Id) {
//                                return sx10Id.accept(this);
//                            }
//
//                            // -----------------------------
//                            public Object visit(Equals equals) { return null; }
//                            // -----------------------------
//                            public Object visit(Place place) { return null; }
//                            // -----------
//                            public Object visit(IsFirst isFirst) { return null; }
//                            public Object visit(IsLast isLast) { return null; }
//                            public Object visit(Next next) { return null; }
//                            public Object visit(Prev prev) { return null; }
//                            public Object visit(Places places) { return null; }
//                            // -----------
//                            public Object visit(FirstPlace firstPlace) { return null; }
//                            public Object visit(MaxPlaces maxPlaces) { return null; }
//                            public Object visit(jsrc.x10.ast.tree.expression.id.specialids.x10.place.properties.Id id) { return null; }
//                            // -----------------------------
//                            public Object visit(Distribution distribution) { return null; }
//                            // -----------------------------
//                            public Object visit(Dist dist) { return null; }
//                            // -----------
//                            public Object visit(BlockFactory blockFactory) { return null; }
//                            public Object visit(BlockCyclicFactory blockCyclicFactory) { return null; }
//                            public Object visit(UniqueFactory uniqueFactory) { return null; }
//                            // -----------------------------
//                            public Object visit(jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Region region) { return null; }
//                            // -----------
//                            public Object visit(EmptyFactory emptyFactory) { return null; }
//                            public Object visit(High high) { return null; }
//                            public Object visit(Low low) { return null; }
//                            public Object visit(Size size) { return null; }
//                            public Object visit(Projection projection) { return null; }
//                            public Object visit(Contains contains) { return null; }
//                            // -----------
//                            public Object visit(jsrc.x10.ast.tree.expression.id.specialids.x10.region.properties.Region region) { return null; }
//                            // -----------------------------
//                            public Object visit(System system) { return null; }
//                            // -----------
//                            public Object visit(CurrentTime currentTime) { return null; }
//                            // -----------------------------
//                        }
//                    }
                }


                public Object visit(Type type) { return typeVisitor.visitDispatch(type); }

                public Object visit(FormalParam formalParam) {
                    shared.visit(formalParam.name);
                    return typeVisitor.visitDispatch(formalParam.type);
                }

                public Object visit(VarDecl varDecl) {
//                    visit(new IsFinal(varDecl.isConstant));
                    shared.visit(varDecl.name);
                    return typeVisitor.visitDispatch(varDecl.type);
                }

                public StatementVisitor statementVisitor =  new StatementVisitor();
                public class StatementVisitor implements jsrc.x10.ast.visitor.StatementVisitor {
                    public Object visitDispatch(Statement statement) {
                        return statement.accept(this);
                    }

                    public Object visit(Assignment assignment) {
                        expressionVisitor.visitDispatch(assignment.left);
                        return expressionVisitor.visitDispatch(assignment.right);
                    }

                    public Object visit(ExpSt expSt) {
                        return expressionVisitor.visitDispatch(expSt.expression);
                    }
                    public Object visit(Throw theThrow) {
                        return expressionVisitor.visitDispatch(theThrow.arg);
                    }
                    public Object visit(ValueReturn valueReturn) {
                        return expressionVisitor.visitDispatch(valueReturn.arg);
                    }
                    public Object visit(VoidReturn voidReturn) { return null; }

                    public Object visit(If theIf) {
                        expressionVisitor.visitDispatch(theIf.condition);
                        visitDispatch(theIf.ifStatement);
                        theIf.elseStatement.apply(
                                new Fun0<Object>() {
                                    public Object apply() {
                                        return null;
                                    }
                                },
                                new Fun<Statement, Object>() {
                                    public Object apply(Statement statement) {
                                        return visitDispatch(statement);
                                    }
                                }
                        );
                        return null;
                    }

//                    public Object visit(While theWhile) {
//                        Expression condition = theWhile.condition;
//                        Statement statement = theWhile.statement;
//                        expressionVisitor.visitDispatch(condition);
//                        visitDispatch(statement);
//                        return null;
//                    }
//
//                    public Object visit(DoWhile doWhile) {
//                        Statement statement = doWhile.statement;
//                        Expression condition = doWhile.condition;
//                        visitDispatch(statement);
//                        expressionVisitor.visitDispatch(condition);
//                        return null;
//                    }
//
//                    public Object visit(Break theBreak) {
//                        return null;
//                    }
//
//                    public Object visit(Continue theContinue) {
//                        return null;
//                    }
//
//                    public Object visit(Switch theSwitch) {
//                        Expression selector = theSwitch.selector;
//                        Switch.Case[] cases = theSwitch.cases;
//                        Option<VarDeclOrSt[]> defaultBlockStatements = theSwitch.defaultBlockStatements;
//                        expressionVisitor.visitDispatch(selector);
//                        for (Switch.Case aCase : cases) {
//                            Expression guard = aCase.guard;
//                            VarDeclOrSt[] varDeclOrSts = aCase.statements;
//                            expressionVisitor.visitDispatch(guard);
//                            for (VarDeclOrSt varDeclOrSt : varDeclOrSts) {
//                                if (varDeclOrSt instanceof VarDecl) {
//                                    final VarDecl varDecl = (VarDecl) varDeclOrSt;
//                                    Shared.this.visit(varDecl);
//                                    varDecl.expression.apply(
//                                            new Fun0<Object>() {
//                                                public Object apply() {
//                                                    return null;
//                                                }
//                                            },
//                                            new Fun<Expression, Object>() {
//                                                public Object apply(Expression expression) {
//                                                    Assignment assignment = new Assignment(varDecl.name, expression);
//                                                    return statementVisitor.visitDispatch(assignment);
//                                                }
//                                            }
//                                    );
//                                }
//                                else {
//                                    Statement statement = (Statement) varDeclOrSt;
//                                    statementVisitor.visitDispatch(statement);
//                                }
//                            }
//                        }
//                        if (defaultBlockStatements.isPresent()) {
//                            VarDeclOrSt[] sts = (((Some<VarDeclOrSt[]>) defaultBlockStatements)).get();
//                            for (VarDeclOrSt varDeclOrSt : sts) {
//                                if (varDeclOrSt instanceof VarDecl) {
//                                    final VarDecl varDecl = (VarDecl) varDeclOrSt;
//                                    Shared.this.visit(varDecl);
//                                    varDecl.expression.apply(
//                                            new Fun0<Object>() {
//                                                public Object apply() {
//                                                    return null;
//                                                }
//                                            },
//                                            new Fun<Expression, Object>() {
//                                                public Object apply(Expression expression) {
//                                                    Assignment assignment = new Assignment(varDecl.name, expression);
//                                                    return statementVisitor.visitDispatch(assignment);
//                                                }
//                                            }
//                                    );
//                                }
//                                else {
//                                    Statement statement = (Statement) varDeclOrSt;
//                                    statementVisitor.visitDispatch(statement);
//                                }
//                            }
//                        }
//                        return null;
//                    }

                    public Object visit(Block block) {
                        VarDeclOrSt[] varDeclOrSts = block.statements;
                        for (VarDeclOrSt varDeclOrSt : varDeclOrSts) {
                            if (varDeclOrSt instanceof VarDecl) {
                                final VarDecl varDecl = (VarDecl) varDeclOrSt;
                                Shared.this.visit(varDecl);
                                varDecl.expression.apply(
                                        new Fun0<Object>() {
                                            public Object apply() {
                                                return null;
                                            }
                                        },
                                        new Fun<Expression, Object>() {
                                            public Object apply(Expression expression) {
                                                Assignment assignment = new Assignment(varDecl.name, expression);
                                                return statementVisitor.visitDispatch(assignment);
                                            }
                                        }
                                );
                            }
                            else {
                                Statement statement = (Statement) varDeclOrSt;
                                statementVisitor.visitDispatch(statement);
                            }
                        }
                        return null;
                    }

//                    public Object visit(X10Statement x10Statement) {
//                        return x10StatementVisitor.visitDispatch(x10Statement);
//                    }
//
//                    public X10StatementVisitor x10StatementVisitor =  new X10StatementVisitor();
//                    public class X10StatementVisitor {
//                        public Object visitDispatch(X10Statement statement) {
//                            return statement.accept(this);
//                        }
//                        public Object visit(Async async) {
//                            expressionVisitor.visitDispatch(async.place);
//                            return statementVisitor.visitDispatch(async.statement);
//                        }
//                        public Object visit(Finish finish) {
//                            return statementVisitor.visitDispatch(finish.statement);
//                        }
//
//                        public Object visit(PointFor theFor) {
//                            theFor.elementType.apply(
//                                    new Fun0<Object>() {
//                                        public Object apply() {
//                                            return null;
//                                        }
//                                    },
//                                    new Fun<Type, Object>() {
//                                        public Object apply(Type elementType) {
//                                            typeVisitor.visitDispatch(elementType);
//                                            return null;
//                                        }
//                                    }
//                            );
//                            Expression aggregate = theFor.aggregate;
//                            Statement statement = theFor.statement;
//                            pointFormalVarVisitor.visitDispatch(theFor.pointFormalVar);
//                            expressionVisitor.visitDispatch(aggregate);
//                            statementVisitor.visitDispatch(statement);
//                            return null;
//                        }

//                        public PointFormalVarVisitor pointFormalVarVisitor =  new PointFormalVarVisitor();
//                        public class PointFormalVarVisitor {
//                            public Object visitDispatch(PointFormalVar pointFormalVar) {
//                                return pointFormalVar.accept(this);
//                            }
//                            public Object visit(jsrc.x10.ast.tree.statement.x10.parts.Id id) {
//                                return shared.visit(id.name);
//                            }
//                            public Object visit(Coordinates coordinates) {
//                                for (jsrc.x10.ast.tree.expression.id.Id id : coordinates.ids)
//                                    shared.visit(id);
//                                return null;
//                            }
//                            public Object visit(IdCoordinates idCoordinates) {
//                                visit(idCoordinates.id);
//                                return visit(idCoordinates.coordinates);
//                            }
//                        }
                    }
                }

                public ExpressionVisitor expressionVisitor =  new ExpressionVisitor();
                public class ExpressionVisitor {
                    public Object visitDispatch(Expression expression) {
                        return expression.accept(this);
                    }

                    public Object visit(GId id) {
                        return gIdVisitor.visitDispatch(id);
                    }

                    public Object visit(Literal literal) {
                        return literalVisitor.visitDispatch(literal);
                    }

                    // Todo: Later, I need to change the categories of operations
                    // from unary and binay to relational, math and ... .
//                    public Object visit(UnaryOp unaryOp) {
//                        return unaryOpVisitor.visitDispatch(unaryOp);
//                    }

                    public Object visit(BinaryOp binaryOp) {
                        return binaryOpVisitor.visitDispatch(binaryOp);
                    }

//                    public Object visit(New theNew) {
//                        Type type = theNew.type;
//                        Expression[] args = theNew.args;
//                        typeVisitor.visitDispatch(type);
//                        for (Expression arg : args) {
//                            expressionVisitor.visitDispatch(arg);
//                        }
//                        return null;
//                    }

//                    public Object visit(FieldSelection fieldSelection) {
//                        Expression object = fieldSelection.receiver;
//                        GId fieldName = fieldSelection.fieldName;
//                        expressionVisitor.visitDispatch(object);
//                        shared.visit(fieldName);
//                        return null;
//                    }

//                    public Object visit(MethodCall methodCall) {
//                        Option<Expression> optionalReceiver = methodCall.receiver;
//                        GId methodName = methodCall.methodName;
//                        Expression[] args = methodCall.args;
//                        if (optionalReceiver.isPresent()) {
//                            Expression receiver = ((Some<Expression>)optionalReceiver).get();
//                            expressionVisitor.visitDispatch(receiver);
//                        }
//                        shared.visit(methodName);
//                        for (Expression arg : args) {
//                            expressionVisitor.visitDispatch(arg);
//                        }
//                        return null;
//                    }

                    public Object visit(ObjectMethod objectMethod) {
                        GId objectName = objectMethod.objectName;
                        GId methodName = objectMethod.methodName;
                        Expression[] args = objectMethod.args;

                        expressionVisitor.visitDispatch(objectName);
                        expressionVisitor.visit(methodName);
                        for (Expression arg : args) {
                            expressionVisitor.visitDispatch(arg);
                        }
                        return null;
                    }

//                    public Object visit(Cast cast) {
//                        NonVoidType type = cast.type;
//                        Expression operand = cast.operand;
//                        typeVisitor.visitDispatch(type);
//                        expressionVisitor.visitDispatch(operand);
//                        return null;
//                    }

                    public Object visit(Conditional conditional) {
                        Expression condition = conditional.condition;
                        Expression ifExp = conditional.ifExp;
                        Expression elseExp = conditional.elseExp;
                        expressionVisitor.visitDispatch(condition);
                        expressionVisitor.visitDispatch(ifExp);
                        expressionVisitor.visitDispatch(elseExp);
                        return null;
                    }

                    public Object visit(Sequence sequence) {
                        Expression Exp1 = sequence.exp1;
                        Expression Exp2 = sequence.exp2;
                        expressionVisitor.visitDispatch(Exp1);
                        expressionVisitor.visitDispatch(Exp2);
                        return null;
                    }

                    public Object visit(Var var) {
                        return null;
                    }

//                    public Object visit(Parenthesized parenthesized) {
//                        return expressionVisitor.visitDispatch(parenthesized.expression);
//                    }

//                    public Object visit(X10Expression expression) {
//                        return x10ExpressionVisitor.visitDispatch(expression);
//                    }
//                    public X10ExpressionVisitor x10ExpressionVisitor =  new X10ExpressionVisitor();
//
//                    public class X10ExpressionVisitor {
//                        public Object visitDispatch(X10Expression expression) {
//                            return expression.accept(this);
//                        }
//                        public Object visit(NewDistArray newDistArray) {
//                            ScalarType elementType = newDistArray.elementType;
//                            Id regionOrDistName = newDistArray.regionOrDistName;
//                            Option<NewDistArray.ArrayInit> arrayInitOption = newDistArray.arrayInitOption;
//                            typeVisitor.visitDispatch(elementType);
//                            shared.visit(regionOrDistName);
//                            if (arrayInitOption.isPresent()) {
//                                NewDistArray.ArrayInit arrayInit = (((Some<NewDistArray.ArrayInit>) arrayInitOption)).get();
//                                PointType pointType = arrayInit.pointType;
//                                //PointFormalVar pointFormalVar = arrayInit.pointFormalVar;
//                                Block block = arrayInit.block;
//                                typeVisitor.visitDispatch(pointType);
//                                statementVisitor.visitDispatch(block);
//                            }
//                            return null;
//                        }
//
//                        public Object visit(PointConstructor pointConstructor) {
//                            Expression[] expressions = pointConstructor.expressions;
//                            for (Expression expression : expressions) {
//                                expressionVisitor.visitDispatch(expression);
//                            }
//                            return null;
//                        }
//                        public Object visit(RegionConstructor regionConstructor) {
//                            RegionConstructor.Dimension[] dims = regionConstructor.dims;
//                            for (RegionConstructor.Dimension dim : dims) {
//                                Option<Expression> min = dim.min;
//                                Expression max = dim.max;
//                                if (min.isPresent())
//                                    expressionVisitor.visitDispatch(((Some<Expression>) min).get());
//                                expressionVisitor.visitDispatch(max);
//                            }
//                            return null;
//                        }
//
//                        public Object visit(DistConstructor distConstructor) {
//                            Expression regionExpression = distConstructor.regionExpression;
//                            Expression placeExpression = distConstructor.placeExpression;
//                            expressionVisitor.visitDispatch(regionExpression);
//                            expressionVisitor.visitDispatch(placeExpression);
//                            return null;
//                        }
//
//                        public Object visit(SX10Expression expression) {
//                            return sX10ExpressionVisitor.visitDispatch(expression);
//                        }
//
//                        public X10ExpressionVisitor.SX10ExpressionVisitor sX10ExpressionVisitor =  new X10ExpressionVisitor.SX10ExpressionVisitor();
//                        public class SX10ExpressionVisitor {
//                            public Object visitDispatch(SX10Expression expression) {
//                                return expression.accept(this);
//                            }
//                            public Object visit(Coord coord) {
//                                Expression region = coord.region;
//                                Expression pointIndex = coord.pointIndex;
//                                Expression coordIndex = coord.coordIndex;
//                                expressionVisitor.visitDispatch(region);
//                                expressionVisitor.visitDispatch(pointIndex);
//                                expressionVisitor.visitDispatch(coordIndex);
//                                return null;
//                            }
//
//                            public Object visit(Ordinal ordinal) {
//                                Expression region = ordinal.region;
//                                Expression point = ordinal.point;
//                                expressionVisitor.visitDispatch(region);
//                                expressionVisitor.visitDispatch(point);
//                                return null;
//
//                            }
//                            public Object visit(Max max) {
//                                Expression array = max.array;
//                                expressionVisitor.visitDispatch(array);
//                                return null;
//                            }
//                            public Object visit(Sum sum) {
//                                Expression array = sum.array;
//                                expressionVisitor.visitDispatch(array);
//                                return null;
//                            }
//                        }
//
//
//                    }

                    public GIdVisitor gIdVisitor = new GIdVisitor();
                    public class GIdVisitor {
                        public Object visitDispatch(GId gId) {
                            return gId.accept(this);
                        }

                        public Object visit(Id id) { return null; }
                    }

                    public LiteralVisitor literalVisitor = new LiteralVisitor();
                    public class LiteralVisitor {
                        public Object visitDispatch(Literal literal) {
                            return literal.accept(this);
                        }

//                        public Object visit(True theTrue) { return null; }
//                        public Object visit(False theFalse) { return null; }

                        public Object visit(IntLiteral intLiteral) { return null; }
//                        public Object visit(LongLiteral longLiteral) { return null; }
//                        public Object visit(FloatLiteral floatLiteral) { return null; }
//                        public Object visit(DoubleLiteral doubleLiteral) { return null; }
//                        public Object visit(HexLiteral hexLiteral) { return null; }
//                        public Object visit(StringLiteral stringLiteral) { return null; }

//                        public Object visit(ArrayLiteral arrayLiteral) { return null; }

                        public Object visit(This theThis) { return null; }

//                        public Object visit(Here here) { return null; }
                    }

//                    public UnaryOpVisitor unaryOpVisitor =  new UnaryOpVisitor();
//                    public class UnaryOpVisitor {
//                        public Object visitDispatch(UnaryOp unaryOp) {
//                            expressionVisitor.visitDispatch(unaryOp.operand);
//                            return unaryOp.accept(this);
//                        }
//
//                        public Object visit(UnaryPlus unaryPlus) { return null; }
//                        public Object visit(UnaryMinus unaryMinus) { return null; }
//
//                        public Object visit(PreIncrement preIncrement) { return null; }
//                        public Object visit(PreDecrement preDecrement) { return null; }
//                        public Object visit(PostIncrement postIncrement) { return null; }
//                        public Object visit(PostDecrement postDecrement) { return null; }
//
//                        public Object visit(Complement complement) { return null; }
//                        public Object visit(Not not) { return null; }
//                    }


                    public BinaryOpVisitor binaryOpVisitor =  new BinaryOpVisitor();
                    public class BinaryOpVisitor {
                        public Object visitDispatch(BinaryOp binaryOp) {
                            expressionVisitor.visitDispatch(binaryOp.operand1);
                            expressionVisitor.visitDispatch(binaryOp.operand2);
                            return binaryOp.accept(this);
                        }

                        public Object visit(Plus plus) { return null; }
//                        public Object visit(Minus minus) { return null; }
//                        public Object visit(Times times) { return null; }
//                        public Object visit(Divide divide) { return null; }
//                        public Object visit(Modulus modulus) { return null; }
//                        public Object visit(Power Power) { return null; }
//
//                        public Object visit(BitAnd bitAnd) { return null; }
//                        public Object visit(BitOr bitOr) { return null; }
//                        public Object visit(BitXOr bitXOr) { return null; }
//
//                        public Object visit(ShiftLeft shiftLeft) { return null; }
//                        public Object visit(ShiftRight shiftRight) { return null; }
//                        public Object visit(UnsignedShiftRight unsignedShiftRight) { return null; }
//
//                        public Object visit(And and) { return null; }
//                        public Object visit(Or or) { return null; }
//
////                        public Object visit(ElementWiseAnd elementWiseAnd) { return null; }
////                        public Object visit(ElementWiseOr elementWiseOr) { return null; }
//
//                        public Object visit(Equality equality) { return null; }
//                        public Object visit(NotEquality notEquality) { return null; }
//                        public Object visit(GreaterThan greaterThan) { return null; }
//                        public Object visit(GreaterThanEqual greaterThanEqual) { return null; }
//                        public Object visit(LessThan lessThan) { return null; }
//                        public Object visit(LessThanEqual lessThanEqual) { return null; }
                    }
                }

                public TypeVisitor typeVisitor =  new TypeVisitor();
                public class TypeVisitor {
                    public Object visitDispatch(Type type) {
                        return type.accept(this);
                    }
                    public Object visit(BooleanType booleanType) { return null; }
                    //public Object visit(ByteType byteType) { return null; }
                    //public Object visit(ShortType shortType) { return null; }
                    public Object visit(IntType intType) { return null; }
//                    public Object visit(LongType longType) { return null; }
//                    public Object visit(DoubleType doubleType) { return null; }
//                    public Object visit(StringType stringType) { return null; }
//                    public Object visit(VoidType voidType) { return null; }
//                    public Object visit(LocalArrayType localArrayType) { return null; }
//
//                    public Object visit(DefinedType definedType) { return null; }
//                    public Object visit(ArrayType arrayType) { return null; }
//
//                    public Object visit(PlaceType placeType) { return null; }
//                    public Object visit(PointType pointType) { return null; }
//                    public Object visit(RegionType regionType) { return null; }
//                    public Object visit(DistType distType) { return null; }
//
//                    public Object visit(MatrixType matrixType) { return null; }

//                    public ConstraintVisitor constraintVisitor =  new ConstraintVisitor();
//                    public class ConstraintVisitor {
//                        public Object visitDispatch(Constraint constraint) {
//                            return constraint.accept(this);
//                        }
//
//                        public Object visit(DistConstraint distConstraint) { return null; }
//                        public Object visit(RankConstraint rankConstraint) { return null; }
//                    }
                }

            }
        }
    }


