package lambda_calculus;

//import javax.swing.plaf.nimbus.State;
import lambda_calculus.cps_ast.tree.Context;
import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.source_ast.tree.expression.Conditional;
import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.tree.expression.ObjectMethod;
import lambda_calculus.source_ast.tree.expression.Var;
import lambda_calculus.source_ast.tree.expression.literal.IntLiteral;
import lambda_calculus.source_ast.tree.expression.op.Sequence;
import lambda_calculus.source_ast.visitor.CPSPrinter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


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
