package lesani.image.core.op.core.base;

import lesani.image.core.image.Image;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 12:40:12 AM
 */

public abstract class BOp<T extends Image> {

    public abstract T compute(T image1, T image2);

}