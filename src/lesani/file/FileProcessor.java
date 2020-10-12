package lesani.file;

import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.texttree.Printable;

import java.io.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 2, 2010
 * Time: 1:39:24 PM
 */

@SuppressWarnings("unchecked")
public class FileProcessor extends Logger {
    public String separator = "/"; //"\\";

    private String baseInputPath = "";
    private String baseOutputPath = "";
    private Option<InputEntry[]> entries = None.instance();
    private String inputExtension;
    private String outputExtension;

    private Process process;
    private int fileCount = 0;

    {
        test = new Logger() {
            @Override
            public void print(String s) {
                FileProcessor.this.print(s);
            }

            @Override
            public void println(String s) {
                FileProcessor.this.println(s);
            }

            @Override
            public void startLine(String s) {
                FileProcessor.this.startLine(s);
            }

            @Override
            public void endLine(String s) {
                FileProcessor.this.endLine(s);
            }
        };
    }

    public FileProcessor() {}
    public FileProcessor(String baseInputPath, String baseOutputPath, String inputExtension, String outputExtension, InputEntry[] inputEntries, Process process) {
        this.baseInputPath = baseInputPath;
        this.baseOutputPath = baseOutputPath;
        this.inputExtension = inputExtension;
        this.outputExtension = outputExtension;
        this.entries = new Some<InputEntry[]>(inputEntries);
        this.process = process;

    }

    //--------------------------------------------------
    public void process() {
        long t1 = System.currentTimeMillis();
        if (entries.isPresent()) {
            InputEntry[] infos = ((Some<InputEntry[]>) entries).get();
            for (InputEntry inputEntry : infos) {
                String relDirOrFilePathName = inputEntry.relDirOrFileRelPathName;
                String inputFileOrDirFullPathName = makeFullInputPathName(relDirOrFilePathName);
                File dirOrFile = new File(inputFileOrDirFullPathName);

                String relOutDir;
                Option<String> relOutDirOpt = inputEntry.relOutDirOrOutFileRelPathName;
                if (relOutDirOpt.isPresent()) {
                    relOutDir = ((Some<String>)relOutDirOpt).get();
                }
                else if (dirOrFile.isDirectory()) {
                    relOutDir = inputEntry.relDirOrFileRelPathName;
                }
                else {
                    relOutDir = Address.getPath(inputEntry.relDirOrFileRelPathName);
                }

                processDirOrFile(dirOrFile, "", relOutDir);
            }
        } else {
            processDirOrFile(new File(baseInputPath), "", "");
        }

        long t2 = System.currentTimeMillis();
        System.out.println("Processed " + fileCount + " files in " + ((double)(t2-t1) / 1000) + " seconds.");
    }

    private void processDirOrFile(File dirOrFile, String relDir, String outDir) {
        if (dirOrFile.isDirectory())
            processDir(dirOrFile, relDir, outDir);
        else
            processFile(dirOrFile, relDir, outDir);
    }

    private Stack<Long> startTimes = new Stack<Long>();

    private void processDir(File dir, String relDir, String outDir) {
        startTimes.push(System.currentTimeMillis());
        println("Folder: " + dir.getName() + "  (" + dir.getPath() + ")");
        mkFullOutPath(outDir, relDir);
        //String relOutDir, String relPath

        Comparator<File> alphaComparator = new Comparator<File>() {
		    public int compare(File f1, File f2) {
			    return f1.getName().compareTo(f2.getName());
		    }
	    };

        File[] fileOrDirs = dir.listFiles();
//       for (File fileOrDir : fileOrDirs) {
//          System.out.println(fileOrDir);
//       }


//        for (int i = 0; i < fileOrDirs.length; i++) {
//            File fileOrDir = fileOrDirs[i];
//            System.out.println(fileOrDir);
//        }


        tabCount++;
        if (process instanceof SingleFileProcess) {
           Arrays.sort(fileOrDirs, alphaComparator);
            for (File file : fileOrDirs) {
                if (file.isDirectory()) {
//                    String newRelDir = relDir + "/" + file.getName();
//                    mkFullOutPath(outDir, newRelDir);
                   // Todo: ...
                    processDir(file, relDir + "/" + file.getName(), outDir);
                } else {
                    processFile(file, relDir, outDir);
                }
            }
        } else {
            Set<File> files = new HashSet<File>();
            Set<File> dirs = new HashSet<File>();
            for (File file : fileOrDirs) {
                if (file.isDirectory())
                    dirs.add(file);
                else
                    if (Address.getExtension(file.toString()).equals(inputExtension))
                        files.add(file);
            }
            File[] fileArray = new File[files.size()];
            int i = 0;
            for (File file : files) {
                fileArray[i]= file;
                i++;
            }
            Arrays.sort(fileArray, alphaComparator);
            if (fileArray.length != 0)
                processFiles(fileArray, relDir, outDir);

            File[] dirArray = new File[dirs.size()];
            i = 0;
            for (File theDir : dirs) {
                dirArray[i] = theDir;
                i++;
            }
            Arrays.sort(dirArray, alphaComparator);
            for (File theDir : dirArray) {
                String newRelDir = relDir + "/" + theDir.getName();
//                mkFullOutPath(outDir, newRelDir);
                processDir(theDir, newRelDir, outDir);
            }            
        }
        long t1 = startTimes.pop();
        long t2 = System.currentTimeMillis();
        println("Processed folder " + dir.getName() + " in " + ((double) (t2 - t1) / 1000) + " seconds.");
        println("");
        tabCount--;
    }

    private void processFiles(File[] fileArray, String relDir, String outDir) {
        fileCount += fileArray.length;
        println("Files:");
        for (int j = 0; j < fileArray.length; j++) {
            File file = fileArray[j];
            startLine("\t" + file.getName() + "  (" + file.getPath() + ")");
            if (j != fileArray.length - 1)
                endLine(", ");
            else
                endLine("");
        }

        MultipleFileProcess multipleFileProcess = (MultipleFileProcess) process;
        
        try {
            Option<Output[]> outputsOpt = multipleFileProcess.process(fileArray, this);
            if (outputsOpt.isPresent()) {
                Output[] outputs = outputsOpt.value();
                for (int i = 0; i < outputs.length; i++) {
                    Output output = outputs[i];
                    String name;
                    if (output.name.isPresent())
                        name = ((Some<String>) output.name).get();
                    else
                        name = fileArray[i].getName();
                        name = Address.getBareName(name);
                    Printable printable = output.printable;
                    writeForFile(name, relDir, outDir, printable);
                }
            }
        } catch (Exception e) {
            multipleFileProcess.exceptionHandler(e);
        }

    }

    private void processFile(File file, String relPath, String outDir) {
        if (Address.getExtension(file.toString()).equals(inputExtension)) {
            SingleFileProcess singleFileProcess = (SingleFileProcess) process;
            try {
                fileCount++;
                println("File: " + file.getName() + "  (" + file.getPath() + ")");
                //main(new String[] {file.getAbsolutePath()});
                tabCount++;
                Option<Printable> printer = singleFileProcess.process(file, this);
                if (printer.isPresent()) {
                    String fileName = file.getName();
                    String bareName = Address.getBareName(fileName);
                    writeForFile(bareName, relPath, outDir, printer.value());
                }
                tabCount--;
            } catch (Exception e) {
                singleFileProcess.exceptionHandler(e);
            }
        }
    }


    // bareName: is the name for the output file.
    // relPath: is the relative path that the input file is read from.
    // relOutDir: is the relative path to the base output path that is given for the input entry that this file comes from.
    private void writeForFile(String bareName, String relPath, String relOutDir, Printable printable) {
        String dirAddress = makeFullOutPath(relOutDir, relPath);
        String outputPathNameExtension;
        if (!(new File(dirAddress).isDirectory()))
            outputPathNameExtension = dirAddress;
            // This is for the case where in the InputEntry the file name and extension is specified.
        else
            outputPathNameExtension = dirAddress + bareName + "." + outputExtension;

        try {
            FileWriter fileWriter = new FileWriter(outputPathNameExtension);
            printable.print(fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("At FileProcessor.writeForFile");
            System.out.println(e);
        }
    }

    // ------------------------------------------------------
    private boolean atBeg = true;
    private int tabCount = 0;
	private void printTabs() {
        for (int i = 0; i < tabCount; i++)
            System.out.print("\t");
    }
	public void print(String s) {
        if (atBeg)
            startLine("");
        System.out.print(s);
        atBeg = false;
    }
	public void println(String s) {
        if (atBeg)
            startLine("");
		System.out.println(s);
        atBeg = true;
	}
    public void startLine(String s) {
        if (!atBeg)
            System.out.println("");
        printTabs();
        System.out.print(s);
        atBeg = false;
    }
    public void endLine(String s) {
        if (atBeg)
            startLine("");
        System.out.print(s);
        System.out.println();
        atBeg = true;
    }

    private void put(String s) {
        print(s);
    }

    // ------------------------------------------------------

    private String makeFullInputPathName(String inputDirName) {
        return baseInputPath + (baseInputPath.equals("")?"":separator) + inputDirName;
    }

    private String makeFullOutPath(String relOutDir, String relPath) {
//        System.out.println(baseOutputPath);
//        System.out.println(relOutDir);
//        System.out.println(relPath);
        return baseOutputPath + (baseInputPath.equals("")?"":separator) +
                ((relOutDir==null)?"":relOutDir)
                + separator  + relPath + (relPath.equals("")?"":separator);
    }

    private String mkFullOutPath(String relOutDir, String relPath) {
        String dirAddress = makeFullOutPath(relOutDir, relPath);
        File dir = new File(dirAddress);
//        System.out.println("Making " + dir);
        if (!dir.exists())
            if (!dir.mkdir())
                System.out.println("Error: Dir not created.");

//        File[] fileOrDirs = dir.listFiles();
//        for (File file : fileOrDirs) {
//            if (!file.isDirectory()) {
//                file.delete();
//            }
//        }

        return dirAddress;
    }

    // ------------------------------------------------------

    public Option<InputEntry[]> getEntries() {
        return entries;
    }

    public void setEntries(InputEntry[] inputEntries) {
        this.entries = new Some<InputEntry[]>(inputEntries);
    }
    // ------------------------------------------------------
    
    public String getBaseInputPath() {
        return baseInputPath;
    }

    public void setBaseInputPath(String baseInputPath) {
        this.baseInputPath = baseInputPath;
    }

    public String getBaseOutputPath() {
        return baseOutputPath;
    }

    public void setBaseOutputPath(String baseOutputPath) {
        this.baseOutputPath = baseOutputPath;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getOutputExtension() {
        return outputExtension;
    }

    public void setOutputExtension(String outputExtension) {
        this.outputExtension = outputExtension;
    }

    public String getInputExtension() {
        return inputExtension;
    }

    public void setInputExtension(String inputExtension) {
        this.inputExtension = inputExtension;
    }

    // ------------------------------------------------------------------------    
    // The file contains the following, one per line:
    // 1. File Comment line
    // 2. baseInputPath
    // 3. baseOutputPath
    // Later lines contain triples of the following form
    // 6. Comment line
    // 7. Input dir or input file pathname relative to the baseInputPath
    // 8. Output dir relative to the baseOutputPath
    // To specify absolute paths in 7 and 8, leave out 2 and 3.

    public FileProcessor(String listFile, String inputExtension, String outputExtension) {
        try {

            File file = new File(listFile);
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner fileScanner = new Scanner(fileInputStream);

            fileScanner.nextLine(); // Comment line

            String baseInputPath = fileScanner.nextLine();
            baseInputPath = baseInputPath.trim();

            String baseOutputPath = fileScanner.nextLine();
            baseOutputPath = baseOutputPath.trim();


            List<InputEntry> inputEntrySet = new LinkedList<InputEntry>();
            try {
            while (fileScanner.hasNext()) {
                fileScanner.nextLine(); // Comment line
                String inputRelDirOrRelFilePathName = fileScanner.nextLine();
                if (inputRelDirOrRelFilePathName.substring(0,2).equals("//")) {
                    //skip this entry
                    fileScanner.nextLine();
                    continue;
                }
                String outputRelDirOrRelFilePathName = fileScanner.nextLine();
                if (outputRelDirOrRelFilePathName.equals(""))
                    inputEntrySet.add(new InputEntry(inputRelDirOrRelFilePathName));
                else
                    inputEntrySet.add(new InputEntry(inputRelDirOrRelFilePathName, outputRelDirOrRelFilePathName));
            }
            } catch (Exception e) {}

            InputEntry[] inputEntries = new InputEntry[inputEntrySet.size()];
            int i = 0;
            for (InputEntry inputEntry : inputEntrySet) {
                inputEntries[i] = inputEntry;
                i++;
            }

            this.baseInputPath = baseInputPath;
            this.baseOutputPath = baseOutputPath;
            this.inputExtension = inputExtension;
            this.outputExtension = outputExtension;
            this.entries = new Some<InputEntry[]>(inputEntries);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
