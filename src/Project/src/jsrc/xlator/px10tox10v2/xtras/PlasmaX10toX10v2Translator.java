package jsrc.xlator.px10tox10v2.xtras;

/**
 * User: Mohsen's Desktop
 * Date: Aug 25, 2009
 */

import jsrc.px10.syntaxanalyser.parser.PlasmaX10Parser;
import jsrc.x10.ast.tree.declaration.File;
import jsrc.x10.srcgen.SourceGenerator;
import jsrc.px10.astbuild.ASTBuilder;

import java.util.Scanner;
import java.io.*;

import lesani.collection.Pair;
import lesani.compiler.texttree.Printable;


public class PlasmaX10toX10v2Translator {

//    public static final String separator = "\\";
    public static final String SEPARATOR = "/";
    public static String baseOutDir =
            "/media/MOHSENHD/1.Works/3.Research/0.Research/2.X10/2.Project/Project/x10out";
    //"/benchmarks/<benchmark-name>/tmp/<MainFile>.x10"
    //The input file/dir pathname can be written here or if left null, the input file/dir pathname will be asked at runtime.
	private static String[] inCodeInputFileOrDirName;
//            "/home/lesani/Prog/Shortcuts/4.Research/2.X10/2.Project/BenchmarksSource/Scratch/benchmarks";
            //"/media/MOHSENHD/1.Works/3.Research/0.Research/2.X10/2.Project/BenchmarksSource/Scratch/mapreduce/RunMain.x10";
//            "/media/MOHSENHD/1.Works/3.Research/0.Research/2.X10/2.Project/BenchmarksSource/Scratch/frontend.plasmax10/RunMain.x10";
            //"/media/MOHSENHD/1.Works/3.Research/0.Research/2.X10/2.Project/Project/src/splasmax10/benchmarks/BenchmarkLinuxPaths.fileList";
            //"/media/MOHSENHD/1.Works/3.Research/0.Research/2.Plasma/Project/Project/src/frontend.plasmax10/benchmarks/BenchmarkLinuxPaths.fileList";
//            "C:\\Documents and Settings\\FARFAR\\Desktop\\Mohsen\\Plasma\\Project\\src\\frontend.plasmax10\\benchmarks\\BenchmarkWindowsPaths.fileList";
    //The extension of input files to process in directories.
    private static final String inputExtension = "x10";

    //The extension of text files that list input file/dir pathnames.
    //The first line contains the root directory or blank line if there is no root dir.
    //Later lines contain file pathnames relative to the root directory.
	private static final String listFileExtension = "fileList";


    private static void processInputFile(java.io.File file) {
		try {
            startLine("\tParse ...");
            PlasmaX10Parser parser = constructParserFor(file);
			jsrc.px10.syntaxanalyser.syntaxtree.File root = parser.File();
			endLine(" OK");
			startLine("\tASTBuild ...");
            File astFile = new ASTBuilder(root).build();
            endLine(" OK");
			startLine("\tTranslation ...");
			SourceGenerator sourceGenerator = new SourceGenerator(astFile);
			Pair<String, Printable> res = sourceGenerator.gen();
            Printable printable = res._2();
            endLine(" OK");
            startLine("\tOutput ...");
            /*
            Writer writer = getWriterForFile(file);
            String translation = textNode.print(writer);
            finalizeWiter(writer);
            */
			writeForFile(file, printable, "V2.0", "V2.x10");
            endLine(" OK");
		}
		catch (jsrc.px10.syntaxanalyser.parser.ParseException e) {
			System.err.println("\tParse Error.");
			e.printStackTrace();
            System.exit(1);
		}
		fullLine("");
	}

    private static jsrc.px10.syntaxanalyser.parser.PlasmaX10Parser parser = null;
    private static jsrc.px10.syntaxanalyser.parser.PlasmaX10Parser constructParserFor(java.io.File file) {
		try {
            if (parser == null)
                parser = new PlasmaX10Parser(new FileInputStream(file));
            else
                parser.ReInit(new java.io.FileInputStream(file));
            return parser;
		} catch (java.io.FileNotFoundException e) {
			fullLine("File " + file.getName() + " not found.");
            System.exit(1);
		}
        return null;
    }

    private static int tabCount = 0;
	private static void printTabs() {
        for (int i = 0; i < tabCount; i++)
            System.out.print("\t");
    }
	private static void put(String s) {
        System.out.print(s);
    }
	private static void fullLine(String s) {
        printTabs();
		System.out.println(s);
	}
    private static void startLine(String s) {
        printTabs();
        put(s);
    }
    private static void endLine(String s) {
        put(s);
        System.out.println();
    }

	public static void main(String args[]) {

		String[] inputFileOrDirName;
		if (args.length != 0)
			inputFileOrDirName = new String[] {args[0]};
		else
			if (inCodeInputFileOrDirName == null) {
				fullLine("Please enter the file or dir name:");
				Scanner scanner = new Scanner(System.in);
				inputFileOrDirName = new String[] {scanner.nextLine()};
			}
			else
				inputFileOrDirName = inCodeInputFileOrDirName;
        for (int i = 0; i < inputFileOrDirName.length; i++) {
            String s = inputFileOrDirName[i];
            process(s);
        }
	}


	private static void process(String inputFileOrDirName) {
		java.io.File fileOrDir = new java.io.File(inputFileOrDirName);
		if (fileOrDir.isDirectory())
			processDir(fileOrDir);
		else
			processFile(fileOrDir);
	}


	private static void processDir(java.io.File dir) {
		fullLine("Dir: " + dir.getName() + "  (" + dir.getPath() + ")");
		java.io.File[] files = dir.listFiles();
		tabCount++;
		for (java.io.File file : files) {
			if (file.isDirectory())
				processDir(file);
			else {
				processFile(file);
			}
		}
		tabCount--;
	}

    private static void processFile(java.io.File file) {
        try {
            if (getExtension(file.toString()).equals(inputExtension)) {
                fullLine("File: " + file.getName() + "  (" + file.getPath() + ")");
                //main(new String[] {file.getAbsolutePath()});
                processInputFile(file);

            } else {
                if (getExtension(file.toString()).equals(listFileExtension)) {
                    long t1 = System.currentTimeMillis();
                    fullLine("List file: " + file.getName() + "  (" + file.getPath() + ")");
                    tabCount++;
                    FileInputStream fileInputStream = new FileInputStream(file);
                    Scanner fileScanner = new Scanner(fileInputStream);
                    String rootDir = fileScanner.nextLine();
                    rootDir = rootDir.trim();
                    int fileCount = 0;
                    while (fileScanner.hasNext()) {
                        fileCount++;
                        String fileorDirName = fileScanner.nextLine();
                        process(rootDir + fileorDirName);
                    }
                    tabCount--;
                    long t2 = System.currentTimeMillis();
                    fullLine(fileCount +" files translated in " + ((t2-t1) / 1000) + " seconds.");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    private static void writeForFile(java.io.File inputFile, Printable printable, String subdir, String extension) {
		try {
			String fileName = inputFile.getName();
			String bareName = getBareName(fileName);
            if (baseOutDir == null)
                baseOutDir = inputFile.getParent();
            String outputFileName = baseOutDir + SEPARATOR + subdir + SEPARATOR + bareName + "." + extension;

			FileWriter fileWriter = new FileWriter(outputFileName);
			printable.print(fileWriter);

			fileWriter.close();
		} catch (IOException e) {
			System.err.println(e);
		}
    }

	private static void writeForFile(java.io.File inputFile, String content, String extension) {
		try {
			String fileName = inputFile.getName();
			String bareName = getBareName(fileName);
			String outputFileName = inputFile.getParent() + SEPARATOR + bareName + "." + extension;

			FileWriter fileWriter = new FileWriter(outputFileName);
			fileWriter.write(content);

			fileWriter.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}


	private static String getBareName(String fileName) {
		int index = 0;
		while (true)
		{
			//println(fileName.substring(index));

			int thisIndex = fileName.substring(index).indexOf(".");
			if (thisIndex == -1)
				break;
			index += thisIndex + 1;

		}
		if (index != 0)
			return fileName.substring(0, index - 1);
		else
			return fileName;

	}

	private static String getExtension(String fileName)
	{
		int index = 0;
		while (true)
		{
			//println(fileName.substring(index));

			int thisIndex = fileName.substring(index).indexOf(".");
			if (thisIndex == -1)
				break;
			index += thisIndex + 1;

		}
		if (index != 0)
			return fileName.substring(index, fileName.length());
		else
			return "";
	}
}

