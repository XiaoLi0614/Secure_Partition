package lesani.image.core.image;

//import android.graphics.Bitmap;
//import android.graphics.Color;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Feb 12, 2005
 * Time: 4:48:32 PM
 */

public class GSImage extends Image implements Serializable {

	public GSImage(int width, int height) {
		super(width, height);
	}

    public GSImage(int[][] array) {
        super(array);
    }

	public GSImage(BufferedImage bufferedImage) {
		super(bufferedImage);
	}
//	public GSImage(Bitmap bitmap) {
//		super(bitmap);
//	}

    public GSImage(BImage bImage) {
        super(bImage.getWidth(), bImage.getHeight());

        final int width = bImage.getWidth();
        final int height = bImage.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; i < height; j++) {
                if (bImage.get(i, j) == BImage.WHITE_BIT)
                    set(i, j, maxIntensity());
                else
                    set(i, j, 0);
            }
        }
    }

    @Override
    public int getIntensity(int x, int y) {
        return super.get(x, y);
    }

    @Override
    public void setIntensity(int x, int y, int value) {
        super.set(x, y, value);
    }

    public int maxIntensity() {
        return 255;
    }

    public GSImage getBlock(int x, int y, int windowWidth, int windowHeight) {
        return (GSImage)super.getBlock(x, y, windowWidth, windowHeight);
    }

    protected int bufferedImageGet(BufferedImage bufferedImage, int x, int y) {
        Raster raster = bufferedImage.getRaster();
        int[] samples = new int[raster.getNumBands()];
        bufferedImage.getRaster().getPixel(x, y, samples);

        int sampleSum = 0;
        for (int sample : samples) sampleSum += sample;
        return sampleSum / samples.length;
    }

//    protected int bitmapGet(Bitmap bitmap, int x, int y) {
//        int color = bitmap.getPixel(x, y);
//        int red = Color.red(color);
//        int green = Color.green(color);
//        int blue = Color.blue(color);
//
//        return (red + green + blue) / 3;
//    }

    public GSImage intensityValued() {
        return this;
    }

    @Override
    protected int getBufferedImageType() {
        return BufferedImage.TYPE_BYTE_GRAY;
    }

    public String toString() {
        String s = "";
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                s += get(j, i) + " ";
            }
            s += "\n";
        }
        return s;
    }

    public void print() {
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(get(j, i) + " ");
            }
            System.out.println("");
        }
    }
}


/*
	protected GSImage(int width, int height, int typeByteBinary)
	{
		super(width, height, typeByteBinary);
	}
*/

/*
	public GSImage(int[][] imageArray) {
		this(imageArray.length, imageArray[0].length);
		int width = imageArray.length;
		int height = imageArray[0].length;
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				set(i, j, imageArray[i][j]);
	}

	// This is not needed because we have conversion from Image.
	public GSImage(BImage bImage) {
		super(bImage.getWidth(), bImage.getHeight(), Image.GRAY_SCALE_IMAGE);

		for (int i = 0; i < bImage.getWidth(); i++)
			for (int j = 0; j < bImage.getHeight(); j++)
			if (bImage.get(i, j) == BImage.BLACK_BIT)
		        set(i, j, 0);
			else
				set(i, j, 255);
	}
*/