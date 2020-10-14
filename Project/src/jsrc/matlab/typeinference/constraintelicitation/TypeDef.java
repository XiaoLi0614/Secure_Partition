package jsrc.matlab.typeinference.constraintelicitation;

import jsrc.matlab.ast.tree.declaration.type.*;
import jsrc.matlab.typeinference.type.*;
import jsrc.matlab.typeinference.type.TypeVar;
import lesani.collection.Pair;
import lesani.collection.Triple;
import lesani.collection.xtolook.Union14;
import lesani.collection.func.*;
import lesani.collection.option.Option;
import lesani.compiler.typing.*;

import java.util.*;


public class TypeDef {

    public static ScalarType[] scalarTypes = {IntType.instance(), DoubleType.instance(), BoolType.instance(), UnitType.instance()};
    public static ScalarType[] numericTypes = {IntType.instance(), DoubleType.instance()};
    public static ScalarType[] boolAndNumericTypes = {BoolType.instance(), IntType.instance(), DoubleType.instance()};
    public static List<Pair<ScalarType, ScalarType>> boolAndNumericTypePairs =
        new LinkedList<Pair<ScalarType, ScalarType>>();
    public static void addToBoolAndNumericTypePairs(ScalarType t1, ScalarType t2) {
                boolAndNumericTypePairs.add(new Pair<ScalarType, ScalarType>(t1, t2));
    }
    public static List<Triple<ScalarType, ScalarType, ScalarType>> boolAndNumericTypeTriples =
            new LinkedList<Triple<ScalarType, ScalarType, ScalarType>>();
    public static void addToTypeTriples(ScalarType t1, ScalarType t2, ScalarType t3) {
                boolAndNumericTypeTriples.add(new Triple<ScalarType, ScalarType, ScalarType>(t1, t2, t3));
    }

    static {
        // ------------------------------------------------------------------------------------

        addToBoolAndNumericTypePairs(IntType.instance(), IntType.instance());
        addToBoolAndNumericTypePairs(IntType.instance(), DoubleType.instance());

        addToBoolAndNumericTypePairs(DoubleType.instance(), DoubleType.instance());
        addToBoolAndNumericTypePairs(DoubleType.instance(), IntType.instance());

        addToBoolAndNumericTypePairs(BoolType.instance(), BoolType.instance());

        addToBoolAndNumericTypePairs(BoolType.instance(), IntType.instance());
        addToBoolAndNumericTypePairs(IntType.instance(), BoolType.instance());
        addToBoolAndNumericTypePairs(BoolType.instance(), DoubleType.instance());
        addToBoolAndNumericTypePairs(DoubleType.instance(), BoolType.instance());

        // ------------------------------------------------------------------------------------
        // Defined in NDT3Matrix.x10
        addToTypeTriples(IntType.instance(), IntType.instance(), IntType.instance());
        addToTypeTriples(IntType.instance(), DoubleType.instance(), DoubleType.instance());

        addToTypeTriples(DoubleType.instance(), DoubleType.instance(), DoubleType.instance());
        addToTypeTriples(DoubleType.instance(), IntType.instance(), DoubleType.instance());

        // Defined in BooleanMatrix.x10
        addToTypeTriples(BoolType.instance(), BoolType.instance(), IntType.instance());

        // Defined in NDTMatrix.x10
        addToTypeTriples(BoolType.instance(), IntType.instance(), IntType.instance());
        addToTypeTriples(IntType.instance(), BoolType.instance(), IntType.instance());
        addToTypeTriples(BoolType.instance(), DoubleType.instance(), DoubleType.instance());
        addToTypeTriples(DoubleType.instance(), BoolType.instance(), DoubleType.instance());

        // ------------------------------------------------------------------------------------
    }

    // ------------------------------------------------------------------------------------
    // Language op types
    // normalOpType makes the 4 combinations of singular and array for boolAndNumericTypeTriples.
    public static TypeScheme binaryNormalOpTypes = manyToOneTypeScheme(genTypes(binaryNormalOpDefs()));
    public static TypeScheme normalOpToBoolTypes = manyToOneTypeScheme(genTypes(relationalOpDefs()));

    public static SingleFunType shortCircuitAndAndOrType = new DFunType(new DataType[] {BoolType.instance(), BoolType.instance()}, BoolType.instance());
    public static TypeScheme notTypes = manyToOneTypeScheme(genTypes(notDefs()));
    public static TypeScheme timesTypes = manyToOneTypeScheme(genTypes(timesDefs()));
    public static TypeScheme dividesTypes = manyToOneTypeScheme(genTypes(dividesDefs()));
    public static TypeScheme unaryAndNormalOpTypes = manyToOneTypeScheme(genTypes(unaryAndBinaryNormalOpDefs()));
    public static TypeScheme vectorConstructorTypes = manyToOneTypeScheme(genTypes(vectorConstructorDefs()));
    public static TypeScheme rangeVectorConstructorTypes = manyToOneTypeScheme(genTypes(rangeVectorConstructorDefs()));
    public static TypeScheme arrayAccessTypes = manyToOneTypeScheme(genTypes(arrayAccessDefs()));
    public static TypeScheme arrayAssignmentTypes = manyToOneTypeScheme(genTypes(arrayAssignmentDefs()));
    public static TypeScheme transposeTypes = manyToOneTypeScheme(genTypes(transposeDefs()));
    public static TypeScheme powerTypes = manyToOneTypeScheme(genTypes(powerDefs()));
    public static TypeScheme lengthTypes = manyToOneTypeScheme(genTypes(lengthDefs()));

    // ------------------------------------------------------------------------------------
    // Lib function types
    public static HashSet<String> libFunctions = new HashSet<String>();
    static {
        libFunctions.add("readFormatImage");
        libFunctions.add("writeFormatImage");
        libFunctions.add("writeMatrix");
        libFunctions.add("readMatrix");
        libFunctions.add("writeIntMatrix");
        libFunctions.add("readIntMatrix");
        libFunctions.add("writeDoubleMatrix");
        libFunctions.add("readDoubleMatrix");

        libFunctions.add("logical");
        libFunctions.add("size");
        libFunctions.add("disp");
        libFunctions.add("mod");
        libFunctions.add("sqrt");
        libFunctions.add("randn");
        libFunctions.add("zeros");
        libFunctions.add("ones");
        libFunctions.add("randperm");
        libFunctions.add("norm");
        libFunctions.add("sort");
        libFunctions.add("abs");
        libFunctions.add("union");
        libFunctions.add("pinv");
        libFunctions.add("length");
        libFunctions.add("sum");
        libFunctions.add("double");
        libFunctions.add("tic");
        libFunctions.add("toc");
        libFunctions.add("find");
        libFunctions.add("reshape");
        libFunctions.add("min");
        libFunctions.add("max");
    }

    public static TypeScheme readFormatImageTypes = manyToOneTypeScheme(readFormatImageDefs());
    public static TypeScheme writeFormatImageTypes = manyToOneTypeScheme(writeFormatImageDefs());
    public static TypeScheme readMatrixTypes = manyToOneTypeScheme(readMatrixDefs());
    public static TypeScheme writeMatrixTypes = manyToOneTypeScheme(writeMatrixDefs());
    public static TypeScheme readIntMatrixTypes = manyToOneTypeScheme(readIntMatrixDefs());
    public static TypeScheme writeIntMatrixTypes = manyToOneTypeScheme(writeIntMatrixDefs());
    public static TypeScheme readDoubleMatrixTypes = manyToOneTypeScheme(readDoubleMatrixDefs());
    public static TypeScheme writeDoubleMatrixTypes = manyToOneTypeScheme(writeDoubleMatrixDefs());

    public static TypeScheme logicalTypes = manyToOneTypeScheme(genTypes(logicalDefs()));
    public static TypeScheme sizeTypes = manyToOneTypeScheme(genTypes(sizeDefs()));
    public static TypeScheme dispTypes = manyToOneTypeScheme(genTypes(dispDefs()));
    public static TypeScheme modTypes = unaryAndNormalOpTypes;
    public static TypeScheme sqrtTypes = manyToOneTypeScheme(genTypes(sqrtDefs()));
    public static TypeScheme randnTypes = manyToOneTypeScheme(randnDefs());
    public static TypeScheme zerosTypes = manyToOneTypeScheme(zerosAndOnesDefs());
    public static TypeScheme onesTypes = manyToOneTypeScheme(zerosAndOnesDefs());
    public static TypeScheme randpermTypes = manyToOneTypeScheme(randpermDefs());
    public static TypeScheme normTypes = manyToOneTypeScheme(genTypes(normDefs()));
    public static TypeScheme sortTypes = manyToOneTypeScheme(genTypes(sortDefs()));
    public static TypeScheme absTypes = manyToOneTypeScheme(genTypes(unaryNormalOpDefs()));
    public static TypeScheme unionTypes = manyToOneTypeScheme(genTypes(unionDefs()));
    public static TypeScheme pinvTypes = manyToOneTypeScheme(genTypes(pinvDefs()));
    public static TypeScheme sumTypes = manyToOneTypeScheme(genTypes(sumDefs()));
    public static TypeScheme doubleTypes = manyToOneTypeScheme(genTypes(doubleDefs()));
    public static DFunType ticTypes = new DFunType(new DataType[]{}, UnitType.instance());
    public static DFunType tocTypes = new DFunType(new DataType[]{}, DoubleType.instance());
    public static TypeScheme findTypes = manyToOneTypeScheme(genTypes(findDefs()));
    public static TypeScheme reshapeTypes = manyToOneTypeScheme(genTypes(reshapeDefs()));
    public static TypeScheme minAndMaxTypes = manyToOneTypeScheme(genTypes(minAndMaxDefs()));

    // ------------------------------------------------------------------------------------



    private static TypeScheme manyToOneTypeScheme(List<TypeScheme> typeSchemes) {
        Set<lesani.compiler.typing.TypeVar> allTypeVars = new HashSet<lesani.compiler.typing.TypeVar>();
        IntersectionFunType intersectionFunType = new IntersectionFunType();
        for (TypeScheme typeScheme : typeSchemes) {
            final Set<lesani.compiler.typing.TypeVar> typeVars = typeScheme.typeVars;
            for (lesani.compiler.typing.TypeVar typeVar : typeVars)
                allTypeVars.add(typeVar);
            intersectionFunType.add((SingleFunType)typeScheme.type);
        }
        return new TypeScheme(allTypeVars, intersectionFunType);
    }

    private static List<TypeScheme> genTypes(
        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > TypeDefs) {

        List<TypeScheme> allTypeDecls = new LinkedList<TypeScheme>();

        for (Union14<Fun0<SingleFunType>, Pair<ScalarType[], Fun<ScalarType, SingleFunType>>, Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>, Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>, Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>, Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>, Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>, Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>, Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>, Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>, Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>, Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>, Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>, Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>> typeDef : TypeDefs) {
            TypeScheme[] typeDecls = typeDef.apply(
                new Fun<Fun0<SingleFunType>, TypeScheme[]>() {
                    public TypeScheme[] apply(Fun0<SingleFunType> fun) {
                        return new TypeScheme[] {new TypeScheme(fun.apply())};
                    }
                },
                new Fun<Pair<ScalarType[], Fun<ScalarType, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<ScalarType[], Fun<ScalarType, SingleFunType>> input) {
                        ScalarType[] scalarTypes = input._1();
                        Fun<ScalarType, SingleFunType> fun = input._2();

                        TypeScheme[] typeDecls = new TypeScheme[scalarTypes.length];
                        for (int i = 0; i < typeDecls.length; i++)
                            typeDecls[i] = new TypeScheme(fun.apply(scalarTypes[i]));
                        return typeDecls;
                    }
                },
                new Fun<Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>> input) {
                        ScalarType[] scalarTypes = input._1();
                        Fun3<ScalarType, TypeVar, TypeVar, SingleFunType> fun = input._2();

                        TypeVar varN = TypeVar.fresh();
                        TypeVar varM = TypeVar.fresh();

                        TypeScheme[] typeDecls = new TypeScheme[scalarTypes.length];
                        for (int i = 0; i < typeDecls.length; i++)
                            typeDecls[i] = new TypeScheme(/*varN, varM, */fun.apply(scalarTypes[i], varN, varM));

                        return typeDecls;
                    }
                },
                new Fun<Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>> input) {
                        ScalarType[] scalarTypes = input._1();
                        Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType> fun = input._2();

                        TypeVar var1 = TypeVar.fresh();
                        TypeVar var2 = TypeVar.fresh();
                        TypeVar var3 = TypeVar.fresh();

                        TypeScheme[] typeDecls = new TypeScheme[scalarTypes.length];
                        for (int i = 0; i < typeDecls.length; i++) {
                            typeDecls[i] = new TypeScheme(
                                    /*var1, var2, var3,*/
                                    fun.apply(scalarTypes[i], var1, var2, var3));
                        }
                        return typeDecls;
                    }
                },
                new Fun<Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>> input) {
                        ScalarType[] scalarTypes = input._1();
                        Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType> fun = input._2();

                        TypeVar varN1 = TypeVar.fresh();
                        TypeVar varM1 = TypeVar.fresh();
                        TypeVar varN2 = TypeVar.fresh();
                        TypeVar varM2 = TypeVar.fresh();

                        TypeScheme[] typeDecls = new TypeScheme[scalarTypes.length];
                        for (int i = 0; i < typeDecls.length; i++) {
                            typeDecls[i] = new TypeScheme(
                                    /*varN1, varM1, varN2, varM2,*/
                                    fun.apply(scalarTypes[i], varN1, varM1, varN2, varM2));
                        }
                        return typeDecls;
                    }
                },
                new Fun<Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>> input) {
                        ScalarType[] scalarTypes = input._1();
                        Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType> fun = input._2();

                        TypeVar var1 = TypeVar.fresh();
                        TypeVar var2 = TypeVar.fresh();
                        TypeVar var3 = TypeVar.fresh();
                        TypeVar var4 = TypeVar.fresh();
                        TypeVar var5 = TypeVar.fresh();
                        TypeVar var6 = TypeVar.fresh();

                        TypeScheme[] typeDecls = new TypeScheme[scalarTypes.length];
                        for (int i = 0; i < typeDecls.length; i++) {
                            typeDecls[i] = new TypeScheme(
                                    /*var1, var2, var3, var4, var5, var6,*/
                                    fun.apply(scalarTypes[i], var1, var2, var3, var4, var5, var6));
                        }
                        return typeDecls;
                    }
                },
                new Fun<Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>> input) {
                        List<Pair<ScalarType, ScalarType>> typePairs = input._1();
                        Fun2<ScalarType, ScalarType, SingleFunType> fun = input._2();

                        TypeScheme[] typeDecls = new TypeScheme[typePairs.size()];
                        int i = 0;
                        for (Pair<ScalarType, ScalarType> typePair : typePairs) {
                            ScalarType type1 = typePair._1();
                            ScalarType type2 = typePair._2();
                            typeDecls[i] = new TypeScheme(fun.apply(type1, type2));
                            i++;
                        }
                        return typeDecls;
                    }
                },
                new Fun<Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>> input) {
                        List<Pair<ScalarType, ScalarType>> typePairs = input._1();
                        Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType> fun = input._2();

                        TypeVar varN = TypeVar.fresh();
                        TypeVar varM = TypeVar.fresh();

                        TypeScheme[] typeDecls = new TypeScheme[typePairs.size()];
                        int i = 0;
                        for (Pair<ScalarType, ScalarType> typePair : typePairs) {
                            ScalarType type1 = typePair._1();
                            ScalarType type2 = typePair._2();
                            typeDecls[i] = new TypeScheme(
                                    /*varN, varM,*/
                                    fun.apply(type1, type2, varN, varM));
                            i++;
                        }
                        return typeDecls;
                    }
                },
                new Fun<Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>> input) {
                        List<Pair<ScalarType, ScalarType>> typePairs = input._1();
                        Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType> fun = input._2();

                        TypeVar varN = TypeVar.fresh();
                        TypeVar varM = TypeVar.fresh();
                        TypeVar varP = TypeVar.fresh();

                        TypeScheme[] typeDecls = new TypeScheme[typePairs.size()];
                        int i = 0;
                        for (Pair<ScalarType, ScalarType> typePair : typePairs) {
                            ScalarType type1 = typePair._1();
                            ScalarType type2 = typePair._2();
                            typeDecls[i] = new TypeScheme(
                                    /*varN, varM, varP,*/
                                    fun.apply(type1, type2, varN, varM, varP));
                            i++;
                        }
                        return typeDecls;
                    }
                },
                new Fun<Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>> input) {
                        List<Pair<ScalarType, ScalarType>> typePairs = input._1();
                        Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType> fun = input._2();

                        TypeVar varN1 = TypeVar.fresh();
                        TypeVar varM1 = TypeVar.fresh();
                        TypeVar varN2 = TypeVar.fresh();
                        TypeVar varM2 = TypeVar.fresh();

                        TypeScheme[] typeDecls = new TypeScheme[typePairs.size()];
                        int i = 0;
                        for (Pair<ScalarType, ScalarType> typePair : typePairs) {
                            ScalarType type1 = typePair._1();
                            ScalarType type2 = typePair._2();
                            typeDecls[i] = new TypeScheme(
                                    /*varN1, varM1, varN2, varM2,*/
                                    fun.apply(type1, type2, varN1, varM1, varN2, varM2));
                            i++;
                        }
                        return typeDecls;
                    }
                },


                new Fun<Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>> input) {
                        List<Triple<ScalarType, ScalarType, ScalarType>> typeTriples = input._1();
                        Fun3<ScalarType, ScalarType, ScalarType, SingleFunType> fun = input._2();


                        TypeScheme[] typeDecls = new TypeScheme[typeTriples.size()];
                        int i = 0;
                        for (Triple<ScalarType, ScalarType, ScalarType> typeTriple : typeTriples) {
                            ScalarType type1 = typeTriple._1();
                            ScalarType type2 = typeTriple._2();
                            ScalarType type3 = typeTriple._3();
                            typeDecls[i] = new TypeScheme(fun.apply(type1, type2, type3));
                            i++;
                        }
                        return typeDecls;
                    }
                },
                new Fun<Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>> input) {
                        List<Triple<ScalarType, ScalarType, ScalarType>> typeTriples = input._1();
                        Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType> fun = input._2();

                        TypeVar varN = TypeVar.fresh();
                        TypeVar varM = TypeVar.fresh();


                        TypeScheme[] typeDecls = new TypeScheme[typeTriples.size()];
                        int i = 0;
                        for (Triple<ScalarType, ScalarType, ScalarType> typeTriple : typeTriples) {
                            ScalarType type1 = typeTriple._1();
                            ScalarType type2 = typeTriple._2();
                            ScalarType type3 = typeTriple._3();
                            typeDecls[i] = new TypeScheme(
                                    /*varN, varM,*/
                                    fun.apply(type1, type2, type3, varN, varM));
                            i++;
                        }
                        return typeDecls;
                    }
                },
                new Fun<Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>> input) {
                        List<Triple<ScalarType, ScalarType, ScalarType>> typeTriples = input._1();
                        Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType> fun = input._2();

                        TypeVar varN = TypeVar.fresh();
                        TypeVar varM = TypeVar.fresh();
                        TypeVar varP = TypeVar.fresh();


                        TypeScheme[] typeDecls = new TypeScheme[typeTriples.size()];
                        int i = 0;
                        for (Triple<ScalarType, ScalarType, ScalarType> typeTriple : typeTriples) {
                            ScalarType type1 = typeTriple._1();
                            ScalarType type2 = typeTriple._2();
                            ScalarType type3 = typeTriple._3();
                            typeDecls[i] = new TypeScheme(
                                    /*varN, varM, varP,*/
                                    fun.apply(type1, type2, type3, varN, varM, varP));
                            i++;
                        }
                        return typeDecls;
                    }
                },
                new Fun<Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>, TypeScheme[]>() {
                    public TypeScheme[] apply(Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>> input) {
                        List<Triple<ScalarType, ScalarType, ScalarType>> typeTriples = input._1();
                        Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType> fun = input._2();

                        TypeVar varN1 = TypeVar.fresh();
                        TypeVar varM1 = TypeVar.fresh();
                        TypeVar varN2 = TypeVar.fresh();
                        TypeVar varM2 = TypeVar.fresh();


                        TypeScheme[] typeDecls = new TypeScheme[typeTriples.size()];
                        int i = 0;
                        for (Triple<ScalarType, ScalarType, ScalarType> typeTriple : typeTriples) {
                            ScalarType type1 = typeTriple._1();
                            ScalarType type2 = typeTriple._2();
                            ScalarType type3 = typeTriple._3();
                            typeDecls[i] = new TypeScheme(
                                    /*varN1, varM1, varN2, varM2,*/
                                    fun.apply(type1, type2, type3, varN1, varM1, varN2, varM2));
                            i++;
                        }
                        return typeDecls;
                    }
                }
            );
            allTypeDecls.addAll(Arrays.asList(typeDecls));
        }
        return allTypeDecls;
    }

// =========================================================================================
// TypeDefs
// -----------------------------------------------------------------------------------------
    // Relational op
    private static
            List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > relationalOpDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem7<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>(
                    boolAndNumericTypePairs,
                    new Fun2<ScalarType, ScalarType, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2) {
                            // Single values
                            DataType[] inputTypes = new DataType[]{t1, t2};
                            DataType outputType = BoolType.instance();
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
/*
        typeDefs.add(
            new Union14.Elem8<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypePairs,
                    new Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, TypeVar n, TypeVar m) {
                            // Arrays - Static checks
                            MatrixType matrixType1 = new MatrixType(t1, n, m);
                            MatrixType matrixType2 = new MatrixType(t2, n, m);
                            MatrixType resultMatrixType = new MatrixType(BoolType.instance(), n, m);
                            DataType[] inputTypes = new DataType[] {matrixType1, matrixType2};
                            DataType outputType = resultMatrixType;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
*/
        typeDefs.add(
            new Union14.Elem8<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypePairs,
                    new Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, TypeVar n, TypeVar m) {
                            // Array and single value
                            MatrixType matrixType = new MatrixType(t1, n, m);
                            MatrixType resultMatrixType = new MatrixType(BoolType.instance(), n, m);
                            DataType[] inputTypes = new DataType[] {matrixType, t2};
                            DataType outputType = resultMatrixType;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem8<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypePairs,
                    new Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, TypeVar n, TypeVar m) {
                            // Single value and array
                            MatrixType matrixType = new MatrixType(t2, n, m);
                            MatrixType resultMatrixType = new MatrixType(BoolType.instance(), n, m);
                            DataType[] inputTypes = new DataType[] {t1, matrixType};
                            DataType outputType = resultMatrixType;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem10<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypePairs,
                    new Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            // Arrays - Runtime checks
                            MatrixType matrixType1 = new MatrixType(t1, n1, m1);
                            MatrixType matrixType2 = new MatrixType(t2, n2, m2);
                            MatrixType resultMatrixType = new MatrixType(BoolType.instance(), n1, m1);
                            DataType[] inputTypes = new DataType[] {matrixType1, matrixType2};
                            DataType outputType = resultMatrixType;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // Not
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > notDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem2<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun<ScalarType, SingleFunType>>(
                    boolAndNumericTypes,
                    new Fun<ScalarType, SingleFunType>() {
                        public SingleFunType apply(ScalarType t) {
                            return new DFunType(new DataType[] {t}, BoolType.instance());
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t, TypeVar n, TypeVar m) {
                            MatrixType matrixType = new MatrixType(t, n, m);
                            MatrixType rMatrixType = new MatrixType(BoolType.instance(), n, m);
                            return new DFunType(new DataType[]{matrixType}, rMatrixType);
                        }
                    }
                )
            )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // Times
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > timesDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem11<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>(
                        boolAndNumericTypeTriples,
                    new Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3) {
                            return new DFunType(new DataType[]{t1, t2}, t3);
                        }
                    }
                )
            )
        );

/*
        typeDefs.add(
            new Union14.Elem13<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypeTriples,
                    new Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3, TypeVar n, TypeVar m, TypeVar p) {
                            // Matrix multiplication - Static checks
                            MatrixType mt1 = new MatrixType(t1, n, m);
                            MatrixType mt2 = new MatrixType(t2, m, p);
                            MatrixType rmt = new MatrixType(t3, n, p);
                            return new DFunType(new DataType[]{mt1, mt2}, rmt);
                        }
                    }
                )
            )
        );
*/
        typeDefs.add(
            new Union14.Elem12<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypeTriples,
                    new Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3, TypeVar n, TypeVar m) {
                            // Matrix and scalar
                            MatrixType mt1 = new MatrixType(t1, n, m);
                            MatrixType rmt = new MatrixType(t3, n, m);
                            return new DFunType(new DataType[]{mt1, t2}, rmt);

                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem12<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypeTriples,
                    new Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3, TypeVar n, TypeVar m) {
                            // Scalar and Matrix
                            MatrixType mt2 = new MatrixType(t2, n, m);
                            MatrixType rmt = new MatrixType(t3, n, m);
                            return new DFunType(new DataType[]{t1, mt2}, rmt);

                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypeTriples,
                    new Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            // Two Matrices - Runtime checks
                            MatrixType mt1 = new MatrixType(t1, n1, m1);
                            MatrixType mt2 = new MatrixType(t2, n2, m2);
                            MatrixType rmt = new MatrixType(t3, n1, m2);
                            return new DFunType(new DataType[]{mt1, mt2}, rmt);
                        }
                    }
                )
            )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // Divides
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > dividesDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem11<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>(
                        boolAndNumericTypeTriples,
                    new Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3) {
                            t3 = DoubleType.instance();
                            return new DFunType(new DataType[]{t1, t2}, t3);
                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem12<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypeTriples,
                    new Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3, TypeVar n, TypeVar m) {
                            // Matrix and Scalar
                            t3 = DoubleType.instance();
                            MatrixType mt1 = new MatrixType(t1, n, m);
                            MatrixType rmt = new MatrixType(t3, n, m);
                            return new DFunType(new DataType[]{mt1, t2}, rmt);

                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem12<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypeTriples,
                    new Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3, TypeVar n, TypeVar m) {
                            // Scalar and Matrix
                            t3 = DoubleType.instance();
                            MatrixType mt2 = new MatrixType(t2, n, m);
                            MatrixType rmt = new MatrixType(t3, n, m);
                            return new DFunType(new DataType[]{t1, mt2}, rmt);
                        }
                    }
                )
            )
        );

/*
        typeDefs.add(
            new Union14.Elem9<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypePairs,
                    new Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, TypeVar n, TypeVar m, TypeVar p) {
                            // Two Matrices - Static checks
                            MatrixType mt1 = new MatrixType(t1, n, m);
                            MatrixType mt2 = new MatrixType(t2, p, m);
                            MatrixType rmt = new MatrixType(DoubleType.instance(), n, p);
                            return new DFunType(new DataType[]{mt1, mt2}, rmt);
                        }
                    }
                )
            )
        );
*/
        typeDefs.add(
            new Union14.Elem10<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypePairs,
                    new Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            // Two Matrices - Runtime checks
                            MatrixType mt1 = new MatrixType(t1, n1, m1);
                            MatrixType mt2 = new MatrixType(t2, n2, m2);
                            MatrixType rmt = new MatrixType(DoubleType.instance(), n1, n2);
                            return new DFunType(new DataType[]{mt1, mt2}, rmt);
                        }
                    }
                )
            )
        );

        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // Unary normal op
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > unaryNormalOpDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem2<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun<ScalarType, SingleFunType>>(
                    boolAndNumericTypes,
                    new Fun<ScalarType, SingleFunType>() {
                        public SingleFunType apply(ScalarType t) {
                            return new DFunType(new DataType[]{t}, t);
                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t, TypeVar n, TypeVar m) {
                            MatrixType matrixType = new MatrixType(t, n, m);
                            return new DFunType(new DataType[]{matrixType}, matrixType);
                        }
                    }
                )
            )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // Unary and binary normal op
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > unaryAndBinaryNormalOpDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.addAll(binaryNormalOpDefs());
        typeDefs.addAll(unaryNormalOpDefs());
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // Binary normal op
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > binaryNormalOpDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
                new Union14.Elem11<
                        Fun0<SingleFunType>,
                        Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                        Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                        Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                        Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                        Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                        >(
                        new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>(
                                boolAndNumericTypeTriples,
                                new Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>() {
                                    public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3) {
                                        // Both singular
                                        DataType[] inputTypes = new DataType[]{t1, t2};
                                        DataType outputType = t3;
                                        return new DFunType(inputTypes, outputType);
                                    }
                                }
                        )
                )
        );
        typeDefs.add(
            new Union14.Elem12<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypeTriples,
                    new Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3, TypeVar n, TypeVar m) {
                            // Array, singular
                            MatrixType matrixType = new MatrixType(t1, n, m);
                            MatrixType rMatrixType = new MatrixType(t3, n, m);
                            DataType[] inputTypes = new DataType[] {matrixType, t2};
                            DataType outputType = rMatrixType;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem12<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypeTriples,
                    new Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3, TypeVar n, TypeVar m) {
                            // Singular, array
                            MatrixType matrixType = new MatrixType(t2, n, m);
                            MatrixType rMatrixType = new MatrixType(t3, n, m);
                            DataType[] inputTypes = new DataType[] {t1, matrixType};
                            DataType outputType = rMatrixType;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
/*
        typeDefs.add(
            new Union14.Elem12<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypeTriples,
                    new Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3, TypeVar n, TypeVar m) {
                            // Both array - Static checks
                            MatrixType matrixType1 = new MatrixType(t1, n, m);
                            MatrixType matrixType2 = new MatrixType(t2, n, m);
                            MatrixType matrixType3 = new MatrixType(t3, n, m);
                            DataType[] inputTypes = new DataType[] {matrixType1, matrixType2};
                            DataType outputType = matrixType3;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
*/
        typeDefs.add(
            new Union14.Elem14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypeTriples,
                    new Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            // Both array - Runtime checks
                            MatrixType matrixType1 = new MatrixType(t1, n1, m1);
                            MatrixType matrixType2 = new MatrixType(t2, n2, m2);
                            MatrixType matrixType3 = new MatrixType(t3, n1, m1);
                            DataType[] inputTypes = new DataType[] {matrixType1, matrixType2};
                            DataType outputType = matrixType3;
                            return new DFunType(inputTypes, outputType);

                        }
                    }
                )
            )
        );

        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // Power op
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > powerDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
                new Union14.Elem11<
                        Fun0<SingleFunType>,
                        Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                        Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                        Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                        Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                        Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                        >(
                            new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>(
                                    boolAndNumericTypeTriples,
                                new Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>() {
                                    public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3) {
                                        // Both singular
                                        DataType[] inputTypes = new DataType[]{t1, t2};
                                        DataType outputType = t3;
                                        return new DFunType(inputTypes, outputType);
                                    }
                                }
                        )
                )
        );
        typeDefs.add(
            new Union14.Elem12<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypeTriples,
                    new Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, ScalarType t2, ScalarType t3, TypeVar n, TypeVar m) {
                            // Singular, array
                            MatrixType matrixType = new MatrixType(t2, n, m);
                            MatrixType rMatrixType = new MatrixType(t3, n, m);
                            DataType[] inputTypes = new DataType[] {t1, matrixType};
                            DataType outputType = rMatrixType;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
/*
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, TypeVar n, TypeVar m) {
                            // Array, singular - Static checks
                            MatrixType matrixType = new MatrixType(t1, n, n);
                            MatrixType rMatrixType = new MatrixType(t1, n, n);
                            DataType[] inputTypes = new DataType[] {matrixType, IntType.instance()};
                            DataType outputType = rMatrixType;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
*/
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t1, TypeVar n, TypeVar m) {
                            // Array, singular - Runtime checks
                            MatrixType matrixType = new MatrixType(t1, n, m);
                            MatrixType rMatrixType = new MatrixType(t1, n, m);
                            DataType[] inputTypes = new DataType[] {matrixType, IntType.instance()};
                            DataType outputType = rMatrixType;
                            return new DFunType(inputTypes, outputType);

                        }
                    }
                )
            )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // Transpose op
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > transposeDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType t, TypeVar n, TypeVar m) {
                            MatrixType mt1 = new MatrixType(t, n, m);
                            MatrixType rmt = new MatrixType(t, m, n);
                            return new DFunType(new DataType[]{mt1}, rmt);
                        }
                    }
                )
            )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // Matrix Constructor
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > vectorConstructorDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem2<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun<ScalarType, SingleFunType>>(
                    scalarTypes,
                    new Fun<ScalarType, SingleFunType>() {
                        public SingleFunType apply(final ScalarType scalarType) {
                            VarArgFunType.OutputTypeComputer computer = new VarArgFunType.OutputTypeComputer() {
                                public DataType computeOutputType(int rows, int columns) {
                                    if (rows == 1)
                                        return new HVectorType(scalarType, columns);
                                    else if (columns == 1)
                                        return new VVectorType(scalarType, rows);
                                    else
                                        return new MatrixType(scalarType, rows, columns);
                                }
                            };
                            return new VarArgFunType(scalarType, computer);
                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem4<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m, TypeVar m2) {
                            // Runtime check that n is 1.
                            MatrixType matrixType = new MatrixType(st, n, m);
                            MatrixType matrixType2 = new MatrixType(st, n, m2);

                            return new DFunType(new DataType[]{st, matrixType}, matrixType2);
                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem4<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m, TypeVar m2) {
                            // Runtime check that n is 1.
                            MatrixType matrixType = new MatrixType(st, n, m);
                            MatrixType matrixType2 = new MatrixType(st, n, m2);
                            return new DFunType(new DataType[]{matrixType, st}, matrixType2);
                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m1, TypeVar m2, TypeVar m3) {
                            MatrixType matrixType1 = new MatrixType(st, n, m1);
                            MatrixType matrixType2 = new MatrixType(st, n, m2);
                            MatrixType matrixType3 = new MatrixType(st, n, m3);
                            return new DFunType(new DataType[]{matrixType1, matrixType2}, matrixType3);
                        }
                    }
                )
            )
        );

        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // Range Vector Constructor
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > rangeVectorConstructorDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem1<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Fun0<SingleFunType>() {
                    public SingleFunType apply() {
                        final DataType[] inputTypes = new DataType[]{IntType.instance(), IntType.instance()};
                        ValueDepFunType.OutputTypeComputer computer = new ValueDepFunType.OutputTypeComputer() {
                            public DataType computeOutputType(Option<Value>[] optionValues) {
                                DataType outputType;
                                try {
                                    int[] ints = new int[2];
                                    for (int i = 0; i < ints.length; i++) {
                                        ints[i] = optionValues[i].apply(
                                            new Fun0<Integer>() {
                                                public Integer apply() {
                                                    throw new RuntimeException();
                                                }
                                            },
                                            new Fun<Value, Integer>() {
                                                public Integer apply(Value value) {
                                                    if (!(value.object instanceof Integer))
                                                        throw new RuntimeException();
                                                    return (Integer)value.object;
                                                }
                                            }
                                        );
                                    }
                                    final int n = ints[1] - ints[0] + 1;
                                    outputType = new HVectorType(IntType.instance(), n);
                                } catch (RuntimeException e) {
                                    TypeVar typeVar = TypeVar.fresh();
                                    outputType = new HVectorType(IntType.instance(), typeVar);
                                }
                                return outputType;
                            }
                        };
                        return new ValueDepFunType(inputTypes, computer);
                    }
                }
            )
        );
        typeDefs.add(
            new Union14.Elem1<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Fun0<SingleFunType>() {
                    public SingleFunType apply() {
                        final DataType[] inputTypes = new DataType[]{IntType.instance(), IntType.instance(), IntType.instance()};
                        ValueDepFunType.OutputTypeComputer computer = new ValueDepFunType.OutputTypeComputer() {
                            public DataType computeOutputType(Option<Value>[] optionValues) {
                                DataType outputType;
                                try {
                                    int[] ints = new int[3];
                                    for (int i = 0; i < ints.length; i++) {
                                        ints[i] = optionValues[i].apply(
                                            new Fun0<Integer>() {
                                                public Integer apply() {
                                                    throw new RuntimeException();
                                                }
                                            },
                                            new Fun<Value, Integer>() {
                                                public Integer apply(Value value) {
                                                    if (!(value.object instanceof Integer))
                                                        throw new RuntimeException();
                                                    return (Integer)value.object;
                                                }
                                            }
                                        );
                                    }
                                    final int n = (ints[2] - ints[0])%ints[1] + 1;
                                    outputType = new HVectorType(IntType.instance(), n);
                                } catch (RuntimeException e) {
                                    TypeVar typeVar = TypeVar.fresh();
                                    outputType = new HVectorType(IntType.instance(), typeVar);
                                }
                                return outputType;
                            }
                        };
                        return new ValueDepFunType(inputTypes, computer);
                    }
                }
            )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // Array Access
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > arrayAccessDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();


        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                // Simple Indexing
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m) {
                            MatrixType matrixType = new MatrixType(st, n, m);
                            DataType[] inputTypes = new DataType[]{matrixType, IntType.instance(), IntType.instance()};
                            return new DFunType(inputTypes, st);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                // Multi Indexing
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            MatrixType matrixType = new MatrixType(st, n1, m1);
                            HVectorType index1Type  = new HVectorType(IntType.instance(), n2);
                            HVectorType index2Type  = new HVectorType(IntType.instance(), m2);
                            // ToDo: The indices can be vertical vertors too. Add rules for them.
                            DataType[] inputTypes = new DataType[]{matrixType, index1Type, index2Type};

                            MatrixType outType = new MatrixType(st, n2, m2);
                            return new DFunType(inputTypes, outType);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem4<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                // Simple and Multi Indexing
                new Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2) {
                            MatrixType matrixType = new MatrixType(st, n1, m1);
                            HVectorType index2Type  = new HVectorType(IntType.instance(), n2);
                            DataType[] inputTypes = new DataType[]{matrixType, IntType.instance(), index2Type};

                            MatrixType outType = new MatrixType(st, 1, n2);
                            return new DFunType(inputTypes, outType);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                // Simple and Multi Indexing
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar p5) {
                            MatrixType matrixType = new MatrixType(st, n1, m1);
                            HVectorType index1Type  = new HVectorType(IntType.instance(), n2);
                            DataType[] inputTypes = new DataType[]{matrixType, index1Type, IntType.instance()};

                            MatrixType outType = new MatrixType(st, n2, 1);
                            return new DFunType(inputTypes, outType);
                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType scalarType, TypeVar var1, TypeVar var2) {
                            MatrixType idType = new MatrixType(scalarType, var1, var2);
                            DataType[] inputTypes = new DataType[]{idType, IntType.instance()};
                            DataType outType = scalarType;
                            return new DFunType(inputTypes, outType);

                        }
                    }
                )
            )
        );

/*
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                // Linear Indexing 1
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar n2) {
                            HVectorType idType = new HVectorType(st, n1);
                            HVectorType indexType  = new HVectorType(IntType.instance(), n2);

                            DataType[] inputTypes = new DataType[]{idType, indexType};
                            HVectorType outType = new HVectorType(st, n2);
                            return new DFunType(inputTypes, outType);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar n2) {
                            VVectorType idType = new VVectorType(st, n1);
                            HVectorType indexType  = new HVectorType(IntType.instance(), n2);

                            DataType[] inputTypes = new DataType[]{idType, indexType};
                            VVectorType outType = new VVectorType(st, n2);
                            return new DFunType(inputTypes, outType);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar n2) {
                            HVectorType idType = new HVectorType(st, n1);
                            VVectorType indexType  = new VVectorType(IntType.instance(), n2);

                            DataType[] inputTypes = new DataType[]{idType, indexType};
                            HVectorType outType = new HVectorType(st, n2);
                            return new DFunType(inputTypes, outType);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar n2) {
                            VVectorType idType = new VVectorType(st, n1);
                            VVectorType indexType  = new VVectorType(IntType.instance(), n2);

                            DataType[] inputTypes = new DataType[]{idType, indexType};
                            VVectorType outType = new VVectorType(st, n2);
                            return new DFunType(inputTypes, outType);
                        }
                    }
                )
            )
        );
        // Order of processing the constrains is important,
        // We want that the compiler to apply the preceding four rules if both of
        // the matrices are actually vectors.

        // Todo: Add rules for the case that at least one of the matrices is not a vector.
        // ...


*/
        // The case that no info is known about the two matrices.
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        // Linear indexing : Runtime checks
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            MatrixType idType = new MatrixType(st, n1, m1);
                            MatrixType indexType = new MatrixType(IntType.instance(), n2, m2);
                            DataType[] inputTypes = new DataType[]{idType, indexType};

                            final MatrixType outputType = new MatrixType(st, n2, m2);
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );

        // Logical Indexing
        // Todo: can add a definition before this that checks equality of indices and removes runtime check.
        typeDefs.add(
            new Union14.Elem6<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    // We only need 5 vars for this. Change Union14 to Union15 to optimize?
                    new Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2, TypeVar n3, TypeVar m3) {
                            // Logical Indexing : Runtime checks
                            MatrixType idType = new MatrixType(st, n1, m1);
                            MatrixType indexType = new MatrixType(BoolType.instance(), n2, m2);
                            DataType[] inputTypes = new DataType[]{idType, indexType};
                            // We don't know the length of the result!
                            VVectorType outputType = new VVectorType(st, n3);
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );

        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // Array Assignment
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > arrayAssignmentDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    // Single indexing
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m) {
                            MatrixType matrixType = new MatrixType(st, n, m);
                            DataType[] inputTypes = new DataType[]{matrixType, IntType.instance(), IntType.instance(), st};
                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );

/*
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    // Single and Multi indexing 1
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            MatrixType idType = new MatrixType(st, n1, m1);
                            HVectorType index1Type  = new HVectorType(IntType.instance(), n2);
                            VVectorType rightType = new VVectorType(st, n2);
                            DataType[] inputTypes = new DataType[]{idType, index1Type, IntType.instance(), rightType};

                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    // Single and Multi indexing 2
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            MatrixType idType = new MatrixType(st, n1, m1);
                            HVectorType index1Type  = new HVectorType(IntType.instance(), n2);
                            HVectorType rightType = new HVectorType(st, n2);
                            DataType[] inputTypes = new DataType[]{idType, index1Type, IntType.instance(), rightType};

                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    // Multi indexing
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            MatrixType idType = new MatrixType(st, n1, m1);
                            HVectorType index1Type  = new HVectorType(IntType.instance(), n2);
                            HVectorType index2Type  = new HVectorType(IntType.instance(), m2);
                            MatrixType rightType = new MatrixType(st, n2, m2);
                            DataType[] inputTypes = new DataType[]{idType, index1Type, index2Type, rightType};

                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );
*/

        typeDefs.add(
            new Union14.Elem4<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar v1, TypeVar v2, TypeVar v3) {
                            // Single and multi indexing 1 - Right hand side single value
                            MatrixType idType = new MatrixType(st, v1, v2);
                            HVectorType index1Type  = new HVectorType(IntType.instance(), v3);
                            DataType[] inputTypes = new DataType[]{idType, index1Type, IntType.instance(), st};
                            return new DFunType(inputTypes, UnitType.instance());

                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem4<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar v1, TypeVar v2, TypeVar v3) {
                            // Single and multi indexing 2 - Right hand side single value
                            MatrixType idType = new MatrixType(st, v1, v2);
                            HVectorType index2Type  = new HVectorType(IntType.instance(), v3);
                            DataType[] inputTypes = new DataType[]{idType, IntType.instance(), index2Type, st};
                            return new DFunType(inputTypes, UnitType.instance());

                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar var1, TypeVar var2, TypeVar var3, TypeVar var4) {
                            // Multi indexing - Single value right hand side
                            MatrixType idType = new MatrixType(st, var1, var2);
                            HVectorType index1Type  = new HVectorType(IntType.instance(), var3);
                            HVectorType index2Type  = new HVectorType(IntType.instance(), var4);
                            DataType[] inputTypes = new DataType[]{idType, index1Type, index2Type, st};

                            return new DFunType(inputTypes, UnitType.instance());

                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    // Single and Multi indexing 1 - Runtime checks
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            MatrixType idType = new MatrixType(st, n1, m1);
                            HVectorType index1Type  = new HVectorType(IntType.instance(), n2);
                            VVectorType rightType = new VVectorType(st, m2);
                            DataType[] inputTypes = new DataType[]{idType, index1Type, IntType.instance(), rightType};

                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    // Single and Multi indexing 2 - Runtime checks
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            MatrixType idType = new MatrixType(st, n1, m1);
                            HVectorType index2Type  = new HVectorType(IntType.instance(), n2);
                            VVectorType rightType = new VVectorType(st, m2);
                            DataType[] inputTypes = new DataType[]{idType, IntType.instance(), index2Type, rightType};

                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem6<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar var1, TypeVar var2, TypeVar var3, TypeVar var4, TypeVar var5, TypeVar var6) {
                            // Multi indexing - Runtime checks
                            MatrixType idType = new MatrixType(st, var1, var2);
                            HVectorType index1Type  = new HVectorType(IntType.instance(), var3);
                            HVectorType index2Type  = new HVectorType(IntType.instance(), var4);
                            MatrixType rightType = new MatrixType(st, var5, var6);
                            DataType[] inputTypes = new DataType[]{idType, index1Type, index2Type, rightType};

                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );

        // Linear indexing
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            // Linear indexing - Single value right hand side
                            MatrixType idType = new MatrixType(st, n1, m1);
                            MatrixType indexType = new MatrixType(IntType.instance(), n2, m2);
                            DataType rightType = st;

                            DataType[] inputTypes = new DataType[]{idType, indexType, rightType};

                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );

/*
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            // Linear indexing - Array right hand side : Static checks
                            MatrixType idType = new MatrixType(st, n1, m1);
                            MatrixType indexType = new MatrixType(IntType.instance(), n2, m2);
                            MatrixType rightType = new MatrixType(st, n2, m2);

                            DataType[] inputTypes = new DataType[]{idType, indexType, rightType};

                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );
*/

        typeDefs.add(
            new Union14.Elem6<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar v1, TypeVar v2, TypeVar v3, TypeVar v4, TypeVar v5, TypeVar v6) {
                            // Linear indexing - Array right hand side : Runtime checks
                            MatrixType idType = new MatrixType(st, v1, v2);
                            MatrixType indexType = new MatrixType(IntType.instance(), v3, v4);
                            MatrixType rightType = new MatrixType(st, v5, v6);

                            DataType[] inputTypes = new DataType[]{idType, indexType, rightType};

                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );

        // Logical indexing
/*
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1) {
                            // Logical indexing - Single value right hand side : Runtime checks
                            MatrixType idType = new MatrixType(st, n1, m1);
                            MatrixType indexType = new MatrixType(BoolType.instance(), n1, m1);

                            DataType[] inputTypes = new DataType[]{idType, indexType, st};

                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );

        typeDefs.add()
        new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            // Logical indexing - Array right hand side : Static checks
                            MatrixType idType = new MatrixType(st, n1, m1);
                            MatrixType indexType = new MatrixType(BoolType.instance(), n1, m1);//n2, m2);
                            MatrixType rightType = new MatrixType(st, n2, m2);

                            DataType[] inputTypes = new DataType[]{idType, indexType, rightType};

                            return new DFunType(inputTypes, UnitType.instance());

                        }
                    }
                )
        );*/
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                            // Logical indexing - Single value right hand side : Runtime checks
                            MatrixType idType = new MatrixType(st, n1, m1);
                            MatrixType indexType = new MatrixType(BoolType.instance(), n2, m2);

                            DataType[] inputTypes = new DataType[]{idType, indexType, st};

                            return new DFunType(inputTypes, UnitType.instance());
                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem6<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    scalarTypes,
                    new Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2, TypeVar n3, TypeVar m3) {
                            // Logical indexing - Array right hand side : Runtime checks
                            MatrixType idType = new MatrixType(st, n1, m1);
                            MatrixType indexType = new MatrixType(BoolType.instance(), n2, m2);
                            MatrixType rightType = new MatrixType(st, n3, m3);

                            DataType[] inputTypes = new DataType[]{idType, indexType, rightType};

                            return new DFunType(inputTypes, UnitType.instance());

                        }
                    }
                )
            )
        );

        return typeDefs;
    }
// -----------------------------------------------------------------------------------------
    // Lib function types
// -----------------------------------------------------------------------------------------
    // readFormatImage
    private static List<TypeScheme> readFormatImageDefs() {

        LinkedList<TypeScheme> typeSchemes = new LinkedList<TypeScheme>();

        final TypeVar varN1 = TypeVar.fresh();
        final TypeVar varM1 = TypeVar.fresh();

        DFunType type1 = new DFunType(
            new DataType[] {StringType.instance()},
            new MatrixType(IntType.instance(), varN1, varM1)
        );

        TypeScheme typeScheme1 = new TypeScheme(varN1, varM1, type1);

        typeSchemes.add(typeScheme1);

        return typeSchemes;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // writeFormatImage
    private static List<TypeScheme> writeFormatImageDefs() {

        LinkedList<TypeScheme> typeSchemes = new LinkedList<TypeScheme>();

        final TypeVar varN1 = TypeVar.fresh();
        final TypeVar varM1 = TypeVar.fresh();

        DFunType type1 = new DFunType(
            new DataType[] {new MatrixType(IntType.instance(), varN1, varM1), StringType.instance()},
            UnitType.instance()
        );

        TypeScheme typeScheme1 = new TypeScheme(varN1, varM1, type1);

        typeSchemes.add(typeScheme1);

        final TypeVar varN2 = TypeVar.fresh();
        final TypeVar varM2 = TypeVar.fresh();

        DFunType type2 = new DFunType(
            new DataType[] {new MatrixType(DoubleType.instance(), varN2, varM2), StringType.instance()},
            UnitType.instance()
        );

        TypeScheme typeScheme2 = new TypeScheme(varN2, varM2, type2);

        typeSchemes.add(typeScheme1);
        typeSchemes.add(typeScheme2);

        return typeSchemes;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // readMatrix
    private static List<TypeScheme> readMatrixDefs() {

        LinkedList<TypeScheme> typeSchemes = new LinkedList<TypeScheme>();

        final TypeVar varN1 = TypeVar.fresh();
        final TypeVar varM1 = TypeVar.fresh();

        DFunType type1 = new DFunType(
            new DataType[] {StringType.instance()},
            new MatrixType(IntType.instance(), varN1, varM1)
        );

        TypeScheme typeScheme1 = new TypeScheme(varN1, varM1, type1);

        final TypeVar varN2 = TypeVar.fresh();
        final TypeVar varM2 = TypeVar.fresh();

        DFunType type2 = new DFunType(
            new DataType[] {StringType.instance()},
            new MatrixType(DoubleType.instance(), varN2, varM2)
        );

        TypeScheme typeScheme2 = new TypeScheme(varN2, varM2, type2);

        // Process the double type first!

        typeSchemes.add(typeScheme2);
        typeSchemes.add(typeScheme1);

        return typeSchemes;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // writeMatrix
    private static List<TypeScheme> writeMatrixDefs() {

        LinkedList<TypeScheme> typeSchemes = new LinkedList<TypeScheme>();

        final TypeVar varN1 = TypeVar.fresh();
        final TypeVar varM1 = TypeVar.fresh();

        DFunType type1 = new DFunType(
            new DataType[] {new MatrixType(IntType.instance(), varN1, varM1), StringType.instance()},
            UnitType.instance()
        );

        TypeScheme typeScheme1 = new TypeScheme(varN1, varM1, type1);

        final TypeVar varN2 = TypeVar.fresh();
        final TypeVar varM2 = TypeVar.fresh();

        DFunType type2 = new DFunType(
            new DataType[] {new MatrixType(DoubleType.instance(), varN2, varM2), StringType.instance()},
            UnitType.instance()
        );

        TypeScheme typeScheme2 = new TypeScheme(varN2, varM2, type2);

        typeSchemes.add(typeScheme1);
        typeSchemes.add(typeScheme2);

        return typeSchemes;
    }

// -----------------------------------------------------------------------------------------
    // readIntMatrix
    private static List<TypeScheme> readIntMatrixDefs() {

        LinkedList<TypeScheme> typeSchemes = new LinkedList<TypeScheme>();

        final TypeVar varN1 = TypeVar.fresh();
        final TypeVar varM1 = TypeVar.fresh();

        DFunType type1 = new DFunType(
            new DataType[] {StringType.instance()},
            new MatrixType(IntType.instance(), varN1, varM1)
        );

        TypeScheme typeScheme1 = new TypeScheme(varN1, varM1, type1);

        typeSchemes.add(typeScheme1);

        return typeSchemes;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // writeIntMatrix
    private static List<TypeScheme> writeIntMatrixDefs() {

        LinkedList<TypeScheme> typeSchemes = new LinkedList<TypeScheme>();

        final TypeVar varN1 = TypeVar.fresh();
        final TypeVar varM1 = TypeVar.fresh();

        DFunType type1 = new DFunType(
            new DataType[] {new MatrixType(IntType.instance(), varN1, varM1), StringType.instance()},
            UnitType.instance()
        );

        TypeScheme typeScheme1 = new TypeScheme(varN1, varM1, type1);

        typeSchemes.add(typeScheme1);


        return typeSchemes;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // readDoubleMatrix
    private static List<TypeScheme> readDoubleMatrixDefs() {

        LinkedList<TypeScheme> typeSchemes = new LinkedList<TypeScheme>();

        final TypeVar varN1 = TypeVar.fresh();
        final TypeVar varM1 = TypeVar.fresh();

        DFunType type1 = new DFunType(
            new DataType[] {StringType.instance()},
            new MatrixType(DoubleType.instance(), varN1, varM1)
        );

        TypeScheme typeScheme1 = new TypeScheme(varN1, varM1, type1);

        typeSchemes.add(typeScheme1);

        return typeSchemes;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // writeDoubleMatrix
    private static List<TypeScheme> writeDoubleMatrixDefs() {

        LinkedList<TypeScheme> typeSchemes = new LinkedList<TypeScheme>();

        final TypeVar varN1 = TypeVar.fresh();
        final TypeVar varM1 = TypeVar.fresh();

        DFunType type1 = new DFunType(
            new DataType[] {new MatrixType(DoubleType.instance(), varN1, varM1), StringType.instance()},
            UnitType.instance()
        );

        TypeScheme typeScheme1 = new TypeScheme(varN1, varM1, type1);

        typeSchemes.add(typeScheme1);


        return typeSchemes;
    }

// -----------------------------------------------------------------------------------------
    // logical
    private static
            List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > logicalDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem2<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun<ScalarType, SingleFunType>>(
                    numericTypes,
                    new Fun<ScalarType, SingleFunType>() {
                        public SingleFunType apply(ScalarType scalarType) {
                            DataType[] inputs = {scalarType};
                            DataType output = BoolType.instance();
                            return new DFunType(inputs, output);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    numericTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType scalarType, TypeVar var1, TypeVar var2) {
                            MatrixType matrixType = new MatrixType(scalarType, var1, var2);
                            DataType[] inputs = {matrixType};
                            DataType output = new MatrixType(BoolType.instance(), var1, var2);
                            return new DFunType(inputs, output);
                        }
                    }
                )
            )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    private static
            List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > sizeDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType scalarType, TypeVar var1, TypeVar var2) {
                            MatrixType matrixType = new MatrixType(scalarType, var1, var2);
                            DataType[] inputs = {matrixType};
                            DataType output = new TupleType(new DataType[] {IntType.instance(), IntType.instance()});
                            return new DFunType(inputs, output);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType scalarType, TypeVar var1, TypeVar var2) {
                            MatrixType matrixType = new MatrixType(scalarType, var1, var2);
                            DataType[] inputs = {matrixType, IntType.instance()};
                            DataType output = IntType.instance();
                            return new DFunType(inputs, output);
                        }
                    }
                )
            )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    private static
            List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > dispDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem1<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Fun0<SingleFunType>() {
                    public SingleFunType apply() {
                        DataType[] inputs = {StringType.instance()};
                        DataType output = UnitType.instance();
                        return new DFunType(inputs, output);
                    }
                }
            )
        );
        typeDefs.add(
            new Union14.Elem2<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun<ScalarType, SingleFunType>>(
                    boolAndNumericTypes,
                    new Fun<ScalarType, SingleFunType>() {
                        public SingleFunType apply(ScalarType scalarType) {
                            DataType[] inputs = {scalarType};
                            DataType output = UnitType.instance();
                            return new DFunType(inputs, output);
                        }
                    }
                )
            )
        );
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    boolAndNumericTypes,
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType scalarType, TypeVar var1, TypeVar var2) {
                            MatrixType matrixType = new MatrixType(scalarType, var1, var2);
                            DataType[] inputs = {matrixType};
                            DataType output = UnitType.instance();
                            return new DFunType(inputs, output);

                        }
                    }
                )
            )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // mod
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > modDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
                new Union14.Elem1<
                        Fun0<SingleFunType>,
                        Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                        Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                        Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                        Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                        Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                        Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                        >(
                            new Fun0<SingleFunType>() {
                                public SingleFunType apply() {
                                    // Both singular
                                    DataType[] inputTypes = new DataType[]{IntType.instance(), IntType.instance()};
                                    DataType outputType = IntType.instance();
                                    return new DFunType(inputTypes, outputType);

                                }
                            }
                        )
        );

        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    new ScalarType[]{IntType.instance()},
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m) {
                            // Array, singular
                            MatrixType matrixType = new MatrixType(st, n, m);
                            MatrixType rMatrixType = new MatrixType(st, n, m);
                            DataType[] inputTypes = new DataType[] {matrixType, st};
                            DataType outputType = rMatrixType;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );

        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    new ScalarType[]{IntType.instance()},
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m) {
                            // Singular, array
                            MatrixType matrixType = new MatrixType(st, n, m);
                            MatrixType rMatrixType = new MatrixType(st, n, m);
                            DataType[] inputTypes = new DataType[] {st, matrixType};
                            DataType outputType = rMatrixType;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
/*
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                    new ScalarType[]{IntType.instance()},
                    new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m) {
                            // Both array : Static checks
                            MatrixType matrixType1 = new MatrixType(st, n, m);
                            MatrixType matrixType2 = new MatrixType(st, n, m);
                            MatrixType matrixType3 = new MatrixType(st, n, m);
                            DataType[] inputTypes = new DataType[] {matrixType1, matrixType2};
                            DataType outputType = matrixType3;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );
*/
        typeDefs.add(
            new Union14.Elem6<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >(
                new Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                    new ScalarType[]{IntType.instance()},
                    new Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                        public SingleFunType apply(ScalarType st, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2, TypeVar n3, TypeVar m3) {
                            // Both array : Runtime checks
                            MatrixType matrixType1 = new MatrixType(st, n1, m1);
                            MatrixType matrixType2 = new MatrixType(st, n2, m2);
                            MatrixType matrixType3 = new MatrixType(st, n3, m3);
                            DataType[] inputTypes = new DataType[] {matrixType1, matrixType2};
                            DataType outputType = matrixType3;
                            return new DFunType(inputTypes, outputType);
                        }
                    }
                )
            )
        );

        return typeDefs;
    }
// -----------------------------------------------------------------------------------------
    // sqrt
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > sqrtDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem2<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                    >(
                        new Pair<ScalarType[], Fun<ScalarType, SingleFunType>>(
                            numericTypes,
                            new Fun<ScalarType, SingleFunType>() {
                                public SingleFunType apply(ScalarType nt) {
                                    DataType[] inputTypes = new DataType[]{nt};
                                    DataType outputType = DoubleType.instance();
                                    return new DFunType(inputTypes, outputType);
                                }
                            }
                        )
                    )
        );
        typeDefs.add(
            new Union14.Elem3<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                    >(
                        new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                            numericTypes,
                            new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                                public SingleFunType apply(ScalarType nt, TypeVar n, TypeVar m) {
                                    DataType[] inputTypes = new DataType[]{new MatrixType(nt, n, m)};
                                    DataType outputType = new MatrixType(DoubleType.instance(), n, m);
                                    return new DFunType(inputTypes, outputType);
                                }
                            }
                        )
                    )
        );

        return typeDefs;
    }
// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // randn
    private static List<TypeScheme> randnDefs() {

        LinkedList<TypeScheme> typeSchemes = new LinkedList<TypeScheme>();
        // We could add another alternative to the union type instead of doing this directly here.

        final TypeVar varN1 = TypeVar.fresh();
        final TypeVar varM1 = TypeVar.fresh();

        DFunType type1 = new DFunType(
            new DataType[] {IntType.instance(), IntType.instance()},
            new MatrixType(DoubleType.instance(), varN1, varM1)
        );

        TypeScheme typeScheme1 = new TypeScheme(varN1, varM1, type1);

        final TypeVar varNN = TypeVar.fresh();

        DFunType type2 = new DFunType(
            new DataType[] {IntType.instance()},
            new MatrixType(DoubleType.instance(), varNN, varNN)
        );

        TypeScheme typeScheme2 = new TypeScheme(varNN, type2);

        final TypeVar varN3 = TypeVar.fresh();
        final TypeVar varM3 = TypeVar.fresh();
        final TypeVar varN4 = TypeVar.fresh();
        final TypeVar varM4 = TypeVar.fresh();
        // Runtime check for the input matrix to be 1x2.
        MatrixType matrixType3 = new MatrixType(IntType.instance(), varN3, varM3);
        DFunType type3 = new DFunType(
            new DataType[] {matrixType3},
            new MatrixType(DoubleType.instance(), varN4, varM4)
        );

        TypeScheme typeScheme3 = new TypeScheme(varN3, varM3, varN4, varM4, type3);

        typeSchemes.add(typeScheme1);
        typeSchemes.add(typeScheme2);
        typeSchemes.add(typeScheme3);

        // Todo: Can extend the type to single input.
        return typeSchemes;
    }
// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // zeros and ones
    private static List<TypeScheme> zerosAndOnesDefs() {

        LinkedList<TypeScheme> typeSchemes = new LinkedList<TypeScheme>();
        // We could add another alternative to the union type instead of doing this directly here.
        final TypeVar varN = TypeVar.fresh();
        final TypeVar varM = TypeVar.fresh();
        DFunType type = new DFunType(
            new DataType[] {IntType.instance(), IntType.instance()},
            new MatrixType(IntType.instance(), varN, varM)
        );
        TypeScheme typeScheme = new TypeScheme(varN, varM, type);
        typeSchemes.add(typeScheme);

        final TypeVar varN2 = TypeVar.fresh();
        final TypeVar varM2 = TypeVar.fresh();
        DFunType type2 = new DFunType(
            new DataType[] {IntType.instance(), IntType.instance()},
            new MatrixType(DoubleType.instance(), varN2, varM2)
        );
        TypeScheme typeScheme2 = new TypeScheme(varN2, varM2, type2);
        typeSchemes.add(typeScheme2);

        return typeSchemes;
    }
// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // randperm
    private static List<TypeScheme> randpermDefs() {

        LinkedList<TypeScheme> typeSchemes = new LinkedList<TypeScheme>();
        // We could add another alternative to the union type instead of doing this directly here.
        final TypeVar varM = TypeVar.fresh();

        DFunType type = new DFunType(
            new DataType[] {IntType.instance()},
            new MatrixType(IntType.instance(), 1, varM)
        );

        TypeScheme typeScheme = new TypeScheme(varM, type);
        typeSchemes.add(typeScheme);

        // Todo: Can statically resolve the length of the vector for a literal input.
        return typeSchemes;
    }
// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // norm
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > normDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem2<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun<ScalarType, SingleFunType>>(
                        boolAndNumericTypes,
                        new Fun<ScalarType, SingleFunType>() {
                            public SingleFunType apply(ScalarType st) {
                                DataType[] inputTypes = new DataType[]{st};
                                DataType outputType = DoubleType.instance();
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );

        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypes,
                        new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m) {
                                DataType matrixType = new MatrixType(st, n, m);
                                DataType[] inputTypes = new DataType[]{matrixType};
                                DataType outputType = DoubleType.instance();
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );
        // End of without param

        // Start of with param
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        numericTypes,
                        new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType nt, TypeVar n, TypeVar m) {
                                MatrixType matrixType = new MatrixType(nt, n, m);
                                DataType[] inputTypes = new DataType[]{matrixType, IntType.instance()};
                                DataType outputType = DoubleType.instance();
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        numericTypes,
                        new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType nt, TypeVar n, TypeVar m) {
                                MatrixType matrixType = new MatrixType(nt, n, m);
                                DataType[] inputTypes = new DataType[]{matrixType, StringType.instance()};
                                DataType outputType = DoubleType.instance();
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );

        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // sort
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > sortDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        numericTypes,
                        new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m) {
                                MatrixType matrixType = new MatrixType(st, n, m);
                                DataType[] inputTypes = new DataType[]{matrixType};
                                DataType outputType = matrixType;
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        numericTypes,
                        new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m) {
                                MatrixType matrixType = new MatrixType(st, n, m);
                                DataType[] inputTypes = new DataType[]{matrixType, StringType.instance()};
                                DataType outputType = matrixType;
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );

        // With indices
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        numericTypes,
                        new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m) {
                                MatrixType matrixType = new MatrixType(st, n, m);
                                MatrixType indexType = new MatrixType(IntType.instance(), n, m);
                                DataType[] inputTypes = new DataType[]{matrixType};
                                DataType outputType = new TupleType(new DataType[] {matrixType, indexType});
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        numericTypes,
                        new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m) {
                                MatrixType matrixType = new MatrixType(st, n, m);
                                MatrixType indexType = new MatrixType(IntType.instance(), n, m);
                                DataType[] inputTypes = new DataType[]{matrixType, StringType.instance()};
                                DataType outputType = new TupleType(new DataType[] {matrixType, indexType});
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );

        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // union
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > unionDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();
        // static rules to be added later.
        // Runtime checks:
        typeDefs.add(
            new Union14.Elem6<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                        numericTypes,
                        new Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType nt, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2, TypeVar n3, TypeVar m3) {
                                // Runtime checks
                                MatrixType matrixType1 = new MatrixType(nt, n1, m1);
                                MatrixType matrixType2 = new MatrixType(nt, n2, m2);
                                MatrixType matrixType3 = new MatrixType(nt, n3, m3);
                                DataType[] inputTypes = new DataType[]{matrixType1, matrixType2};
                                DataType outputType = matrixType3;
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );

        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // pinv
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > pinvDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();
        // static rules to be added later.
        // Runtime checks:
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                        numericTypes,
                        new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType nt, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                                // Runtime checks
                                MatrixType matrixType1 = new MatrixType(nt, n1, m1);
                                MatrixType matrixType2 = new MatrixType(DoubleType.instance(), n2, m2);
                                DataType[] inputTypes = new DataType[]{matrixType1};
                                DataType outputType = matrixType2;
                                return new DFunType(inputTypes, outputType);

                            }
                        }
                    )
                )
        );

        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // length
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > lengthDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        scalarTypes,
                        new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType st, TypeVar n, TypeVar m) {
                                MatrixType matrixType = new MatrixType(st, n, m);
                                DataType[] inputTypes = new DataType[]{matrixType};
                                DataType outputType = IntType.instance();
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // sum
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > sumDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        numericTypes,
                        new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType nt, TypeVar n, TypeVar m) {
                                MatrixType matrixType = new MatrixType(nt, n, m);
                                MatrixType matrixType2 = new MatrixType(nt, 1, m);

                                DataType[] inputTypes = new DataType[]{matrixType};
                                DataType outputType = matrixType2;
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    // We can statically know that one of the indices of the result is 1,
                    // if we use ValueDepFunType to define this rule.
                    new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                        numericTypes,
                        new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType nt, TypeVar n, TypeVar m, TypeVar n2, TypeVar m2) {
                                MatrixType matrixType = new MatrixType(nt, n, m);
                                MatrixType matrixType2 = new MatrixType(nt, n2, m2);

                                DataType[] inputTypes = new DataType[]{matrixType, IntType.instance()};
                                DataType outputType = matrixType2;
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------
    // double
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > doubleDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();
        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        numericTypes,
                        new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType nt, TypeVar n, TypeVar m) {
                                MatrixType matrixType = new MatrixType(nt, n, m);
                                MatrixType matrixType2 = new MatrixType(DoubleType.instance(), n, m);

                                DataType[] inputTypes = new DataType[]{matrixType};
                                DataType outputType = matrixType2;
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // find
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > findDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypes,
                        new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType nt, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                                MatrixType matrixType = new MatrixType(nt, n1, m1);
                                MatrixType matrixType2 = new MatrixType(IntType.instance(), n2, m2);

                                DataType[] inputTypes = new DataType[]{matrixType};
                                DataType outputType = matrixType2;
                                return new DFunType(inputTypes, outputType);

                            }
                        }
                    )
                )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // reshape
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > reshapeDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();
        typeDefs.add(
            new Union14.Elem5<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypes,
                        new Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType nt, TypeVar n1, TypeVar m1, TypeVar n2, TypeVar m2) {
                                MatrixType matrixType = new MatrixType(nt, n1, m1);
                                MatrixType matrixType2 = new MatrixType(nt, n2, m2);

                                DataType[] inputTypes = new DataType[]{matrixType, IntType.instance(), IntType.instance()};
                                DataType outputType = matrixType2;
                                return new DFunType(inputTypes, outputType);

                            }
                        }
                    )
                )
        );
        return typeDefs;
    }

// -----------------------------------------------------------------------------------------
    // min and max
    private static List<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            > minAndMaxDefs() {

        List<
            Union14<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
            >
        > typeDefs = new
            LinkedList<
                Union14<
                    Fun0<SingleFunType>,
                    Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                    Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                    Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >
            >();

        typeDefs.add(
            new Union14.Elem3<
                Fun0<SingleFunType>,
                Pair<ScalarType[], Fun<ScalarType, SingleFunType>>,
                Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun4<ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun5<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<ScalarType[], Fun7<ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Pair<ScalarType, ScalarType>>, Fun2<ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun4<ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Pair<ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>,

                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun3<ScalarType, ScalarType, ScalarType, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun5<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun6<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, SingleFunType>>,
                Pair<List<Triple<ScalarType, ScalarType, ScalarType>>, Fun7<ScalarType, ScalarType, ScalarType, TypeVar, TypeVar, TypeVar, TypeVar, SingleFunType>>
                >(
                    new Pair<ScalarType[], Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>>(
                        boolAndNumericTypes,
                        new Fun3<ScalarType, TypeVar, TypeVar, SingleFunType>() {
                            public SingleFunType apply(ScalarType nt, TypeVar n, TypeVar m) {
                                MatrixType matrixType = new MatrixType(nt, n, m);
                                MatrixType matrixType2 = new MatrixType(nt, 1, m);

                                DataType[] inputTypes = new DataType[]{matrixType};
                                DataType outputType = matrixType2;
                                return new DFunType(inputTypes, outputType);
                            }
                        }
                    )
                )
        );

        typeDefs.addAll(binaryNormalOpDefs());

        return typeDefs;
    }

// -----------------------------------------------------------------------------------------

// =========================================================================================

}
