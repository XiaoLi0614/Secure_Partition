package lesani.file;

import java.io.File;

import static lesani.file.Address.getExtension;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Nov 1, 2010
 * Time: 6:26:50 PM
 */

public class Launcher {

    private static final String listFileExtension = "list";
    private static /*final*/ String defaultListFilePathName = "DefaultSources" + "." + listFileExtension;


    public static void processFiles(
            Process process,
            String inputPath,
            String inputExtension
            ) {
        String[] args = new String[]{inputPath};
        processFiles(process, args, inputExtension, "out");
    }

    public static void processFiles(
            Process process,
            String inputPath,
            String inputExtension,
            String outputExtension) {
        String[] args = new String[]{inputPath};
        processFiles(process, args, inputExtension, outputExtension);
    }

    public static void processFiles(
            Process process,
            String inputPath,
            String outputPath,
            String inputExtension,
            String outputExtension) {
        String[] args = new String[]{inputPath, outputPath};
        processFiles(process, args, inputExtension, outputExtension);
    }

    public static void processFiles(
            Process process,
            String[] args,
            String inputExtension,
            String outputExtension) {

        processFiles(
                process, args,
                defaultListFilePathName,
                inputExtension, outputExtension
        );
    }
    public static void processFiles(
            Process process,
            String[] args,
            String defaultListFilePathName,
            String inputExtension,
            String outputExtension) {

        Launcher.defaultListFilePathName = defaultListFilePathName;

        FileProcessor fileProcessor = null;

        if (args.length == 0) {
            if (!(new File(defaultListFilePathName)).exists()) {
                System.out.println("Default input list file does not exit: " + defaultListFilePathName);
                printHelp();
                System.exit(0);
            }
            fileProcessor = new FileProcessor(
                    defaultListFilePathName,
                    inputExtension,
                    outputExtension);
        } else if (args.length == 1) {
            if (!(new File(args[0])).exists()) {
                System.out.println("Input file does not exit: " + args[0]);
                printHelp();
                System.exit(0);
            }
            if (Address.getExtension(args[0]).equals(listFileExtension)) {
                fileProcessor = new FileProcessor(
                        args[0],
                        inputExtension,
                        outputExtension);
            } else {
                InputEntry[] inputEntries;
                inputEntries = new InputEntry[] {
                    new InputEntry(args[0])
                };
                fileProcessor = new FileProcessor();
                String inputExtension2 = getExtension(args[0]);
                if (!inputExtension2.equals(""))
                     inputExtension = inputExtension2;
                fileProcessor.setInputExtension(inputExtension);
                fileProcessor.setOutputExtension(outputExtension);

                fileProcessor.setEntries(inputEntries);
            }
        } else if (args.length == 2) {
            InputEntry[] inputEntries;
            inputEntries = new InputEntry[] {
                new InputEntry(args[0], args[1])
            };

            if (inputExtension == null)
                 inputExtension = getExtension(args[0]);

            fileProcessor = new FileProcessor();
            fileProcessor.setInputExtension(inputExtension);
            fileProcessor.setOutputExtension(outputExtension);

            fileProcessor.setEntries(inputEntries);

        } else {
            printHelp();
            System.exit(0);
        }

        fileProcessor.setProcess(process);

        fileProcessor.process();
    }

    private static void printHelp() {
        System.out.println("" +
                "Usage:\n" +
                "\tIf run with no arguments,\n" +
                "\t\tThe default list file is processed: \"" + defaultListFilePathName + "\".\n" +
                "\tIf a pathFileNameExtension with the \"list\" extension is provided,\n" +
                "\t\tIt is regarded as a list file and processed.\n" +
                "\tIf a pathFileNameExtension or path is provided,\n" +
                "\t\tThe file or the files of the path are processed and outputs are put in the same dirs.\n" +
                "\tIf two arguments are provided,\n" +
                "\t\tThe first argument is the input file or dir and\n" +
                "\t\tThe second argument is the the output pathFileNameExtension or path.\n");
    }
}


