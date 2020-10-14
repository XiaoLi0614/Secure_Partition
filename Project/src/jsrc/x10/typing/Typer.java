package jsrc.x10.typing;


/*
public class Typer implements SVisitor<Type> {

    private SymbolTable<Type> symbolTable = new SymbolTable<Type>();

    private TextNode visitDispatch(Node node) {
        return node.accept(this);
    }

    public Type visit(File file) {
        ClassDecl classDecl[] = file.classDecls;
        for (ClassDecl decl : classDecl) {
            visitDispatch(decl);
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Visibility visibility) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(ClassDecl classDecl) {
        symbolTable.startScope();
        FieldDecl[] fieldDecls = classDecl.fieldDecls;
        for (FieldDecl fieldDecl : fieldDecls) {
            visitDispatch(fieldDecl);
        }
        MethodDecl[] methodDecls = classDecl.methodDecls;
        for (MethodDecl methodDecl : methodDecls) {
            visitDispatch(methodDecl);
        }
        symbolTable.endScope();
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(FieldDecl fieldDecl) {
        symbolTable.put(fieldDecl.name.name, fieldDecl.type);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(ConstructorDecl constructorDecl) {
        symbolTable.startScope();
        methodDecl.formalParams.apply(new Fun0<Object>() {
            public Object apply() {
                return null;
            }
        }, new Fun<FormalParam[], Object>() {
            public Object apply(FormalParam[] input) {
                for (FormalParam formalParam : input) {
                    visitDispatch(input)
                }
            }
        });
        visitDispatch(constructorDecl.block);
        symbolTable.endScope();
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(MethodDecl methodDecl) {
        symbolTable.startScope();
        methodDecl.formalParams.apply(new Fun0<Object>() {
            public Object apply() {
                return null;
            }
        }, new Fun<FormalParam[], Object>() {
            public Object apply(FormalParam[] input) {
                for (FormalParam formalParam : input) {
                    visitDispatch(input)
                }
            }
        });

        visitDispatch(methodDecl.block);
        symbolTable.endScope();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(FormalParam formalParam) {
        symbolTable.put(formalParam.name, formalParam.type);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(VarDecl varDecl) {
        if (!(varDecl.type instanceof ArrayType)) {
            varDecl.expression.apply(
                new Fun0<Object>() {
                    public Object apply() {
                        return null;
                    }
                },
                new Fun<Expression, Object>() {
                    public Object apply(Expression input) {

                    }
                }
            );

            if (symbolTable.get(newArray.regionOrDistName.name) instanceof RegionType)
                return newArray.
            else if (symbolTable.get(newArray.regionOrDistName.name) instanceof DistType)
                seq.put("DistArray.make");
            else
                throw new RuntimeException("Unkown type at new array");
        } else
            symbolTable.put(varDecl.name, varDecl.type);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Async async) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Finish finish) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Assignment assignment) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(ExpSt expSt) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Throw theThrow) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(ValueReturn valueReturn) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(VoidReturn voidReturn) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(If theIf) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(IteratorFor theFor) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(While theWhile) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(DoWhile doWhile) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Break theBreak) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Continue theContinue) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Switch theSwitch) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Print print) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Println println) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(PrintError printError) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Block block) {
        BlockSt[] blocksts = block.statements;
        for (BlockSt blockst : blocksts) {
            visitDispatch(blockst);
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Literal literal) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(IntegerSize integerSize) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(UnaryOp unaryOp) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(BinaryOp binaryOp) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Id id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(New theNew) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(NewArray newArray) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(DistAccess distAccess) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(FieldSelection fieldSelection) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(MethodCall methodCall) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Coercion coercion) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Conditional conditional) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(PointConstructor pointConstructor) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(RegionConstructor regionConstructor) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(DistConstructor distConstructor) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Parenthesized parenthesized) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Coord coord) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Ordinal ordinal) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Max max) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Sum sum) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Type visit(Type type) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
*/


