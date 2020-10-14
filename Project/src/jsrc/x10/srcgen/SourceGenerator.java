package jsrc.x10.srcgen;

import jsrc.x10.ast.tree.declaration.Method;
import jsrc.x10.ast.tree.expression.id.GId;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.expression.id.specialids.SpecialId;
import jsrc.x10.ast.tree.expression.id.specialids.general.*;
import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.*;
import jsrc.x10.ast.tree.expression.op.logical.*;
import jsrc.x10.ast.tree.expression.x10.derived.DistAccess;
import jsrc.x10.ast.tree.expression.x10.sx10lib.*;
import jsrc.x10.ast.tree.expression.id.specialids.x10.SX10Id;
import jsrc.x10.ast.tree.expression.literal.*;
import jsrc.x10.ast.tree.expression.op.UnaryOp;
import jsrc.x10.ast.tree.expression.op.BinaryOp;
import jsrc.x10.ast.tree.expression.op.relational.*;
import jsrc.x10.ast.tree.expression.op.math.*;
import jsrc.x10.ast.tree.expression.*;
import jsrc.x10.ast.tree.expression.id.specialids.x10.array.properties.Distribution;
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
import jsrc.x10.ast.tree.expression.x10.derived.ArrayAccess;
import jsrc.x10.ast.tree.expression.x10.derived.ArrayUpdate;
import jsrc.x10.ast.tree.expression.xtras.Parenthesized;
import jsrc.x10.ast.tree.statement.*;
import jsrc.x10.ast.tree.statement.x10.Async;
import jsrc.x10.ast.tree.statement.x10.Finish;
import jsrc.x10.ast.tree.statement.x10.PointFor;
import jsrc.x10.ast.tree.declaration.*;
import jsrc.x10.ast.tree.statement.x10.X10Statement;
import jsrc.x10.ast.tree.statement.x10.parts.Coordinates;
import jsrc.x10.ast.tree.statement.x10.parts.IdCoordinates;
import jsrc.x10.ast.tree.statement.x10.parts.PointFormalVar;
import jsrc.x10.ast.tree.type.*;

import jsrc.x10.ast.tree.type.constraint.Constraint;
import jsrc.x10.ast.tree.type.constraint.DistConstraint;
import jsrc.x10.ast.tree.type.constraint.RankConstraint;
import jsrc.x10.ast.visitor.DFSVisitor;

import lesani.collection.Pair;
import lesani.collection.option.Option;
import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.compiler.texttree.*;
import lesani.collection.option.Some;
import lesani.compiler.texttree.seq.TextSeq;
import lesani.compiler.texttree.seq.TextFact;
import lesani.compiler.typing.SymbolTable;

import static lesani.file.Address.capName;

/**
 * User: lesani, Date: 9-Nov-2009, Time: 4:16:49 PM
 */


public class SourceGenerator extends DFSVisitor {

    private File root;
    private String fileName;

    private SymbolTable<Type> symbolTable = new SymbolTable<Type>();

    public SourceGenerator(File root) {
        this.root = root;
    }

    private boolean libUsed = false;
    public Pair<String, Printable> gen() {
        TextSeq textSeq = textFact.newText();
        textSeq.add((Text)root.accept(this));
        if (libUsed)
            textSeq.add(LIB);
        return new Pair<String, Printable>(fileName, textSeq.get());
    }


    private TextFact textFact = new TextFact();
//    private SeqGen seqGen = new SeqGen(new VisitDispatcher() {
//        public TextNode visitDispatch(lesani.compiler.ast.Node node) {
//            return SourceGenerator.this.visitDispatch((Node)node);
//        }
//    });

    // ---------------------------------------------------------

    public Object visitDispatch(File file) {
        return visit(file);
    }

    public Object visit(File file) {
        return fileVisitor.visitDispatch(file);

    }

    {
        fileVisitor = new FileVisitor() {
            public Object visitDispatch(File file) {
                TextSeq textSeq = textFact.newText();
                textSeq.skipLine();
                textSeq.fullLine("import x10.compiler.*;");
                textSeq.skipLine();

                ClassDecl[] classDecls = file.classDecls;

                for (ClassDecl classDecl : classDecls) {
                    textSeq.put(classVisitor.visitDispatch(classDecl));
                    textSeq.skipLine();
                }
                return textSeq.get();
            }

            {
                classVisitor = new ClassVisitor() {
                    public Object visitDispatch(ClassDecl classDecl) {
                    symbolTable.startScope();

                    TextSeq textSeq = textFact.newText();
                    textSeq.startLine(visit(classDecl.visibility));
                    if (classDecl.visibility != Visibility.PACKAGE)
                        textSeq.put(" ");
                    if (classDecl.visibility == Visibility.PUBLIC)
                        fileName = capName(classDecl.name.lexeme);
                    // It is assumed that the compilation unit that is passed to this class
                    // is checked to have only one public class

                    if (classDecl.isValue)
                        //seq.put("struct ");
                        textSeq.put("class ");
                    else
                        textSeq.put("class ");
                    textSeq.put(visit(classDecl.name));
                    textSeq.endLine(" {");
                    textSeq.incIndent();
                    Field[] fields = classDecl.fields;

                    for (Field field : fields)
                        textSeq.put(visit(field));
                    if (fields.length > 0)
                        textSeq.skipLine();

                    Constructor[] constructors = classDecl.constructors;
                    for (Constructor constructor : constructors) {
                        textSeq.put(visit(constructor));
                        textSeq.skipLine();
                    }

                    if (classDecl.methods.length != 0)
                        textSeq.skipLine();
                    Method[] methods = classDecl.methods;
                    for (Method method : methods) {
                        textSeq.put(visit(method));
                        textSeq.skipLine();
                    }
                    textSeq.decIndent();
                    textSeq.fullLine("}");
                    symbolTable.endScope();
                    return textSeq.get();
                }

                    {
                        fieldVisitor =  new FieldVisitor() {
                            public Object visitDispatch(Field field) {

                                TextSeq textSeq = textFact.newText();
                                textSeq.startLine(visit(field.visibility));
                                textSeq.put(" ");
                                if (field.isStatic)
                                    textSeq.put("static ");
    //        seq.put("global ");
                                if (field.isFinal) {
    //            seq.put("global ");
                                    textSeq.put("val ");
                                } else {
    //            seq.put("global ");
                                    textSeq.put("var ");
                                }
                                textSeq.put(visit(field.name));
                                textSeq.put(": ");
                                textSeq.put(shared.typeVisitor.visitDispatch(field.type));
                            //if (! fieldDecl.isStatic)
                            //    seq.put("{self.at(this)}");

                                if (field.initExpression.isPresent()) {
                                    textSeq.put(" ");
                                    textSeq.put("= ");
                                    Expression e = ((Some<Expression>) field.initExpression).get();
                                    textSeq.put(shared.expressionVisitor.visitDispatch(e));
                                }
                                textSeq.endLine(";");

                                try {
                                    symbolTable.put(field.name.lexeme, field.type);
                                } catch (Exception e) {}

                                return textSeq.get();

                            }
                        };

                        constructorVisitor =  new ConstructorVisitor() {
                            public Object visitDispatch(Constructor constructor) {
                                symbolTable.startScope();
                                TextSeq textSeq = textFact.newText();
                                textSeq.startLine("public def this");
                                FormalParam[] formalParams = constructor.formalParams;
                                Text[] texts = new Text[formalParams.length];
                                for (int i = 0; i < texts.length; i++)
                                    texts[i] = (Text)shared.visit(formalParams[i]);
                                textSeq.putParList(texts);
                                textSeq.put(" ");
                                textSeq.put((new BracedBlock(constructor.block)).print());
                                textSeq.endLine();
                                symbolTable.endScope();
                                return textSeq.get();
                            }
                        };

                        methodVisitor =  new MethodVisitor() {
                            public Object visitDispatch(Method method) {

                        symbolTable.startScope();
                        final TextSeq textSeq = textFact.newText();
                        if (method.isProto)
//            seq.put("proto");
                            textSeq.fullLine("@NonEscaping final");

                        textSeq.startLine(visit(method.visibility));
                        textSeq.put(" ");
//        if (methodDecl.name.name.equals("toString"))
//            seq.put("safe ");
                        if (method.isStatic)
                            textSeq.put("static ");
                        if (method.isFinal)
                            textSeq.put("final ");
//        seq.put("global ");
                        textSeq.put("def ");
                        textSeq.put(visit(method.name));
                        //seq.putParList(methodDecl.formalParams);

                        method.formalParams.apply(
                                new Fun0<Object>() {
                                    public Object apply() {
                                        textSeq.put("()");
                                        return null;
                                    }
                                },
                                new Fun<FormalParam[], Object>() {
                                    public Object apply(FormalParam[] formalParams) {
                                        Text[] texts = new Text[formalParams.length];
                                        for (int i = 0; i < texts.length; i++)
                                            texts[i] = (Text)shared.visit(formalParams[i]);
                                        textSeq.putParList(texts);
                                        return null;
                                    }
                                }
                        );
                        if (!(method.type instanceof VoidType)) {
                            textSeq.put(": ");
                            textSeq.put(visit(method.type));
                        }
                        //seq.put("!");
                        textSeq.put(" ");
                        textSeq.put(new BracedBlock(method.block).print());
                        textSeq.endLine();
                        symbolTable.endScope();
                        return textSeq.get();

                    }
                        };

                        shared = new Shared() {
                            public Object visit(Visibility visibility) {
                                TextSeq textSeq = textFact.newText();
                                textSeq.put(visibility.toString());
                                return textSeq.get();
                            }
                            public Object visit(GId name) { return gIdVisitor.visitDispatch(name); }

                            public Object visit(Type type) {
                                TextSeq textSeq = textFact.newText();
                                textSeq.put(typeVisitor.visitDispatch(type));
                                return textSeq.get();
                            }

                            public Object visit(FormalParam formalParam) {

                                TextSeq textSeq = textFact.newText();
                                if (formalParam.isFinal)
                                    textSeq.put("val ");
                                textSeq.put(visit(formalParam.name));
                                textSeq.put(": ");
                                textSeq.put(typeVisitor.visitDispatch(formalParam.type));

                                try {
                                    symbolTable.put(formalParam.name.lexeme, formalParam.type);
                                } catch (Exception e) {}

                                return textSeq.get();
                            }

                            public Object visit(VarDecl varDecl) {

                                TextSeq textSeq = textFact.newText();

                                //seq.startLine("public ");
                                if (varDecl.isConstant)
                                    textSeq.startLine("val ");
                                else
                                    textSeq.startLine("var ");
                                textSeq.put(visit(varDecl.name));
                                if (varDecl.isConstant)
                                    textSeq.put(" <: ");
                                else
                                    textSeq.put(": ");

                                textSeq.put(typeVisitor.visitDispatch(varDecl.type));
                                //seq.put("!");
                                final Option<Expression> expressionOption = varDecl.expression;
                                if (expressionOption.isPresent()) {
                                    Expression expression = ((Some<Expression>)expressionOption).get();
                                    textSeq.put(" ");
                                    textSeq.put("= ");
                                    textSeq.put(expressionVisitor.visitDispatch(expression));
                                } else {
        //            seq.put(" ");
        //            seq.put("= ");
        //            seq.put("_");
                                }
                                textSeq.endLine(";");

                                try {
                                    symbolTable.put(varDecl.name.lexeme, varDecl.type);
                                } catch (Exception e) {}

                                return textSeq.get();
                            }

                            {
                                statementVisitor =  new StatementVisitor() {
                                    public Object visit(Assignment assignment) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.startLine(expressionVisitor.visitDispatch(assignment.left));
                                        textSeq.put(" = ");
                                        textSeq.put(expressionVisitor.visitDispatch(assignment.right));
                                        textSeq.endLine(";");
                                        return textSeq.get();
                                    }

                                    public Object visit(ExpSt expSt) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.startLine(expressionVisitor.visitDispatch(expSt.expression));
                                        textSeq.endLine(";");
                                        return textSeq.get();
                                    }
                                    public Object visit(Throw theThrow) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.startLine("throw new RuntimeException(");
                                        textSeq.put(expressionVisitor.visitDispatch(theThrow.arg));
                                        textSeq.endLine(");");
                                        return textSeq.get();

                                    }

                                    public Object visit(ValueReturn valueReturn) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.startLine("return ");
                                        textSeq.put(expressionVisitor.visitDispatch(valueReturn.arg));
                                        textSeq.endLine(";");
                                        return textSeq.get();
                                    }

                                    public Object visit(VoidReturn voidReturn) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.fullLine("return;");
                                        return textSeq.get();
                                    }

                                    public Object visit(If theIf) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.startLine("if (");
                                        textSeq.put(expressionVisitor.visitDispatch(theIf.condition));
                                        textSeq.put(") ");
            //        seq.put(visitDispatch(theIf.ifStatement);
                                        // X10 compiler confuses matching else!!!
                                        textSeq.put(new BracedBlock(new Block(new Statement[] {theIf.ifStatement})).print());
                                        if (theIf.elseStatement.isPresent()) {
                                            Statement elseStatement = ((Some<Statement>)theIf.elseStatement).get();
                                            if (theIf.ifStatement instanceof Block && ((Block)theIf.ifStatement).statements.length > 1)
                                                textSeq.put(" else ");
                                            else
                                                textSeq.startLine("else ");
            //            seq.println("else");
                                            textSeq.put(visitDispatch(elseStatement));
                                            if (elseStatement instanceof Block && ((Block)elseStatement).statements.length > 1)
                                                textSeq.endLine();
                                        } else {
                                            if (theIf.ifStatement instanceof Block && ((Block)theIf.ifStatement).statements.length > 1)
                                                textSeq.endLine();
                                        }
                                        return textSeq.get();
                                    }

                                    public Object visit(While theWhile) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.startLine("while (");
                                        textSeq.put(expressionVisitor.visitDispatch(theWhile.condition));
                                        textSeq.put(") ");
                                        textSeq.put(visitDispatch(theWhile.statement));
                                        if (theWhile.statement instanceof Block && ((Block)theWhile.statement).statements.length > 1)
                                            textSeq.endLine();
                                        return textSeq.get();
                                    }

                                    public Object visit(DoWhile doWhile) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.startLine("do ");
                                        textSeq.put(visitDispatch(doWhile.statement));
                                        textSeq.startLine("while (");
                                        textSeq.put(expressionVisitor.visitDispatch(doWhile.condition));
                                        textSeq.endLine(");");
                                        return textSeq.get();
                                    }

                                    public Object visit(Break theBreak) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.fullLine("break;");
                                        return textSeq.get();
                                    }

                                    public Object visit(Continue theContinue) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.fullLine("continue;");
                                        return textSeq.get();
                                    }

                                    public Object visit(Switch theSwitch) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.startLine("switch (");
                                        textSeq.put(expressionVisitor.visitDispatch(theSwitch.selector));
                                        textSeq.endLine(") {");
                                        textSeq.incIndent();
                                        Switch.Case[] cases = theSwitch.cases;
                                        for (Switch.Case theCase : cases) {
                                            textSeq.startLine("case ");
                                            textSeq.put(expressionVisitor.visitDispatch(theCase.guard));
                                            textSeq.endLine(":");
                                            textSeq.incIndent();
                                            for (VarDeclOrSt varDeclOrSt : theCase.statements)
                                                if (varDeclOrSt instanceof VarDecl) {
                                                    VarDecl varDecl = (VarDecl) varDeclOrSt;
                                                    textSeq.put(shared.visit(varDecl));
                                                } else {
                                                    Statement statement = (Statement) varDeclOrSt;
                                                    textSeq.put(visitDispatch(statement));
                                                }
            //                    seq.add(visit(Break.instance()));
                                            textSeq.decIndent();
                                        }
                                        if (theSwitch.defaultBlockStatements.isPresent()) {
                                            VarDeclOrSt[] statements =
                                                    ((Some<VarDeclOrSt[]>)theSwitch.defaultBlockStatements).get();
                                            textSeq.fullLine("default: ");
                                            textSeq.incIndent();
                                            for (VarDeclOrSt varDeclOrSt : statements) {
                                                if (varDeclOrSt instanceof VarDecl) {
                                                    VarDecl varDecl = (VarDecl) varDeclOrSt;
                                                    textSeq.put(shared.visit(varDecl));
                                                } else {
                                                    Statement statement = (Statement) varDeclOrSt;
                                                    textSeq.put(visitDispatch(statement));
                                                }
                                            }
                                            textSeq.decIndent();
                                        }
                                        textSeq.decIndent();
                                        textSeq.fullLine("}");
                                        return textSeq.get();
                                    }

                                    public Object visit(Block block) {
                                        TextSeq textSeq = textFact.newText();
                                        VarDeclOrSt[] sts = block.statements;
                                        if (sts.length == 0)
                                            textSeq.endLine(";");
                                        else {
                                            if (sts.length > 1)
                                                textSeq.endLine("{");
                                            else
                                                textSeq.endLine("");

                                            visitStatements(textSeq, sts);

                                            if (sts.length > 1)
                                                textSeq.startLine("}");
                                        }
            //        else
            //            seq.startLine("");
                                        return textSeq.get();
                                    }

                                    public Object visit(X10Statement x10Statement) {
                                        return x10StatementVisitor.visitDispatch(x10Statement);
                                    }

                                    {
                                        x10StatementVisitor =  new X10StatementVisitor() {
                                            public Object visitDispatch(X10Statement statement) {
                                                return statement.accept(this);
                                            }
                                            public Object visit(Async async) {
                                                TextSeq textSeq = textFact.newText();
                                                textSeq.startLine("at (");
                                                textSeq.put(expressionVisitor.visitDispatch(async.place));
                                                textSeq.put(") async ");
                                                textSeq.put(statementVisitor.visitDispatch(async.statement));
                                                textSeq.endLine();
                                                return textSeq.get();
                                            }
                                            public Object visit(Finish finish) {
                                                TextSeq textSeq = textFact.newText();
                                                textSeq.startLine("finish ");
                                                textSeq.put(statementVisitor.visitDispatch(finish.statement));

                                                //        seq.println("finish");
                                                //        seq.put(visitDispatch(finish.statement));
                                                textSeq.endLine();
                                                return textSeq.get();
                                            }

                                            public Object visit(PointFor theFor) {
                                                final TextSeq textSeq = textFact.newText();
                                                textSeq.startLine(theFor.type.toString());
                                                textSeq.put(" (");
                                                textSeq.put(pointFormalVarVisitor.visitDispatch(theFor.pointFormalVar));

                                                // Not needed because of type inference.
                                                theFor.elementType.apply(
                                                        new Fun0<Object>() {
                                                            public Object apply() {
                                                                return null;
                                                            }
                                                        },
                                                        new Fun<Type, Object>() {
                                                            public Object apply(Type elementType) {
                                                                textSeq.put(": ");
                                                                textSeq.put(typeVisitor.visitDispatch(elementType));
                                                                return null;
                                                            }
                                                        }
                                                );
                                                textSeq.put(" in ");
                                                textSeq.put(expressionVisitor.visitDispatch(theFor.aggregate));
                                                textSeq.put(") ");
                                                //        System.out.println(theFor.pointFormalVar);
                                                if (theFor.pointFormalVar instanceof IdCoordinates) {
                                                    //            System.out.println("inside here");
                                                    IdCoordinates idCoordinates = (IdCoordinates) theFor.pointFormalVar;
                                                    Id point = idCoordinates.id.name;
                                                    Id[] ids = idCoordinates.coordinates.ids;
                                                    VarDeclOrSt[] statements = new VarDeclOrSt[ids.length + 1];
                                                    for (int i = 0; i < ids.length; i++) {
                                                        Id id = ids[i];
                                                        statements[i] = new VarDecl(IntType.instance(), id, new ArrayAccess(point, new Expression[]{new IntLiteral(i)}));
                                                    }
                                                    statements[statements.length - 1] = theFor.statement;
                                                    //            System.out.println("here1");
                                                    textSeq.put(statementVisitor.visitDispatch(new Block(statements)));
                                                    //            System.out.println("here2");
                                                } else {
                                                    textSeq.put(statementVisitor.visitDispatch(theFor.statement));
                                                    if (theFor.statement instanceof Block && ((Block)theFor.statement).statements.length > 1)
                                                        textSeq.endLine();
                                                }
                                                return textSeq.get();
                                            }

                                            {pointFormalVarVisitor =  new PointFormalVarVisitor() {
                                                public Object visitDispatch(PointFormalVar pointFormalVar) {
                                                    return pointFormalVar.accept(this);
                                                }

                                                public Object visit(jsrc.x10.ast.tree.statement.x10.parts.Id id) {
                                                    TextSeq textSeq = textFact.newText();
                                                    textSeq.put(id.name.lexeme);
                                                    return textSeq.get();
                                                }
                                                public Object visit(Coordinates coordinates) {
                                                    TextSeq textSeq = textFact.newText();
                                                    Id[] ids = coordinates.ids;
                                                    Text[] texts = new Text[ids.length];
                                                    for (int i = 0; i < texts.length; i++)
                                                        texts[i] = (Text)shared.visit(ids[i]);

                                                    textSeq.putBracketList(texts);

                                                    /*
                                                                if (ids.length > 1)
                                                    //                seq.putParList(ids);
                                                                    seq.putBracketList(ids);
                                                                else
                                                                    seq.put(ids[0].name);
                                                    */
                                                    return textSeq.get();
                                                }
                                                public Object visit(IdCoordinates idCoordinates) {
                                                    TextSeq textSeq = textFact.newText();
                                                    textSeq.put(idCoordinates.id.name.lexeme);
                                                    //            seq.putParList(idCoordinates.coordinates.ids);
                                                    return textSeq.get();
                                                }
                                            };}
                                        };
                                    }
                                };


                                expressionVisitor =  new ExpressionVisitor() {
                                public Object visitDispatch(Expression expression) {
                                    return expression.accept(this);
                                }

                                public Object visit(GId id) {
                                    return gIdVisitor.visitDispatch(id);
                                }

                                public Object visit(Literal literal) {
                                    TextSeq textSeq = textFact.newText();
                                    if (!(literal instanceof ArrayLiteral))
                                        textSeq.put(literal.lexeme);
                                    else {
                                        ArrayLiteral arrayLiteral = (ArrayLiteral)literal;
                                        Expression[] expressions = arrayLiteral.elements;
                                        textSeq.put("[");
                                        for (int i = 0, expressionsLength = expressions.length; i < expressionsLength; i++) {
                                            Expression expression = expressions[i];
                                            textSeq.put(visitDispatch(expression));
                                            if (i != expressions.length - 1)
                                                textSeq.put(", ");
                                        }
                                        textSeq.put("]");

                                    }
                                    return textSeq.get();
                                }

                                // from unary and binay to relational, math and ... .
                                public Object visit(UnaryOp unaryOp) {
                                    TextSeq textSeq = textFact.newText();
                                    //seq.put("(" + unaryOpVisitor.visitDispatch(unaryOp));
                                    if ((unaryOp instanceof PostIncrement) ||
                                            (unaryOp instanceof PostDecrement)) {
                                        textSeq.put(visitDispatch(unaryOp.operand));
                                        textSeq.put(unaryOpVisitor.visitDispatch(unaryOp));
                                    } else {
                                        textSeq.put(unaryOpVisitor.visitDispatch(unaryOp));
                                        textSeq.put(visitDispatch(unaryOp.operand));
                                    }
                                    //seq.put(")");
                                    return textSeq.get();
                                }

                                public Object visit(BinaryOp binaryOp) {
                                    TextSeq textSeq = textFact.newText();
                                    textSeq.put("(");
                                    textSeq.put(visitDispatch(binaryOp.operand1));
                                    textSeq.put(" ");
                                    textSeq.put(binaryOpVisitor.visitDispatch(binaryOp) + " ");
                                    textSeq.put(visitDispatch(binaryOp.operand2));
                                    textSeq.put(")");
                                    return textSeq.get();

                                }

                                public Object visit(New theNew) {

                                    TextSeq textSeq = textFact.newText();
                                    textSeq.put("new ");
                                    textSeq.put(typeVisitor.visitDispatch(theNew.type));
                                    Expression[] argumentNames = theNew.args;
                                    Text[] argumentTexts = new Text[argumentNames.length];
                                    for (int i = 0, argumentNamesLength = argumentNames.length; i < argumentNamesLength; i++)
                                        argumentTexts[i] = (Text) expressionVisitor.visitDispatch(argumentNames[i]);

                                    textSeq.putParList(argumentTexts);
                                    return textSeq.get();
                                }

                                public Object visit(FieldSelection fieldSelection) {

                                    TextSeq textSeq = textFact.newText();
                                    //seq.put("(");
                                    textSeq.put(expressionVisitor.visitDispatch(fieldSelection.receiver));
                                    textSeq.put(".");
                                    textSeq.put(gIdVisitor.visitDispatch(fieldSelection.fieldName));
                                    //seq.put(")");
                                    return textSeq.get();

                                }

                                    public Text visit(ArrayAccess arrayAccess) {
                                    TextSeq textSeq = textFact.newText();
                                    Expression receiver = arrayAccess.array();
                                    textSeq.put(visitDispatch(receiver));
                                    //seq.put(".apply");
                                    Text[] texts = new Text[arrayAccess.indices().length];
                                    for (int i = 0; i < texts.length; i++)
                                        texts[i] = (Text) expressionVisitor.visitDispatch(arrayAccess.indices()[i]);
                                    textSeq.putParList(texts);
                                    return textSeq.get();
                                }

                                public Text visit(ArrayUpdate arrayUpdate) {
                                    TextSeq textSeq = textFact.newText();
                                    Expression receiver = arrayUpdate.array;
                                    textSeq.put(visitDispatch(receiver));
                                    //seq.put(".apply");
                                    Text[] texts = new Text[arrayUpdate.indices.length];
                                    for (int i = 0; i < texts.length; i++)
                                        texts[i] = (Text) expressionVisitor.visitDispatch(arrayUpdate.indices[i]);

                                    textSeq.putParList(texts);
                                    textSeq.put(" = ");
                                    textSeq.put(visitDispatch(arrayUpdate.right));
                                    return textSeq.get();
                                }
                                public Text visit(DistAccess distAccess) {
                                    TextSeq textSeq = textFact.newText();
                                    textSeq.put(visitDispatch(distAccess.dist()));
                                    //seq.put("(");
                                    //seq.put("new Point");
                                    //seq.put("(");
                                    //seq.putBracketList(distAccess.coordinates.ids);
                                    // In the benchmark sources both an int and a point are used for the index.
                                    // We can put bracket for none of them.
                                    Text[] texts = new Text[distAccess.indices().length];
                                    for (int i = 0; i < texts.length; i++)
                                        texts[i] = (Text) expressionVisitor.visitDispatch(distAccess.indices()[i]);

                                    textSeq.putParList(texts);
                                    //seq.put(")");
                                    //seq.put(")");
                                    return textSeq.get();
                                }

                                public Object visit(MethodCall methodCall) {

                                    if (methodCall instanceof ArrayAccess)
                                        return visit((ArrayAccess) methodCall);
                                    if (methodCall instanceof ArrayUpdate)
                                        return visit((ArrayUpdate) methodCall);
                                    if (methodCall instanceof DistAccess)
                                        return visit((DistAccess) methodCall);

                                    Option<Expression> optionalReceiver = methodCall.receiver;
                                    if (optionalReceiver.isPresent()) {
                                        Expression receiver = ((Some<Expression>)optionalReceiver).get();
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.put(visitDispatch(receiver));
                                        textSeq.put(".");
                                        textSeq.put(visitDispatch(methodCall.methodName));
                                        // BlockCyclicFactory is not supported in V2 anymore.
        //            if (methodCall.methodName instanceof BlockCyclicFactory) {
        //                Expression[] args = methodCall.args;
        //                Expression[] newArgs = new Expression[args.length + 1];
        //                newArgs[0] = args[0];
        //                newArgs[1] = new IntLiteral("0");
        //                for (int i = 2; i < newArgs.length; i++) {
        //                    newArgs[i] = args[i-1];
        //                }
        //                methodCall.args = newArgs;
        //            }
                                        if (methodCall.methodName.lexeme.equals("log")) {
                                            Expression[] args = methodCall.args;
                                            args[0] = new Cast(DoubleType.instance(), args[0]);
                                        }
                                        Text[] texts = new Text[methodCall.args.length];
                                        for (int i = 0; i < texts.length; i++)
                                            texts[i] = (Text)expressionVisitor.visitDispatch(methodCall.args[i]);
                                        textSeq.putParList(texts);
                                        return textSeq.get();
                                    } else {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.put(visitDispatch(methodCall.methodName));
                                        Text[] texts = new Text[methodCall.args.length];
                                        for (int i = 0; i < texts.length; i++)
                                            texts[i] = (Text)expressionVisitor.visitDispatch(methodCall.args[i]);

                                        textSeq.putParList(texts);
                                        return textSeq.get();
                                    }
                                }

                                public Object visit(Cast cast) {

                                    TextSeq textSeq = textFact.newText();
                                    textSeq.put("(");
                                    textSeq.put(visitDispatch(cast.operand));
                                    textSeq.put(" as ");
                                    //seq.put(" to ");
                                    textSeq.put(typeVisitor.visitDispatch(cast.type));
                                    textSeq.put(")");
                                    return textSeq.get();
                                }

                                public Object visit(Conditional conditional) {
                                    TextSeq textSeq = textFact.newText();
                                    textSeq.put(visitDispatch(conditional.condition));
                                    textSeq.put("?");
                                    textSeq.put(visitDispatch(conditional.ifExp));
                                    textSeq.put(":");
                                    textSeq.put(visitDispatch(conditional.elseExp));
                                    return textSeq.get();
                                }

                                public Object visit(Parenthesized parenthesized) {

                                    TextSeq textSeq = textFact.newText();
                                    textSeq.put("(");
                                    textSeq.put(visitDispatch(parenthesized.expression));
                                    textSeq.put(")");
                                    return textSeq.get();
                                }

                                public Object visit(X10Expression expression) {
                                    return x10ExpressionVisitor.visitDispatch(expression);
                                }

                                {
                                    x10ExpressionVisitor =  new X10ExpressionVisitor() {
                                        public Object visit(NewDistArray newDistArray) {

                                            TextSeq textSeq = textFact.newText();
                                            //String initializer = "";

                                            textSeq.put("(");
            /*
                    if (newArray.isConstant)
                        seq.put("Array.makeVal");
                    else
            */


            //        seq.put("Array.make");
            //        seq.put("new Array");
                                            textSeq.put("DistArray.make");
                                            textSeq.put("[");
                                            textSeq.put(typeVisitor.visitDispatch(newDistArray.elementType));
                                            textSeq.put("]");
                                            try {
                                                textSeq.put("(");
                                                if (symbolTable.get(newDistArray.regionOrDistName.lexeme) instanceof RegionType) {
                                                    textSeq.put(gIdVisitor.visitDispatch(newDistArray.regionOrDistName));
                                                    textSeq.put("-> here");
                                                } else if (symbolTable.get(newDistArray.regionOrDistName.lexeme) instanceof DistType)
                                                    textSeq.put(gIdVisitor.visitDispatch(newDistArray.regionOrDistName));
                                            } catch (Exception e) {
                                                textSeq.put(gIdVisitor.visitDispatch(newDistArray.regionOrDistName));
                                            }

            //        seq.put(visitDispatch(newArray.regionOrDistName));
                                            if (newDistArray.arrayInitOption.isPresent()) {
                                                NewDistArray.ArrayInit arrayInit =
                                                        ((Some<NewDistArray.ArrayInit>) newDistArray.arrayInitOption).get();
                                                textSeq.put(", ");
                                                textSeq.put("(");
                                                //String loopVar = arrayInit.pointFormalVar.toString();
                                                //this.initFuncParam = loopVar;
                                                textSeq.put(statementVisitor.x10StatementVisitor.pointFormalVarVisitor.visitDispatch(arrayInit.pointFormalVar));
                                                textSeq.put(":");
                                                textSeq.put(typeVisitor.visitDispatch(arrayInit.pointType));
                                                textSeq.put(")");
                                                textSeq.put(" => ");
                                                //inArrayInit = true;

                                                VarDeclOrSt[] varDeclOrSts = arrayInit.block.statements;
                                                if (newDistArray.elementType instanceof DoubleType)
                                                    if (varDeclOrSts.length > 0) {
                                                        VarDeclOrSt st = varDeclOrSts[0];
                                                        if (st instanceof ValueReturn) {
                                                            ValueReturn valueReturn = ((ValueReturn) st);
                                                            if (valueReturn.arg instanceof IntLiteral) {
                                                                IntLiteral intLiteral = (IntLiteral) valueReturn.arg;
                                                                if (intLiteral.lexeme.equals("0"))
                                                                    valueReturn.arg = new DoubleLiteral("0.0");
                                                            }
                                                        }
                                                    }
                                                BracedBlock block;
                                                if (arrayInit.pointFormalVar instanceof IdCoordinates) {
            //            System.out.println("inside here");
                                                    IdCoordinates idCoordinates = (IdCoordinates) arrayInit.pointFormalVar;
                                                    Id point = idCoordinates.id.name;
                                                    Id[] ids = idCoordinates.coordinates.ids;
                                                    VarDeclOrSt[] statements = new VarDeclOrSt[ids.length + 1];
                                                    for (int i = 0; i < ids.length; i++) {
                                                        Id id = ids[i];
                                                        statements[i] = new VarDecl(IntType.instance(), id, new ArrayAccess(point, new Expression[]{new IntLiteral(i)}));
                                                    }
                                                    statements[statements.length - 1] = arrayInit.block;
            //            System.out.println("here1");
                                                    block = new BracedBlock(new Block(statements));
            //            System.out.println("here2");
                                                } else {
                                                    block = new BracedBlock(arrayInit.block);
                                                }

                                                textSeq.put(block.print());
                                                //inArrayInit = false;
                                            }
                                            textSeq.put(")");
                                            textSeq.put(")");
                                            //seq.endLine(";");
                                            return textSeq.get();
                                        }

                                        public Object visit(PointConstructor pointConstructor) {
                                            TextSeq textSeq = textFact.newText();
                                            textSeq.put("Point.make(");
                                            Text[] texts = new Text[pointConstructor.expressions.length];
                                            for (int i = 0; i < texts.length; i++)
                                                texts[i] = (Text)expressionVisitor.visitDispatch(pointConstructor.expressions[i]);
                                            textSeq.putBracketList(texts);
                                            textSeq.put(")");
                                            return textSeq.get();
                                        }
                                        public Object visit(RegionConstructor regionConstructor) {

                                            TextSeq textSeq = textFact.newText();
                                            RegionConstructor.Dimension[] dims = regionConstructor.dims;
            //        seq.put("[");
            //        seq.put("Region.make(");
            //        seq.put("[");
                                            for (int i = 0; i < dims.length; i++) {
                                                textSeq.put("(");
                                                if (regionConstructor.dims[i].min.isPresent()) {
                                                    Expression minExpression = ((Some<Expression>)(dims[i].min)).get();
                                                    textSeq.put(expressionVisitor.visitDispatch(minExpression));
                                                } else
                                                    textSeq.put("0");
                                                textSeq.put("..");
                                                textSeq.put(expressionVisitor.visitDispatch(dims[i].max));
                                                textSeq.put(")");

                                                if (i != dims.length - 1)
            //                seq.put(", ");
                                                    textSeq.put(" * ");
                                            }
            //        seq.put("]");
            //        seq.put("]");
            //        seq.put(")");

                                            return textSeq.get();
                                        }

                                        public Object visit(DistConstructor distConstructor) {

                                            TextSeq textSeq = textFact.newText();
                                            textSeq.put("(");
                                            textSeq.put(expressionVisitor.visitDispatch(distConstructor.regionExpression));
                                            textSeq.put("->");
                                            textSeq.put(expressionVisitor.visitDispatch(distConstructor.placeExpression));
                                            textSeq.put(")");
                                            return textSeq.get();
                                        }

                                        public Object visit(SX10Expression expression) {
                                            return sX10ExpressionVisitor.visitDispatch(expression);
                                        }

                                        {
                                            sX10ExpressionVisitor =  new SX10ExpressionVisitor() {
                                                public Object visitDispatch(SX10Expression expression) {
                                                    return expression.accept(this);
                                                }
                                                public Object visit(Coord coord) {
                                                    libUsed = true;
                                                    TextSeq textSeq = textFact.newText();
                                                    textSeq.put("COMPILER_INSERTED_LIB.coord(");
                                                    textSeq.put(expressionVisitor.visitDispatch(coord.region));
                                                    textSeq.put(",");
                                                    textSeq.put(expressionVisitor.visitDispatch(coord.pointIndex));
                                                    textSeq.put(",");
                                                    textSeq.put(expressionVisitor.visitDispatch(coord.coordIndex));
                                                    textSeq.put(")");
                                                    return textSeq.get();
                                                }

                                                public Object visit(Ordinal ordinal) {

                                                    libUsed = true;
                                                    TextSeq textSeq = textFact.newText();
                                                    textSeq.put("COMPILER_INSERTED_LIB.ordinal(");
                                                    textSeq.put(expressionVisitor.visitDispatch(ordinal.region));
                                                    textSeq.put(",");
                                                    textSeq.put(expressionVisitor.visitDispatch(ordinal.point));
                                                    textSeq.put(")");
                                                    return textSeq.get();

                                                }
                                                public Object visit(Max max) {
                                                    libUsed = true;
                                                    TextSeq textSeq = textFact.newText();
                                                    textSeq.put("COMPILER_INSERTED_LIB.sum(");
                                                    textSeq.put(expressionVisitor.visitDispatch(max.array));
                                                    textSeq.put(")");
                                                    return textSeq.get();
                                                }
                                                public Object visit(Sum sum) {

                                                    libUsed = true;
                                                    TextSeq textSeq = textFact.newText();
                                                    textSeq.put("COMPILER_INSERTED_LIB.sum(");
                                                    textSeq.put(expressionVisitor.visitDispatch(sum.array));
                                                    textSeq.put(")");
                                                    return textSeq.get();

                                                }
                                            };
                                        }
                                    };

                                    unaryOpVisitor =  new UnaryOpVisitor() {
                                        public Object visit(UnaryPlus unaryPlus) {
                                            return "+";
                                        }

                                        public Object visit(UnaryMinus unaryMinus) {
                                            return "-";
                                        }

                                        public Object visit(PreIncrement preIncrement) {
                                            return "++";
                                        }

                                        public Object visit(PreDecrement preDecrement) {
                                            return "--";
                                        }

                                        public Object visit(PostIncrement postIncrement) {
                                            return "++";
                                        }

                                        public Object visit(PostDecrement postDecrement) {
                                            return "--";
                                        }

                                        public Object visit(Complement complement) {
                                            return "~";
                                        }

                                        public Object visit(Not not) {
                                            return "!";
                                        }
                                    };


                                    binaryOpVisitor =  new BinaryOpVisitor() {
                                        public Object visitDispatch(BinaryOp binaryOp) {
                                            expressionVisitor.visitDispatch(binaryOp.operand1);
                                            expressionVisitor.visitDispatch(binaryOp.operand2);
                                            return binaryOp.accept(this);
                                        }

                                        public Object visit(Plus plus) {
                                            return "+";
                                        }
                                        public Object visit(Minus minus) {
                                            return "-";
                                        }
                                        public Object visit(Times times) {
                                            return "*";
                                        }
                                        public Object visit(Divide divide) {
                                            return "/";
                                        }
                                        public Object visit(Modulus modulus) {
                                            return "%";
                                        }

                                        public Object visit(Power Power) {
                                            return "^";
                                        }

                                        public Object visit(BitAnd bitAnd) {
                                            return "&";
                                        }

                                        public Object visit(BitOr bitOr) {
                                            return "|";
                                        }

                                        public Object visit(BitXOr bitXOr) {
                                            return "^";
                                        }

                                        public Object visit(ShiftLeft shiftLeft) {
                                            return "<<";
                                        }

                                        public Object visit(ShiftRight shiftRight) {
                                            return ">>";
                                        }

                                        public Object visit(UnsignedShiftRight unsignedShiftRight) {
                                            return ">>>";
                                        }

                                        public Object visit(And and) {
                                            return "&&";
                                        }

                                        public Object visit(Or or) {
                                            return "||";
                                        }

                                        public Object visit(ElementWiseAnd elementWiseAnd) {
                                            return "&"; // We assume that operator overloading is done for "|".
                                        }

                                        public Object visit(ElementWiseOr elementWiseOr) {
                                            return "|"; // We assume that operator overloading is done for "&".
                                        }

                                        public Object visit(Equality equality) {
                                            return "==";
                                        }

                                        public Object visit(NotEquality notEquality) {
                                            return "!=";
                                        }

                                        public Object visit(GreaterThan greaterThan) {
                                            return ">";
                                        }

                                        public Object visit(GreaterThanEqual greaterThanEqual) {
                                            return ">=";
                                        }

                                        public Object visit(LessThan lessThan) {
                                            return "<";
                                        }

                                        public Object visit(LessThanEqual lessThanEqual) {
                                            return "<=";
                                        }
                                    };
                                }
                            };

                                typeVisitor =  new TypeVisitor() {
                                    public Object visitDispatch(Type type) {
                                        return type.accept(this);
                                    }

                                    public Object visit(BooleanType booleanType) {
                                        return new Snippet("Boolean");
                                    }

                                    public Object visit(ByteType byteType) {
                                        return new Snippet("Byte");
                                    }

                                    public Object visit(ShortType shortType) {
                                        return new Snippet("Short");
                                    }

                                    public Object visit(IntType intType) {
                                        return new Snippet("Int");
                                    }

                                    public Object visit(LongType longType) {
                                        return new Snippet("Long");
                                    }

                                    public Object visit(DoubleType doubleType) {
                                        return new Snippet("Double");
                                    }

                                    public Object visit(StringType stringType) {
                                        return new Snippet("String");
                                    }

                                    public Object visit(VoidType voidType) {
                                        return new Snippet("Void");
                                    }

                                    public Object visit(LocalArrayType localArrayType) {
                                        TextSeq textSeq = textFact.newText();
        //            seq.add("Rail[");
                                        textSeq.add("Array[");
                                        textSeq.add((Text)this.visitDispatch(localArrayType.elementType));
                                        textSeq.add("]");
                                        return textSeq.get();
                                    }

                                    public Object visit(DefinedType definedType) {
                                        final TextSeq textSeq = textFact.newText();
                                        textSeq.add(definedType.name);
                                        definedType.typeParams.apply(
                                                new Fun0<Object>() {
                                                    public Object apply() {
                                                        return null;
                                                    }
                                                },
                                                new Fun<Type[], Object>() {
                                                    public Object apply(Type[] typeParams) {
                                                        textSeq.add("[");
                                                        Text[] texts = new Text[typeParams.length];
                                                        for (int i = 0; i < texts.length; i++)
                                                            texts[i] = (Text)typeVisitor.visitDispatch(typeParams[i]);

                                                        textSeq.putCommaList(texts);
                                                        textSeq.add("]");
                                                        return null;
                                                    }
                                                }
                                        );
                                        definedType.properties.apply(
                                                new Fun0<Object>() {
                                                    public Object apply() {
                                                        return null;
                                                    }
                                                },
                                                new Fun<String[], Object>() {
                                                    public Object apply(String[] properties) {
                                                        textSeq.add("(");
                                                        textSeq.putCommaList(properties);
                                                        textSeq.add(")");
                                                        return null;
                                                    }
                                                }
                                        );
                                        return textSeq.get();
                                    }

                                    public Object visit(ArrayType arrayType) {
                                        TextSeq textSeq = textFact.newText();
        //            seq.add("Array");
                                        textSeq.add("DistArray");
                                        /*
                                        if (arrayType.isConstant)
                                            typeString += "ValArray";
                                        else
                                            typeString += "Array";
                                        */
                                        textSeq.add("[");
                                        textSeq.add((Text)this.visitDispatch(arrayType.elementType));
                                        textSeq.add("]");
                                        Option<Constraint[]> constraintsOption = arrayType.constraints;
                                        if (constraintsOption.isPresent()) {
                                            Constraint[] constraints = ((Some<Constraint[]>)constraintsOption).get();
                                            textSeq.add("{");
                                            for (int i = 0; i < constraints.length; i++) {
                                                textSeq.put(constraintVisitor.visitDispatch(constraints[i]));
                                                if (i != constraints.length - 1)
                                                    textSeq.put(" && ");
                                            }
                                            textSeq.add("}");
                                        }
                                        return textSeq.get();
                                    }

                                    public Object visit(PlaceType placeType) {
                                        return new Snippet("Place");
                                    }

                                    public Object visit(PointType pointType) {
                                        //return "Point(1)";
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.put(new Snippet("Point"));
                                        Option<Constraint> constraintOption = pointType.constraint;

                                        Constraint constraint = ((Some<Constraint>)constraintOption).get();
                                        if (constraintOption.isPresent()) {
                                            textSeq.add("{");
                                            textSeq.put(constraintVisitor.visitDispatch(constraint));
                                            textSeq.add("}");
                                        }
                                        return textSeq.get();
                                    }

                                    public Object visit(RegionType regionType) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.put(new Snippet("Region"));
                                        Option<Constraint> constraintOption = regionType.constraint;

                                        Constraint constraint = ((Some<Constraint>)constraintOption).get();
                                        if (constraintOption.isPresent()) {
                                            textSeq.add("{");
                                            textSeq.put(constraintVisitor.visitDispatch(constraint));
                                            textSeq.add("}");
                                        }
                                        return textSeq.get();
                                    }

                                    public Object visit(DistType distType) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.put(new Snippet("Dist"));
                                        Option<Constraint> constraintOption = distType.constraint;

                                        Constraint constraint = ((Some<Constraint>)constraintOption).get();
                                        if (constraintOption.isPresent()) {
                                            textSeq.add("{");
                                            textSeq.put(constraintVisitor.visitDispatch(constraint));
                                            textSeq.add("}");
                                        }
                                        return textSeq.get();
                                    }

                                    public Object visit(MatrixType matrixType) {
                                        TextSeq textSeq = textFact.newText();
                                        textSeq.add((Text)typeVisitor.visitDispatch(matrixType.elemType));
                                        textSeq.add("Matrix");
        //            if (matrixType.n.isPresent() && matrixType.m.isPresent())
        //                seq.add("(" + matrixType.n.value() + ", " + matrixType.m.value() + ")");
                                        return textSeq.get();
                                    }


                                    {constraintVisitor =  new ConstraintVisitor() {
                                        public Object visitDispatch(Constraint constraint) {
                                            return constraint.accept(this);
                                        }

                                        public Object visit(DistConstraint distConstraint) {
                                            TextSeq textSeq = textFact.newText();
                                            textSeq.put("(self.dist == ");
                                            textSeq.put(expressionVisitor.visitDispatch(distConstraint.value));
                                            textSeq.put(")");
                                            return textSeq.get();
                                        }

                                        public Object visit(RankConstraint rankConstraint) {
                                            TextSeq textSeq = textFact.newText();
                                            textSeq.put("(self.rank == ");
                                            textSeq.put(expressionVisitor.visitDispatch(rankConstraint.value));
                                            textSeq.put(")");
                                            return textSeq.get();
                                        }
                                    };}
                                };

                                gIdVisitor = new GIdVisitor() {
                                        public Object visitDispatch(GId gId) {
                                            return gId.accept(this);
                                        }

                                        public Object visit(Id id) {
                                            TextSeq textSeq = textFact.newText();
                                            textSeq.put(id.lexeme);
                                            /*
                                            if (inArrayInit && initFuncParam.equals(id.name)) {
                                                seq.put("(");
                                                seq.put(id.name);
                                                seq.put(" ");
                                                seq.put("as ");
                                                seq.put("Point(" + arrayRank + ")");
                                                seq.put(")");
                                            } else
                                                seq.put(id.name);
                                            */

                                            return textSeq.get();
                                        }
                                        public Object visit(SpecialId specialId) {
                                            TextSeq textSeq = textFact.newText();
                                            textSeq.put(specialIdVisitor.visitDispatch(specialId));
                                            return textSeq.get();
                                        }

                                        {
                                        specialIdVisitor = new SpecialIdVisitor() {
                                            public Object visitDispatch(SpecialId specialId) {
                                                return specialId.accept(this);
                                            }

                                            public Object visit(GSpecialId gSpecialId) { return gSpecialIdVisitor.visitDispatch(gSpecialId); }
                                            public Object visit(SX10Id sx10Id) { return x10SpecialIdVisitor.visitDispatch(sx10Id); }

                                            {
                                                gSpecialIdVisitor = new GSpecialIdVisitor() {

                                                public Object visitDispatch(GSpecialId gSpecialId) {
                                                    return gSpecialId.accept(this);
                                                }

                                                public Object visit(PrintId printId) { return "print"; }
                                                public Object visit(PrintlnId printlnId) { return "println"; }

                                                @Override
                                                public Object visit(SOut sOut) { return "Console.OUT"; }

                                                @Override
                                                public Object visit(SErr sErr) { return "Console.ERR"; }

                                                public Object visit(IntegerSize integerSize) { return expressionVisitor.visit(new IntLiteral("32")); }

                                            };

                                                x10SpecialIdVisitor = new SX10IdVisitor() {
                                                    public Object visitDispatch(SX10Id sx10Id) {
                                                        return sx10Id.accept(this);
                                                    }


                                                    public Object visit(Equals equals) {
                                                        return "equals";
                                                    }

                                                    public Object visit(Place place) {
                                                        return "Place";
                                                    }

                                                    public Object visit(IsFirst isFirst) {
                                                        return "isFirst";
                                                    }

                                                    public Object visit(IsLast isLast) {
                                                        return "isLast";
                                                    }

                                                    public Object visit(Next next) {
                                                        return "next";
                                                    }

                                                    public Object visit(Prev prev) {
                                                        return "prev";
                                                    }

                                                    public Object visit(Places places) {
        //            return "places";
                                                        return "place";
                                                    }

                                                    public Object visit(FirstPlace firstPlace) {
                                                        return "FIRST_PLACE";
                                                    }

                                                    public Object visit(MaxPlaces maxPlaces) {
                                                        return "MAX_PLACES";
                                                    }

                                                    public Object visit(jsrc.x10.ast.tree.expression.id.specialids.x10.place.properties.Id id) {
                                                        return "id";
                                                    }

                                                    public Object visit(Distribution distribution) {
                                                        return "dist";
                                                    }

                                                    public Object visit(Dist dist) {
                                                        return "Dist";
                                                    }

                                                    public Object visit(BlockFactory blockFactory) {
                                                        return "makeBlock";
                                                    }

                                                    public Object visit(BlockCyclicFactory blockCyclicFactory) {
                                                        // BlockCyclicFactory is not supported in V2 anymore.
                                                        //            return "makeBlockCyclic";
                                                        return "makeBlock";
                                                    }

                                                    public Object visit(UniqueFactory uniqueFactory) {
                                                        return "makeUnique";
                                                    }

                                                    public Object visit(jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Region region) {
                                                        return "Region";
                                                    }

                                                    public Object visit(EmptyFactory emptyFactory) {
                                                        return "makeEmpty";
                                                    }

                                                    public Object visit(High high) {
                                                        //return "max(0)";
                                                        return "max";
                                                    }

                                                    public Object visit(Low low) {
                                                        //return "min(0)";
                                                        return "min";
                                                    }

                                                    public Object visit(Size size) {
                                                        return "size";
                                                    }

                                                    public Object visit(Projection projection) {
                                                        return "projection";
                                                    }

                                                    public Object visit(Contains contains) {
                                                        return "contains";
                                                    }

                                                    public Object visit(jsrc.x10.ast.tree.expression.id.specialids.x10.region.properties.Region region) {
                                                        return "region";
                                                    }

                                                    public Object visit(jsrc.x10.ast.tree.expression.id.specialids.x10.classes.System system) {
                                                        return "System";
                                                    }

                                                    public Object visit(CurrentTime currentTime) {
                                                        return "currentTimeMillis";
                                                    }

                                                };
                                            }
                                        };
                                        }
                                };
                            }
                    };
                    }
                };
            }
        };
    }

    private class BracedBlock {
        public Block block;

        public BracedBlock(Block block) {
            this.block = block;
        }

        public Text print() {
            TextSeq textSeq = textFact.newText();
            VarDeclOrSt[] sts = block.statements;

            textSeq.endLine("{");

            visitStatements(textSeq, sts);

            textSeq.startLine("}");
            return textSeq.get();
        }
    }

    private void visitStatements(TextSeq textSeq, VarDeclOrSt[] sts) {
                textSeq.incIndent();
                for (VarDeclOrSt statement : sts) {
//            if (statement == null) {
//                System.out.println("null in Block");
//                System.out.println(sts);
//            }
                    if (statement instanceof VarDecl)
                        textSeq.put(fileVisitor.classVisitor.shared.visit((VarDecl)statement));
                    else
                        textSeq.put(fileVisitor.classVisitor.shared.statementVisitor.visitDispatch((Statement)statement));
                }

                textSeq.decIndent();
            }




            public static final String LIB =
                        "\n" +
                        "class COMPILER_INSERTED_LIB {\n" +
                        "\n" +
                        "\tpublic static def coord(region: Region, pointIndex: Int, coordIndex: Int): Int {\n" +
                        "\t\tval iterator = (region.iterator() as (Iterator[Point]));\n" +
                        "\t\tvar i: Int = 0;\n" +
                        "\t\twhile (iterator.hasNext()) {\n" +
                        "\t\t\tval point = iterator.next();\n" +
                        "\t\t\tif (i == pointIndex)\n" +
                        "\t\t\t\treturn point(coordIndex);\n" +
                        "\t\t\ti = i + 1;\n" +
                        "\t\t}\n" +
                        "\t\treturn -1;\n" +
                        "\t}\n" +
                        "\n" +
                        "\tpublic static def ordinal(region: Region, point: Point): Int {\n" +
                        "\t\tval iterator = (region.iterator() as (Iterator[Point]));\n" +
                        "\t\tvar i: Int = 0;\n" +
                        "\t\twhile (iterator.hasNext()) {\n" +
                        "\t\t\tval p = iterator.next();\n" +
                        "\t\t\tif (point.equals(p))\n" +
                        "\t\t\t\treturn i;\n" +
                        "\t\t\ti = i + 1;\n" +
                        "\t\t}\n" +
                        "\t\treturn -1;\n" +
                        "\t}\n" +
                        "\n" +
                        "\tprivate static def traverseAll(array: DistArray[Int], fun: (Int, Int) => (Int)): Int {\n" +
                        "\t\tval places = array.dist.places();\n" +
                        "\t\t//var region: Region(1) = Region.makeEmpty(1);\n" +
                        "\t\t//for (place in places) {\n" +
                        "\t\t\t//region = region + [place.id];\n" +
                        "\t\t\t//region = region + Point.make(place.id);\n" +
                        "\t\t\t// \"Region +\" does not work in this implementation!\n" +
                        "\t\t\t//region = region.union(Region.make(place.id, place.id));\n" +
                        "\t\t//}\n" +
                        "\t\tval region = 0..(places.size()-1);\n" +
                        "\t\tval centralArray = DistArray.make[Int](region->here, (p:Point(1)) => 0);\n" +
                        "\t\tval center = here;\n" +
                        "\t\tfinish {\n" +
                        "\t\t\tvar i: Int = -1;\n" +
                        "\t\t\tfor (place in places) {\n" +
                        "\t\t\ti = i + 1;\n" +
                        "\t\t\tval j = i;\n" +
                        "\t\t\t\tasync at (place) {\n" +
                        "\t\t\t\t\tval localPart = array | here;\n" +
                        "\t\t\t\t\tvar result: Int = 0;\n" +
                        "\t\t\t\t\tfor (point in localPart)\n" +
                        "\t\t\t\t\t\tresult = fun(result, array(point));\n" +
                        "\t\t\t\t\tval finalResult = result;\n" +
                        "\t\t\t\t\tasync at (center) {\n" +
                        "\t\t\t\t\t\tcentralArray(Point.make(j)) = finalResult;\n" +
                        "\t\t\t\t\t}\n" +
                        "\t\t\t\t}\n" +
                        "\t\t\t}\n" +
                        "\t\t}\n" +
                        "\t\tvar result: Int = 0;\n" +
                        "\t\tfor (point in centralArray.region)\n" +
                        "\t\t\tresult += centralArray(point);\n" +
                        "\t\treturn result;\n" +
                        "\t}\n" +
                        "\tprivate static def traverseAll(array: DistArray[Double], fun: (Double, Double) => (Double)): Double {\n" +
                        "\t\tval places = array.dist.places();\n" +
                        "\t\t//var region: Region(1) = Region.makeEmpty(1);\n" +
                        "\t\t//for (place in places) {\n" +
                        "\t\t\t//region = region + [place.id];\n" +
                        "\t\t\t//region = region + Point.make(place.id);\n" +
                        "\t\t\t// \"Region +\" does not work in this implementation!\n" +
                        "\t\t\t//region = region.union(Region.make(place.id, place.id));\n" +
                        "\t\t//}\n" +
                        "\t\tval region = 0..(places.size()-1);\n" +
                        "\t\tval centralArray = DistArray.make[Double](region->here, (p:Point(1)) => 0.0);\n" +
                        "\t\tval center = here;\n" +
                        "\t\tfinish {\n" +
                        "\t\t\tvar i: Int = -1;\n" +
                        "\t\t\tfor (place in places) {\n" +
                        "\t\t\ti = i + 1;\n" +
                        "\t\t\tval j = i;\n" +
                        "\t\t\t\tasync at (place) {\n" +
                        "\t\t\t\t\tval localPart = array | here;\n" +
                        "\t\t\t\t\tvar result: Double = 0;\n" +
                        "\t\t\t\t\tfor (point in localPart)\n" +
                        "\t\t\t\t\t\tresult = fun(result, array(point));\n" +
                        "\t\t\t\t\tval finalResult = result;\n" +
                        "\t\t\t\t\tasync at (center) {\n" +
                        "\t\t\t\t\t\tcentralArray(Point.make(j)) = finalResult;\n" +
                        "\t\t\t\t\t}\n" +
                        "\t\t\t\t}\n" +
                        "\t\t\t}\n" +
                        "\t\t}\n" +
                        "\t\tvar result: Double = 0;\n" +
                        "\t\tfor (point:Point(1) in centralArray.region)\n" +
                        "\t\t\tresult += centralArray(point);\n" +
                        "\t\treturn result;\n" +
                        "\t}\n" +
                        "\n" +
                        "\tpublic static def sum(array: DistArray[Int]): Int {\n" +
                        "\t\treturn traverseAll(array, (i1: Int, i2: Int) => i1 + i2);\n" +
                        "\t}\n" +
                        "\tpublic static def sum(array: DistArray[Double]): Double {\n" +
                        "\t\treturn traverseAll(array, (i1: Double, i2: Double) => i1 + i2);\n" +
                        "\t}\n" +
                        "\tpublic static def max(array: DistArray[Int]): Int {\n" +
                        "\t\treturn traverseAll(array, (i1: Int, i2: Int) => Math.max(i1,i2));\n" +
                        "\t}\n" +
                        "\tpublic static def max(array: DistArray[Double]): Double {\n" +
                        "\t\treturn traverseAll(array, (i1: Double, i2: Double) => Math.max(i1,i2));\n" +
                        "\t}\n" +
                        "}";

}


