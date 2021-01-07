package lambda_calculus;

//import javax.swing.plaf.nimbus.State;
import fj.Hash;
import lambda_calculus.cps_ast.tree.Context;
import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.translateToOtherAST;
import lambda_calculus.partition_package.tree.MethodDefinition;
import lambda_calculus.partition_package.visitor.CIAType;
import lambda_calculus.partition_package.visitor.PartitionMethod;
import lambda_calculus.partition_package.visitor.nodeSet;
import lambda_calculus.partition_package.visitor.quorumDef;
import lambda_calculus.source_ast.tree.expression.Conditional;
import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.tree.expression.ObjectMethod;
import lambda_calculus.source_ast.tree.expression.Var;
import lambda_calculus.source_ast.tree.expression.literal.IntLiteral;
import lambda_calculus.source_ast.tree.expression.op.Sequence;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lesani.collection.Pair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class translation_test {
    static String outputPath = "/home/xiao/IdeaProjects/secure_partition/out/lambda_calculus/";
    public static void main(String[] args)
    {
        Expression lambda1 = createOFTUseCase();
        System.out.println("Complete create use-case");
        CPSPrinter test = new CPSPrinter();
        Command resultAST = test.print(lambda1).element1;
        //Context resultContext = test.print(lambda1).element2;
        System.out.println("Complete translation: " + resultAST.toString());
        BetaReduction test2 = new BetaReduction();
        Command resultAST2 = test2.wholeReduction(resultAST);
        System.out.println("Complete reduction: " + resultAST2.toString());
        translateToOtherAST test3 = new translateToOtherAST();
        lambda_calculus.partition_package.tree.command.Command translatedAST = test3.getAST(resultAST2);
        System.out.println("Complete translation 2: " + translatedAST.toString());
        PartitionMethod test4 = new PartitionMethod();
        ArrayList<MethodDefinition> resultMethodDefs = test4.methodSeparation(translatedAST);
        System.out.println("Complete separation: " );
        for(MethodDefinition d : resultMethodDefs){
            System.out.println(d.toString());
        }

        //failure situation for return value and m0 and m1
        HashSet<Integer> b1 = new HashSet<Integer>(Arrays.asList(1, 2, 8));
        HashSet<Integer> b2 = new HashSet<Integer>(Arrays.asList(1, 2, 9));
        HashSet<Integer> b3 = new HashSet<Integer>(Arrays.asList(1, 3, 8));
        HashSet<Integer> b4 = new HashSet<Integer>(Arrays.asList(1, 3, 9));
        HashSet<Integer> i1B1 = new HashSet<>(Arrays.asList(1, 2, 8, 9, 10, 11));
        HashSet<Integer> i1B2 = new HashSet<>(Arrays.asList(1, 3, 8, 9, 10, 11));
        HashSet<Integer> i2B1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        HashSet<Integer> i2B2 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9));

        //confidentiality information for objects
        HashSet<Integer> c1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 12));
        HashSet<Integer> c2 = new HashSet<>(Arrays.asList(8, 9, 10, 11, 12));
        HashSet<Integer> cx = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));

        //host and quorum information
        HashSet<Integer> Hm1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        HashSet<Integer> Hm2 = new HashSet<>(Arrays.asList(9, 10, 11));
        HashSet<Integer> Hm3 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 10, 11));
        HashSet<Integer> Hm4 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 10, 11));
        HashSet<nodeSet> Qm1 = new HashSet<>();
        HashSet<Integer> Qm1_1 = new HashSet<>(Arrays.asList(4, 5, 6));
        HashSet<Integer> Qm1_2 = new HashSet<>(Arrays.asList(5, 6, 7));
        Qm1.add(new nodeSet(Qm1_1));
        Qm1.add(new nodeSet(Qm1_2));
        HashSet<nodeSet> Qm2 = new HashSet<>();
        HashSet<Integer> Qm2_1 = new HashSet<>(Arrays.asList(10, 11));
        Qm2.add(new nodeSet(Qm2_1));
        HashSet<nodeSet> Qm3 = new HashSet<>();
        HashSet<Integer> Qm3_1 = new HashSet<>(Arrays.asList(4, 5, 6, 10, 11));
        Qm3.add(new nodeSet(Qm3_1));
        HashSet<nodeSet> Qm4 = new HashSet<>();
        HashSet<Integer> Qm4_1 = new HashSet<>(Arrays.asList(4, 5, 6, 10, 11));
        Qm4.add(new nodeSet(Qm4_1));

        //object replication information
        HashSet<nodeSet> Qi1 = new HashSet<>();
        HashSet<Integer> Qi1_1 = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
        HashSet<Integer> Qi1_2 = new HashSet<>(Arrays.asList(2, 4, 5, 6, 7));
        Qi1.add(new nodeSet(Qi1_1));
        Qi1.add(new nodeSet(Qi1_2));
        HashSet<nodeSet> Qi2 = new HashSet<>();
        HashSet<Integer> Qi2_1 = new HashSet<>(Arrays.asList(8, 10, 11));
        HashSet<Integer> Qi2_2 = new HashSet<>(Arrays.asList(9, 10, 11));
        Qi2.add(new nodeSet(Qi2_1));
        Qi2.add(new nodeSet(Qi2_2));
        HashSet<nodeSet> Qa = new HashSet<>();
        HashSet<Integer> Qa_1 = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7, 8, 10, 11));
        HashSet<Integer> Qa_2 = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7, 9, 10, 11));
        HashSet<Integer> Qa_3 = new HashSet<>(Arrays.asList(2, 4, 5, 6, 7, 8, 10, 11));
        HashSet<Integer> Qa_4 = new HashSet<>(Arrays.asList(2, 4, 5, 6, 7, 9, 10, 11));
        Qa.add(new nodeSet(Qa_1));
        Qa.add(new nodeSet(Qa_2));
        Qa.add(new nodeSet(Qa_3));
        Qa.add(new nodeSet(Qa_4));

        //input the methods host information and signature manually
        ArrayList<Pair<nodeSet, quorumDef>> methodsInfo = new ArrayList<>();
        Pair<nodeSet, quorumDef> m1Info = new Pair<>(new nodeSet(Hm1), new quorumDef(Qm1));
        methodsInfo.add(m1Info);
        Pair<nodeSet, quorumDef> m2Info = new Pair<>(new nodeSet(Hm2), new quorumDef(Qm2));
        methodsInfo.add(m2Info);
        Pair<nodeSet, quorumDef> m3Info = new Pair<>(new nodeSet(Hm3), new quorumDef(Qm3));
        methodsInfo.add(m3Info);
        Pair<nodeSet, quorumDef> m4Info = new Pair<>(new nodeSet(Hm4), new quorumDef(Qm4));
        methodsInfo.add(m4Info);

        ArrayList<Pair<ArrayList<CIAType>, HashMap<String, CIAType>>> methodSig = new ArrayList<>();
        //input the object host information and signature manually
        HashMap<String, HashMap<String, HashMap<String, CIAType>>> objSigs = new HashMap<>();
        HashMap<String, Pair<quorumDef, quorumDef>> objInfo = new HashMap<>();

        //printToFile(lambda1., "oft_cps");
    }

//        public static String cpsTransformation(lambda_usecase useCase)
//        {
//            String result = new boolean[useCase.getOperations().size()][useCase.getOperations().size()];
//            System.out.println("CPS transformation:");
//            return result;
//        }

        public static void printToFile(String transformation, String fileName)
        {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(outputPath + fileName);
                fileOutputStream.write(transformation.getBytes());
                fileOutputStream.close();
            }
            catch (IOException e) {
                System.out.println("IO exception when print result to file");
            }
        }

        public static Expression createOFTUseCase()
        {
            Expression[] emptyArg = {};
            Expression[] trueArg = new Expression[1];
            trueArg[0] = new IntLiteral(1);
            Expression oftUseCase = new Conditional(
                    new ObjectMethod("read", "a", emptyArg),
                    new Sequence(new ObjectMethod("write", "a", trueArg),
                            new Conditional(new Var("x"),
                                    new ObjectMethod("read", "i1", emptyArg),
                                    new ObjectMethod("read", "i2", emptyArg))),
                    new IntLiteral(0));
            return oftUseCase;
        }
}
