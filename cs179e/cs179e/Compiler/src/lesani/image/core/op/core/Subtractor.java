package lesani.image.core.op.core;

import lesani.image.core.image.Image;
import lesani.image.core.op.core.base.BOp;
import lesani.image.core.op.morphology.exception.NotSameSizeImageException;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 15, 2005
 * Time: 4:11:11 PM
 */

public class Subtractor<T extends Image> extends BOp<T> {
    private static Subtractor theInstance = new Subtractor();

    public static <T extends Image> Subtractor<T> instance() {
        return theInstance;
    }

    private Subtractor() {
    }

    public T compute(T image1, T image2) throws NotSameSizeImageException {
		if ((image1.getWidth() != image2.getWidth()) ||
                (image1.getHeight() != image2.getHeight()))
			throw new NotSameSizeImageException();

        T difference = Image.createImageOfType(image1);

		for (int i = 0; i < image1.getWidth(); i++)
			for (int j = 0; j < image1.getHeight(); j++)
					difference.set(i, j, image1.get(i, j) - image2.get(i, j));
     	return difference;
	}
}
