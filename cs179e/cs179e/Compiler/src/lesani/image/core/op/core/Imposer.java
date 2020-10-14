package lesani.image.core.op.core;

import lesani.image.core.image.Image;
import lesani.image.core.op.core.base.BOp;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen's Desktop
 * Date: May 2, 2007
 * Time: 2:55:00 PM
 */

public class Imposer<T extends Image> extends BOp<T> {

    private static Imposer theInstance = new Imposer();

    public static <T extends Image> Imposer<T> instance() {
        return theInstance;
    }

    private Imposer() {
    }


    @Override
    public T compute(T image1, T image2) {

        T resultImageArray = Image.createImageOfType(image1);
        int width = image1.getWidth();
        int height = image1.getHeight();

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
                final int value1 = image1.get(i, j);
                final int value2 = image2.get(i, j);
                if (value2 != 0)
                    resultImageArray.set(i, j, value2);
                else
                    resultImageArray.set(i, j, value1);
            }
		return resultImageArray;
	}

    public static <T extends Image> T process(T image1, T image2) {
        return (T) Imposer.instance().compute(image1, image2);
    }
}
