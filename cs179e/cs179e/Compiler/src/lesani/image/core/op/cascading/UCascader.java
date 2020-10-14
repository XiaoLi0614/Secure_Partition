package lesani.image.core.op.cascading;

import lesani.image.core.image.Image;
import lesani.image.core.op.core.base.UOp;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 4:24:33 PM
 */

public class UCascader<T extends Image> extends UOp<T> {

    private UOp<T>[] operations;

    public UCascader(UOp<T>[] operations) {
        this.operations = operations;
    }

    public T compute(T image) {
        T currentImage = image;
        for (UOp<T> operation : operations) {
            currentImage = operation.compute(currentImage);
        }
        return currentImage;
    }
}
