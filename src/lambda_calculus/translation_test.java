package lambda_calculus;

//import javax.swing.plaf.nimbus.State;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import fj.Hash;
import graph.lang.ast.False;
import lambda_calculus.cps_ast.tree.Context;
import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.translateToOtherAST;
import lambda_calculus.partition_package.tree.MethodDefinition;
import lambda_calculus.partition_package.visitor.*;
import lambda_calculus.source_ast.tree.expression.*;
import lambda_calculus.source_ast.tree.expression.literal.IntLiteral;
import lambda_calculus.source_ast.tree.expression.op.Plus;
import lambda_calculus.source_ast.tree.expression.op.Sequence;
import lambda_calculus.source_ast.visitor.CPSPrinter;
import lesani.collection.Pair;
import com.google.common.collect.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.collect.Sets.newHashSet;


public class translation_test {
    static String outputPath = "/home/xiao/IdeaProjects/secure_partition/src/lambda_calculus/partition_package/visitor/";
    public static void main(String[] args) throws IOException
    {
        //Expression lambda1 = createOFTUseCase();
        //Expression lambda1 = createTicketsUseCase();
        Expression lambda1 = createObliviousTransferUseCase();
        //Expression lambda1 = createAuctionUseCase();
        //Expression lambda1 = createTestUseCase();
        System.out.println("Complete create use-case");
        CPSPrinter test = new CPSPrinter();
        Command resultAST = test.print(lambda1).element1;
        //Context resultContext = test.print(lambda1).element2;
        System.out.println("Complete ast translation: " + resultAST.toString());
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

        System.out.println("\nStart generate constraints: \n");
        //printToFile(OneTimeTransferInfer(resultMethodDefs), "OneTimeTransfer");
        //printToFile(AuctionInfer(resultMethodDefs), "Auction");
        //printToFile(TicketInfer(resultMethodDefs), "Ticket");
        printToFile(ObliviousTransferInfer(resultMethodDefs), "ObliviousTransfer");

        //OneTimeTransferTypeCheckingP(resultMethodDefs);
        //TicketTypeChecking(resultMethodDefs);
        //TicketTypeCheckingP(resultMethodDefs);
        //ObliviousTransferTypeCheckingP(resultMethodDefs);
        //AuctionTypeCheckingP(resultMethodDefs);

        //test for powersets
/*        HashSet<Integer> in = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        nodeSet in1 = new nodeSet(in);
        getPowerSet(in1, 2);*/

    }

//        public static String cpsTransformation(lambda_usecase useCase)
//        {
//            String result = new boolean[useCase.getOperations().size()][useCase.getOperations().size()];
//            System.out.println("CPS transformation:");
//            return result;
//        }

    public static void printToFile(String transformation, String fileName) throws IOException
    {
        try {
            File f = new File(outputPath + fileName);
            if (!f.exists()) {
                f.createNewFile();
            }

            File template = new File(outputPath + "constraints_template");
            if (!template.exists()) {
                template.createNewFile();
            }

            //add the constraints template to output file
            FileChannel tempChannel = new FileInputStream(template).getChannel();
            FileChannel fChannel = new FileOutputStream(f).getChannel();
            fChannel.transferFrom(tempChannel, 0, tempChannel.size());
            tempChannel.close();
            fChannel.close();

            //write the collected constraints for the specific use-case
            FileOutputStream consOutputStream = new FileOutputStream(f, true);
            consOutputStream.write(transformation.getBytes());
            consOutputStream.flush();
            consOutputStream.close();
        }
        catch (IOException e) {
            System.out.println("IO exception when print result to file");
            e.printStackTrace();
        }
    }

    public static String OneTimeTransferInfer(ArrayList<MethodDefinition> resultDefs)
    {
        StringBuilder r = new StringBuilder();

        //initialize the method argument name array
        ArrayList<ArrayList<String>> mArgNames = new ArrayList<>();
        ArrayList<String> m1ArgNames = new ArrayList<>();
        m1ArgNames.add("return");
        ArrayList<String> m2ArgNames = new ArrayList<>();
        m2ArgNames.add("x");
        ArrayList<String> m3ArgNames = new ArrayList<>();
        m3ArgNames.add("x");
        mArgNames.add(m1ArgNames);
        mArgNames.add(new ArrayList<>());
        mArgNames.add(new ArrayList<>());
        mArgNames.add(m2ArgNames);
        mArgNames.add(m3ArgNames);

        //initialize the object method argument number array
        HashMap<String, HashMap<String, Integer>> oArgNums;
        oArgNums = new HashMap<>();
        oArgNums.put("i1", new HashMap<>());
        oArgNums.get("i1").put("read", new Integer(0));
        oArgNums.put("i2", new HashMap<>());
        oArgNums.get("i2").put("read", new Integer(0));
        oArgNums.put("a", new HashMap<>());
        oArgNums.get("a").put("read", new Integer(0));
        oArgNums.get("a").put("write", new Integer(1));

        //initialize the principals
        ArrayList<Integer> principals = new ArrayList<>();
        principals.add(new Integer(4));
        principals.add(new Integer(7));
        principals.add(new Integer(1));

        //initialize the return type
        ArrayList<Boolean> rc = new ArrayList<>();
        //[False, False, True]
        rc.add(false);
        rc.add(false);
        rc.add(true);
        //resultI = [[1, 2, 0], [0, 0, 0], [0, 0, 0]]
        ArrayList<ArrayList<Integer>> ri = new ArrayList<>();
        ArrayList<Integer> ri0 = new ArrayList<>();
        ri0.add(new Integer(1));
        ri0.add(new Integer(2));
        ri0.add(new Integer(0));
        ArrayList<Integer> ri1 = new ArrayList<>();
        ri1.add(new Integer(0));
        ri1.add(new Integer(0));
        ri1.add(new Integer(0));
        ri.add(ri0);
        ri.add(ri1);
        ri.add(ri1);
        ArrayList<ArrayList<Integer>> ra = ri;

        //initialize the start context type
        ArrayList<Boolean> sc = new ArrayList<>();
        //[True, True, True]
        sc.add(true);
        sc.add(true);
        sc.add(true);
        //[[4, 7, 0], [0, 0, 0], [0, 0, 0]]
        ArrayList<ArrayList<Integer>> si = new ArrayList<>();
        ArrayList<Integer> si0 = new ArrayList<>();
        si0.add(new Integer(4));
        si0.add(new Integer(7));
        si0.add(new Integer(0));
        ArrayList<Integer> si1 = new ArrayList<>();
        si1.add(new Integer(0));
        si1.add(new Integer(0));
        si1.add(new Integer(0));
        si.add(si0);
        si.add(si1);
        si.add(si1);
        ArrayList<ArrayList<Integer>> sa = si;

        //initialize the bot type
        ArrayList<Boolean> bc = sc;
        ArrayList<ArrayList<Integer>> bi = si;
        ArrayList<ArrayList<Integer>> ba = sa;

        //initialize the rH [0, 0, 1]
        ArrayList<Integer> resH = new ArrayList<>();
        resH.add(new Integer(0));
        resH.add(new Integer(0));
        resH.add(new Integer(1));

        //initialize the pre-defined variables
        //x is the same as bot(start context)
        HashMap<String, ArrayList<Boolean>> preV = new HashMap<>();
        preV.put("x", sc);

        //initialize the pre-define object methods type
        //i1.read() [True, False, True], i2.read() [False, True, True]
        HashMap<String, HashMap<String, ArrayList<Boolean>>> preOM = new HashMap<>();
        preOM.put("i1", new HashMap<>());
        ArrayList<Boolean> i1C = new ArrayList<>();
        i1C.add(true);
        i1C.add(false);
        i1C.add(true);
        preOM.get("i1").put("i1readoutputC", i1C);

        preOM.put("i2", new HashMap<>());
        preOM.put("i2", new HashMap<>());
        ArrayList<Boolean> i2C = new ArrayList<>();
        i2C.add(false);
        i2C.add(true);
        i2C.add(true);
        preOM.get("i2").put("i2readoutputC", i2C);

        preOM.put("a", new HashMap<>());

        ArrayList<Integer> w = new ArrayList<>();
        w.add(new Integer(1));
        w.add(new Integer(1));
        w.add(new Integer(12));

        TypeInference test = new TypeInference();
        r.append(test.classTypeCheck(resultDefs, mArgNames, oArgNums, 3, principals,
                rc, ri, ra, sc, si, sa, bc, bi, ba, resH, preV, preOM, w).toString());

        return r.toString();
    }

    //for the auction use-case, we have to allow some of the methods hosted on client
    public static String AuctionInfer(ArrayList<MethodDefinition> resultDefs)
    {
        StringBuilder r = new StringBuilder();

        //initialize the method argument name array
        ArrayList<ArrayList<String>> mArgNames = new ArrayList<>();
        ArrayList<String> resArgNames = new ArrayList<>();
        resArgNames.add("return");
        ArrayList<String> m0ArgNames = new ArrayList<>();
        m0ArgNames.add("o");
        ArrayList<String> m1ArgNames = new ArrayList<>();
        m1ArgNames.add("offerA");
        ArrayList<String> m2ArgNames = new ArrayList<>();
        m2ArgNames.add("offerB");
        m2ArgNames.add("offerA");
        m2ArgNames.add("seatInfoB");
        ArrayList<String> m3ArgNames = new ArrayList<>();
        m3ArgNames.add("offerA");
        m3ArgNames.add("seatInfoB");
        m3ArgNames.add("u");
        ArrayList<String> m4ArgNames = new ArrayList<>();
        m4ArgNames.add("offerA");
        m4ArgNames.add("u");
        ArrayList<String> m5ArgNames = new ArrayList<>();
        m5ArgNames.add("offerA");
        m5ArgNames.add("u");
        m5ArgNames.add("seatInfoA");
        m5ArgNames.add("o");
        ArrayList<String> m6ArgNames = new ArrayList<>();
        m6ArgNames.add("u");
        m6ArgNames.add("seatInfoA");
        m6ArgNames.add("o");
        ArrayList<String> m7ArgNames = new ArrayList<>();
        m7ArgNames.add("u");
        m7ArgNames.add("o");
        ArrayList<String> m8ArgNames = new ArrayList<>();
        m8ArgNames.add("o");

        mArgNames.add(resArgNames);
        mArgNames.add(m0ArgNames);
        mArgNames.add(m1ArgNames);
        mArgNames.add(m2ArgNames);
        mArgNames.add(m3ArgNames);
        mArgNames.add(m4ArgNames);
        mArgNames.add(m5ArgNames);
        mArgNames.add(m6ArgNames);
        mArgNames.add(m7ArgNames);
        mArgNames.add(m8ArgNames);

        //initialize the object method argument number array
        HashMap<String, HashMap<String, Integer>> oArgNums;
        oArgNums = new HashMap<>();
        oArgNums.put("A", new HashMap<>());
        oArgNums.get("A").put("makeOffer1", new Integer(2));
        oArgNums.get("A").put("makeOffer2", new Integer(2));
        oArgNums.put("B", new HashMap<>());
        oArgNums.get("B").put("makeOffer1", new Integer(2));
        oArgNums.get("B").put("makeOffer2", new Integer(2));
        oArgNums.put("user", new HashMap<>());
        oArgNums.get("user").put("read", new Integer(0));
        oArgNums.get("user").put("update", new Integer(2));
        oArgNums.get("user").put("declareWinner", new Integer(1));

        //initialize the principals
        ArrayList<Integer> principals = new ArrayList<>();
        principals.add(new Integer(4));
        principals.add(new Integer(7));
        principals.add(new Integer(1));

        //initialize the return type
        ArrayList<Boolean> rc = new ArrayList<>();
        //[False, False, True]
        rc.add(false);
        rc.add(false);
        rc.add(true);
        //resultI = [[1, 2, 0], [0, 0, 0], [0, 0, 0]]
        ArrayList<ArrayList<Integer>> ri = new ArrayList<>();
        ArrayList<Integer> ri0 = new ArrayList<>();
        ri0.add(new Integer(1));
        ri0.add(new Integer(2));
        ri0.add(new Integer(0));
        ArrayList<Integer> ri1 = new ArrayList<>();
        ri1.add(new Integer(0));
        ri1.add(new Integer(0));
        ri1.add(new Integer(0));
        ri.add(ri0);
        ri.add(ri1);
        ri.add(ri1);
        ArrayList<ArrayList<Integer>> ra = ri;

        //initialize the start context type
        ArrayList<Boolean> sc = new ArrayList<>();
        //[True, True, True]
        sc.add(true);
        sc.add(true);
        sc.add(true);
        //[[4, 7, 0], [0, 0, 0], [0, 0, 0]]
        ArrayList<ArrayList<Integer>> si = new ArrayList<>();
        ArrayList<Integer> si0 = new ArrayList<>();
        si0.add(new Integer(4));
        si0.add(new Integer(7));
        si0.add(new Integer(0));
        ArrayList<Integer> si1 = new ArrayList<>();
        si1.add(new Integer(0));
        si1.add(new Integer(0));
        si1.add(new Integer(0));
        si.add(si0);
        si.add(si1);
        si.add(si1);
        ArrayList<ArrayList<Integer>> sa = si;

        //initialize the bot type
        ArrayList<Boolean> bc = sc;
        ArrayList<ArrayList<Integer>> bi = si;
        ArrayList<ArrayList<Integer>> ba = sa;

        //initialize the rH [0, 0, 1]
        ArrayList<Integer> resH = new ArrayList<>();
        resH.add(new Integer(0));
        resH.add(new Integer(0));
        resH.add(new Integer(1));

        //initialize the pre-defined variables
        //o is the same as bot(start context)
        HashMap<String, ArrayList<Boolean>> preV = new HashMap<>();
        preV.put("o", sc);

        //initialize the pre-define object methods type
        //user.read() [True, True, True]
        //A.makeOffer1(u, o) [True, False, True], B.makeOffer1(u, offerA) [False, True, True]
        //A.makeOffer2(u, o) [True, True, True], B.makeOffer2(u, offerA) [True, True, True]
        HashMap<String, HashMap<String, ArrayList<Boolean>>> preOM = new HashMap<>();
        preOM.put("A", new HashMap<>());
        ArrayList<Boolean> A1C = new ArrayList<>();
        A1C.add(true);
        A1C.add(false);
        A1C.add(true);
        preOM.get("A").put("AmakeOffer1outputC", A1C);
        ArrayList<Boolean> A2C = new ArrayList<>();
        A2C.add(true);
        A2C.add(true);
        A2C.add(true);
        preOM.get("A").put("AmakeOffer2outputC", A2C);

        preOM.put("B", new HashMap<>());
        ArrayList<Boolean> B1C = new ArrayList<>();
        B1C.add(false);
        B1C.add(true);
        B1C.add(true);
        preOM.get("B").put("BmakeOffer1outputC", B1C);
        preOM.get("B").put("BmakeOffer2outputC", A2C);

        preOM.put("user", new HashMap<>());
        preOM.get("user").put("userreadoutputC", A2C);

        ArrayList<Integer> w = new ArrayList<>();
        w.add(new Integer(1));
        w.add(new Integer(1));
        w.add(new Integer(12));

        TypeInference test = new TypeInference();
        r.append(test.classTypeCheck(resultDefs, mArgNames, oArgNums, 3, principals,
                rc, ri, ra, sc, si, sa, bc, bi, ba, resH, preV, preOM, w).toString());

        return r.toString();
    }

    //for the ticket use-case, we have to allow some of the methods hosted on client
    public static String TicketInfer(ArrayList<MethodDefinition> resultDefs)
    {
        StringBuilder r = new StringBuilder();

        //initialize the method argument name array
        ArrayList<ArrayList<String>> mArgNames = new ArrayList<>();
        ArrayList<String> resArgNames = new ArrayList<>();
        resArgNames.add("return");
        ArrayList<String> m0ArgNames = new ArrayList<>();
        m0ArgNames.add("price");
        ArrayList<String> m1ArgNames = new ArrayList<>();
        m1ArgNames.add("price");
        m1ArgNames.add("num");
        ArrayList<String> m2ArgNames = new ArrayList<>();
        m2ArgNames.add("balance");
        m2ArgNames.add("price");
        m2ArgNames.add("num");
        m2ArgNames.add("cashback");
        ArrayList<String> m3ArgNames = new ArrayList<>();
        m3ArgNames.add("price");
        m3ArgNames.add("num");
        m3ArgNames.add("ID");
        m3ArgNames.add("cashback");
        ArrayList<String> m4ArgNames = new ArrayList<>();
        m4ArgNames.add("price");
        m4ArgNames.add("num");
        m4ArgNames.add("ID");
        ArrayList<String> m5ArgNames = new ArrayList<>();
        m5ArgNames.add("price");
        m5ArgNames.add("num");
        ArrayList<String> m6ArgNames = new ArrayList<>();
        m6ArgNames.add("schedule");
        m6ArgNames.add("price");
        m6ArgNames.add("num");
        ArrayList<String> m7ArgNames = new ArrayList<>();
        m7ArgNames.add("schedule");
        m7ArgNames.add("num");
        ArrayList<String> m8ArgNames = new ArrayList<>();
        m8ArgNames.add("num");

        mArgNames.add(resArgNames);
        mArgNames.add(m0ArgNames);
        mArgNames.add(m1ArgNames);
        mArgNames.add(m2ArgNames);
        mArgNames.add(m3ArgNames);
        mArgNames.add(m4ArgNames);
        mArgNames.add(m5ArgNames);
        mArgNames.add(m6ArgNames);
        mArgNames.add(m7ArgNames);
        mArgNames.add(m8ArgNames);
        mArgNames.add(new ArrayList<>());

        //initialize the object method argument number array
        HashMap<String, HashMap<String, Integer>> oArgNums;
        oArgNums = new HashMap<>();
        oArgNums.put("airline", new HashMap<>());
        oArgNums.get("airline").put("getPrice1", new Integer(1));
        oArgNums.get("airline").put("getPrice2", new Integer(1));
        oArgNums.get("airline").put("decSeat", new Integer(1));
        oArgNums.put("bank", new HashMap<>());
        oArgNums.get("bank").put("getBalance1", new Integer(1));
        oArgNums.get("bank").put("getBalance2", new Integer(1));
        oArgNums.get("bank").put("decBalance", new Integer(1));
        oArgNums.put("customer", new HashMap<>());
        oArgNums.get("customer").put("ticketNum", new Integer(0));
        oArgNums.get("customer").put("updateInfo", new Integer(2));
        oArgNums.get("customer").put("updatePayment", new Integer(2));
        oArgNums.get("customer").put("getID", new Integer(0));

        //initialize the principals
        ArrayList<Integer> principals = new ArrayList<>();
        principals.add(new Integer(7));
        principals.add(new Integer(10));
        principals.add(new Integer(1));

        //initialize the return type
        ArrayList<Boolean> rc = new ArrayList<>();
        //[False, False, True]
        rc.add(true);
        rc.add(true);
        rc.add(true);
        //resultI = [[1, 2, 0], [0, 0, 0], [0, 0, 0]]
        ArrayList<ArrayList<Integer>> ri = new ArrayList<>();
        ArrayList<Integer> ri0 = new ArrayList<>();
        ri0.add(new Integer(2));
        ri0.add(new Integer(3));
        ri0.add(new Integer(0));
        ArrayList<Integer> ri1 = new ArrayList<>();
        ri1.add(new Integer(0));
        ri1.add(new Integer(0));
        ri1.add(new Integer(0));
        ri.add(ri0);
        ri.add(ri1);
        ri.add(ri1);
        ArrayList<ArrayList<Integer>> ra = ri;

        //initialize the start context type
        ArrayList<Boolean> sc = new ArrayList<>();
        //[True, True, True]
        sc.add(true);
        sc.add(true);
        sc.add(true);
        //[[7, 10, 0], [0, 0, 0], [0, 0, 0]]
        ArrayList<ArrayList<Integer>> si = new ArrayList<>();
        ArrayList<Integer> si0 = new ArrayList<>();
        si0.add(new Integer(7));
        si0.add(new Integer(10));
        si0.add(new Integer(0));
        ArrayList<Integer> si1 = new ArrayList<>();
        si1.add(new Integer(0));
        si1.add(new Integer(0));
        si1.add(new Integer(0));
        si.add(si0);
        si.add(si1);
        si.add(si1);
        ArrayList<ArrayList<Integer>> sa = si;

        //initialize the bot type
        ArrayList<Boolean> bc = sc;
        ArrayList<ArrayList<Integer>> bi = si;
        ArrayList<ArrayList<Integer>> ba = sa;

        //initialize the rH [0, 0, 1]
        ArrayList<Integer> resH = new ArrayList<>();
        resH.add(new Integer(0));
        resH.add(new Integer(0));
        resH.add(new Integer(1));

        //initialize the pre-defined variables
        HashMap<String, ArrayList<Boolean>> preV = new HashMap<>();

        //initialize the pre-define object methods type
        //
        //airline.getPrice1(num) [True, False, True], bank.getBalance1(ID) [False, True, True]
        //airline.getPrice2(num) [True, True, True], bank.makeOffer2(ID) [True, True, True]
        HashMap<String, HashMap<String, ArrayList<Boolean>>> preOM = new HashMap<>();
        preOM.put("airline", new HashMap<>());
        ArrayList<Boolean> A1C = new ArrayList<>();
        A1C.add(true);
        A1C.add(false);
        A1C.add(true);
        preOM.get("airline").put("airlinegetPrice1outputC", A1C);
        ArrayList<Boolean> A2C = new ArrayList<>();
        A2C.add(true);
        A2C.add(true);
        A2C.add(true);
        preOM.get("airline").put("airlinegetPrice2outputC", A2C);

        preOM.put("bank", new HashMap<>());
        ArrayList<Boolean> B1C = new ArrayList<>();
        B1C.add(false);
        B1C.add(true);
        B1C.add(true);
        preOM.get("bank").put("bankgetBalance1outputC", B1C);
        preOM.get("bank").put("bankgetBalance2outputC", A2C);

        preOM.put("customer", new HashMap<>());
        //preOM.get("user").put("userreadoutputC", A2C);

        ArrayList<Integer> w = new ArrayList<>();
        w.add(new Integer(1));
        w.add(new Integer(1));
        w.add(new Integer(18));

        TypeInference test = new TypeInference();
        r.append(test.classTypeCheck(resultDefs, mArgNames, oArgNums, 3, principals,
                rc, ri, ra, sc, si, sa, bc, bi, ba, resH, preV, preOM, w).toString());

        return r.toString();
    }

    public static String ObliviousTransferInfer(ArrayList<MethodDefinition> resultDefs)
    {
        StringBuilder r = new StringBuilder();

        //initialize the method argument name array
        ArrayList<ArrayList<String>> mArgNames = new ArrayList<>();
        ArrayList<String> rArgNames = new ArrayList<>();
        rArgNames.add("return");
        ArrayList<String> m0ArgNames = new ArrayList<>();
        m0ArgNames.add("x");
        m0ArgNames.add("temp1");
        ArrayList<String> m1ArgNames = new ArrayList<>();
        m1ArgNames.add("x");
        ArrayList<String> m2ArgNames = new ArrayList<>();
        m2ArgNames.add("x");
        ArrayList<String> m3ArgNames = new ArrayList<>();
        m3ArgNames.add("x");
        mArgNames.add(rArgNames);
        mArgNames.add(m0ArgNames);
        mArgNames.add(m1ArgNames);
        mArgNames.add(m2ArgNames);
        mArgNames.add(m3ArgNames);

        //initialize the object method argument number array
        HashMap<String, HashMap<String, Integer>> oArgNums;
        oArgNums = new HashMap<>();
        oArgNums.put("i1", new HashMap<>());
        oArgNums.get("i1").put("read", new Integer(0));
        oArgNums.put("i2", new HashMap<>());
        oArgNums.get("i2").put("read", new Integer(0));
        oArgNums.put("a", new HashMap<>());
        oArgNums.get("a").put("read", new Integer(0));
        oArgNums.get("a").put("write", new Integer(1));

        //initialize the principals
        ArrayList<Integer> principals = new ArrayList<>();
        principals.add(new Integer(7));
        principals.add(new Integer(4));
        principals.add(new Integer(4));

        //initialize the return type
        ArrayList<Boolean> rc = new ArrayList<>();
        //[False, False, True]
        rc.add(false);
        rc.add(false);
        rc.add(true);
        //resultI = [[2, 1, 1], [0, 0, 0], [0, 0, 0]]
        ArrayList<ArrayList<Integer>> ri = new ArrayList<>();
        ArrayList<Integer> ri0 = new ArrayList<>();
        ri0.add(new Integer(2));
        ri0.add(new Integer(1));
        ri0.add(new Integer(1));
        ArrayList<Integer> ri1 = new ArrayList<>();
        ri1.add(new Integer(0));
        ri1.add(new Integer(0));
        ri1.add(new Integer(0));
        ri.add(ri0);
        ri.add(ri1);
        ri.add(ri1);
        ArrayList<ArrayList<Integer>> ra = ri;

        //initialize the start context type
        ArrayList<Boolean> sc = new ArrayList<>();
        //[True, True, True]
        sc.add(true);
        sc.add(true);
        sc.add(true);
        //[[7, 4, 4], [0, 0, 0], [0, 0, 0]]
        ArrayList<ArrayList<Integer>> si = new ArrayList<>();
        ArrayList<Integer> si0 = new ArrayList<>();
        si0.add(new Integer(7));
        si0.add(new Integer(4));
        si0.add(new Integer(4));
        ArrayList<Integer> si1 = new ArrayList<>();
        si1.add(new Integer(0));
        si1.add(new Integer(0));
        si1.add(new Integer(0));
        si.add(si0);
        si.add(si1);
        si.add(si1);
        ArrayList<ArrayList<Integer>> sa = si;

        //initialize the bot type
        ArrayList<Boolean> bc = sc;
        ArrayList<ArrayList<Integer>> bi = si;
        ArrayList<ArrayList<Integer>> ba = sa;

        //initialize the rH [0, 0, 1]
        ArrayList<Integer> resH = new ArrayList<>();
        resH.add(new Integer(0));
        resH.add(new Integer(0));
        resH.add(new Integer(4));

        //initialize the pre-defined variables
        //x is the same as bot(start context)
        HashMap<String, ArrayList<Boolean>> preV = new HashMap<>();
        ArrayList<Boolean> xc = new ArrayList<>();
        xc.add(false);
        xc.add(false);
        xc.add(true);
        preV.put("x", xc);

        //initialize the pre-define object methods type
        //i1.read() [True, False, True], i2.read() [False, True, True]
        HashMap<String, HashMap<String, ArrayList<Boolean>>> preOM = new HashMap<>();
        preOM.put("i1", new HashMap<>());
        ArrayList<Boolean> i1C = new ArrayList<>();
        i1C.add(true);
        i1C.add(false);
        i1C.add(true);
        preOM.get("i1").put("i1readoutputC", i1C);

        preOM.put("i2", new HashMap<>());
        preOM.put("i2", new HashMap<>());
        ArrayList<Boolean> i2C = new ArrayList<>();
        i2C.add(false);
        i2C.add(true);
        i2C.add(true);
        preOM.get("i2").put("i2readoutputC", i2C);

        preOM.put("a", new HashMap<>());

        ArrayList<Integer> w = new ArrayList<>();
        w.add(new Integer(1));
        w.add(new Integer(1));
        w.add(new Integer(12));

        TypeInference test = new TypeInference();
        r.append(test.classTypeCheck(resultDefs, mArgNames, oArgNums, 3, principals,
                rc, ri, ra, sc, si, sa, bc, bi, ba, resH, preV, preOM, w).toString());

        return r.toString();
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

        public static Expression createObliviousTransferUseCase()
        {
            Expression[] emptyArg = {};
            Expression[] trueArg = new Expression[1];
            trueArg[0] = new IntLiteral(1);
            Expression otUseCase = new Conditional(
                    new ObjectMethod("read", "a", emptyArg),
                    new Sequence(new ObjectMethod("write", "a", trueArg),
                            new Sequence(new ObjectMethod("read", "i1", emptyArg, "temp1"),
                                    new Sequence(new ObjectMethod("read", "i2", emptyArg, "temp2"),
                                            new Conditional(new Var("x"), new Var("temp1"), new Var("temp2"))))),
                    new IntLiteral(0));
            return otUseCase;
        }

        //because we do not have several return, we need to split the makeOffer methods into two different methods
        public static Expression createAuctionUseCase()
        {
            Expression[] emptyArg = {};
            Expression[] oAsArg = new Expression[1];
            oAsArg[0] = new Var("o");
            Expression[] offerAAsArg = new Expression[1];
            offerAAsArg[0] = new Var("offerA");
            Expression[] offerBAsArg = new Expression[1];
            offerBAsArg[0] = new Var("offerB");

            Expression[] AMakeOfferArgs = new Expression[2];
            AMakeOfferArgs[0] = new ObjectMethod("read", "user", emptyArg, "u");
            AMakeOfferArgs[1] = new Var("o");
            Expression[] AMakeOfferArgs2 = new Expression[2];
            AMakeOfferArgs2[0] = new Var("u");
            AMakeOfferArgs2[1] = new Var("o");
            Expression[] BMakeOfferArgs = new Expression[2];
            BMakeOfferArgs[0] = new Var("u");
            BMakeOfferArgs[1] = new Var("offerA");

            Expression[] userUpdate1Args = new Expression[2];
            userUpdate1Args[0] = new ObjectMethod("makeOffer1", "A", AMakeOfferArgs, "seatInfoA");
            userUpdate1Args[1] = new ObjectMethod("makeOffer2", "A", AMakeOfferArgs2, "offerA");

            Expression[] userUpdate2Args = new Expression[2];
            userUpdate2Args[0] = new ObjectMethod("makeOffer1", "B", BMakeOfferArgs, "seatInfoB");
            userUpdate2Args[1] = new ObjectMethod("makeOffer2", "B", BMakeOfferArgs, "offerB");

            //todo: how to make the recursion automatically know it's method name
            // create a map for the entrance of this-method: auction -> m8 and then have another map for where the replacement is needed
            Expression auctionUseCase = new Sequence(new ObjectMethod("update", "user", userUpdate1Args)
                    , new Conditional(new Plus(new Var("o"),new Var("offerA")),
                    new ObjectMethod("declareWinner", "user", oAsArg),
                    new Sequence(new ObjectMethod("update", "user", userUpdate2Args),
                            new Conditional(new Plus(new Var("offerA"), new Var("offerB")),
                                    new ThisMethod("m8", offerBAsArg),
                                    new ObjectMethod("declareWinner", "user", offerAAsArg)))));
            return auctionUseCase;
        }

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
            decBalanceArg[0] = new Var("price");

            Expression[] updateInfoArgs = new Expression[2];
            updateInfoArgs[0] = new ObjectMethod("getPrice1", "airline", getPriceArg, "schedule");
            updateInfoArgs[1] = new ObjectMethod("getPrice2", "airline", getPriceArg1, "price");

            Expression[] updatePaymentArgs = new Expression[2];
            updatePaymentArgs[0] = new ObjectMethod("getBalance1", "bank", getBalanceArg, "cashback");
            updatePaymentArgs[1] = new ObjectMethod("getBalance2", "bank", getBalanceArg1, "balance");

            /*Expression ticketUseCase = new Sequence(new ObjectMethod("updateInfo", "customer", updateInfoArgs),
                    new Sequence(new ObjectMethod("updatePayment", "customer", updatePaymentArgs),
                            new Conditional(new Plus(new Var("price"), new Var("balance")),
                                    new Sequence(new ObjectMethod("decSeat", "airline", decSeatArg),
                                            new Sequence(new ObjectMethod("decBalance", "bank", decBalanceArg),
                                                    new IntLiteral(1))),
                                    new IntLiteral(0))));*/
            Expression ticketUseCase = new Sequence(new ObjectMethod("updateInfo", "customer", updateInfoArgs),
                    new Sequence(new ObjectMethod("updatePayment", "customer", updatePaymentArgs),
                            new Conditional(new Plus(new Var("price"), new Var("balance")),
                                    new Sequence(new ObjectMethod("decSeat", "airline", decSeatArg),
                                            new ObjectMethod("decBalance", "bank", decBalanceArg)),
                                    new IntLiteral(0))));
            return ticketUseCase;
        }

    public static Expression createTestUseCase(){
        Expression[] emptyArg = {};
        Expression[] oAsArg = new Expression[1];
        oAsArg[0] = new Var("o");
        Expression[] offerAAsArg = new Expression[1];
        offerAAsArg[0] = new Var("offerA");
        Expression[] offerBAsArg = new Expression[1];
        offerBAsArg[0] = new Var("offerB");

        Expression[] AMakeOfferArgs = new Expression[2];
        AMakeOfferArgs[0] = new ObjectMethod("read", "user", emptyArg, "u");
        AMakeOfferArgs[1] = new Var("o");
        Expression[] AMakeOfferArgs2 = new Expression[2];
        AMakeOfferArgs2[0] = new Var("u");
        AMakeOfferArgs2[1] = new Var("o");

        Expression[] userUpdate1Args = new Expression[2];
        userUpdate1Args[0] = new ObjectMethod("makeOffer1", "A", AMakeOfferArgs, "seatInfoA");
        userUpdate1Args[1] = new ObjectMethod("makeOffer2", "A", AMakeOfferArgs2, "offerA");

        Expression[] userUpdate2Args = new Expression[2];
        userUpdate2Args[0] = new ObjectMethod("makeOffer1", "B", AMakeOfferArgs2, "seatInfoB");
        userUpdate2Args[1] = new ObjectMethod("makeOffer2", "B", AMakeOfferArgs2, "offerB");

        Expression auctionUseCase = new Conditional(new Plus(new Var("offerA"), new Var("offerB")),
                                new ThisMethod("m0", offerBAsArg),
                                new ObjectMethod("declareWinner", "user", offerAAsArg));
        //Expression auctionUseCase = new ThisMethod("m0", offerBAsArg);
        return auctionUseCase;
    }

    public static void OneTimeTransferTypeCheckingP(ArrayList<MethodDefinition> resultMethodDefs){
        HashSet<Integer> A0 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        nodeSet A = new nodeSet(A0);
        HashSet<Integer> B0 = new HashSet<>(Arrays.asList(8, 9, 10, 11));
        nodeSet B = new nodeSet(B0);
        HashSet<Integer> C0 = new HashSet<>(Arrays.asList(12));
        nodeSet C = new nodeSet(C0);
        quorumDef P_2A = getPowerSet(A, 2);
        quorumDef P_1A = getPowerSet(A, 1);
        quorumDef P_1B = getPowerSet(B, 1);
        quorumDef SingletonA = new quorumDef();
        SingletonA.quorum.add(A);
        quorumDef SingletonB = new quorumDef();
        SingletonB.quorum.add(B);
        quorumDef SingletonC = new quorumDef();
        SingletonC.quorum.add(C);

        //failure situation
        quorumDef P2AP1B = P_2A.crossUnion(P_1B);
        quorumDef P1AP1B = P_1A.crossUnion(P_1B);
        quorumDef AP1B = SingletonA.crossUnion(P_1B);
        quorumDef BP2A = SingletonB.crossUnion(P_2A);
        quorumDef BP1A = SingletonB.crossUnion(P_1A);
        quorumDef AB = SingletonA.crossUnion(SingletonB);

        //confidentiality information for objects
        HashSet<Integer> c1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 12));
        HashSet<Integer> c2 = new HashSet<>(Arrays.asList(8, 9, 10, 11, 12));
        HashSet<Integer> cx = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        HashSet<Integer> cret = new HashSet<>(Arrays.asList(12));

        //communication quorum
        quorumDef P_3A = getPowerSet(A, 3);
        quorumDef P_2B = getPowerSet(B, 2);
        quorumDef P3AP2B = P_3A.crossUnion(P_2B);
        quorumDef P3AP2BU = P_3A.union(P_2B);

        //object replication quorum
        quorumDef P_5A = getPowerSet(A, 5);
        quorumDef P_3B = getPowerSet(B, 3);
        quorumDef P5AP3B = P_5A.crossUnion(P_3B);

        //context types
        CIAType t0 = new CIAType(new nodeSet(cx), AB, AB);

        //variable type
        CIAType t3 = new CIAType(new nodeSet(cx), P2AP1B, P1AP1B);
        CIAType t1 = new CIAType(new nodeSet(c1), BP2A, BP1A);
        CIAType t2 = new CIAType(new nodeSet(c2), AP1B, AP1B);
        CIAType t4 = new CIAType(new nodeSet(c1), P2AP1B, P1AP1B);
        CIAType t5 = new CIAType(new nodeSet(c2), P2AP1B, P1AP1B);
        CIAType t = new CIAType(new nodeSet(cret), P2AP1B, P1AP1B);

        //host and quorum information
        HashSet<Integer> Hret = new HashSet<>(Arrays.asList(12));
        HashSet<Integer> Hm1 = new HashSet<>(Arrays.asList(2, 3, 4, 5));
        HashSet<Integer> Hm2 = new HashSet<>(Arrays.asList(9, 10, 11));
        HashSet<Integer> Hm3 = new HashSet<>(Arrays.asList(2, 3, 4, 5, 9, 10, 11));
        HashSet<Integer> Hm4 = new HashSet<>(Arrays.asList(2, 3, 4, 5, 9, 10, 11));

        //input the methods host information and signature manually
        ArrayList<Pair<nodeSet, quorumDef>> methodsInfo = new ArrayList<>();
        Pair<nodeSet, quorumDef> retInfo = new Pair<>(new nodeSet(Hret), SingletonC.union(P_3A).union(P_2B));
        methodsInfo.add(retInfo);
        Pair<nodeSet, quorumDef> m1Info = new Pair<>(new nodeSet(Hm1), P3AP2BU);
        methodsInfo.add(m1Info);
        Pair<nodeSet, quorumDef> m2Info = new Pair<>(new nodeSet(Hm2), P3AP2BU);
        methodsInfo.add(m2Info);
        Pair<nodeSet, quorumDef> m3Info = new Pair<>(new nodeSet(Hm3), P3AP2B);
        methodsInfo.add(m3Info);
        Pair<nodeSet, quorumDef> m4Info = new Pair<>(new nodeSet(Hm4), SingletonC);
        methodsInfo.add(m4Info);

        ArrayList<ArrayList<String>> mANames = new ArrayList<>(resultMethodDefs.size());
        for(int i = 0; i < resultMethodDefs.size(); i++){
            mANames.add(new ArrayList<>());
        }
        ArrayList<Pair<ArrayList<CIAType>, ArrayList<CIAType>>> methodSig = new ArrayList<>();
        CIAType retType = t;
        CIAType m1retType = new CIAType(new nodeSet(cret), P2AP1B, P1AP1B);
        CIAType m2retType = m1retType.clone();
        CIAType m3retType = m1retType.clone();
        CIAType m4retType = m1retType.clone();

        //context type for m4, which is the bottom
        CIAType m4Context = t0;
        CIAType m3Context = m4Context.ciaJoin(t3);
        CIAType m2Context = m3Context.clone();
        CIAType m1Context = m3Context.clone();
        CIAType retContext = t;

        ArrayList<CIAType> mret = new ArrayList<>();
        mret.add(retContext);
        mret.add(retType);
        methodSig.add(new Pair<>(mret, new ArrayList<>()));
        mANames.get(0).add("bot");
        methodSig.get(0).element2.add(t);
        ArrayList<CIAType> m1 = new ArrayList<>();
        m1.add(m1Context);
        m1.add(m1retType);
        methodSig.add(new Pair<>(m1, new ArrayList<>()));
        mANames.get(1).add("bot");
        methodSig.get(1).element2.add(t3);
        ArrayList<CIAType> m2 = new ArrayList<>();
        m2.add(m2Context);
        m2.add(m2retType);
        methodSig.add(new Pair<>(m2, new ArrayList<>()));
        mANames.get(2).add("bot");
        methodSig.get(2).element2.add(t3);
        ArrayList<CIAType> m3 = new ArrayList<>();
        m3.add(m3Context);
        m3.add(m3retType);
        methodSig.add(new Pair<>(m3, new ArrayList<>()));
        mANames.get(3).add("x");
        methodSig.get(3).element2.add(t3);
        ArrayList<CIAType> m4 = new ArrayList<>();
        m4.add(m4Context);
        m4.add(m4retType);
        methodSig.add(new Pair<>(m4, new ArrayList<>()));
        mANames.get(4).add("x");
        methodSig.get(4).element2.add(t3);

        //input the object host information and signature manually
        HashMap<String, HashMap<String, ArrayList<CIAType>>> objSigs = new HashMap<>();
        //for object a
        ArrayList<CIAType> awriteArg = new ArrayList<>();
        awriteArg.add(t3);
        //ret is in the last index
        awriteArg.add(t3);
        HashMap<String, ArrayList<CIAType>> amethods = new HashMap<>();
        amethods.put("write", awriteArg);
        ArrayList<CIAType> aread = new ArrayList<>();
        aread.add(t3);
        aread.add(t3);
        amethods.put("read", aread);
        objSigs.put("a", amethods);
        //for object i1 and i2
        HashMap<String, ArrayList<CIAType>> i1methods = new HashMap<>();
        ArrayList<CIAType> i1read = new ArrayList<>();
        //ret
        i1read.add(t3);
        //i1read.add(new CIAType(new nodeSet(c1), new quorumDef(Bi1), new quorumDef(Bi1)));
        i1read.add(t4);
        i1methods.put("read", i1read);
        objSigs.put("i1", i1methods);
        HashMap<String, ArrayList<CIAType>> i2methods = new HashMap<>();
        ArrayList<CIAType> i2read = new ArrayList<>();
        i2read.add(t3);
        //ret
        //i2read.add(new CIAType(new nodeSet(c2), new quorumDef(Bi2), new quorumDef(Bi2)));
        i2read.add(t5);
        i2methods.put("read", i2read);
        objSigs.put("i2", i2methods);

        //the objects hosts and hear from information
        HashMap<String, Pair<quorumDef, quorumDef>> objInfo = new HashMap<>();
        Pair<quorumDef, quorumDef> i1Info = new Pair<>(P_5A, P_3A);
        objInfo.put("i1", i1Info);
        Pair<quorumDef, quorumDef> i2Info = new Pair<>(P_3B, P_2B);
        objInfo.put("i2", i2Info);
        Pair<quorumDef, quorumDef> aInfo = new Pair<>(P5AP3B, P3AP2B);
        objInfo.put("a", aInfo);

        //input the predefined variable information
        HashMap<String, CIAType> p = new HashMap<>();
        p.put("x", t3);
        //input predefine umbrella for the objects
        HashMap<String, CIAType> u = new HashMap<>();
        u.put("i1", t1);
        u.put("i2", t2);
        u.put("a", t3);
        //printToFile(lambda1., "oft_cps");

        SecureTypeChecking test5 = new SecureTypeChecking();
        Boolean r = test5.classTypeCheck(resultMethodDefs, methodsInfo, methodSig, mANames, objSigs, objInfo, p, u, t0);
        System.out.println("The type checking result for one time transfer program:" + r.toString());
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

        //context integrity and availability for methods
        HashSet<Integer> b0 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        HashSet<nodeSet> B0 = new HashSet<>();
        B0.add(new nodeSet(b0));

        //confidentiality information for objects
        HashSet<Integer> c1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 12));
        HashSet<Integer> c2 = new HashSet<>(Arrays.asList(8, 9, 10, 11, 12));
        HashSet<Integer> cx = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        HashSet<Integer> cret = new HashSet<>(Arrays.asList(12));

        //context types
        CIAType t0 = new CIAType(new nodeSet(cx), new quorumDef(B0), new quorumDef(B0));

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
        //CIAType m4Context = new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B));
        //CIAType m3Context = m4Context.ciaJoin(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        //CIAType m2Context = m3Context.clone();
        //CIAType m1Context = m3Context.clone();
        CIAType m4Context = t0;
        CIAType m3Context = m4Context.ciaJoin(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        CIAType m2Context = m3Context.clone();
        CIAType m1Context = m3Context.clone();
        ArrayList<CIAType> m1 = new ArrayList<>();
        m1.add(m1Context);
        m1.add(m1retType);
        methodSig.add(new Pair<>(m1, new ArrayList<>()));
        mANames.get(0).add("bot");
        methodSig.get(0).element2.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        ArrayList<CIAType> m2 = new ArrayList<>();
        m2.add(m2Context);
        m2.add(m2retType);
        methodSig.add(new Pair<>(m2, new ArrayList<>()));
        mANames.get(1).add("bot");
        methodSig.get(1).element2.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
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
        aread.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        amethods.put("read", aread);
        objSigs.put("a", amethods);
        //for object i1 and i2
        HashMap<String, ArrayList<CIAType>> i1methods = new HashMap<>();
        ArrayList<CIAType> i1read = new ArrayList<>();
        //ret
        i1read.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        //i1read.add(new CIAType(new nodeSet(c1), new quorumDef(Bi1), new quorumDef(Bi1)));
        i1read.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        i1methods.put("read", i1read);
        objSigs.put("i1", i1methods);
        HashMap<String, ArrayList<CIAType>> i2methods = new HashMap<>();
        ArrayList<CIAType> i2read = new ArrayList<>();
        i2read.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
        //ret
        //i2read.add(new CIAType(new nodeSet(c2), new quorumDef(Bi2), new quorumDef(Bi2)));
        i2read.add(new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));
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
        Boolean r = test5.classTypeCheck(resultMethodDefs, methodsInfo, methodSig, mANames, objSigs, objInfo, p, u, t0);
        System.out.println("The type checking result for one time transfer program:" + r.toString());
    }

    public static void ObliviousTransferTypeCheckingP(ArrayList<MethodDefinition> resultMethodDefs){
        HashSet<Integer> A0 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        nodeSet A = new nodeSet(A0);
        HashSet<Integer> B0 = new HashSet<>(Arrays.asList(8, 9, 10, 11));
        nodeSet B = new nodeSet(B0);
        HashSet<Integer> C0 = new HashSet<>(Arrays.asList(12, 13, 14, 15));
        nodeSet C = new nodeSet(C0);
        quorumDef P_2A = getPowerSet(A, 2);
        quorumDef P_1B = getPowerSet(B, 1);
        quorumDef P_1C = getPowerSet(C, 1);
        quorumDef SingletonA = new quorumDef();
        SingletonA.quorum.add(A);
        quorumDef SingletonB = new quorumDef();
        SingletonB.quorum.add(B);
        quorumDef SingletonC = new quorumDef();
        SingletonC.quorum.add(C);

        //failure situation
        quorumDef P2AP1BP1C = P_2A.crossUnion(P_1B).crossUnion(P_1C);
        quorumDef P1BAC = SingletonA.crossUnion(P_1B).crossUnion(SingletonC);
        quorumDef P2ABC = SingletonB.crossUnion(P_2A).crossUnion(SingletonC);
        quorumDef P2AP1BC = P_2A.crossUnion(P_1B).crossUnion(SingletonC);
        quorumDef P2ABP1C = P_2A.crossUnion(SingletonB).crossUnion(P_1C);
        quorumDef AP1BP1C = SingletonA.crossUnion(P_1B).crossUnion(P_1C);
        quorumDef ABP1C = SingletonA.crossUnion(SingletonB).crossUnion(P_1C);

        //confidentiality information for objects
        HashSet<Integer> c1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 12, 13, 14, 15));
        HashSet<Integer> c2 = new HashSet<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15));
        HashSet<Integer> cx = new HashSet<>(Arrays.asList(12, 13, 14, 15));
        HashSet<Integer> c0 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));

        //communication quorum
        quorumDef P_2C = getPowerSet(C, 2);

        //object replication quorum
        quorumDef P_5A = getPowerSet(A, 5);
        quorumDef P_3B = getPowerSet(B, 3);
        quorumDef P5AP3B = P_5A.crossUnion(P_3B);

        //context types
        CIAType t0 = new CIAType(new nodeSet(c0), ABP1C, ABP1C);

        //variable type
        CIAType t1 = new CIAType(new nodeSet(c1), P2ABC, P2ABC);
        CIAType t2 = new CIAType(new nodeSet(c2), P1BAC, P1BAC);
        CIAType t3 = new CIAType(new nodeSet(c0), P2AP1BC, P2AP1BC);
        CIAType t4 = new CIAType(new nodeSet(c1), P2AP1BP1C, P2AP1BP1C);
        CIAType t5 = new CIAType(new nodeSet(c2), P2AP1BP1C, P2AP1BP1C);
        CIAType t6 = new CIAType(new nodeSet(c0), P2AP1BP1C, P2AP1BP1C);
        CIAType t7 = new CIAType(new nodeSet(cx), ABP1C, ABP1C);
        CIAType t8 = new CIAType(new nodeSet(cx), P2AP1BP1C, P2AP1BP1C);

        //host and quorum information
        HashSet<Integer> Hret = new HashSet<>(Arrays.asList(12, 13, 14, 15));
        HashSet<Integer> Hm1 = new HashSet<>(Arrays.asList(12, 13, 14));
        HashSet<Integer> Hm2 = Hm1;
        HashSet<Integer> Hm3 = Hm1;
        HashSet<Integer> Hm4 = Hm1;

        //input the methods host information and signature manually
        ArrayList<Pair<nodeSet, quorumDef>> methodsInfo = new ArrayList<>();
        Pair<nodeSet, quorumDef> retInfo = new Pair<>(new nodeSet(Hret), P_2C);
        methodsInfo.add(retInfo);
        Pair<nodeSet, quorumDef> m1Info = new Pair<>(new nodeSet(Hm1), P_2C);
        methodsInfo.add(m1Info);
        Pair<nodeSet, quorumDef> m2Info = new Pair<>(new nodeSet(Hm2), P_2C);
        methodsInfo.add(m2Info);
        Pair<nodeSet, quorumDef> m3Info = new Pair<>(new nodeSet(Hm3), P_2C);
        methodsInfo.add(m3Info);
        Pair<nodeSet, quorumDef> m4Info = new Pair<>(new nodeSet(Hm4), P_2C);
        methodsInfo.add(m4Info);

        ArrayList<ArrayList<String>> mANames = new ArrayList<>(resultMethodDefs.size());
        for(int i = 0; i < resultMethodDefs.size(); i++){
            mANames.add(new ArrayList<>());
        }
        ArrayList<Pair<ArrayList<CIAType>, ArrayList<CIAType>>> methodSig = new ArrayList<>();
        CIAType m1retType = new CIAType(new nodeSet(cx), P2AP1BP1C, P2AP1BP1C);
        CIAType m2retType = m1retType.clone();
        CIAType m3retType = m1retType.clone();
        CIAType m4retType = m1retType.clone();
        CIAType retType = t8;

        //context type for m4, which is the bottom
        CIAType m4Context = t0;
        CIAType m3Context = t6;
        CIAType m2Context = t6;
        CIAType m1Context = t6;
        CIAType retContext = t8;

        ArrayList<CIAType> mret = new ArrayList<>();
        mret.add(retContext);
        mret.add(retType);
        methodSig.add(new Pair<>(mret, new ArrayList<>()));
        mANames.get(0).add("bot");
        methodSig.get(0).element2.add(t8);

        ArrayList<CIAType> m1 = new ArrayList<>();
        m1.add(m1Context);
        m1.add(m1retType);
        methodSig.add(new Pair<>(m1, new ArrayList<>()));
        mANames.get(1).add("x");
        methodSig.get(1).element2.add(t8);
        mANames.get(1).add("temp1");
        methodSig.get(1).element2.add(t4);

        ArrayList<CIAType> m2 = new ArrayList<>();
        m2.add(m2Context);
        m2.add(m2retType);
        methodSig.add(new Pair<>(m2, new ArrayList<>()));
        mANames.get(2).add("x");
        methodSig.get(2).element2.add(t8);

        ArrayList<CIAType> m3 = new ArrayList<>();
        m3.add(m3Context);
        m3.add(m3retType);
        methodSig.add(new Pair<>(m3, new ArrayList<>()));
        mANames.get(3).add("x");
        methodSig.get(3).element2.add(t8);

        ArrayList<CIAType> m4 = new ArrayList<>();
        m4.add(m4Context);
        m4.add(m4retType);
        methodSig.add(new Pair<>(m4, new ArrayList<>()));
        mANames.get(4).add("x");
        methodSig.get(4).element2.add(t7);

        //input the object host information and signature manually
        HashMap<String, HashMap<String, ArrayList<CIAType>>> objSigs = new HashMap<>();
        //for object a
        ArrayList<CIAType> awriteArg = new ArrayList<>();
        awriteArg.add(t6);
        //ret is in the last index
        awriteArg.add(t6);
        HashMap<String, ArrayList<CIAType>> amethods = new HashMap<>();
        amethods.put("write", awriteArg);
        ArrayList<CIAType> aread = new ArrayList<>();
        aread.add(t0);
        aread.add(t6);
        amethods.put("read", aread);
        objSigs.put("a", amethods);

        //for object i1 and i2
        HashMap<String, ArrayList<CIAType>> i1methods = new HashMap<>();
        ArrayList<CIAType> i1read = new ArrayList<>();
        //ret
        i1read.add(t6);
        //i1read.add(new CIAType(new nodeSet(c1), new quorumDef(Bi1), new quorumDef(Bi1)));
        i1read.add(t4);
        i1methods.put("read", i1read);
        objSigs.put("i1", i1methods);
        HashMap<String, ArrayList<CIAType>> i2methods = new HashMap<>();
        ArrayList<CIAType> i2read = new ArrayList<>();
        i2read.add(t6);
        //ret
        //i2read.add(new CIAType(new nodeSet(c2), new quorumDef(Bi2), new quorumDef(Bi2)));
        i2read.add(t5);
        i2methods.put("read", i2read);
        objSigs.put("i2", i2methods);

        //the objects hosts and hear from information
        HashMap<String, Pair<quorumDef, quorumDef>> objInfo = new HashMap<>();
        Pair<quorumDef, quorumDef> i1Info = new Pair<>(P_5A, P_2C);
        objInfo.put("i1", i1Info);
        Pair<quorumDef, quorumDef> i2Info = new Pair<>(P_3B, P_2C);
        objInfo.put("i2", i2Info);
        Pair<quorumDef, quorumDef> aInfo = new Pair<>(P5AP3B, P_2C);
        objInfo.put("a", aInfo);

        //input the predefined variable information
        HashMap<String, CIAType> p = new HashMap<>();
        p.put("x", t7);
        //input predefine umbrella for the objects
        HashMap<String, CIAType> u = new HashMap<>();
        u.put("i1", t1);
        u.put("i2", t2);
        u.put("a", t3);

        SecureTypeChecking test5 = new SecureTypeChecking();
        Boolean r = test5.classTypeCheck(resultMethodDefs, methodsInfo, methodSig, mANames, objSigs, objInfo, p, u, t0);
        System.out.println("The type checking result for oblivious transfer program:" + r.toString());
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
        HashSet<Integer> c3 = new HashSet<>(Arrays.asList(12));

        //construct the types
        CIAType t0 = new CIAType(new nodeSet(cret), new quorumDef(B3), new quorumDef(B3));
        CIAType tp = new CIAType(new nodeSet(cret), new quorumDef(B1), new quorumDef(B1));
        CIAType ts = new CIAType(new nodeSet(c1), new quorumDef(B1), new quorumDef(B1));
        CIAType td = new CIAType(new nodeSet(cret), new quorumDef(B0), new quorumDef(B0));
        CIAType tc = new CIAType(new nodeSet(c2), new quorumDef(B0), new quorumDef(B0));
        CIAType tnum1 = new CIAType(new nodeSet(cret), new quorumDef(B3), new quorumDef(B1));
        CIAType tu1 = new CIAType(new nodeSet(c1), new quorumDef(B1), new quorumDef(B1));
        CIAType tu2 = new CIAType(new nodeSet(c2), new quorumDef(B0), new quorumDef(B0));

        //define \bot integrity and availability
        HashSet<Integer> b = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        HashSet<nodeSet> bot = new HashSet<>();
        bot.add(new nodeSet(b));

        //host and quorum information
        HashSet<Integer> Hm9 = new HashSet<>(Arrays.asList(12));
        HashSet<Integer> Hm8 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        HashSet<Integer> Hm7 = Hm8;
        HashSet<Integer> Hm6 = new HashSet<>(Arrays.asList(12));
        HashSet<Integer> Hm5 = new HashSet<>(Arrays.asList(12));
        HashSet<Integer> Hm4 = new HashSet<>(Arrays.asList(8, 9, 10));
        HashSet<Integer> Hm3 = Hm4;
        HashSet<Integer> Hm2 = new HashSet<>(Arrays.asList(12));
        HashSet<Integer> Hm1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        HashSet<Integer> Hm0 = new HashSet<>(Arrays.asList(8, 9, 10));

        HashSet<nodeSet> Qm9 = new HashSet<>();
        HashSet<Integer> Qm9_1 = new HashSet<>(Arrays.asList(12));
        Qm9.add(new nodeSet(Qm9_1));
        HashSet<nodeSet> Qm8 = Qm9;
        HashSet<nodeSet> Qm7 = new HashSet<>();
        HashSet<Integer> Qm7_1 = new HashSet<>(Arrays.asList(1, 2, 3));
        HashSet<Integer> Qm7_2 = new HashSet<>(Arrays.asList(1, 2, 4));
        HashSet<Integer> Qm7_3 = new HashSet<>(Arrays.asList(1, 2, 5));
        HashSet<Integer> Qm7_4 = new HashSet<>(Arrays.asList(1, 3, 4));
        HashSet<Integer> Qm7_5 = new HashSet<>(Arrays.asList(1, 3, 5));
        HashSet<Integer> Qm7_6 = new HashSet<>(Arrays.asList(1, 4, 5));
        HashSet<Integer> Qm7_7 = new HashSet<>(Arrays.asList(2, 3, 4));
        HashSet<Integer> Qm7_8 = new HashSet<>(Arrays.asList(2, 3, 5));
        HashSet<Integer> Qm7_9 = new HashSet<>(Arrays.asList(3, 4, 5));
        HashSet<Integer> Qm7_10 = new HashSet<>(Arrays.asList(2, 4, 5));
        Qm7.add(new nodeSet(Qm7_1));
        Qm7.add(new nodeSet(Qm7_2));
        Qm7.add(new nodeSet(Qm7_3));
        Qm7.add(new nodeSet(Qm7_4));
        Qm7.add(new nodeSet(Qm7_5));
        Qm7.add(new nodeSet(Qm7_6));
        Qm7.add(new nodeSet(Qm7_7));
        Qm7.add(new nodeSet(Qm7_8));
        Qm7.add(new nodeSet(Qm7_9));
        Qm7.add(new nodeSet(Qm7_10));
        HashSet<nodeSet> Qm6 = Qm7;
        HashSet<nodeSet> Qm5 = Qm9;
        HashSet<nodeSet> Qm4 = Qm9;
        HashSet<nodeSet> Qm3 = new HashSet<>();
        HashSet<Integer> Qm3_1 = new HashSet<>(Arrays.asList(8, 9));
        HashSet<Integer> Qm3_2 = new HashSet<>(Arrays.asList(8, 10));
        HashSet<Integer> Qm3_3 = new HashSet<>(Arrays.asList(9, 10));
        Qm3.add(new nodeSet(Qm3_1));
        Qm3.add(new nodeSet(Qm3_2));
        Qm3.add(new nodeSet(Qm3_3));
        HashSet<nodeSet> Qm2 = Qm3;
        HashSet<nodeSet> Qm1 = Qm9;
        HashSet<nodeSet> Qm0 = Qm7;

        //object replication information
        HashSet<nodeSet> Qairline = new HashSet<>();
        HashSet<Integer> Qi1_1 = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
        HashSet<Integer> Qi1_2 = new HashSet<>(Arrays.asList(2, 4, 5, 6, 7));
        Qairline.add(new nodeSet(Qi1_1));
        Qairline.add(new nodeSet(Qi1_2));
        HashSet<nodeSet> Qbank = new HashSet<>();
        HashSet<Integer> Qi2_1 = new HashSet<>(Arrays.asList(8, 10, 11));
        HashSet<Integer> Qi2_2 = new HashSet<>(Arrays.asList(9, 10, 11));
        HashSet<Integer> Qi2_3 = new HashSet<>(Arrays.asList(8, 9, 10));
        Qbank.add(new nodeSet(Qi2_1));
        Qbank.add(new nodeSet(Qi2_2));
        Qbank.add(new nodeSet(Qi2_3));
        HashSet<nodeSet> Qcustomer = new HashSet<>();
        HashSet<Integer> Qa_1 = new HashSet<>(Arrays.asList(12));
        Qcustomer.add(new nodeSet(Qa_1));


        HashSet<nodeSet> Q2_airline = new HashSet<>();
        HashSet<Integer> Q2_i1_1 = new HashSet<>(Arrays.asList(2, 4, 5));
        Q2_airline.add(new nodeSet(Q2_i1_1));
        //HashSet<Integer> Q2_i1_2 = new HashSet<>(Arrays.asList(12));
        //Q2_airline.add(new nodeSet(Q2_i1_2));
        HashSet<Integer> Q2_i1_3 = new HashSet<>(Arrays.asList(3, 4, 5));
        Q2_airline.add(new nodeSet(Q2_i1_3));

        HashSet<nodeSet> Q2_bank = new HashSet<>();
        HashSet<Integer> Q2_i2_1 = new HashSet<>(Arrays.asList(9, 10));
        Q2_bank.add(new nodeSet(Q2_i2_1));
        //HashSet<Integer> Q2_i2_2 = new HashSet<>(Arrays.asList(12));
        //Q2_bank.add(new nodeSet(Q2_i2_2));
        HashSet<Integer> Q2_i2_3 = new HashSet<>(Arrays.asList(8, 10));
        Q2_bank.add(new nodeSet(Q2_i2_3));

        HashSet<nodeSet> Q2_customer = new HashSet<>();
        HashSet<Integer> Q2_a_1 = new HashSet<>(Arrays.asList(12));
        Q2_customer.add(new nodeSet(Q2_a_1));
        //HashSet<Integer> Q2_a_2 = new HashSet<>(Arrays.asList(2, 4, 5));
        //Q2_customer.add(new nodeSet(Q2_a_2));
        //HashSet<Integer> Q2_a_3 = new HashSet<>(Arrays.asList(9, 10));
        //Q2_customer.add(new nodeSet(Q2_a_3));
        //HashSet<Integer> Q2_a_4 = new HashSet<>(Arrays.asList(3, 4, 5));
        //Q2_customer.add(new nodeSet(Q2_a_4));
        //HashSet<Integer> Q2_a_5 = new HashSet<>(Arrays.asList(8, 10));
        //Q2_customer.add(new nodeSet(Q2_a_5));

        //input the methods host information and signature manually
        ArrayList<Pair<nodeSet, quorumDef>> methodsInfo = new ArrayList<>();
        Pair<nodeSet, quorumDef> m0Info = new Pair<>(new nodeSet(Hm0), new quorumDef(Qm0));
        methodsInfo.add(m0Info);
        Pair<nodeSet, quorumDef> m1Info = new Pair<>(new nodeSet(Hm1), new quorumDef(Qm1));
        methodsInfo.add(m1Info);
        Pair<nodeSet, quorumDef> m2Info = new Pair<>(new nodeSet(Hm2), new quorumDef(Qm2));
        methodsInfo.add(m2Info);
        Pair<nodeSet, quorumDef> m3Info = new Pair<>(new nodeSet(Hm3), new quorumDef(Qm3));
        methodsInfo.add(m3Info);
        Pair<nodeSet, quorumDef> m4Info = new Pair<>(new nodeSet(Hm4), new quorumDef(Qm4));
        methodsInfo.add(m4Info);
        Pair<nodeSet, quorumDef> m5Info = new Pair<>(new nodeSet(Hm5), new quorumDef(Qm5));
        methodsInfo.add(m5Info);
        Pair<nodeSet, quorumDef> m6Info = new Pair<>(new nodeSet(Hm6), new quorumDef(Qm6));
        methodsInfo.add(m6Info);
        Pair<nodeSet, quorumDef> m7Info = new Pair<>(new nodeSet(Hm7), new quorumDef(Qm7));
        methodsInfo.add(m7Info);
        Pair<nodeSet, quorumDef> m8Info = new Pair<>(new nodeSet(Hm8), new quorumDef(Qm8));
        methodsInfo.add(m8Info);
        Pair<nodeSet, quorumDef> m9Info = new Pair<>(new nodeSet(Hm9), new quorumDef(Qm9));
        methodsInfo.add(m9Info);

        //input the return type for all the methods
        //input the arguments for all the methods
        ArrayList<ArrayList<String>> mANames = new ArrayList<>(10);
        for(int i = 0; i < 10; i++){
            mANames.add(new ArrayList<>());
        }
        //notice the the type for a method maybe stronger than the return type of the whole class because of sequence.
        ArrayList<Pair<ArrayList<CIAType>, ArrayList<CIAType>>> methodSig = new ArrayList<>();
        CIAType m0retType = new CIAType(new nodeSet(cret), new quorumDef(B0), new quorumDef(B0));
        CIAType m1retType = m0retType;
        CIAType m2retType = new CIAType(new nodeSet(c2), new quorumDef(B0), new quorumDef(B0));
        CIAType m3retType = m2retType;
        CIAType m4retType = m2retType;
        CIAType m5retType = m2retType;
        CIAType m6retType = new CIAType(new nodeSet(c3), new quorumDef(B0), new quorumDef(B0));
        CIAType m7retType = m6retType;
        CIAType m8retType = m6retType;
        CIAType m9retType = m6retType;

        CIAType m9Context = t0;
        CIAType m8Context = t0;
        CIAType m7Context = tnum1;
        CIAType m6Context = tnum1;
        CIAType m5Context = tnum1;
        CIAType m4Context = tnum1;
        CIAType m3Context = td;
        CIAType m2Context = td;
        CIAType m1Context = td;
        CIAType m0Context = td;

        //the order of the arguments matters, they are defined in mANames
        ArrayList<CIAType> m0 = new ArrayList<>();
        m0.add(m0Context);
        m0.add(m0retType);
        methodSig.add(new Pair<>(m0, new ArrayList<>()));
        mANames.get(0).add("price");
        methodSig.get(0).element2.add(td);

        ArrayList<CIAType> m1 = new ArrayList<>();
        m1.add(m1Context);
        m1.add(m1retType);
        methodSig.add(new Pair<>(m1, new ArrayList<>()));
        mANames.get(1).add("price");
        mANames.get(1).add("num");
        methodSig.get(1).element2.add(td);
        methodSig.get(1).element2.add(td);

        ArrayList<CIAType> m2 = new ArrayList<>();
        m2.add(m2Context);
        m2.add(m2retType);
        methodSig.add(new Pair<>(m2, new ArrayList<>()));
        mANames.get(2).add("balance");
        mANames.get(2).add("price");
        mANames.get(2).add("num");
        mANames.get(2).add("cashback");
        methodSig.get(2).element2.add(td);
        methodSig.get(2).element2.add(td);
        methodSig.get(2).element2.add(td);
        methodSig.get(2).element2.add(tc);

        //this is the first split of two methods
        ArrayList<CIAType> m3 = new ArrayList<>();
        m3.add(m3Context);
        m3.add(m3retType);
        methodSig.add(new Pair<>(m3, new ArrayList<>()));
        mANames.get(3).add("price");
        mANames.get(3).add("num");
        mANames.get(3).add("ID");
        mANames.get(3).add("cashback");
        methodSig.get(3).element2.add(td);
        methodSig.get(3).element2.add(td);
        methodSig.get(3).element2.add(td);
        methodSig.get(3).element2.add(tc);

        ArrayList<CIAType> m4 = new ArrayList<>();
        m4.add(m4Context);
        m4.add(m4retType);
        methodSig.add(new Pair<>(m4, new ArrayList<>()));
        mANames.get(4).add("price");
        mANames.get(4).add("num");
        mANames.get(4).add("ID");
        methodSig.get(4).element2.add(tp);
        methodSig.get(4).element2.add(tp);
        methodSig.get(4).element2.add(tp);

        ArrayList<CIAType> m5 = new ArrayList<>();
        m5.add(m5Context);
        m5.add(m5retType);
        methodSig.add(new Pair<>(m5, new ArrayList<>()));
        mANames.get(5).add("price");
        mANames.get(5).add("num");
        methodSig.get(5).element2.add(tp);
        methodSig.get(5).element2.add(tp);

        ArrayList<CIAType> m6 = new ArrayList<>();
        m6.add(m6Context);
        m6.add(m6retType);
        methodSig.add(new Pair<>(m6, new ArrayList<>()));
        mANames.get(6).add("schedule");
        mANames.get(6).add("price");
        mANames.get(6).add("num");
        methodSig.get(6).element2.add(ts);
        methodSig.get(6).element2.add(tp);
        methodSig.get(6).element2.add(tp);

        ArrayList<CIAType> m7 = new ArrayList<>();
        m7.add(m7Context);
        m7.add(m7retType);
        methodSig.add(new Pair<>(m7, new ArrayList<>()));
        mANames.get(7).add("schedule");
        mANames.get(7).add("num");
        methodSig.get(7).element2.add(ts);
        methodSig.get(7).element2.add(tp);

        ArrayList<CIAType> m8 = new ArrayList<>();
        m8.add(m8Context);
        m8.add(m8retType);
        methodSig.add(new Pair<>(m8, new ArrayList<>()));
        mANames.get(8).add("num");
        methodSig.get(8).element2.add(t0);

        ArrayList<CIAType> m9 = new ArrayList<>();
        m9.add(m9Context);
        m9.add(m9retType);
        methodSig.add(new Pair<>(m9, new ArrayList<>()));
        mANames.get(9).add("bot");
        methodSig.get(9).element2.add(t0);


        //input the object host information and signature manually
        HashMap<String, HashMap<String, ArrayList<CIAType>>> objSigs = new HashMap<>();
        //for object airline
        ArrayList<CIAType> getPrice1Arg = new ArrayList<>();
        getPrice1Arg.add(tp);
        //ret is in the last index
        getPrice1Arg.add(ts);
        HashMap<String, ArrayList<CIAType>> amethods = new HashMap<>();
        amethods.put("getPrice1", getPrice1Arg);
        ArrayList<CIAType> getPrice2Arg = new ArrayList<>();
        getPrice2Arg.add(tp);
        getPrice2Arg.add(tp);
        amethods.put("getPrice2", getPrice2Arg);
        ArrayList<CIAType> decSeatArg = new ArrayList<>();
        decSeatArg.add(td);
        decSeatArg.add(td);
        amethods.put("decSeat", decSeatArg);
        objSigs.put("airline", amethods);

        //for object Bank
        ArrayList<CIAType> getBalance1Arg = new ArrayList<>();
        getBalance1Arg.add(td);
        //ret is in the last index
        getBalance1Arg.add(tc);
        HashMap<String, ArrayList<CIAType>> bmethods = new HashMap<>();
        bmethods.put("getBalance1", getBalance1Arg);
        ArrayList<CIAType> getBalance2Arg = new ArrayList<>();
        getBalance2Arg.add(td);
        getBalance2Arg.add(td);
        bmethods.put("getBalance2", getBalance2Arg);
        ArrayList<CIAType> decBalanceArg = new ArrayList<>();
        decBalanceArg.add(td);
        decBalanceArg.add(td);
        bmethods.put("decBalance", decBalanceArg);
        objSigs.put("bank", bmethods);

        //for object customer
        ArrayList<CIAType> ticketNumArg = new ArrayList<>();
        ticketNumArg.add(t0);
        //ret is in the last index
        ticketNumArg.add(t0);
        HashMap<String, ArrayList<CIAType>> cmethods = new HashMap<>();
        cmethods.put("ticketNum", ticketNumArg);
        ArrayList<CIAType> getIDArg = new ArrayList<>();
        getIDArg.add(tnum1);
        getIDArg.add(tnum1);
        cmethods.put("getID", getIDArg);
        ArrayList<CIAType> updateInfoArg = new ArrayList<>();
        updateInfoArg.add(ts);
        updateInfoArg.add(tp);
        updateInfoArg.add(tu1);
        cmethods.put("updateInfo", updateInfoArg);
        ArrayList<CIAType> updatePaymentArg = new ArrayList<>();
        updatePaymentArg.add(tc);
        updatePaymentArg.add(td);
        updatePaymentArg.add(tu2);
        cmethods.put("updatePayment", updatePaymentArg);
        objSigs.put("customer", cmethods);

        //the objects hosts and hear from information
        HashMap<String, Pair<quorumDef, quorumDef>> objInfo = new HashMap<>();
        Pair<quorumDef, quorumDef> airlineInfo = new Pair<>(new quorumDef(Qairline),new quorumDef(Q2_airline));
        objInfo.put("airline", airlineInfo);
        Pair<quorumDef, quorumDef> bankInfo = new Pair<>(new quorumDef(Qbank), new quorumDef(Q2_bank));
        objInfo.put("bank", bankInfo);
        Pair<quorumDef, quorumDef> customerInfo = new Pair<>(new quorumDef(Qcustomer), new quorumDef(Q2_customer));
        objInfo.put("customer", customerInfo);

        //input the predefined variable information
        HashMap<String, CIAType> p = new HashMap<>();
        //p.put("x", new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));

        //input predefine umbrella for the objects
        //todo: we have to input tight umbrella type for the objects.
        // If there is no requirement, we can infer one
        HashMap<String, CIAType> u = new HashMap<>();
        u.put("airline", t0);
        u.put("bank", tnum1);
        u.put("customer", t0);

        SecureTypeChecking test5 = new SecureTypeChecking();
        Boolean r = test5.classTypeCheck(resultMethodDefs, methodsInfo, methodSig, mANames, objSigs, objInfo, p, u, t0);
        System.out.println("The type checking result for ticket system program:" + r.toString());
    }

    public static void TicketTypeCheckingP(ArrayList<MethodDefinition> resultMethodDefs){
        //node sets
        HashSet<Integer> A0 = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        HashSet<Integer> B0 = new HashSet<Integer>(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17));
        HashSet<Integer> C0 = new HashSet<Integer>(Arrays.asList(18));
        nodeSet A = new nodeSet(A0);
        nodeSet B = new nodeSet(B0);
        nodeSet C = new nodeSet(C0);

        //confidentiality information for objects
        HashSet<Integer> c1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 18));
        HashSet<Integer> c2 = new HashSet<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18));
        HashSet<Integer> cret = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18));
        HashSet<Integer> c3 = new HashSet<>(Arrays.asList(18));

        //failure situations
        quorumDef SingletonA = new quorumDef();
        SingletonA.quorum.add(A);
        quorumDef SingletonB = new quorumDef();
        SingletonB.quorum.add(B);
        quorumDef SingletonC = new quorumDef();
        SingletonC.quorum.add(C);
        quorumDef ABU = SingletonA.union(SingletonB);
        quorumDef AB = SingletonA.crossUnion(SingletonB);
        quorumDef P_2A = getPowerSet(A,2 );
        quorumDef P_3B = getPowerSet(B, 3);
        quorumDef P2AB = P_2A.crossUnion(SingletonB);
        quorumDef P2AP3B = P_2A.crossUnion(P_3B);
        quorumDef P3BA = P_3B.crossUnion(SingletonA);

        //replication information for methods and objects
        quorumDef P_5A = getPowerSet(A, 5);
        quorumDef P_7B = getPowerSet(B, 7);
        quorumDef P_3A = getPowerSet(A, 3);
        quorumDef P_4B = getPowerSet(B, 4);

        //construct the types
        CIAType t0 = new CIAType(new nodeSet(cret), AB, AB);
        CIAType tp = new CIAType(new nodeSet(cret), P2AB, P2AB);
        CIAType ts = new CIAType(new nodeSet(c1), P2AB, P2AB);
        CIAType td = new CIAType(new nodeSet(cret), P2AP3B, P2AP3B);
        CIAType tc = new CIAType(new nodeSet(c2), P2AP3B, P2AP3B);
        CIAType tnum1 = new CIAType(new nodeSet(cret), AB, P2AB);

        //define \bot integrity and availability
        HashSet<Integer> b = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17));
        HashSet<nodeSet> bot = new HashSet<>();
        bot.add(new nodeSet(b));

        //host and quorum information
        HashSet<Integer> Hm9 = new HashSet<>(Arrays.asList(18));
        HashSet<Integer> Hm8 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        HashSet<Integer> Hm7 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        HashSet<Integer> Hm6 = new HashSet<>(Arrays.asList(18));
        HashSet<Integer> Hm5 = new HashSet<>(Arrays.asList(18));
        HashSet<Integer> Hm4 = new HashSet<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14));
        HashSet<Integer> Hm3 = new HashSet<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14));
        HashSet<Integer> Hm2 = new HashSet<>(Arrays.asList(18));
        HashSet<Integer> Hm1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        HashSet<Integer> Hm0 = new HashSet<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14));
        HashSet<Integer> Hret = new HashSet<>(Arrays.asList(18));

        //input the methods host information and signature manually
        ArrayList<Pair<nodeSet, quorumDef>> methodsInfo = new ArrayList<>();
        Pair<nodeSet, quorumDef> retInfo = new Pair<>(new nodeSet(Hret), P_4B.union(SingletonC));
        methodsInfo.add(retInfo);
        Pair<nodeSet, quorumDef> m0Info = new Pair<>(new nodeSet(Hm0), P_3A);
        methodsInfo.add(m0Info);
        Pair<nodeSet, quorumDef> m1Info = new Pair<>(new nodeSet(Hm1), SingletonC);
        methodsInfo.add(m1Info);
        Pair<nodeSet, quorumDef> m2Info = new Pair<>(new nodeSet(Hm2), P_4B);
        methodsInfo.add(m2Info);
        Pair<nodeSet, quorumDef> m3Info = new Pair<>(new nodeSet(Hm3), P_4B);
        methodsInfo.add(m3Info);
        Pair<nodeSet, quorumDef> m4Info = new Pair<>(new nodeSet(Hm4), SingletonC);
        methodsInfo.add(m4Info);
        Pair<nodeSet, quorumDef> m5Info = new Pair<>(new nodeSet(Hm5), SingletonC);
        methodsInfo.add(m5Info);
        Pair<nodeSet, quorumDef> m6Info = new Pair<>(new nodeSet(Hm6), P_3A);
        methodsInfo.add(m6Info);
        Pair<nodeSet, quorumDef> m7Info = new Pair<>(new nodeSet(Hm7), P_3A);
        methodsInfo.add(m7Info);
        Pair<nodeSet, quorumDef> m8Info = new Pair<>(new nodeSet(Hm8), SingletonC);
        methodsInfo.add(m8Info);
        Pair<nodeSet, quorumDef> m9Info = new Pair<>(new nodeSet(Hm9), SingletonC);
        methodsInfo.add(m9Info);

        //input the return type for all the methods
        //input the arguments for all the methods
        ArrayList<ArrayList<String>> mANames = new ArrayList<>(resultMethodDefs.size());
        for(int i = 0; i < resultMethodDefs.size(); i++){
            mANames.add(new ArrayList<>());
        }
        //notice the the type for a method maybe stronger than the return type of the whole class because of sequence.
        ArrayList<Pair<ArrayList<CIAType>, ArrayList<CIAType>>> methodSig = new ArrayList<>();
        CIAType retType = new CIAType(new nodeSet(cret), P2AP3B, P2AP3B);
        CIAType m0retType = new CIAType(new nodeSet(cret), P2AP3B, P2AP3B);
        CIAType m1retType = m0retType;
        CIAType m2retType = new CIAType(new nodeSet(c2), P2AP3B, P2AP3B);
        CIAType m3retType = m2retType;
        CIAType m4retType = m2retType;
        CIAType m5retType = m2retType;
        CIAType m6retType = new CIAType(new nodeSet(c3), P2AP3B, P2AP3B);
        CIAType m7retType = m6retType;
        CIAType m8retType = m6retType;
        CIAType m9retType = m6retType;

        CIAType retContext = new CIAType(new nodeSet(cret), P2AP3B, P2AP3B);
        CIAType m9Context = t0;
        CIAType m8Context = t0;
        CIAType m7Context = tnum1;
        CIAType m6Context = tnum1;
        CIAType m5Context = tnum1;
        CIAType m4Context = tnum1;
        CIAType m3Context = td;
        CIAType m2Context = td;
        CIAType m1Context = td;
        CIAType m0Context = td;

        //the order of the arguments matters, they are defined in mANames
        ArrayList<CIAType> mret = new ArrayList<>();
        mret.add(retContext);
        mret.add(retType);
        methodSig.add(new Pair<>(mret, new ArrayList<>()));
        mANames.get(0).add("bot");
        methodSig.get(0).element2.add(td);

        ArrayList<CIAType> m0 = new ArrayList<>();
        m0.add(m0Context);
        m0.add(m0retType);
        methodSig.add(new Pair<>(m0, new ArrayList<>()));
        mANames.get(1).add("price");
        methodSig.get(1).element2.add(td);

        ArrayList<CIAType> m1 = new ArrayList<>();
        m1.add(m1Context);
        m1.add(m1retType);
        methodSig.add(new Pair<>(m1, new ArrayList<>()));
        mANames.get(2).add("price");
        mANames.get(2).add("num");
        methodSig.get(2).element2.add(td);
        methodSig.get(2).element2.add(td);

        ArrayList<CIAType> m2 = new ArrayList<>();
        m2.add(m2Context);
        m2.add(m2retType);
        methodSig.add(new Pair<>(m2, new ArrayList<>()));
        mANames.get(3).add("balance");
        mANames.get(3).add("price");
        mANames.get(3).add("num");
        mANames.get(3).add("cashback");
        methodSig.get(3).element2.add(td);
        methodSig.get(3).element2.add(td);
        methodSig.get(3).element2.add(td);
        methodSig.get(3).element2.add(tc);

        //this is the first split of two methods
        ArrayList<CIAType> m3 = new ArrayList<>();
        m3.add(m3Context);
        m3.add(m3retType);
        methodSig.add(new Pair<>(m3, new ArrayList<>()));
        mANames.get(4).add("price");
        mANames.get(4).add("num");
        mANames.get(4).add("ID");
        mANames.get(4).add("cashback");
        methodSig.get(4).element2.add(td);
        methodSig.get(4).element2.add(td);
        methodSig.get(4).element2.add(td);
        methodSig.get(4).element2.add(tc);

        ArrayList<CIAType> m4 = new ArrayList<>();
        m4.add(m4Context);
        m4.add(m4retType);
        methodSig.add(new Pair<>(m4, new ArrayList<>()));
        mANames.get(5).add("price");
        mANames.get(5).add("num");
        mANames.get(5).add("ID");
        methodSig.get(5).element2.add(tp);
        methodSig.get(5).element2.add(tp);
        methodSig.get(5).element2.add(tp);

        ArrayList<CIAType> m5 = new ArrayList<>();
        m5.add(m5Context);
        m5.add(m5retType);
        methodSig.add(new Pair<>(m5, new ArrayList<>()));
        mANames.get(6).add("price");
        mANames.get(6).add("num");
        methodSig.get(6).element2.add(tp);
        methodSig.get(6).element2.add(tp);

        ArrayList<CIAType> m6 = new ArrayList<>();
        m6.add(m6Context);
        m6.add(m6retType);
        methodSig.add(new Pair<>(m6, new ArrayList<>()));
        mANames.get(7).add("schedule");
        mANames.get(7).add("price");
        mANames.get(7).add("num");
        methodSig.get(7).element2.add(ts);
        methodSig.get(7).element2.add(tp);
        methodSig.get(7).element2.add(tp);

        ArrayList<CIAType> m7 = new ArrayList<>();
        m7.add(m7Context);
        m7.add(m7retType);
        methodSig.add(new Pair<>(m7, new ArrayList<>()));
        mANames.get(8).add("schedule");
        mANames.get(8).add("num");
        methodSig.get(8).element2.add(ts);
        methodSig.get(8).element2.add(tp);

        ArrayList<CIAType> m8 = new ArrayList<>();
        m8.add(m8Context);
        m8.add(m8retType);
        methodSig.add(new Pair<>(m8, new ArrayList<>()));
        mANames.get(9).add("num");
        methodSig.get(9).element2.add(t0);

        ArrayList<CIAType> m9 = new ArrayList<>();
        m9.add(m9Context);
        m9.add(m9retType);
        methodSig.add(new Pair<>(m9, new ArrayList<>()));
        mANames.get(10).add("bot");
        methodSig.get(10).element2.add(t0);


        //input the object host information and signature manually
        HashMap<String, HashMap<String, ArrayList<CIAType>>> objSigs = new HashMap<>();
        //for object airline
        ArrayList<CIAType> getPrice1Arg = new ArrayList<>();
        getPrice1Arg.add(tp);
        //ret is in the last index
        getPrice1Arg.add(ts);
        HashMap<String, ArrayList<CIAType>> amethods = new HashMap<>();
        amethods.put("getPrice1", getPrice1Arg);
        ArrayList<CIAType> getPrice2Arg = new ArrayList<>();
        getPrice2Arg.add(tp);
        getPrice2Arg.add(tp);
        amethods.put("getPrice2", getPrice2Arg);
        ArrayList<CIAType> decSeatArg = new ArrayList<>();
        decSeatArg.add(td);
        decSeatArg.add(td);
        amethods.put("decSeat", decSeatArg);
        objSigs.put("airline", amethods);

        //for object Bank
        ArrayList<CIAType> getBalance1Arg = new ArrayList<>();
        getBalance1Arg.add(td);
        //ret is in the last index
        getBalance1Arg.add(tc);
        HashMap<String, ArrayList<CIAType>> bmethods = new HashMap<>();
        bmethods.put("getBalance1", getBalance1Arg);
        ArrayList<CIAType> getBalance2Arg = new ArrayList<>();
        getBalance2Arg.add(td);
        getBalance2Arg.add(td);
        bmethods.put("getBalance2", getBalance2Arg);
        ArrayList<CIAType> decBalanceArg = new ArrayList<>();
        decBalanceArg.add(td);
        decBalanceArg.add(td);
        bmethods.put("decBalance", decBalanceArg);
        objSigs.put("bank", bmethods);

        //for object customer
        ArrayList<CIAType> ticketNumArg = new ArrayList<>();
        ticketNumArg.add(t0);
        //ret is in the last index
        ticketNumArg.add(t0);
        HashMap<String, ArrayList<CIAType>> cmethods = new HashMap<>();
        cmethods.put("ticketNum", ticketNumArg);
        ArrayList<CIAType> getIDArg = new ArrayList<>();
        getIDArg.add(tnum1);
        getIDArg.add(tnum1);
        cmethods.put("getID", getIDArg);
        ArrayList<CIAType> updateInfoArg = new ArrayList<>();
        updateInfoArg.add(ts);
        updateInfoArg.add(tp);
        updateInfoArg.add(ts);
        cmethods.put("updateInfo", updateInfoArg);
        ArrayList<CIAType> updatePaymentArg = new ArrayList<>();
        updatePaymentArg.add(tc);
        updatePaymentArg.add(td);
        updatePaymentArg.add(tc);
        cmethods.put("updatePayment", updatePaymentArg);
        objSigs.put("customer", cmethods);

        //the objects hosts and hear from information
        HashMap<String, Pair<quorumDef, quorumDef>> objInfo = new HashMap<>();
        Pair<quorumDef, quorumDef> airlineInfo = new Pair<>(P_5A,P_3A);
        objInfo.put("airline", airlineInfo);
        Pair<quorumDef, quorumDef> bankInfo = new Pair<>(P_7B, P_4B);
        objInfo.put("bank", bankInfo);
        Pair<quorumDef, quorumDef> customerInfo = new Pair<>(SingletonC, SingletonC);
        objInfo.put("customer", customerInfo);

        //input the predefined variable information
        HashMap<String, CIAType> p = new HashMap<>();
        //p.put("x", new CIAType(new nodeSet(cx), new quorumDef(B), new quorumDef(B)));

        //input predefine umbrella for the objects
        //todo: we have to input tight umbrella type for the objects.
        // If there is no requirement, we can infer one
        HashMap<String, CIAType> u = new HashMap<>();
        u.put("airline", t0);
        u.put("bank", tnum1);
        u.put("customer", t0);

        SecureTypeChecking test5 = new SecureTypeChecking();
        Boolean r = test5.classTypeCheck(resultMethodDefs, methodsInfo, methodSig, mANames, objSigs, objInfo, p, u, t0);
        System.out.println("The type checking result for ticket system program:" + r.toString());
    }

    public static void AuctionTypeCheckingP(ArrayList<MethodDefinition> resultMethodDefs){
        //node sets
        HashSet<Integer> A0 = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4));
        HashSet<Integer> B0 = new HashSet<Integer>(Arrays.asList(5, 6, 7, 8, 9, 10, 11));
        HashSet<Integer> C0 = new HashSet<Integer>(Arrays.asList(12));
        nodeSet A = new nodeSet(A0);
        nodeSet B = new nodeSet(B0);
        nodeSet C = new nodeSet(C0);

        //confidentiality information for objects
        HashSet<Integer> c1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 12));
        HashSet<Integer> c2 = new HashSet<>(Arrays.asList(5, 6, 7, 8, 9, 10, 11, 12));
        HashSet<Integer> c3 = new HashSet<>(Arrays.asList(12));
        HashSet<Integer> cret = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));

        //failure situations
        quorumDef SingletonA = new quorumDef();
        SingletonA.quorum.add(A);
        quorumDef SingletonB = new quorumDef();
        SingletonB.quorum.add(B);
        quorumDef SingletonC = new quorumDef();
        SingletonC.quorum.add(C);
        quorumDef P_1A = getPowerSet(A, 1);
        quorumDef P_2B = getPowerSet(B, 2);
        quorumDef P1AP2B = P_1A.crossUnion(P_2B);

        //replication information for methods and objects
        quorumDef P_3A = getPowerSet(A, 3);
        quorumDef P_5B = getPowerSet(B, 5);
        quorumDef P_2A = getPowerSet(A, 2);
        quorumDef P_3B = getPowerSet(B, 3);

        //construct the types
        CIAType tsa = new CIAType(new nodeSet(c1), P1AP2B, P1AP2B);
        CIAType tsb = new CIAType(new nodeSet(c2), P1AP2B, P1AP2B);
        CIAType tu = new CIAType(new nodeSet(cret), P1AP2B, P1AP2B);
        CIAType t = new CIAType(new nodeSet(c3), P1AP2B, P1AP2B);

        //define \bot integrity and availability
        HashSet<Integer> b = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        HashSet<nodeSet> bot = new HashSet<>();
        bot.add(new nodeSet(b));

        //host and quorum information
        HashSet<Integer> Hm8 = new HashSet<>(Arrays.asList(12));
        HashSet<Integer> Hm7 = new HashSet<>(Arrays.asList(1, 2, 3));
        HashSet<Integer> Hm6 = new HashSet<>(Arrays.asList(1, 2, 3));
        HashSet<Integer> Hm5 = new HashSet<>(Arrays.asList(12));
        HashSet<Integer> Hm4 = new HashSet<>(Arrays.asList(5, 6, 7, 8, 9));
        HashSet<Integer> Hm3 = new HashSet<>(Arrays.asList(5, 6, 7, 8, 9));
        HashSet<Integer> Hm2 = new HashSet<>(Arrays.asList(12));
        HashSet<Integer> Hm1 = new HashSet<>(Arrays.asList(12));
        HashSet<Integer> Hm0 = new HashSet<>(Arrays.asList(12));
        HashSet<Integer> Hmret = new HashSet<>(Arrays.asList(12));

        //input the methods host information and signature manually
        ArrayList<Pair<nodeSet, quorumDef>> methodsInfo = new ArrayList<>();
        Pair<nodeSet, quorumDef> mretInfo = new Pair<>(new nodeSet(Hmret), SingletonC);
        methodsInfo.add(mretInfo);
        Pair<nodeSet, quorumDef> m0Info = new Pair<>(new nodeSet(Hm0), SingletonC);
        methodsInfo.add(m0Info);
        Pair<nodeSet, quorumDef> m1Info = new Pair<>(new nodeSet(Hm1), SingletonC);
        methodsInfo.add(m1Info);
        Pair<nodeSet, quorumDef> m2Info = new Pair<>(new nodeSet(Hm2), P_3B);
        methodsInfo.add(m2Info);
        Pair<nodeSet, quorumDef> m3Info = new Pair<>(new nodeSet(Hm3), P_3B);
        methodsInfo.add(m3Info);
        Pair<nodeSet, quorumDef> m4Info = new Pair<>(new nodeSet(Hm4), SingletonC);
        methodsInfo.add(m4Info);
        Pair<nodeSet, quorumDef> m5Info = new Pair<>(new nodeSet(Hm5), P_2A);
        methodsInfo.add(m5Info);
        Pair<nodeSet, quorumDef> m6Info = new Pair<>(new nodeSet(Hm6), P_2A);
        methodsInfo.add(m6Info);
        Pair<nodeSet, quorumDef> m7Info = new Pair<>(new nodeSet(Hm7), SingletonC);
        methodsInfo.add(m7Info);
        Pair<nodeSet, quorumDef> m8Info = new Pair<>(new nodeSet(Hm8), SingletonC);
        methodsInfo.add(m8Info);

        //input the return type for all the methods
        //input the arguments for all the methods
        ArrayList<ArrayList<String>> mANames = new ArrayList<>(resultMethodDefs.size());
        for(int i = 0; i < resultMethodDefs.size(); i++){
            mANames.add(new ArrayList<>());
        }
        //notice the the type for a method maybe stronger than the return type of the whole class because of sequence.
        ArrayList<Pair<ArrayList<CIAType>, ArrayList<CIAType>>> methodSig = new ArrayList<>();
        CIAType retType = t;
        CIAType m0retType = new CIAType(new nodeSet(c3), P1AP2B, P1AP2B);
        CIAType m1retType = m0retType;
        CIAType m2retType = m0retType;
        CIAType m3retType = m2retType;
        CIAType m4retType = m2retType;
        CIAType m5retType = m2retType;
        CIAType m6retType = m0retType;
        CIAType m7retType = m6retType;
        CIAType m8retType = m6retType;

        CIAType m8Context = tu;
        CIAType m7Context = tu;
        CIAType m6Context = tu;
        CIAType m5Context = tu;
        CIAType m4Context = tu;
        CIAType m3Context = tu;
        CIAType m2Context = tu;
        CIAType m1Context = tu;
        CIAType m0Context = tu;
        CIAType retContext = t;

        //the order of the arguments matters, they are defined in mANames
        ArrayList<CIAType> mret = new ArrayList<>();
        mret.add(retContext);
        mret.add(retType);
        methodSig.add(new Pair<>(mret, new ArrayList<>()));
        mANames.get(0).add("ret");
        methodSig.get(0).element2.add(t);

        ArrayList<CIAType> m0 = new ArrayList<>();
        m0.add(m0Context);
        m0.add(m0retType);
        methodSig.add(new Pair<>(m0, new ArrayList<>()));
        mANames.get(1).add("o");
        methodSig.get(1).element2.add(tu);

        ArrayList<CIAType> m1 = new ArrayList<>();
        m1.add(m1Context);
        m1.add(m1retType);
        methodSig.add(new Pair<>(m1, new ArrayList<>()));
        mANames.get(2).add("offerA");
        methodSig.get(2).element2.add(tu);

        ArrayList<CIAType> m2 = new ArrayList<>();
        m2.add(m2Context);
        m2.add(m2retType);
        methodSig.add(new Pair<>(m2, new ArrayList<>()));
        mANames.get(3).add("offerA");
        mANames.get(3).add("offerB");
        mANames.get(3).add("seatInfoB");
        methodSig.get(3).element2.add(tu);
        methodSig.get(3).element2.add(tu);
        methodSig.get(3).element2.add(tsb);

        //this is the first split of two methods
        ArrayList<CIAType> m3 = new ArrayList<>();
        m3.add(m3Context);
        m3.add(m3retType);
        methodSig.add(new Pair<>(m3, new ArrayList<>()));
        mANames.get(4).add("offerA");
        mANames.get(4).add("seatInfoB");
        mANames.get(4).add("u");
        methodSig.get(4).element2.add(tu);
        methodSig.get(4).element2.add(tsb);
        methodSig.get(4).element2.add(tu);

        ArrayList<CIAType> m4 = new ArrayList<>();
        m4.add(m4Context);
        m4.add(m4retType);
        methodSig.add(new Pair<>(m4, new ArrayList<>()));
        mANames.get(5).add("offerA");
        mANames.get(5).add("u");
        methodSig.get(5).element2.add(tu);
        methodSig.get(5).element2.add(tu);

        ArrayList<CIAType> m5 = new ArrayList<>();
        m5.add(m5Context);
        m5.add(m5retType);
        methodSig.add(new Pair<>(m5, new ArrayList<>()));
        mANames.get(6).add("offerA");
        mANames.get(6).add("u");
        mANames.get(6).add("seatInfoA");
        mANames.get(6).add("o");
        methodSig.get(6).element2.add(tu);
        methodSig.get(6).element2.add(tu);
        methodSig.get(6).element2.add(tsa);
        methodSig.get(6).element2.add(tu);

        ArrayList<CIAType> m6 = new ArrayList<>();
        m6.add(m6Context);
        m6.add(m6retType);
        methodSig.add(new Pair<>(m6, new ArrayList<>()));
        mANames.get(7).add("u");
        mANames.get(7).add("seatInfoA");
        mANames.get(7).add("o");
        methodSig.get(7).element2.add(tu);
        methodSig.get(7).element2.add(tsa);
        methodSig.get(7).element2.add(tu);

        ArrayList<CIAType> m7 = new ArrayList<>();
        m7.add(m7Context);
        m7.add(m7retType);
        methodSig.add(new Pair<>(m7, new ArrayList<>()));
        mANames.get(8).add("u");
        mANames.get(8).add("o");
        methodSig.get(8).element2.add(tu);
        methodSig.get(8).element2.add(tu);

        ArrayList<CIAType> m8 = new ArrayList<>();
        m8.add(m8Context);
        m8.add(m8retType);
        methodSig.add(new Pair<>(m8, new ArrayList<>()));
        mANames.get(9).add("o");
        methodSig.get(9).element2.add(tu);

        //input the object host information and signature manually
        HashMap<String, HashMap<String, ArrayList<CIAType>>> objSigs = new HashMap<>();
        //for object agent A
        ArrayList<CIAType> amakeOffer1Arg = new ArrayList<>();
        amakeOffer1Arg.add(tu);
        amakeOffer1Arg.add(tu);
        //ret is in the last index
        amakeOffer1Arg.add(tsa);
        HashMap<String, ArrayList<CIAType>> amethods = new HashMap<>();
        amethods.put("makeOffer1", amakeOffer1Arg);
        ArrayList<CIAType> amakeOffer2Arg = new ArrayList<>();
        amakeOffer2Arg.add(tu);
        amakeOffer2Arg.add(tu);
        amakeOffer2Arg.add(tu);
        amethods.put("makeOffer2", amakeOffer2Arg);
        objSigs.put("A", amethods);

        //for object agent B
        ArrayList<CIAType> bmakeOffer1Arg = new ArrayList<>();
        bmakeOffer1Arg.add(tu);
        bmakeOffer1Arg.add(tu);
        //ret is in the last index
        bmakeOffer1Arg.add(tsb);
        HashMap<String, ArrayList<CIAType>> bmethods = new HashMap<>();
        bmethods.put("makeOffer1", bmakeOffer1Arg);
        ArrayList<CIAType> bmakeOffer2Arg = new ArrayList<>();
        bmakeOffer2Arg.add(tu);
        bmakeOffer2Arg.add(tu);
        bmakeOffer2Arg.add(tu);
        bmethods.put("makeOffer2", bmakeOffer2Arg);
        objSigs.put("B", bmethods);

        //for object user agent
        ArrayList<CIAType> readArg = new ArrayList<>();
        readArg.add(tu);
        //ret is in the last index
        readArg.add(tu);
        HashMap<String, ArrayList<CIAType>> cmethods = new HashMap<>();
        cmethods.put("read", readArg);
        ArrayList<CIAType> winnerArg = new ArrayList<>();
        winnerArg.add(tu);
        winnerArg.add(t);
        cmethods.put("declareWinner", winnerArg);
        ArrayList<CIAType> updateArg = new ArrayList<>();
        updateArg.add(t);
        updateArg.add(tu);
        updateArg.add(t);
        cmethods.put("update", updateArg);
        //ArrayList<CIAType> updatePaymentArg = new ArrayList<>();
        //updatePaymentArg.add(tc);
        //updatePaymentArg.add(td);
        //updatePaymentArg.add(tc);
        //cmethods.put("updatePayment", updatePaymentArg);
        objSigs.put("user", cmethods);

        //the objects hosts and hear from information
        HashMap<String, Pair<quorumDef, quorumDef>> objInfo = new HashMap<>();
        Pair<quorumDef, quorumDef> AInfo = new Pair<>(P_3A,P_2A);
        objInfo.put("A", AInfo);
        Pair<quorumDef, quorumDef> BInfo = new Pair<>(P_5B, P_3B);
        objInfo.put("B", BInfo);
        Pair<quorumDef, quorumDef> userInfo = new Pair<>(SingletonC, SingletonC);
        objInfo.put("user", userInfo);

        //input the predefined variable information
        HashMap<String, CIAType> p = new HashMap<>();
        p.put("o", tu);

        //input predefine umbrella for the objects
        //we have to input tight umbrella type for the objects. If there is no requirement, we can infer one
        HashMap<String, CIAType> u = new HashMap<>();
        u.put("A", tu);
        u.put("B", tu);
        u.put("user", tu);

        SecureTypeChecking test5 = new SecureTypeChecking();
        Boolean r = test5.classTypeCheck(resultMethodDefs, methodsInfo, methodSig, mANames, objSigs, objInfo, p, u, tu);
        System.out.println("The type checking result for ticket system program:" + r.toString());
    }

    public static quorumDef getPowerSet(nodeSet inputSet, int setSize){
            quorumDef rev = new quorumDef();
            ImmutableSet<Integer> set = ImmutableSet.copyOf(inputSet.nSet);
            Set<Set<Integer>> powersetOfK = Sets.combinations(set, setSize);
            for(Set<Integer> qs: powersetOfK){
                nodeSet Rev_qs = new nodeSet(newHashSet(qs));
                rev.quorum.add(Rev_qs);
            }
            //System.out.println("The powersets are" + rev.toString());
            return rev;
    }
}
