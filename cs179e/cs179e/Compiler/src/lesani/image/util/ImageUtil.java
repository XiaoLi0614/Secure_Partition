package lesani.image.util;

import lesani.image.core.image.BImage;
import lesani.image.core.image.GSImage;

import lesani.image.core.image.Image;
import lesani.image.core.op.cascading.AdderScaler;
import lesani.image.core.op.cascading.MaskerAdderScaler;
import lesani.image.core.op.core.*;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.op.core.masking.Masker;
import lesani.image.gui.environment.Environment;
import lesani.image.xdatahiding.runlength.core.RunLengthDataHider;
import lesani.image.xdatahiding.runlength.core.*;
import lesani.image.core.op.morphology.core.binary.*;
import lesani.image.core.op.morphology.core.binary.core.*;
import lesani.image.core.op.morphology.core.grayscale.GrayScaleCloser;
import lesani.image.core.op.morphology.core.grayscale.GrayScaleDilator;
import lesani.image.core.op.morphology.core.grayscale.GrayScaleEroder;
import lesani.image.core.op.morphology.core.grayscale.GrayScaleOpener;
import lesani.image.core.op.morphology.exception.NotSameSizeImageException;
import lesani.image.util.exception.NotAnImageFileException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Jan 24, 2005
 * Time: 5:53:56 PM
 */

public class ImageUtil {

	public static BImage complement(BImage image) {
        Inverter<BImage> inverter = Inverter.instance();
        return inverter.compute(image);
    }

	public static GSImage complement(GSImage image) {
        Inverter<GSImage> inverter = Inverter.instance();
        return inverter.compute(image);
    }

	public static BImage addOneRow(BImage hiddenImage)
	{
		BImage bImage = new BImage(hiddenImage.getWidth(), hiddenImage.getHeight() + 1);

		for (int i = 0; i < hiddenImage.getWidth(); i++)
			for (int j = 0; j < hiddenImage.getHeight(); j++)
		        bImage.set(i, j, hiddenImage.get(i, j));

		return bImage;
	}

	public static BImage runLengthHide(BImage originalImage, BImage logoImage)
	{
		return new RunLengthDataHider(originalImage, logoImage).getEmbeddingImage();
	}

	public static BImage runLengthExtract(BImage embeddingImage)
	{
		return new RunLengthDataExtracter(embeddingImage).getHiddenImage();
	}

	public static BImage runLengthHide(BImage originalImage, BImage logoImage, int windowWidth, int windowHeight)
	{
		return new PositionRunLengthDataHider(originalImage, logoImage, windowWidth, windowHeight).getEmbeddingImage();
	}

	public static BImage runLengthAuthenticate(BImage embeddingImage, BImage logoImage, int windowWidth, int windowHeight)
	{
		return new PositionRunLengthAuthenticator(embeddingImage, logoImage, windowWidth, windowHeight).getMarkedImage();
	}

    private static Environment environment = null;

    private static void initEnvironment() {
        if (environment == null)
            try {
                javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        environment = new Environment();

                        environment.setSize(1000, 600);
                        environment.setLocation(200, 50);
                        environment.setVisible(true);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

    }

    public static void showImage(String title, BufferedImage image) {
        initEnvironment();
        environment.showImage(title, image);
    }
    public static void showImage(BufferedImage image) {
        initEnvironment();
        environment.showImage(image);
    }
	public static void showImage(String title, Image image) {
        initEnvironment();
        environment.showImage(title, image.getBufferedImage());
	}
	public static void showImage(Image image) {
        initEnvironment();
        environment.showImage(image.getBufferedImage());
	}

/*
	public static ImageFrame showImage(String title, Image image) {
		return showImage(title, image.getBufferedImage());
	}

	public static ImageFrame showImage(String title, BufferedImage image) {
		ImageFrame imageFrame = new ImageFrame(title, image);
		imageFrame.view();
		return imageFrame;
	}
*/

	public static BImage dilate(BImage bImage, int[][] mask)
	{
		return Dilator.compute(bImage, new Mask(mask));
	}

	public static BImage erode(BImage bImage, int[][] mask)
	{
		return Eroder.compute(bImage, new Mask(mask));
	}

	public static BImage open(BImage bImage, int maskLength) {
		return Opener.compute(bImage, maskLength);
	}

	public static BImage close(BImage bImage, int maskLength) {
		return Closer.compute(bImage, maskLength);
	}

	public static BImage differentiate(BImage bImageA, BImage bImageB)
			throws NotSameSizeImageException
	{
		Subtractor<BImage> subtractor = Subtractor.instance();
		return subtractor.compute(bImageA, bImageB);
	}
	public static GSImage differentiate(GSImage gsImageA, GSImage gsImageB)
			throws NotSameSizeImageException
	{
		Subtractor<GSImage> subtractor = Subtractor.instance();
		return subtractor.compute(gsImageA, gsImageB);
	}

	public static BImage detectBorder(BImage bImage, int maskLength)
	{
		BorderDetector borderDetector = new BorderDetector(bImage, maskLength);
		return borderDetector.getBorder();
	}

	public static GSImage grayScaleDilate(GSImage gsImage, int[][] mask)
	{
		return GrayScaleDilator.compute(gsImage, new Mask(mask));
	}

	public static GSImage grayScaleErode(GSImage gsImage, int[][] mask)
	{
		return GrayScaleEroder.compute(gsImage, new Mask(mask));

	}

	public static GSImage grayScaleOpen(GSImage gsImage, int maskLength)
	{
		return GrayScaleOpener.compute(gsImage, maskLength);
	}

	public static GSImage grayScaleClose(GSImage gsImage, int maskLength)
	{
        return GrayScaleCloser.compute(gsImage, maskLength);
    }

	public static GSImage maskAndScale(GSImage gsImage, Mask mask)
	{
		MaskerAdderScaler op = new MaskerAdderScaler(mask);
		return op.compute(gsImage);
		//return masker.compute();
	}

	public static GSImage mask(GSImage gsImage, Mask mask)
	{
		Masker<GSImage> masker = new Masker<GSImage>(mask);
		return masker.compute(gsImage);
		//return masker.compute();
	}

/*
	public static GSImage mask(GSImage grayScaleImage, Mask mask, int maskFactor)
	{
		Masker<GSImage> masker = new Masker<GSImage>(mask, maskFactor);
		return masker.compute(grayScaleImage);
		//return masker.compute();
	}

	public static GSImage mask(GSImage grayScaleImage, Mask mask, int maskFactor, int imageFactor)
	{
		Masker<GSImage> masker = new Masker<GSImage>(mask, maskFactor, imageFactor);
		return masker.compute(grayScaleImage);
		//return masker.compute();
	}


	public static GSImage maskAndScale(GSImage grayScaleImage, Mask mask, int maskFactor, int imageFactor)
	{
		MaskerAdderScaler op = new MaskerAdderScaler(mask, maskFactor, imageFactor);
		return op.compute(grayScaleImage);
		//return masker.compute();
	}
*/
	public static GSImage getScaledLaplacian(GSImage gsImage)
	{
		Masker<GSImage> masker = new Masker<GSImage>(Mask.Sharpening.SecondDerivative.laplacianMask);
		return masker.compute(gsImage);
		//return masker.compute();
	}
/*
// Input the image from file:
BufferedImage inputImage = ImageIO.read(new File(inputPathFileName));
GSImage gsImage = new GSImage(inputImage); //This gives you GrayScaleImage. Use BImage if you need BinayImage.

// Process the image object (gsImage) here.
// You can use the classes that available.
// ...


// Output the image from file:
BufferedImage outputImage = gsImage.getBufferedImage()
ImageIO.write(outputImage , "JPG", file);
*/

    public static GSImage readGSImage(String pathFileName) throws NotAnImageFileException {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(pathFileName));
//            System.out.println(bufferedImage);
            assert bufferedImage != null;
            GSImage image = new GSImage(bufferedImage);
            assert image != null;
//            GSImage image = new GSImage(bufferedImage);
//            System.out.println(image);
            return image;
            //return new Image(bufferedImage);
        } catch (IOException ioe) {
            throw new NotAnImageFileException();
        }
    }

    public static BImage readBImage(String pathFileName) throws NotAnImageFileException {
        try
        {
            final BufferedImage bufferedImage = ImageIO.read(new File(pathFileName));
            return new BImage(bufferedImage);
            //return new Image(bufferedImage);
        }
        catch (IOException ioe)
        {
            throw new NotAnImageFileException();
        }
    }

	public static BufferedImage getImage(String pathFileName) throws NotAnImageFileException
	{
		try
		{
			return ImageIO.read(new File(pathFileName));
			//return new Image(bufferedImage);
		}
		catch (IOException ioe)
		{
			throw new NotAnImageFileException();
		}
	}

	public static GSImage getGrayScaleImage(String pathFileName)
			throws NotAnImageFileException
	{
		return new GSImage(getImage(pathFileName));
	}

	public static GSImage getScaled(GSImage image)
	{
		return Scaler.instance().compute(image);
	}

	public static GSImage getThresholded(GSImage image, int threshold)
	{
		Cutter cutter = new Cutter(0, threshold);
		return cutter.compute(image);
	}

	public static GSImage getThresholdedAndScaled(GSImage image, int threshold)
	{
		Cutter cutter = new Cutter(0, threshold);
		GSImage thresholded = cutter.compute(image);
		Scaler scaler = Scaler.instance();
		return scaler.compute(thresholded);
	}

	public static GSImage getAddedAndScaled(GSImage image1, GSImage image2)
	{
		return ImageUtil.getScaled(ImageUtil.getAdded(image1, image2));
	}

	public static GSImage getAddedAndScaled(GSImage[] images) {
		return AdderScaler.instance().compute(images);
	}

	public static GSImage getAdded(GSImage[] images) {
        Adder<GSImage> adder = Adder.instance();
		return adder.compute(images);
	}

	public static GSImage getAdded(GSImage image1, GSImage image2) {
        Adder<GSImage> adder = Adder.instance();
		return adder.compute(image1, image2);
	}

    public static void main(String[] args) throws NotAnImageFileException {
        GSImage gsImage = ImageUtil.readGSImage("/home/mohsen/lenna.jpg");
        ImageUtil.showImage("EdgeImage", gsImage);
    }
}
