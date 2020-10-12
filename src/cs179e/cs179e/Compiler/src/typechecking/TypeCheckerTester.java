package typechecking;

import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.compiler.texttree.Printable;
import lesani.file.*;
import lesani.file.Process;

import java.io.File;
import java.io.FileInputStream;

import static lesani.file.Launcher.processFiles;

@SuppressWarnings("unchecked")
public class TypeCheckerTester {

    static class TypeCheckingProcess extends SingleFileProcess {
        @Override
        public Option<Printable> process(File file, Logger logger) {
            try {
                final FileInputStream stream = new FileInputStream(file);
                TypeCheckerLauncher.processStream(stream, logger);
            } catch (java.io.FileNotFoundException e) {
                logger.println("File " + file.getName() + " not found.");
            }
            return None.instance();
        }

        @Override
        public void exceptionHandler(Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
//        System.out.println("Working dir:");
        final String baseDir = System.getProperty("user.dir");
//        System.out.println(baseDir);
        // Working dir should be
        // /media/MOHSENHD/1.Works/4.Course/1.CC/Project/Compiler
        final String inputPath = baseDir + "/" + "tests/typechecking/src";
        final String inputExt = "java";

        Process process = new TypeCheckingProcess();

        processFiles(process, inputPath, inputExt);
    }
}

