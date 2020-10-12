package lambda_calculus;

import jsrc.x10.ast.tree.expression.Conditional;
import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.ObjectMethod;
import jsrc.x10.ast.tree.expression.Sequence;
import jsrc.x10.ast.tree.expression.literal.IntLiteral;
import jsrc.x10.ast.tree.statement.If;
import jsrc.x10.ast.tree.statement.Statement;
import jsrc.x10.ast.tree.type.IntType;
import jsrc.x10.ast.tree.xtras.IfThen;
import jsrc.x10.ast.visitor.CPSPrinter;
import jsrc.x10.ast.*;
import jsrc.x10.ast.tree.type.*;
import jsrc.x10.ast.visitor.StatementVisitor;


//import javax.swing.plaf.nimbus.State;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class translation_test {
    static String outputPath = "/home/xiao/IdeaProjects/secure_partition/out/lambda_calculus/";
    public static void main(String[] args)
    {
        Expression lambda1 = createOFTUseCase();;
        printToFile(lambda1., "oft_cps");
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
                            new Conditional(, , )),
                    new IntLiteral(0));
            return oftUseCase;
        }
}
