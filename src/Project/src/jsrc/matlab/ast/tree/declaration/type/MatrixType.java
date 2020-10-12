package jsrc.matlab.ast.tree.declaration.type;

import jsrc.matlab.ast.visitor.SVisitor;
import jsrc.matlab.typeinference.exceptions.NonScalarElementException;
import jsrc.matlab.typeinference.type.DataType;
import jsrc.matlab.typeinference.type.TypeVar;
import lesani.compiler.typing.substitution.VarSubst;
import lesani.collection.xtolook.Either;
import lesani.collection.func.Fun;

public class MatrixType implements DataType {

    public Either<ScalarType, TypeVar> elemType;
    public Either<TypeVar, Integer> n;
    public Either<TypeVar, Integer> m;

    public MatrixType(Either<ScalarType, TypeVar> elemType, Either<TypeVar, Integer> n, Either<TypeVar, Integer> m) {
        this.elemType = elemType;
        this.n = n;
        this.m = m;
    }

    public MatrixType(ScalarType elemType, int n, int m) {
        this.elemType = new Either.Left<ScalarType, TypeVar>(elemType);
        this.n = new Either.Right<TypeVar, Integer>(n);
        this.m = new Either.Right<TypeVar, Integer>(m);
    }

    public MatrixType(TypeVar typeVar, int n, int m) {
        this.elemType = new Either.Right<ScalarType, TypeVar>(typeVar);
        this.n = new Either.Right<TypeVar, Integer>(n);
        this.m = new Either.Right<TypeVar, Integer>(m);
    }

    public MatrixType(TypeVar typeVar, TypeVar n, TypeVar m) {
        this.elemType = new Either.Right<ScalarType, TypeVar>(typeVar);
        this.n = new Either.Left<TypeVar, Integer>(n);
        this.m = new Either.Left<TypeVar, Integer>(m);
    }

    public MatrixType(TypeVar typeVar, TypeVar n, int m) {
        this.elemType = new Either.Right<ScalarType, TypeVar>(typeVar);
        this.n = new Either.Left<TypeVar, Integer>(n);
        this.m = new Either.Right<TypeVar, Integer>(m);
    }

    public MatrixType(TypeVar typeVar, int n, TypeVar m) {
        this.elemType = new Either.Right<ScalarType, TypeVar>(typeVar);
        this.n = new Either.Right<TypeVar, Integer>(n);
        this.m = new Either.Left<TypeVar, Integer>(m);
    }

    public MatrixType(ScalarType elemType, TypeVar n, TypeVar m) {
        this.elemType = new Either.Left<ScalarType, TypeVar>(elemType);
        this.n = new Either.Left<TypeVar, Integer>(n);
        this.m = new Either.Left<TypeVar, Integer>(m);
    }

    public MatrixType(ScalarType elemType, TypeVar n, int m) {
        this.elemType = new Either.Left<ScalarType, TypeVar>(elemType);
        this.n = new Either.Left<TypeVar, Integer>(n);
        this.m = new Either.Right<TypeVar, Integer>(m);
    }

    public MatrixType(ScalarType elemType, int n, TypeVar m) {
        this.elemType = new Either.Left<ScalarType, TypeVar>(elemType);
        this.n = new Either.Right<TypeVar, Integer>(n);
        this.m = new Either.Left<TypeVar, Integer>(m);
    }

    public boolean contains(final TypeVar typeVar) {
        return elemType.apply(
            new Fun<ScalarType, Boolean>() {
                public Boolean apply(ScalarType input) {
                    return false;
                }
            },
            new Fun<TypeVar, Boolean>() {
                public Boolean apply(TypeVar thisTypeVar) {
                    return thisTypeVar == typeVar;
                }
            }
        );
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (this.getClass() != other.getClass()) return false;
        MatrixType otherMatrixType = (MatrixType) other;
        return elemType.equals(otherMatrixType.elemType) &&
                n == otherMatrixType.n &&
                m == otherMatrixType.m;
    }


    public boolean horizontal() {
        return n.apply(
            new Fun<TypeVar, Boolean>() {
                public Boolean apply(TypeVar input) {
                    return false;
                }
            },
            new Fun<Integer, Boolean>() {
                public Boolean apply(Integer i) {
                    return (i == 1);
                }
            }
        );
    }

    public boolean vertical() {
        return m.apply(
            new Fun<TypeVar, Boolean>() {
                public Boolean apply(TypeVar input) {
                    return false;
                }
            },
            new Fun<Integer, Boolean>() {
                public Boolean apply(Integer i) {
                    return (i == 1);
                }
            }
        );
    }

    public boolean hasKnownRowCount() {
        return n.apply(
            new Fun<TypeVar, Boolean>() {
                public Boolean apply(TypeVar input) {
                    return false;
                }
            },
            new Fun<Integer, Boolean>() {
                public Boolean apply(Integer input) {
                    return true;
                }
            }
        );
    }

    public boolean hasKnownColumnCount() {
        return m.apply(
            new Fun<TypeVar, Boolean>() {
                public Boolean apply(TypeVar input) {
                    return false;
                }
            },
            new Fun<Integer, Boolean>() {
                public Boolean apply(Integer input) {
                    return true;
                }
            }
        );
    }


    @Override
    public String toString() {
        return "Matrix[" + elemType +"](" + n + ", " + m + ")";
    }

    public Either<lesani.compiler.typing.Type, Integer> apply(final VarSubst varSubst) {
        Either<ScalarType, TypeVar> xElemType = elemType.apply(
            new Fun<ScalarType, Either<ScalarType, TypeVar>>() {
                public Either<ScalarType, TypeVar> apply(ScalarType scalarType) {
                    return new Either.Left<ScalarType, TypeVar>(scalarType);
                }
            },
            new Fun<TypeVar, Either<ScalarType, TypeVar>>() {
                public Either<ScalarType, TypeVar> apply(final TypeVar var) {
                    final Either<lesani.compiler.typing.Type, Integer> applied = var.apply(varSubst);
                    return applied.apply(
                            new Fun<lesani.compiler.typing.Type, Either<ScalarType, TypeVar>>() {
                                public Either<ScalarType, TypeVar> apply(lesani.compiler.typing.Type xType) {
                                    if (xType instanceof ScalarType) {
                                        final ScalarType scalarType = (ScalarType) xType;
                                        return new Either.Left<ScalarType, TypeVar>(scalarType);
                                    } else if (xType instanceof TypeVar) {
                                        final TypeVar typeVar = (TypeVar) xType;
                                        return new Either.Right<ScalarType, TypeVar>(typeVar);
                                    } else {
//                                        System.out.println("The whole matrix type = " + MatrixType.this);
//                                        System.out.println("Var type = " + var);
//                                        System.out.println("x Var Type = " + xType);
//                                        throw new RuntimeException();
                                        throw new NonScalarElementException();
                                        //We do not expect matrix or function types here.
                                        // Allow this for multi assignment

                                    }
                                }
                            },
                            new Fun<Integer, Either<ScalarType, TypeVar>>() {
                                public Either<ScalarType, TypeVar> apply(Integer integer) {
                                    throw new RuntimeException(); //We do not expect function types here.
                                }
                            }
                    );
                }
            }
        );

        Either<TypeVar, Integer> xN = apply(n, varSubst);
        Either<TypeVar, Integer> xM = apply(m, varSubst);

        return new Either.Left<lesani.compiler.typing.Type, Integer>(new MatrixType(xElemType, xN, xM));
    }

    protected static Either<TypeVar, Integer> apply(Either<TypeVar, Integer> n, final VarSubst varSubst) {
        return n.apply(
            new Fun<TypeVar, Either<TypeVar, Integer>>() {
                public Either<TypeVar, Integer> apply(TypeVar typeVar) {
                    return typeVar.apply(varSubst).apply(
                        new Fun<lesani.compiler.typing.Type, Either<TypeVar, Integer>>() {
                            public Either<TypeVar, Integer> apply(lesani.compiler.typing.Type xType) {
                                if (xType instanceof TypeVar) {
                                    TypeVar xTypeVar = (TypeVar) xType;
                                    return new Either.Left<TypeVar, Integer>(xTypeVar);
                                } else {
                                    throw new RuntimeException(); //Should not happen.
                                }
                            }
                        },
                        new Fun<Integer, Either<TypeVar, Integer>>() {
                            public Either<TypeVar, Integer> apply(Integer integer) {
                                return new Either.Right<TypeVar, Integer>(integer);
                            }
                        }
                    );
                }
            },
            new Fun<Integer, Either<TypeVar, Integer>>() {
                public Either<TypeVar, Integer> apply(Integer i) {
                    return new Either.Right<TypeVar, Integer>(i);
                }
            }
        );
    }

    public <T> T accept(SVisitor.TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }


//    public Either<lesani.compiler.typing.Type, Integer> apply(VarSubst<lesani.compiler.typing.Type> varSubst) {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
}


