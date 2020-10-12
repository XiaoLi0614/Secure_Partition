package lesani.image.core.op.core.base;

import lesani.image.core.image.Image;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 12:40:12 AM
 */

public abstract class MOp<T extends Image> extends BOp<T> {

    public abstract T compute(T[] images);


    @Override
    public T compute(T image1, T image2) {
        return compute((T[]) new Image[] {image1, image2});
    }
}