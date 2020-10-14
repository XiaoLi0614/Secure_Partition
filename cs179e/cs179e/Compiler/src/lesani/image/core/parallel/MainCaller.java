package lesani.image.core.parallel;

import lesani.image.util.exception.NotAnImageFileException;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Jun 3, 2010
 * Time: 2:55:52 PM
 */

public class MainCaller {
    public static void main(String[] argz) throws NotAnImageFileException {
        String[] args = new String[2];
        args[0] =
                                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Lena.jpg";
                //                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/lena124.jpg";
                //                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/lena256.jpg";
//                            "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/lena512.jpg";
                //                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Landscape1.jpg";
                //                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Landscape2.jpg";
        args[1] = "2";
        ParallelEdgeDetectorLauncher.main(args);
    }
}
