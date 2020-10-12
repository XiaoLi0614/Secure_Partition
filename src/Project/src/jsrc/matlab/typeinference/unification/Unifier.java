package jsrc.matlab.typeinference.unification;

import jsrc.matlab.ast.tree.declaration.type.MatrixType;
import jsrc.matlab.ast.tree.declaration.type.ScalarType;
import jsrc.matlab.typeinference.exceptions.*;
import jsrc.matlab.typeinference.type.*;
import lesani.collection.xtolook.Either;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.ast.LocInfo;
import lesani.collection.func.Fun;
import lesani.compiler.typing.substitution.IndexVarSubst;
import lesani.compiler.typing.substitution.Substitution;
import lesani.compiler.typing.substitution.TypeVarSubst;
import lesani.compiler.typing.substitution.VarSubst;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static lesani.math.LMath.plusOneOrdinal;
import static lesani.gui.console.Logger.*;

public class Unifier {

    public static Substitution solve(List<Constraint> constraints, fj.data.List<List<List<Constraint>>> altConsts) throws TypeMismatches {
        final Substitution subst = (new Unifier(constraints, altConsts)).solve();
        println();
        println("Substitution");
        println(subst);
        return subst;
    }
    
    private List<Constraint> constraints;
    private fj.data.List<List<List<Constraint>>> altConsts;
    //private Substitution substitution;

    private List<TypeMismatch> mismatches = new LinkedList<TypeMismatch>();


    private Unifier(List<Constraint> constraints, fj.data.List<List<List<Constraint>>> altConsts) {
        this.constraints = constraints;
        this.altConsts = altConsts;
    }

    public Substitution solve() throws TypeMismatches {
        Substitution substitution = new Substitution();
        try {
            while (!constraints.isEmpty()) {
                Iterator<Constraint> iterator = constraints.iterator();
                Constraint constraint = iterator.next();
                iterator.remove();
                substitution = processConstraint(substitution, constraint,
                        new LinkedList<Integer>(),
                        new LinkedList<Constraint>());
            }
        } catch (TypeMismatch typeMismatch) {
            throw new TypeMismatches(typeMismatch, constraints, altConsts);
        }

        return processAlts(substitution, altConsts,
                new LinkedList<Integer>(),
                new LinkedList<Constraint>());
    }



/*
    // The version to search for the second typing.
    Substitution foundSubstitution = null;
    private Substitution processAlts(
                Substitution substitution,
                fj.data.List<List<List<Constraint>>> altConsts,
                LinkedList<Integer> altNos,
                LinkedList<Constraint> visitedConsts
    ) throws TypeMismatches {

        if (altConsts.isEmpty()) {
            // save substitution here.
            if (foundSubstitution != null)
                throw new MoreThanOneTypeException(foundSubstitution, substitution);
            else
                foundSubstitution = substitution;
            return null;
        }

        // We get a node
        List<List<Constraint>> alts = altConsts.head();

        fj.data.List<List<List<Constraint>>> altsRest = altConsts.tail();

        int altNo = 0;
        for (List<Constraint> alt : alts) {
            altNo++;
            LinkedList<Integer> newAltNos = (LinkedList<Integer>)altNos.clone();
            LinkedList<Constraint> newVisitedConstrains = (LinkedList<Constraint>)visitedConsts.clone();
            newAltNos.add(altNo);

            // We get its children
            Substitution subst = substitution;
            try {
                for (Constraint constraint : alt) {
//                    newVisitedConstrains.add(constraint);
                    subst = processConstraint(subst, constraint, newAltNos, newVisitedConstrains);
                }
                while (!constraints.isEmpty()) {
                    Iterator<Constraint> it = constraints.iterator();
                    Constraint constraint = it.next();
                    it.remove();
                    subst = processConstraint(subst, constraint, newAltNos, newVisitedConstrains);
                }
            } catch (TypeMismatch typeMismatch) {
                mismatches.add(typeMismatch);
                constraints.clear();
                continue;
            }
            try {
                processAlts(subst, altsRest, newAltNos, newVisitedConstrains);
            } catch (TypeMismatches typeMismatches) {
                // This branch doesn't work.
                // The mismatches in this TypeMismatch are previously added to the mismatches set.
                // Try the next child
            }
        }
        // If the root is returning
        if (altConsts == this.altConsts) {
            if (foundSubstitution != null)
                return foundSubstitution;
            else
                throw new TypeMismatches(mismatches, constraints, altConsts);
        } else
            return null;
    }
*/

    // The version to find only the first typing.
    private Substitution processAlts(
                Substitution substitution,
                fj.data.List<List<List<Constraint>>> altConsts,
                LinkedList<Integer> altNos,
                LinkedList<Constraint> visitedConsts
    ) throws TypeMismatches {

        if (altConsts.isEmpty()) {
//            System.out.println(altNos);
            return substitution;
        }

        // We get a node
        List<List<Constraint>> alts = altConsts.head();

        fj.data.List<List<List<Constraint>>> altsRest = altConsts.tail();

        int altNo = 0;
        for (List<Constraint> alt : alts) {
            altNo++;
//            LinkedList<Integer> newAltNos = (LinkedList<Integer>)altNos.clone();
//            newAltNos.add(altNo);
              LinkedList<Integer>  newAltNos = altNos;
//            LinkedList<Constraint> newVisitedConstrains = (LinkedList<Constraint>)visitedConsts.clone();
            LinkedList<Constraint> newVisitedConstrains = visitedConsts;

            // We get its children
            Substitution subst = substitution;
            try {
                for (Constraint constraint : alt) {
//                    newVisitedConstrains.add(constraint);
                    subst = processConstraint(subst, constraint, newAltNos, newVisitedConstrains);
                }
                while (!constraints.isEmpty()) {
                    Iterator<Constraint> it = constraints.iterator();
                    Constraint constraint = it.next();
                    it.remove();
                    subst = processConstraint(subst, constraint, newAltNos, newVisitedConstrains);
                }
            } catch (TypeMismatch typeMismatch) {
                mismatches.add(typeMismatch);
                constraints.clear();
                continue;
            }
            try {
                return processAlts(subst, altsRest, newAltNos, newVisitedConstrains);
            } catch (TypeMismatches typeMismatches) {
                // This branch doesn't work.
                // The mismatches in this TypeMismatch are previously added to the mismatches set.
                // Try the next child
            }
        }
        throw new TypeMismatches(mismatches, constraints, altConsts);
    }


    private Substitution processConstraint(final Substitution substitution, final Constraint constr, LinkedList<Integer> altNos, LinkedList<Constraint> visitedConstrains) throws TypeMismatch {
        visitedConstrains.add(constr);
//        System.out.println(constr);
        final Constraint constraint;
        try {
            constraint = constr.apply(substitution);
        } catch (NonScalarElementException re) {
            throw new TypeMismatch(constr, constr, altNos,
                    visitedConstrains, substitution);
        }

        Option<Substitution> option =
        constraint.left.apply(
            new Fun<Type, Option<Substitution>>() {
                public Option<Substitution> apply(final Type type1) {
                    return constraint.right.apply(
                        new Fun<Type, Option<Substitution>>() {
                            public Option<Substitution> apply(Type type2) {

                                final int compilationUnitNo = constraint.compilationUnitNo;
                                final LocInfo locInfo = constraint.locInfo;
                                final String text = constraint.text;

                                if ((type1 == null) || (type2 == null)) {
                                    println();
                                    println("type1 = " + type1);
                                    println("type1 = " + type2);
                                    println("loc = " + locInfo);
                                    throw new RuntimeException();
                                }

                                if (type1 == type2)
                                    return new Some<Substitution>(substitution);
                                else if (type1 instanceof TypeVar) {
                                    TypeVar typeVar1 = (TypeVar) type1;
                                    return new Some<Substitution>(processTypeVarAndType(substitution, typeVar1, type2));
                                } else if (type2 instanceof TypeVar) {
                                    TypeVar typeVar2 = (TypeVar) type2;
                                    return new Some<Substitution>(processTypeVarAndType(substitution, typeVar2, type1));
                                } else if ((type1 instanceof MatrixType) && (type2 instanceof MatrixType)) {
                                    MatrixType matrixType1 = (MatrixType) type1;
                                    MatrixType matrixType2 = (MatrixType) type2;
                                    processGMatrixTypes(
                                            matrixType1, matrixType2,
                                            compilationUnitNo, locInfo, text);
                                    return new Some<Substitution>(substitution);
                                } else if ((type1 instanceof TupleType) && (type2 instanceof TupleType)) {
                                    TupleType tupleType1 = (TupleType) type1;
                                    TupleType tupleType2 = (TupleType) type2;
                                    processTupleTypes(
                                            tupleType1, tupleType2,
                                            compilationUnitNo, locInfo, text);
                                    return new Some<Substitution>(substitution);
                                } else if ((type1 instanceof DFunType) && (type2 instanceof DFunType)) {
                                    DFunType funType1 = (DFunType) type1;
                                    DFunType funType2 = (DFunType) type2;
                                    processFunTypes(
                                            funType1, funType2,
                                            compilationUnitNo, locInfo, text);
                                    return new Some<Substitution>(substitution);
                                }
                                return None.instance();
                            }
                        },
                        new Fun<Integer, Option<Substitution>>() {
                            public Option<Substitution> apply(Integer int2) {
                                if (!(type1 instanceof TypeVar))
                                    return None.instance();
                                TypeVar typeVar1 = (TypeVar) type1;
                                return new Some<Substitution>(processTypeVarAndInt(substitution, typeVar1, int2));
                            }
                        }
                    );
                }
            },
            new Fun<Integer, Option<Substitution>>() {
                public Option<Substitution> apply(final Integer int1) {
                    return constraint.right.apply(
                        new Fun<Type, Option<Substitution>>() {
                            public Option<Substitution> apply(Type type2) {
                                if (!(type2 instanceof TypeVar))
                                    return None.instance();
                                TypeVar typeVar2 = (TypeVar) type2;
                                return new Some<Substitution>(processTypeVarAndInt(substitution, typeVar2, int1));
                            }
                        },
                        new Fun<Integer, Option<Substitution>>() {
                            public Option<Substitution> apply(Integer int2) {
                                if (int1.equals(int2))
                                    return new Some<Substitution>(substitution);
                                else
                                    return None.instance();
                            }
                        }
                    );
                }
            }
        );


        if (!option.isPresent())
            throw new TypeMismatch(constraint, constr, altNos,
                    visitedConstrains, substitution);
        else
            return option.value();

    }


    private Substitution processTypeVarAndInt(Substitution substitution, TypeVar typeVar, Integer integer) {
        return newVarSubst(substitution, typeVar, integer);
    }


    private Substitution processTypeVarAndType(Substitution substitution, TypeVar typeVar1, Type type2) {
//        System.out.println(left);
//        System.out.println(right);

        if (!type2.contains(typeVar1))
            return newVarSubst(substitution, typeVar1, type2);
        else
            throw new RuntimeException("");
    }

    private void processTupleTypes(final TupleType tupleType1,
                                           final TupleType tupleType2,
                                           final int compilationUnitNo,
                                           final LocInfo locInfo,
                                           final String text) {


        DataType[] elemTypes1 = tupleType1.types;
        DataType[] elemTypes2 = tupleType2.types;

        Constraint constraint;
        constraint = new Constraint(
                elemTypes1.length, elemTypes2.length,
                compilationUnitNo, locInfo,
                text + " - " + "The length of the two tuples");
        constraints.add(constraint);

        for (int i = 0; i < elemTypes1.length; i++) {
            constraint = new Constraint(
                    elemTypes1[i], elemTypes2[i],
                    compilationUnitNo, locInfo,
                    text + " - " + "The " + plusOneOrdinal(i) + " element of the two tuples");
            constraints.add(constraint);
        }

    }


    private void processGMatrixTypes(
               final MatrixType matrixType1,
               final MatrixType matrixType2,
               final int compilationUnitNo,
               final LocInfo locInfo,
               final String text) {

        final Type type1 = matrixType1.elemType.apply(
            new Fun<ScalarType, Type>() {
                public Type apply(ScalarType scalarType) {
                    return scalarType;
                }
            },
            new Fun<TypeVar, Type>() {
                public Type apply(TypeVar typeVar) {
                    return typeVar;
                }
            }
        );
        final Type type2 = matrixType2.elemType.apply(
            new Fun<ScalarType, Type>() {
                public Type apply(ScalarType scalarType) {
                    return scalarType;
                }
            },
            new Fun<TypeVar, Type>() {
                public Type apply(TypeVar typeVar) {
                    return typeVar;
                }
            }
        );

        final Constraint constraint = new Constraint(type1, type2, compilationUnitNo, locInfo,
                text + " - " + "Elements of the two matrices " + matrixType1 + ", " + matrixType2);
        constraints.add(constraint);

//        System.out.println("Unifier.processGMatrixTypes");
//        System.out.println("matrixType1 = " + matrixType1);
//        System.out.println("matrixType2 = " + matrixType2);
//        System.out.println("type1 = " + type1);
//        System.out.println("type2 = " + type2);
//        System.out.println(constraint);


//        TODO: For now we do not consider these constraints.
//        Constraint constraint1 = extractConstraint(matrixType1.n, matrixType2.n, compilationUnitNo, locInfo, text + " - " + "The row count");
//        Constraint constraint2 = extractConstraint(matrixType1.m, matrixType2.m, compilationUnitNo, locInfo, text + " - " + "The column count");
//        constraints.add(constraint1);
//        constraints.add(constraint2);
    }

    private void processFunTypes(DFunType funType1, DFunType funType2, int compilationUnitNo, LocInfo locInfo, String text) {

        DataType[] inputTypes1 = funType1.inputs;
        DataType outputType1 = funType1.output;

        DataType[] inputTypes2 = funType2.inputs;
        DataType outputType2 = funType2.output;

        Constraint constraint = new Constraint(
                inputTypes1.length, inputTypes2.length,
                compilationUnitNo, locInfo,
                text + " - " + "The parameter count of the two function types");
        constraints.add(constraint);

        for (int i = 0; i < inputTypes1.length; i++)
            constraints.add(new Constraint(inputTypes1[i], inputTypes2[i], compilationUnitNo, locInfo,
                    text + " - " + plusOneOrdinal(i) + "parameter of the two function types"));
        constraints.add(new Constraint(outputType1, outputType2, compilationUnitNo, locInfo,
                text + " - " + "The return value of the two function types"));
    }

    private Substitution newVarSubst(Substitution substitution, TypeVar typeVar, Type type) {
        VarSubst varSubst = new TypeVarSubst(typeVar, type);
        return substitution.add(varSubst);
    }

    private Substitution newVarSubst(Substitution substitution, TypeVar typeVar, Integer integer) {
        VarSubst indexVarSubst = new IndexVarSubst(typeVar, integer);
        return substitution.add(indexVarSubst);
    }


    private Constraint extractConstraint(Either<TypeVar, Integer> n1, final Either<TypeVar, Integer> n2,
                                         final int compilationUnitNo, final LocInfo locInfo, final String text) {
        return n1.apply(
            new Fun<TypeVar, Constraint>() {
                public Constraint apply(final TypeVar type1) {
                    return n2.apply(
                        new Fun<TypeVar, Constraint>() {
                            public Constraint apply(TypeVar type2) {
                                Constraint constraint = new Constraint(type1, type2, compilationUnitNo, locInfo, text);
//                                constraints.add(constraint);
                                return constraint;
                            }
                        },
                        new Fun<Integer, Constraint>() {
                            public Constraint apply(Integer i2) {
                                Constraint constraint = new Constraint(type1, i2, compilationUnitNo, locInfo, text);
//                                constraints.add(constraint);
                                return constraint;
                            }
                        }
                    );
                }
            },
            new Fun<Integer, Constraint>() {
                public Constraint apply(final Integer i1) {
                    return n2.apply(
                        new Fun<TypeVar, Constraint>() {
                            public Constraint apply(TypeVar type2) {
                                Constraint constraint = new Constraint(type2, i1, compilationUnitNo, locInfo, text);
//                                constraints.add(constraint);
                                return constraint;
                            }
                        },
                        new Fun<Integer, Constraint>() {
                            public Constraint apply(Integer i2) {
                                Constraint constraint = new Constraint(i1, i2, compilationUnitNo, locInfo, text);
                                return constraint;
                            }
                        }
                    );
                }
            }
        );
    }
}


