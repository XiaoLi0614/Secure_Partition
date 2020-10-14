package lesani.image.core.op.core;

import lesani.image.core.image.Image;
import lesani.image.core.op.core.base.UOp;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 15, 2005
 * Time: 12:16:44 PM
 */

public class Inverter<T extends Image> extends UOp<T>
{
    private static Inverter theInstance = new Inverter();

    public static <T extends Image> Inverter<T> instance() {
        return theInstance;
    }

    private Inverter() {
    }

	public T compute(T image) {
        T complementedImage = Image.createImageOfType(image);
		for (int i = 0; i < image.getWidth(); i++)
			for (int j = 0; j < image.getHeight(); j++)
                complementedImage.set(i, j, image.maxIntensity() - image.get(i, j));
    	return complementedImage;
	}

    public static <T extends Image> T process(T image) {
        return (T) Inverter.instance().compute(image);
    }

}



