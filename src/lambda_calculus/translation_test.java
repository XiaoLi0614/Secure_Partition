package lambda_calculus;

//import javax.swing.plaf.nimbus.State;
import fj.Hash;
import lambda_calculus.cps_ast.tree.Context;
import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.translateToOtherAST;
import lambda_calculus.partition_package.tree.MethodDefinition;
import lambda_calculus.partition_package.visitor.*;
import lambda_calculus.source_ast.tree.expression.Conditional;
import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.tree.expression.ObjectMethod;
import lambda_calculus.source_ast.tree.expression.Var;
import lambda_calculus.source_ast.tree.expression.literal.IntLiteral;
import lambda_calculus.source_ast.tree.expression.op.Plus;
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
        //Expression lambda1 = createOFTUseCase();
        Expression lambda1 = createTicketsUseCase();
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
        //OneTimeTransferTypeChecking(resultMethodDefs);
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

/*        //because we do not have several return, we need to split the makeOffer methods into two different methods
        public static Expression createAuctionUseCase()
        {
            Expression[] emptyArg = {};
            Expression[] oAsArg = new Expression[1];
            oAsArg[0] = new Var("o");
            Expression[] offerAAsArg = new Expression[1];
            offerAAsArg[0] = new Var("offerA");

            Expression[] AMakeOfferArgs = new Expression[2];
            AMakeOfferArgs[0] = new ObjectMethod("read", "user", emptyArg, "u");
            AMakeOfferArgs[1] = new Var("o");

            Expression[] userUpdate1Args = new Expression[2];
            userUpdate1Args[0] = new ObjectMethod("makeOffer1", "A", AMakeOfferArgs, "seatInfoA");
            userUpdate1Args[1] = new ObjectMethod("makeOffer2", "A", AMakeOfferArgs, "offerA");

            Expression[] userUpdate2Args = new Expression[2];
            userUpdate2Args[0] = new ObjectMethod("makeOffer1", "B", AMakeOfferArgs, "seatInfoB");
            userUpdate2Args[1] = new ObjectMethod("makeOffer2", "B", AMakeOfferArgs, "offerB");

            //todo: how to make the recursion call in the ast tree?
            Expression auctionUseCase = new Sequence(new ObjectMethod("update", "user", userUpdate1Args)
                    , new Conditional(new Plus(new Var("o"),new Var("offerA")),
                    new ObjectMethod("declareWinner", "user", oAsArg),
                    new Sequence(new ObjectMethod("update", "user", userUpdate2Args),
                            new Conditional(new Plus(new Var("offerA"), new Var("offerB")), auctionUseCase
                                    ,
                                    new ObjectMethod("declareWinner", "user", offerAAsArg)))));
            return auctionUseCase;
        }*/

        public static Expression createTicketsUseCase(){
            Expression[] emptyArg = {};

            Expression[] getPriceArg = new Expression[1];
            getPriceArg[0] = new ObjectMethod("ticketNum", "customer", emptyArg, "num");
            Expression[] getPriceArg1 = new Expression[1];
            getPriceArg1[0] = new Var("num");

            Expression[] getBalanceArg = new Expression[1];
            getBalanceArg[0] = new ObjectMethod("getID", "customer", emptyArg, "ID");
            Expression[] getBalanceArg1 = new Expression[1];
            getBalanceArg1[0] = new Var("ID");

            Expression[] decSeatArg = new Expression[1];
            decSeatArg[0] = new Var("num");
            Expression[] decBalanceArg = new Expression[1];
            //todo: may need to change this to the object method call to get ID
            decBalanceArg[0] = new Var("ID");

            Expression[] updateInfoArgs = new Expression[2];
            updateInfoArgs[0] = new ObjectMethod("getPrice1", "airline", getPriceArg, "schedule");
            updateInfoArgs[1] = new ObjectMethod("getPrice2", "airline", getPriceArg1, "price");

            Expression[] updatePaymentArgs = new Expression[2];
            updatePaymentArgs[0] = new ObjectMethod("getBalance1", "bank", getBalanceArg, "cashback");
            updatePaymentArgs[1] = new ObjectMethod("getBalance2", "bank", getBalanceArg1, "balance");

            Expression ticketUseCase = new Sequence(new ObjectMethod("updateInfo", "customer", updateInfoArgs),
                    new Sequence(new ObjectMethod("updatePayment", "customer", updatePaymentArgs),
                            new Conditional(new Plus(new Var("price"), new Var("balance")),
                                    new Sequence(new ObjectMethod("decSeat", "airline", decSeatArg),
                                            new Sequence(new ObjectMethod("decBalance", "bank", decBalanceArg),
                                                    new IntLiteral(1))),
                                    new IntLiteral(0))));
            return ticketUseCase;
        }

    public static Expression createTestUseCase(){
        Expression[] emptyArg = {};

        Expression[] getPriceArg = new Expression[1];
        getPriceArg[0] = new ObjectMethod("ticketNum", "customer", emptyArg, "num");
        Expression[] getPriceArg1 = new Expression[1];
        getPriceArg1[0] = new Var("num");

        Expression[] getBalanceArg = new Expression[1];
        getBalanceArg[0] = new ObjectMethod("getID", "customer", emptyArg, "ID");
        Expression[] getBalanceArg1 = new Expression[1];
        getBalanceArg1[0] = new Var("ID");

        Expression[] decSeatArg = new Expression[1];
        decSeatArg[0] = new Var("num");
        Expression[] decBalanceArg = new Expression[1];
        //todo: may need to change this to the object method call to get ID
        decBalanceArg[0] = new Var("ID");

        Expression[] updateInfoArgs = new Expression[2];
        updateInfoArgs[0] = new ObjectMethod("getPrice1", "airline", getPriceArg, "schedule");
        updateInfoArgs[1] = new ObjectMethod("getPrice2", "airline", getPriceArg1, "price");

        Expression[] updatePaymentArgs = new Expression[2];
        updatePaymentArgs[0] = new ObjectMethod("getBalance1", "bank", getBalanceArg1, "cashback");
        updatePaymentArgs[1] = new ObjectMethod("getBalance2", "bank", getBalanceArg, "balance");

        Expression ticketUseCase = new ObjectMethod("updateInfo", "customer", updateInfoArgs);
        return ticketUseCase;
    }

        public static void OneTimeTransferTypeChecking(ArrayList<MethodDefinition> resultMethodDefs){
            //failure situation for return value and m0 and m1
            HashSet<Integer> b1 = new HashSet<Integer>(Arrays.asList(1, 2, 8));
            HashSet<Integer> b2 = new HashSet<Integer>(Arrays.asList(1, 2, 9));
            HashSet<Integer> b3 = new HashSet<Integer>(Arrays.asList(1, 3, 8));
            HashSet<Integer> b4 = new HashSet<Integer>(Arrays.asList(1, 3, 9));
            HashSet<nodeSet> B = new HashSet<>();
            B.add(new nodeSet(b1));
            B.add(new nodeSet(b2));
            B.add(new nodeSet(b3));
            B.add(new nodeSet(b4));

            HashSet<Integer> i1B1 = new HashSet<>(Arrays.asList(1, 2, 8, 9, 10, 11));
            HashSet<Integer> i1B2 = new HashSet<>(Arrays.asList(1, 3, 8, 9, 10, 11));
            HashSet<nodeSet> Bi1 = new HashSet<>();
            Bi1.add(new nodeSet(i1B1));
            Bi1.add(new nodeSet(i1B2));

            HashSet<Integer> i2B1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
            HashSet<Integer> i2B2 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9));
            HashSet<nodeSet> Bi2 = new HashSet<>();
            Bi2.add(new nodeSet(i2B1));
            Bi2.add(new nodeSet(i2B2));

            //confidentiality information for objects
            HashSet<Integer> c1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 12));
            HashSet<Integer> c2 = new HashSet<>(Arrays.asList(8, 9, 10, 11, 12));
            HashSet<Integer> cx = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
            HashSet<Integer> cret = new HashSet<>(Arrays.asList(12));

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

            HashSet<nodeSet> Q2_i1 = new HashSet<>();
            HashSet<Integer> Q2_i1_1 = new HashSet<>(Arrays.asList(4, 5, 6));
            Q2_i1.add(new nodeSet(Q2_i1_1));
            HashSet<nodeSet> Q2_i2 = new HashSet<>();
            HashSet<Integer> Q2_i2_1 = new HashSet<>(Arrays.asList(10, 11));
            Q2_i2.add(new nodeSet(Q2_i2_1));
            HashSet<nodeSet> Q2_a = new HashSet<>();
            HashSet<Integer> Q2_a_1 = new HashSet<>(Arrays.asList(4, 5, 6, 10, 11));
            Q2_a.add(new nodeSet(Q2_a_1));

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

            ArrayList<ArrayList<String>> mANames = new ArrayList<>(4);
            for(int i = 0; i < 4; i++){
                mANames.add(new ArrayList<>());
            }
            ArrayList<Pair<ArrayList<CIAType>, ArrayList<CIAType>>> methodSig = new ArrayList<>();
            CIAType m1retType = new CIAType(new nodeSet(cret), new quorumDef(B), new quorumDef(B));
            CIAType m2retType = m1retType.clone();
            CIAType m3retType = m1retType.clone();
            CIAType m4retType = m1retType.clone();

            //context type for m4, which is the bottom
            //HashSet<nodeSet> m4context = new HashSet<>();
            //context type for m4 should be higher than the type of x
            //m4context.add(new nodeSet(cx));
            CIAType m4Context = new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B));
            CIAType m3Context = m4Context.ciaJoin(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
            CIAType m2Context = m3Context.clone();
            CIAType m1Context = m3Context.clone();
            ArrayList<CIAType> m1 = new ArrayList<>();
            m1.add(m1Context);
            m1.add(m1retType);
            methodSig.add(new Pair<>(m1, new ArrayList<>()));
            ArrayList<CIAType> m2 = new ArrayList<>();
            m2.add(m2Context);
            m2.add(m2retType);
            methodSig.add(new Pair<>(m2, new ArrayList<>()));
            ArrayList<CIAType> m3 = new ArrayList<>();
            m3.add(m3Context);
            m3.add(m3retType);
            methodSig.add(new Pair<>(m3, new ArrayList<>()));
            mANames.get(2).add("x");
            methodSig.get(2).element2.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
            ArrayList<CIAType> m4 = new ArrayList<>();
            m4.add(m4Context);
            m4.add(m4retType);
            methodSig.add(new Pair<>(m4, new ArrayList<>()));
            mANames.get(3).add("x");
            methodSig.get(3).element2.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));


            //input the object host information and signature manually
            HashMap<String, HashMap<String, ArrayList<CIAType>>> objSigs = new HashMap<>();
            //for object a
            ArrayList<CIAType> awriteArg = new ArrayList<>();
            awriteArg.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
            //ret is in the last index
            awriteArg.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
            HashMap<String, ArrayList<CIAType>> amethods = new HashMap<>();
            amethods.put("write", awriteArg);
            ArrayList<CIAType> aread = new ArrayList<>();
            aread.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
            amethods.put("read", aread);
            objSigs.put("a", amethods);
            //for object i1 and i2
            HashMap<String, ArrayList<CIAType>> i1methods = new HashMap<>();
            ArrayList<CIAType> i1read = new ArrayList<>();
            //ret
            i1read.add(new CIAType(new nodeSet(c1), new quorumDef(Bi1), new quorumDef(Bi1)));
            i1methods.put("read", i1read);
            objSigs.put("i1", i1methods);
            HashMap<String, ArrayList<CIAType>> i2methods = new HashMap<>();
            ArrayList<CIAType> i2read = new ArrayList<>();
            //ret
            i2read.add(new CIAType(new nodeSet(c2), new quorumDef(Bi2), new quorumDef(Bi2)));
            i2methods.put("read", i2read);
            objSigs.put("i2", i2methods);

            //the objects hosts and hear from information
            HashMap<String, Pair<quorumDef, quorumDef>> objInfo = new HashMap<>();
            Pair<quorumDef, quorumDef> i1Info = new Pair<>(new quorumDef(Qi1),new quorumDef(Q2_i1));
            objInfo.put("i1", i1Info);
            Pair<quorumDef, quorumDef> i2Info = new Pair<>(new quorumDef(Qi2), new quorumDef(Q2_i2));
            objInfo.put("i2", i2Info);
            Pair<quorumDef, quorumDef> aInfo = new Pair<>(new quorumDef(Qa), new quorumDef(Q2_a));
            objInfo.put("a", aInfo);

            //input the predefined variable information
            HashMap<String, CIAType> p = new HashMap<>();
            p.put("x", new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
            //input predefine umbrella for the objects
            HashMap<String, CIAType> u = new HashMap<>();
            u.put("i1", new CIAType(new nodeSet(c1), new quorumDef(Bi1), new quorumDef(Bi1)));
            u.put("i2", new CIAType(new nodeSet(c2), new quorumDef(Bi2), new quorumDef(Bi2)));
            u.put("a", new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
            //printToFile(lambda1., "oft_cps");

            SecureTypeChecking test5 = new SecureTypeChecking();
            Boolean r = test5.classTypeCheck(resultMethodDefs, methodsInfo, methodSig, mANames, objSigs, objInfo, p, u);
            System.out.println("The type checking result for one time transfer program:" + r.toString());
        }

    public static void TicketTypeChecking(ArrayList<MethodDefinition> resultMethodDefs){
        //failure situation B0:{{1, 2, 8}, {1, 2, 9}, {1, 3, 8}, {1, 3, 9}}
        HashSet<Integer> b1 = new HashSet<Integer>(Arrays.asList(1, 2, 8));
        HashSet<Integer> b2 = new HashSet<Integer>(Arrays.asList(1, 2, 9));
        HashSet<Integer> b3 = new HashSet<Integer>(Arrays.asList(1, 3, 8));
        HashSet<Integer> b4 = new HashSet<Integer>(Arrays.asList(1, 3, 9));
        HashSet<nodeSet> B0 = new HashSet<>();
        B0.add(new nodeSet(b1));
        B0.add(new nodeSet(b2));
        B0.add(new nodeSet(b3));
        B0.add(new nodeSet(b4));

        //failure situation B1:{{1, 2, 8, 9, 10, 11}, {1, 3, 8, 9, 10, 11}}
        HashSet<Integer> B1s1 = new HashSet<>(Arrays.asList(1, 2, 8, 9, 10, 11));
        HashSet<Integer> B1s2 = new HashSet<>(Arrays.asList(1, 3, 8, 9, 10, 11));
        HashSet<nodeSet> B1 = new HashSet<>();
        B1.add(new nodeSet(B1s1));
        B1.add(new nodeSet(B1s2));

        //failure situation B2:{{1, 2, 3, 4, 5, 6, 7, 8}, {1, 2, 3, 4, 5, 6, 7, 9}}
        HashSet<Integer> B2s1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        HashSet<Integer> B2s2 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9));
        HashSet<nodeSet> B2 = new HashSet<>();
        B2.add(new nodeSet(B1s1));
        B2.add(new nodeSet(B2s2));

        //failure situation B3:{{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}}
        HashSet<Integer> B3s1  = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        HashSet<nodeSet> B3 = new HashSet<>();
        B3.add(new nodeSet(B3s1));

        //confidentiality information for objects
        HashSet<Integer> c1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 12));
        HashSet<Integer> c2 = new HashSet<>(Arrays.asList(8, 9, 10, 11, 12));
        HashSet<Integer> cret = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));

        //construct the types
        CIAType t0 = new CIAType(new nodeSet(cret), new quorumDef(B3), new quorumDef(B3));
        CIAType tp = new CIAType(new nodeSet(cret), new quorumDef(B1), new quorumDef(B1));
        CIAType ts = new CIAType(new nodeSet(c1), new quorumDef(B1), new quorumDef(B1));
        CIAType td = new CIAType(new nodeSet(cret), new quorumDef(B0), new quorumDef(B0));
        CIAType tc = new CIAType(new nodeSet(c2), new quorumDef())

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

        HashSet<nodeSet> Q2_i1 = new HashSet<>();
        HashSet<Integer> Q2_i1_1 = new HashSet<>(Arrays.asList(4, 5, 6));
        Q2_i1.add(new nodeSet(Q2_i1_1));
        HashSet<nodeSet> Q2_i2 = new HashSet<>();
        HashSet<Integer> Q2_i2_1 = new HashSet<>(Arrays.asList(10, 11));
        Q2_i2.add(new nodeSet(Q2_i2_1));
        HashSet<nodeSet> Q2_a = new HashSet<>();
        HashSet<Integer> Q2_a_1 = new HashSet<>(Arrays.asList(4, 5, 6, 10, 11));
        Q2_a.add(new nodeSet(Q2_a_1));

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

        ArrayList<ArrayList<String>> mANames = new ArrayList<>(4);
        for(int i = 0; i < 4; i++){
            mANames.add(new ArrayList<>());
        }
        ArrayList<Pair<ArrayList<CIAType>, ArrayList<CIAType>>> methodSig = new ArrayList<>();
        CIAType m1retType = new CIAType(new nodeSet(cret), new quorumDef(B), new quorumDef(B));
        CIAType m2retType = m1retType.clone();
        CIAType m3retType = m1retType.clone();
        CIAType m4retType = m1retType.clone();

        //context type for m4, which is the bottom
        //HashSet<nodeSet> m4context = new HashSet<>();
        //context type for m4 should be higher than the type of x
        //m4context.add(new nodeSet(cx));
        CIAType m4Context = new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B));
        CIAType m3Context = m4Context.ciaJoin(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        CIAType m2Context = m3Context.clone();
        CIAType m1Context = m3Context.clone();
        ArrayList<CIAType> m1 = new ArrayList<>();
        m1.add(m1Context);
        m1.add(m1retType);
        methodSig.add(new Pair<>(m1, new ArrayList<>()));
        ArrayList<CIAType> m2 = new ArrayList<>();
        m2.add(m2Context);
        m2.add(m2retType);
        methodSig.add(new Pair<>(m2, new ArrayList<>()));
        ArrayList<CIAType> m3 = new ArrayList<>();
        m3.add(m3Context);
        m3.add(m3retType);
        methodSig.add(new Pair<>(m3, new ArrayList<>()));
        mANames.get(2).add("x");
        methodSig.get(2).element2.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        ArrayList<CIAType> m4 = new ArrayList<>();
        m4.add(m4Context);
        m4.add(m4retType);
        methodSig.add(new Pair<>(m4, new ArrayList<>()));
        mANames.get(3).add("x");
        methodSig.get(3).element2.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));


        //input the object host information and signature manually
        HashMap<String, HashMap<String, ArrayList<CIAType>>> objSigs = new HashMap<>();
        //for object a
        ArrayList<CIAType> awriteArg = new ArrayList<>();
        awriteArg.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        //ret is in the last index
        awriteArg.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        HashMap<String, ArrayList<CIAType>> amethods = new HashMap<>();
        amethods.put("write", awriteArg);
        ArrayList<CIAType> aread = new ArrayList<>();
        aread.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        amethods.put("read", aread);
        objSigs.put("a", amethods);
        //for object i1 and i2
        HashMap<String, ArrayList<CIAType>> i1methods = new HashMap<>();
        ArrayList<CIAType> i1read = new ArrayList<>();
        //ret
        i1read.add(new CIAType(new nodeSet(c1), new quorumDef(Bi1), new quorumDef(Bi1)));
        i1methods.put("read", i1read);
        objSigs.put("i1", i1methods);
        HashMap<String, ArrayList<CIAType>> i2methods = new HashMap<>();
        ArrayList<CIAType> i2read = new ArrayList<>();
        //ret
        i2read.add(new CIAType(new nodeSet(c2), new quorumDef(Bi2), new quorumDef(Bi2)));
        i2methods.put("read", i2read);
        objSigs.put("i2", i2methods);

        //the objects hosts and hear from information
        HashMap<String, Pair<quorumDef, quorumDef>> objInfo = new HashMap<>();
        Pair<quorumDef, quorumDef> i1Info = new Pair<>(new quorumDef(Qi1),new quorumDef(Q2_i1));
        objInfo.put("i1", i1Info);
        Pair<quorumDef, quorumDef> i2Info = new Pair<>(new quorumDef(Qi2), new quorumDef(Q2_i2));
        objInfo.put("i2", i2Info);
        Pair<quorumDef, quorumDef> aInfo = new Pair<>(new quorumDef(Qa), new quorumDef(Q2_a));
        objInfo.put("a", aInfo);

        //input the predefined variable information
        HashMap<String, CIAType> p = new HashMap<>();
        p.put("x", new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        //input predefine umbrella for the objects
        HashMap<String, CIAType> u = new HashMap<>();
        u.put("i1", new CIAType(new nodeSet(c1), new quorumDef(Bi1), new quorumDef(Bi1)));
        u.put("i2", new CIAType(new nodeSet(c2), new quorumDef(Bi2), new quorumDef(Bi2)));
        u.put("a", new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        //printToFile(lambda1., "oft_cps");

        SecureTypeChecking test5 = new SecureTypeChecking();
        Boolean r = test5.classTypeCheck(resultMethodDefs, methodsInfo, methodSig, mANames, objSigs, objInfo, p, u);
        System.out.println("The type checking result for ticket system program:" + r.toString());
    }
}
