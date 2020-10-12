package lesani.image.gui.launchers;

import lesani.image.core.image.GSImage;

import lesani.image.core.op.core.masking.EdgeDetector;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.util.ImageUtil;
import lesani.image.util.exception.NotAnImageFileException;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen's Desktop
 * Date: May 4, 2007
 * Time: 11:13:56 AM
 */

public class EdgeDetectorLauncher
{
	public static void main(String[] args)
	{
		Scanner consoleScanner = new Scanner(System.in);
		System.out.println("Edge Detection\nEnter Path\\name for image.");
		String pathFileName = consoleScanner.nextLine();
		//String pathFileName = "C:\\1. MohsenDocuments\\1. Works\\4. Research\\Image Processing\\Processed\\0. Original\\3. Original 1 50%.jpg";
		//String pathFileName = "C:\\1. MohsenDocuments\\1. Works\\4. Research\\Image Processing\\Processed\\1. First Derivative\\1st Image\\X5\\Scaled after filter\\2. Result\\1. Little RightDiagonal and Vertical Added (Masked Scaled Thresholded 25 Scaled).jpg";

		GSImage image = null;
		try
		{
			image = ImageUtil.getGrayScaleImage(pathFileName);
		}
		catch (NotAnImageFileException e)
		{
			e.printStackTrace();
			System.exit(1);
		}


//        ImageUtil.showImage("Input Image", image);

        GSImage verticalEdegImage =
                EdgeDetector.process(
                        image,
                        Mask.Sharpening.FirstDerivative.verticalSobelMask);
        GSImage horizontalEdegImage =
                EdgeDetector.process(
                        image,
                        Mask.Sharpening.FirstDerivative.leftDiagonalSobelMask);
        GSImage rightDiagonalEdegImage =
                EdgeDetector.process(
                        image,
                        Mask.Sharpening.FirstDerivative.horizontalSobelMask);
        GSImage leftDiagonalEdegImage =
                EdgeDetector.process(
                        image,
                        Mask.Sharpening.FirstDerivative.rightDiagonalSobelMask);

//		ImageUtil.showImage("Vertical edges", verticalEdegImage);
//		ImageUtil.showImage("RightDiagonal edges", rightDiagonalEdegImage);
//		ImageUtil.showImage("Horizontal Edges", horizontalEdegImage);
//		ImageUtil.showImage("LeftDiagonal Edges", leftDiagonalEdegImage);


        
		GSImage[] edgeImages = {
            verticalEdegImage,
            horizontalEdegImage,
            rightDiagonalEdegImage,
            leftDiagonalEdegImage
        };

		GSImage edgeImagesScaled = ImageUtil.getAddedAndScaled(edgeImages);
//		ImageUtil.showImage("All Edges", edgeImagesScaled);


        GSImage[] inputPlusEdgeImages = {
            edgeImagesScaled,
            leftDiagonalEdegImage
        };

        GSImage inputPlusEdgeImagesScaled =
                ImageUtil.getAddedAndScaled(inputPlusEdgeImages);
//        ImageUtil.showImage("Image plus all edges", inputPlusEdgeImagesScaled);

	}
}
