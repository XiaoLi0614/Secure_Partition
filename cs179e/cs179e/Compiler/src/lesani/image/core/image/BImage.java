package lesani.image.core.image;

//import android.graphics.Bitmap;
//import android.graphics.Color;

//import android.graphics.Bitmap;
//import android.graphics.Color;

import java.awt.image.*;


/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Jan 24, 2005
 * Time: 8:38:25 PM
 */

public class BImage extends Image
{
    public static final int WHITE_INTENSITY = 1;
    public static final int BLACK_INTENSITY = 0;
    // 1 is black here
    public static final int BLACK_BIT = 1;
	public static final int WHITE_BIT = 0;


    public BImage(int width, int height) {
		super(width, height);
	}

    public BImage(int[][] array) {
        super(array);
    }

	public BImage(BufferedImage bufferedImage) {
		super(bufferedImage);
	}

//	public BImage(Bitmap bitmap) {
//		super(bitmap);
//	}

    protected int finalGet(int x, int y) {
        if (super.finalGet(x, y) == WHITE_INTENSITY)
            return WHITE_BIT;
        else
            return BLACK_BIT;
    }

    protected void finalSet(int x, int y, int value) {
        if (value == WHITE_BIT)
            super.finalSet(x, y, WHITE_INTENSITY);
        else //if (value == BLACK_BIT)
            super.finalSet(x, y, BLACK_INTENSITY);
    }

    public int getIntensity(int x, int y) {
        if (get(x, y) == WHITE_BIT)
            return WHITE_INTENSITY;
        else
            return BLACK_INTENSITY;
    }

    public void setIntensity(int x, int y, int value) {
        int bit;
        if (value == WHITE_INTENSITY)
            bit = WHITE_BIT;
        else
            bit = BLACK_BIT;
        super.set(x, y, bit);
    }

    public int maxIntensity() {
        return WHITE_INTENSITY;
    }

    public void set(int x, int y) {
        setIntensity(x, y, BLACK_INTENSITY);
    }
    public void unset(int x, int y) {
        setIntensity(x, y, WHITE_INTENSITY);
    }

    public BImage getBlock(int x, int y, int windowWidth, int windowHeight) {
        return (BImage)super.getBlock(x, y, windowWidth, windowHeight);
    }

    protected int bufferedImageGet(BufferedImage bufferedImage, int x, int y) {

        int[] samples = new int[bufferedImage.getRaster().getNumBands()];
        bufferedImage.getRaster().getPixel(x, y, samples);

        int sampleSum = 0;
//        System.out.print("(");
        for (int sample : samples) {
            sampleSum += sample;
//            System.out.print(samples[k]);
        }
//        System.out.print("->");
        int average = sampleSum / samples.length;
//        System.out.print(average);
//        System.out.print(")");

        int value;
        if (average > (255 / 2))
            value = WHITE_INTENSITY;
        else
            value = BLACK_INTENSITY;
        return value;
    }
//    protected int bitmapGet(Bitmap bitmap, int x, int y) {
//        int color = bitmap.getPixel(x, y);
//        int red = Color.red(color);
//        int green = Color.green(color);
//        int blue = Color.blue(color);
//
//        int grayScale = (red + green + blue) / 3;
//        int value;
//        if (grayScale > (255 / 2))
//            value = WHITE_INTENSITY;
//        else
//            value = BLACK_INTENSITY;
//        return value;
//    }

    public BImage intensityValued() {
        return new IntensityValuedBImage(this);
    }

    @Override
    protected int getBufferedImageType() {
        return BufferedImage.TYPE_BYTE_BINARY;
    }

}


/*
        int[] samples = new int[1];
        bufferedImage.getRaster().getPixel(x, y, samples);
        return samples[0];
*/
