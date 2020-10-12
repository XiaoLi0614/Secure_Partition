package lesani.image.core.image;

//import android.graphics.Bitmap;

import java.awt.image.BufferedImage;
import java.io.Serializable;

//import mobiprog.system.Message;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Feb 13, 2005
 * Time: 4:43:54 PM
 */

public abstract class Image /*extends Message*/ implements Cloneable, Serializable {

	public static int GRAY_SCALE_IMAGE = BufferedImage.TYPE_BYTE_GRAY;
	public static int BINARY_IMAGE = BufferedImage.TYPE_BYTE_BINARY;

    /*private */
    public int[][] array;

    public Image(int width, int height) {
		array = new int[width][height];
	}

    protected Image(int[][] array) {
        this.array = array;
    }


    public Image(BufferedImage bufferedImage) {
        this(bufferedImage.getWidth(), bufferedImage.getHeight());

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++)
            {
//                System.out.print("(");
                int value = bufferedImageGet(bufferedImage, i, j);
//                System.out.print("" + value + ")");
                setIntensity(i, j, value);
            }
//            System.out.println("");
        }
    }
/*
    public Image(Bitmap bitmap) {
        this(bitmap.getWidth(), bitmap.getHeight());
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++)
            {
                int value = bitmapGet(bitmap, i, j);
                setIntensity(i, j, value);
            }
        }
    }
*/
    public BufferedImage getBufferedImage() {
        // ToDo: This does not work for BImage

        BufferedImage bufferedImage =
                new BufferedImage(getWidth(), getHeight(), getBufferedImageType());
        final int width = getWidth();
        final int height = getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                final int intensity = intensityValued().get(i, j);
                bufferedImageSet(bufferedImage, i, j, intensity);

//                System.out.print(intensity + " ");
            }
//            System.out.println("");
        }
        return bufferedImage;
    }
/*
    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);

        final int width = getWidth();
        final int height = getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int intensity = intensityValued().get(i, j);
                int value = intensity;
                for (int k = 0; k < 2; k++) {
                    intensity *= 256;
                    value += intensity;
                }
                value += 255*256*256*256;
                bitmap.setPixel(i, j, value);
            }
        }
        return bitmap;
    }
*/
    
    protected abstract int getBufferedImageType();


    public int getWidth() {
        return array.length;
    }
    public int getHeight() {
        return array[0].length;
    }

    public int get(int x, int y) {
        checkIndexBoundary(x, y);
        return finalGet(x, y);
    }

    public void setRangeUnchecked(int x, int y, int value) {
        valueUncheckedSet(x, y, value);
    }

    public void set(int x, int y, int value) {
        checkValueRange(value);
        valueUncheckedSet(x, y, value);
    }

    public void thresholdSet(int x, int y, int value) {
        if (value < 0)
            value = 0;
        else if (value > maxIntensity())
            value = maxIntensity();
        valueUncheckedSet(x, y, value);
    }
    public void valueUncheckedSet(int x, int y, int value) {
        checkIndexBoundary(x, y);
        finalSet(x, y, value);
    }

    protected int finalGet(int x, int y) {
        return array[x][y];
    }
    protected void finalSet(int x, int y, int value) {
        array[x][y] = value;
    }

    public abstract int getIntensity(int x, int y);

    public abstract void setIntensity(int x, int y, int value);

    public abstract int maxIntensity();

    public int[][] getArray() {
        int width = getWidth();
        int height = getHeight();

        int[][] imageArray = new int[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                imageArray[i][j] = get(i, j);

        return imageArray;
    }

    public Image getBlock(int x, int y, int windowWidth, int windowHeight) {
        Image block = createImageOfType(this);
        //Image block = new Image(windowWidth, windowHeight);

        for (int i = 0; i < windowWidth; i++)
            for (int j = 0; j < windowHeight; j++)
            {
                int value = 0;
                if ((x + i < getWidth()) && (y + j < getHeight()))
                    value = get(x + i, y + j);
                block.set(i, j, value);
            }
        return block;
    }

    public void setBlock(int x, int y, Image blockImage) {
        int windowWidth = blockImage.getWidth();
        int windowHeight = blockImage.getHeight();

        for (int i = 0; i < windowWidth; i++)
            for (int j = 0; j < windowHeight; j++)
                if ((x + i < getWidth()) && (y + j < getHeight()))
                    set(x + i, y + j, blockImage.get(i, j));
    }

    public Object clone() {
        Image image = null;
        try {
            image = (Image) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return image;
        //return getBlock(0, 0, getWidth(), getHeight());
    }

    protected void checkIndexBoundary(int x, int y) throws RuntimeException {
        if ((x < 0) || (x >= getWidth()) || (y < 0) || (y >= getHeight())) {
//            System.out.println("x: " + x);
//            System.out.println("width: " + getWidth());
//            System.out.println("y: " + y);
//            System.out.println("height: " + getHeight());
            throw new RuntimeException(
                    "\n" +
                    "x: " + x + "\n" +
                    "width: " + getWidth() + "\n" +
                    "y: " + y + "\n" +
                    "height: " + getHeight()
            );
        }

    }

    protected void checkValueRange(int value) {
        if ((value < 0) || (value > maxIntensity()))
            throw new RuntimeException();
    }

    public abstract Image intensityValued();

    protected abstract int bufferedImageGet(BufferedImage bufferedImage, int x, int y);
//    protected abstract int bitmapGet(Bitmap bitmap, int x, int y);

    protected void bufferedImageSet(BufferedImage bufferedImage, int x, int y, int intensity) {
        int[] samples = new int[1];
        samples[0] = intensity;
        bufferedImage.getRaster().setPixel(x, y, samples);
    }

    public static <T extends Image> T createImageOfType(T sampleImage) {
        int width = sampleImage.getWidth();
        int height = sampleImage.getHeight();
        return createImageOfType(sampleImage, width, height);
    }
    public static <T extends Image> T createImageOfType(T sampleImage, int width, int height) {
        T image;
        if (sampleImage instanceof BImage)
            image = (T) new BImage(width, height);
        else //if (image instanceof BImage)
            image = (T) new GSImage(width, height);
        return image;
    }

    public void drawCircle(int radius, int xc, int yc, int value) {
        try {
        int x = 0;
        int y = radius;
        int d = 1 - radius;

        set(xc, yc+y, value);
        set(xc, yc-y, value);
        set(xc+y, yc, value);
        set(xc-y, yc, value);

        while (x < y - 1) {
            x = x + 1;
            if ( d < 0 )
                d = d + x + x + 1;
            else {
                y = y - 1;
                int a = x - y + 1;
                d = d + a + a;
            }
            set( x+xc,  y+yc, value);
            set( y+xc,  x+yc, value);
            set( y+xc, -x+yc, value);
            set( x+xc, -y+yc, value);
            set(-x+xc, -y+yc, value);
            set(-y+xc, -x+yc, value);
            set(-y+xc,  x+yc, value);
            set(-x+xc,  y+yc, value);
        }
        } catch (RuntimeException e) {
            System.out.println("Drawing out");
        }
    }

    public void drawLine(int x0, int x1, int y0, int y1, int value) {
        try {
        int dy = y1 - y0;
        int dx = x1 - x0;
        float t = (float) 0.5;                      // offset for rounding

        set(x0, y0, value);
        if (Math.abs(dx) > Math.abs(dy)) {          // slope < 1
            float m = (float) dy / (float) dx;      // compute slope
            t += y0;
            dx = (dx < 0) ? -1 : 1;
            m *= dx;
            while (x0 != x1) {
                x0 += dx;                           // step to next x value
                t += m;                             // add slope to y value
                set(x0, (int) t, value);
            }
        } else {                                    // slope >= 1
            float m = (float) dx / (float) dy;      // compute slope
            t += x0;
            dy = (dy < 0) ? -1 : 1;
            m *= dy;
            while (y0 != y1) {
                y0 += dy;                           // step to next y value
                t += m;                             // add slope to x value
                set((int) t, y0, value);
            }
        }
        } catch (RuntimeException e) {
            System.out.println("Drawing out");
        }
    }
/*
    {
        try {
        boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
        if (steep) {
            int temp = x0;
            x0 = y0;
            y0 = temp;

            temp = x1;
            x1 = y1;
            y1 = temp;
        }
        if (x0 > x1) {
            int temp = x0;
            x0 = x1;
            x1 = temp;

            temp = y0;
            y0 = y1;
            y1 = temp;
        }
        int deltax = x1 - x0;
        int deltay = Math.abs(y1 - y0);
        int error = deltax / 2;
        int ystep;
        int y = y0;
        if (y0 < y1)
            ystep = 1;
        else
            ystep = -1;
        if (x1 >= x0)
            for (int x=x0; x < x1; x++) {
                if (steep)
                    set(y, x, value);
                else
                    set(x, y, value);
                error = error - deltay;
                if (error < 0) {
                    y = y + ystep;
                    error = error + deltax;
                }
            }
        else
            for (int x=x0; x < x1; x--) {
                if (steep)
                    set(y, x, value);
                else
                    set(x, y, value);
                error = error - deltay;
                if (error < 0) {
                    y = y + ystep;
                    error = error + deltax;
                }
            }
        } catch (RuntimeException e) {}
    }*/

}


/*
	public Image(BufferedImage bufferedImage)
	{
		super(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

		Raster raster = bufferedImage.getRaster();

		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();


		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				double[] samples = new double[raster.getNumBands()];
				raster.getPixel(j, i, samples);
				getRaster().setPixel(j, i, samples);
			}
		}

		//System.out.println(raster.getNumBands());
		//System.out.println(getRaster().getNumBands());

		//Raster newRaster = Raster.createWritableRaster(
		//		bufferedImage.getSampleModel(), raster.getDataBuffer(), new Point(0, 0));
		//setRaster(newRaster);

	}
*/

/*
	public Image(int width, int height, int imageType) {
		super(width, height, imageType);
	}
*/

