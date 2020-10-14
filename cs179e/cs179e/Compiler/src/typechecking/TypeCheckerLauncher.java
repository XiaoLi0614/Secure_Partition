package typechecking;

/**
 * User: Mohsen's Desktop
 * Date: Aug 25, 2009
 */

import lesani.file.ConsoleLogger;
import lesani.file.Logger;
import parsing.astbuilder.ASTBuilder;
import parsing.parser.syntaxtree.Goal;
import parsing.parser.parser.MiniJavaParser;
import parsing.parser.parser.ParseException;
import lesani.compiler.typing.exception.TypeErrorException;
import parsing.ast.tree.CompilationUnit;

import java.io.InputStream;


public class TypeCheckerLauncher {
    public static void processStream(InputStream stream, Logger logger) {
        try {
            MiniJavaParser parser = new MiniJavaParser(stream);
            Goal root = parser.Goal();
//            logger.test.println("Parse successful.");
            CompilationUnit compilationUnit = new ASTBuilder(root).build();
//            logger.test.println("ASTBuild successful.");
//			IndentPrinter indentPrinter = new IndentPrinter(compilationUnit);
//			String astPrint = indentPrinter.print();
//			writeForFile(file, astPrint, "ast");
            TypeChecker typeChecker = new TypeChecker(compilationUnit);
            typeChecker.check();
            logger.println("Program type checked successfully");
        }
        catch (ParseException e) {
            System.err.println("Parse Error.");
            e.printStackTrace();
        } catch (TypeErrorException e) {
            logger.println("Type error");
            logger.test.println("Type Error: " + e.getMessage());
        }
        logger.test.println("");
    }

	public static void main(String args[]) {
        processStream(System.in, ConsoleLogger.instance);
	}

}

