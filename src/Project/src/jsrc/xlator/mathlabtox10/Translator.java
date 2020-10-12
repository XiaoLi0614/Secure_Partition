package jsrc.xlator.mathlabtox10;


import lesani.gui.console.Logger;

import static lesani.file.Launcher.processFiles;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 2, 2010
 * Time: 1:45:08 PM
 */

public class Translator {
    public static void main(String[] args) {
//        Logger.setOn();
        Logger.setOff();

        String listFilePathName =
                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/jsrc/xlator/mathlabtox10/Files.list";

        Translation translation = new Translation();

        processFiles(translation, args, listFilePathName, "m", "x10");
    }
}
