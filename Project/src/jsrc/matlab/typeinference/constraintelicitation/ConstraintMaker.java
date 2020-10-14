package jsrc.matlab.typeinference.constraintelicitation;

import jsrc.matlab.ast.tree.declaration.type.HVectorType;
import jsrc.matlab.ast.tree.declaration.type.ScalarType;
import jsrc.matlab.typeinference.exceptions.SemanticErrorException;
import jsrc.matlab.typeinference.type.*;
import jsrc.matlab.typeinference.type.TypeVar;
import jsrc.matlab.typeinference.unification.Constraint;
import lesani.collection.Pair;
import lesani.collection.Triple;
import lesani.collection.option.Option;
import lesani.compiler.ast.LocInfo;

import static lesani.math.LMath.*;

import java.util.LinkedList;
import java.util.List;

import static fj.data.List.list;

public class ConstraintMaker {

    private List<Constraint> constraints = new LinkedList<Constraint>();
    private fj.data.List<List<List<Constraint>>> altConsts = list();
    private boolean inAlt = false;
    private List<List<Constraint>> alts;
    private List<Constraint> thisAlt;
    public DataType origType = null;


    public TypeVar addConstrainsFor(
            List<SingleFunType> funTypes,
            final DataType[] argTypes,
            final Option<Value>[] argValues,
            final int compilationUnitNo,
            final LocInfo locInfo,
            final String opText) {
        return addConstrainsFor(funTypes, argTypes, argValues, null, null, compilationUnitNo, locInfo, opText);
    }

    public TypeVar addConstrainsFor(
            List<SingleFunType> funTypes,
            final DataType[] argTypes,
            final Option<Value>[] argValues,
            final String[] messages,
            final int compilationUnitNo,
            final LocInfo locInfo,
            final String opText) {
        return addConstrainsFor(funTypes, argTypes, argValues, messages, null, compilationUnitNo, locInfo, opText);
    }
    public TypeVar addConstrainsFor(
            List<SingleFunType> funTypes,
            final DataType[] argTypes,
            final Option<Value>[] argValues,
            final String[] startMessages,
            final String[] endMessages,
            final int compilationUnitNo,
            final LocInfo locInfo,
            final String opText) {


        int altCount = 0;
        final TypeVar resultVar = TypeVar.fresh();
        origType = TypeVar.fresh();
        newAlts();
        for (SingleFunType singleFunType : funTypes) {
            DataType[] paramTypes;
            DataType resultType;
            if (singleFunType instanceof DFunType) {
                DFunType dFunType = (DFunType) singleFunType;
                paramTypes = dFunType.inputs;
                resultType = dFunType.output;
            //} else if (singleFunType instanceof VarArgFunType) {
            } else /*if (singleFunType instanceof ValueDepFunType)*/ {
                ValueDepFunType valueDepFunType = (ValueDepFunType) singleFunType;
                paramTypes = valueDepFunType.inputs;
                if (!(paramTypes.length == argTypes.length))
                    continue;
                resultType = valueDepFunType.computer.computeOutputType(argValues);
            }

//            System.out.println(paramTypes.length);
//            System.out.println(argTypes.length);

            if (!(paramTypes.length == argTypes.length))
                continue;

            newAlt();
                for (int i = 0; i < paramTypes.length; i++) {
                    String operandMsg = makeOperandMessage(argTypes.length, startMessages, endMessages, opText, i);
                    addConstraint(
                            argTypes[i], paramTypes[i],
                            compilationUnitNo, locInfo, operandMsg);
                }
                final String resultMsg = "Result of " + opText /*+ " operation/call/indexing"*/;
//                if (opText.equals("a")) {
//                    System.out.println(compilationUnitNo);
//                    System.out.println(locInfo);
//                    throw new RuntimeException();
//                }

                addConstraint(
                        resultVar, resultType,
                        compilationUnitNo, locInfo, resultMsg);
            finishAlt();
            altCount++;

            Triple<Boolean, Constraint[], DataType> hVectorReturn =
                processResultType(resultType,
                                  compilationUnitNo, locInfo);

            if (hVectorReturn._1()) {
                newAlt();

                    for (int i = 0; i < paramTypes.length; i++) {
                        String operandMsg = makeOperandMessage(argTypes.length, startMessages, endMessages, opText, i);
                        addConstraint(
                                argTypes[i], paramTypes[i],
                                compilationUnitNo, locInfo, operandMsg);
                    }
                    final String resMsg = "Result of " + opText /*+ " operation/call/indexing"*/;

                    addConstraint(origType, resultType, compilationUnitNo, locInfo, "orig type!");
                    resultType = hVectorReturn._3();
//                    System.out.println("resultType = " + resultType);
                    addConstraint(
                            resultVar, resultType,
                            compilationUnitNo, locInfo, resMsg);
                    for (int i = 0; i < hVectorReturn._2().length; i++) {
                        Constraint constraint = hVectorReturn._2()[i];
                        addConstraint(constraint);
                    }
                finishAlt();
                altCount++;
            }

        }
        addAlts();

        if (altCount == 0)
            throw new SemanticErrorException(compilationUnitNo, locInfo,
                    "Parameter and argument count mismatch for " + opText);
        return resultVar;
    }

    private Triple<Boolean, Constraint[], DataType> processResultType(DataType resultType, int compilationUnitNo, LocInfo locInfo) {
        if (!(resultType instanceof TupleType))
            return new Triple<Boolean, Constraint[], DataType>(false, null, null);

        TupleType tupleType = (TupleType) resultType;
        DataType[] elemTypes = tupleType.types;
        Constraint[] constraints = new Constraint[elemTypes.length];

        DataType theType = elemTypes[0];

        for (int i = 0, elemTypesLength = elemTypes.length; i < elemTypesLength; i++) {
            DataType elemType = elemTypes[i];
            if (!((elemType instanceof ScalarType) || (elemType instanceof TypeVar)))
                return new Triple<Boolean, Constraint[], DataType>(false, null, null);
                // This can allow matrix of matrix types! To do ...
            constraints[i] = newConstraint(elemType, theType, compilationUnitNo, locInfo, "Elements of the returned vector must be of the same type.");
        }
        HVectorType hVectorType;
        if (theType instanceof ScalarType) {
            ScalarType type = (ScalarType) theType;
            hVectorType = new HVectorType(type, elemTypes.length);
        } else {
            TypeVar type = (TypeVar) theType;
            hVectorType = new HVectorType(type, elemTypes.length);
        }

//        System.out.println("hVectorType = " + hVectorType);
//        for (int i = 0; i < constraints.length; i++) {
//            Constraint constraint = constraints[i];
//            System.out.println("constraint = " + constraint);
//        }
        return new Triple<Boolean, Constraint[], DataType>(true, constraints, hVectorType);

        // Converts a TupleType to a HVectorType if all the element are of the same type.
        // Maybe this should go to a later phase to support this for user defined functions too.
    }

    private String makeOperandMessage(int argCount, String[] startMessages, String[] endMessages, String opText, int i) {
        String operandMsg;
        final int startMessagesLength = (startMessages==null) ? 0 : startMessages.length;
        final int endMessagesLength = (endMessages==null) ? 0 : endMessages.length;
        if (i < startMessagesLength)
            operandMsg = startMessages[i];
        else if (i >= argCount - endMessagesLength)
            operandMsg = endMessages[i - (argCount - endMessagesLength)];
        else
            operandMsg = plusOneOrdinal(i - startMessagesLength) + " operand/argument of " + opText /*+ " operation/call/indexing"*/;
        return operandMsg;
    }

    public void addAltConstrainsFor(DataType type, DataType[] dataTypes, int compilationUnitNo, LocInfo locInfo, String text) {
        newAlts();
        for (DataType dataType : dataTypes) {
            newAlt();
            addConstraint(type, dataType, compilationUnitNo, locInfo, text);
            finishAlt();
        }
        addAlts();
    }

    public void newAlts() {
        alts = new LinkedList<List<Constraint>>();
    }
    public void newAlt() {
        thisAlt = new LinkedList<Constraint>();
        inAlt = true;
    }
    public void finishAlt() {
        alts.add(thisAlt);
        inAlt = false;
    }
    public void addAlts() {
        altConsts = altConsts.snoc(alts);
    }

    private Constraint newConstraint(DataType type1, DataType type2, int compilationUnitNo, LocInfo locInfo, String text) {
        return new Constraint(type1, type2, compilationUnitNo, locInfo, text);
    }

    private Constraint newConstraint(int int1, int int2, int compilationUnitNo, LocInfo locInfo, String text) {
        return new Constraint(int1, int2, compilationUnitNo, locInfo, text);
    }

    public void addConstraint(Constraint constraint) {
        if (!inAlt)
            constraints.add(constraint);
        else
            thisAlt.add(constraint);
    }

    public void addConstraint(DataType type1, DataType type2,
                              int compilationUnitNo, LocInfo locInfo, String text) {
        if ((type1==null) || (type2==null))
            throw new RuntimeException(type1 + "-" + type2);

        Constraint constraint = newConstraint(type1, type2, compilationUnitNo, locInfo, text);
        if (!inAlt)
            constraints.add(constraint);
        else
            thisAlt.add(constraint);
    }

    public void addConstraint(int int1, int int2,
                              int compilationUnitNo, LocInfo locInfo, String text) {
        Constraint constraint = newConstraint(int1, int2, compilationUnitNo, locInfo, text);
        if (!inAlt)
            constraints.add(constraint);
        else
            thisAlt.add(constraint);
    }

    public Pair<List<Constraint>, fj.data.List<List<List<Constraint>>>> getResult() {
        return new Pair<List<Constraint>, fj.data.List<List<List<Constraint>>>>(constraints, altConsts);
    }





/*
    public static class ConvertedHVectorType extends HVectorType {
        public ConvertedHVectorType(ScalarType type, int length) {
            super(type, length);
        }

        public ConvertedHVectorType(TypeVar type, int length) {
            super(type, length);
        }

        public ConvertedHVectorType(Either<ScalarType, TypeVar> elemType, Either<TypeVar, Integer> m) {
            super(elemType, m);
        }

        public Either<lesani.compiler.typing.Type, Integer> apply(final VarSubst varSubst) {
            Either<ScalarType, TypeVar> xElemType = elemType.apply(
                new Fun<ScalarType, Either<ScalarType, TypeVar>>() {
                    public Either<ScalarType, TypeVar> apply(ScalarType scalarType) {
                        return new Either.Left<ScalarType, TypeVar>(scalarType);
                    }
                },
                new Fun<TypeVar, Either<ScalarType, TypeVar>>() {
                    public Either<ScalarType, TypeVar> apply(TypeVar var) {
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
                                            throw new RuntimeException();
                                            //We do not expect matrix or function types here.
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

            Either<TypeVar, Integer> xM = apply(m, varSubst);

            return new Either.Left<lesani.compiler.typing.Type, Integer>(new ConvertedHVectorType(xElemType, xM));
        }

        @Override
        public String toString() {
            return "ConvertedHVector[" + elemType +"](" + n + ", " + m + ")";
        }
    }
*/
}





