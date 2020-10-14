package jsrc.x10.mhp;

import jsrc.px10.astbuild.ASTBuilder;
import jsrc.px10.syntaxanalyser.parser.PlasmaX10Parser;
import jsrc.x10.ast.tree.statement.Statement;
import lesani.compiler.constraintsolver.ConstConstraint;
import lesani.compiler.constraintsolver.Solver;
import lesani.compiler.constraintsolver.SubsetConstraint;
import lesani.collection.Pair;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.texttree.Printable;
import lesani.compiler.texttree.seq.TextSeq;
import lesani.file.Logger;
import lesani.file.SingleFileProcess;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
            logger.startLine("\tParsing ...");
            PlasmaX10Parser parser = constructParserFor(file, logger);
            jsrc.px10.syntaxanalyser.syntaxtree.File root = parser.File();
            logger.endLine(" OK");
            logger.startLine("\tBuilding AST ...");
            jsrc.x10.ast.tree.declaration.File astFile = new ASTBuilder(root).build();
            logger.endLine(" OK");
            logger.startLine("\tEliciting Constraints ...");
            ConstraintElicitor elicitor = new ConstraintElicitor(astFile);
            elicitor.elicit();
            HashSet<ConstConstraint<Var, Statement>> level1Consts = elicitor.level1Consts;
            HashSet<SubsetConstraint<Var>> level1subsets = elicitor.level1subsets;
            HashSet<SubsetConstraint<Var>> level2subsets = elicitor.level2subsets;
            HashSet<CrossConstraint> level2Crosses = elicitor.level2Crosses;
            Var mainVar = elicitor.mainVar;
            logger.endLine(" OK");
            logger.startLine("\tSolving Constraints ...");
            Solver<Var, Statement> level1Solver = new Solver<Var, Statement>(level1Consts, level1subsets);
            Map<Var, Set<Statement>> level1Solution = level1Solver.solve();
            Set<ConstConstraint<Var, Pair<Statement, Statement>>> level2Consts = resolveCrosses(level2Crosses, level1Solution);
            Solver<Var, Pair<Statement, Statement>> level2Solver = new Solver<Var, Pair<Statement, Statement>>(level2Consts, level2subsets);
            Map<Var, Set<Pair<Statement, Statement>>> level2Solution = level2Solver.solve();
            final Set<Pair<Statement, Statement>> mhp = level2Solution.get(mainVar);
            logger.endLine(" OK");

            logger.startLine("\tGenerating output ...");
            TextSeq textSeq = new TextSeq();
            textSeq.fullLine("May-happen-in-parallel pairs:");
            for (Pair<Statement, Statement> pair : mhp) {
                Statement s1 = pair._1();
                Statement s2 = pair._2();
                String line = "(" + s1.sourceLoc.lineNo + ":" + s1.sourceLoc.columnNo +
                             ", " + s2.sourceLoc.lineNo + ":" + s2.sourceLoc.columnNo + ")";
//                        System.out.println(line);
                textSeq.fullLine(line);
            }
            logger.endLine(" OK");
            return new Some<Printable>(textSeq.get());
        }
        catch (jsrc.px10.syntaxanalyser.parser.ParseException e) {
            System.err.println("\tParse Error.");
            e.printStackTrace();
            System.exit(1);
        }
        logger.println("");
        return null;
    }

    private HashSet<ConstConstraint<Var, Pair<Statement, Statement>>> resolveCrosses(HashSet<CrossConstraint> crosses, Map<Var, Set<Statement>> vars) {
        HashSet<ConstConstraint<Var, Pair<Statement, Statement>>> res = new HashSet<ConstConstraint<Var, Pair<Statement, Statement>>>();
        for (CrossConstraint cross : crosses) {
            Set<Statement> right1 = vars.get(cross.right1);
            Set<Statement> right2 = vars.get(cross.right2);
            ConstConstraint<Var, Pair<Statement, Statement>> constConstraint = cross.resolve(right1, right2);

/*
            Set<Pair<Statement, Statement>> pairs = constConstraint.rightValue;
            if (pairs.size() != 0) {
                System.out.println();
                System.out.println("--------------");
                for (Pair<Statement, Statement> pair : pairs) {
                    System.out.println(pair._1().sourceLoc + ", " + pair._2().sourceLoc);
                }
                System.out.println("--------------");
                System.out.println();
            }
*/

            res.add(constConstraint);
        }
        return res;
    }

    @Override
    public void exceptionHandler(Exception e) {
        e.printStackTrace(System.out);
    }

    private PlasmaX10Parser parser = null;
    private PlasmaX10Parser constructParserFor(File file, Logger logger) {
        try {
            if (parser == null)
                parser = new PlasmaX10Parser(new FileInputStream(file));
            else
                parser.ReInit(new FileInputStream(file));
            return parser;
        } catch (java.io.FileNotFoundException e) {
            logger.println("File " + file.getName() + " not found.");
            System.exit(1);
        }
        return null;
    }
}
