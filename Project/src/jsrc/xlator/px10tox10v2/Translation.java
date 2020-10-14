package jsrc.xlator.px10tox10v2;

import jsrc.px10.astbuild.ASTBuilder;
import jsrc.px10.syntaxanalyser.parser.PlasmaX10Parser;
import jsrc.x10.srcgen.SourceGenerator;
import lesani.collection.Pair;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.texttree.Printable;
import lesani.file.Logger;
import lesani.file.SingleFileProcess;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Nov 1, 2010
 * Time: 5:12:00 PM
 */

public class Translation extends SingleFileProcess {
    @Override
    public Option<Printable> process(File file, Logger logger) {
        try {
            logger.startLine("\tParse ...");
            PlasmaX10Parser parser = constructParserFor(file, logger);
            jsrc.px10.syntaxanalyser.syntaxtree.File root = parser.File();
            logger.endLine(" OK");
            logger.startLine("\tASTBuild ...");
            jsrc.x10.ast.tree.declaration.File astFile = new ASTBuilder(root).build();
            logger.endLine(" OK");
            logger.startLine("\tTranslation ...");
            SourceGenerator sourceGenerator = new SourceGenerator(astFile);
            Pair<String, Printable> res = sourceGenerator.gen();
            logger.endLine(" OK");
            logger.startLine("\tOutput ...");
            /*
            Writer writer = getWriterForFile(file);
            String translation = textNode.print(writer);
            finalizeWiter(writer);
            */

            Printable printable = res._2();
            logger.endLine(" OK");
            return new Some<Printable>(printable);
        }
        catch (jsrc.px10.syntaxanalyser.parser.ParseException e) {
            System.err.println("\tParse Error.");
            e.printStackTrace();
            System.exit(1);
        }
        logger.println("");
        return null;
    }

    @Override
    public void exceptionHandler(Exception e) {
        e.printStackTrace(System.out);
    }

    private jsrc.px10.syntaxanalyser.parser.PlasmaX10Parser parser = null;
    private jsrc.px10.syntaxanalyser.parser.PlasmaX10Parser constructParserFor(java.io.File file, Logger logger) {
        try {
            if (parser == null)
                parser = new PlasmaX10Parser(new FileInputStream(file));
            else
                parser.ReInit(new java.io.FileInputStream(file));
            return parser;
        } catch (java.io.FileNotFoundException e) {
            logger.println("File " + file.getName() + " not found.");
            System.exit(1);
        }
        return null;
    }
}
