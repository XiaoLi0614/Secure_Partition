package jsrc.xlator.mathlabtox10;

import jsrc.matlab.astbuild.ASTBuilder;
import jsrc.matlab.ast.tree.declaration.CompilationUnit;
import jsrc.matlab.converttox10.ASTConverter;
import jsrc.matlab.syntaxanalysis.parser.MatlabParser;
import jsrc.matlab.syntaxanalysis.parser.ParseException;
import jsrc.matlab.syntaxanalysis.parser.Token;
import jsrc.matlab.typeinference.TypeInferencer;
import jsrc.matlab.typeinference.constraintelicitation.ConstraintElicitor;
import jsrc.matlab.typeinference.exceptions.MoreThanOneTypeException;
import jsrc.matlab.typeinference.exceptions.SemanticErrorException;
import jsrc.matlab.typeinference.exceptions.TypeMismatch;
import jsrc.matlab.typeinference.exceptions.TypeMismatches;
import jsrc.matlab.typeinference.unification.Constraint;
import jsrc.x10.ast.tree.Node;
import jsrc.x10.srcgen.SourceGenerator;
import lesani.collection.Pair;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.ast.LocInfo;
import lesani.compiler.texttree.Printable;
import lesani.file.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Nov 1, 2010
 * Time: 5:12:00 PM
 */

public class Translation extends MultipleFileProcess {
    @Override
    public Option<Output[]> process(File[] files, Logger logger) throws Exception {
//        TypeVar.restart();
        try {
            logger.startLine("\t\tParsing mat ...");
            jsrc.matlab.syntaxanalysis.syntaxtree.File[] roots = new jsrc.matlab.syntaxanalysis.syntaxtree.File[files.length];
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                MatlabParser parser = constructParserFor(file, logger);
                try {
                    roots[i] = parser.File();
                }
                catch (ParseException e) {
                    String fileName = file.getName();
                    logger.endLine(" Fail");
                    logger.println("\t\t\tParse Error:");
                    logger.startLine("\t\t\t\t" + fileName);
                    printParseError(logger, e);
                    throw new SkipException();
                }
            }
            logger.endLine(" OK");

            logger.startLine("\t\tBuilding AST ...");
            CompilationUnit[] compilationUnits = new CompilationUnit[roots.length];
            for (int i = 0; i < roots.length; i++) {
                jsrc.matlab.syntaxanalysis.syntaxtree.File root = roots[i];
                compilationUnits[i] = new ASTBuilder(root).build();
            }
            logger.endLine(" OK");


            logger.startLine("\t\tInferring Types ...");
//            TypedCompilationUnits typedCompilationUnits;
            try {
//                typedCompilationUnits = TypeInferencer.infer(compilationUnits);
                compilationUnits = TypeInferencer.infer(compilationUnits);
            } catch (TypeMismatches exception) {
                logger.endLine(" Fail");
                logger.startLine("\t\t\tType Mismatch:");
                if (exception.typeMismatches.size() == 1)
                    logger.endLine("");
                else
                    logger.endLine("(One of the following)");

                System.out.println();
                System.out.println("Constraints:");
                System.out.println(ConstraintElicitor.printConsts(exception.constraints));
                System.out.println("AltConstraints:");
                System.out.println(ConstraintElicitor.printAltConsts(exception.altConsts));

                System.out.println("Enter the altNos that you want to see. Enter -1 at the end.");
                Scanner scanner = new Scanner(System.in);
                List<Integer> ns = new LinkedList<Integer>();
                int n = 0;
                while (n != -1) {
                    n = scanner.nextInt();
                    if (n != -1)
                        ns.add(n);
                }
                Outer:
                for (TypeMismatch typeMismatch : exception.typeMismatches) {
                    if (exception.typeMismatches.size() > 500) {
                        Iterator<Integer> aIterator = typeMismatch.altNos.iterator();

                        for (Iterator<Integer> iterator = ns.iterator(); iterator.hasNext();) {
                            Integer integer = iterator.next();
                            if (!aIterator.hasNext())
                                break;
                            Integer aInteger = aIterator.next();
                            if (!integer.equals(aInteger))
                                continue Outer;
                        }
                    }

                    Constraint constraint = typeMismatch.constraint;
                    File file = files[constraint.compilationUnitNo];
                    String fileName = file.getName();
                    LocInfo locInfo = constraint.locInfo;
                    logger.println("\t\t\t\t" + fileName + " at " + locInfo);
                    //logger.println("\t\t\t\tbetween ");
                    logger.println("\t\t\t\t" + constraint.text);
                    logger.println("\t\t\t\t\t" + constraint.left + " and ");
                    logger.println("\t\t\t\t\t" + constraint.right);
                    System.out.println(typeMismatch.altNos);

                    System.out.println("Visited Constraints");
                    for (Constraint constr : typeMismatch.visitedConstrains)
                        System.out.println(constr);

                    System.out.println("OrigConst");
                    System.out.println(typeMismatch.origConstr);
                    System.out.println("Subst");
                    System.out.println(typeMismatch.substitution);
                }
                throw new SkipException();
            } catch (SemanticErrorException exception) {
                logger.endLine(" Fail");
                logger.println("\t\t\tSemantic Error:");
                File file = files[exception.compilationUnitNo];
                String fileName = file.getName();
                LocInfo locInfo = exception.locInfo;
                logger.println("\t\t\t\t" + fileName + " at " + locInfo);
                logger.println("\t\t\t\t" + exception.getMessage());
                throw new SkipException();
            } catch (MoreThanOneTypeException exception) {
                logger.endLine(" Fail");
                logger.println("\t\t\tMore than one typing");
                throw new SkipException();
            }
            logger.endLine(" OK");

            logger.startLine("\t\tConverting to X10 AST ...");
//            ASTConverter astConverter = new ASTConverter(typedCompilationUnits);
            ASTConverter astConverter = new ASTConverter(compilationUnits);
            Node[] x10ASTs =  astConverter.convert();
            logger.endLine(" OK");

            logger.startLine("\t\tGenerating X10 source ...");
            Printable[] printables = new Printable[x10ASTs.length];
            String[] names = new String[x10ASTs.length];

            for (int i = 0; i < x10ASTs.length; i++) {
                SourceGenerator generator = new SourceGenerator((jsrc.x10.ast.tree.declaration.File)(x10ASTs[i]));
                Pair<String, Printable> res = generator.gen();
                names[i] = res._1();
                printables[i] = res._2();
            }
            logger.endLine(" OK");

//            logger.println("");

            Output[] outputs = new Output[printables.length];
            for (int i = 0; i < outputs.length; i++)
                outputs[i] = new Output(names[i], printables[i]);
            return new Some<Output[]>(outputs);
        } catch (RuntimeException e) {
            System.out.println();
            e.printStackTrace(System.out);
            throw new SkipException();
        }

//            logger.startLine("\tTranslation ...");
//            jsrc.backend.x10v2.Translator xlator = new jsrc.backend.x10v2.Translator(compilationUnits);
//            TextNode textNode = xlator.translate();
//            logger.endLine(" OK");
//            logger.startLine("\tOutput ...");
//            lesani.compiler.texttree.Printer printer = textNode;
//            logger.endLine(" OK");
//            return printer;

    }

    private MatlabParser parser = null;
    private MatlabParser constructParserFor(File file, Logger logger) {
        try {
            if (parser == null)
                parser = new MatlabParser(new FileInputStream(file));
            else
                parser.ReInit(new FileInputStream(file));
            return parser;
        } catch (java.io.FileNotFoundException e) {
            logger.println("File " + file.getName() + " not found.");
            System.exit(1);
        }
        return null;
    }


    static void printParseError(Logger logger, ParseException e) {
        Token currentToken = e.currentToken;
        int[][] expectedTokenSequences = e.expectedTokenSequences;
        String[] tokenImage = e.tokenImage;

        logger.endLine(" at " + currentToken.next.beginLine + ":" + currentToken.next.beginColumn);

        int maxSize = 0;
        for (int[] expectedTokenSequence : expectedTokenSequences) {
            if (maxSize < expectedTokenSequence.length)
                maxSize = expectedTokenSequence.length;
        }


        logger.println("\t\t\t\tEncountered:");

        String st = "";
        Token tok = currentToken.next;
        for (int i = 0; i < maxSize; i++) {
            if (i != 0)
                st += " ";
            if (tok.kind == 0) {
                st += tokenImage[0];
                break;
            }
            st += " " + tokenImage[tok.kind];
            tok = tok.next;
        }
        logger.println("\t\t\t\t\t" + st);

        if (expectedTokenSequences.length == 1)
            logger.println("\t\t\t\tWas expecting:");
        else
            logger.println("\t\t\t\tWas expecting one of:");
        
        st = "";
        for (int i = 0; i < expectedTokenSequences.length; i++) {
            for (int j = 0; j < expectedTokenSequences[i].length; j++)
                st += tokenImage[expectedTokenSequences[i][j]] + " ";

            if (expectedTokenSequences[i][expectedTokenSequences[i].length - 1] != 0) {
                st += "...";
            }
            logger.println("\t\t\t\t\t" + st);
        }
    }


    @Override
    public void exceptionHandler(Exception exception) {
        if (!(exception instanceof SkipException))
            exception.printStackTrace();
    }

}

class SkipException extends Exception {}

