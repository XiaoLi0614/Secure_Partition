package lesani.image.gui.launchers;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.parallel.xtras.ParallelMasker;
import lesani.image.util.ImageUtil;
import lesani.image.util.exception.NotAnImageFileException;
import lesani.gui.console.Util;

import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 3:43:34 PM
 */

public class ParallelMaskerLauncher {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        final String filePathName = null;
        final String filePathName = "/home/mohsen/Prog/Shortcuts/4. Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Morphology/2.1. Fig9.29(a)Horse.jpg";
//        final String filePathName = "F:\\1.Works\\3.Research\\0.Research\\1.CDSC\\1.ImageProcessing\\2.Projects\\Project\\Images\\Morphology\\2.1. Fig9.29(a)Horse.jpg";
        String[] filePathNames;
        if (filePathName == null) {
            filePathNames = Util.readLines();
        } else
            filePathNames = new String[] {filePathName};

        if (filePathNames.length == 0)
            System.exit(1);


        GSImage[] images = new GSImage[filePathNames.length];
        try {
            for (int i = 0; i < images.length; i++) {
                images[i] = ImageUtil.getGrayScaleImage(filePathNames[i]);
            }
        }
        catch (NotAnImageFileException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        for (GSImage image : images) {
//            ImageUtil.showImage("Original image", image);
            ParallelMasker masker = new ParallelMasker(
                    image,
                    Mask.Sharpening.FirstDerivative.rightDiagonalSobelMask
//                    4
            );
            GSImage resultImage = masker.compute();
//            ImageUtil.showImage("Masked Image",
//                    Scaler.instance().compute(
//                    Abs.instance().compute(
//                    resultImage)
//                    ));

//            ImageUtil.showImage("Masked Image", resultImage);
        }
    }
}


