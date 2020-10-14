package jsrc.x10.mhp;


import static lesani.file.Launcher.processFiles;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 2, 2010
 * Time: 1:45:08 PM
 */

public class Translator {
    public static void main(String[] args) {
        String listFilePathName =
                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/jsrc/x10/mhp/Files.list";

        Translation translation = new Translation();

        processFiles(translation, args, listFilePathName, "x10", "mhp");

    }
}
