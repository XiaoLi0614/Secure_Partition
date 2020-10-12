package lesani.image.core.op.core;

import lesani.image.core.image.Image;
import lesani.image.core.op.core.base.MOp;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen's Desktop
 * Date: May 2, 2007
 * Time: 2:55:00 PM
 */

public class Adder<T extends Image> extends MOp<T> {

    private static Adder theInstance = new Adder();

    public static <T extends Image> Adder<T> instance() {
        return theInstance;
    }

    private Adder() {
    }


    @Override
    public T compute(T[] images) {
		if (images == null || images.length == 0)
			return null;

        int width = Integer.MIN_VALUE;
		for (T image : images)
			width = Math.max(width, image.getWidth());

		int height = Integer.MIN_VALUE;
		for (T image : images)
			height = Math.max(height, image.getHeight());

        T resultImageArray = Image.createImageOfType(images[0], width, height);

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				for (T image : images)
					resultImageArray.valueUncheckedSet(
                            i, j,
                            resultImageArray.get(i, j) + getPixelValue(image, i, j)
                    );
		return resultImageArray;
	}

	private int getPixelValue(T image, int i, int j) {
		if (i >= image.getWidth() || j >= image.getHeight())
			return 0;
		return image.get(i, j);
	}

    public static <T extends Image> T process(T[] images) {
        return (T)Adder.instance().compute(images);
    }
}
